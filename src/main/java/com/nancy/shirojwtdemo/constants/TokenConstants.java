package com.nancy.shirojwtdemo.constants;

/**
 * @author chen
 * @date 2020/5/31 23:21
 */
public class TokenConstants {
    /**
     * token过期时间-毫秒
     */
    public static final int LOGIN_TIMEOUT = 6 * 60 * 60 * 1000;

    /**
     * 存token的headerName
     */
    public static final String HEADER_NAME = "authorize";

    /**
     * jwt秘钥
     */
    public static final String SECRET = "zPzB8BIEwiOBJbQNPttUYg==";

    /**
     * jwt附带userId的key
     */
    public static final String CLAIM_KEY = "userId";
}
