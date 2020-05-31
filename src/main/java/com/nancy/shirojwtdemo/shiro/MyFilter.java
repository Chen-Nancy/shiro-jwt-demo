package com.nancy.shirojwtdemo.shiro;

import com.nancy.shirojwtdemo.constants.TokenConstants;
import com.nancy.shirojwtdemo.constants.UriConstants;
import com.nancy.shirojwtdemo.exception.NoPermissionException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chen
 * @date 2020/5/31 23:49
 */
@Component
public class MyFilter extends BasicHttpAuthenticationFilter {
    private static final Logger LOG = LoggerFactory.getLogger(MyFilter.class);
    /**
     * 此集合中的uri无需登录即可访问
     */
    private static final List<String> NO_LOGIN_URI_LIST = new ArrayList<>();
    /**
     * 此集合中的uri无需授权即可访问
     */
    private static final List<String> NO_PERMISSION_URI_LIST = new ArrayList<>();

    static {
        NO_LOGIN_URI_LIST.add(UriConstants.WEB_ICO_URI);
        NO_LOGIN_URI_LIST.add(UriConstants.GET_CODE_URI);
        NO_LOGIN_URI_LIST.add(UriConstants.LOGIN_URI);
        NO_LOGIN_URI_LIST.add(UriConstants.LOGIN_PAGE_URI);

        NO_PERMISSION_URI_LIST.add(UriConstants.INDEX_PAGE_URI);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = new String(req.getRequestURL());
        LOG.info("访问URL：" + url);
        String uri = req.getRequestURI();
        //无需登录，放行
        for (String s : NO_LOGIN_URI_LIST) {
            if (uri.startsWith(s)) {
                return true;
            }
        }
        //登录验证
        String token = req.getHeader(TokenConstants.HEADER_NAME);
        getSubject(request, response).login(new MyToken(token));
        //无需授权，放行
        for (String s : NO_PERMISSION_URI_LIST) {
            if (uri.startsWith(s)) {
                return true;
            }
        }
        //授权验证
        boolean flag = getSubject(request, response).isPermitted(uri);
        if (!flag) {
            throw new NoPermissionException("无权访问：" + url);
        }
        return true;
    }
}
