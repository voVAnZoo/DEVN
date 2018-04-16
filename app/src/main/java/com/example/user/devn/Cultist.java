package com.example.user.devn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Cultist extends Monster{

    public Cultist(float mx, float my, int width, int height, GameMap gm) {
        super(mx, my, width, height, gm);
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap1 = BitmapFactory.decodeResource(gm.getContext().getApplicationContext().getResources(), R.drawable.monster2, options);
        bitmap1 =  Bitmap.createScaledBitmap(bitmap1, (int)width, (int)height, false);

    }

    public Cultist(float mx, float my, int width, int height, int level, float hp, GameMap gm) {
        super(mx, my, width, height, level, hp, gm);
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap1 = BitmapFactory.decodeResource(gm.getContext().getApplicationContext().getResources(), R.drawable.monster2, options);
        bitmap1 =  Bitmap.createScaledBitmap(bitmap1, (int)width, (int)height, false);
    }



}
