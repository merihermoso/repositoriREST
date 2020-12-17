package edu.upc.dsa.models.Object;

public abstract class Object {

    //Cuando cogemos un objeto debemos añadirlo a la lista de objects

    public abstract String getId(); //Id del objeto creado
    public abstract void setId(String id);

    public abstract int getHit(); //Asignamos la cantidad de usos del objeto
    public abstract void setHit(int hit);

    public abstract int getForce(); //Asignamos cuanto nos sirve el objeto
    public abstract void setForce(int force);
}
