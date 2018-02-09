package com.andro.prolapso.shapi.models;

import java.util.ArrayList;

public class Program {
    private final int mId;
    private final String mName;
    private final ArrayList<Exercise> mExercises;

    public Program(int id, String name) {
        mId = id;
        mName = name;

        // TODO: get exercises from bd
        mExercises = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<Exercise> getExercises() {
        return mExercises;
    }
}
