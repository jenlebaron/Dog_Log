package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MedicalHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
    }

    public void saveNote (View view){
        Toast.makeText(this, "pressed button save Note", Toast.LENGTH_SHORT).show();
    }

    public void loadHistory (View view){
        Toast.makeText(this, "pressed button to view notes", Toast.LENGTH_SHORT).show();
    }
}