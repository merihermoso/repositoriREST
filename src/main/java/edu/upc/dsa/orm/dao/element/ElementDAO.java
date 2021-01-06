package edu.upc.dsa.orm.dao.element;

import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;

import java.sql.SQLException;
import java.util.*;

public interface ElementDAO {
    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Element> findAll();
    public int size();

    /*****************************************  OBTENIM ELEMENT     ***************************************************/
    public Element getElementById(int elementID) throws SQLException;
    public Element getElementByName(String name) throws SQLException;
    public Element getElementByUsername(String username) throws SQLException;

    /*****************************************  registre ELEMENT     ***************************************************/
    boolean registerElement(ElementCredentials elementCredentials);


}
