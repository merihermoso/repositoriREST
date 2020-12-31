package edu.upc.dsa.orm.dao;

import edu.upc.dsa.orm.models.Jugador;
import edu.upc.dsa.orm.models.Weapone.*;

public interface JugadorManager {

    Jugador CreateJugador(int x, int y); //Creamos el jugador y asignamos las coordenadas de aparicion
    Jugador CreateJugador(Jugador p); //Creamos el jugador

    void Disparo(Weapone weapone); //
    void Damage(int damage, int defense); //El jugador pierde vida
    void Health(int health); //El jugador recupera vida

    //List<Item> findAll();

}
