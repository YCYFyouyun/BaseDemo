package com.template.demo.event;

import com.template.base.app.BaseEvent;

public class MessageEvent extends BaseEvent {

    public final static int CLEAR_DEVICE_MSG = 1; //清空设备消息
    public final static int CLEAR_SYSTEM_MSG = 2; //清空系统公告
    
    public MessageEvent(int type) {
        super(type);
    }

    public MessageEvent(int type, Object obj1) {
        super(type, obj1);
    }

    public MessageEvent(int type, Object obj1, Object obj2) {
        super(type, obj1, obj2);
    }

}
