package com.template.demo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import com.template.demo.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class MarketUtil {

    public final static String MARKET_PKG_NAME = "com.tencent.android.qqdownloader"; //应用宝对应的包名

    /**
     * 判断市场是否存在的方法
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        List<String> pName = new ArrayList<>();// 用于存储所有已安装程序的包名
        if (pinfo != null) { // 从pinfo中将包名字逐一取出，压入pName list中
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                pName.add(pn);
            }
        }
        return pName.contains(packageName);// 判断pName中是否有目标程序的包名，有TRUE，没有FALSE
    }

    /**
     * 启动到app详情界面
     *
     * @param appPkg    App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 跳转应用宝
     *
     * @param context
     */
    public static void goToMarketDefault(Context context) {
        goToMarket(context, BuildConfig.APPLICATION_ID, MARKET_PKG_NAME);
    }

    /**
     * 跳转对应的应用市场
     *
     * @param context
     * @param appPkg
     * @param marketPkg
     */
    public static void goToMarket(Context context, String appPkg, String marketPkg) {
        if (isAvilible(context, marketPkg)) {
            launchAppDetail(context, appPkg, marketPkg);
        } else {
            Uri uri = Uri.parse("https://a.app.qq.com/o/simple.jsp?pkgname=" + appPkg);
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);
        }
    }

}