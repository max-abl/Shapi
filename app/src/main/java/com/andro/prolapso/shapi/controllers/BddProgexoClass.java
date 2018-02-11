package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.andro.prolapso.shapi.models.ProgExo;

import java.util.ArrayList;

public class BddProgexoClass extends BddClass {

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
            " FROM " + TABLE_NAME +
            " WHERE " + ID_PROGRAM + " = ?";

    // Select un ProgExo
    private static final String querySelectProgExoById = "SELECT " + ID_PROGRAM + ", " + ID_EXO +
            ", " + TIME + ", " + REPETITION + ", " + SERIE +", " + WEIGHT +
            " FROM " + TABLE_NAME + " WHERE " + ID_PROGRAM + " = ? AND " + ID_EXO + " = ?" ;


    private final BddProgramClass mBddProgramClass;
    private final BddExerciseClass mBddExerciseClass;

    // CONSTRUCTOR
    public BddProgexoClass(Context context, BddProgramClass bddProgramClass) {
        super(context);

        mBddProgramClass = bddProgramClass;
        mBddExerciseClass = new BddExerciseClass(context);
    }


    // Renvoie tous les exercices d'un programme
    public ArrayList<ProgExo> getAllExoProgram(int progId) {
        // Open bdd and cursor
        open();
        Cursor cursor = mDb.rawQuery(querySelectAllExoProgram, new String[]{Integer.toString(progId)});

        // HashMap Results
        ArrayList<ProgExo> results = new ArrayList<>();

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    results.add(new ProgExo(
                            progId,
                            mBddExerciseClass.getExerciseById(Integer.toString(cursor.getInt(1))),
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
*  Renvoie le programme dont l'id correspond a celui passé en parametre
*
* */
    public ProgExo getProgExoByIds(String progId, String exoId) {
        // Open bdd
        open();

        // HashMap Results
        ProgExo result = null;

        // Query Select
        Cursor cursor = mDb.rawQuery(querySelectProgExoById, new String[]{progId, exoId});

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                final int idProg = cursor.getInt(0);
                result = new ProgExo(
                        cursor.getInt(0),
                        mBddExerciseClass.getExerciseById(Integer.toString(cursor.getInt(1))),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5));
            }
            cursor.close();
        }

        // Close bdd
        close();

        return result;
    }


    /*
    *  Mise a jour du nom du programme
    *
    *  En parametre HashMap avec :
    *  "id_exo" = Le numero de l'exo a changer
    *  "id_program" = Le numero du programme a changer
    *  Les autres clef = nom de la colonne
    */
    public void updateProgram(ProgExo progExo) {
        open();

        ContentValues cv = new ContentValues();
        cv.put(TIME, progExo.getTime());
        cv.put(REPETITION, progExo.getRepetition());
        cv.put(SERIE, progExo.getSerie());
        cv.put(WEIGHT, progExo.getWeight());

        mDb.update(TABLE_NAME, cv, ID_PROGRAM + "= ? AND " + ID_EXO + " = ?",
                new String[]{Integer.toString(progExo.getProgramId()), Integer.toString(progExo.getExo().getId())});

        close();
    }

    // Ajoute un programme
    public void addProgexo(ProgExo p) {

        // Ouvre la bdd
        open();

        // Insert les données dans le Content Values
        ContentValues cv = new ContentValues();
        cv.put(ID_EXO, p.getExo().getId());
        cv.put(ID_PROGRAM, p.getProgramId());
        cv.put(TIME, p.getTime());
        cv.put(REPETITION, p.getRepetition());
        cv.put(SERIE, p.getSerie());
        cv.put(WEIGHT, p.getWeight());

        // Insert les données dans la table
        mDb.insert(TABLE_NAME, null, cv);

        // Ferme la bdd
        close();
    }

    // Supprime un ProgExo de la base
    public void deleteProgExo(int progId, int exoId) {
        // Ouvre la bdd
        open();

        mDb.delete(TABLE_NAME, ID_PROGRAM + " = ? AND " + ID_EXO + " = ?", new String[]{Integer.toString(progId), Integer.toString(exoId)});

        // Ferme la bdd
        close();
    }

    public BddExerciseClass getBddExerciseClass() {
        return mBddExerciseClass;
    }
}
