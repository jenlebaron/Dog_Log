package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

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

public class MedicalHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
    }

    //called when user presses save note button
    public void saveNote (View view){
        Presenter presenter = new Presenter();

        //get dates and notes from user input
        EditText userInput = (EditText) findViewById(R.id.medical_date);
        String date = userInput.getText().toString();
        userInput.setText("");
        userInput = (EditText) findViewById(R.id.medical_history_notes);
        String notes = userInput.getText().toString();
        userInput.setText("");

        //presenter will store note in shared preferences
        presenter.saveNote(this, date, notes);

        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
    }

    //called when user presses view notes button
    public void loadHistory (View view){
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

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {

                //MyClass selItem = (MyClass) myList.getSelectedItem(); //
                String value = (String) lv.getItemAtPosition(position);
                //value =

            }
        }*/
    }

    public void deleteNotes(View view){
        SharedPreferences sharedPrefs = this.getSharedPreferences("medicalHistoryNotes", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.clear();
        editor.commit();
        loadHistory(view);
    }


}