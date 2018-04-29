package com.example.user.devn;


import java.util.Random;

public class Room {
    int height;
    int width;
    int x;
    int y;
    double radius;

    public Room(int x,int y,int height,int width){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Vector GetPosition() {
        return new Vector(x,y);
    }

    public void SetPosition(Vector newPos) {
        this.x = (int)newPos.x;
        this.y = (int)newPos.y;
    }
    public Boolean TooCloseTo(Room room){

        return x + width / 2 > room.GetPosition().x - room.width / 2 || y + height / 2 > room.GetPosition().y - room.height / 2 ||
                x - width / 2 < room.GetPosition().x + room.width / 2 || y - height / 2 < room.GetPosition().y + room.height / 2;

    }
}
