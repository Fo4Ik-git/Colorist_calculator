package com.fo4ik.colouristcounter.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface MainDao {

    String TN = Config.TABLE_NAME;

    //Insert query
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    //Delete query
    @Delete
    void delete(MainData mainData);

    //Delete all query
    @Delete
    void reset(List<MainData> mainData);

    //Update query
    @Query("UPDATE " + Config.TABLE_NAME + " SET price = :sPrice, own_price = :sOwn_Price WHERE ID = :sID")
    void update(int sID, float sPrice, float sOwn_Price);

    //Get all data query
    @Query("SELECT * FROM " + Config.TABLE_NAME)
    List<MainData> getAll();
}
