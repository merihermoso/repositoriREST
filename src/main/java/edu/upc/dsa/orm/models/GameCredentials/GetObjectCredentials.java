package edu.upc.dsa.orm.models.GameCredentials;

public class GetObjectCredentials {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Object { name:" +name+"}";
    }
}
