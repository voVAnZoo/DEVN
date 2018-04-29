package com.example.user.devn;

public class Edge {
    Point p1;
    Point p2;

    public Edge(Point p1, Point p2){
        this.p1=p1;
        this.p2=p2;
    }

    public boolean sameEdge (Edge e1, Edge e2){
        return ((e1.p1 == e2.p1) && (e1.p2 == e2.p2))
        || ((e1.p1 == e2.p2) && (e1.p2 == e2.p1));
    }
    public double lengthEdge(Edge e1){
        return Point.distance2(p1,p2);
    }

}
