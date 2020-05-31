package com.nancy.shirojwtdemo.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author chen
 * @date 2020/5/31 23:45
 */
public class MyToken implements AuthenticationToken {
    private String token;

    public MyToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
