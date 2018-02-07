package com.andro.prolapso.shapi.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddUserClass;

import java.util.HashMap;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EDIT_PROFILE = 45;

    private TextView tv_bv, tv_morpho;
    private BddUserClass dbuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On cree la class user bdd
        dbuser = new BddUserClass(this);

        // On recup les tv
        tv_bv = findViewById(R.id.tv_bv);
        tv_morpho = findViewById(R.id.tv_morpho);

        // On met a jour la vue pour afficher les données en base
        updateView();

    }


    // Intent vers l'edition de profil
    public void editProfile(View v) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "EditProfileActivity");
        startActivityForResult(intent, REQUEST_EDIT_PROFILE);
    }


    // Le retour de l'edition de profil
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_EDIT_PROFILE) {
            if (resultCode == RESULT_OK) {
                updateView();
            }
        }
    }


    // Met a jour la vue en fonction des valeurs en bdd
    private void updateView() {

        HashMap<String, String> results;
        results = dbuser.getProfile();

        if (results.get(BddUserClass.NAME) != "nom") {

            // Set message Bienvenue
            tv_bv.setText("Bienvenue, " + results.get(BddUserClass.FIRSTNAME));

            // Set message morpho
            String text_morpho = tv_morpho.getText().toString();
            String text_morpho_alt = text_morpho.replace("%poids%", results.get(BddUserClass.WEIGHT));
            text_morpho = text_morpho_alt.replace("%taille%", results.get(BddUserClass.HEIGHT));
            tv_morpho.setText(text_morpho);
        }
    }

}
