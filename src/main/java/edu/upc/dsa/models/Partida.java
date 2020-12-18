package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Partida {

    String id_partida;
    int score_partida;

    public Partida() {
        this.id_partida = RandomUtils.getId();
        this.score_partida = 0;
    }

    public Partida(int score_partida) {
        this();
        this.setScore_partida(score_partida);
    }

    public String getId_partida() {
        return this.id_partida;
    }

    public void setId_partida(String id) {
        this.id_partida = id;
    }

    public int getScore_partida() {
        return score_partida;
    }

    public void setScore_partida(int score_partida) {
        this.score_partida = score_partida;
    }



    @Override
    public String toString() {
        return "Partida [id_partida = "+id_partida+", Score partida = " +score_partida+ "]";
    }

}