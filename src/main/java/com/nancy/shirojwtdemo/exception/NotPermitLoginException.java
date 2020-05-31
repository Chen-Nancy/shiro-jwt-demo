package com.nancy.shirojwtdemo.exception;

/**
 * @author chen
 * @date 2020/5/31 23:30
 */
public class NotPermitLoginException extends MyException {
    public NotPermitLoginException(String msg) {
        super(msg);
    }
}
