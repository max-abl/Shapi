package com.andro.prolapso.shapi.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Maxime on 07/02/2018.
 */

public class BddClass {

    protected final static int VERSION = 1;
    protected final static String NOM = "shapi.db";
    protected SQLiteDatabase mDb = null;
    protected BddHandler mHandler = null;

    public BddClass(Context pContext) {
        this.mHandler = new BddHandler(pContext, NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
