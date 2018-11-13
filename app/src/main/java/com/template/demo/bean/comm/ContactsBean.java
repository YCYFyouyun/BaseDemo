package com.template.demo.bean.comm;

/**
 * 联系方式
 */
public class ContactsBean {

    private String id;
    private int type; //类型（1固话，2手机，3QQ）
    private String number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
