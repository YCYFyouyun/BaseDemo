package com.template.base.utils;

import java.util.regex.Pattern;

/**
 * 正则匹配工具类
 */

public final class RegexUtils {
    private RegexUtils() {
        throw new UnsupportedOperationException("Can't instantiate this class !");
    }

    /**
     * 正则匹配规则，可参考：http://toutiao.com/i6231678548520731137
     */
    static class RegexConstants {
        /**
         * 正则：手机号【截至到2017.05.01统计结果，查询地址：http://m.jihaoba.com/tools/haoduan/】
         */
        public static final String MOBILE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,1,3,6-8])|(18[0-9])|(147))\\d{8}$";

        /**
         * 正则：身份证号码18位
         */
        public static final String ID_CARD = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";

        /**
         * 正则：邮箱
         */
        public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

        /**
         * 正则：URL
         */
        public static final String URL = "[a-zA-z]+://[^\\s]*";

        /**
         * 正则：汉字
         */
        public static final String CHINESE = "^[\\u4e00-\\u9fa5]+$";

        /**
         * 正则：英文字母
         */
        public static final String ENGLISH = "^[A-Za-z]+$";

        /**
         * 正则：数字
         */
        public static final String NUMBER = "^[0-9]+$";

        /**
         * 正则：英文字母+数字
         */
        public static final String ENGLISH_AND_NUMBER = "^[A-Za-z0-9]+$";
    }

    /**
     * 验证是否规范的手机号
     */
    public static boolean isMobile(CharSequence input) {
        return isMatch(RegexConstants.MOBILE, input);
    }

    /**
     * 验证是否规范的身份证号码
     */
    public static boolean isIdCard(CharSequence input) {
        return isMatch(RegexConstants.ID_CARD, input);
    }

    /**
     * 验证是否全是中文
     */
    public static boolean isChinese(CharSequence input) {
        return isMatch(RegexConstants.CHINESE, input);
    }

    /**
     * 验证是否规范的邮箱地址
     */
    public static boolean isEmail(CharSequence input) {
        return isMatch(RegexConstants.EMAIL, input);
    }

    /**
     * 验证是否规范的URL
     */
    public static boolean isURL(CharSequence input) {
        return isMatch(RegexConstants.URL, input);
    }

    /**
     * 验证是否只包含字母
     */
    public static boolean isEnglish(CharSequence input) {
        return isMatch(RegexConstants.ENGLISH, input);
    }

    /**
     * 验证是否只包含数字
     */
    public static boolean isNumber(CharSequence input) {
        return isMatch(RegexConstants.NUMBER, input);
    }

    /**
     * 验证是否只包含英文字母和数字
     */
    public static boolean isEnglishAndNumber(CharSequence input) {
        return isMatch(RegexConstants.ENGLISH_AND_NUMBER, input);
    }

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }
}
