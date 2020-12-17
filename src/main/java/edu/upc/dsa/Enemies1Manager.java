package edu.upc.dsa;

import edu.upc.dsa.models.Enemy1;
import edu.upc.dsa.models.User;

import java.util.List;

public interface Enemies1Manager {

    public int size();

    public Enemy1 addEnemy1(int x, int y); //Creamos un enemigo
    public Enemy1 addEnemy1(Enemy1 p);

    //public User getUser(String id);
    public List<Enemy1> findAll();

    public void deleteEnemy1(String id);
}
