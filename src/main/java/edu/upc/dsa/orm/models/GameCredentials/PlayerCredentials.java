package edu.upc.dsa.orm.models.GameCredentials;

public class PlayerCredentials {

    private String status;
    private int coins;
    private int score;
    private int numLevel;
    private int speed;
    private int hit;
    private int defense;
    private int healing;
    private int damage;

    public String getStatus() {        return status;    }
    public void setStatus(String status) {        this.status = status;    }

    public int getCoins() {        return coins;    }
    public void setCoins(int coins) {        this.coins = coins;    }

    public int getScore() {        return score;    }
    public void setScore(int score) {        this.score = score;    }

    public int getNumLevel() {        return numLevel;    }
    public void setNumLevel(int numLevel) {        this.numLevel = numLevel;    }

    public int getSpeed() {        return speed;    }
    public void setSpeed(int speed) {        this.speed = speed;    }

    public int getHit() {        return hit;    }
    public void setHit(int hit) {        this.hit = hit;    }

    public int getDefense() {        return defense;    }
    public void setDefense(int defense) {        this.defense = defense;    }

    public int getHealing() {        return healing;    }
    public void setHealing(int healing) {        this.healing = healing;    }

    public int getDamage() {        return damage;    }
    public void setDamage(int damage) {        this.damage = damage;    }


}
