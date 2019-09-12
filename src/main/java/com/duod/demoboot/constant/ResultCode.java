package com.duod.demoboot.constant;

public enum ResultCode {

    NEED_LOGIN(-2,"未登录！请先登录！"),
    NOT_PERMIT(-3,"拒绝访问，没有权限");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
