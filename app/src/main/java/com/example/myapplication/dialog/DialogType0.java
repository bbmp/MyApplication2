package com.example.myapplication.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import java.util.List;

public class DialogType0 extends BaseDialog{
    private Button mOkTv;
    private Button mCancelTv;

    public DialogType0(Context context) {
        super(context);
    }

    @Override
    public void initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout_type_0, null);

        mCancelTv = rootView.findViewById(R.id.common_dialog_cancel_btn);
        mOkTv = rootView.findViewById(R.id.common_dialog_ok_btn);

        createDialog();
    }

    private void createDialog() {
        if (mDialog == null) {
            mDialog = new CoreDialog(mContext, R.style.dialog, rootView, true);
            mDialog.setAnimation(R.style.li_dialog_default);
            mDialog.setPosition(Gravity.BOTTOM, 0, 0);
        }
    }

}
