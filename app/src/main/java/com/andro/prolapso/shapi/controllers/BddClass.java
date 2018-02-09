package com.andro.prolapso.shapi.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

abstract class BddClass {

    private final static int VERSION = 1;
    private final static String NOM = "shapi.db";
    SQLiteDatabase mDb = null;
    private BddHandler mHandler = null;

    BddClass(Context context) {
        this.mHandler = new BddHandler(context, NOM, null, VERSION);
    }

    SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
