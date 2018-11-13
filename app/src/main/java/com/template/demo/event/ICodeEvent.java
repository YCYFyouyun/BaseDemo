package com.template.demo.event;

import com.template.base.app.BaseEvent;

public class ICodeEvent extends BaseEvent {
    
    //用于区分不同页面，"国际区号"的选择
    public final static int LOGIN = 101; //登录页
    public final static int REGISTER = 102; //注册页
    public final static int FORGET = 103; //忘记密码页

    public ICodeEvent(int type) {
        super(type);
    }

    public ICodeEvent(int type, Object obj1) {
        super(type, obj1);
    }


}
