package com.andro.prolapso.shapi.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddExerciseClass;
import com.andro.prolapso.shapi.controllers.BddProgramClass;
import com.andro.prolapso.shapi.controllers.ExerciseAdapter;
import com.andro.prolapso.shapi.models.Exercise;
import com.andro.prolapso.shapi.models.ProgExo;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ExerciseListActivity extends AppCompatActivity {
    private ArrayList<Exercise> mExerciseList;

    // If True, inform that we previously were in ProgramActivity and we want to add an exercise
    // Else, we were in MainActivity
    private boolean addingExercise;
    private Program program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_list);

        BddExerciseClass mBddExercise = new BddExerciseClass(this);

        Intent launchIntent = getIntent();
        addingExercise = ProgramActivity.ACTION_ADD_EXERCISE.equals(launchIntent.getAction());
        if (addingExercise) {
            program = new BddProgramClass(this).getProgramsById(launchIntent.getStringExtra(ProgramActivity.EXTRA_PROGRAM_ID));
        }

        final ListView programListView = findViewById(R.id.list_exercises);
        mExerciseList = mBddExercise.getAllExercises();
        final ExerciseAdapter exercisesAdatper = new ExerciseAdapter(this, mExerciseList);
        programListView.setAdapter(exercisesAdatper);

        programListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Exercise selectedExercise = mExerciseList.get(i);
                if (addingExercise) {
                    new ProgExoDialogBuilder(ExerciseListActivity.this, program.getId(), selectedExercise).create().show();
                } else {
                    // TODO: do something on exercise click
                }
            }
        });
    }

    // Return the exercise to add to a program in ProgramAcitivty
    void returnAndAddExercise(ProgExo progExo) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(ProgramActivity.EXTRA_EXO_ID, Integer.toString(progExo.getExo().getId()));
        resultIntent.putExtra(ProgramActivity.EXTRA_REPETITION, progExo.getRepetition());
        resultIntent.putExtra(ProgramActivity.EXTRA_SERIE, progExo.getSerie());
        resultIntent.putExtra(ProgramActivity.EXTRA_TIME, progExo.getTime());
        resultIntent.putExtra(ProgramActivity.EXTRA_WEIGHT, progExo.getWeight());
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
