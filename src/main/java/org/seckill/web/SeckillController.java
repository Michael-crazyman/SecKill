package org.seckill.web;

import org.seckill.SeckillEnum.SeckillStateEnum;
import org.seckill.dto.ExecutionSeckill;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by lishaoxun on 2016/6/25.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<SecKill> list = seckillService.getSeckillList();

        model.addAttribute("list", list);

        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        SecKill secKill = seckillService.getSecKillById(seckillId);
        if (secKill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("secKill", secKill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<Exposer> expose(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(exposer, true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(e.getMessage(), false);
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SeckillResult<ExecutionSeckill> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,Long phone) {
        if(phone == null){
            return new SeckillResult<ExecutionSeckill>("未注册",false);
        }
        SeckillResult<ExecutionSeckill> result;
        try{
            ExecutionSeckill executionSeckill = seckillService.executeSecKill(seckillId,phone,md5);
            result =  new SeckillResult<ExecutionSeckill>(executionSeckill,true);
        }catch(RepeatKillException e){
            logger.error(e.getMessage(),e);
            ExecutionSeckill executionSeckill = new ExecutionSeckill(seckillId,SeckillStateEnum.REPEAT_KILL);
            result =  new SeckillResult<ExecutionSeckill>(executionSeckill,false);
        }catch(SeckillClosedException e){
            logger.error(e.getMessage(),e);
            ExecutionSeckill executionSeckill = new ExecutionSeckill(seckillId,SeckillStateEnum.END);
            result =  new SeckillResult<ExecutionSeckill>(executionSeckill,false);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            ExecutionSeckill executionSeckill = new ExecutionSeckill(seckillId,SeckillStateEnum.INNER_ERROR);
            result =  new SeckillResult<ExecutionSeckill>(executionSeckill,false);
        }
        return result;
    }

    @RequestMapping(value="/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        SeckillResult<Long> result = new SeckillResult<Long>(now.getTime(),true);
        return result;
    }


}
