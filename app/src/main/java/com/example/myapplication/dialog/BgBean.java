package com.example.myapplication.dialog;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class BgBean {
    public int color; //背景颜色
    public float left_top_radius; //
    public float right_top_radius; //
    public float right_bottom_radius; //
    public float left_bottom_radius; //

    public GradientDrawable getRoundRectDrawable(){
        //1. 圆角
        float[] radiuss = new float[]{ //左上、右上、右下、左下的圆角半径
                left_top_radius, left_top_radius,
                right_top_radius, right_top_radius,
                right_bottom_radius, right_bottom_radius,
                left_bottom_radius, left_bottom_radius};;
        //2.渐变
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color!=0 ? color : Color.TRANSPARENT);

        drawable.setCornerRadii(radiuss);
        return drawable;
    }
}
