package edu.upc.dsa.orm.models;

public class Partida {

    private int id;
    private String fechaInicio;
    private String horaInicio;
    private String fechaFin;
    private String horaFin;
    private int score;

    public Partida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score) {

        setFechaInicio(fechaInicio);
        setHoraInicio(horaInicio);
        setFechaFin(fechaFin);
        setHoraFin(horaFin);
        setScore(score);

    }

    public int getPartidaID() {
        return id;
    }
    public void setPartidaID(int partidaID) {
        this.id = partidaID;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }
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
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

}