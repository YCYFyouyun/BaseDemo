package com.template.demo.utils;

import android.content.Context;
import android.content.res.Resources;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.template.demo.R;
import com.template.demo.utils.core.LanguageUtils;

import java.util.Calendar;
import java.util.List;

public class TimePickerUtils {

    /**
     * 获取时间选择器
     *
     * @param context
     * @param listener
     * @return
     */
    public static TimePickerView getTimePickerView(Context context, OnTimeSelectListener listener) {
        Resources resources = context.getResources();
        TimePickerView pvTime = new TimePickerBuilder(context, listener)
                .setLabel("", "", "", "", "", "")
                .setContentTextSize(22)//滚轮文字大小
                .setRangDate(null, Calendar.getInstance())
                .setLineSpacingMultiplier(2F)
                .setTitleBgColor(resources.getColor(R.color.white))
                .setCancelText(LanguageUtils.get().getString("cancel", R.string.cancel))//取消按钮文字
                .setCancelColor(resources.getColor(R.color.text999))
                .setSubmitText(LanguageUtils.get().getString("confirm", R.string.confirm))//确认按钮文字
                .setSubmitColor(resources.getColor(R.color.blue))
                .setTitleSize(18)//标题文字大小
                .setTitleText(LanguageUtils.get().getString("bornday", R.string.bornday))//标题文字
                .setTitleColor(resources.getColor(R.color.text333))
                .build();
        return pvTime;
    }

    /**
     * 获取选择器
     *
     * @param context
     * @param hours
     * @param minutes
     * @param listener
     * @return
     */
    public static OptionsPickerView<String> getOptionsPickerView(
            Context context,
            List<String> hours,
            List<String> minutes,
            OnOptionsSelectListener listener) {

        Resources resources = context.getResources();
        OptionsPickerBuilder builder = new OptionsPickerBuilder(context, listener);
        OptionsPickerView<String> pvNoLinkOptions = builder.setContentTextSize(22)//滚轮文字大小
                .setLineSpacingMultiplier(2F)
                .setTitleBgColor(resources.getColor(R.color.white))
                .setCancelText(LanguageUtils.get().getString("cancel", R.string.cancel))//取消按钮文字
                .setCancelColor(resources.getColor(R.color.text999))
                .setSubmitText(LanguageUtils.get().getString("confirm", R.string.confirm))//确认按钮文字
                .setSubmitColor(resources.getColor(R.color.blue))
                .setTitleSize(18)//标题文字大小
                .setTitleText(LanguageUtils.get().getString("bornday", R.string.bornday))//标题文字
                .setTitleColor(resources.getColor(R.color.text333))
                .build();
        pvNoLinkOptions.setNPicker(hours, minutes, null);
        pvNoLinkOptions.setSelectOptions(0, 0, 0);

        return pvNoLinkOptions;
    }

}
