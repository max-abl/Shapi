package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.HashMap;

public class BddProgramClass extends BddClass {

    // Table
    private static final String TABLE_NAME = "program";

    // Champs
    public static final String ID_PROGRAM = "id_program";
    public static final String NAME = "name";

    // Creation
    private static final String CREATE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            " id_program        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)            " +
            ");";


    //Suppresion
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public BddProgramClass(Context pContext) {
        super(pContext);
    }

    // Retour en tableau de HashMap tous les programmes
    public HashMap<String, String>[] getAllPrograms() {

        // Open bdd
        open();

        // HashMap Results
        HashMap<String, String> array[] = new HashMap[500];

        // Query Select
        Cursor cursor = mDb.rawQuery("select " + NAME + ", " + ID_PROGRAM + " from " +
                TABLE_NAME, new String[]{});


        int counter = 0;

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> results = new HashMap<>();
                    results.put(ID_PROGRAM, cursor.getString(1));
                    results.put(NAME, cursor.getString(0));
                    counter++;
                    array[counter] = results;
                } while (cursor.moveToNext());
            }
        }
        // Close
        cursor.close();

        // Clode bdd
        close();

        // Return results
        return array;
    }


    // Retour en HashMap le programme dont l'ID est specifié
    public HashMap<String, String> getProgramsById(String id) {

        // Open bdd
        open();

        // HashMap Results
        HashMap<String, String> results = new HashMap<>();

        // Query Select
        Cursor cursor = mDb.rawQuery("SELECT " + NAME + ", " + ID_PROGRAM + " FROM " +
                TABLE_NAME + " WHERE id_program ='" + id + "'", new String[]{});

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                results.put(ID_PROGRAM, cursor.getString(1));
                results.put(NAME, cursor.getString(0));
            }
        }
        // Close
        cursor.close();

        // Clode bdd
        close();

        // Return results
        return results;
    }


    // Mise a jour du nom du programme
    // En parametre HashMap avec :
    // "ID" = Le numero du programme a changer
    // Les autres clef = nom de la colonne
    public void updateProgram(HashMap<String, String> params) {

        open();

        ContentValues cv = new ContentValues();
        cv.put(NAME, params.get(NAME));

        mDb.update(TABLE_NAME, cv, "id_program=?", new String[]{params.get("ID")});

        close();
    }

    // Ajoute un Programme
    public void addProgram(String nom) {

        // Ouvre la bdd
        open();

        // Insert les données dans le Content Values
        ContentValues cv = new ContentValues();
        cv.put(NAME, nom);

        // Insert les données dans la table
        mDb.insert(TABLE_NAME, null, cv);

        // Ferme la bdd
        close();
    }
}
