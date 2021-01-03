package edu.upc.dsa.orm.models;

public class Partidas {

    private int id;
    private String fechaInicio;
    private String horaInicio;
    private String fechaFin;
    private String horaFin;
    private int score_partida;

    public Partidas() {

    }
    public Partidas(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score_partida) {

        setFechaInicio(fechaInicio);
        setHoraInicio(horaInicio);
        setFechaFin(fechaFin);
        setHoraFin(horaFin);
        setScore(score_partida);

    }
    public int getId() {
        return this.id;
    }
    public void setId(int partidaID) {
        this.id=partidaID;
    }

    public String getFechaInicio() {return fechaInicio;}
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public int getScore() {
        return score_partida;
    }
    public void setScore(int score_partida) {
        this.score_partida = score_partida;
    }

}