package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 * Created by lishaoxun on 2016/6/19.
 */
public interface SuccessKilledDAO {
    /**
     *
     *插入购买明细，使用联合主键避免重复
     * @param seckillId
     * @param userPhone
     * @return 插入的结果集数量
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     *
     * 根据id查询SuccessKilled并携带秒杀商品实体对象
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
