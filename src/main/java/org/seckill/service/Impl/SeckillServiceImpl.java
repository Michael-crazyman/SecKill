package org.seckill.service.Impl;

import org.seckill.SeckillEnum.SeckillStateEnum;
import org.seckill.dao.SeckillDAO;
import org.seckill.dao.SuccessKilledDAO;
import org.seckill.dto.ExecutionSeckill;
import org.seckill.dto.Exposer;
import org.seckill.entity.SecKill;
import org.seckill.entity.SuccessKilled;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.seckill.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by lishaoxun on 2016/6/24.
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDAO seckillDAO;
    @Autowired
    private SuccessKilledDAO successKilledDAO;


    public List<SecKill> getSeckillList() {
        return seckillDAO.queryAll(0, 4);
    }

    public SecKill getSecKillById(long seckillId) {
        return seckillDAO.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        boolean isStart = false;
        SecKill secKill = this.getSecKillById(seckillId);
        if (secKill == null) {
            return new Exposer(seckillId, isStart);
        }
        long startTime = secKill.getStartTime().getTime();
        long endTime = secKill.getEndTime().getTime();
        long nowTime = new Date().getTime();
        if (nowTime > endTime || nowTime < startTime) {
            return new Exposer(seckillId, isStart, nowTime, startTime, endTime);
        }
        isStart = true;
        String md5 = MD5Util.getMD5(seckillId);
        return new Exposer(seckillId, isStart, md5);
    }
    @Transactional
    /**
     *
     * 不是所有的方法都需要事务
     * 如只有一条修改操作，只读操作不需要事务
     * 尽量减少http/RPC这样的操作在事务中完成
     */
    public ExecutionSeckill executeSecKill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillClosedException {

        if (md5 == null || !md5.equals(MD5Util.getMD5(seckillId))) {
            throw new SeckillException("Data Rewrite!");
        }
        try {
            //减库存 + 记录用户购买行为
            Date nowTime = new Date();
            int updateCount = seckillDAO.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                throw new SeckillException("seckill is close！");
            } else {
                int insertCount = successKilledDAO.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new SeckillClosedException("seckill repeated!");
                } else {
                    SuccessKilled successKilled = successKilledDAO.queryByIdWithSeckill(seckillId);
                    return new ExecutionSeckill(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }
        } catch (RepeatKillException e1) {
            throw e1;
        } catch (SeckillClosedException e2) {
            throw e2;
        } catch (SeckillException e3) {
            throw e3;
        } catch (Exception e) {
            throw new SeckillException("Inner error！" + e.getMessage());
        }
    }
}
