package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;

public class Reminders extends AppCompatActivity {
    Date foodOrderReminder;
    Date nextOfficeVisitReminder;
    Date distemperReminder;
    Date rabiesReminder;
    Date parvoReminder;
    Date hepatitisReminder;
    boolean getReminders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        AlarmReceiver nm = new AlarmReceiver();
        Log.d("message", "setting alarm");
        nm.setAlarm(this);
    }

    public Reminders() {
        getReminders = false;
    }

    public void setFoodOrderReminder(Date d){
        this.foodOrderReminder = d;

        SharedPreferences sharedPrefs = getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putInt("foodOrderReminder", 10);

    }
}