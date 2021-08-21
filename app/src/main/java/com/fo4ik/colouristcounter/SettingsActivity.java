package com.fo4ik.colouristcounter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {


    Spinner lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


    }


    public void toMain(View view) {
        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btnChooseLang: {
                Intent intent = new Intent(this, Language.class);
                startActivity(intent);

            }
            break;
            case R.id.btnEditContent: {
            }
            break;
        }


    }





    public String[] getMaterials() {

        return null;
    }
}