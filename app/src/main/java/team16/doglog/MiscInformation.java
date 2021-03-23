package team16.doglog;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;

public class MiscInformation extends AppCompatActivity {
    private int[] ids = new int[]{R.id.MiscFood, R.id.MiscRoutine, R.id.MiscInfoList};

    private String[] keys = new String[]{"MiscFood", "MiscRoutine", "MiscInfoList"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misc_information);

        this.loadMiscInformation();
    }

    //called when activity is created
    private void loadMiscInformation() {

        Log.d("message", "entered loadMiscInformation");
        SharedPreferences sharedPref = getSharedPreferences("MiscInformation", Context.MODE_PRIVATE);
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
    public void saveMiscInformation(View view) {
        SharedPreferences sharedPrefs = this.getSharedPreferences("MiscInformation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        for (int j = 0; j < ids.length; j++) {
            EditText t = (EditText) findViewById(ids[j]);
            String value = t.getText().toString();
            editor.putString(keys[j], value);

        }
        Toast.makeText(this, "Misc information saved", Toast.LENGTH_SHORT).show();
        Log.d(getClass().getName(), "Received Toast");
        editor.apply();


    }
}