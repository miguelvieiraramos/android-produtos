package br.unisul.products.infra;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.unisul.products.domain.Product;
import br.unisul.products.domain.ProductRepository;

public class SQLiteProductRepository implements ProductRepository {

    private SQLiteHelper sqliteHelper;

    public SQLiteProductRepository(Context context) {
        this.sqliteHelper = new SQLiteHelper(context);
    }

    public void save(Product product) {
        SQLiteDatabase database = null;
        try {
            database = this.sqliteHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", product.getName());
            values.put("quantity", product.getQuantity());
            values.put("value", product.getValue());
            database.insert("product", null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    public void update(Product product) {
        SQLiteDatabase database = null;
        try {
            database = this.sqliteHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", product.getName());
            values.put("quantity", product.getQuantity());
            values.put("value", product.getValue());
            database.update("product", values, "id = ?", new String[]{String.valueOf(product.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    public List<Product> findAll() {
        SQLiteDatabase database = null;
        List<Product> products = new ArrayList<>();
        try {
            database = this.sqliteHelper.getWritableDatabase();
            Cursor cursor = database.query("product", new String[]{"id", "name", "quantity", "value"},
                    null, null, null, null, null);
            while (cursor.moveToNext()) {
                Product product = new Product(cursor.getString(1), cursor.getInt(2), cursor.getDouble(3));
                product.setId(cursor.getInt(0));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return products;
    }

    public List<Product> findProductsByName(String name) {
        SQLiteDatabase database = null;
        List<Product> products = new ArrayList<>();
        try {
            database = this.sqliteHelper.getWritableDatabase();
            Cursor cursor = database.query("product", new String[]{"id", "name", "quantity", "value"},
                    "name LIKE ?", new String[]{"%"+name+"%"}, null, null, null);
            while (cursor.moveToNext()) {
                Product product = new Product(cursor.getString(1), cursor.getInt(2), cursor.getDouble(3));
                product.setId(cursor.getInt(0));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return products;
    }

    public void delete(int id) {
        SQLiteDatabase database = null;
        try {
            database = this.sqliteHelper.getWritableDatabase();
            database.execSQL("DELETE FROM product WHERE id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }

    }


    public Product findById(int id) {
        SQLiteDatabase database = null;
        Product product = null;
        try {
            database = this.sqliteHelper.getWritableDatabase();
            Cursor cursor = database.rawQuery("SELECT * FROM product WHERE id = ?", new String[]{String.valueOf(id)});
            cursor.moveToFirst();
            product = new Product(cursor.getString(1), cursor.getInt(2), cursor.getDouble(3));
            product.setId(cursor.getInt(0));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return product;
    }
}
