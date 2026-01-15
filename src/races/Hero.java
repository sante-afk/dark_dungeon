package races;

import java.util.Random;

public abstract class Hero {
    private static final Random RANDOM = new Random();
    private double exp;
    private int expEnd;
    private String name;
    private int health;
    private int armor;
    private String race;

    private int level;
    private int minDamage;
    private int maxDamage;

    public Hero(String name, int health, int armor, String race, int level,
                double exp, int expEnd, int minDamage, int maxDamage) {
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.race = race;

        this.level = level;
        this.exp = exp;
        this.expEnd = expEnd;

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
    public double getExp () {
        return exp;
    }
    public int getExpEnd () {
        return expEnd;
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
        if (name.length() < 2) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
    public void setHealth (int health, boolean beginSet) {
        if (health < 100 && beginSet) {
            System.out.print("\nNotification: health will be 100 by default ");
            System.out.print("ʕっ•ᴥ•ʔっ\n");
            this.health = 100;
        } else {
            this.health = health;
        }
    }
    public void setArmor (int armor, boolean beginSet) {
        if (armor < 100 && beginSet) {
            System.out.print("Notification: armor will be 100 by default ");
            System.out.print("ʕっ•ᴥ•ʔっ\n");
            this.armor = 100;
        } else {
            this.armor = armor;
        }
    }
    public void setRace (String race) {
        this.race = race;
    }
    public void setLevel (int level) {
        this.level = level;
    }
    public void setExp (double exp) {
        this.exp = exp;
    }
    public void setExpEnd (int expEnd) {
        this.expEnd = expEnd;
    }
    public void setMinDamage (int minDamage) {
        this.minDamage = minDamage;
    }
    public void setMaxDamage (int maxDamage) {
        this.maxDamage = maxDamage;
    }

    // Methods
    public int physDamage (int minDamage, int maxDamage) {
        return RANDOM.nextInt(minDamage, maxDamage + 1);
    }
}
