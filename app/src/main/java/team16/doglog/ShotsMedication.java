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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_medication);

        this.loadShotsMedication();
    }

    private int[] ids = new int[]{R.id.distemper_shot_month, R.id.distemper_shot_day, R.id.distemper_shot_year,
            R.id.rabies_shot_month, R.id.rabies_shot_day, R.id.rabies_shot_year, R.id.parvo_shot_month,
            R.id.parvo_shot_day, R.id.parvo_shot_year, R.id.hepatitis_shot_month, R.id.hepatitis_shot_day,
            R.id.hepatitis_shot_year, R.id.other_shot_name, R.id.other_shot_month, R.id.other_shot_day,
            R.id.other_shot_year, R.id.medication_info
};
 private String[]keys=new String[]{"distemperShotMonth","distemperShotDay","distemperShotYear",
         "rabiesShotMonth","rabiesShotDay","rabiesShotYear",
         "parvoShotMonth","parvoShotDay","parvoShotYear",
         "hepatitisShotMonth","hepatitisShotDay","hepatitisShotYear","otherShotsName",
         "otherShotMonth","otherShotDay","otherShotYear","medicationInfo"};


//called when activity is created
public void loadShotsMedication(){
        String[]keys=new String[]{"distemperShotMonth","distemperShotDay","distemperShotYear",
        "rabiesShotMonth","rabiesShotDay","rabiesShotYear",
        "parvoShotMonth","parvoShotDay","parvoShotYear",
        "hepatitisShotMonth","hepatitisShotDay","hepatitisShotYear","otherShotsName",
        "otherShotMonth","otherShotDay","otherShotYear","medicationInfo"};

        Log.d("message","entered loadShotsMedication");
        SharedPreferences sharedPref=getSharedPreferences("ShotsMedication",Context.MODE_PRIVATE);
        Log.d("message",sharedPref.getAll().toString());

        //get saved dates from shared preferences
        String value;
        EditText editText;
        for(int i = 0; i < keys.length; i++){
        value = sharedPref.getString(keys[i], "");
        editText = findViewById(ids[i]);
        editText.setText(value);
        }

        }



//called when user presses save button
public void saveShotsMedication(View view){
        String[]keys=new String[]{"distemperShot","rabiesShot","parvoShot","hepatitisShot", "otherShots"};

        Map<String, Long> reminderDates=new HashMap<>();
        Long dateInMillis;
        SharedPreferences sharedPrefs=this.getSharedPreferences("ShotsMedication",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPrefs.edit();


        }

        }






//

//    // load from saved preferences
//        SharedPreferences sp = getSharedPreferences(
//                "ShotsMedication.txt", Context.MODE_PRIVATE);
//        SharedPreferences.Editor myEdit = sp.edit();
//        Toast.makeText(this, "Load Shots", Toast.LENGTH_SHORT).show();
//        myEdit.putString("DISTEMPER_MONTH", distemperMonth);
//        myEdit.putString("DISTEMPER_DAY", distemperDay);
//        myEdit.putString("DISTEMPER_YEAR", distemperYear);
//
//        myEdit.putString("RABIES_MONTH", rabiesMonth);
//        myEdit.putString("RABIES_DAY", rabiesDay);
//        myEdit.putString("RABIES_YEAR", rabiesYear);
//
//        myEdit.putString("PARVO_MONTH", parvoMonth);
//        myEdit.putString("PARVO_DAY", parvoDay);
//        myEdit.putString("PARVO_YEAR", parvoYear);
//
//        myEdit.putString("HEPATITIS_MONTH", hepatitisMonth);
//        myEdit.putString("HEPATITIS_DAY", hepatitisDay);
//        myEdit.putString("HEPATITIS_YEAR", hepatitisYear);
//
//        myEdit.putString("OTHER_SHOTS_NAME", otherShotsName);
//        myEdit.putString("OTHER_SHOTS_MONTH", otherShotsMonth);
//        myEdit.putString("OTHER_SHOTS_DAY", otherShotsDay);
//        myEdit.putString("OTHER_SHOTS_YEAR", otherShotsYear);
//
//        myEdit.putString("MEDICATION", medication);
//
//        distemperMonthId.setText(distemperMonth);
//
//}
//
//
//
//
////        //pulling strings out of objects
////        String distemperMonth =  distemperMonthId.getText().toString();
////        String distemperDay = distemperDayId.getText().toString();
////        String distemperYear = distemperYearId.getText().toString();
////
////        String rabiesMonth = rabiesMonthId.getText().toString();
////        String rabiesDay = rabiesDayId.getText().toString();
////        String rabiesYear = rabiesYearId.getText().toString();
////
////        String parvoMonth = parvoMonthId.getText().toString();
////        String parvoDay = parvoDayId.getText().toString();
////        String parvoYear = parvoYearId.getText().toString();
////
////        String hepatitisMonth = hepatitisMonthId.getText().toString();
////        String hepatitisDay = hepatitisDayId.getText().toString();
////        String hepatitisYear = hepatitisYearId.getText().toString();
////
////        String otherShotsName = otherShotsNameId.getText().toString();
////        String otherShotsMonth = otherShotsMonthId.getText().toString();
////        String otherShotsDay = otherShotsDayId.getText().toString();
////        String otherShotsYear = otherShotsYearId.getText().toString();
////
////        String medication = medicationId.getText().toString();
////
////
////        SharedPreferences sharedPref = getSharedPreferences(
////                "ShotsMedication.txt", Context.MODE_PRIVATE);
////        SharedPreferences.Editor editor = sharedPref.edit();
////
////        editor.putString("DISTEMPER_MONTH", distemperMonth);
////        editor.putString("DISTEMPER_DAY", distemperDay);
////        editor.putString("DISTEMPER_YEAR", distemperYear);
////
////        editor.putString("RABIES_MONTH", rabiesMonth);
////        editor.putString("RABIES_DAY", rabiesDay);
////        editor.putString("RABIES_YEAR", rabiesYear);
////
////        editor.putString("PARVO_MONTH", parvoMonth);
////        editor.putString("PARVO_DAY", parvoDay);
////        editor.putString("PARVO_YEAR", parvoYear);
////
////        editor.putString("HEPATITIS_MONTH", hepatitisMonth);
////        editor.putString("HEPATITIS_DAY", hepatitisDay);
////        editor.putString("HEPATITIS_YEAR", hepatitisYear);
////
////        editor.putString("OTHER_SHOTS_NAME", otherShotsName);
////        editor.putString("OTHER_SHOTS_MONTH", otherShotsMonth);
////        editor.putString("OTHER_SHOTS_DAY", otherShotsDay);
////        editor.putString("OTHER_SHOTS_YEAR", otherShotsYear);
////
////        editor.putString("MEDICATION", medication);
////        editor.apply();
//
////        Toast.makeText(this, "Shots & medication saved", Toast.LENGTH_SHORT).show();
////        Log.d(getClass().getName(),"Received Toast");
////

    //public EditText distemperMonthId;
//    public EditText distemperDayId;
//    public EditText distemperYearId;
//    public EditText rabiesMonthId;
//    public EditText rabiesDayId;
//    public EditText rabiesYearId;
//    public EditText parvoMonthId;
//    public EditText parvoDayId;
//    public EditText parvoYearId;
//    public EditText hepatitisMonthId;
//    public EditText hepatitisDayId;
//    public EditText hepatitisYearId;
//    public EditText otherShotsNameId;
//    public EditText otherShotsMonthId;
//    public EditText otherShotsDayId;
//    public EditText otherShotsYearId;
//    public EditText medicationId;


//    public String distemperMonth;
//    public String distemperDay;
//    public String distemperYear;
//    public String rabiesMonth;
//    public String rabiesDay;
//    public String rabiesYear;
//    public String parvoMonth;
//    public String parvoDay;
//    public String parvoYear;
//    public String hepatitisMonth;
//    public String hepatitisDay;
//    public String hepatitisYear;
//    public String otherShotsName;
//    public String otherShotsMonth;
//    public String otherShotsDay;
//    public String otherShotsYear;
//    public String medication;


//add distemper shot date to shared preferences
//        distemperMonthId =  (EditText) findViewById(R.id.distemper_shot_month);
//        distemperDayId = (EditText)findViewById(R.id.distemper_shot_day);
//        distemperYearId = (EditText)findViewById(R.id.distemper_shot_year);
//
//        //add rabies shot date to shared preferences
//        rabiesMonthId =  (EditText) findViewById(R.id.rabies_shot_month);
//        rabiesDayId = (EditText)findViewById(R.id.rabies_shot_day);
//        rabiesYearId = (EditText)findViewById(R.id.rabies_shot_year);
//
//        //add parvo shot date to shared preferences
//        parvoMonthId =  (EditText) findViewById(R.id.parvo_shot_month);
//        parvoDayId = (EditText)findViewById(R.id.parvo_shot_day);
//        parvoYearId= (EditText)findViewById(R.id.parvo_shot_year);
//
//        //add hepatitis shot date to shared preferences
//        hepatitisMonthId =  (EditText) findViewById(R.id.hepatitis_shot_month);
//        hepatitisDayId = (EditText)findViewById(R.id.hepatitis_shot_day);
//        hepatitisYearId= (EditText)findViewById(R.id.hepatitis_shot_year);
//
//        //add other shot name and date to shared preferences
//        otherShotsNameId = (EditText)findViewById(R.id.other_shot_name);
//        otherShotsMonthId =  (EditText) findViewById(R.id.other_shot_month);
//        otherShotsDayId = (EditText)findViewById(R.id.other_shot_day);
//        otherShotsYearId = (EditText)findViewById(R.id.other_shot_year);
//
//        //add medicine to shared preferences
//        medicationId = (EditText)findViewById(R.id.medication_info);
//
//        //
////        this.loadShotsMedication();

//    String[] keys = new String[]{"distemperMonth", "distemperDay", "distemperYear",
//                "rabiesMonth", "rabiesDay", "rabiesYear", "parvoMonth", "parvoDay", "parvoYear",
//                "hepatitisMonth", "hepatitisDay", "hepatitisYear", "otherShotsName",
//                "otherShotsMonth", "otherShotsDay", "otherShotsYear", "medication"};
//
//        Log.d("message", "entered loadShotsMedication");
//        SharedPreferences sharedPref = getSharedPreferences("ShotsMedication.txt", Context.MODE_PRIVATE);
//        Log.d("message", sharedPref.getAll().toString());
//
//        //get saved dates from shared preferences
//        String value;
//        EditText editText;
//        for(int i = 0; i < keys.length; i++){
//            value = sharedPref.getString(keys[i], "");
//            editText = findViewById(ids[i]);
//            editText.setText(value);

    //called when user presses save button
//    public void saveShotsMedication(View view) {
//        Presenter presenter = new Presenter();
//
//        //get shot dates and medicine info from user input
//        EditText userInput = (EditText) findViewById(R.id.distemper_shot_month);
//        String distemper_shot_month = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.distemper_shot_day);
//        String distemper_shot_day = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.distemper_shot_year);
//        String distemper_shot_year = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.rabies_shot_month);
//        String rabies_shot_month = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.rabies_shot_day);
//        String rabies_shot_day = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.rabies_shot_year);
//        String rabies_shot_year = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.parvo_shot_month);
//        String parvo_shot_month = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.parvo_shot_day);
//        String parvo_shot_day = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.parvo_shot_year);
//        String parvo_shot_year = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.hepatitis_shot_month);
//        String hepatitis_shot_month = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.hepatitis_shot_day);
//        String hepatitis_shot_day = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.hepatitis_shot_year);
//        String hepatitis_shot_year = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.other_shot_name);
//        String other_shot_name = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.other_shot_month);
//        String other_shot_month = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.other_shot_day);
//        String other_shot_day = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.other_shot_year);
//        String other_shot_year = userInput.getText().toString();
//        userInput.setText("");
//        userInput = (EditText) findViewById(R.id.medication_info);
//        String medication_info = userInput.getText().toString();
//        userInput.setText("");
//
//        //presenter will store note in shared preferences
//        presenter.saveShotsMedication(this, distemper_shot_month, distemper_shot_day,
//                distemper_shot_year, rabies_shot_month, rabies_shot_day, rabies_shot_year,
//                parvo_shot_month, parvo_shot_day, parvo_shot_year,
//                hepatitis_shot_month, hepatitis_shot_day, hepatitis_shot_year,
//                other_shot_name, other_shot_month, other_shot_day, other_shot_year,
//                medication_info);
//
//        Toast.makeText(this, "Shots & medication saved", Toast.LENGTH_SHORT).show();
//        Log.d(getClass().getName(), "Received Toast");
//    }
//
//    //called when activity is created
//    public void loadShotsMedication() {
//        SharedPreferences sharedPrefs = getSharedPreferences("ShotsMedication.txt", Context.MODE_PRIVATE);
//        Map<String, ?> shots = sharedPrefs.getAll();
//        List<String> shotsMedicineList = new ArrayList<>();
//
//        //create a list of all of the notes saved in shared preferences
//        for (Map.Entry<String, ?> e : shots.entrySet()) {
//            shotsMedicineList.add(e.getKey() + "\n" + (String) e.getValue());
//        }
//
//        ListView lv = findViewById(R.id.note_history);
//        //set array adapter
//        ArrayAdapter<String> adapter =
//                new ArrayAdapter<String>(this,
//                        android.R.layout.simple_list_item_1,
//                        shotsMedicineList);
//        lv.setAdapter(adapter);
//    }
//}
