package enemies;

import java.util.Random;

public class Enemy {
    private static final Random RANDOM = new Random();
    private String name;
    private int health;
    private int armor;
    private String race;

    private int level;
    private int minDamage;
    private int maxDamage;

    public Enemy (String name, int health, int armor, String race, int level, int minDamage, int maxDamage) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.race = race;

        this.level = level;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
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

    public int getLevel () {
        return level;
    }
    public int getMinDamage () {
        return minDamage;
    }
    public int getMaxDamage () {
        return maxDamage;
    }

    // Setters
    public void setName (String name) {
        this.name = name;
    }
    public void setHealth (int health) {
        this.health = health;
    }
    public void setArmor (int armor) {
        this.armor = armor;
    }
    public void setRace (String race) {
        this.race = race;
    }
    public void setLevel (int level) {
        this.level = level;
    }
    public void setMinDamage (int minDamage) {
        this.minDamage = minDamage;
    }
    public void setMaxDamage (int maxDamage) {
        this.maxDamage = maxDamage;
    }

    // Methods
    public int physDamage (int minDamage, int maxDamage) {
        return RANDOM.nextInt(minDamage, maxDamage) + 1;
    }

}
