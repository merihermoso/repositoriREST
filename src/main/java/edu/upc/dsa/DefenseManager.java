package edu.upc.dsa;


import edu.upc.dsa.models.Defense.Defense;

import java.util.List;

public interface DefenseManager {

    int size();

    Defense addDefense1(); //Creamos un objeto defense tipo 1
    Defense addDefense2(); //Creamos un objeto defense tipo 2
    Defense addDefense3(); //Creamos un objeto defense tipo 3
    Defense addDefense(Defense p); //Creamos un objeto defense

    Defense getDefense(String id);

    List<Defense> findAll();

    void deleteDefense(String id);
}
