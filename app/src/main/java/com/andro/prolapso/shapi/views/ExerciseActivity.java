package com.andro.prolapso.shapi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.andro.prolapso.shapi.R;
import com.andro.prolapso.shapi.controllers.BddExerciseClass;
import com.andro.prolapso.shapi.controllers.ExerciseAdapter;
import com.andro.prolapso.shapi.models.Exercise;

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

        ListView progListView = findViewById(R.id.exercises);
        mExerciseList = mBddExerciceClass.getAllExercises();
        mExerciseAdapter = new ExerciseAdapter(this, mExerciseList);
        progListView.setAdapter(mExerciseAdapter);

    }
}
