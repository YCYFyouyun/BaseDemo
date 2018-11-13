package com.template.base.net;

import java.io.Serializable;


/**
 * 数据请求的基础模型
 */
public class RequestBean implements Serializable {

    private int returnCode; // 返回状态码

    private String msg; // 返回信息

    private String data; // 返回结果

    private String timestamp; // 时间戳

    //private boolean success; // 调用成功或失败
    
    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}