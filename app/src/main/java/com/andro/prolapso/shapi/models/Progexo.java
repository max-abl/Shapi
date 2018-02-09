package com.andro.prolapso.shapi.models;

/**
 * Created by Maxime on 09/02/2018.
 */

public class Progexo {


    private int id_program;
    private int id_exo;
    private String time;
    private int repetition;
    private int serie;
    private String weight;

    public Progexo(int id_program, int id_exo, String time, int repetition, int serie, String weight) {
        this.id_program = id_program;
        this.id_exo = id_exo;
        this.time = time;
        this.repetition = repetition;
        this.serie = serie;
        this.weight = weight;
    }


    public int getId_program() {
        return id_program;
    }

    public int getId_exo() {
        return id_exo;
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
