package com.andro.prolapso.shapi.models;

public class Exercise {
    private final int mId;
    private final String mName;
    private final String mDescription;
    private final String mType;

    public Exercise(int id, String description, String name, String type) {
        mId = id;
        mName = name;
        mDescription = description;
        mType = type;
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getType() {
        return mType;
    }

}
