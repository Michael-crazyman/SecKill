package org.seckill.dto;

/**
 * Created by lishaoxun on 2016/6/24.
 */
public class Exposer {

    private long seckillId;
    //是否开启秒杀
    private boolean isStart;

    //一种加密措施
    private String md5;
    //系统当前时间
    private long now;
    //秒杀开启时间
    private long startTime;
    //秒杀结束时间
    private long endTime;

    public Exposer(long seckillId, boolean isStart, String md5) {
        this.seckillId = seckillId;
        this.isStart = isStart;
        this.md5 = md5;
    }

    public Exposer(long seckillId, boolean isStart, long now, long startTime, long endTime) {
        this.seckillId = seckillId;
        this.isStart = isStart;
        this.now = now;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Exposer(long seckillId, boolean isStart) {
        this.seckillId = seckillId;
        this.isStart = isStart;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "seckillId=" + seckillId +
                ", isStart=" + isStart +
                ", md5='" + md5 + '\'' +
                ", now=" + now +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
