package darktheme.apollo.darktheme;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Toast;


public class AnotherActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("darktheme.apollo.darktheme.Theme",MODE_APPEND);
        String theme = sharedPreferences.getString("Theme","Default");
        try{
            if(theme.equals("Dark")){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else if (theme.equals("Light")){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            else{
                setTheme(R.style.AppTheme);
            }
        }catch(Exception e){
            Toast.makeText(this, "error changing theme", Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_another);
    }
}
