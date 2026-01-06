abstract class Person {
    public String name;
    public int health;
    public int armor;

    public Person(String name, int health, int armor) {
        this.name = name;
        this.health = health;
        this.armor = armor;
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

    // Setters
    void setName (String name) {
        if (name.length() < 2) {
            System.out.println("Error, name is small");
        }
        this.name = name;
    }
    void setHealth (int health) {
        if (health < 100) {
            System.out.print("Health will be 100 by default ");
            System.out.print("ʕっ•ᴥ•ʔっ");
            this.health = 100;
        } else {
            this.health = health;
        }
    }
    void setArmor (int armor) {
        if (armor < 100) {
            System.out.print("Armor will be 100 by default ");
            System.out.print("ʕっ•ᴥ•ʔっ");
            this.armor = 100;
        } else {
            this.armor = armor;
        }
    }
}
