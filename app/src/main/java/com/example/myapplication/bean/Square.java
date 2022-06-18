package com.example.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

public class Square extends Shape{
    //宽高
    public int width;
    public int height;
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
    private List<Point> Area14 = new ArrayList<>();
    private List<Point> Area15 = new ArrayList<>();

    public Square(int x, int y) {
        super(x, y);
    }

    public void clear() {
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
        Area14.clear();
        Area15.clear();
    }
}
