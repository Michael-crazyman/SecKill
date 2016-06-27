package org.seckill.entity;

import java.util.Date;

/**
 * Created by lishaoxun on 2016/6/19.
 */
public class SuccessKilled {
    private long seckillId;
    private long userPhone;
    private Date createTime;
    private short state;

    private SecKill seckill;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public SecKill getSeckill() {
        return seckill;
    }

    public void setSeckill(SecKill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", createTime=" + createTime +
                ", state=" + state +
                ", seckill=" + seckill +
                '}';
    }
}
