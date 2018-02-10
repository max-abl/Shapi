package com.andro.prolapso.shapi.models;

import java.util.ArrayList;

public class Program {

    private final int mId;
    private final String mName;
    private final ArrayList<Progexo> mProgramExercises;

    public Program(int id, String name, ArrayList<Progexo> progexos) {
        mId = id;
        mName = name;
        mProgramExercises = progexos;
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<Progexo> getExercises() {
        return mProgramExercises;
    }
}
