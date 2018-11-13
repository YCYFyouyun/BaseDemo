package com.template.base.net;

public class RequestError extends RuntimeException {

    public RequestError(RequestBean bean) {
        super(bean.getMsg());
        returnCode = bean.getReturnCode();
        msg = bean.getMsg();
        data = bean.getData();
        timestamp = bean.getTimestamp();
    }

    public RequestError(int returnCode, String msg) {
        super(msg);
        this.returnCode = returnCode;
        this.msg = msg;
    }

    private int returnCode; // 返回状态码

    private String msg; // 返回信息

    private String data; // 返回结果

    private String timestamp; // 时间戳

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