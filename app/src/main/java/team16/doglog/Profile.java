package team16.doglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    private int[] ids = new int[]{R.id.textView, R.id.textView3, R.id.textView4, R.id.textView5,
    R.id.textView6, R.id.textView7, R.id.textView8};

    String[] keys = new String[]{"name", "breed", "spayNeuter", "birthDate",
            "licenseNum", "chipInfo", "vet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       loadProfile();
    }

    //called when activity is created
    //fills in previously saved profile data
    public void loadProfile(){
        SharedPreferences sharedPrefs = this.getSharedPreferences("profile", Context.MODE_PRIVATE);

        for(int i = 0; i < ids.length; i++) {
            EditText t = (EditText) findViewById(ids[i]);
            Log.d("profile", "value is " + sharedPrefs.getString(keys[i], ""));
            t.setText(sharedPrefs.getString(keys[i], ""));
        }
    }

    public void saveProfile(View view){
        SharedPreferences sharedPrefs = this.getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        for(int i = 0; i < ids.length; i++) {
            EditText t = (EditText) findViewById(ids[i]);
            Log.d("profile", "added " + t.getText().toString());
            editor.putString(keys[i], t.getText().toString());
        }

        editor.apply();
        Toast.makeText(this, "Profile Saved", Toast.LENGTH_SHORT).show();
        Log.d("profile", sharedPrefs.getAll().toString());

    }
}