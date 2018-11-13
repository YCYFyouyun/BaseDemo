package com.template.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.template.base.utils.TypeUtil;
import com.template.demo.R;
import com.template.demo.utils.core.LanguageUtils;

public class CommonDialog extends Dialog implements View.OnClickListener {

    public CommonDialog(@NonNull Context context, String notice,
                        DialogListener listener) {
        this(context, notice, null, null, listener);
    }

    private DialogListener mListener;

    public CommonDialog(@NonNull Context context, String notice,
                        String left, String right, DialogListener listener) {
        super(context, R.style.AlertDialog);
        setContentView(R.layout.dialog_common);
        TextView tvNotice = findViewById(R.id.tv_notice);
        TextView tvLeft = findViewById(R.id.tv_left);
        TextView tvRight = findViewById(R.id.tv_right);

        tvNotice.setText(TypeUtil.getValue(notice));
        tvLeft.setText(left != null ? left
                : LanguageUtils.get().getString("cancel", R.string.cancel));
        tvRight.setText(right != null ? right
                : LanguageUtils.get().getString("confirm", R.string.confirm));

        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                dismiss();
                if (mListener != null) mListener.clickLeft(this);
                break;
            case R.id.tv_right:
                if (mListener != null) mListener.clickRight(this);
                break;
        }
    }

    public interface DialogListener {
        void clickLeft(CommonDialog dialog);

        void clickRight(CommonDialog dialog);
    }

}
