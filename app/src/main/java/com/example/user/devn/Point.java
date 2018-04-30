package com.example.user.devn;

public class Point {
    double num;
    double x;
    double y;

    public Point(double num, double x, double y){
        this.num=num;
        this.x=x;
        this.y=y;
    }

    static public double distance2(Point p1,Point p2){
        return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y);
    }

    public boolean inCurcle(Point p1,int x, int y,int rad){
        Point p2 = new Point(x,y,1);
        return  distance2(p1,p2) <= rad*rad;
    }
}
