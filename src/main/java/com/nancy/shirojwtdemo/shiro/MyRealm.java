package com.nancy.shirojwtdemo.shiro;

import com.nancy.shirojwtdemo.exception.NoLoginException;
import com.nancy.shirojwtdemo.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author chen
 * @date 2020/5/31 23:46
 */
@Component
public class MyRealm extends AuthorizingRealm {
    private static final Logger LOG = LoggerFactory.getLogger(MyRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        LOG.info("开始验证权限");
        String token = (String) principalCollection.getPrimaryPrincipal();
        Long userId = JWTUtil.getUserId(token);
        //查询权限，并加入授权器
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOG.info("开始验证身份");
        String token = (String) authenticationToken.getPrincipal();
        if (token == null) {
            throw new NoLoginException("未登录");
        }
        if (!JWTUtil.verify(token)) {
            throw new NoLoginException("未登录");
        }
        Long userId = JWTUtil.getUserId(token);
        if (userId == null) {
            throw new NoLoginException("未登录");
        }
        //查询账号是否被禁用
        //验证通过
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
