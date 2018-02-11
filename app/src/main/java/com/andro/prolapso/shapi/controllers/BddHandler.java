package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class BddHandler extends SQLiteOpenHelper {

    // Creation de la base de données
    private static final String createUser = "CREATE TABLE IF NOT EXISTS user(" +
            " id_user        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)            ," +
            " firstname      VARCHAR(40)            ," +
            " weight         INTEGER                ," +
            " height         INTEGER                ," +
            " flag           VARCHAR(2)             " +
            ");";

    private static final String createTableProgramme = "CREATE TABLE IF NOT EXISTS program(" +
            " id_program     INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)" +
            ");";

    private static final String createTableType = "CREATE TABLE IF NOT EXISTS type(" +
            " id_type       INTEGER         ," +
            " name          VARCHAR(40)     " +
            ");";

    private static final String createTableExercise = "CREATE TABLE IF NOT EXISTS exercise(" +
            " id_exo         INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)," +
            " description    VARCHAR(200)," +
            " id_type      INTEGER," +
            " FOREIGN KEY(id_type) REFERENCES type(id_type)" +
            ");";

    private static final String createTableProgExo = "CREATE TABLE IF NOT EXISTS progexo(" +
            " id_program    INTEGER             ," +
            " id_exo        INTEGER             ," +
            " time          VARCHAR(10)         ," +
            " repetition    INTEGER             ," +
            " serie         INTEGER             ," +
            " weight        INTEGER             ," +
            " FOREIGN KEY(id_program) REFERENCES program(id_program)," +
            " FOREIGN KEY(id_exo) REFERENCES exercise(id_exo)" +
            ");";

    private static final String TABLE_DROP_PROGRAM = "DROP TABLE IF EXISTS " + "program" + ";";
    private static final String TABLE_DROP_USER = "DROP TABLE IF EXISTS " + "user" + ";";
    private static final String TABLE_DROP_EXO = "DROP TABLE IF EXISTS " + "exercise" + ";";
    private static final String TABLE_DROP_TYPE = "DROP TABLE IF EXISTS " + "type" + ";";
    private static final String TABLE_DROP_PROGEXO = "DROP TABLE IF EXISTS " + "progexo" + ";";


    public BddHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUser);
        db.execSQL(createTableProgramme);
        db.execSQL(createTableType);
        db.execSQL(createTableExercise);
        db.execSQL(createTableProgExo);

        if (countUser(db) < 1) {
            initUser(db);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP_USER);
        db.execSQL(TABLE_DROP_PROGEXO);
        db.execSQL(TABLE_DROP_EXO);
        db.execSQL(TABLE_DROP_TYPE);
        db.execSQL(TABLE_DROP_PROGRAM);
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

    private void initType(SQLiteDatabase db) {

        ContentValues vals = new ContentValues();
        vals.put("id_type","1");
        vals.put("name","Haut du corps");

        long res = db.insert("type", null, vals);

        vals.put("id_type","2");
        vals.put("name","Tronc");

        res = db.insert("type", null, vals);

        vals.put("id_type","3");
        vals.put("name","Jambes");

        res = db.insert("type", null, vals);

        // Initialized
        Log.d("-- BDD_CLASS --", "INIT_TYPE : Initialized: " + res);
    }

    // Comptage du nombre d'user
    private long countUser(SQLiteDatabase db) {
        return DatabaseUtils.queryNumEntries(db, "user");
    }

}
