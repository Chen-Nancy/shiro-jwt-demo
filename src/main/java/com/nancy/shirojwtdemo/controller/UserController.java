package com.nancy.shirojwtdemo.controller;

import com.nancy.shirojwtdemo.constants.CodeConstants;
import com.nancy.shirojwtdemo.exception.NotPermitLoginException;
import com.nancy.shirojwtdemo.model.Result;
import com.nancy.shirojwtdemo.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author chen
 * @date 2020/5/31 23:59
 */
@RestController
@Validated
@RequestMapping("user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("login")
    public Result login(@NotBlank String username, @NotBlank String password) {
        if ("admin".equals(username)) {
            return new Result(CodeConstants.SUCCESS_CODE, "success").object(JWTUtil.createToken(1L));
        }
        if ("root".equals(username)) {
            throw new NotPermitLoginException("此账号已被禁用：" + username);
        }
        return new Result(CodeConstants.ERROR_CODE, "登录失败");
    }

    @RequestMapping("getCode")
    public Result getCode() {
        return new Result(CodeConstants.SUCCESS_CODE, "success").object(123);
    }
}
