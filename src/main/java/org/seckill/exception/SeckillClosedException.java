package org.seckill.exception;

/**
 * Created by lishaoxun on 2016/6/24.
 */
public class SeckillClosedException extends SeckillException {
    public SeckillClosedException(String message) {
        super(message);
    }

    public SeckillClosedException(String message, Throwable cause) {
        super(message, cause);
    }
}
