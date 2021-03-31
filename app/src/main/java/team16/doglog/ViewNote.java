package team16.doglog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class ViewNote extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        final String message = intent.getStringExtra(MedicalHistory.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        final TextView textView = findViewById(R.id.Heading);
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



        Button share = (Button) findViewById(R.id.btnTextShare);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getText = message;
                if (!getText.equals("") && getText.length() != 0)

                    shareText(getText);
                else
                    Toast.makeText(ViewNote.this,
                            "Please enter something to share.", Toast.LENGTH_SHORT)
                            .show();
            }
        });
    }







    private void shareText(String text) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");// Plain format text
        // You can add subject also
        /*
         * sharingIntent.putExtra( android.content.Intent.EXTRA_SUBJECT,
         * "Subject Here");
         */
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(sharingIntent, "Share Text Using"));
    }

    public void deleteNote(String text){
        SharedPreferences sharedPrefs = this.getSharedPreferences("medicalHistoryNotes", Context.MODE_PRIVATE);
        sharedPrefs.edit().remove(text).apply();
   }








}