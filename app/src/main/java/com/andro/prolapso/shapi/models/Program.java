package com.andro.prolapso.shapi.models;

public class Program {
    private final int mId;
    private final String mName;

    Program(int id, String name) {
        mId = id;
        mName = name;
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
}
