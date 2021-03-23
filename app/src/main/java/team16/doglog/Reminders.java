package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Reminders extends AppCompatActivity {
    private int[] ids = new int[]{R.id.food_month,R.id.food_day,R.id.food_year,
            R.id.office_visit_month,R.id.office_visit_day,R.id.office_visit_year,
            R.id.distemper_month,R.id.distemper_day,R.id.distemper_year,
            R.id.rabies_month,R.id.rabies_day,R.id.rabies_year,
            R.id.parvo_month,R.id.parvo_day,R.id.parvo_year,
            R.id.hepatitis_month,R.id.hepatitis_day,R.id.hepatitis_year};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        this.loadDates();
    }

    //called when activity is created
    public void loadDates(){
        String[] keys = new String[]{"foodMonth", "foodDay", "foodYear",
                "officeVisitMonth", "officeVisitDay", "officeVisitYear",
                "distemperShotMonth", "distemperShotDay", "distemperShotYear",
                "rabiesShotMonth", "rabiesShotDay", "rabiesShotYear",
                "parvoShotMonth", "parvoShotDay", "parvoShotYear",
                "hepatitisShotMonth", "hepatitisShotDay", "hepatitisShotYear"};

        Log.d("message", "entered loadDates");
        SharedPreferences sharedPref = getSharedPreferences("ReminderDate", Context.MODE_PRIVATE);
        Log.d("message", sharedPref.getAll().toString());

        //get saved dates from shared preferences
        String value;
        EditText editText;
        for(int i = 0; i < keys.length; i++){
            value = sharedPref.getString(keys[i], "");
            editText = findViewById(ids[i]);
            editText.setText(value);
        }

    }

    //called when user taps save button
    public void saveReminders(View view){
        String[] keys = new String[]{"food", "officeVisit", "distemperShot", "rabiesShot",
                                        "parvoShot", "hepatitisShot"};

       // Map<String, Long> reminderDates = new HashMap<>();
        Long millisFromNow;
        SharedPreferences sharedPrefs = this.getSharedPreferences("ReminderDate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        //clear previously saved dates
        editor.clear();
        editor.commit();

        int i =0;
        for(int j = 0; j < ids.length; j++){
            EditText t = (EditText) findViewById(ids[j]);
            String month = t.getText().toString();
            j++;
            t = (EditText) findViewById(ids[j]);
            String day = t.getText().toString();
            j++;
            t = (EditText) findViewById(ids[j]);
            String year = t.getText().toString();


            //validate date before storing reminder and setting alarm
            if(isValidDate(month, day, year)){
                editor.putString(keys[i] + "Month", month);
                editor.putString(keys[i] + "Day", day);
                editor.putString(keys[i] + "Year", year);
               // editor.apply();
                millisFromNow = dateToMillis(month, day, year);
                if(millisFromNow >= 0) {
                    setAlarm((long) 5000, i, keys[i]);
                }
                else Toast.makeText(this, "Reminder for " + keys[i] + " has already passed.", Toast.LENGTH_SHORT).show();
                Log.d("message", "added key: " + keys[i]);

            }
            else Toast.makeText(this, "Reminder for " + keys[i] + " has an invalid date.", Toast.LENGTH_SHORT).show();
            i++;
        }

        editor.apply();
        //presenter will save reminders to shared preferences and initiate setting alarms
        /*Presenter presenter = new Presenter();
        presenter.storeReminderDates(reminderDates, this);
        presenter.setAlarms(this);*/

        Toast.makeText(this, "Reminders set", Toast.LENGTH_SHORT).show();
    }

    private Long dateToMillis(String m, String d, String y){
        Calendar c = Calendar.getInstance();
        int month = Integer.parseInt(m) - 1;
        int day = Integer.parseInt(d);
        int year = Integer.parseInt(y);
        //c.setTimeZone(m)
        c.set(year, month, day);
        Calendar now = Calendar.getInstance();
        Long millisFromNow = c.getTimeInMillis() - now.getTimeInMillis();
        Log.d("message", "3returning " + millisFromNow);
        //return number of millis from now until alarm should go off
        return millisFromNow;
    }

    //first checks if all strings are numeric, then validates they are within range
    private boolean isValidDate(String m, String d, String y){
        if(m.matches ("\\d+") && d.matches("\\d+") && y.matches("\\d+")){
            //days with 31 days
            if(m.matches("1|01|3|03|5|05|7|07|8|08|10|12")){
                if(Integer.parseInt(d) >= 1 && Integer.parseInt(d) <= 31){
                    if(Integer.parseInt(y) >= 2021 && Integer.parseInt(y) <= 3000){
                        return true;
                    }
                    else return false;
                }
                else return false;
            }
            //for february
            else if(m.matches("2|02")){
                if(Integer.parseInt(d) >= 1 && Integer.parseInt(d) <= 28){
                    if(Integer.parseInt(y) >= 2021 && Integer.parseInt(y) <= 3000){
                        return true;
                    }
                    else return false;
                }
                else return false;
            }
            //for days with 30 days
            else if(m.matches("4|04|6|06|9|09|11")){
                if(Integer.parseInt(d) >= 1 && Integer.parseInt(d) <= 30){
                    if(Integer.parseInt(y) >= 2021 && Integer.parseInt(y) <= 3000){
                        return true;
                    }
                    else return false;
                }
                else return false;
            }
            else return false;
        }
        else return false;
    }

    public void setAlarm(Long time, int code, String key){
        AlarmManager am = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);

        //cancel all alarms
        for(int rc = 0; rc < 6; rc++){
            //Log.d("message", "canceling alarm" + rc);
            am.cancel(PendingIntent.getBroadcast(this, rc,
                    new Intent(this, Reminders.class),
                    0));
        }

        //Intent i = new Intent(this, AlarmReceiver.class);
            if(key.matches("food")){
                // i.setData(Uri.parse("https://chewy.com"));
                //i.putExtra("type", key);
                Intent notifIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://chewy.com"));
                notifIntent.putExtra("type", key);
                PendingIntent pi = PendingIntent.getActivity(this, code, notifIntent, 0);
                am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pi);
                Log.d("message", "in set alarm matches food");
            }
            else {
                Intent i = new Intent(this, AlarmReceiver.class);
                i.putExtra("type", key);
                PendingIntent pi = PendingIntent.getBroadcast(this, code, i, 0);
                am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pi);
            }
            Log.d("message", "set alarm for " + key);

        boolean alarmUp = (PendingIntent.getBroadcast(this, 0,
                new Intent(this, AlarmReceiver.class),
                0) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 0 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(this, 1,
                new Intent(this, AlarmReceiver.class),
                0) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 1 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(this, 2,
                new Intent(this, AlarmReceiver.class),
                0) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 2 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(this, 3,
                new Intent(this, AlarmReceiver.class),
                0) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 3 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(this, 4,
                new Intent(this, AlarmReceiver.class),
                0) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 4 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(this, 5,
                new Intent(this, AlarmReceiver.class),
                0) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 5 is already active");
        }
    }
}