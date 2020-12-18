package edu.upc.dsa;


import edu.upc.dsa.models.Healing.Healing;
import java.util.List;

public interface HealingManager {

    int size();

    Healing addHealing1(int hit, int force); //Creamos un healing tipo 1
    Healing addHealing2(int hit, int force); //Creamos un healing tipo 2
    Healing addHealing3(int hit, int force); //Creamos un healing tipo 3
    Healing addHealing(Healing p); //Creamos un healing

    Healing getHelaing(String id);

    List<Healing> findAll();

    void deleteHealing(String id);

}
