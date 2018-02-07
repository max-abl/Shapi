package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BddHandler extends SQLiteOpenHelper {

    // Creation de la base de données
    private static final String createUser = "CREATE TABLE IF NOT EXISTS user(" +
            " id_user        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)            ," +
            " firstname      VARCHAR(40)            ," +
            " weight         INTEGER                ," +
            " height         INTEGER                ," +
            " flag           VARCHAR(2)             " +
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
            " name          VARCHAR(40)," +
            " id_type       INTEGER," +
            " FOREIGN KEY(id_type) REFERENCES type(id_type)" +
            ");";

    private static final String createTableExercice = "CREATE TABLE IF NOT EXISTS exercice(" +
            " id_exo         INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)," +
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


    public BddHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUser);
        db.execSQL(createTableProgramme);
        db.execSQL(createTableType);
        db.execSQL(createTableMuscle);
        db.execSQL(createTableExercice);
        db.execSQL(createTableProgExo);

        if (countUser(db) < 1) {
            initUser(db);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


    // Initialisation d'un user a la premiere connexion
    private void initUser(SQLiteDatabase db) {

        ContentValues vals = new ContentValues();
        vals.put("name", "nom");
        vals.put("firstname", "prenom");
        vals.put("weight", 80);
        vals.put("height", 180);
        vals.put("flag", "F");

        // Insert
        long res = db.insert("user", null, vals);

        // Initialized
        Log.d("-- BDD_CLASS --", "INIT_USER : Initialized: " + res);

    }

    // Comptage du nombre d'user
    private long countUser(SQLiteDatabase db) {
        return DatabaseUtils.queryNumEntries(db, "id_user");
    }

}
