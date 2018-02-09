package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.andro.prolapso.shapi.models.Progexo;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;
import java.util.HashMap;

class BddProgexoClass extends BddClass {

    // TABLES
    private static final String TABLE_NAME = "progexo";

    // CHAMPS
    private static final String ID_PROGRAM = "id_program";
    private static final String ID_EXO = "id_exo";
    private static final String TIME = "time";
    private static final String REPETITION = "repetition";
    private static final String SERIE = "serie";
    private static final String WEIGHT = "weight";

    // CREATION
    private static final String CREATE_TABLE_PROGEXO = "CREATE TABLE IF NOT EXISTS progexo(" +
            " id_program    INTEGER             ," +
            " id_exo        INTEGER             ," +
            " time          VARCHAR(10)         ," +
            " repetition    INTEGER             ," +
            " serie         INTEGER             ," +
            " weight        INTEGER             ," +
            " FOREIGN KEY(id_program) REFERENCES program(id_program)," +
            " FOREIGN KEY(id_exo) REFERENCES exercise(id_exo)" +
            ");";


    // SUPPRESSION
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    // REQUETE
    // Select tous les programmes d'entrainement
    private static final String querySelectAllExoProgram = "SELECT " + ID_PROGRAM + ", " + ID_EXO +
            ", " + TIME + ", " + REPETITION + ", " + SERIE +", " + WEIGHT +
            "FROM " + TABLE_NAME + " " +
            "WHERE " + ID_PROGRAM + " = ?";


    // CONSTRUCTOR
    public BddProgexoClass(Context pContext) {
        super(pContext);
    }


    // Renvoie tous les programmes
    public ArrayList<Progexo> getAllExoProgram(String i) {

        // Open bdd and cursor
        open();
        Cursor cursor = mDb.rawQuery(querySelectAllExoProgram, new String[]{i});

        // HashMap Results
        ArrayList<Progexo> results = new ArrayList<>();

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    results.add(new Progexo(
                            cursor.getInt(0),
                            cursor.getInt(1),
                            cursor.getString(2),
                            cursor.getInt(3),
                            cursor.getInt(4),
                            cursor.getString(5)
                    ));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        // Close
        close();

        // Return results
        return results;
    }


    /*
    *  Mise a jour du nom du programme
    *
    *  En parametre HashMap avec :
    *  "id_exo" = Le numero de l'exo a changer
    *  "id_program" = Le numero du programme a changer
    *  Les autres clef = nom de la colonne
    */
    public void updateProgram(HashMap<String, String> params) {
        open();

        ContentValues cv = new ContentValues();
        cv.put(TIME, params.get(TIME));
        cv.put(REPETITION, params.get(REPETITION));
        cv.put(SERIE, params.get(SERIE));
        cv.put(WEIGHT, params.get(WEIGHT));

        mDb.update(TABLE_NAME, cv, "id_exo=? AND id_program=?", new String[]{params.get(ID_EXO), params.get(ID_PROGRAM)});

        close();
    }


    // Ajoute un programme
    public void addProgexo(Progexo p) {

        // Ouvre la bdd
        open();

        // Insert les données dans le Content Values
        ContentValues cv = new ContentValues();
        cv.put(ID_EXO, p.getId_exo());
        cv.put(ID_PROGRAM, p.getId_program());
        cv.put(TIME, p.getTime());
        cv.put(REPETITION, p.getRepetition());
        cv.put(SERIE, p.getSerie());
        cv.put(WEIGHT, p.getWeight());

        // Insert les données dans la table
        mDb.insert(TABLE_NAME, null, cv);

        // Ferme la bdd
        close();
    }
}
