package team16.doglog;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Presenter {
    //stores dates for reminders in shared preferences
    public void storeReminderDates(Map<String, Long> dates, Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
Log.d("message", "cleared shared prefs");
        String key;

        for (Map.Entry<String,Long> e : dates.entrySet()){
            Log.d("message", "added to shared prefs: " + e.getKey());
            key = e.getKey();
            editor.putLong(key, e.getValue());
        }

        editor.apply();
    }

    public void setAlarms(Context context){
        AlarmReceiver nm = new AlarmReceiver();
        nm.setAlarm(context);
    }

}
