package com.example.user.devn;

import java.util.Random;

/**
 * Created by user on 6/8/17.
 */

public class Turtle{
    private GameMap gameMap;
    private int x = 0;
    private int y = 0;
    private int height = 1;
    private int width = 1;
    public  int i;
    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getHeight(){ return height; }
    public int getWidth(){ return width; }
    public void setX(int value){ x = value; }
    public void setY(int value){ y = value; }
    public void setWidth(int value){ height = value; }
    public void setHeight(int value){ width = value; }
    /*
        1 - right
        -1 - left
        2 - bottom
        -2 - top
         */
    public Room Rooms[];
    public Turtle (int level){
        for (i=0;i<level*50+100;){
            Rooms[i]=new Room(level*10);
        }
        while(!separated) {
            let separated = true;
            this.preRooms.forEach((room1) => {
                    let velocity = new geom.Point();
            let center1 = room1.createRectangle().createCenter();
            this.preRooms.forEach((room2) => {
            if (room1 === room2) return;

            if (!geom.Rectangle.intersect(room1.createRectangle(), room2.createRectangle())) return;

            let center2 = room2.createRectangle().createCenter();

            let diff = center1.clone().sub(center2);

            let diffLength2 = diff.length2();

            if (diffLength2 > 0) {
                diff.nor();
                velocity.add(diff);
            }
    });

            if (velocity.length2() > 0) {
                separated = false;

                velocity.nor().mult(4);

                room1._position.add(velocity);
                room1.setxy(room1._position);
            }

            });
        }
    }

}