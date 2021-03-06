package com.fo4ik.colouristcounter.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add database entities
@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    //Create database instance
    private static RoomDB database;

    //Define database name


    public synchronized static RoomDB getInstance(Context context){
        //Check condition
        if(database == null){
            //Initialize database
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, Config.DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    //Create Dao
    public abstract MainDao mainDao();

}
