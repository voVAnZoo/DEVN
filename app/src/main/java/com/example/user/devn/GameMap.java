package com.example.user.devn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 6/6/17.
 */

public class GameMap extends View {
    /*
    0 - nothing
    1 -wall
    2 - start
    3 - finish
     */
    public int maparr[][];

    public List<Entity> entitys = new ArrayList<>();

    public static Player player;


    public GameMap(Context context) {
        super(context);
        init();
    }

    public GameMap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for(int i = 0 ;i < entitys.size(); i++){
                    Entity e = entitys.get(i);
                    e.action();
                }
            }
        };
        t.schedule(timerTask,0,100);
        /*try {
            save("test");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void addEntity(Entity e){
        entitys.add(e);
    }

    public void clear(int a) {
        int i;
        int j;
        for (i = 0; i < Data.mapHeight; i++) {
            for (j = 0; j < Data.mapWidth; j++) {
                maparr[i][j] = a;
            }
        }
    }

    public void generate() {
        maparr = new int[Data.mapHeight][Data.mapWidth];
        clear(1);
        Random rand = new Random();
        int startX = rand.nextInt(Data.mapWidth - Data.startWidth + 1);
        int startY = rand.nextInt(Data.mapHeight - Data.startHeight + 1);
        int finishX;
        int finishY;
        do {
            finishX = rand.nextInt(Data.mapWidth - Data.finishHeight + 1);
            finishY = rand.nextInt(Data.mapHeight - Data.finishWidth + 1);
        }while (finishX + finishY - startX - startY < (Data.mapWidth + Data.mapHeight) / 2);
        Turtle turtle = new Turtle(this);
        turtle.setX(startX);
        turtle.setY(startY);
        int i = 0;
       while (((turtle.getX() != finishX) || (turtle.getY() != finishY)) && i < 100) {
            turtle.nextStep();
            i++;
        }
        turtle.finish(finishX, finishY);
        int j;
        for (i = startY; i < startY + Data.startHeight; i++) {
            for (j = startX; j < startX + Data.startWidth; j++) {
                maparr[i][j] = 2;
            }
        }
        for (i = finishY; i < finishY + Data.finishHeight; i++) {
            for (j = finishX; j < finishX + Data.finishWidth; j++) {
                maparr[i][j] = 3;
            }
        }
        player = new Player((startX + Data.startWidth / 2) * Data.cdellWidth, (startY + Data.startHeight / 2) * Data.cdellHeight, Data.cdellWidth, Data.cdellHeight, getContext());
        entitys.add(player);
        generateMonsters();
    }
    public void generateMonsters(){
        Random rand = new Random();
        Monster monster;
        for (int y = 0; y < Data.mapHeight; y++)
            for (int x = 0; x < Data.mapWidth; x++)
                if (maparr[y][x] == 0)
                    if (rand.nextInt(10) == 1) {
                        monster = new Monster(x * Data.cdellWidth, y * Data.cdellHeight, Data.cdellWidth / 10 * 8, Data.cdellHeight / 10 * 8, getContext());
                        entitys.add(monster);
                    }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        int imageX = -Data.camX;
        int imageY = -Data.camY;
        for (int i = 0; i < Data.mapHeight; i++) {
            imageX = -Data.camX;
            for (int j = 0; j < Data.mapWidth; j++) {
                switch (maparr[i][j]) {
                    case 0:
                        paint.setColor(Color.BLACK);
                        break;
                    case 1:
                        paint.setColor(Color.GRAY);
                        break;
                    case 2:
                        paint.setColor(Color.BLUE);
                        break;
                    case 3:
                        paint.setColor(Color.RED);
                        break;
                }
                canvas.drawRect(imageX, imageY, imageX + Data.cdellWidth, imageY + Data.cdellHeight, paint);
                imageX += Data.cdellHeight;
            }
            imageY += Data.cdellHeight;
        }

        for(int i = 0;i < entitys.size();i++){
            Entity e = entitys.get(i);
            e.onDraw(canvas,paint);
        }
        invalidate();
    }

    public void save(String name) throws IOException {
        File filesDir = getContext().getFilesDir();
        File mapFile = new File(filesDir, name + ".devn");
        FileWriter out = new FileWriter(mapFile.getPath());

        Data.save(out);
        out.write("\n");

        for(int i = 0; i < Data.mapWidth;i++ ){
            for(int j = 0;j < Data.mapHeight;j++){
                out.write(Integer.toString(maparr[i][j]) + " ");
            }
            out.write("\n");
        }

        out.write(Integer.toString(entitys.size()) + "\n");

        for(int i = 0;i < entitys.size();i++){
            entitys.get(i).save(out);
            out.write("\n");
        }
    }

    public void open (String name) throws IOException{
        File filesDir = getContext().getFilesDir();
        File mapFile = new File(filesDir, name + ".devn");
        Scanner in = new Scanner(mapFile);

        Data.open(in.nextLine());
    }
}
