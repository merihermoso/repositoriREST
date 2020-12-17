package edu.upc.dsa.models.Enemy;

import edu.upc.dsa.models.Enemy.*;

public abstract class Enemy {      //MAI podem crear instancies de enemy

    //En el Main de cada mapa, deberemos crear una Lista 'Enemy' con los tipos de enemigos

    public abstract String getId(); //Id al enemigo creado
    public abstract void setId(String id);

    public abstract int getPosX(); //PosX del enemigo creado
    public abstract void setPosX(int posX);

    public abstract int getPosY(); //PosY del enemigo creado
    public abstract void setPosY(int posY);
}

