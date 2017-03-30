package com.example.raptor.shopping.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RAPTOR on 23.03.2017.
 */

public class ShoppingDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Shopping.db";

    private static final String SQL_CREATE_ITEMS =
            "CREATE TABLE " + ShopingDatabase.ShoppingItemEntry.TABLE_NAME + " (" +
                    ShopingDatabase.ShoppingItemEntry._ID + " INTEGER PRIMARY KEY," +
                    ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_TITLE + " TEXT," +
                    ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_Quality + " INTEGER," +
                    ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_IMAGE + " INTEGER" +
                    " )";

    private static final String SQL_DELETE_ITEMS =
            "DROP TABLE IF EXISTS " + ShopingDatabase.ShoppingItemEntry.TABLE_NAME;

    public ShoppingDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ITEMS);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ITEMS);
        onCreate(db);
    }
}
