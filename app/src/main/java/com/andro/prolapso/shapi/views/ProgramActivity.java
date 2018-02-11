package com.andro.prolapso.shapi.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    // Used to add exercise to program
    static final String ACTION_ADD_EXERCISE = "action.add.exercise",
            EXTRA_PROGRAM_ID = "extra.program.id",
            EXTRA_EXO_ID = "extra.exo.id",
            EXTRA_TIME = "extra.prog.exo.time",
            EXTRA_WEIGHT = "extra.prog.exo.weight",
            EXTRA_REPETITION = "extra.prog.exo.repetition",
            EXTRA_SERIE = "extra.prog.exo.serie";

    private BddProgexoClass mBddProgexoClass;
    private ArrayList<ProgExo> mProgExercises;
    private ProgExoAdapter mProgExoAdapter;

    // Current program
    private Program mProgram;

    // Used to dissmis ChoiceDialog
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
            mBddProgexoClass = new BddProgexoClass(this, bddProgramClass);

            mProgram = bddProgramClass.getProgramsById(Integer.toString(progId));

            setTitle(mProgram.getName());

            final ListView progExoListView = findViewById(R.id.list_prog_exos);
            mProgExercises = mBddProgexoClass.getAllExoProgram(mProgram.getId());
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
            final FloatingActionButton btnAdd = findViewById(R.id.btn_add_prog_exo);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startIntent = new Intent(ProgramActivity.this, ExerciseListActivity.class);
                    startIntent.setAction(ACTION_ADD_EXERCISE);
                    startIntent.putExtra(EXTRA_PROGRAM_ID, Integer.toString(mProgram.getId()));
                    startActivityForResult(startIntent, REQUEST_NEW_PROG_EXO);
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
                new ProgExoDialogBuilder(ProgramActivity.this, progExo, mAlertDialog).create().show();
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
                        deleteProgExo(progExo);
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
                final String newExoId = data.getStringExtra(EXTRA_EXO_ID);
                if (newExoId != null) {
                    ProgExo newProgExo = new ProgExo(mProgram.getId(),
                            mBddProgexoClass.getBddExerciseClass().getExerciseById(newExoId),
                            data.getStringExtra(EXTRA_TIME),
                            data.getIntExtra(EXTRA_REPETITION, 0),
                            data.getIntExtra(EXTRA_SERIE, 0),
                            data.getStringExtra(EXTRA_WEIGHT));
                    mBddProgexoClass.addProgexo(newProgExo);
                    mProgExercises.add(newProgExo);
                    mProgExoAdapter.notifyDataSetChanged();
                    modifications = true;
                }
            }
        }
    }

    // Remove the exercise for a program
    private void deleteProgExo(ProgExo progExo) {
        modifications = true;
        mBddProgexoClass.deleteProgExo(progExo.getProgramId(), progExo.getExo().getId());
        mProgExercises.remove(progExo);
        mProgExoAdapter.notifyDataSetChanged();
    }

    // Update ProgExo
    void updateProgExo(ProgExo progExo) {
        mBddProgexoClass.updateProgram(progExo);
        mProgExercises = mBddProgexoClass.getAllExoProgram(mProgram.getId());
        mProgExoAdapter.notifyDataSetChanged();
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
