package com.example.user.devn;

public class Point {
    int num;
    int x;
    int y;

    public Point(int num, int x, int y){
        this.num=num;
        this.x=x;
        this.y=y;
    }

     static public int distance2(Point p1,Point p2){
        return (p1.x-p2.x)^2+(p1.y-p2.y)^2;
    }
    public boolean inCurcle(Point p1,int x, int y,int rad){
        Point p2 = new Point(x,y,1);
        return  distance2(p1,p2) <= rad*rad;
    }
}
