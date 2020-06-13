package darktheme.apollo.darktheme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //implements CompoundButton.OnCheckedChangeListener {
    SharedPreferences sp;
    Switch aSwitch;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Check preference to get status of theme
        sp = getSharedPreferences("darktheme.apollo.darktheme.Theme", MODE_PRIVATE);
        Boolean state = sp.getBoolean("switchKey", false);
        String themeValue = sp.getString("Theme", "Default");
        try {
            switch (themeValue) {
                case "Dark":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case "Light":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error changing theme", Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.switch1);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        String themeText = sp.getString("name", "Default");
        textView.setText(themeText);

        if (state) {
            aSwitch.setChecked(true);
        } else {
            aSwitch.setChecked(false);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnotherActivity.class));
            }
        });
        //onclick listener to save theme state
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("darktheme.apollo.darktheme.Theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Theme", "Dark");
                    editor.putBoolean("switchKey", true);
                    editor.putString("name", "Dark Mode");
                    editor.apply();
                    recreate();

                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("darktheme.apollo.darktheme.Theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Theme", "Light");
                    editor.putString("name", "Light Mode");
                    editor.putBoolean("switchKey", false);
                    editor.apply();
                    recreate();
                }
            }
        });

        //Disable swipe functionality on button

        aSwitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getActionMasked() == MotionEvent.ACTION_MOVE;
            }
        });

    }
}