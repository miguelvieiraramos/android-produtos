package br.unisul.products.infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {


    private static final String name = "product.db";
    private static final int version = 1;

    public SQLiteHelper(Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE product (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR (100)," +
                "quantity INTEGER," +
                "value REAL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
