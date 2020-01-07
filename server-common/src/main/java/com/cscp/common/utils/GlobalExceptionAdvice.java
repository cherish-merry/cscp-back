package com.cscp.common.utils;

import com.cscp.common.support.Result;
import com.cscp.common.support.ResultEnum;
import com.cscp.common.support.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/1/2 - 14:46
 */
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        e.printStackTrace();
        if (e instanceof ViewException) {
            ViewException viewException = (ViewException) e;
            return ResultUtil.error(viewException.getCode(), viewException.getMessage());
        } else {
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }
}
