package org.seckill.exception;

/**
 * Created by lishaoxun on 2016/6/24.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
