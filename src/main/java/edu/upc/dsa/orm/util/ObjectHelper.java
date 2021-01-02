package edu.upc.dsa.orm.util;

import java.lang.reflect.Field;

public class ObjectHelper {
    public static String[] getFields(Object entity) {

        Class theClass = entity.getClass();

        Field[] fields = theClass.getDeclaredFields();

        String[] sFields = new String[fields.length];
        int i=0;

        for (Field f: fields) sFields[i++]=f.getName();

        return sFields;
    }

    public static void setter(Object object, String property, Object value){
        //Method            //invoke
        //property="name";
        //value = "toni"
        //Employee e =
        //        e.setName("toni")

    }
    public static Object getter(Object object, String property){
        //Method        //invoke

        return null;
    }
}
