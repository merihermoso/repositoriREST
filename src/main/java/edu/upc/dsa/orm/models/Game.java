package edu.upc.dsa.orm.models;

public class Game {

    private int id;
    private int id_user;
    private int id_map;
    private String dateStart;
    private String timeStart;
    private String dateEnd;
    private String timeEnd;
    private String dateLast;
    private String timeLast;

    private int score;
    private int coins;
    private int speed;
    private int defense;
    private int healing;
    private int damage;
    private int health;
    private int x;
    private int y;

    public Game() {

    }

    public Game(int id, int id_user, int id_map, String dateStart, String timeStart, String dateEnd, String timeEnd
            , String dateLast, String timeLast, int score, int coins
            , int speed, int defense, int healing, int damage, int health, int x, int y) {

        this.id = id;
        this.id_user = id_user;
        this.id_map = id_map;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.dateEnd = dateEnd;
        this.timeEnd = timeEnd;
        this.dateLast = dateLast;
        this.timeLast = timeLast;
        this.score = score;
        this.coins = coins;
        this.speed = speed;
        this.defense = defense;
        this.healing = healing;
        this.damage = damage;
        this.health = health;
        this.x = x;
        this.y = y;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_map() {
        return id_map;
    }

    public void setId_map(int id_map) {
        this.id_map = id_map;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDateLast() {
        return dateLast;
    }

    public void setDateLast(String dateLast) {
        this.dateLast = dateLast;
    }

    public String getTimeLast() {
        return timeLast;
    }

    public void setTimeLast(String timeLast) {
        this.timeLast = timeLast;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}