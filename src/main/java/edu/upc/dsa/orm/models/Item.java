package edu.upc.dsa.orm.models;


public class Item {     //classe per objectes DINS del inventari    (objectes comprats a la botiga o conseguits al joc)

    private int id; //Asignamos un id al name creado
    private String name;
    private int hit;
    private int defense;
    private int healing;
    private int damage;
    private String description;
    private int price;
    private String image;

    public Item() {

    }

    public Item(String name, int hit, int defense, int healing, int damage,  int price, String description, String image) {

        setName(name);
        setHit(hit);
        setDefense(defense);
        setHealing(healing);
        setDamage(damage);
        setDescription(description);
        setPrice(price);
        setImage(image);            //s'hauria de linkear Media/foto ????
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {        return name;    }
    public void setName(String item) {
        this.name = item;
    }

    public int getHit() {
        return hit;
    }
    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealing() {
        return healing;
    }
    public void setHealing(int healing) {
        this.healing = healing;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getPrice() {        return price;    }
    public void setPrice(int price) {        this.price = price;    }
    public String getDescription() {        return description;    }
    public void setDescription(String description) {        this.description = description;    }

    public String getImage() {        return image;    }
    public void setImage(String image) {        this.image = image;    }

    @Override
    public String toString(){
        return "User { id: "+id+",Name:" +name+", Hit:"+hit+", Defense:"+defense+", Healing:"+healing+ ", Damage:"+damage+"}";
    }

}
