package com.duod.demoboot.domain;

import com.duod.demoboot.constant.ResultCode;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用于controller返回数据
 * @Author: duod
 * @Date: 2019/9/12
 */
public class JsonDataDTO implements Serializable {
    /**
     * 状态 代码
     */
    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;

    private JsonDataDTO(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    private JsonDataDTO(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static JsonDataDTO buildSuccess(Integer code, String msg){
        return new JsonDataDTO(code,null,msg);
    }

    public static JsonDataDTO buildSuccess(ResultCode needLogin) {
        return new JsonDataDTO(needLogin);
    }

    public static JsonDataDTO buildSuccess(Object obj) {
        return new JsonDataDTO(0,obj,"success");
    }

    public static JsonDataDTO buildError(ResultCode resultCode) {
        return new JsonDataDTO(resultCode.getCode(),null,resultCode.getMsg());
    }


}
