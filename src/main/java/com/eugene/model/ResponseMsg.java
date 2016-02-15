package com.eugene.model;

import java.io.Serializable;

/**
 * Created by DCLab on 12/22/2015.
 */
public class ResponseMsg implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;

    public ResponseMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseMsg() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
