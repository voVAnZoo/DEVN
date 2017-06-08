package com.example.user.devn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.os.EnvironmentCompat;
import android.util.AttributeSet;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by user on 6/6/17.
 */

public class GameMap extends View {
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

        player = new Player(10,10,200,200);

        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for(int i = 0;i < entitys.size();i++){
                    Entity e = entitys.get(i);
                    e.action();
                }
            }
        };
        t.schedule(timerTask,0,100);

        entitys.add(player);
    }

    public void addEntity(Entity e){
        entitys.add(e);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i = 0;i < entitys.size();i++){
            Entity e = entitys.get(i);
            Paint paint = new Paint();
            e.onDraw(canvas,paint);
        }
        invalidate();
    }

    public void seve(String name) throws IOException {
        File filesDir = getContext().getFilesDir();
        File mapFile = new File(filesDir, name + ".devn");
        OutputStream out = new FileOutputStream(mapFile);

        out.write(Data.mapWidth);
        out.write(Data.mapHeight);

        for(int i = 0; i < Data.mapWidth;i++ ){
            for(int j = 0;j < Data.mapHeight;j++){
                out.write(maparr[i][j]);
            }
        }



        File[] files = filesDir.listFiles();
    }
}
