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
    private static final String createUser = "CREATE TABLE IF NOT EXISTS user(" +
            " id_user        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)            ," +
            " firstname      VARCHAR(40)            ," +
            " birthday       DATE                   ," +
            " weigth         INTEGER                ," +
            " height         VARCHAR(40)            ," +
            " flag           VARCHAR(2)             ," +
            " creationDate   DATE                    " +
            ");";

    // Creation de la base de données
    private static final String createDeleteUser = "CREATE OR REPLACE user(" +
            " id_user        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)            ," +
            " firstname      VARCHAR(40)            ," +
            " birthday       DATE                   ," +
            " weigth         INTEGER                ," +
            " height         VARCHAR(40)            ," +
            " flag           VARCHAR(2)             ," +
            " creationData   DATE                    " +
            ");";

    private static final String createTableProgramme = "CREATE TABLE IF NOT EXISTS programme(" +
            " id_program     INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)" +
            ");";

    private static final String createTableType = "CREATE TABLE IF NOT EXISTS type(" +
            " id_type       INTEGER         NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name          VARCHAR(40)" +
            ");";

    private static final String createTableMuscle = "CERATE TABLE IF NOT EXISTS muscle(" +
            " id_muscle     INTEGER         NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name          VARCHAR(40)" +
            " id_type       INTEGER," +
            " FOREIGN KEY(id_type) REFERENCES type(id_type)" +
            ");";

    private static final String createTableExercice = "CREATE TABLE IF NOT EXISTS exercice(" +
            " id_exo         INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)" +
            " id_muscle      INTEGER," +
            " FOREIGN KEY(id_muscle) REFERENCES muscle(id_muscle)" +
            ");";

    private static final String createTableProgExo = "CREATE TABLE IF NOT EXISTS prog_exo(" +
            " id_program    INTEGER             ," +
            " id_exo        INTEGER             ," +
            " time          INTEGER             ," +
            " repetition    INTEGER             ," +
            " serie         INTEGER             ," +
            " weight        INTEGER             ," +
            " FOREIGN KEY(id_program) REFERENCES programme(id_program)," +
            " FOREIGN KEY(id_exo) REFERENCES exercice(id_exo)" +
            ");";

    // Recherche de l'utilisateur
    private static final String requeteGetUser = "SELECT id_user FROM user LIMIT 1";


    // -- CONSTRUCTOR --
    public BddClass() {
        // Ouverture de la base
        db = SQLiteDatabase.openOrCreateDatabase(DB_NAME, null);

        dbInit();

        // Log initialisation de la base
        Log.d("-- BDD_CLASS --", "CONSTRUCTOTR : Initialized");
    }

    // Initialize db by creating tables
    private void dbInit() {
        // On cree la base si elle n'existe pas
        //db.execSQL(createUser);

        // Suppression creation => Mode developpement uniquement
        db.execSQL(createDeleteUser);

        db.execSQL(createTableProgramme);
        db.execSQL(createTableType);
        db.execSQL(createTableMuscle);
        db.execSQL(createTableExercice);
        db.execSQL(createTableProgExo);

        // Premiere connexion a l'application
        if (countUser() < 1) {
            initUser();
        }
    }

    // Close the database
    public void close() {
        db.close();
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
