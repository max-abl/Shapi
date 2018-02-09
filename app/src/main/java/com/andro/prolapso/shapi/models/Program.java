package com.andro.prolapso.shapi.models;

import com.andro.prolapso.shapi.controllers.BddProgexoClass;

import java.util.ArrayList;

public class Program {

    private BddProgexoClass bdd;

    private final int mId;
    private final String mName;
    private ArrayList<Progexo> mProgramExercises;

    public Program(int id, String name) {
        mId = id;
        mName = name;
        mProgramExercises =  bdd.getAllExoProgram((Integer.toString(mId)));
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<Progexo> getExercises() {
        mProgramExercises =  bdd.getAllExoProgram((Integer.toString(mId)));
        return mProgramExercises;
    }
}
