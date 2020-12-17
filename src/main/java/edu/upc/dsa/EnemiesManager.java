package edu.upc.dsa;

import edu.upc.dsa.models.Enemy.Enemy;

import java.util.List;

public interface EnemiesManager {

    public int size();

    public Enemy addEnemy(int x, int y); //Creamos un enemigo
    public Enemy addEnemy(Enemy p);

    public Enemy getEnemy(String id);

    //public User getUser(String id);
    public List<Enemy> findAll();

    public void deleteEnemy(String id);
}
