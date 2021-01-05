package edu.upc.dsa.orm.models.GameCredentials;

public class GameCredentials {

    private String dateStart;
    private String timeStart;
    private String dateEnd;
    private String timeEnd;
    private int score;

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


}
