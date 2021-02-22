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

    //called when user selects shots from main menu
    public void selectShots(View view){
        Intent intent = new Intent(this, Shots.class);
        startActivity(intent);

    }

    //called when user selects office visits from main menu
    public void selectOfficeVisits(View view){
        Intent intent = new Intent(this, OfficeVisits.class);
        startActivity(intent);

    }

    //called when user selects food from main menu
    public void selectFood(View view){
        Intent intent = new Intent(this, Food.class);
        startActivity(intent);

    }

    //called when user selects medication from main menu
    public void selectMedication(View view){
        Intent intent = new Intent(this, Medication.class);
        startActivity(intent);

    }
}