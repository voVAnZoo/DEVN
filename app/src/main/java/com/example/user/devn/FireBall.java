package com.example.user.devn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Vova on 15.06.2017.
 */

public class FireBall extends Entity{

    Bitmap bitmap;

    float speedX;
    float speedY;

    public FireBall(float mx, float my, float width, float height, double speedX, double speedY, GameMap gm) {
        super(mx, my, width, height, gm);
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap = BitmapFactory.decodeResource(gm.getContext().getApplicationContext().getResources(), R.drawable.fireball, options);
        bitmap =  Bitmap.createScaledBitmap(bitmap, (int)width, (int)height, false);

        this.speedX = (float) speedX;
        this.speedY = (float) speedY;
    }

    public FireBall(float mx, float my, float width, float height, int level, float hp, GameMap gm) {
        super(mx, my, width, height, level, hp, gm);
        BitmapFactory.Options options = new BitmapFactory.Options();
        bitmap = BitmapFactory.decodeResource(gm.getContext().getApplicationContext().getResources(), R.drawable.fireball, options);
        bitmap =  Bitmap.createScaledBitmap(bitmap, (int)width, (int)height, false);

        speedX = 10;
        speedY = 10;
    }

    @Override
    void onDraw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap,(int)mx - Data.camX,(int)my - Data.camY,paint);
    }

    @Override
    public void save(FileWriter out) {
        try {
            out.write("f ");
            super.save(out);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void action() {
        try {
            if (gm.maparr[(int) my / Data.cellHeight][(int) mx / Data.cellWidth] == 1) {
                this.death();
            }
        }catch (ArrayIndexOutOfBoundsException e){
            this.death();
        }
        for (int i = 0;i < gm.entitys.size();i++){
            if(gm.entitys.get(i) != null) {
                if ((gm.entitys.get(i) != gm.player) && (gm.entitys.get(i) != this)) {
                    if(Entity.is_collide(gm.entitys.get(i),this)){
                        gm.entitys.get(i).addHp(Data.damag * gm.player.level/gm.entitys.get(i).level);
                        this.death();
                        break;
                    }
                }
            }
        }
        addMx(speedX);
        addMy(speedY);
    }
}
