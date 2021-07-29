package com.fo4ik.colouristcounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "db";
    public static final String TABLE_NAME = "materials";


    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PRICE = "price";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT UNIQUE NOT NULL," +
                KEY_PRICE + " TEXT NOT NULL" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    //TODO Создать метод для записи и изменения данных в табоице, также для обновления и для удоления. Разобраться с DATABASE

    public void getValues(SQLiteDatabase db, String DATABASE_NAME, String values) { // Для получения колоны из БД


    }


    public void putValues(SQLiteDatabase db, String DATABASE_NAME, String KEY_NAME, String KEY_PRICE){ // Для ввода данныйх в 1 строку в БД
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, KEY_PRICE);

        db.insert(DATABASE_NAME, null, contentValues);

    }


}
