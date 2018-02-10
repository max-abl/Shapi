package com.andro.prolapso.shapi.models;

public class ProgExo {

    private final int repetition, serie;
    private final Program program;
    private final Exercise exo;
    private final String time, weight;

    public ProgExo(Program program, Exercise exo, String time, int repetition, int serie, String weight) {
        this.program = program;
        this.exo = exo;
        this.time = time;
        this.repetition = repetition;
        this.serie = serie;
        this.weight = weight;
    }

    public Program getProgram() {
        return program;
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
