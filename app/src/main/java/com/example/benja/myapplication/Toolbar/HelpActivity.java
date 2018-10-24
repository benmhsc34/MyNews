package com.example.benja.myapplication.Toolbar;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.benja.myapplication.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelpActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView contentTextView = findViewById(R.id.helpContent);

        contentTextView.setText("This is the help page. \n" +
                " You can write whatever you want to \n" +
                " You don't have to if you don't want to though \n" +
                " It depends if you something to say or not \n" +
                " But if you do you should absolutely go ahead");


    }
}
