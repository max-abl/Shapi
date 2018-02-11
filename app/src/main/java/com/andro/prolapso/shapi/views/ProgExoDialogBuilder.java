package com.andro.prolapso.shapi.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.models.Exercise;
import com.andro.prolapso.shapi.models.ProgExo;
import com.andro.prolapso.shapi.models.Program;

class ProgExoDialogBuilder extends AlertDialog.Builder {

    ProgExoDialogBuilder(final Activity activity, final Program program, final Exercise exercise) {
        super(activity);

        final View dialogView = View.inflate(activity, R.layout.prog_exo_form, null);

        setView(dialogView);
        setTitle(exercise.getName());

        final EditText editTime = dialogView.findViewById(R.id.edit_prog_exo_form_time),
                editSerie = dialogView.findViewById(R.id.edit_prog_exo_form_serie),
                editRepetition = dialogView.findViewById(R.id.edit_prog_exo_form_repetition),
                editWeight = dialogView.findViewById(R.id.edit_prog_exo_form_weight);

        final String time = editTime.getText().toString().length() == 0 ? "0" : editTime.getText().toString(),
                weight = editWeight.getText().toString().length() == 0 ? "0" : editWeight.getText().toString();

        setNegativeButton(R.string.cancel, null);
        setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editSerie.getText().toString().length() == 0) {
                    Toast.makeText(activity, R.string.prog_exo_form_toast_serie, Toast.LENGTH_SHORT).show();
                } else if (editRepetition.getText().toString().length() == 0) {
                    Toast.makeText(activity, R.string.prog_exo_form_toast_repetition, Toast.LENGTH_SHORT).show();
                } else {
                    ProgExo newProgExo = new ProgExo(program, exercise, time, Integer.parseInt(editRepetition.getText().toString()),
                            Integer.parseInt(editSerie.getText().toString()), weight);
                    if (activity instanceof ProgramActivity) {
                        // Update
                        ((ProgramActivity) activity).updateProgExo(newProgExo);

                    } else {
                        // Create
                        ((ExerciseListActivity) activity).returnAndAddExercise(newProgExo);
                    }
                }
            }
        });
    }

}
