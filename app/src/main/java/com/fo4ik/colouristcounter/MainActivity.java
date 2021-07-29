package com.fo4ik.colouristcounter;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText consumables, surface, lakme, powder, oxident, time, assets, foam_bath;
    Button result, add_content, clear;
    TextView text_res;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.btn_result);



        dbHelper = new DBHelper(this);
    }

    public void onClean(View view){
        surface = findViewById(R.id.surface);
        consumables = findViewById(R.id.consumables);
        lakme = findViewById(R.id.lakme);
        powder = findViewById(R.id.powder);
        oxident = findViewById(R.id.oxident);
        time = findViewById(R.id.time);
        assets = findViewById(R.id.assets);
        foam_bath = findViewById(R.id.foam_bath);
        text_res = findViewById(R.id.text_res);

        clear = findViewById(R.id.btn_clear);

        surface.setText("");
        consumables.setText("");
        lakme.setText("");
        powder.setText("");
        time.setText("");
        assets.setText("");
        foam_bath.setText("");
    }

    public void onCalculate(View view) {

        surface = findViewById(R.id.surface);
        consumables = findViewById(R.id.consumables);
        lakme = findViewById(R.id.lakme);
        powder = findViewById(R.id.powder);
        oxident = findViewById(R.id.oxident);
        time = findViewById(R.id.time);
        assets = findViewById(R.id.assets);
        foam_bath = findViewById(R.id.foam_bath);
        text_res = findViewById(R.id.text_res);
        clear = findViewById(R.id.btn_clear);



       Calculate(consumables, surface, lakme, powder, oxident, time, assets, text_res, foam_bath);
    }

    public void add_cat(View view) {
        Toast.makeText(this, "Временно не работает", Toast.LENGTH_SHORT).show();
    }

    public void toSettings(View view){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }


    public void Calculate(EditText consumables, EditText surface, EditText lakme, EditText powder, EditText oxident, EditText time, EditText assets, TextView text_res, EditText foam_bath) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();


        float material = 0;
        float own_count = 0;
        float profit = 0;
        float work = 0;
        float asset = 0;
        float service_cost = 0;

        try {
            if (!surface.getText().toString().equals("")) {
                material = material + (Float.parseFloat(surface.getText().toString()) * 15);
                own_count = own_count + (Float.parseFloat((surface.getText().toString())) * 5);
            }
            if (!lakme.getText().toString().equals("")) {
                material = material + (Float.parseFloat(lakme.getText().toString()) * 10);
                own_count = own_count + (Float.parseFloat((lakme.getText().toString())) * 3);
            }
            if (!powder.getText().toString().equals("")) {
                material = material + (Float.parseFloat(powder.getText().toString()) * 5);
                own_count = (float) (own_count + (Float.parseFloat((powder.getText().toString())) * 1.5));
            }
            if (!oxident.getText().toString().equals("")) {
                material = material + (Float.parseFloat(oxident.getText().toString()) * 1);
                own_count = (float) (own_count + (Float.parseFloat((oxident.getText().toString())) * 0.4));
            }
            if (!assets.getText().toString().equals("")) {
                material = material + (Float.parseFloat(assets.getText().toString()) * 250);
                own_count = (float) (own_count + (Float.parseFloat((assets.getText().toString())) * 50));
                asset = (Float.parseFloat(assets.getText().toString()) * 250);
            }
            if (!foam_bath.getText().toString().equals("")) {
                material = material + (Float.parseFloat(foam_bath.getText().toString()) * 150);
                own_count = (float) (own_count + (Float.parseFloat((foam_bath.getText().toString())) * 0.4));
            }
            if (!time.getText().toString().equals("")) {
                work = Float.parseFloat(time.getText().toString()) * 600;
            }
            if(!consumables.getText().toString().equals("")){
               material = material + (Float.parseFloat(consumables.getText().toString()));
            }
            profit = material - own_count;

            service_cost = material + work;

            text_res.setText("Стоимость услуги: " + service_cost + "\n" +
                    "Прибыль: "+ profit + "\n" +
                    "Активы: "+ asset + "\n" +
                    "Себе стоимость: " + own_count + "\n" +
                    "Материалы: "+ material + "\n" +
                    "Время: "+ work + "\n" +
                    "");

        } catch (Exception e) {
            text_res.setText("Error try again");
        }
    }




}