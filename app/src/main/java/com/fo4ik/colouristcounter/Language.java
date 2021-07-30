package com.fo4ik.colouristcounter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class Language extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        String[] languages = {"English", "Русский", "Polski"};
        ListView langList = findViewById(R.id.listView);

        Log.d("", String.valueOf(Locale.getDefault()));


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, languages);
        langList.setAdapter(adapter);

        langList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0:{
                        setLocale(Language.this, "US");
                        toSettings();
                    } break;
                    case 1:{
                        setLocale(Language.this, "RU");
                        toSettings();
                    } break;
                    case 2:{
                        setLocale(Language.this, "PL");
                        toSettings();
                    }

                }


            }
        });

    }

    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public void onClick(View view){



        switch (view.getId()){
            case R.id.btnToSettings:{
                toSettings();
            }
        }

    }

    public  void toSettings(){
        Intent intent = new Intent(Language.this, SettingsActivity.class);
        startActivity(intent);
    }
}