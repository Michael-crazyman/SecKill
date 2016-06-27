package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SecKill;

import java.util.Date;
import java.util.List;

/**
 * Created by lishaoxun on 2016/6/19.
 */
public interface SeckillDAO {
    /**
     *
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果更新返回>1,表示更新影响的行数
     */
    int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

    /**
     *根据Id查询商品
     * @param seckillId
     * @return
     */
    SecKill queryById(long seckillId);

    /**
     *根据偏移量查询商品列表即:
     *由于java不保存形参的表述,mybatis不知道offset和limit对应着哪个参数，所以需要添加@Param
     * @param offset
     * @param limit
     * @return
     */
    List<SecKill> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
