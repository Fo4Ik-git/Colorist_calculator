package com.fo4ik.colouristcounter;

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
    public static final String KEY_PRICE_OWN = "price_own";



    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                 KEY_ID + " INTEGER PRIMARY KEY," +
                 KEY_NAME + " TEXT UNIQUE NOT NULL," +
                 KEY_PRICE + " DOUBLE NOT NULL," +
                 KEY_PRICE_OWN + " DOUBLE NOT NULL" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

      onCreate(db);
    }

    //TODO Создать метод для записи и изменения данных в табоице, также для обновления и для удоления



}
