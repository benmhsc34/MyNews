package com.example.benja.myapplication.Toolbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.benja.myapplication.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView contentTextView = findViewById(R.id.aboutContent);


        contentTextView.setText("This is the about page. \n You can write whatever you want to \n You don't have to if you don't want to though \n It depends if you something to say or not \n But if you do you should absolutely go ahead \n\n\n\n\n Thanks to the New York Times for providing the articles \n Special Thanks to Rohan Taneja to really guide me to success and be able to create the beauty news application");
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
