package com.cscp.common.support;

public enum ResultEnum {
    UNKNOWN_ERROR(500, "Internal Server Error"),
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "ERROR"),
    POST(201, "CREATED"),
    DELETE(204, "NO CONTENT"),
    PUT(200, "SUCCESS"),
    GET(200, "SUCCESS"),
    NOT_FOUND(404, "NOT FOUND"),
    BAD_REQUEST(400, "BAD REQUEST"),
    NOT_AUTHORIZED(401, "NOT AUTHORIZED"),
    FORBIDDEN(403, "FORBIDDEN"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR");

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
