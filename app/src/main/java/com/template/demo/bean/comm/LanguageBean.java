package com.template.demo.bean.comm;

/**
 * 语言
 */
public class LanguageBean {

    /**
     * id : 1
     * code : zh_CN
     * name : 中文简体
     * deleteFlag : 1
     * createTime : 1525582444000
     */

    private String id;
    private String code;
    private String name;
    private int deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
}
