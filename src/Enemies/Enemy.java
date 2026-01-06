package Enemies;

public class Enemy {
    public String name;
    public int health;
    public int armor;
    public String race;

    public Enemy(String name, int health, int armor, String race){
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.race = race;
    }

    // Getters
    public String getName () {
        return name;
    }
    public int getHealth () {
        return health;
    }
    public int getArmor () {
        return armor;
    }
    public String getRace () {
        return race;
    }

    // Setters
    public void setName (String name) {
        this.name = name;
    }
    public void setHealth (int health) {
        this.health = health;
    }
    public void setArmor(int armor) {
        this.armor = armor;
    }
    public void setRace(String race) {
        this.race = race;
    }
}
