package com.template.base.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * SharedPreferences工具类
 */
public final class SpUtils {
    
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    private static SharedPreferences getSp() {
        if (mSharedPreferences == null)
            mSharedPreferences = Utils.getContext()
                    .getSharedPreferences(Utils.getContext().getPackageName(), Context.MODE_PRIVATE);
        return mSharedPreferences;
    }

    private static SharedPreferences.Editor getEditor() {
        if (mEditor == null)
            mEditor = getSp().edit();
        return mEditor;
    }

    /**
     * 保存String
     *
     * @param key   键
     * @param value 值
     * @return 是否保存成功
     */
    public static boolean putString(String key, String value) {
        return getEditor().putString(key, value).commit();
    }

    /**
     * 获取String
     *
     * @param key 键
     * @return 值（不存在则默认返回null）
     */
    public static String getString(String key) {
        return getString(key, null);
    }

    /**
     * 获取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     */
    public static String getString(String key, String defaultValue) {
        return getSp().getString(key, defaultValue);
    }

    /**
     * 保存Integer
     *
     * @param key   键
     * @param value 值
     * @return 是否保存成功
     */
    public static boolean putInt(String key, int value) {
        return getEditor().putInt(key, value).commit();
    }

    /**
     * 获取Integer
     *
     * @param key 键
     * @return 值（不存在则默认返回-1）
     */
    public static int getInt(String key) {
        return getInt(key, -1);
    }

    /**
     * 获取Integer
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     */
    public static int getInt(String key, int defaultValue) {
        return getSp().getInt(key, defaultValue);
    }

    /**
     * 保存Long
     *
     * @param key   键
     * @param value 值
     * @return 是否保存成功
     */
    public static boolean putLong(String key, long value) {
        return getEditor().putLong(key, value).commit();
    }

    /**
     * 获取Long
     *
     * @param key 键
     * @return 值（不存在则默认返回-1）
     */
    public static long getLong(String key) {
        return getLong(key, -1L);
    }

    /**
     * 获取Long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     */
    public static long getLong(String key, long defaultValue) {
        return getSp().getLong(key, defaultValue);
    }

    /**
     * 保存Float
     *
     * @param key   键
     * @param value 值
     * @return 是否保存成功
     */
    public static boolean putFloat(String key, float value) {
        return getEditor().putFloat(key, value).commit();
    }

    /**
     * 获取Float
     *
     * @param key 键
     * @return 值（不存在默认返回-1）
     */
    public static float getFloat(String key) {
        return getFloat(key, -1F);
    }

    /**
     * 获取Float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     */
    public static float getFloat(String key, float defaultValue) {
        return getSp().getFloat(key, defaultValue);
    }

    /**
     * 保存Boolean
     *
     * @param key   键
     * @param value 值
     * @return 是否保存成功
     */
    public static boolean putBoolean(String key, boolean value) {
        return getEditor().putBoolean(key, value).commit();
    }

    /**
     * 获取Boolean
     *
     * @param key 键
     * @return 值（不存在默认返回false）
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取Boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return getSp().getBoolean(key, defaultValue);
    }

    /**
     * 根据key删除某条记录
     *
     * @param key 键
     */
    public static boolean remove(String key) {
        return getEditor().remove(key).commit();
    }

    /**
     * 根据key检查是否保存过某条记录
     *
     * @param key 键
     * @return 是否保存
     */
    public static boolean containKey(String key) {
        return getSp().contains(key);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static Set<String> getStringSet(String key) {
        return getSp().getStringSet("StringSet-" + key, new HashSet<String>());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static boolean putStringSet(String key, Set<String> value) {
        return getEditor().putStringSet("StringSet-" + key, value).commit();
    }

    /**
     * 使用 fastJson 转成 json 数据存储
     *
     * @param key
     * @param value
     */
    public static void putObject(String key, Object value) {
        getEditor().putString("Object-" + key,
                value == null ? null : JSON.toJSONString(value))
                .commit();
    }

    /**
     * 获取存储的对象, 获取不到则返回 null
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getObject(String key, Class<T> clazz) {
        String value = getSp().getString("Object-" + key, null);
        if (null == value) return null;
        return JSON.parseObject(value, clazz);
    }

    public static void putList(String key, List list) {
        String value = (list == null || list.size() == 0) ? "" : JSON.toJSONString(list);
        getEditor().putString("List-" + key, value).commit();
    }

    public static <T> List<T> getList(String key, Class<T> clazz) {
        String value = getSp().getString("List-" + key, "");
        if (TextUtils.isEmpty(value)) return new ArrayList<T>();
        try {
            return JSON.parseArray(value, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<T>();
        }
    }

}
