package com.template.demo.api;

/**
 * 网络请求常量
 */
public interface ApiConstants {
    class URL {
        //域名
        public static String HOST;

        //商城链接(临时)
        public static String Shop = "https://www.taobao.com/";

        //......
    }

    class ErrorCode {
        /**
         * 正常响应的代码：0
         */
        public static final int OK = 0;                         //正常

        /**
         * 服务器异常代码：500
         */
        public static final int SERVER_ERR = 1;                 //服务器异常

        /**
         * 参数异常
         */
        public static final int MISSING_PARAM = 1000;           //参数缺失
        public static final int ILLEGAL_PARAM = 1001;           //参数非法
        public static final int ILLEGAL_TOKEN = 1002;           //token非法

        /**
         * 用户业务异常代码
         */
        //注册
        public static final int USER_EXISTED = 2000;            //用户已存在

        //登录
        public static final int USER_NOT_EXIST = 2001;          //用户不存在
        public static final int USER_PWD_ERR = 2002;            //用户密码错误
        public static final int USER_3RD_UNBIND = 2003;         //用户未绑定
        public static final int USER_DISABLED = 2004;           //用户被禁用

        //登录、注册、修改密码的验证码
        public static final int IDENTIFY_SEND_FAIL = 2004;      //验证码发送失败
        public static final int IDENTIFY_ERR = 2005;            //验证码错误
        public static final int IDENTIFY_EXPIRED = 2006;        //验证码过期
        public static final int ILLEGAL_MOBILE = 2007;          //手机号非法

        /**
         * 文件类异常
         */
        public static final int ILLEGAL_IMG = 5000;             //非法图片格式

        //......
    }

}
