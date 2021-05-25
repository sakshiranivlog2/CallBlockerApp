package com.example.mycallblocker.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 4;

    public DbHelper(Context context) {
        super(context, "database", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Number._TABLE + "(" +
                Number.NUMBER + " TEXT NOT NULL," +
                Number.NAME + " TEXT," +
                Number.LAST_CALL + " INTEGER," +
                Number.TIMES_CALLED + " INTEGER NOT NULL DEFAULT 0," +
                Number.ALLOW + " INTEGER," +
                Number.ID + " INTEGER PRIMARY KEY" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int from, int to) {
        db.execSQL("DROP TABLE IF EXISTS " + Number._TABLE);
        onCreate(db);
    }

}