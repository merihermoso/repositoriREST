package edu.upc.dsa.orm.models.Enemy;


public abstract class Enemy {      //MAI podem crear instancies de enemy

    //En el Main de cada mapa, deberemos crear una Lista 'Enemy' con los tipos de enemigos
    String id; //Enemy's id
    int posX; //Enemy's "X" position
    int posY; //Enemy's "Y" position

    int health; //Enemy's health
    int damage; //Enemy's damage per hit
    int speed; //Enemy's speedd

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}

