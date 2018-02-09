package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;
import java.util.HashMap;

public class BddProgramClass extends BddClass {

    /*
    *  TABLES
    *
    * */
    private static final String TABLE_NAME = "program";

    /*
    *  CHAMPS
    *
    * */
    public static final String ID_PROGRAM = "id_program";
    public static final String NAME = "name";

    /*
    *  CREATION
    *
    * */
    private static final String CREATE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            " id_program        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)            " +
            ");";


    /*
    *  SUPPRESSION
    *
    * */
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    /*
    *  REQUETE
    *
    * */
    // Select tous les programmes d'entrainement
    public static final String querySelectAllPrograms = "SELECT " + ID_PROGRAM  + ", " + NAME + " FROM " + TABLE_NAME;

    // Se
    public static final String querySelectProgramById = "SELECT " + ID_PROGRAM + ", " + NAME + " FROM " + TABLE_NAME + "WHERE " + ID_PROGRAM + " =?";
    /*
    *  CONSTRUCTOR
    *
    * */
    public BddProgramClass(Context pContext) {
        super(pContext);
    }


    /*
    *  Renvoie tous les programmes
    *
    * */
    public ArrayList<Program> getAllPrograms() {

        // Open bdd and cursor
        open();
        Cursor cursor = mDb.rawQuery(querySelectAllPrograms, new String[]{});

        // HashMap Results
        ArrayList<Program> results = new ArrayList<>();

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    results.add(new Program(
                            cursor.getInt(0),
                            cursor.getString(1)
                    ));
                } while (cursor.moveToNext());
            }
        }
        // Close
        cursor.close();
        close();

        // Return results
        return results;
    }


    // Retour en HashMap le programme dont l'ID est specifié
    public Program getProgramsById(String id) {

        // Open bdd
        open();

        // HashMap Results
        Program results = null;

        // Query Select
        Cursor cursor = mDb.rawQuery(querySelectProgramById, new String[]{id});

        // Results
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                results = new Program(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
            }
        }

        // Close cursor and bdd
        cursor.close();
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
