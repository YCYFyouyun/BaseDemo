package com.template.demo.event;

import com.template.base.app.BaseEvent;

public class LanguageEvent extends BaseEvent {

    public static final int LANGUAGE_CHANGED = 1; //改变语言

    public LanguageEvent(int type) {
        super(type);
    }

    public LanguageEvent(int type, Object obj1) {
        super(type, obj1);
    }

    public LanguageEvent(int type, Object obj1, Object obj2) {
        super(type, obj1, obj2);
    }
}
