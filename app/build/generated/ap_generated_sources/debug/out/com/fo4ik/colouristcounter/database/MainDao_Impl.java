package com.fo4ik.colouristcounter.database;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class MainDao_Impl implements MainDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMainData;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfMainData;

  private final SharedSQLiteStatement __preparedStmtOfUpdate;

  public MainDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMainData = new EntityInsertionAdapter<MainData>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `material`(`ID`,`name`,`price`,`own_price`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MainData value) {
        stmt.bindLong(1, value.getID());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindDouble(3, value.getPrice());
        stmt.bindDouble(4, value.getOwn_price());
      }
    };
    this.__deletionAdapterOfMainData = new EntityDeletionOrUpdateAdapter<MainData>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `material` WHERE `ID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MainData value) {
        stmt.bindLong(1, value.getID());
      }
    };
    this.__preparedStmtOfUpdate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE material SET price = ?, own_price = ? WHERE ID = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(MainData mainData) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMainData.insert(mainData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(MainData mainData) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfMainData.handle(mainData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void reset(List<MainData> mainData) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfMainData.handleMultiple(mainData);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(int sID, float sPrice, float sOwn_Price) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdate.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindDouble(_argIndex, sPrice);
      _argIndex = 2;
      _stmt.bindDouble(_argIndex, sOwn_Price);
      _argIndex = 3;
      _stmt.bindLong(_argIndex, sID);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdate.release(_stmt);
    }
  }

  @Override
  public List<MainData> getAll() {
    final String _sql = "SELECT * FROM material";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfID = _cursor.getColumnIndexOrThrow("ID");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
      final int _cursorIndexOfOwnPrice = _cursor.getColumnIndexOrThrow("own_price");
      final List<MainData> _result = new ArrayList<MainData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MainData _item;
        _item = new MainData();
        final int _tmpID;
        _tmpID = _cursor.getInt(_cursorIndexOfID);
        _item.setID(_tmpID);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final float _tmpPrice;
        _tmpPrice = _cursor.getFloat(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final float _tmpOwn_price;
        _tmpOwn_price = _cursor.getFloat(_cursorIndexOfOwnPrice);
        _item.setOwn_price(_tmpOwn_price);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
