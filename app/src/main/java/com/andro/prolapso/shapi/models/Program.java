package com.andro.prolapso.shapi.models;

import java.util.ArrayList;

public class Program {

    private final int mId;
    private final ArrayList<ProgExo> mProgramExercises;
    private String mName;

    public Program(int id, String name, ArrayList<ProgExo> progExos) {
        mId = id;
        mName = name;
        mProgramExercises = progExos;
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<ProgExo> getExercises() {
        return mProgramExercises;
    }

    // Setter
    public void setName(String newName) {
        mName = newName;
    }
}
