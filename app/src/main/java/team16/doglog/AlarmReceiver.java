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


public class AlarmReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
       Log.d("message", "entered onReceive");


        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("Reminders",
                    "Reminders Channel", NotificationManager.IMPORTANCE_HIGH);
            Log.d("message", "line 1");
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            Log.d("message", "created channel");
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Reminders")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Test Notification")
                .setContentText("success")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Log.d("message", "created notification");

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(0, builder.build());
        Log.d("message", "executed notification");

       /* //method 1
        context.runOnUiThread {
                Toast.makeText(context, "Notification made!", Toast.LENGTH_SHORT).show();
        }

        //method 2
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, "Notification made!", Toast.LENGTH_SHORT).show();
        }

        //method 3
        Handler mainHandler = new Handler(context.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Notification made!", Toast.LENGTH_SHORT).show();
            } // This is your code
        };
        mainHandler.post(myRunnable);

        //method 4
    context.getMa
        context.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context, "Notification made!", Toast.LENGTH_SHORT).show();
            }
        });*/


    }

    public void setAlarm(Context context){

        AlarmManager am = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

       SharedPreferences sharedPrefs = context.getSharedPreferences("Reminders", Context.MODE_PRIVATE);
       am.set( AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5*1000, pi);


    }
}
