package edu.upc.dsa;


import edu.upc.dsa.models.Enemy.Enemy;
import java.util.List;

public interface EnemyManager {

    int size();

    Enemy addEnemy1(int x, int y); //Creamos un enemigo tipo 1
    Enemy addEnemy2(int x, int y); //Creamos un enemigo tipo 2
    Enemy addEnemy3(int x, int y); //Creamos un enemigo tipo 3
    Enemy addEnemy(Enemy p); //Creamos un enemigo

    Enemy getEnemy(String id);

    List<Enemy> findAll();

    void deleteEnemy(String id);
}
