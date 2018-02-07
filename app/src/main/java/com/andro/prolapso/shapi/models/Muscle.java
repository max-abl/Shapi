package com.andro.prolapso.shapi.models;

public class Muscle {
    private final int mId;
    private final String mName, mType;

    public Muscle(int id, String name, String type) {
        mId = id;
        mName = name;
        mType = type;
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getType() {
        return mType;
    }

}
