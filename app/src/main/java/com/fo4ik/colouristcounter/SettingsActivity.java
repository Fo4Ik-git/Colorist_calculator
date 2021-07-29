package com.fo4ik.colouristcounter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {


    Button btn_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        btn_theme = findViewById(R.id.btn_theme);


    }




    public void toMain(View view){
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("ShowToast")
    public void onDarkTheme(View view){


        if (getTheme().equals(R.style.Theme_AppCompat_Light_NoActionBar)) {
            btn_theme.setText("Dark");
            this.setTheme(R.style.Theme_AppCompat_NoActionBar);
        } else {
            btn_theme.setText("Light");
            this.setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        }

    }

}