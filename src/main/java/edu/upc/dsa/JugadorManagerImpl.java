package edu.upc.dsa;


import edu.upc.dsa.models.Jugador;
import edu.upc.dsa.models.Weapone.Weapone;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class JugadorManagerImpl implements JugadorManager{

    private static JugadorManager instance;

    final static Logger logger = Logger.getLogger(JugadorManagerImpl.class);



    private JugadorManagerImpl() { //Constructor
        //this.enemies = new LinkedList<>(); //Que pongo aqui?
    }

    public static JugadorManager getInstance() {
        if (instance==null) instance = new JugadorManagerImpl();
        return instance;
    }

    @Override
    public Jugador CreateJugador(int x, int y) {
        return this.CreateJugador(new Jugador(x, y));
    }

    @Override
    /*public Jugador CreateJugador(Jugador p) {
        logger.info("new jugador to add: " + t);

        this.enemies.add (t);
        logger.info("new Jugador added");
        return t;
    }*/

    @Override
    public void Disparo(Weapone weapone) {

    }

    @Override
    public void Damage(int damage, int defense) {

    }

    @Override
    public void Health(int health) {

    }
}
