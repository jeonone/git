package com.hdin.jdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "person.db";
    private static final int DATABASE_VERSION =1;

    public static final String TABLE_NAME = "person";
    public static final String PERSON_ID = "_id";
    public static final String PERSON_NAME = "jname";
    public static final String PERSON_MEMO = "memo";
    public static final String PERSON_DATETIME = "dateTime";
    public static final String PERSON_MOBILE = "mobile";
    public static final String[] ALL_COLUMNS = {PERSON_ID, PERSON_NAME, PERSON_MEMO,PERSON_DATETIME ,PERSON_MOBILE};

    private static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("+PERSON_ID+" integer primary Key autoincrement,"+
            PERSON_NAME+" text,"+PERSON_MEMO+" text,"+PERSON_DATETIME+" integer,"+PERSON_MOBILE+" text)";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}