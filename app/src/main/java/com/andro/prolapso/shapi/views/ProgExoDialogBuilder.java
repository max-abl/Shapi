package com.andro.prolapso.shapi.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.models.Exercise;
import com.andro.prolapso.shapi.models.ProgExo;

class ProgExoDialogBuilder extends AlertDialog.Builder {
    private final Activity mActivity;
    private final EditText editTime, editSerie, editRepetition, editWeight;
    private final int mProgId;
    private final Exercise mExercise;

        // Used to update
    ProgExoDialogBuilder(final ProgramActivity activity, final ProgExo progExo, AlertDialog alertDialog) {
        super(activity);
        mActivity = activity;
        mExercise = progExo.getExo();
        mProgId = progExo.getProgramId();

        final View dialogView = View.inflate(activity, R.layout.prog_exo_form, null);

        setView(dialogView);
        setTitle(progExo.getExo().getName());

        editTime = dialogView.findViewById(R.id.edit_prog_exo_form_time);
        editSerie = dialogView.findViewById(R.id.edit_prog_exo_form_serie);
        editRepetition = dialogView.findViewById(R.id.edit_prog_exo_form_repetition);
        editWeight = dialogView.findViewById(R.id.edit_prog_exo_form_weight);

        // Prepopulate fields
        editRepetition.setText(activity.getString(R.string.integer, progExo.getRepetition()));
        editSerie.setText(activity.getString(R.string.integer, progExo.getSerie()));
        editTime.setText(progExo.getTime());
        editWeight.setText(progExo.getWeight());

        setNegativeButton(R.string.cancel, null);
        setPositiveButton(R.string.ok, null);

        alertDialog.dismiss();
    }

    // Used to create
    ProgExoDialogBuilder(final ExerciseListActivity activity, final int programId, final Exercise exercise) {
        super(activity);

        mProgId = programId;
        mExercise = exercise;

        mActivity = activity;

        final View dialogView = View.inflate(activity, R.layout.prog_exo_form, null);

        setView(dialogView);
        setTitle(exercise.getName());

        editTime = dialogView.findViewById(R.id.edit_prog_exo_form_time);
        editSerie = dialogView.findViewById(R.id.edit_prog_exo_form_serie);
        editRepetition = dialogView.findViewById(R.id.edit_prog_exo_form_repetition);
        editWeight = dialogView.findViewById(R.id.edit_prog_exo_form_weight);

        setNegativeButton(R.string.cancel, null);
        setPositiveButton(R.string.ok, null);
    }

    @Override
    public AlertDialog create() {
        final AlertDialog dialog = super.create();

        // Show Keyboard
        if (dialog.getWindow() != null)
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        // Prevent dismiss if edit fields empty
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button button = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                button.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (mActivity instanceof ProgramActivity) {
                            final String time = editTime.getText().toString().length() == 0 ? "0" : editTime.getText().toString(),
                                    weight = editWeight.getText().toString().length() == 0 ? "0" : editWeight.getText().toString();

                            if (editSerie.getText().toString().length() == 0) {
                                Toast.makeText(mActivity, R.string.prog_exo_form_toast_serie, Toast.LENGTH_SHORT).show();
                            } else if (editRepetition.getText().toString().length() == 0) {
                                Toast.makeText(mActivity, R.string.prog_exo_form_toast_repetition, Toast.LENGTH_SHORT).show();
                            } else {
                                ProgExo newProgExo = new ProgExo(mProgId, mExercise, time, Integer.parseInt(editRepetition.getText().toString()),
                                        Integer.parseInt(editSerie.getText().toString()), weight);
                                // Update
                                ((ProgramActivity) mActivity).updateProgExo(newProgExo);
                            }
                        } else {
                            final String time = editTime.getText().toString().length() == 0 ? "0" : editTime.getText().toString(),
                                    weight = editWeight.getText().toString().length() == 0 ? "0" : editWeight.getText().toString();

                            if (editSerie.getText().toString().length() == 0) {
                                Toast.makeText(mActivity, R.string.prog_exo_form_toast_serie, Toast.LENGTH_SHORT).show();
                            } else if (editRepetition.getText().toString().length() == 0) {
                                Toast.makeText(mActivity, R.string.prog_exo_form_toast_repetition, Toast.LENGTH_SHORT).show();
                            } else {
                                ProgExo newProgExo = new ProgExo(mProgId, mExercise, time, Integer.parseInt(editRepetition.getText().toString()),
                                        Integer.parseInt(editSerie.getText().toString()), weight);
                                // Create
                                ((ExerciseListActivity) mActivity).returnAndAddExercise(newProgExo);
                            }
                        }
                    }
                });

            }
        });

        return dialog;
    }
}
