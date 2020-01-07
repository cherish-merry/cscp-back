package com.cscp.common.utils;

import com.cscp.common.support.ResultEnum;
import lombok.Data;

@Data
public class ViewException extends RuntimeException {
    private static final long serialVersionUID = 5720848396842316463L;
    private int code;

    public ViewException(String message) {
        super(message);
        this.code = ResultEnum.ERROR.getCode();
    }

    public ViewException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ViewException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
}
