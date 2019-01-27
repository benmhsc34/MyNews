package com.example.benja.myapplication.Toolbar;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.benja.myapplication.R;

import java.util.Objects;

public class HelpActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TextView contentTextView = findViewById(R.id.helpContent);

        contentTextView.setText("This is the help page. \n" +
                " You can write whatever you want to \n" +
                " You don't have to if you don't want to though \n" +
                " It depends if you something to say or not \n" +
                " But if you do you should absolutely go ahead");


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }
}
