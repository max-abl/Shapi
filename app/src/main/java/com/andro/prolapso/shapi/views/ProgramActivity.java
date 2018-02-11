package com.andro.prolapso.shapi.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddProgexoClass;
import com.andro.prolapso.shapi.controllers.BddProgramClass;
import com.andro.prolapso.shapi.controllers.ProgExoAdapter;
import com.andro.prolapso.shapi.models.ProgExo;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ProgramActivity extends AppCompatActivity {
    private static final int REQUEST_NEW_PROG_EXO = 68;
    static final String EXTRA_EXO_ID = "extra.exo.id";

    private BddProgexoClass mBbddProgexoClass;
    private ArrayList<ProgExo> mProgExercises;
    private ProgExoAdapter mProgExoAdapter;

    private int idProgram;
    private AlertDialog mAlertDialog;
    // True if the user modificates something
    private boolean modifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        modifications = false;

        final int progId = getIntent().getIntExtra(ProgramListActivity.EXTRA_PROGRAM_ID, -1);
        if (progId == -1) finish();
        else {
            final BddProgramClass bddProgramClass = new BddProgramClass(this);
            mBbddProgexoClass = new BddProgexoClass(this, bddProgramClass);

            final Program program = bddProgramClass.getProgramsById(Integer.toString(progId));

            setTitle(program.getName());

            idProgram = program.getId();

            final ListView progExoListView = findViewById(R.id.list_exercises);
            mProgExercises = mBbddProgexoClass.getAllExoProgram(Integer.toString(idProgram));
            mProgExoAdapter = new ProgExoAdapter(this, mProgExercises);
            progExoListView.setAdapter(mProgExoAdapter);

            progExoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    showChoiceDialog(mProgExercises.get(i));
                    return false;
                }
            });

            // Bouton ajout exercice
            final Button btnAdd = findViewById(R.id.btn_add_prog_exo);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: list exercices
                }
            });
        }
    }

    // Show dialog which ask whether to delete or not the program
    private void showChoiceDialog(final ProgExo progExo) {

        // Dialog Edit ProgExo
        View.OnClickListener listenerOne = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //TODO:     new ProgExoDialogBuilder(ProgramActivity.this, progExo);
            }
        };

        // Dialog to Delete ProgramExo
        View.OnClickListener listenerTwo = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProgramActivity.this);
                builder.setTitle(R.string.delete_title);
                builder.setMessage(R.string.program_dialog_delete_message);
                builder.setNegativeButton(R.string.cancel, null);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        deleteProgExo(progExo.getExo().getId());
                        mAlertDialog.dismiss();
                    }
                });
                builder.create().show();
            }
        };

        // Build choice dialog
        mAlertDialog = new ChoiceDialogBuilder(ProgramActivity.this, R.string.program_dialog_choice_update,
                R.string.program_list_dialog_choice_delete, listenerOne, listenerTwo).create();

        mAlertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_NEW_PROG_EXO) {
            // New exercise added
            if (resultCode == RESULT_OK) {
                int newProgExoId = data.getIntExtra(EXTRA_EXO_ID, -1);
                if (newProgExoId != -1) {
                    mProgExercises.add(mBbddProgexoClass.getProgExoByIds(Integer.toString(idProgram), Integer.toString(newProgExoId)));
                    mProgExoAdapter.notifyDataSetChanged();
                    modifications = true;
                }
            }
        }
    }

    // Remove the exercise for a program
    private void deleteProgExo(int exoId) {
        mBbddProgexoClass.deleteProgExo(idProgram, exoId);
    }

    // Update ProgExo
    void updateProgExo(ProgExo progExo) {
        mBbddProgexoClass.updateProgram(progExo);
    }

    @Override
    public void onBackPressed() {
        // Update interface in previous activity if we did changes
        if (modifications) {
            setResult(RESULT_OK);
        }
        super.onBackPressed();
    }
}
