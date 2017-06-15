
package com.example.user.devn;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
            Data.sizeX = size.x;
            Data.sizeY = size.y;
        }else {
            Data.sizeX = display.getWidth();
            Data.sizeY = display.getHeight();
        }

       Data.sizeY -= 50;

        final Intent game = new Intent(this, GameActivity.class);
        Button bStart = (Button) findViewById(R.id.start);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(game);
            }
        });

        final Intent sett = new Intent(this, SettingActivity.class);
        Button button = (Button) findViewById(R.id.settingButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(sett);
            }
        });

        final Intent list = new Intent(this,OpenMapActivity.class);
        Button bContin = (Button) findViewById(R.id.continueButton);
        bContin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(list);
            }
        });



    }
}
