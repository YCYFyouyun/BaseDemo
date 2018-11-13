package com.template.demo.constant;

public interface AppValue {

    int COOLING_TIME_COUNT = 60; //验证码缓冲时间
    int CODE_LENGTH = 4; //验证码长度
    int PWD_LENGTH_MIN = 6; //密码最小长度
    int PWD_LENGTH_MAX = 14; //密码最大长度
    String SEPARATE = "/"; //分割符
    String TIME_SEPARATE = ":";

    int FIRST_PAGE = 1; //列表，第一页
    int PAGE_NUMBER = 10; //列表，默认的页数

    interface Language {
        String ZH = "zh_CN"; //中文
        String EN = "en_US"; //英文
        String DEF = ZH;
        String ZH_R = "zh_TW"; //中文繁体

        String ZH_NAME = "中文";
        String EN_NAME = "English";
    }

    interface ICode {
        String Pre = "+"; //国际区号的前缀
        String Def = "86"; //默认使用中国的国际区号
    }

    interface Contacts {
        //联系方式：1固话，2手机，3QQ
        int Tel = 1;
        int Mobile = 2;
        int QQ = 3;
    }

    interface Gender {
        //用户性别：1男，2女，3未知
        int Male = 1;
        int Female = 2;
        int Unknow = 3;
    }

    interface CodeType {
        //验证码类型：100登录、200注册、300密码修改
        int Login = 100;
        int Register = 200;
        int ChangePwd = 300;
    }

    interface DeviceType {
        //设备类型：1安卓、2iOS、3微信、4其它
        int Android = 1;
        int IOS = 2;
        int Wechat = 3;
        int Other = 4;
    }

    interface Third {
        //WECHAT、QQ、WEIBO、FACEBOOK
        String WECHAT = "WECHAT";
        String QQ = "QQ";
        String WEIBO = "WEIBO";
        String FACEBOOK = "FACEBOOK";
    }

    interface MessageType {
        int All = 0; //全部
        int Device = 1; //设备
        int System = 2; //系统
    }

    //......

}
