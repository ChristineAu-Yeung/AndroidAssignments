package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "database.db";
    static int VERSION_NUM = 2;
    public final static String KEY_ID = "KEY_ID";
    public final static String KEY_MESSAGE = "KEY_M";
    public final static String TABLE_NAME = "MESSAGES";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.i("ChatDatabaseHelper","Calling onCreate");
    }

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + KEY_ID + " integer primary key autoincrement, "
            + KEY_MESSAGE + " text not null);";
    private static final String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
        onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVerion=" + oldVersion + "newVersion=" +newVersion);
    }
}
