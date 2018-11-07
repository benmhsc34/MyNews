package com.example.benja.myapplication.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
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

    public static final String MY_PREFS_NAME = "MyPrefsFile";
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

        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final boolean isChecked = prefs.getBoolean("isChecked", false);
        boolean isArtsChecked = prefs.getBoolean("isArtsChecked", false);
        boolean isBusinessChecked = prefs.getBoolean("isBusinessChecked", false);
        boolean isSportsChecked = prefs.getBoolean("isSportsChecked", false);
        boolean isEntrepreneursChecked = prefs.getBoolean("isEntrepreneursChecked", false);
        boolean isPoliticsChecked = prefs.getBoolean("isPoliticsChecked", false);
        boolean isTravelChecked = prefs.getBoolean("isTravelChecked", false);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                editor.putString("editTextNotification", editText.getText().toString());
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                editor.putString("editTextNotification", editText.getText().toString());
                editor.apply();
            }
        });
        String editTextValue = prefs.getString("editTextNotification", "");
        editText.setText(editTextValue);

        artsCB.setChecked(isArtsChecked);
        artsCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("isArtsChecked", b);
                editor.apply();
            }
        });

        sportsCB.setChecked(isSportsChecked);
        sportsCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("isSportsChecked", b);
                editor.apply();
            }
        });
        travelCB.setChecked(isTravelChecked);
        travelCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("isTravelChecked", b);
                editor.apply();
            }
        });
        businessCB.setChecked(isBusinessChecked);
        businessCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("isBusinessChecked", b);
                editor.apply();
            }
        });
        entrepreneursCB.setChecked(isEntrepreneursChecked);
        entrepreneursCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("isEntrepreneursChecked", b);
                editor.apply();
            }
        });
        politicsCB.setChecked(isPoliticsChecked);
        politicsCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("isPoliticsChecked", b);
                editor.apply();
            }
        });

        if (notificationSwitch != null) {
            notificationSwitch.setOnCheckedChangeListener(this);
            notificationSwitch.setChecked(isChecked);
        }
    }


    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, "Notifications are " + (isChecked ? "on" : "off"), Toast.LENGTH_SHORT).show();

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean("isChecked", isChecked);
        editor.apply();

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
