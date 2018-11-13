package com.template.demo.event;

import com.template.base.app.BaseEvent;

public class UserEvent extends BaseEvent {

    public final static int REFRESH_USERINFO = 1; //刷新用户信息

    public UserEvent(int type) {
        super(type);
    }

    public UserEvent(int type, Object obj1) {
        super(type, obj1);
    }

    public UserEvent(int type, Object obj1, Object obj2) {
        super(type, obj1, obj2);
    }
    
}
