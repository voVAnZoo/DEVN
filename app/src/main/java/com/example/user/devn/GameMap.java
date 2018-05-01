package com.example.user.devn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    public  Level  map;
    Bitmap wall;
    Bitmap start;
    Bitmap finish;
    Bitmap ground;
    int level;

    public List<Entity> entitys;

    public Player player;

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

    public void init() {
        entitys = new ArrayList<>();
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < entitys.size(); i++) {
                    Entity e = entitys.get(i);
                    e.action();
                }
            }
        };
        t.schedule(timerTask, 0, 50);
        BitmapFactory.Options options = new BitmapFactory.Options();

        wall = BitmapFactory.decodeResource(getContext().getApplicationContext().getResources(), R.drawable.wall, options);
        wall = Bitmap.createScaledBitmap(wall, Data.cellWidth, Data.cellHeight, false);
        start = BitmapFactory.decodeResource(getContext().getApplicationContext().getResources(), R.drawable.start, options);
        start = Bitmap.createScaledBitmap(start, Data.cellWidth, Data.cellHeight, false);
        finish = BitmapFactory.decodeResource(getContext().getApplicationContext().getResources(), R.drawable.finish, options);
        finish = Bitmap.createScaledBitmap(finish, Data.cellWidth, Data.cellHeight, false);
        ground = BitmapFactory.decodeResource(getContext().getApplicationContext().getResources(), R.drawable.ground, options);
        ground = Bitmap.createScaledBitmap(ground, Data.cellWidth, Data.cellHeight, false);
    }



    public void generate(int level, Player pl) {
        this.level = level;
        entitys = new ArrayList<>();
        Random rand = new Random();
        int startX = map.minx;
        int startY = map.miny;
        int finishX= map.maxx;
        int finishY= map.maxy;
        int i = 0;
        if(pl == null) {
            player = new Player((startX + Data.startWidth / 2) * Data.cellWidth, (startY + Data.startHeight / 2) * Data.cellHeight, Data.cellWidth/2, (int) (Data.cellHeight*0.8), this);
        }else {
            player = pl;
            pl.mx = (startX + Data.startWidth / 2) * Data.cellWidth;
            pl.my = (startY + Data.startHeight / 2) * Data.cellHeight;
        }
        Data.camX = (int) (player.mx + player.width/2 - Data.sizeX/2);
        Data.camY = (int) (player.my + player.height/2 - Data.sizeY/2);

        if (Data.camX < 0) {
            Data.camX = 0;
        }else {
            if(Data.camX  > Data.mapWidth*Data.cellWidth - Data.sizeX){
                Data.camX = Data.mapWidth*Data.cellWidth - Data.sizeX;
            }
        }

        if (Data.camY < 0) {
            Data.camY = 0;
        }else {
            if(Data.camY > Data.mapHeight*Data.cellHeight - Data.sizeY){
                Data.camY = Data.mapHeight*Data.cellHeight - Data.sizeY;
            }
        }

        entitys.add(player);

        generateMonsters();
        if(pl == null) {
            player.hp = 100;
        }
    }


    public void generateMonsters() {
        Random rand = new Random();
        float a = 100;
        Monster monster;
        for (int y = 0; y < Data.mapHeight; y++)
            for (int x = 0; x < Data.mapWidth; x++)
                if (map.maparr[y][x] == 0)
                    if (rand.nextInt(30) == 1) {
                        monster = new Monster(x * Data.cellWidth, y * Data.cellHeight, Data.cellWidth / 10 * 8, Data.cellHeight / 10 * 8,level,a, this);
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
                switch (map.maparr[i][j]) {
                    case 1:
                        canvas.drawBitmap(ground,imageX, imageY,paint);
                        break;
                    case 0:
                        canvas.drawBitmap(wall,imageX, imageY,paint);
                        break;
                    case 2:
                        canvas.drawBitmap(start,imageX, imageY,paint);
                        break;
                    case 3:
                        canvas.drawBitmap(finish,imageX, imageY,paint);
                        break;
                }
                imageX += Data.cellHeight;
            }
            imageY += Data.cellHeight;
        }

        for (int i = 0; i < entitys.size(); i++) {
            Entity e = entitys.get(i);
            e.onDraw(canvas, paint);
        }
        paint.setColor(Color.RED);
        canvas.drawRect(Data.sizeX - 100,20,player.hp + Data.sizeX - 100,40,paint);
        invalidate();
    }

    public void save(String name) throws IOException {
        File filesDir = getContext().getFilesDir();
        File mapFile = new File(filesDir, name + ".devn");
        FileWriter out = new FileWriter(mapFile.getPath());

        Data.save(out);
        out.write("\n");

        for (int i = 0; i < Data.mapWidth; i++) {
            for (int j = 0; j < Data.mapHeight; j++) {
                out.write(Integer.toString(map.maparr[j][i]) + " ");
            }
            out.write("\n");
        }

        out.write(Integer.toString(entitys.size()) + "\n");

        for (int i = 0; i < entitys.size(); i++) {
            entitys.get(i).save(out);
            out.write("\n");
        }
        out.write(Integer.toString(level) + "\n");
        out.flush();
        out.close();
    }

    public void open(String name) throws IOException {
        File filesDir = getContext().getFilesDir();
        File mapFile = new File(filesDir, name + ".devn");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(mapFile));

        Data.open(bufferedReader.readLine());

        String s;
        map.maparr = new int[Data.mapHeight][Data.mapWidth];
        for (int i = 0; i < Data.mapWidth; i++) {
            s = bufferedReader.readLine();
            for (int j = 0; j < Data.mapHeight; j++) {
                map.maparr[j][i] = Integer.parseInt(s.substring(0, s.indexOf(" ")));
                s = s.substring(s.indexOf(" ") + 1);
            }
        }

        s = bufferedReader.readLine();
        int ss = Integer.parseInt(s);
        for (int i = 0; i < ss; i++) {
            s = bufferedReader.readLine();
            char a = s.toCharArray()[0];
            s = s.substring(s.indexOf(" ") + 1);
            switch (a) {
                case 'p':
                    player = new Player(s,this);
                    entitys.add(player);
                    break;
                case 'm':
                    entitys.add(new Monster(s, this));
                    break;
                case 'f':
                    break;
            }
        }

        s = bufferedReader.readLine();
        level = Integer.parseInt(s);
    }
}
