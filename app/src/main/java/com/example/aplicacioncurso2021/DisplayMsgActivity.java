package com.example.aplicacioncurso2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_msg);

        Intent intent=getIntent();
        String msg=intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textview= findViewById(R.id.textMsg);
        textview.setText(msg);
    }
}