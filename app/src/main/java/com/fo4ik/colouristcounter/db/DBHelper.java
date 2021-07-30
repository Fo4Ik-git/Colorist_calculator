package com.fo4ik.colouristcounter.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {




    public DBHelper(@Nullable Context context) {
        super(context, DBContent.DATABASE_NAME, null, DBContent.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + DBContent.TABLE_NAME + "(" +
                DBContent._ID + " INTEGER PRIMARY KEY," +
                DBContent.NAME + " TEXT UNIQUE NOT NULL," +
                DBContent.PRICE + " TEXT NOT NULL" +
                //DBContent.OWN_PRICE + " TEXT NOT NULL," +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DBContent.TABLE_NAME);

        onCreate(db);
    }

    //TODO Создать метод для записи и изменения данных в табоице, также для обновления и для удоления. Разобраться с DATABASE




}
