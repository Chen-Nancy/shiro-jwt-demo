package com.nancy.shirojwtdemo.resolver;

import com.nancy.shirojwtdemo.constants.CodeConstants;
import com.nancy.shirojwtdemo.exception.NoLoginException;
import com.nancy.shirojwtdemo.exception.NoPermissionException;
import com.nancy.shirojwtdemo.exception.NotFoundException;
import com.nancy.shirojwtdemo.exception.NotPermitLoginException;
import com.nancy.shirojwtdemo.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * @author chen
 * @date 2020/5/31 23:37
 */
@RestControllerAdvice
public class MyExceptionResolver {
    private static final Logger LOG = LoggerFactory.getLogger(MyExceptionResolver.class);

    @ExceptionHandler(NoLoginException.class)
    public Result noLoginExceptionResolver(NoLoginException e) {
        LOG.error(e.getMessage(), e);
        return new Result(CodeConstants.NO_LOGIN_CODE, "未登录");
    }

    @ExceptionHandler(NotPermitLoginException.class)
    public Result notPermitLoginExceptionResolver(NotPermitLoginException e) {
        LOG.error(e.getMessage(), e);
        return new Result(CodeConstants.NOT_PERMIT_LOGIN_CODE, "账号已被禁用");
    }

    @ExceptionHandler(NoPermissionException.class)
    public Result noPermissionExceptionResolver(NoPermissionException e) {
        LOG.error(e.getMessage(), e);
        return new Result(CodeConstants.NO_PERMISSION_CODE, "无权访问");
    }

    @ExceptionHandler(BindException.class)
    public Result bindExceptionResolver(BindException e) {
        LOG.error(e.getMessage(), e);
        return new Result(CodeConstants.PARAMETER_ERROR_CODE, "参数错误");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionResolver(ConstraintViolationException e) {
        LOG.error(e.getMessage(), e);
        return new Result(CodeConstants.PARAMETER_ERROR_CODE, "参数错误");
    }

    @ExceptionHandler(NotFoundException.class)
    public Result notFoundExceptionResolver(NotFoundException e) {
        LOG.error(e.getMessage(), e);
        return new Result(CodeConstants.NOT_FOUND_CODE, "资源未找到");
    }

    @ExceptionHandler(Exception.class)
    public Result exceptionResolver(Exception e) {
        LOG.error(e.getMessage(), e);
        return new Result(CodeConstants.SYSTEM_ERROR_CODE, "系统错误：" + e.getMessage());
    }
}
