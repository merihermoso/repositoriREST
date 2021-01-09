package edu.upc.dsa.orm.models.adminCredentials;

public class ChangePlayerCoins {

    private String username;
    private int newCoins;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getNewCoins() {
        return newCoins;
    }
    public void setNewCoins(int newCoins) { this.newCoins = newCoins; }
                                                     //s'hauria de posar que nomes aumenti la quantitat que indroduim
                                                    // FER ADDcoins enlloc de new coins
}
