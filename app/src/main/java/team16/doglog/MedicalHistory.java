package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.DatePicker;
import android.app.DatePickerDialog;

import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MedicalHistory extends AppCompatActivity {
    public static final String HEADER_MSG = "com.example.notes.MESSAGE";
    public static final String EXTRA_MESSAGE = "com.example.notes.MESSAGE";
    EditText date;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
        loadHistory();
// initiate the date picker and a button
        date = (EditText) findViewById(R.id.date);
        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(MedicalHistory.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Intent intent = getIntent();
        final String message = intent.getStringExtra(MedicalHistory.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        final TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
        final String FILE_NAME = message + ".txt";
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader( new InputStreamReader( fis ) );
            String line;
            String whole = "";
            while ( (line = reader.readLine()) != null ) {
                if(whole == "") {
                    whole = whole + line;
                }else{
                    whole = whole + "\n" + line;
                }
            }
            reader.close();
            TextView textView1 = (TextView) findViewById(R.id.content);
            textView1.setText(whole);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //called when user presses save note button
    public void saveNote (View view){
        Presenter presenter = new Presenter();

        //get dates and notes from user input
        EditText userInput = (EditText) findViewById(R.id.date);
        String date = userInput.getText().toString();
        // userInput.setText("");
        userInput = (EditText) findViewById(R.id.medical_history_notes);
        String notes = userInput.getText().toString();
        // userInput.setText("");

        //presenter will store note in shared preferences
        presenter.saveNote(this, date, notes);

        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        loadHistory();
    }

    //called when user presses view notes button
    public void loadHistory (){
        SharedPreferences sharedPrefs = this.getSharedPreferences("medicalHistoryNotes", Context.MODE_PRIVATE);
        Map<String, ?> notes = sharedPrefs.getAll();
        List<String> noteList = new ArrayList<>();
        //create a list of all of the notes saved in shared preferences
        for (Map.Entry<String,?> e : notes.entrySet()){
            noteList.add(e.getKey() + "\n" + (String) e.getValue());
        }

        ListView lv = findViewById(R.id.note_history);
        //set array adapter
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        noteList);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = lv.getItemAtPosition(position).toString();
                Intent intent = new Intent(getApplicationContext(), ViewNote.class);
                intent.putExtra(EXTRA_MESSAGE, item);
                startActivity(intent);
            }
        });
    }

    public void deleteNotes(View view){
        SharedPreferences sharedPrefs = this.getSharedPreferences("medicalHistoryNotes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        loadHistory();
    }


}