package com.andro.prolapso.shapi.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.andro.prolapso.shapi.models.ProgExo;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class BddProgramClass extends BddClass {

    // TABLES
    private static final String TABLE_NAME = "program";

    // CHAMPS
    private static final String ID_PROGRAM = "id_program";
    private static final String NAME = "name";

    //  CREATION
    private static final String CREATE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            " id_program        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " name           VARCHAR(40)            " +
            ");";


    // SUPPRESSION
    public static final String TABLE_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";


    // REQUETE
    // Select tous les programmes d'entrainement
    private static final String querySelectAllPrograms = "SELECT " + ID_PROGRAM + ", " + NAME + " FROM " + TABLE_NAME;

    // Select un programme via l'ID
    private static final String querySelectProgramById = "SELECT " + ID_PROGRAM + ", " + NAME + " FROM " + TABLE_NAME + " WHERE " + ID_PROGRAM + " = ?";

    private BddProgexoClass bddProgexoClass;

    // CONSTRUCTOR
    public BddProgramClass(Context context) {
        super(context);
        bddProgexoClass = new BddProgexoClass(context, this);
    }

    // Renvoie tous les programmes
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
                    final int idProg = cursor.getInt(0);
                    results.add(new Program(idProg,
                            cursor.getString(1),
                            bddProgexoClass.getAllExoProgram(idProg)));
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
                final int idProg = cursor.getInt(0);
                results = new Program(idProg,
                        cursor.getString(1),
                        bddProgexoClass.getAllExoProgram(idProg));
            }
            cursor.close();
        }

        // Close bdd
        close();

        return results;
    }

    /*
    *  Mise a jour du nom du programme
    *
    *  En parametre HashMap avec :
    *  "ID" = Le numero du programme a changer
    *  Les autres clef = nom de la colonne
    */
    public void updateProgram(String idProg, String newName) {
        open();

        ContentValues cv = new ContentValues();
        cv.put(NAME, newName);

        mDb.update(TABLE_NAME, cv, "id_program = ?", new String[]{idProg});

        close();
    }


    // Ajoute un programme
    public Program addProgram(String nom) {
        // Ouvre la bdd
        open();

        // Insert les données dans le Content Values
        ContentValues cv = new ContentValues();
        cv.put(NAME, nom);

        // Insert les données dans la table
        long newId = mDb.insert(TABLE_NAME, null, cv);

        // Ferme la bdd
        close();

        return new Program((int) newId, nom, new ArrayList<ProgExo>());
    }

    // Supprime un programme
    public void deleteProgram(int id) {
        // Ouvre la bdd
        open();

        mDb.delete(TABLE_NAME, ID_PROGRAM +"=?", new String[]{Integer.toString(id)});


        // Ferme la bdd
        close();
    }
}
