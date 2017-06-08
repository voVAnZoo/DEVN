package com.example.user.devn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenMapActivity extends AppCompatActivity {

    static int idpos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_map);

        final Intent game = new Intent(this, GameActivity.class);

        View rootView = findViewById(android.R.id.content);
        final File filesDir = rootView.getContext().getFilesDir();
        File[] files = filesDir.listFiles();

        final ListView lvMain = (ListView) findViewById(R.id.lvMain);
        final List<String> names = new ArrayList<String>();

        for (File f: files){
            names.add(f.getName().replaceAll(".devn",""));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, names);

        lvMain.setAdapter(adapter);

        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idpos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button del = (Button) findViewById(R.id.del);
        Button cont = (Button) findViewById(R.id.contin);

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idpos != -1) {
                    game.putExtra("continue", names.get(idpos));
                    startActivity(game);
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idpos != -1) {
                    String name = names.get(idpos);
                    names.remove(idpos);
                    adapter.notifyDataSetChanged();
                    File newFile = new File(filesDir, name + ".devn");
                    newFile.delete();
                }
            }
        });



    }
}
