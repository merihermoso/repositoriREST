package edu.upc.dsa.orm.models;

public class Partida {

    private int partidaID;              //mirar si a bbdd es id_partida?!
    private String fechaInicio;
    private String horaInicio;
    private String fechaFin;
    private String horaFin;
    private int score;


    public Partida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score) {
        this.partidaID = partidaID;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.score = score;
    }

    public int getPartidaID() {
        return partidaID;
    }
    public void setPartidaID(int partidaID) {
        this.partidaID = partidaID;
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

    @Override
    public String toString() {
        return "Partida [id_partida = "+partidaID+", FechaInicioPartida= " +fechaInicio+", HoraInicioPartida= " +horaInicio+", FechaFinPartida= " +fechaFin+", HoraFinPartida= " +horaFin+", ScorePartida = " +score+ "]";
    }
}