package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ShotsMedication extends AppCompatActivity {
    private int[] ids = new int[]{R.id.distemper_shot_month, R.id.distemper_shot_day, R.id.distemper_shot_year,
            R.id.rabies_shot_month, R.id.rabies_shot_day, R.id.rabies_shot_year, R.id.parvo_shot_month,
            R.id.parvo_shot_day, R.id.parvo_shot_year, R.id.hepatitis_shot_month, R.id.hepatitis_shot_day,
            R.id.hepatitis_shot_year, R.id.other_shot_name, R.id.other_shot_month, R.id.other_shot_day,
            R.id.other_shot_year, R.id.medication_info};

    private String[] keys = new String[]{"distemperShotMonth", "distemperShotDay", "distemperShotYear",
            "rabiesShotMonth", "rabiesShotDay", "rabiesShotYear",
            "parvoShotMonth", "parvoShotDay", "parvoShotYear",
            "hepatitisShotMonth", "hepatitisShotDay", "hepatitisShotYear", "otherShotsName",
            "otherShotMonth", "otherShotDay", "otherShotYear", "medicationInfo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_medication);

        this.loadShotsMedication();
    }


    //called when activity is created
    public void loadShotsMedication() {

        Log.d("message", "entered loadShotsMedication");
        SharedPreferences sharedPref = getSharedPreferences("ShotsMedication", Context.MODE_PRIVATE);
        Log.d("message", sharedPref.getAll().toString());

        //get saved dates from shared preferences
        String value;
        EditText editText;
        for (int i = 0; i < keys.length; i++) {
            value = sharedPref.getString(keys[i], "");
            editText = findViewById(ids[i]);
            editText.setText(value);
        }
    }


    //called when user presses save button
    public void saveShotsMedication(View view) {
        SharedPreferences sharedPrefs = this.getSharedPreferences("ShotsMedication", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        for (int j = 0; j < ids.length; j++) {
            EditText t = (EditText) findViewById(ids[j]);
            String value = t.getText().toString();
            editor.putString(keys[j], value);

        }
        Toast.makeText(this, "Shots & medication saved", Toast.LENGTH_SHORT).show();
        Log.d(getClass().getName(), "Received Toast");
        editor.apply();
    }
}

