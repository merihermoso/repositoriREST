package edu.upc.dsa.orm.dao.element;

//import com.sun.tools.javac.jvm.Items;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Element;

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


}
