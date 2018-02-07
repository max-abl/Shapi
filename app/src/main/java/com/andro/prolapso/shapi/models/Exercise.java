package com.andro.prolapso.shapi.models;

public class Exercise {
    private final int mId;
    private final String mName;
    private final Muscle mMuscle;


    public Exercise(int id, String name, Muscle muscle) {
        mId = id;
        mName = name;
        mMuscle = muscle;
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public Muscle getMuscle() {
        return mMuscle;
    }
}
