package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        //load reminder dates already set
        loadDates();
    }

    private void loadDates(){
        SharedPreferences sharedPrefs = this.getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        Map<String, ?> dates = sharedPrefs.getAll();
        for (Map.Entry<String,?> e : dates.entrySet()) {

        }
    }

    //called when user taps save button
    public void saveReminders(View view){
        int[] ids = new int[]{R.id.food_month,R.id.food_day,R.id.food_year,
                              R.id.office_visit_month,R.id.office_visit_day,R.id.office_visit_year,
                              R.id.distemper_month,R.id.distemper_day,R.id.distemper_year,
                              R.id.rabies_month,R.id.rabies_day,R.id.rabies_year,
                              R.id.parvo_month,R.id.parvo_day,R.id.parvo_year,
                              R.id.hepatitis_month,R.id.hepatitis_day,R.id.hepatitis_year, };
        String[] keys = new String[]{"food", "office Visit", "distemper shot", "rabies shot",
                                        "parvo shot", "hepatitis shot"};

        Map<String, Long> reminderDates = new HashMap<>();
        Long dateInMillis;

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
                dateInMillis = dateToMillis(month, day, year);
                Log.d("message", "added key: " + keys[i]);
                reminderDates.put(keys[i], dateInMillis);
            }
            else Toast.makeText(this, "Reminder for " + keys[i] + " has an invalid date.", Toast.LENGTH_SHORT).show();
            i++;
        }


        //presenter will save reminders to shared preferences and initiate setting alarms
        Presenter presenter = new Presenter();
        presenter.storeReminderDates(reminderDates, this);
        presenter.setAlarms(this);

        Toast.makeText(this, "Reminders set", Toast.LENGTH_SHORT).show();
    }

    private Long dateToMillis(String m, String d, String y){
        Calendar c = Calendar.getInstance();
        int month = Integer.parseInt(m);
        int day = Integer.parseInt(d);
        int year = Integer.parseInt(y);
        c.set(year, month, day, 15, 16);
        Log.d("message", "returning " + c.getTimeInMillis());
        return c.getTimeInMillis();
    }

    //first checks if all strings are numeric, then validates they are within range
    private boolean isValidDate(String m, String d, String y){
        if(m.matches ("\\d+") && d.matches("\\d+") && y.matches("\\d+")){
            //days with 31 days
            if(m.matches("1|01|3|03|5|05|7|07|8|08|10|12")){
                if(Integer.parseInt(d) >= 1 && Integer.parseInt(d) <= 31){
                    if(Integer.parseInt(y) >= 2000 && Integer.parseInt(y) <= 3000){
                        return true;
                    }
                    else return false;
                }
                else return false;
            }
            //for february
            else if(m.matches("2|02")){
                if(Integer.parseInt(d) >= 1 && Integer.parseInt(d) <= 28){
                    if(Integer.parseInt(y) >= 2000 && Integer.parseInt(y) <= 3000){
                        return true;
                    }
                    else return false;
                }
                else return false;
            }
            //for days with 30 days
            else if(m.matches("4|04|6|06|9|09|11")){
                if(Integer.parseInt(d) >= 1 && Integer.parseInt(d) <= 30){
                    if(Integer.parseInt(y) >= 2000 && Integer.parseInt(y) <= 3000){
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
}