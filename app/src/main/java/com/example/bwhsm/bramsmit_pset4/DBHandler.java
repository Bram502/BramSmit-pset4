package com.example.bwhsm.bramsmit_pset4;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COMPLETED = "completed";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_TASKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_COMPLETED + " INTEGER DEFAULT 0" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    // Add new item to database
    public void addItem(Item item) {
        ContentValues values = new ContentValues();
        if (!item.getTitle().equals(null)) {
            values.put(COLUMN_TITLE, item.getTitle());
            int completed;
            if (!item.getCompleted()) {
                completed = 0;
            } else {
                completed = 1;
            }
            values.put(COLUMN_COMPLETED, completed);
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_TASKS, null, values);
            db.close();
        }
    }

    // Delete item from database
    public void deleteItem(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_TITLE + "=\"" + title + "\";");
    }

    // return database as Array list
    public ArrayList<Item> databaseToArray() {
        ArrayList<Item> dbArray = new ArrayList<Item>();

        SQLiteDatabase db = getReadableDatabase();
        String query = " SELECT * FROM " + TABLE_TASKS + " WHERE 1 ";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            Item newItem = new Item();
            if (c.getString(c.getColumnIndex(COLUMN_TITLE)) != null) {
                newItem.setTitle(c.getString(c.getColumnIndex("title")));
            }

            int completed = c.getInt(c.getColumnIndex(COLUMN_COMPLETED));
            if (completed == 0) {
                newItem.setCompleted(false);
            } else {
                newItem.setCompleted(true);
            }
            dbArray.add(newItem);
            c.moveToNext();
        }
        db.close();
        return dbArray;
    }

    public void clearDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TASKS);
    }
}
