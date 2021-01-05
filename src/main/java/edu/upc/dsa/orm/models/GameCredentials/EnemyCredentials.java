package edu.upc.dsa.orm.models.GameCredentials;

public class EnemyCredentials {

    private int id;
    private String name;
    private int hit;
    private int defense;
    private int healing;
    private int damage;
 //   private int posX;
 //   private int posY;


    public String getName() {        return name;    }
    public void setName(String item) {
        this.name = item;
    }

    public int getHit() {
        return hit;
    }
    public void setHit(int hit) {
        this.hit = hit;
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

 //   public int getPosX() {        return posX;    }
 //   public void setPosX(int posX) {        this.posX = posX;    }

  //  public int getPosY() {        return posY;    }
  //  public void setPosY(int posY) {        this.posY = posY;    }


}
