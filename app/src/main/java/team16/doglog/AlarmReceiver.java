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
                .setContentTitle("Test Notification")
                .setContentText("success")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //run notification
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(0, builder.build());
    }

    public void setAlarm(Context context){
        AlarmManager am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

       SharedPreferences sharedPrefs = context.getSharedPreferences("Reminders", Context.MODE_PRIVATE);
       Map<String, ?> dates = sharedPrefs.getAll();
       Long dateInMillis;
       String key;

        for (Map.Entry<String,?> e : dates.entrySet()){
            key = e.getKey();
            dateInMillis = (Long) e.getValue();
            am.set(AlarmManager.RTC_WAKEUP, dateInMillis, pi);
        }
    }
}
