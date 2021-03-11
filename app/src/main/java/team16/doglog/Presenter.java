package team16.doglog;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Presenter {
    //stores dates for reminders in shared preferences
    public void storeReminderDates(Map<String, Long> dates, Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences("Reminders", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        Log.d("message", "cleared shared prefs");
        String key;

        for (Map.Entry<String, Long> e : dates.entrySet()) {
            Log.d("message", "added to shared prefs: " + e.getKey());
            key = e.getKey();
            editor.putLong(key, e.getValue());
        }

        editor.apply();
    }

    public void setAlarms(Context context) {
        AlarmReceiver nm = new AlarmReceiver();
        nm.setAlarm(context);
    }

    //stores note for medical history in shared preferences
    public void saveNote(Context context, String date, String note) {
        Log.d("message", "presenter saved note");
        SharedPreferences sharedPrefs = context.getSharedPreferences("medicalHistoryNotes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(date, note);
        editor.apply();

        Log.d("message", sharedPrefs.getAll().toString());
    }

//    //stores note for shots and medication in shared preferences
//    public void saveShotsMedication(Context context, String distemper_shot_month,
//                                    String distemper_shot_day, String distemper_shot_year,
//                                    String rabies_shot_month, String rabies_shot_day,
//                                    String rabies_shot_year, String parvo_shot_month,
//                                    String parvo_shot_day, String parvo_shot_year,
//                                    String hepatitis_shot_month, String hepatitis_shot_day,
//                                    String hepatitis_shot_year, String other_shot_name,
//                                    String other_shot_month, String other_shot_day,
//                                    String other_shot_year, String medication_info) {
//
//        Log.d("message", "presenter saved shots and medication");
//        SharedPreferences sharedPrefs = context.getSharedPreferences("ShotsMedication.txt", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putString("DISTEMPER_SHOTS_MONTH", distemper_shot_month);,
//        ("DISTEMPER_SHOTS_DAY", distemper_shot_day);,
//        ("DISTEMPER_SHOTS_YEAR", distemper_shot_year),("RABIES_SHOTS_MONTH", rabies_shot_month);,
//        ("RABIES_SHOTS_DAY", rabies_shot_day),("RABIES_SHOTS_YEAR", rabies_shot_year);,
//        ("PARVO_SHOTS_MONTH", parvo_shot_month);,
//        ("PARVO_SHOTS_DAY", parvo_shot_day),("PARVO_SHOTS_YEAR", parvo_shot_year);,
//        ("HEPATITIS_SHOTS_MONTH", hepatitis_shot_month),
//        ("HEPATITIS_SHOTS_DAY", hepatitis_shot_day),("HEPATITIS_SHOTS_YEAR", hepatitis_shot_year),
//        ("OTHER_SHOTS_NAME", other_shot_name),
//        ("OTHER_SHOTS_MONTH", other_shot_month),("OTHER_SHOTS_DAY", other_shot_day),
//        ("OTHER_SHOTS_YEAR", other_shot_year),("MEDICATION", medication_info);
//        editor.apply();
//
//        Log.d("message", sharedPrefs.getAll().toString());
//    }
//}

    //stores note for shots and medication in shared preferences
    public void saveShotsMedication(Map<String, Long> dates, Context context){
        SharedPreferences sharedPrefs = context.getSharedPreferences("ShotsMedication.txt", Context.MODE_PRIVATE);
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

}
