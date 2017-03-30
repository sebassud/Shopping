package com.example.raptor.shopping.database;

import android.provider.BaseColumns;

/**
 * Created by RAPTOR on 23.03.2017.
 */

public final class ShopingDatabase {

    private ShopingDatabase(){}

    public static class ShoppingItemEntry implements BaseColumns {

        public static final String TABLE_NAME = "ShoppingItem";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_Quality = "quality";
        public static final String COLUMN_NAME_IMAGE = "idImage";

    }
}
