package com.nancy.shirojwtdemo.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author chen
 * @date 2020/5/31 23:29
 */
public class NoLoginException extends AuthenticationException {
    public NoLoginException(String msg) {
        super(msg);
    }
}
