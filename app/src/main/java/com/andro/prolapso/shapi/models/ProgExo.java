package com.andro.prolapso.shapi.models;

public class ProgExo {

    private final int programId;
    private final Exercise exo;
    private final int repetition, serie;
    private final String time, weight;

    public ProgExo(int programId, Exercise exo, String time, int repetition, int serie, String weight) {
        this.programId = programId;
        this.exo = exo;
        this.time = time;
        this.repetition = repetition;
        this.serie = serie;
        this.weight = weight;
    }

    public int getProgramId() {
        return programId;
    }

    public Exercise getExo() {
        return exo;
    }

    public String getTime() {
        return time;
    }

    public int getRepetition() {
        return repetition;
    }

    public int getSerie() {
        return serie;
    }

    public String getWeight() {
        return weight;
    }

}
