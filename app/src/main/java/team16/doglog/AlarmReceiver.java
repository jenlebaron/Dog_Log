package team16.doglog;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;
import java.util.Map;


public class AlarmReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        //create notification channel for reminders
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("Reminders",
                    "Reminders Channel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //create notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Reminders")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Dog Log Reminder")
                .setContentText("It is time to schedule your next " + intent.getStringExtra("type"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //run notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(0, builder.build());
        Log.d("message", "notification for " + intent.getStringExtra("type"));
    }

    public void setAlarm(Context context){
        AlarmManager am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);

       SharedPreferences sharedPrefs = context.getSharedPreferences("Reminders", Context.MODE_PRIVATE);
       Map<String, ?> dates = sharedPrefs.getAll();
       Long dateInMillis;
       String key;
       int code = 0;

        for (Map.Entry<String,?> e : dates.entrySet()){
            key = e.getKey();
            dateInMillis = (Long) e.getValue();
            Intent i = new Intent(context, AlarmReceiver.class);
            i.putExtra("type", key);
            PendingIntent pi = PendingIntent.getBroadcast(context, code, i, PendingIntent.FLAG_CANCEL_CURRENT);
            am.cancel(pi);
            am.set(AlarmManager.RTC_WAKEUP, dateInMillis + 1000 * 10, pi);
            Log.d("message", "set alarm for " + key);
            code++;
        }
        boolean alarmUp = (PendingIntent.getBroadcast(context, 0,
                new Intent(context, AlarmReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 0 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(context, 1,
                new Intent(context, AlarmReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 1 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(context, 2,
                new Intent(context, AlarmReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 2 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(context, 3,
                new Intent(context, AlarmReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 3 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(context, 4,
                new Intent(context, AlarmReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 4 is already active");
        }

        alarmUp = (PendingIntent.getBroadcast(context, 5,
                new Intent(context, AlarmReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            Log.d("message", "Alarm 5 is already active");
        }
    }
}
