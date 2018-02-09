package com.andro.prolapso.shapi.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddUserClass;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EDIT_PROFILE = 45;

    private TextView textHello, textMorpho;
    private BddUserClass dbuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On cree la class user bdd
        dbuser = new BddUserClass(this);

        // On recup les tv
        textHello = findViewById(R.id.text_hello);
        textMorpho = findViewById(R.id.text_morpho);

        // On met a jour la vue pour afficher les données en base
        updateView();

        final Button btnProg = findViewById(R.id.btn_programs);
        btnProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProgrammesActivity.class));
            }
        });
    }


    // Intent vers l'edition de profil
    public void editProfile(View v) {
        startActivityForResult(new Intent(this, EditProfileActivity.class), REQUEST_EDIT_PROFILE);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // Met a jour la vue en fonction des valeurs en bdd
    private void updateView() {

        HashMap<String, String> results;
        results = dbuser.getProfile();

        if (results.get(BddUserClass.NAME).equals("nom")) {
            // Set message Bienvenue
            textHello.setText(getString(R.string.hello_user, results.get(BddUserClass.FIRSTNAME)));

            // Set message morpho
            textMorpho.setText(getString(R.string.main_tv_morpho,
                    results.get(BddUserClass.WEIGHT),
                    results.get(BddUserClass.HEIGHT)));
        } else {
            textMorpho.setVisibility(View.INVISIBLE);
        }
    }

}
