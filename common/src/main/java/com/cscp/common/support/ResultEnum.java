package com.cscp.common.support;

public enum ResultEnum
{
    UNKNOWN_ERROR(500,"未知错误"),
    SUCCESS(200,"操作成功"),
    ERROR(-2, "服务器端异常"),;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
