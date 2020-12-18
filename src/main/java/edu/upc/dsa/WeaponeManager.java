package edu.upc.dsa;

import edu.upc.dsa.models.Enemy.Enemy;
import edu.upc.dsa.models.Weapone.Weapone;

import java.util.List;

public interface WeaponeManager {

    int size();

    Weapone addWeapone1(int hit, int damage); //Creamos una weapone tipo 1
    Weapone addWeapone2(int hit, int damage); //Creamos una weapone tipo 2
    Weapone addWeapone3(int hit, int damage); //Creamos una weapone tipo 3
    Weapone addWeapone(Weapone p); //Creamos una weapone

    Weapone getWeapone(String id);

    List<Weapone> findAll();

    void deleteWeapone(String id);
}
