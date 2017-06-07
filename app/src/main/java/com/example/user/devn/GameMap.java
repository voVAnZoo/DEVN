package com.example.user.devn;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Vector;

/**
 * Created by user on 6/6/17.
 */

public class GameMap extends View {
    public int maparr[][];

    public static Vector entity;

    public GameMap(Context context) {
        super(context);
    }

    public GameMap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GameMap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static void init(){

    }
}
