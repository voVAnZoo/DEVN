package com.example.user.devn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    static Intent game;
    static Intent list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        game = new Intent(this, GameActivity.class);
        list = new Intent(this,OpenMapActivity.class);
        Button bStart = (Button) findViewById(R.id.start);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(game);
            }
        });

        Button bContin = (Button) findViewById(R.id.continueButton);
        bContin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(list);
            }
        });
    }
}
