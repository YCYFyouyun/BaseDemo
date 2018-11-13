package com.template.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.template.base.R;


public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context, boolean isCancelable) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.layout_loading_dialog);
        setCancelable(isCancelable);
    }

}
