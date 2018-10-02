package com.smag.androidlearning;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.smag.androidlearning.helper.RecyclerAdapterEDT;

public class EditProfil extends Activity {

    private CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        calendarView =(CalendarView) findViewById(R.id.date_picker);

        calendarView.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        // display the selected date by using a toast
                       // Toast.makeText(getApplicationContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

// 1. Instantiate an AlertDialog.Builder with its constructor
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

// 2. Chain together various setter methods to set the dialog characteristics
                        builder.setMessage("Test message")
                                .setTitle("Test Titre");

// 3. Get the AlertDialog from create()
                        AlertDialog dialog = builder.create();
                    }
                });
    }

}
