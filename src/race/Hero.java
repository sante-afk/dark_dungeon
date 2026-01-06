package race;

public abstract class Hero {
    public String name;
    public int health;
    public int armor;
    public String race;

    public Hero(String name, int health, int armor, String race) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.race = race;
    }

    // Getters
    public String getName() {
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
    public void setName(String name) {
        if (name.length() < 2) {
            throw new IllegalArgumentException("Error, name is small " + name);
        }
        this.name = name;
    }
    public void setHealth(int health) {
        if (health < 100) {
            System.out.print("Health will be 100 by default ");
            System.out.print("ʕっ•ᴥ•ʔっ\n");
            this.health = 100;
        } else {
            this.health = health;
        }
    }
    public void setArmor(int armor) {
        if (armor < 100) {
            System.out.print("Armor will be 100 by default ");
            System.out.print("ʕっ•ᴥ•ʔっ\n");
            this.armor = 100;
        } else {
            this.armor = armor;
        }
    }
}
