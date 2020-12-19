package edu.upc.dsa;


import edu.upc.dsa.models.Healing.Healing;
import java.util.List;

public interface HealingManager {

    int size();

    Healing addHealing1(); //Creamos un healing tipo 1
    Healing addHealing2(); //Creamos un healing tipo 2
    Healing addHealing3(); //Creamos un healing tipo 3
    Healing addHealing(Healing p); //Creamos un healing

    Healing getHealing(String id);

    List<Healing> findAll();

    void deleteHealing(String id);

}
