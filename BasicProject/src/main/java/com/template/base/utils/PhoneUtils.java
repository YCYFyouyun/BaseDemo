package com.template.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;

/**
 * 手机设备相关工具类
 */
public final class PhoneUtils {

    private PhoneUtils() {
        throw new UnsupportedOperationException("Can't instantiate this class !");
    }

    /**
     * 判断设备是否root
     *
     * @return the boolean{@code true}: 是<br>{@code false}: 否
     */
    public static boolean isRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/"
                , "/sbin/", "/system/sd/xbin/"
                , "/system/bin/failsafe/", "/data/local/xbin/"
                , "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取设备系统版本号
     *
     * @return 设备系统版本号
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取设备AndroidID
     *
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        return Settings.Secure.getString(Utils.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取设备厂商
     *
     * @return 设备厂商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备型号
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null)
            model = model.trim().replaceAll("\\s*", "");
        else
            model = "";
        return model;
    }

    private static TelephonyManager getTeleManager() {
        return (TelephonyManager) Utils.getContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 判断设置是否是手机
     */
    public static boolean isPhone() {
        TelephonyManager manager = getTeleManager();
        return manager != null && manager.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    /**
     * 获取设备唯一识别码【IMEI码】
     */
    public static String getIMEI() {
        TelephonyManager manager = getTeleManager();
        return manager != null ? manager.getDeviceId() : null;
    }

    /**
     * 拨打电话【调用前需要申请权限android.permission.CALL_PHONE】
     *
     * @param teleNumber 电话号码
     */
    public static void dial(String teleNumber) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + teleNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getContext().startActivity(intent);
    }

    /**
     * 跳转拨号盘
     *
     * @param teleNumber 电话号码
     */
    public static void skip(String teleNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + teleNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getContext().startActivity(intent);
    }

    /**
     * 发送短信【调用前需要申请权限android.permission.SEND_SMS】
     *
     * @param teleNumner 电话号码
     * @param message    短信内容
     */
    public static void sendSMS(String teleNumner, String message) {
        Uri uri = Uri.parse("smsto:" + teleNumner);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Utils.getContext().startActivity(intent);
    }

    /**
     * 电话号中间4位显示为****
     *
     * @param phone
     * @return
     */
    public static String encryptPhone(String phone) {
        if (TextUtils.isEmpty(phone) || phone.length() < 11) return "";
        return phone.substring(0, 3) +
                "****" +
                phone.substring(phone.length() - 4, phone.length());
    }

    public static String encryptPhone2(String phone) {
        if (TextUtils.isEmpty(phone)) return "";
        if (phone.length() < 11) return phone;
        return phone.substring(0, 3) +
                "****" +
                phone.substring(phone.length() - 4, phone.length());
    }

}
