package com.example.user.devn;

public class Triangle {
    Point p1;
    Point p2;
    Point p3;
    Edge e1;
    Edge e2;
    Edge e3;
    public Triangle(Point p1,Point p2, Point p3){
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.e1=new Edge(p1,p2);
        this.e1=new Edge(p2,p3);
        this.e1=new Edge(p3,p1);
    }

    public Point getP1(Triangle t) {
        return p1;
    }

    public Point getP2(Triangle t) {
        return p2;
    }

    public Point getP3(Triangle t) {
        return p3;
    }

    public Point centre(Triangle t){
        double x =(( p1.x * p1.x + p1.y * p1.y) * (p2.y - p3.y) +
                ( p2.x * p2.x + p2.y * p2.y) * (p3.y - p1.y) +
                ( p3.x * p3.x + p3.y * p3.y) * (p1.y - p2.y));
        double y =(( p1.x * p1.x + p1.y * p1.y) * (p3.x - p2.x) +
                ( p2.x * p2.x + p2.y * p2.y) * (p1.x - p3.x) +
                ( p3.x * p3.x + p3.y * p3.y) * (p2.x - p1.x));
        double d =( p1.x * (p2.y - p3.y) +
                p2.x * (p3.y - p1.y) +
                p3.x * (p1.y - p2.y)) * 2;
        return new Point(1,x/d,y/d);
    }
    public double radius(Triangle t){
        double per = (e1.lengthEdge(e1)+e2.lengthEdge(e2)+e2.lengthEdge(e2))/2;
        return e1.lengthEdge(e1)*e2.lengthEdge(e2)*e2.lengthEdge(e2)/4/Math.sqrt(per*(per-e1.lengthEdge(e1))*(per-e2.lengthEdge(e2))*(per-e3.lengthEdge(e3)));
    }
    public boolean isintcircle(Triangle t,Point p){
        return Point.distance2(t.centre(t),p)<=t.radius(t);
    }
}
