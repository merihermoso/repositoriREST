package edu.upc.dsa.orm.dao.element;

//import com.sun.tools.javac.jvm.Items;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;

import java.sql.SQLException;
import java.util.*;

public class ElementDAOImpl implements ElementDAO {
    private static ElementDAO instance;

    private ElementDAOImpl() {
    }

    public static ElementDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new ElementDAOImpl();
        return instance;
    }
    /*****************************************  FUNCIONS GENERALS    ***************************************************/

    public List<Element> findAll(){

        Session session;
        List<Element> elementsList;

        HashMap<Integer, Element> result;

        session = FactorySession.openSession();
        result = session.findAll(Element.class);

        elementsList = new ArrayList<>(result.values());

        session.close();

        return elementsList;
    }
    //Obtenemos la cantidad total de elementos registrados
    public int size() {
        Session session;
        HashMap<String, Element> elements = null;
        try{
            session = FactorySession.openSession();
            elements = session.findAll(Element.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements.size();
    }
    /*****************************************  OBTENIM ELEMENT     ***************************************************/
    //Obtenim l'objecte element a partir del seu ID
    public Element getElementById(int elementID) throws SQLException {
        Session session = null;
        Element element = new Element();
        try {
            session = FactorySession.openSession();
            element = (Element) session.getById(element, elementID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return element;
    }
    //Obtenim l'objecte element a partir del seu nom
    public Element getElementByName(String name) throws SQLException {
        Session session = null;
        Element element = new Element();
        try {
            session = FactorySession.openSession();
            element = (Element) session.getByName(element, name);          //com poso la relació game User?¿
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return element;
    }
    //Obtenim l'objecte element a partir del username del usuari propietari
    public Element getElementByUsername(String username) throws SQLException {
        Session session = null;
        Element element = new Element();
        try {
            session = FactorySession.openSession();
            element = (Element) session.getElementByUsername(element, username);          //com poso la relació game User?¿
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return element;
    }
    //Funció per obtenir el ID del element a partir del seu nom
    public int getElementIdByName(String name) throws SQLException {

        Session session = null;

        int objectID = -1;

        try {

            session = FactorySession.openSession();
            objectID = session.getElementIdByName(name);          //com poso la relació game User?¿

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return objectID;
    }
    /*****************************************  INSERT ELEMENT     ***************************************************/
    //Inserta tot l'objecte ELEMENT passant les dades "amagades"
    public boolean registerElement(ElementCredentials elementCredentials) {

        Session session;
        boolean result = false;

        try {
            session = FactorySession.openSession();
            result = session.registerElement(elementCredentials);
            session.close();

        } finally {
        }
        return result;
    }
    /**********************************************************************************************************/
}
