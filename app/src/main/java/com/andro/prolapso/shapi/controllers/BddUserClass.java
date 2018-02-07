package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.HashMap;

public class BddUserClass {
    // Variable mBddClass
    private BddClass mBddClass;

    // Table
    private static final String TABLE = "user";

    // Champs
    public static final String NOM = "nom";
    public static final String PRENOM = "prenom";
    public static final String DATE_NAISSANCE = "date_nais";
    public static final String POIDS = "poids";
    public static final String TAILLE = "taille";
    public static final String FLAG = "flag";
    public static final String DATE_CREATION = "date_creation";


    // Requetes
    private static final String reqGetProfile =
            " SELECT " + NOM + "," + PRENOM + "," + DATE_NAISSANCE + "," + POIDS + "," +
                    TAILLE + "," + FLAG + "," + DATE_CREATION +
                    " FROM " + TABLE +
                    " LIMIT 1 ";

    private static final String reqUpdateProfile = "UPDATE " + TABLE +
            " SET " + NOM + "=?, " +
            PRENOM + "=?, " +
            DATE_NAISSANCE + "=?, " +
            POIDS + "=?, " +
            TAILLE + "=?, " +
            FLAG + "=?, " +
            DATE_CREATION + "=?";


    public BddUserClass() {
        mBddClass = new BddClass();
    }

    public HashMap<String, String> getProfile() {
        HashMap<String, String> results = new HashMap<>();

        Cursor cursor = mBddClass.getdb().rawQuery(reqGetProfile, null);
        cursor.moveToFirst();

        results.put(NOM, cursor.getString(cursor.getColumnIndex(NOM)));
        results.put(PRENOM, cursor.getString(cursor.getColumnIndex(PRENOM)));
        results.put(DATE_NAISSANCE, cursor.getString(cursor.getColumnIndex(DATE_NAISSANCE)));
        results.put(POIDS, cursor.getString(cursor.getColumnIndex(POIDS)));
        results.put(TAILLE, cursor.getString(cursor.getColumnIndex(TAILLE)));
        results.put(FLAG, cursor.getString(cursor.getColumnIndex(FLAG)));
        results.put(DATE_CREATION, cursor.getString(cursor.getColumnIndex(DATE_CREATION)));

        cursor.close();
        return results;
    }

    // Mise a jour des informations personnel
    // En parametre : hashMap clef = nom de la colonne
    public void updateProfile(HashMap<String, String> params) {

        ContentValues cv = new ContentValues();

        cv.put(NOM, params.get(NOM));
        cv.put(PRENOM, params.get(PRENOM));
        cv.put(DATE_NAISSANCE, params.get(DATE_NAISSANCE));
        cv.put(POIDS, params.get(POIDS));
        cv.put(TAILLE, params.get(TAILLE));
        cv.put(FLAG, "O");

        mBddClass.getdb().update(TABLE, cv, "true", null);
    }
}
