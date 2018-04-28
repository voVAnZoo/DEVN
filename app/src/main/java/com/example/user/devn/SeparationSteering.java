package com.example.user.devn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vova on 28.04.2018.
 */

public class SeparationSteering {

    List<Room> rooms = new ArrayList<Room>();
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
}
