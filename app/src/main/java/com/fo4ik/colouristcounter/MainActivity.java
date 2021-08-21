package com.fo4ik.colouristcounter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fo4ik.colouristcounter.database.MainData;
import com.fo4ik.colouristcounter.database.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_calc, btn_clear;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add_content);
        btn_calc = findViewById(R.id.btn_calculate);
        btn_clear = findViewById(R.id.btn_clear);
        recyclerView = findViewById(R.id.list);


        database = RoomDB.getInstance(this);
        dataList = database.mainDao().getAll();

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);


    }

    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_add_content: {
                createAddDialog(MainActivity.this);
            }
            break;

        }

    }

    public void createAddDialog(Activity activity) {
        try {
            Context context = MainActivity.this;
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);


            EditText names = new EditText(activity);
            EditText price = new EditText(activity);
            EditText own_price = new EditText(activity);

            names.setHint("New name");
            price.setHint("New price");
            price.setInputType(0x00002002);
            own_price.setHint("New own price");
            own_price.setInputType(0x00002002);


            String add_content = getResources().getString(R.string.add_content);

            LinearLayout container = new LinearLayout(this); // Создание контейнера для добавления нескольких полей
            container.setOrientation(LinearLayout.VERTICAL);
            container.addView(names);
            container.addView(new TextView(this));
            container.addView(price);
            container.addView(new TextView(this));
            container.addView(own_price);


            builder.setTitle(add_content)
                    .setMessage("")
                    .setView(container)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @SuppressLint("ShowToast")
                        public void onClick(DialogInterface dialog, int id) {
                            try {

                                if (!names.equals("")) {
                                    if (!price.equals("")) {
                                        if (!own_price.equals("")) {
                                            MainData data = new MainData();
                                            data.setName(names.getText().toString().trim());
                                            data.setPrice(Float.parseFloat(price.getText().toString()));
                                            data.setOwn_price(Float.parseFloat(own_price.getText().toString()));

                                            float tmp = data.getPrice();

                                            database.mainDao().insert(data);

                                            dataList.clear();
                                            dataList.addAll(database.mainDao().getAll());
                                            adapter.notifyDataSetChanged();
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                String error = getString(R.string.error_empty_input);
                                Toast.makeText(activity, error, Toast.LENGTH_LONG);
                            }
                        }
                    })
                    .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Toast.makeText(activity,"Нажата кнопка 'Отмена'",Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.create().show();
        } catch (Exception e) {

        }

    }


}