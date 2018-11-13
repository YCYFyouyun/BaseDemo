package com.template.demo.utils.core;

import android.content.res.Resources;
import android.text.TextUtils;

import com.template.base.net.RetrofitRequestTool;
import com.template.base.utils.SpUtils;
import com.template.base.utils.TypeUtil;
import com.template.base.utils.Utils;
import com.template.demo.bean.comm.LanguageBean;
import com.template.demo.bean.comm.LanguageBeanList;
import com.template.demo.constant.AppValue;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * 语言工具类
 */
public class LanguageUtils {

    private static final String LANGUAGE_CODE = "LCODE"; //本地存储语言类型的key
    private static final String LANGUAGE_DATA = "LDATA"; //国际化数据

    private static LanguageUtils mInstance;
    private final Resources mResources;
    private String mLanguageCode;
    private HashMap<String, JSONObject> mMap; //保存不同语言的资源
    private List<LanguageBean> mList; //保存语言种类列表

    private LanguageUtils() {
        mResources = Utils.getContext().getResources();
        mMap = new HashMap<>();
    }

    public static LanguageUtils get() {
        if (mInstance == null) {
            synchronized (LanguageUtils.class) {
                if (mInstance == null) {
                    mInstance = new LanguageUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * 首次打开应用，初始化语言
     */
    public void initFirstLanguge() {
        String language = Locale.getDefault().getLanguage();
        setLanguageCode(language == null || language.equals("zh")
                ? AppValue.Language.ZH : AppValue.Language.EN);
    }

    /**
     * 设置选中的语言
     *
     * @param languageCode
     */
    public void setLanguageCode(String languageCode) {
        if (languageCode == null) languageCode = AppValue.Language.DEF;
        mLanguageCode = languageCode;
        SpUtils.putString(LANGUAGE_CODE, languageCode);
        RetrofitRequestTool.addHeader("language", mLanguageCode);
    }

    /**
     * 获取选中的语言
     *
     * @return
     */
    public String getLanguageCode() {
        if (mLanguageCode == null)
            mLanguageCode = SpUtils.getString(LANGUAGE_CODE, AppValue.Language.DEF);
        return mLanguageCode;
    }

    /**
     * 保存语言数据
     *
     * @param data
     */
    public void setLanguageData(LanguageBeanList data) {
        SpUtils.putObject(LANGUAGE_DATA, data);
        mMap.clear(); //清空数据
        mList = data != null ? data.getLanguages() : null;
    }

    /**
     * 获取语言数据
     *
     * @return
     */
    public LanguageBeanList getLanguageData() {
        return SpUtils.getObject(LANGUAGE_DATA, LanguageBeanList.class);
    }

    /**
     * 获取语言种类列表
     *
     * @return
     */
    public List<LanguageBean> getLanguages() {
        if (mList != null) return mList;
        LanguageBeanList data = getLanguageData();
        return data != null ? data.getLanguages() : null;
    }

    /**
     * 获取"所选语言"对应的名称
     *
     * @return
     */
    public String getLanguageName() {
        String code = getLanguageCode();
        List<LanguageBean> list = getLanguages();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (code.equals(TypeUtil.getValue(list.get(i).getCode()))) {
                    return TypeUtil.getValue(list.get(i).getName());
                }
            }
        }

        return code.equals(AppValue.Language.ZH)
                ? AppValue.Language.ZH_NAME
                : AppValue.Language.EN_NAME;
    }

    /**
     * 获取语言版本号
     *
     * @return
     */
    public String getVersion() {
        LanguageBeanList data = getLanguageData();
        return data != null ? data.getVer() : null;
    }

//    /**
//     * 是否为中文
//     *
//     * @return
//     */
//    public boolean isChineseLanguage() {
//        String code = getLanguageCode();
//        return code.equals(AppValue.Language.ZH) || code.equals(AppValue.Language.ZH_R);
//    }

    /**
     * 获取"所选语言"对应的资源
     *
     * @return
     */
    public JSONObject getJsonObject() {
        String code = LanguageUtils.get().getLanguageCode(); //获取所选语言
        if (mMap.containsKey(code)) return mMap.get(code); //返回语言对应的资源

        LanguageBeanList data = getLanguageData(); //获取所有语言资源
        try {
            if (data != null && !TextUtils.isEmpty(data.getLanguageItems())) {
                JSONObject items = JSON.parseObject(data.getLanguageItems());
                JSONObject object = items.getJSONObject(code);
                if (object != null) { //有数据
                    mMap.put(code, object); //保存到map中
                    return object; //返回语言对应的资源
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }

    /**
     * 获取key对应的资源文件
     *
     * @param key
     * @param strRes
     * @return
     */
    public String getString(String key, int strRes) {
        String value = getJsonObject().getString(key);
        return value != null ? value : mResources.getString(strRes);
    }

}
