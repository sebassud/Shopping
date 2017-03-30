package com.example.raptor.shopping.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.raptor.shopping.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAPTOR on 23.03.2017.
 */

public class ShoppingCrud {
    private SQLiteDatabase database;

    public ShoppingCrud(SQLiteDatabase database) {
        this.database = database;
    }

    private static String[] projection = {
            ShopingDatabase.ShoppingItemEntry._ID,
            ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_TITLE,
            ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_Quality,
            ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_IMAGE
    };

    public List<ShoppingItem> getAllItems() {
        List<ShoppingItem> items = new ArrayList<>();
        Cursor cursor = database.query(
                ShopingDatabase.ShoppingItemEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ShopingDatabase.ShoppingItemEntry._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_TITLE));
            int quality = cursor.getInt(cursor.getColumnIndexOrThrow(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_Quality));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_IMAGE));
            items.add(new ShoppingItem(id, title, quality, image));
        }

        return items;
    }

    public void addNewItem(ShoppingItem item) {
        ContentValues values = new ContentValues();
        values.put(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_TITLE, item.getTitle());
        values.put(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_Quality, item.getQuality());
        values.put(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_IMAGE, item.getIdImage());
        item.setId(database.insert(ShopingDatabase.ShoppingItemEntry.TABLE_NAME, null, values));
    }


    public void updateItem(ShoppingItem item) {
        ContentValues values = new ContentValues();
        values.put(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_TITLE, item.getTitle());
        values.put(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_Quality, item.getQuality());
        values.put(ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_IMAGE, item.getIdImage());

        String selection = ShopingDatabase.ShoppingItemEntry._ID + " LIKE ?";
        String[] selectionArgs = {String.format("%d", item.get_id())};

        database.update(
                ShopingDatabase.ShoppingItemEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }

    public void deleteItem(ShoppingItem item){
        String[] selectionArgs = {item.getTitle()};
        database.delete(ShopingDatabase.ShoppingItemEntry.TABLE_NAME, ShopingDatabase.ShoppingItemEntry.COLUMN_NAME_TITLE + " LIKE ?", selectionArgs);
    }
}
