package edu.upc.dsa.orm.models;

public class Game {

    private int id;
    private String dateStart;
    private String timeStart;
    private String dateEnd;
    private String timeEnd;
    private int score;
    private int userID;


    public Game() {

    }
    public Game(String dateStart, String timeStart, String dateEnd, String timeEnd, int score, int userID) {

        setDateStart(dateStart);
        setTimeStart(timeStart);
        setDateStart(dateEnd);
        setTimeEnd(timeEnd);
        setScore(score);
        setUserID(userID);

    }
    public int getId() {
        return this.id;
    }
    public void setId(int partidaID) {
        this.id=partidaID;
    }

    public String getDateStart() {        return dateStart;    }
    public void setDateStart(String dataStart) {        this.dateStart = dataStart;    }

    public String getTimeStart() {        return timeStart;    }
    public void setTimeStart(String timeStart) {        this.timeStart = timeStart;    }

    public String getDateEnd() {        return dateEnd;    }
    public void setDateEnd(String dateEnd) {        this.dateEnd = dateEnd;    }

    public String getTimeEnd() {        return timeEnd;    }
    public void setTimeEnd(String timeEnd) {        this.timeEnd = timeEnd;    }

    public int getScore() {        return score;    }
    public void setScore(int score) {        this.score = score;    }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

}