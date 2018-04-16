package com.example.user.devn.Activitys;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.devn.R;

public class PauseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);


        Button bt2 = (Button) findViewById(R.id.BackButton);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button bt1 = (Button) findViewById(R.id.SaveButton);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                SaveDialog myDialogFragment = new SaveDialog();
                final String[] name = new String[1];
                myDialogFragment.setSaveNameListener(new SaveDialog.SaveNameListener() {
                    @Override
                    public void onSaveName(String saveName) {
                        name[0] = saveName;
                        Intent intent = new Intent();
                        intent.putExtra("name", name[0]);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
                myDialogFragment.show(manager,"sdsd");
            }
        });
    }
}
