package com.andro.prolapso.shapi.controllers;
import com.andro.prolapso.shapi.models.*;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;

public class BddExerciseClass extends BddClass {

    // TABLES
    private static final String EXERCISE_TABLE_NAME = "exercise";
    private static final String TYPE_TABLE_NAME = "type";


    // CHAMPS
    private static final String EXO_ID = "id_exo";
    private static final String EXO_NAME = "name";
    private static final String EXO_ID_TYPE = "id_type";
    private static final String EXO_DESCRIPTION = "description";

    private static final String TYPE_ID = "id_type";
    private static final String TYPE_NAME = "name";


    // REQUETE CREATION
    private static final String CREATE_TABLE_TYPE = "CREATE TABLE IF NOT EXISTS type(" +
            " id_type       INTEGER         NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name          VARCHAR(40)" +
            ");";
    private static final String CREATE_TABLE_EXERCISE = "CREATE TABLE IF NOT EXISTS exercise(" +
            " id_exo         INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)," +
            " description    VARCHAR(200)," +
            " id_type      INTEGER," +
            " FOREIGN KEY(id_type) REFERENCES muscle(id_type)" +
            " FOREIGN KEY(id_type) REFERENCES muscle(id_type)" +
            ");";


    // REQUETE SUPPRESSION
    public static final String TABLE_DROP_EXO = "DROP TABLE IF EXISTS " + EXERCISE_TABLE_NAME + ";";
    public static final String TABLE_DROP_TYPE = "DROP TABLE IF EXISTS " + TYPE_TABLE_NAME + ";";


    // REQUETES
    // Tous les exercices
    private static final String querySelectAllExecises = "SELECT " + EXO_ID + ", " + EXO_DESCRIPTION + ", E." + EXO_NAME  + ", T." + TYPE_NAME + " " +
            "FROM " + EXERCISE_TABLE_NAME + " E, " + TYPE_TABLE_NAME + " T " +
            "WHERE E."+ EXO_ID_TYPE + " = T." + TYPE_ID;

    // Exercice par ID
    private static final String querySelectExeciseById = "SELECT " + EXO_ID + ", " + EXO_DESCRIPTION + ", E." + EXO_NAME + ", T." + TYPE_NAME + " " +
            "FROM " + EXERCISE_TABLE_NAME + " E, " + TYPE_TABLE_NAME + " T " +
            "WHERE " + EXO_ID_TYPE + " = " + TYPE_ID + " " +
            "AND " + EXO_ID + " =?";


    // CONSTRUCTOR
    public BddExerciseClass(Context context) {
        super(context);
    }

    // Fonction donnant tous les exercices en base
    public ArrayList<Exercise> getAllExercises() {

        // Open bdd and cursor
        open();
        Cursor cursor = mDb.rawQuery(querySelectAllExecises, new String[]{});

        // ArrayList results
        ArrayList<Exercise> results = new ArrayList<>();

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    results.add(new Exercise(
                            cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)
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


    // Fonction retournant un exercice spécifique donné par son ID
    public Exercise getExerciseById(String id) {

        // Open bdd and cursor
        open();
        Cursor cursor = mDb.rawQuery(querySelectExeciseById, new String[]{id});

        // HashMap Results
        Exercise result = null;

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = new Exercise(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
            }
            cursor.close();
        }

        // Close bdd
        close();

        // Return results
        return result;
    }
}
