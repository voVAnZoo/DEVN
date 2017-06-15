package com.example.user.devn;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 6/14/17.
 */

public class SaveDialog extends DialogFragment {

    private SaveNameListener saveNameListener;

    public void setSaveNameListener(SaveNameListener saveNameListener) {
        this.saveNameListener = saveNameListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog_layout, null);

        final Button btok = (Button) v.findViewById(R.id.OkButton);
        final Button btno = (Button) v.findViewById(R.id.NoButton);
        final EditText et = (EditText) v.findViewById(R.id.worldname);

        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveNameListener != null) {
                    saveNameListener.onSaveName(et.getText().toString());
                    System.out.println("dgdsfsf");
                }
            }
        });

        btno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }

    interface SaveNameListener {

        void onSaveName(final String saveName);

    }

}
