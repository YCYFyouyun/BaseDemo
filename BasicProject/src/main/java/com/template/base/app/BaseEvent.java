package com.template.base.app;

public class BaseEvent {

    public final int type;
    public final Object obj1;
    public final Object obj2;

    public BaseEvent(int type) {
        this(type, null, null);
    }

    public BaseEvent(int type, Object obj1) {
        this(type, obj1, null);
    }

    public BaseEvent(int type, Object obj1, Object obj2) {
        this.type = type;
        this.obj1 = obj1;
        this.obj2 = obj2;
    }
}
