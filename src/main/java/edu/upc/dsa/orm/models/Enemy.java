package edu.upc.dsa.orm.models;


public class Enemy {                         //classe per objectes DINS del joc

    private int id; //Asignamos un id al name creado
    private String name;
    private int hit;
    private int defense;
    private int healing;
    private int damage;
 //   private int posX;         añadir a bbdd
 //   private int posY;

    public Enemy() {

    }

    public Enemy(String name, int hit, int defense, int healing, int damage) {      //, int posX, int posY

        this();
        setName(name);
        setHit(hit);
        setDefense(defense);
        setHealing(healing);
        setDamage(damage);
      //  setPosX(posX);
      //  setPosY(posY);
    }

    public String getName() {        return name;    }
    public void setName(String item) {
        this.name = item;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

  //  public int getPosX() {        return posX;    }
  //  public void setPosX(int posX) {        this.posX = posX;    }

    //  public int getPosY() {        return posY;    }
   // public void setPosY(int posY) {        this.posY = posY;    }
    @Override
    public String toString(){
        return "User { id: "+id+",Name:" +name+", Hit:"+hit+", Defense:"+defense+", Healing:"+healing+ ", Damage:"+damage+"}";
    }
}
