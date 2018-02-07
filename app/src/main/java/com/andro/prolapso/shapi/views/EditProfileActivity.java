package com.andro.prolapso.shapi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddUserClass;
import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editName, editFirstName, editHeight, editWeight;
    private BddUserClass dbuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Bdd
        dbuser = new BddUserClass();

        // HashMap
        HashMap<String, String> results;
        results = dbuser.getProfile();

        // Champs
        editName = findViewById(R.id.edit_name);
        editFirstName = findViewById(R.id.edit_firstname);
        editHeight = findViewById(R.id.edit_height);
        editWeight = findViewById(R.id.edit_weight);

        // On pre-remplis les champs
        editName.setText(results.get(BddUserClass.NOM));
        editFirstName.setText(results.get(BddUserClass.PRENOM));
        editHeight.setText(results.get(BddUserClass.TAILLE));
        editWeight.setText(results.get(BddUserClass.POIDS));

        // Btn annuler
        Button btnCancel = findViewById(R.id.btn_cancel);

        // Listener boutton annuler
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // Save the profile
    public void saveProfile(View v) {

        // Recuperation données champs
        String name = editName.getText().toString();
        String first_name = editFirstName.getText().toString();
        String height = editHeight.getText().toString();
        String weight = editWeight.getText().toString();

        // Creation du hashmap
        HashMap<String, String> hm = new HashMap<String, String>();

        // Creation des valeurs dans le HMAP
        hm.put(BddUserClass.NOM, name);
        hm.put(BddUserClass.PRENOM, first_name);
        hm.put(BddUserClass.TAILLE, height);
        hm.put(BddUserClass.POIDS, weight);

        dbuser.updateProfile(hm);

    }


}
