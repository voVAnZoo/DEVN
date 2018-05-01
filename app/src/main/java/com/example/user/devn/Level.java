package com.example.user.devn;

import java.util.ArrayList;
import java.util.List;

public class Level {

    int i;
    int j;
    int k;
    int min;
    int max;
    int minx;
    int miny;
    int maxx;
    int maxy;

    List<Triangle> triangles=new ArrayList<Triangle>();
    List<Point> points=new ArrayList<Point>();
    List<Edge> edges=new ArrayList<Edge>();

    public int maparr[][];

    List<Room> rooms = new ArrayList<Room>();
    List<Room> roomsosn = new ArrayList<Room>();

    void CreateRooms(int level){
        for (i=0;i<50*level+100;i++){
            rooms.add(Utils.goodrandom(level));
        }
    }

    void SeparateRooms(){

        for(Room room: rooms){

            Vector oldPos = room.GetPosition ();
            Vector separation = computeSeparation (room);
            Vector newPos = new Vector (oldPos.x += separation.x, oldPos.y += separation.y);
            room.SetPosition (newPos);
        }

    }

    Vector computeSeparation(Room agent) {

        int neighbours = 0;
        Vector v = new Vector ();

        for (Room room: rooms) {

            if (room != agent) {

                if (agent.TooCloseTo (room)) {

                    v.x += Difference(room, agent, "x");
                    v.y += Difference(room, agent, "y");
                    neighbours++;

                }

            }

        }

        if (neighbours == 0)
            return v;

        v.x /= neighbours;
        v.y /= neighbours;
        v.x *= -1;
        v.y *= -1;
        return v;

    }

    float Difference(Room room, Room agent, String type){

        switch (type) {

            case "x":
                float xBottom = (float) ((room.GetPosition ().x + room.width / 2) - (agent.GetPosition ().x - agent.width / 2));
                float xTop = (float) ((agent.GetPosition ().x + agent.width / 2) - (room.GetPosition ().x - room.width / 2));
                return xBottom > 0 ? xBottom : xTop;

            case "y":
                float xRight= (float) ((room.GetPosition ().y + room.height / 2) - (agent.GetPosition ().y - agent.height / 2));
                float xLeft= (float) ((agent.GetPosition ().y + agent.height / 2) - (room.GetPosition ().y - room.height / 2));
                return xRight > 0 ? xRight : xLeft;
            default:
                return 0;


        }
    }

    void triangulate() {
        int i = 0;
        for (i = 0; i < roomsosn.size(); i++) {
            if (roomsosn.get(i).x < minx) {
                minx = roomsosn.get(i).x;
            }
            if (roomsosn.get(i).y < miny) {
                miny = roomsosn.get(i).y;
            }
            if (roomsosn.get(i).x>maxx){
                maxx=roomsosn.get(i).x;
            }
            if (roomsosn.get(i).y>maxy){
                maxy=roomsosn.get(i).y;
            }
        }
        int trmax=roomsosn.size()*4;
        int dx=maxx-minx;
        int dy=maxy-miny;
        int dmax=Math.max(dx,dy);
        double midx =(minx + maxx) * 0.5;
        double midy =(miny + maxy) * 0.5;
        Point p1=new Point(roomsosn.size()+1,midx - 2 * dmax, midy - dmax);
        Point p2=new Point(roomsosn.size()+2,midx , midy + 2 * dmax);
        Point p3=new Point(roomsosn.size()+3,midx + 2 * dmax, midy - dmax);
        points.add(roomsosn.size()+1,p1);
        points.add(roomsosn.size()+2,p2);
        points.add(roomsosn.size()+3,p3);
        for (i=0;i<roomsosn.size();i++){
            for (j=0;j<triangles.size();j++){
                if (triangles.get(j).isintcircle(triangles.get(j),points.get(i))){
                    edges.add(edges.size(),triangles.get(j).e1);
                    edges.add(edges.size(),triangles.get(j).e2);
                    edges.add(edges.size(),triangles.get(j).e3);
                    triangles.remove(j);
                }
            }
            for (j=0;j<edges.size()-1;j++){
                for (k=j;k<edges.size();k++){
                    if(edges.get(j).sameEdge(edges.get(j),edges.get(k))){
                        edges.remove(j);
                        edges.remove(k+1);
                    }
                }
            }
            for (j=0;j<edges.size()-1;j++){
                if (triangles.size()<=trmax){
                    triangles.add(triangles.size()+1,new Triangle(edges.get(j).p1,edges.get(j).p2,points.get(i)));
                }
            }
        }
        for (j=0;j<triangles.size();j++){
            if (((triangles.get(j).p1.num>triangles.size()-3)||
               (triangles.get(j).p1.num>triangles.size()-3))||
               (triangles.get(j).p1.num>triangles.size()-3)){
                triangles.remove(j);
            }
        }
    }
    public Level(int level){
        CreateRooms(level);
        SeparateRooms();
        for (i=0;i<50*level+100;i++) {
            if (rooms.get(i).height*rooms.get(i).width<50){
                roomsosn.add(rooms.get(i));
            }
        }
        for (i=0;i<roomsosn.size();i++) {
            points.set(i,new Point(i,roomsosn.get(i).x,roomsosn.get(i).y));
            if (roomsosn.get(i).x<minx){
                if (roomsosn.get(i).y<miny){
                    minx=roomsosn.get(i).x;
                    miny=roomsosn.get(i).y;
                    min=i;
                }
            }
            else
                if (roomsosn.get(i).x>maxx){
                    if (roomsosn.get(i).y>maxy){
                        maxx=roomsosn.get(i).x;
                        maxy=roomsosn.get(i).y;
                        max=i;
                    }
            }
        }
        triangulate();
        for (i = 0; i < maxy+20; i++) {
            for (j = 0; j < maxx+20; j++) {
                maparr[i][j] = 1;
            }
        }
        for (Room room: roomsosn){
            for (i=0;i<room.height;i++)
                for (j=0;j<room.width;j++)
                    maparr[room.x-room.width/2+j][room.y-room.height/2+i]=0;
        }
        for (i=0;i<roomsosn.get(max).height;i++)
            for (j=0;j<roomsosn.get(max).width;j++)
                maparr[roomsosn.get(max).x-roomsosn.get(max).width/2+j][roomsosn.get(max).y-roomsosn.get(max).height/2+i]=3;
        for (i=0;i<roomsosn.get(min).height;i++)
            for (j=0;j<roomsosn.get(min).width;j++)
                maparr[roomsosn.get(min).x-roomsosn.get(min).width/2+j][roomsosn.get(min).y-roomsosn.get(min).height/2+i]=2;
        for (i=0;i<triangles.size();i++)
            for (i=Math.min(roomsosn.get(triangles.get(i).p1.num).y,roomsosn.get(triangles.get(i).p2.num).y);i<Math.max(roomsosn.get(triangles.get(i).p1.num).y,roomsosn.get(triangles.get(i).p2.num).y);i++){
                maparr[Math.min(roomsosn.get(triangles.get(i).p1.num).x,roomsosn.get(triangles.get(i).p2.num).x)][i]=0;
            }
            for (i=Math.min(roomsosn.get(triangles.get(i).p1.num).x,roomsosn.get(triangles.get(i).p2.num).x);i<Math.max(roomsosn.get(triangles.get(i).p1.num).x,roomsosn.get(triangles.get(i).p2.num).x);i++){
                maparr[i][Math.max(roomsosn.get(triangles.get(i).p1.num).y,roomsosn.get(triangles.get(i).p2.num).y)]=0;
            }
    }
}
