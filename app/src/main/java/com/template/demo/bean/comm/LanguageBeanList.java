package com.template.demo.bean.comm;

import java.util.List;

/**
 * 语言列表
 */
public class LanguageBeanList {

    private String ver;

    private List<LanguageBean> languages;

    private String languageItems;

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public List<LanguageBean> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageBean> languages) {
        this.languages = languages;
    }

    public String getLanguageItems() {
        return languageItems;
    }

    public void setLanguageItems(String languageItems) {
        this.languageItems = languageItems;
    }
    
}
