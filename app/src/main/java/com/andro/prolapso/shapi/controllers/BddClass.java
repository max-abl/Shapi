package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BddClass {

    // La base de données
    private SQLiteDatabase db;

    // Nom de la base
    private static final String DB_NAME = "SHAPI";

    // Creation de la base de données
    private static final String requeteCreation = "CREATE TABLE IF NOT EXISTS user" +
            " id_user        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " nom            VARCHAR(40)            ," +
            " prenom         VARCHAR(40)            ," +
            " date_nais      DATE                   ," +
            " poids          INTEGER                ," +
            " taille         VARCHAR(40)            ," +
            " date_creation  DATE                   )";

    // Creation de la base de données
    private static final String requeteSuppressionCreation = "CREATE OR REPLACE user" +
            " id_user        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " nom            VARCHAR(40)            ," +
            " prenom         VARCHAR(40)            ," +
            " date_nais      DATE                   ," +
            " poids          INTEGER                ," +
            " taille         VARCHAR(40)            ," +
            " flag           VARCHAR(2)             ," +
            " date_creation  DATE                   )";

    // Recherche de l'utilisateur
    private static final String requeteGetUser = "SELECT id_user FROM user LIMIT 1";


    // -- CONSTRUCTOR --
    public BddClass() {

        // Ouverture de la base
        db = SQLiteDatabase.openOrCreateDatabase(DB_NAME, null);

        // On cree la base si elle n'existe pas
        //db.execSQL(requeteCreation);

        // Suppression creation => Mode developpement uniquement
        db.execSQL(requeteSuppressionCreation);

        // Premiere connexion a l'application
        if (countUser() < 1) {
            initUser();
        }

        // Log initialisation de la base
        Log.d("-- BDD_CLASS --", "CONSTRUCTOTR : Initialized");
    }

    // Initialisation d'un user a la premiere connexion
    private void initUser() {

        // Date now
        Date date_now = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(date_now);

        ContentValues vals = new ContentValues();
        vals.put("nom", "nom");
        vals.put("prenom", "prenom");
        vals.put("date_nais", "date de naissance");
        vals.put("poids", 80);
        vals.put("taille", 180);
        vals.put("flag", "F");
        vals.put("date_creation", date);

        // Insert
        long res = db.insert("user", null, vals);

        Log.d("-- BDD_CLASS --", "INIT_USER : Initialized: " + res);

    }

    // Comptage du nombre d'user
    private long countUser() {
        return DatabaseUtils.queryNumEntries(db, "id_user");
    }

}
