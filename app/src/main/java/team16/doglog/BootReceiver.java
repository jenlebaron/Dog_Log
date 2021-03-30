package team16.doglog;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String[] keys = new String[]{"foodMonth", "foodDay", "foodYear",
                "officeVisitMonth", "officeVisitDay", "officeVisitYear",
                "distemperShotMonth", "distemperShotDay", "distemperShotYear",
                "rabiesShotMonth", "rabiesShotDay", "rabiesShotYear",
                "parvoShotMonth", "parvoShotDay", "parvoShotYear",
                "hepatitisShotMonth", "hepatitisShotDay", "hepatitisShotYear"};
        String[] reminder = new String[]{"food", "office visit", "distemper shot",
                "rabies shot", "parvo Shot", "hepatitis shot"};

        SharedPreferences sharedPref = context.getSharedPreferences("ReminderDate", Context.MODE_PRIVATE);
        String month, day, year;
        Reminders rem = new Reminders();
        int code = 0;
        //loop through keys to get stored dates for reminders
        for(int i = 0; i < keys.length; i++){
           month = sharedPref.getString(keys[i], "");
           i++;
           day = sharedPref.getString(keys[i], "");
           i++;
           year = sharedPref.getString(keys[i], "");
           //check if there is a date saved
           if(month.length() > 0 && day.length() > 0 && year.length() > 0){
                Long millisFromNow;
                millisFromNow = dateToMillis(month, day, year);
                rem.setAlarm(millisFromNow, code, reminder[code]);
           }
           code++;
        }

    }

    private Long dateToMillis(String m, String d, String y){
        Calendar c = Calendar.getInstance();
        int month = Integer.parseInt(m) - 1; //Calendar month is base 0, so have to subtract 1 from month user gives
        int day = Integer.parseInt(d);
        int year = Integer.parseInt(y);
        c.set(year, month, day);
        Calendar now = Calendar.getInstance();
        Long millisFromNow = c.getTimeInMillis() - now.getTimeInMillis();
        Log.d("message", "3returning " + millisFromNow);
        //return number of millis from now until alarm should go off
        return millisFromNow;
    }
}
