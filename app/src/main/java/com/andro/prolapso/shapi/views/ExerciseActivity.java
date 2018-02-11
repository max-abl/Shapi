package com.andro.prolapso.shapi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddExerciseClass;
import com.andro.prolapso.shapi.controllers.BddProgramClass;
import com.andro.prolapso.shapi.controllers.ExerciseAdapter;
import com.andro.prolapso.shapi.controllers.ProgramAdapter;
import com.andro.prolapso.shapi.models.Exercise;
import com.andro.prolapso.shapi.models.Program;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {
    private ArrayList<Exercise> mExerciseList;
    private ExerciseAdapter mExerciseAdapter;

    private BddExerciseClass mBddExerciceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        mBddExerciceClass = new BddExerciseClass(this);

        ListView progListView = findViewById(R.id.list_exercises);
        mExerciseList = mBddExerciceClass.getAllExercise();
        mExerciseAdapter = new ExerciseAdapter(this, android.R.layout.simple_list_item_2, mExerciseList);
        progListView.setAdapter(mExerciseAdapter);

    }
}
