package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.HashMap;

public class BddUserClass extends BddClass {

    // Table
    private static final String TABLE_NAME = "user";

    // Champs
    public static final String NAME = "name";
    public static final String FIRSTNAME = "firstname";
    public static final String WEIGHT = "weight";
    public static final String HEIGHT = "height";
    public static final String FLAG = "flag";


    // Creation
    private static final String CREATE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            " id_user        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)            ," +
            " firstname      VARCHAR(40)            ," +
            " weight         INTEGER                ," +
            " height         INTEGER                ," +
            " flag           VARCHAR(2)             " +
            ");";


    //Suppresion
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public BddUserClass(Context pContext) {
        super(pContext);
    }


    // Données de l'utilisateur
    public HashMap<String, String> getProfile() {

        open();

        // HashMap Results
        HashMap<String, String> results = new HashMap<>();

        // Query Select
        Cursor cursor = mDb.rawQuery("select " + NAME + ", " + FIRSTNAME + ", " + WEIGHT + ", " +
                HEIGHT + ", " + FLAG + " from " + TABLE_NAME, new String[]{});

        // Cursor
        cursor.moveToFirst();

        // Results
        results.put(NAME, cursor.getString(0));
        results.put(FIRSTNAME, cursor.getString(1));
        results.put(WEIGHT, cursor.getString(2));
        results.put(HEIGHT, cursor.getString(3));
        results.put(FLAG, cursor.getString(4));

        // Close
        cursor.close();

        close();

        // Return results
        return results;
    }


    // Mise a jour des informations personnelles
    // En parametre : hashMap clef = nom de la colonne
    public void updateProfile(HashMap<String, String> params) {

        open();

        ContentValues cv = new ContentValues();
        cv.put(NAME, params.get(NAME));
        cv.put(FIRSTNAME, params.get(FIRSTNAME));
        cv.put(WEIGHT, params.get(WEIGHT));
        cv.put(HEIGHT, params.get(HEIGHT));
        cv.put(FLAG, "O");

        mDb.update(TABLE_NAME, cv, "1=1", null);

        close();
    }
}
