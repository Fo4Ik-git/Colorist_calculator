package com.fo4ik.colouristcounter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class DBManager {

    private Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DBManager(Context context) {
        this.context = context;

        dbHelper = new DBHelper(context);
    }

    public void openDB(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeDB(){
        dbHelper.close();
    }

    public void putToDB(String column_name, String value){
        ContentValues values = new ContentValues();
        values.put(column_name, value);

        db.insert(DBContent.TABLE_NAME, null, values);
    }

    public List<String> getFromDB(String columt_name){
        List<String> temp = new ArrayList<>();
        Cursor cursor = db.query(DBContent.TABLE_NAME, null, null,
                null, null, null, null);

        while (cursor.moveToNext()){
            temp.add(cursor.getString(cursor.getColumnIndex(columt_name)));
        }
        cursor.close();

        return temp;
    }

}
