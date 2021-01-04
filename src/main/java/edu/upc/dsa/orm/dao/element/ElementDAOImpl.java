package edu.upc.dsa.orm.dao.element;

//import com.sun.tools.javac.jvm.Items;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.Orders;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;

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

    public boolean registerElement(ElementCredentials elementCredentials) { //Afegeix el user com a obejcte

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

}
