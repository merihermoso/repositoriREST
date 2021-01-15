package edu.upc.dsa.orm.models.API;

public class ItemProfile {

    private String name;
    private int hit;
    private int defense;
    private int healing;
    private int damage;
    private int price;
    private String description;
    private String image;

    public ItemProfile() {

    }

    public ItemProfile(String name, int hit, int defense, int healing, int damage, int price, String description, String image) {
        this.name = name;
        this.hit = hit;
        this.defense = defense;
        this.healing = healing;
        this.damage = damage;
        this.price = price;
        this.description = description;
        this.image = image;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getHit() { return hit; }
    public void setHit(int hit) { this.hit = hit; }

    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }

    public int getHealing() { return healing; }
    public void setHealing(int healing) { this.healing = healing; }

    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }


}
