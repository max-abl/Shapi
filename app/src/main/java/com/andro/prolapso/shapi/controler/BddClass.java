/**
 * Created by Maxime on 03/02/2018.
 */

package com.andro.prolapso.shapi.controler;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class BddClass {


    // La base de données
    private SQLiteDatabase db;

    // Nom de la base
    private static final String DBNAME = "SHAPI";

    // Creation de la base de données
    private static final String requeteCreation = "CREATE TABLE IF NOT EXISTS user" +
            " id_user        INTEGER        NOT NULL PRIMARY KEY AUTOINCREMENT," +
            " nom            VARCHAR(40)            ," +
            " prenom         VARCHAR(40)            ," +
            " date_nais      DATE                   ," +
            " poids          INTEGER                ," +
            " taille         VARCHAR(40)            ," +
            " date_creation  DATE                   )";

    // Recherche de l'utilisateur
    private static final String requeteGetUser = "SELECT id_user FROM user LIMIT 1";



    // -- CONTRUSTOR --
    public BddClass() {

        // Ouverture de la base
        db = SQLiteDatabase.openOrCreateDatabase(DBNAME, null);

        // On cree la base si elle n'existe pas
        db.execSQL(requeteCreation);

        // Premiere connexion a l'application
        if(countUser()<1){
            initUser();
        }

        // Log initialisation de la base
        Log.d("-- BDD_CLASS --", "CONSTRUCTOTR : Initialized");
    }

    // Initialisation d'un user a la premiere connexion
    private void initUser(){

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
        vals.put("date_creation", date);

        // Insert
        long res = db.insert("user", null, vals);

        Log.d("-- BDD_CLASS --", "INIT_USER : Initialized: " + res);

    }

    // Comptage du nombre d'user
    private long countUser(){
        return DatabaseUtils.queryNumEntries(db, "id_user");
    }

}
