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
        dbuser = new BddUserClass(this);

        // HashMap
        final HashMap<String, String> results;
        results = dbuser.getProfile();

        // Champs
        editName = findViewById(R.id.edit_name);
        editFirstName = findViewById(R.id.edit_firstname);
        editHeight = findViewById(R.id.edit_height);
        editWeight = findViewById(R.id.edit_weight);

        // On pre-remplis les champs
        editName.setText(results.get(BddUserClass.NAME));
        editFirstName.setText(results.get(BddUserClass.FIRSTNAME));
        editHeight.setText(results.get(BddUserClass.HEIGHT));
        editWeight.setText(results.get(BddUserClass.WEIGHT));

        // Btn annuler
        final Button btnCancel = findViewById(R.id.btn_cancel);

        // Listener boutton annuler
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
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
        HashMap<String, String> hm = new HashMap<>();

        // Creation des valeurs dans le HashMap
        hm.put(BddUserClass.NAME, name);
        hm.put(BddUserClass.FIRSTNAME, first_name);
        hm.put(BddUserClass.HEIGHT, height);
        hm.put(BddUserClass.WEIGHT, weight);

        dbuser.updateProfile(hm);

        setResult(RESULT_OK);
        finish();
    }
    
}
