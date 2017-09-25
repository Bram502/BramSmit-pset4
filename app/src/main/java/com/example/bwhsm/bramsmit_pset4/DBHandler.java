package com.example.bwhsm.bramsmit_pset4;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_TASKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP_TABLE_IF_EXISTS " + TABLE_TASKS);
        onCreate(db);
    }

    // Add new item to database
    public void addItem(Item item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, item.getTitle());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    // Delete item from database
    public void deleteItem(String title) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_TITLE + "=\"" + title + "\";");
    }

    // Print out database
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = " SELECT * FROM " + TABLE_TASKS + " WHERE 1 ";

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("title")) != null) {
                dbString += c.getString(c.getColumnIndex("title"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }








}
