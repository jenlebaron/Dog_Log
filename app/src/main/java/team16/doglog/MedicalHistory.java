package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        Toast.makeText(this, "pressed button to view notes", Toast.LENGTH_SHORT).show();
    }
}