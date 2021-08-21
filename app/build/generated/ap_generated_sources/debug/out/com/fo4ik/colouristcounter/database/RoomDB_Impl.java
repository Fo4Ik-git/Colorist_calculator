package com.fo4ik.colouristcounter.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class RoomDB_Impl extends RoomDB {
  private volatile MainDao _mainDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `material` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `price` REAL NOT NULL, `own_price` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"a1a1fbca65e27fedc55442a3d9b81390\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `material`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsMaterial = new HashMap<String, TableInfo.Column>(4);
        _columnsMaterial.put("ID", new TableInfo.Column("ID", "INTEGER", true, 1));
        _columnsMaterial.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsMaterial.put("price", new TableInfo.Column("price", "REAL", true, 0));
        _columnsMaterial.put("own_price", new TableInfo.Column("own_price", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMaterial = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMaterial = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMaterial = new TableInfo("material", _columnsMaterial, _foreignKeysMaterial, _indicesMaterial);
        final TableInfo _existingMaterial = TableInfo.read(_db, "material");
        if (! _infoMaterial.equals(_existingMaterial)) {
          throw new IllegalStateException("Migration didn't properly handle material(com.fo4ik.colouristcounter.database.MainData).\n"
                  + " Expected:\n" + _infoMaterial + "\n"
                  + " Found:\n" + _existingMaterial);
        }
      }
    }, "a1a1fbca65e27fedc55442a3d9b81390", "0958e5f9bbac3ed19589ad3b2e4f82e8");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "material");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `material`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public MainDao mainDao() {
    if (_mainDao != null) {
      return _mainDao;
    } else {
      synchronized(this) {
        if(_mainDao == null) {
          _mainDao = new MainDao_Impl(this);
        }
        return _mainDao;
      }
    }
  }
}
