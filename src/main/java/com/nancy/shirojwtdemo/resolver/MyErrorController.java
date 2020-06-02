package com.nancy.shirojwtdemo.resolver;

import com.nancy.shirojwtdemo.constants.CodeConstants;
import com.nancy.shirojwtdemo.exception.MyException;
import com.nancy.shirojwtdemo.exception.NoLoginException;
import com.nancy.shirojwtdemo.exception.NoPermissionException;
import com.nancy.shirojwtdemo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author chen
 * @date 2020/5/31 23:32
 */
@Controller
public class MyErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private static final String NO_LOGIN_MSG = "未登录";
    private static final String NO_PERMISSION_MSG = "无权访问";
    private final ErrorAttributes errorAttributes;

    @Autowired
    public MyErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    @ResponseBody
    public void error(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        if (status == CodeConstants.NOT_FOUND_CODE) {
            throw new NotFoundException("资源未找到");
        }
        ServletWebRequest requestAttributes = new ServletWebRequest(request);
        Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
        String message = attr.get("message").toString();
        if (message == null) {
            throw new MyException("未知错误");
        }
        if (message.startsWith(NO_LOGIN_MSG)) {
            throw new NoLoginException(message);
        } else if (message.startsWith(NO_PERMISSION_MSG)) {
            throw new NoPermissionException(message);
        } else {
            throw new MyException(message);
        }
    }
}
