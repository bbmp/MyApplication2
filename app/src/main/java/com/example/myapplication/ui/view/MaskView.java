package com.example.myapplication.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;


import com.example.myapplication.R;
import com.example.myapplication.bean.Point;
import com.example.myapplication.bean.Round;
import com.example.myapplication.bean.Shape;
import com.example.myapplication.bean.Square;
import com.utils.ColorUtils;
import com.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class MaskView extends View {
    private Context context;
    private Bitmap bitmap;
    //抠图列表
    private List<Shape> rShape = new ArrayList<>();
    //图片缩放到全屏的比例
    private float scale;
    //初始圆半径
    private int initR = 120;
    //初始方形宽高
    private int initWidth = 180;
    private int initHeight = 160;
    //分区半径
    private final static float R1 = 0.3f;
    private final static float R2 = 0.6f;
    /*点击坐标*/
    private float ClickX = 0;
    private float ClickY = 0;

    private boolean move = false;


    public MaskView(Context context) {
        super(context);
        this.context = context;
    }

    public MaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.wet);

    }

    public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        invalidate();
    }
    //圆放大
    public void addR() {
        if (null != bitmap) {
            for (int i = 0; i<rShape.size(); i++) {
                Shape shape = rShape.get(i);
                if (shape.select && shape instanceof Round) {
                    Round round = (Round) shape;
                    if (round.r*2 >= bitmap.getHeight()) //半径超图片高度一半
                        return;
                    round.r++;
                    invalidate();
                    break;
                }
            }
        }
    }
    //圆缩小
    public void decR() {
        if (null != bitmap) {
            for (int i = 0; i<rShape.size(); i++) {
                Shape shape = rShape.get(i);
                if (shape.select && shape instanceof Round) {
                    Round round = (Round) shape;
                    if (round.r <= 20)  //不小于20像素
                        return;
                    round.r--;
                    invalidate();
                    break;
                }
            }
        }
    }

    //矩形长+
    public void addWidth() {
        if (null != bitmap) {
            for (int i = 0; i<rShape.size(); i++) {
                Shape shape = rShape.get(i);
                if (shape.select && shape instanceof Square) {
                    Square square = (Square) shape;
                    if (square.x + square.width >= bitmap.getWidth()) //超过图片边界
                        return;

                    square.width++;
                    invalidate();
                    break;
                }
            }
        }
    }
    //矩形长-
    public void decWidth() {
        if (null != bitmap) {
            for (int i = 0; i<rShape.size(); i++) {
                Shape shape = rShape.get(i);
                if (shape.select && shape instanceof Square) {
                    Square square = (Square) shape;
                    if (square.width <= 20) //不小于20像素
                        return;
                    square.width--;
                    invalidate();
                    break;
                }
            }
        }
    }
    //矩形高+
    public void addHeight() {
        if (null != bitmap) {
            for (int i = 0; i<rShape.size(); i++) {
                Shape shape = rShape.get(i);
                if (shape.select && shape instanceof Square) {
                    Square square = (Square) shape;
                    if (square.y + square.height >= bitmap.getHeight()) //超过图片边界
                        return;

                    square.height++;
                    invalidate();
                    break;
                }
            }
        }
    }
    //矩形高-
    public void decHeight() {
        if (null != bitmap) {
            for (int i = 0; i<rShape.size(); i++) {
                Shape shape = rShape.get(i);
                if (shape.select && shape instanceof Square) {
                    Square square = (Square) shape;
                    if (square.height <= 20) //不小于20像素
                        return;
                    square.height--;
                    invalidate();
                    break;
                }
            }
        }
    }

    //获取矩形个数
    public int getSquareNum() {
        int num = 0;
        for (int i = 0; i<rShape.size(); i++) {
            Shape shape = rShape.get(i);
            if (shape instanceof Square)
                num++;
        }
        return num * 15;
    }

    //获取圆个数
    public int getRoundNum() {
        int num = 0;
        for (int i=0; i<rShape.size(); i++) {
            Shape shape = rShape.get(i);
            if (shape instanceof Round)
                num++;
        }
        return num * 13;
    }
    //add a round
    public void addRound() {
        if (null != bitmap) {
            for (int i = 0; i < rShape.size(); i++) {
                rShape.get(i).select = false;
            }
            Round round = new Round(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
            round.r = initR;
            round.select = true;
            rShape.add(round);
            invalidate();
        }
    }
    //矩形
    public void addSquare() {
        if (null != bitmap) {
            for (int i = 0; i < rShape.size(); i++) {
                rShape.get(i).select = false;
            }
            Square square = new Square(bitmap.getWidth() / 2, bitmap.getHeight() / 2);
            square.width = initWidth;
            square.height = initHeight;
            square.select = true;
            rShape.add(square);
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (null != bitmap) {
//            LogUtils.e("getwidth " + getWidth());
//            LogUtils.e("getheight " + getHeight());
//            LogUtils.e("bitmap.getWidth() " + bitmap.getWidth());
//            LogUtils.e("bitmap.getHeight() " + bitmap.getHeight());
            float sx = getWidth() * 1.0f / bitmap.getWidth();
            float sy = getHeight() * 1.0f / bitmap.getHeight();
            scale = sx < sy ? sx : sy;
            Paint pa = new Paint();
            Matrix matrix = new Matrix();
            matrix.setScale(scale, scale);
            canvas.drawBitmap(bitmap, matrix, pa);


            pa.setAntiAlias(true);
            pa.setStyle(Paint.Style.STROKE);
            pa.setStrokeWidth(4);

            for (int i = 0; i < rShape.size(); i++) {
                if (rShape.get(i).select)
                    pa.setColor(Color.GREEN);
                else
                    pa.setColor(Color.GRAY);
                if (rShape.get(i) instanceof Round) {
                    Round round = (Round) rShape.get(i);
                    canvas.drawCircle(round.x, round.y, round.r, pa);
                } else if (rShape.get(i) instanceof Square) {
                    Square square = (Square) rShape.get(i);
                    canvas.drawRect(square.x, square.y, square.x + square.width, square.y + square.height, pa);
                }
            }
        }

//        for (int j = 0; j < rShape.size(); j++) {
//            Round round = (Round) rShape.get(j);
//            int height = getHeight();
//            Paint paint = new Paint();
//
//            for (int i = 0; i < round.Area1.size(); i++) {
//                paint.setColor(context.getColor(R.color.black));
//                canvas.drawPoint(round.Area1.get(i).x, round.Area1.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area2.size(); i++) {
//                paint.setColor(context.getColor(R.color.white));
//                canvas.drawPoint(round.Area2.get(i).x, round.Area2.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area3.size(); i++) {
//                paint.setColor(context.getColor(R.color.fanqiehong));
//                canvas.drawPoint(round.Area3.get(i).x, round.Area3.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area4.size(); i++) {
//                paint.setColor(context.getColor(R.color.green));
//                canvas.drawPoint(round.Area4.get(i).x, round.Area4.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area5.size(); i++) {
//                paint.setColor(context.getColor(R.color.lenghui));
//                canvas.drawPoint(round.Area5.get(i).x, round.Area5.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area6.size(); i++) {
//                paint.setColor(context.getColor(R.color.pink));
//                canvas.drawPoint(round.Area6.get(i).x, round.Area6.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area7.size(); i++) {
//                paint.setColor(context.getColor(R.color.qingse));
//                canvas.drawPoint(round.Area7.get(i).x, round.Area7.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area8.size(); i++) {
//                paint.setColor(context.getColor(R.color.red));
//                canvas.drawPoint(round.Area8.get(i).x, round.Area8.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area9.size(); i++) {
//                paint.setColor(context.getColor(R.color.shenhong));
//                canvas.drawPoint(round.Area9.get(i).x, round.Area9.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area10.size(); i++) {
//                paint.setColor(context.getColor(R.color.shenlan));
//                canvas.drawPoint(round.Area10.get(i).x, round.Area10.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area11.size(); i++) {
//                paint.setColor(context.getColor(R.color.shenlinlv));
//                canvas.drawPoint(round.Area11.get(i).x, round.Area11.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area12.size(); i++) {
//                paint.setColor(context.getColor(R.color.shibanhui));
//                canvas.drawPoint(round.Area12.get(i).x, round.Area12.get(i).y, paint);
//            }
//            for (int i = 0; i < round.Area13.size(); i++) {
//                paint.setColor(context.getColor(R.color.yellow));
//                canvas.drawPoint(round.Area13.get(i).x, round.Area13.get(i).y, paint);
//            }
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float ev_x = event.getX();
                float ev_y = event.getY();
                for (int i = 0; i< rShape.size(); i++) {
                    if (rShape.get(i) instanceof Round) {
                        Round round = (Round) rShape.get(i);
                        float x = ev_x - round.x;
                        float y = ev_y - round.y;
                        if (Math.sqrt(x * x + y * y) < round.r) {
                            for (int j = 0; j< rShape.size(); j++)
                                rShape.get(j).select = false;

                            round.select = true;
                            for (int j=0; j<rShape.size(); j++)
                                LogUtils.e("select +" + rShape.get(j).select);
                            invalidate();
                            move = true;
                            ClickX = event.getX();
                            ClickY = event.getY();
                            return true;
                        }
                    } else if (rShape.get(i) instanceof Square) {
                        Square square = (Square) rShape.get(i);
                        if (ev_x > square.x && ev_x < square.x + square.width
                        && ev_y > square.y && ev_y < square.y + square.height) {
                            for (int j = 0; j< rShape.size(); j++)
                                rShape.get(j).select = false;

                            square.select = true;
                            invalidate();
                            move = true;
                            ClickX = event.getX();
                            ClickY = event.getY();
                            return true;
                        }
                    }
                }
            }
                break;
            case MotionEvent.ACTION_MOVE:
                int count = event.getPointerCount();
                float ev_x = event.getX();
                float ev_y = event.getY();
                if (move) {
                    Shape shape = null;
                    for (int i = 0; i < rShape.size(); i++) {
                        if (rShape.get(i).select) {
                            shape = rShape.get(i);
                            break;
                        }
                    }
                    if (null != shape) {
                        switch (count) {
                            case 1:
                                //移动距离
                                float dx = ev_x - ClickX;
                                float dy = ev_y - ClickY;
                                ClickX = ev_x;
                                ClickY = ev_y;
                                if (shape.x+dx>=bitmap.getWidth() || shape.x+dx <= 0
                                    || shape.y+dy>=bitmap.getHeight()
                                    || shape.y+dy <= 0) {
                                    break;
                                }
                                shape.x += dx;
                                shape.y += dy;
                                invalidate();

                                break;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                move = false;
                break;

        }
        return super.onTouchEvent(event);
    }

    //分割方形区域
    public void splitSquare(ColorUtils.CaculateCallback callback) {
        Shape shape = null;

        for (int index = 0; index < rShape.size(); index++) {
            shape = rShape.get(index);

            if (null != shape && shape instanceof Square) {
                Square square = (Square) shape;
                //clear
                square.clearArea();
                int y = (int) (square.y * 1.0f / scale);
                int x = (int) (square.x * 1.0f / scale);
                int width = (int) (square.width * 1.0f / scale);
                int height = (int) (square.height * 1.0f / scale);
                //第一行
                for (int i=y; i<(y+height/3); i++) {
                    for (int j=x; j<=(x+width); j++) {
                        if (j < (x + width/5))
                            square.addArea8(new Point(j, i));
                        else if (j < (x + width*2/5))
                            square.addArea9(new Point(j, i));
                        else if (j <= (x + width*3/5))
                            square.addArea2(new Point(j, i));
                        else if (j <= (x + width*4/5))
                            square.addArea10(new Point(j, i));
                        else
                            square.addArea11(new Point(j, i));
                    }
                }
                //第二行
                for (int i=(y+height/3); i<=(y+height*2/3); i++) {
                    for (int j=x; j<=(x+width); j++) {
                        if (j < (x + width/5))
                            square.addArea4(new Point(j, i));
                        else if (j < (x + width*2/5))
                            square.addArea3(new Point(j, i));
                        else if (j <= (x + width*3/5))
                            square.addArea1(new Point(j, i));
                        else if (j <= (x + width*4/5))
                            square.addArea6(new Point(j, i));
                        else
                            square.addArea7(new Point(j, i));
                    }
                }
                //第三行
                for (int i=(y+height*2/3+1); i<=(y+height); i++) {
                    for (int j=x; j<=(x+width); j++) {
                        if (j < (x + width/5))
                            square.addArea12(new Point(j, i));
                        else if (j < (x + width*2/5))
                            square.addArea13(new Point(j, i));
                        else if (j <= (x + width*3/5))
                            square.addArea5(new Point(j, i));
                        else if (j <= (x + width*4/5))
                            square.addArea14(new Point(j, i));
                        else
                            square.addArea15(new Point(j, i));
                    }
                }

//                square.setColor(bitmap);

                square.caculate(bitmap, callback);
            }
        }
    }

    //分割圆形区域
    public void spiltRound(ColorUtils.CaculateCallback callback) {
        Shape shape = null;
        for (int index = 0; index < rShape.size(); index++) {
            shape = rShape.get(index);

            if (shape != null && shape instanceof Round) {
                Round round = (Round) shape;
                //clear
                round.clearArea();
                int y = (int) (round.y * 1.0f / scale);
                int x = (int) (round.x * 1.0f / scale);
                int r = (int) (round.r * 1.0f / scale);

                //第一象限
                for (int i = y - 1; i > (y - r); i--) {
                    for (int j = x; j < (x + r); j++) {
                        int distance2 = (j - x) * (j - x) + (i - y) * (i - y);

                        if (distance2 < r * r * R1 * R1) {
                            //diyikuai
                            round.addArea1(new Point(j, i));
                        } else if (distance2 < r * r * R2 * R2) {

                            round.addArea2(new Point(j, i));
                        } else if (distance2 < r * r) {
                            //jisuanjiaodu
                            double dianji = Math.abs(j - x);
                            double mod = Math.sqrt(distance2);
                            double arc = Math.acos(dianji / mod) * 180 / Math.PI;
                            if (arc > 0 && arc <= 22.5)
                                round.addArea6(new Point(j, i));
                            else if (arc > 22.5 && arc <= 67.5)
                                round.addArea7(new Point(j, i));
                            else
                                round.addArea8(new Point(j, i));
                        } else
                            break;
                    }
                }
                //dierxiangxian
                for (int i = y; i > (y - r); i--) {
                    for (int j = x - 1; j > (x - r); j--) {
                        int distance2 = (j - x) * (j - x) + (i - y) * (i - y);
                        if (distance2 < r * r * R1 * R1) {
                            //diyikuai
                            round.addArea1(new Point(j, i));
                        } else if (distance2 < r * r * R2 * R2) {

                            round.addArea3(new Point(j, i));
                        } else if (distance2 < r * r) {
                            double dianji = Math.abs(x - j);
                            double mod = Math.sqrt(distance2);
                            double arc = Math.acos(dianji / mod) * 180 / Math.PI;
                            if (arc >= 0 && arc < 22.5)
                                round.addArea10(new Point(j, i));
                            else if (arc >= 22.5 && arc < 67.5)
                                round.addArea9(new Point(j, i));
                            else
                                round.addArea8(new Point(j, i));
                        } else
                            break;
                    }
                }
                //disanxiangxian
                for (int i = y + 1; i < (y + r); i++) {
                    for (int j = x; j > (x - r); j--) {
                        int distance2 = (j - x) * (j - x) + (i - y) * (i - y);
                        if (distance2 < r * r * R1 * R1) {
                            //diyikuai
                            round.addArea1(new Point(j, i));
                        } else if (distance2 < r * r * R2 * R2) {
                            round.addArea4(new Point(j, i));
                        } else if (distance2 < r * r) {
                            double dianji = Math.abs(x - j);
                            double mod = Math.sqrt(distance2);
                            double arc = Math.acos(dianji / mod) * 180 / Math.PI;
                            if (arc > 0 && arc <= 22.5)
                                round.addArea10(new Point(j, i));
                            else if (arc > 22.5 && arc <= 67.5)
                                round.addArea11(new Point(j, i));
                            else
                                round.addArea12(new Point(j, i));
                        } else
                            break;
                    }
                }
                //disixiangxian
                for (int i = y; i < y + r; i++) {
                    for (int j = x + 1; j < x + r; j++) {
                        int distance2 = (j - x) * (j - x) + (i - y) * (i - y);
                        if (distance2 < r * r * R1 * R1) {
                            //diyikuai
                            round.addArea1(new Point(j, i));
                        } else if (distance2 < r * r * R2 * R2) {

                            round.addArea5(new Point(j, i));
                        } else if (distance2 < r * r) {
                            double dianji = Math.abs(j - x);
                            double mod = Math.sqrt(distance2);
                            double arc = Math.acos(dianji / mod) * 180 / Math.PI;
                            if (arc >= 0 && arc < 22.5)
                                round.addArea6(new Point(j, i));
                            else if (arc >= 22.5 && arc < 67.5)
                                round.addArea13(new Point(j, i));
                            else
                                round.addArea12(new Point(j, i));
                        } else
                            break;
                    }
                }

//                round.setColor(bitmap);
//        Log.e("Area1", "size=" +Area1.size());
//        Log.e("Area2", "size=" +Area2.size());
//        Log.e("Area3", "size=" +Area3.size());
//        Log.e("Area4", "size=" +Area4.size());
//        Log.e("Area5", "size=" +Area5.size());
//        Log.e("Area6", "size=" +Area6.size());
//        Log.e("Area7", "size=" +Area7.size());
//        Log.e("Area8", "size=" +Area8.size());
//        Log.e("Area9", "size=" +Area9.size());
//        Log.e("Area10", "size=" +Area10.size());
//        Log.e("Area11", "size=" +Area11.size());
//        Log.e("Area12", "size=" +Area12.size());
//        Log.e("Area13", "size=" +Area13.size());

                round.caculate(bitmap, callback);
            }
        }
//        invalidate();
    }
}
