package edu.upc.dsa.orm.models;

public class Player {              //classe per objectes de la botiga

    private int id;                 //s'hauria de vincular amb el userID
  //  private String username;            //s'hauria de relacionar amb username del user
    private String status;
    private int level;
    private int coins;
    private int score;

    public Player() {
    }
    public Player(String status, int coins, int score) {
        setStatus(status);
        setCoins(coins);
        setScore(score);
    }
    public int getId() {
        return this.id;
    }
    public void setId(int elementID) {
        this.id=elementID;
    }

    public String getStatus() {        return status;    }
    public void setStatus(String status) {        this.status = status;    }

    public int getCoins() {        return coins;    }
    public void setCoins(int coins) {        this.coins = coins;    }

    public int getScore() {        return score;    }
    public void setScore(int score) {        this.score = score;    }


    @Override
    public String toString(){
        return "Map { id: "+id+",status:" +status+", Coins:"+coins+", Score:"+score+ "}";
    }
}