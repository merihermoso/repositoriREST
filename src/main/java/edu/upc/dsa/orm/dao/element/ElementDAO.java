package edu.upc.dsa.orm.dao.element;

import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;

import java.sql.SQLException;
import java.util.*;

public interface ElementDAO {

    public Element getElementById(int elementID) throws SQLException;
    public Element getElementByName(String name) throws SQLException;

    public Element getElementByUsername(String username) throws SQLException;

    public List<Element> findAll();


    boolean registerElement(ElementCredentials elementCredentials);
}
