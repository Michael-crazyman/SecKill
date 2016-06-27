package org.seckill.dto;

/**
 * Created by lishaoxun on 2016/6/25.
 */
public class SeckillResult<T> {
    private boolean isSuccess;
    private T data;
    private String error;

    public SeckillResult(String error, boolean isSuccess) {
        this.error = error;
        this.isSuccess = isSuccess;
    }

    public SeckillResult(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
