package com.fo4ik.colouristcounter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
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
                createEditDialog(SettingsActivity.this);
            }
            break;
        }


    }

    public void createEditDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);


        String[] s = {"India ", "Arica", "India ", "Arica", "India ", "Arica",
                "India ", "Arica", "India ", "Arica"};


        Spinner spinner = new Spinner(activity);
        EditText price = new EditText(activity);
        EditText own_price = new EditText(activity);

        price.setHint("New price");
        price.setInputType(0x00002002);
        own_price.setHint("New own price");
        own_price.setInputType(0x00002002);

        final ArrayAdapter<String> adp = new ArrayAdapter<String>(activity,
                android.R.layout.simple_spinner_item, s);
        spinner.setAdapter(adp);


        String edit_content = getResources().getString(R.string.edit_content);

        LinearLayout container = new LinearLayout(this); // Создание контейнера для добавления нескольких полей
        container.setOrientation(LinearLayout.VERTICAL);
        container.addView(spinner);
        container.addView(new TextView(this));
        container.addView(price);
        //container.addView(own_price);


        builder.setTitle(edit_content)
                .setMessage("")
                .setView(container)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(activity, "Временно не работает", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(activity,"Нажата кнопка 'Отмена'",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.create().show();
    }



    public String[] getMaterials() {

        return null;
    }
}