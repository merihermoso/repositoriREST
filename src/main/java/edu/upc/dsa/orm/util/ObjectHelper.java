package edu.upc.dsa.orm.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;
    }

    private static String getSetter(String property){
        String res;
        res="set"+property.substring(0,1).toUpperCase()+property.substring(1);
        return res;
    }

    public static void setter(Object object, String property, Object value){
        Method method = null;

        try{
            if(value.getClass() == Integer.class)
                method = object.getClass().getDeclaredMethod(getSetter(property),int.class);
            else
                method = object.getClass().getDeclaredMethod(getSetter(property),value.getClass());

            method.invoke(object,value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private static String getGetter(String property){
        String res;
        res="get"+property.substring(0,1).toUpperCase()+property.substring(1);
        return res;
    }
    public static int getId(Object entity) throws IllegalAccessException {  /***/

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];

        Field ID = fields[0];
        int id = ID.getInt(entity);

        return id;
    }

    public static Object getter(Object object, String property){     /***/
        Method method = null;
        Object ressult = null;
        try {
            method = object.getClass().getDeclaredMethod(getGetter(property), null); //User.getIdUser()
            ressult = method.invoke(object); //ressult = id
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return ressult;
    }
/*  //default
    public static Object getter(Object object, String property) throws IllegalAccessException {
        Object value = null;
        Class theClass = object.getClass();
        Field[] fields = theClass.getDeclaredFields();
        String[] sFields = new String[fields.length];

        for (Field f: fields) {
            if (f.getName() == property)
                value = f.get(object);
        }

        return value;
    }
    */

}
