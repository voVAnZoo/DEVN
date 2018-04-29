package com.example.user.devn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

import java.util.Random;

/**
 * Created by user on 6/16/17.
 */

public class Utils {

   public static Bitmap flip(final Bitmap srcBitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        return Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, false);
   }

    public static boolean is_collide(Entity a, Entity b){
        double ax, ay,bx,by;
        ax = a.mx + (a.width/2);
        ay = a.my + (a.height/2);
        bx = b.mx + (b.width/2);
        by = b.my + (b.height/2);

        if((Math.abs(ax - bx) < a.width/2 + b.width/2)&&
                (Math.abs(ay - by) < a.height/2 + b.height/2 )){
            return(true);
        } else {
            return (false);
        }
    }

    public static int dpToPx(final Context context, final int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(final Context context, final int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static Room goodrandom(int level){
        double s = 10;
        double h = 0,w = 0,height,width,t,u,r,x,y,radius = level+5;
        Random rnd=new Random(System.currentTimeMillis());
        while ((s<=0) ||(s>1)) {
            h = -1+2*Math.random();
            w = -1+2*Math.random();
            s=h*h+w*w;
        }
        height=2*Math.ceil(Math.abs(h)*Math.sqrt(-2*Math.log(s)/s)*6);
        width=2*Math.ceil(Math.abs(w)*Math.sqrt(-2*Math.log(s)/s)*6);
        t = 2*Math.PI*Math.random();
        u = Math.random()+Math.random();
        if (u > 1)
            r = 2-u;
        else
            r = u;
        x=radius*r*Math.cos(t);
        y=radius*r*Math.sin(t);
        return new Room((int)x,(int)y,(int)height,(int)width);
    }

}
