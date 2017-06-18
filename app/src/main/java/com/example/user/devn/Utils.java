package com.example.user.devn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

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

}
