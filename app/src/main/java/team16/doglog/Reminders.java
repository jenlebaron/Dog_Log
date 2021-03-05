package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reminders extends AppCompatActivity {
    Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
    }

    //called when user taps save button
    public void saveReminders(View view){
        Map<String, Long> reminderDates = new HashMap<>();
        Long dateInMillis;
Log.d("message", "save button pressed");
        //add food reminder to map of reminder dates
        EditText month = (EditText) findViewById(R.id.food_month);
        EditText day = (EditText)findViewById(R.id.food_day);
        EditText year = (EditText)findViewById(R.id.food_year);
        dateInMillis = dateToMillis(month, day, year);
        reminderDates.put("food", dateInMillis);

        //add office visit reminder to map of reminder dates
        month = (EditText) findViewById(R.id.office_visit_month);
        day = (EditText)findViewById(R.id.office_visit_day);
        year = (EditText)findViewById(R.id.office_visit_year);
        dateInMillis = dateToMillis(month, day, year);
        reminderDates.put("office visit", dateInMillis);

        //add distemper shot reminder to map of reminder dates
        month = (EditText) findViewById(R.id.distemper_month);
        day = (EditText)findViewById(R.id.distemper_day);
        year = (EditText)findViewById(R.id.distemper_year);
        dateInMillis = dateToMillis(month, day, year);
        reminderDates.put("distemper shot", dateInMillis);

        //add rabies shot reminder to map of reminder dates
        month = (EditText) findViewById(R.id.rabies_month);
        day = (EditText)findViewById(R.id.rabies_day);
        year = (EditText)findViewById(R.id.rabies_year);
        dateInMillis = dateToMillis(month, day, year);
        reminderDates.put("rabies shot", dateInMillis);

        //add parvo shot reminder to map of reminder dates
        month = (EditText) findViewById(R.id.parvo_month);
        day = (EditText)findViewById(R.id.parvo_day);
        year = (EditText)findViewById(R.id.parvo_year);
        dateInMillis = dateToMillis(month, day, year);
        reminderDates.put("parvo shot", dateInMillis);

        //add hepatitis shot reminder to map of reminder dates
        month = (EditText) findViewById(R.id.hepatitis_month);
        day = (EditText)findViewById(R.id.hepatitis_day);
        year = (EditText)findViewById(R.id.hepatitis_year);
        dateInMillis = dateToMillis(month, day, year);
        reminderDates.put("hepatitis shot", dateInMillis);

        Presenter presenter = new Presenter();
        presenter.storeReminderDates(reminderDates, this);
        presenter.setAlarms(this);
    }

    private Long dateToMillis(EditText m, EditText d, EditText y){
        Calendar c = Calendar.getInstance();
        int month = Integer.parseInt(m.getText().toString());
        int day = Integer.parseInt(d.getText().toString());
        int year = Integer.parseInt(y.getText().toString());
        c.set(year, month, day);
        Log.d("message", "returning " + c.getTimeInMillis());
        return c.getTimeInMillis();
    }
}