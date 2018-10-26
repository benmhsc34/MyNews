package com.example.benja.myapplication.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.benja.myapplication.R;
import com.example.benja.myapplication.Utils.Api;
import com.example.benja.myapplication.Utils.NotficationReceiver;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleFolder;
import com.example.benja.myapplication.Utils.Search_API.SearchArticleList;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText editText;
    CheckBox artsCB;
    CheckBox businessCB;
    CheckBox entrepreneursCB;
    CheckBox sportsCB;
    CheckBox travelCB;
    CheckBox politicsCB;
    List<String> categoriesSelected = new ArrayList<>();
    @SuppressLint("SimpleDateFormat")
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    Date date = new Date();

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, "The Switch is " + (isChecked ? "on" : "off"),
                Toast.LENGTH_SHORT).show();
        if (isChecked) {
            String searchQuery = editText.getText().toString();
            if (artsCB.isChecked()) {
                categoriesSelected.add("arts");
            }
            if (entrepreneursCB.isChecked()) {
                categoriesSelected.add("entrepreneurs");
            }
            if (businessCB.isChecked()) {
                categoriesSelected.add("business");
            }
            if (sportsCB.isChecked()) {
                categoriesSelected.add("sports");
            }
            if (travelCB.isChecked()) {
                categoriesSelected.add("travel");
            }
            if (politicsCB.isChecked()) {
                categoriesSelected.add("politics");
            }

            //Setting Notification Receiver for user to receive daily notifications if new relevant articles have been released
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.HOUR_OF_DAY, 5);
            calendar.set(Calendar.MINUTE, 52);
            calendar.set(Calendar.SECOND, 12);

            Intent myIntent = new Intent(getApplicationContext(), NotficationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            assert alarmManager != null;
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

            Intent intent = new Intent("my.action.string");
            intent.putExtra("searchQuery", searchQuery);
            intent.putExtra("categoriesSelected", (ArrayList) categoriesSelected);
            sendBroadcast(intent);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        editText = findViewById(R.id.editTextSearchNotification);
        artsCB = findViewById(R.id.artsCB);
        businessCB = findViewById(R.id.businessCB);
        entrepreneursCB = findViewById(R.id.entrepreneursCB);
        sportsCB = findViewById(R.id.sportsCB);
        travelCB = findViewById(R.id.travelCB);
        politicsCB = findViewById(R.id.politicsCB);

        Switch notificationSwitch = findViewById(R.id.notificationSwitch);

        if (notificationSwitch != null) {
            notificationSwitch.setOnCheckedChangeListener(this);

        }





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
