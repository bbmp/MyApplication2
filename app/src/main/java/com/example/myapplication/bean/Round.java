package com.example.myapplication.bean;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Round extends Shape{
    public int r;
    //分区像素点
    private List<Point> Area1 = new ArrayList<>();
    private List<Point> Area2 = new ArrayList<>();
    private List<Point> Area3 = new ArrayList<>();
    private List<Point> Area4 = new ArrayList<>();
    private List<Point> Area5 = new ArrayList<>();
    private List<Point> Area6 = new ArrayList<>();
    private List<Point> Area7 = new ArrayList<>();
    private List<Point> Area8 = new ArrayList<>();
    private List<Point> Area9 = new ArrayList<>();
    private List<Point> Area10 = new ArrayList<>();
    private List<Point> Area11 = new ArrayList<>();
    private List<Point> Area12 = new ArrayList<>();
    private List<Point> Area13 = new ArrayList<>();

    public Round(int x, int y) {
        super(x, y);
    }

    public void clearArea() {
        Area1.clear();
        Area2.clear();
        Area3.clear();
        Area4.clear();
        Area5.clear();
        Area6.clear();
        Area7.clear();
        Area8.clear();
        Area9.clear();
        Area10.clear();
        Area11.clear();
        Area12.clear();
        Area13.clear();
    }

    public void addArea1(Point point) {
        Area1.add(point);
    }
    public void addArea2(Point point) {
        Area2.add(point);
    }
    public void addArea3(Point point) {
        Area3.add(point);
    }
    public void addArea4(Point point) {
        Area4.add(point);
    }
    public void addArea5(Point point) {
        Area5.add(point);
    }
    public void addArea6(Point point) {
        Area6.add(point);
    }
    public void addArea7(Point point) {
        Area7.add(point);
    }
    public void addArea8(Point point) {
        Area8.add(point);
    }
    public void addArea9(Point point) {
        Area9.add(point);
    }
    public void addArea10(Point point) {
        Area10.add(point);
    }
    public void addArea11(Point point) {
        Area11.add(point);
    }
    public void addArea12(Point point) {
        Area12.add(point);
    }
    public void addArea13(Point point) {
        Area13.add(point);
    }

    public void setColor(Bitmap bitmap) {
        if (null == bitmap)
            throw new IllegalArgumentException("bitmap is null");
        for (int i = 0; i < Area9.size(); i++) {
            int pixel = bitmap.getPixel(Area9.get(i).x, Area9.get(i).y);
            Log.e("Area9", "x=" + Area9.get(i).x + ",y=" + Area9.get(i).y);
            Area9.get(i).color = pixel;

        }
        for (int i = 0; i < Area1.size(); i++) {
            int pixel = bitmap.getPixel(Area1.get(i).x, Area1.get(i).y);
            Area1.get(i).color = pixel;
        }
        for (int i = 0; i < Area2.size(); i++) {
            int pixel = bitmap.getPixel(Area2.get(i).x, Area2.get(i).y);
            Area2.get(i).color = pixel;
        }
        for (int i = 0; i < Area3.size(); i++) {
            int pixel = bitmap.getPixel(Area3.get(i).x, Area3.get(i).y);
            Area3.get(i).color = pixel;
        }
        for (int i = 0; i < Area4.size(); i++) {
            int pixel = bitmap.getPixel(Area4.get(i).x, Area4.get(i).y);
            Area4.get(i).color = pixel;
        }
        for (int i = 0; i < Area5.size(); i++) {
            int pixel = bitmap.getPixel(Area5.get(i).x, Area5.get(i).y);
            Area5.get(i).color = pixel;
        }
        for (int i = 0; i < Area6.size(); i++) {
            int pixel = bitmap.getPixel(Area6.get(i).x, Area6.get(i).y);
            Area6.get(i).color = pixel;
        }
        for (int i = 0; i < Area7.size(); i++) {
            int pixel = bitmap.getPixel(Area7.get(i).x, Area7.get(i).y);
            Area7.get(i).color = pixel;
        }
        for (int i = 0; i < Area8.size(); i++) {
            int pixel = bitmap.getPixel(Area8.get(i).x, Area8.get(i).y);
            Area8.get(i).color = pixel;
        }
        for (int i = 0; i < Area10.size(); i++) {
            int pixel = bitmap.getPixel(Area10.get(i).x, Area10.get(i).y);
            Area10.get(i).color = pixel;
        }
        for (int i = 0; i < Area11.size(); i++) {
            int pixel = bitmap.getPixel(Area11.get(i).x, Area11.get(i).y);
            Area11.get(i).color = pixel;
        }
        for (int i = 0; i < Area12.size(); i++) {
            int pixel = bitmap.getPixel(Area12.get(i).x, Area12.get(i).y);
            Area12.get(i).color = pixel;
        }
        for (int i = 0; i < Area13.size(); i++) {
            int pixel = bitmap.getPixel(Area13.get(i).x, Area13.get(i).y);
            Area13.get(i).color = pixel;
        }
    }
}
