package com.example.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

public class Shape {
    //圆心或者上顶点
    public int x;
    public int y;
    public boolean select;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
