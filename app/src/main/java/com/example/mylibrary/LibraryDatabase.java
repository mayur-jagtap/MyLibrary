package com.example.mylibrary;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LibraryDatabase extends SQLiteOpenHelper {
    private static final String dname="Library";
    public LibraryDatabase(@Nullable Context context) {
        super(context, dname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String querry="create table Library(book_name text,author text,book_id integer primary key)";
        db.execSQL(querry);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop Table if exists LIbrary");
        onCreate(db);

    }
}
