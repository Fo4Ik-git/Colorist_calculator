package com.fo4ik.colouristcounter.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;



//Define table name
@Entity(tableName = Config.TABLE_NAME)
public class MainData implements Serializable {
    //Create id column
    @PrimaryKey(autoGenerate = true)
    private int ID;

    //Create name column
    @ColumnInfo(name = "name")
    private String name;

    //Create price columns
    @ColumnInfo(name = "price")
    private float price;

    @ColumnInfo(name = "own_price")
    private float own_price;

    //Generate getter and setter


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getOwn_price() {
        return own_price;
    }

    public void setOwn_price(float own_price) {
        this.own_price = own_price;
    }



}
