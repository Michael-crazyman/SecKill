package org.seckill.service;

import org.seckill.dto.ExecutionSeckill;
import org.seckill.dto.Exposer;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillClosedException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by lishaoxun on 2016/6/20.
 */
public interface SeckillService {

    /**
     *
     * 生成秒杀产品的列表
     * @return
     */
    List<SecKill> getSeckillList();

    /**
     *
     * 根据ID拿到秒杀产品
     * @param seckillId
     * @return
     */

    SecKill getSecKillById(long seckillId);

    /**
     *生成并输出秒杀地址
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     *
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillClosedException
     */

    ExecutionSeckill executeSecKill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillClosedException;

}
