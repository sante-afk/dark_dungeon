public class Elf extends Person {

    public Elf(String name, int health, int armor) {
        super(name, health, armor);
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public int getArmor() {
        return armor;
    }
}
