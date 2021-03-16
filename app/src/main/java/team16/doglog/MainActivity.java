package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when user selects profile from main menu
    public void selectProfile(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);

    }

    //called when user selects medical history from main menu
    public void selectMedicalHistory(View view){
        Intent intent = new Intent(this, MedicalHistory.class);
        startActivity(intent);

    }

    //called when user selects shots and medication from main menu
    public void selectShotsMedication(View view){
        Intent intent = new Intent(this, ShotsMedication.class);
        startActivity(intent);

    }

    //called when user selects Reminders from main menu
    public void selectReminders(View view){
        Intent intent = new Intent(this, Reminders.class);
        startActivity(intent);

    }
    //called when user selects Misc Info from main menu
    public void selectMiscInfo(View view){
        Intent intent = new Intent (this, MiscInfo.class);
        startActivity(intent);
    }
}