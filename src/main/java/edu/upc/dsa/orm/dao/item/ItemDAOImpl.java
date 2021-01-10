package edu.upc.dsa.orm.dao.item;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.exeptions.UserNotFoundException;
import edu.upc.dsa.orm.models.*;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;

import java.sql.SQLException;
import java.util.*;

import static org.reflections.Reflections.log;

public class ItemDAOImpl implements ItemDAO {
    private static ItemDAO instance;

    private ItemDAOImpl() {
    }

    public static ItemDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new ItemDAOImpl();
        return instance;
    }
    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Item> findAll(){

        Session session;
        List<Item> itemsList;

        HashMap<Integer, Item> result;

        session = FactorySession.openSession();
        result = session.findAll(Item.class);

        itemsList = new ArrayList<>(result.values());

        session.close();

        return itemsList;
    }
    public int size() {
        Session session;
        HashMap<String, Item> items = null;
        try{
            session = FactorySession.openSession();
            items = session.findAll(Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items.size();
    }


    /*****************************************  OBTENIM ITEM inventari *************************************************/
    //Función que devuelve los objetos del jugador

    @Override
    public HashMap<Integer,Inventory> getItemsUser(String username) throws UserNotFoundException {
        int id_user;
        Session session = null;
        HashMap<Integer, Inventory> items=null;
        try {
            session = FactorySession.openSession();
            id_user = session.getUserIdByUsername(username);
            items = session.getItemsUser(Inventory.class,id_user);
            System.out.println(items);
        }
        catch (Exception e) {
            log.error("Error");
        }
        finally {
            session.close();
        }

        /*for(User u : usersList)
            System.out.println(u.toString());*/
        return items;
    }


    public Item getItemById(int id_item) throws SQLException {
        Session session = null;
        Item item = new Item();
        try {
            session = FactorySession.openSession();
            item = (Item) session.getById(item, id_item);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return item;
    }
    public Item getItemByName(String name) throws SQLException {
        Session session = null;
        Item item = new Item();
        try {
            session = FactorySession.openSession();
            item = (Item) session.getByName(item, name);          //com poso la relació game User?¿
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return item;
    }
    //Obtenim l'objecte element a partir del username del usuari propietari
    public Item getItemByUsername(String username) throws SQLException {
        Session session = null;
        Item item = new Item();
        try {
            session = FactorySession.openSession();
            item = (Item) session.getItemByUsername(item, username);          //com poso la relació game User?¿
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return item;
    }

    //Funció per obtenir el ID del element a partir del seu nom                                         //es pot unificar getIDbyNAME
    public int getIdByName(String name) throws SQLException {

        Session session = null;

        int itemID = -1;

        try {

            session = FactorySession.openSession();
            itemID = session.getItemIdByName(name);          //com poso la relació game User?¿

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return itemID;
    }

//Función que devuelve el precio de un objeto
    public int getPriceItem(int id_item) throws SQLException {
        Session session = null;
        int price = 0;
        try {
            session = FactorySession.openSession();
            price = session.getPriceItem(id_item);
        } finally {
            session.close();
        }
        return price;
    }

    /*****************************************  REGISTRE ITEM     *************************************************/
    public boolean registerItem(ItemCredentials itemCredentials) throws SQLException{ //Afegeix el user com a obejcte

        Session session;
        boolean result = false;

        try {

            session = FactorySession.openSession();
            result = session.registerItem(itemCredentials);
            session.close();

        } finally {

        }

        return result;

    }


    /**********************************************************************************************************/

}
