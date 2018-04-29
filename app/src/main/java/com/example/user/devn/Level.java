package com.example.user.devn;

import java.util.ArrayList;
import java.util.List;

public class Level {

    int i;
    int j;
    int min;
    int max;
    int minx;
    int miny;
    int maxx;
    int maxy;

    public int mappos[][];

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
    public Level(int level){
        CreateRooms(level);
        SeparateRooms();
        for (i=0;i<50*level+100;i++) {
            if (rooms.get(i).height*rooms.get(i).width<50){
                roomsosn.add(rooms.get(i));
            }
        }
        for (i=0;i<50*level+100;i++) {
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
        //триангуляция
        //сопоставление триангулированного графа плоскости
        for (Room room: roomsosn){
            for (i=0;i<room.height;i++)
                for (j=0;j<room.width;j++)
                    mappos[room.x-room.width/2+j][room.y-room.height/2+i]=1;
        }
    }
}