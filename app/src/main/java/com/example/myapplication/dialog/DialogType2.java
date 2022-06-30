package com.example.myapplication.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;

import com.example.myapplication.R;

public class DialogType2 extends BaseDialog{
    private Button mOkTv;

    public DialogType2(Context context) {
        super(context);
    }

    @Override
    public void initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout_type_2, null);

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
