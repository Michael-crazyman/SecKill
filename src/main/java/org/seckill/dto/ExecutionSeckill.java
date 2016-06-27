package org.seckill.dto;

import org.seckill.SeckillEnum.SeckillStateEnum;
import org.seckill.entity.SuccessKilled;

/**
 *
 *封装秒杀后的结果
 * Created by lishaoxun on 2016/6/24.
 */
public class ExecutionSeckill {

    private long seckillId;

    private int state;

    private String stateInfo;

    private SuccessKilled successKilled;
    //秒杀失败后的构造函数
    public ExecutionSeckill(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    //秒杀成功后的构造函数
    public ExecutionSeckill(long seckillId, SeckillStateEnum stateEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
