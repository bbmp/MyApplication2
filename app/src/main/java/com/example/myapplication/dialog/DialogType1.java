package com.example.myapplication.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DialogType1 extends BaseDialog{
    private RecyclerView mRecyclerView;
    private TextView mCancelView;
    private MenuAdapter adapter;

    public DialogType1(Context context) {
        super(context);
    }

    @Override
    public void initView() {
        rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout_type_1, null);
        mRecyclerView = rootView.findViewById(R.id.rv_menu_list);
        mCancelView  = rootView.findViewById(R.id.tv_menu_cancel);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new MenuAdapter();
        mRecyclerView.setAdapter(adapter);
        createDialog();
    }

    private void createDialog() {
        if (mDialog == null) {
            mDialog = new CoreDialog(mContext, R.style.dialog, rootView, true);
//            mDialog.setAnimation(R.style.li_dialog_default);
            mDialog.setPosition(Gravity.BOTTOM, 0, 0);
        }
    }

    private static class MenuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MenuAdapter() {
            super(R.layout.menu_layout_item);
        }

        public MenuAdapter(@Nullable List<String> data) {
            super(R.layout.menu_layout_item, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder baseViewHolder, String s) {
            if (null != baseViewHolder) {
                baseViewHolder.setText(R.id.tv_menu_text, s);
            }
        }
    }

    @Override
    public void setContentList(List<?> list) {
        if (null != list)
            adapter.setList((Collection<? extends String>) list);
    }

    @Override
    public void setListeners(DialogOnClickListener onClickListener, int... viewIds) {
        super.setListeners(onClickListener, viewIds);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (null != onClickListener)
                    onClickListener.onClick(mRecyclerView, position);
                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }
        });
    }
}
