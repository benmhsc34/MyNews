package com.example.benja.myapplication.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.benja.myapplication.R;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final EditText searchEditText = findViewById(R.id.editTextSearch);
        final CheckBox artsCB = findViewById(R.id.artsCB);
        final CheckBox businessCB = findViewById(R.id.businessCB);
        final CheckBox entrepreneursCB = findViewById(R.id.entrepreneursCB);
        final CheckBox sportsCB = findViewById(R.id.sportsCB);
        final CheckBox travelCB = findViewById(R.id.travelCB);
        final CheckBox politicsCB = findViewById(R.id.politicsCB);
        final List<String> categoriesSelected = new ArrayList<>();
        final String[] beginDate = new String[1];
        final String[] theEndDate = new String[1];

        searchEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    hideKeyboard(view);
                }
            }
        });


        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchQuery = searchEditText.getText().toString();
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
                String theBeginDateString = beginDate[0];
                String theEndDateString = theEndDate[0];

                Intent myIntent = new Intent(SearchActivity.this, SearchResultActivity.class);
                myIntent.putExtra("searchQuery", searchQuery);
                myIntent.putExtra("categoriesSelected", (ArrayList) categoriesSelected);
                myIntent.putExtra("theBeginDateString", theBeginDateString);
                myIntent.putExtra("theEndDateString", theEndDateString);
                SearchActivity.this.startActivity(myIntent);
            }
        });


        final Calendar myCalendar = Calendar.getInstance();
        //Date picker for the begin date
        EditText edittext = findViewById(R.id.editTextBeginDate);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                EditText editTextBeginDate = findViewById(R.id.editTextBeginDate);
                editTextBeginDate.setText(sdf.format(myCalendar.getTime()));
                beginDate[0] = sdf.format(myCalendar.getTime());

            }
        };


        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SearchActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }


        });
        //Date picker for the end Date
        final DatePickerDialog.OnDateSetListener endDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                EditText editText2 = findViewById(R.id.editTextEndDate);
                editText2.setText(sdf.format(myCalendar.getTime()));
                theEndDate[0] = sdf.format(myCalendar.getTime());
            }
        };

        EditText editText2 = findViewById(R.id.editTextEndDate);
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(SearchActivity.this, endDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public void hideKeyboard(View rootView) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
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
