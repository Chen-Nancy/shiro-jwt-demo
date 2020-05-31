package com.nancy.shirojwtdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nancy.shirojwtdemo.constants.TokenConstants;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author chen
 * @date 2020/5/31 23:18
 */
public class JWTUtil {
    /**
     * 生成token
     *
     * @param userId 用户id
     * @return
     */
    public static String createToken(Long userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + TokenConstants.LOGIN_TIMEOUT);
            Algorithm algorithm = Algorithm.HMAC256(TokenConstants.SECRET);
            return JWT.create()
                    //附带userId信息
                    .withClaim(TokenConstants.CLAIM_KEY, userId)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 获取token中附带的userId
     *
     * @param token token令牌
     * @return
     */
    public static Long getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(TokenConstants.CLAIM_KEY).asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 校验token是否有效
     *
     * @param token token令牌
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TokenConstants.SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
