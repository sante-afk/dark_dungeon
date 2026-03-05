package printer;

import enemies.Enemy;
import races.Hero;

public class Printer {
    public static String printLogo() {
        return "DARK DUNGEON";
    }
    public static String printStartGame() {
        return "Start the game";
    }
    public static String printMenuYes() {
        return "Yes";
    }
    public static String printMenuOK() {
        return "OK";
    }
    public static String printMenuNo() {
        return "No";
    }
    public static void printHero (Hero hero) {
        System.out.println(
                "\n" + hero.getName() + " " + hero.getRace() +
                        " ( " + hero.getLevel() + " lvl" + " ) " +
                        "\nDamage ( " + hero.getMinDamage() + " - " + hero.getMaxDamage() + " ⚔ )" +
                        "\nHealth ( " + hero.getHealth() + " ♥ ) " +
                        "\nArmor ( " + hero.getArmor() + " ⛊ ) \n");

    }
    public static String printLevel (Hero hero) {
        int paintBar = 10;
        int exp = (int)(hero.getExp() * 10);
        StringBuilder progressBar = new StringBuilder();
        int count = 0;
        int expPersent = (exp * 10) / paintBar / hero.getLevel();

        for (int i = 0; i < expPersent; i++) {
            progressBar.append("◆");
            ++count;
        }

        for (int j = count; j < paintBar; j++) {
            progressBar.append("◇");
        }
        return ("\nlevel - " + hero.getLevel() + "\n"
                + progressBar + "\n"
                + (int)(hero.getExp() * 1000) + " - " + (hero.getExpEnd()) * 100 + "\n");
    }
    public static String printHistory (Hero hero) {
        return (
            "The darkness receded slowly, reluctantly, like thick molasses, and \n" +
            "the first thing " + hero.getName() + " felt was not light or sound, but cold. \n" +
            "The sharp, bone-chilling cold of the stone slabs, biting into his cheek. \n" +
            "He opened his eyes, and instead of the usual ceiling with a spiderweb \n" +
            "crack at the corner, a wall hung over him. It was rough, gray, \n" +
            "and made of gigantic blocks that felt wet to the touch. \n" +
            "He was lying on the floor of a similar corridor that \n" +
            "stretched away into pitch-blackness in both directions. \n" +
            "The air smelled of dust, dampness, and something ancient and forgotten, \n" +
            "like the scent of fear that had soaked into stone for millennia.\n");
    }
    public static void printMenu () {
        System.out.println("1. Continue on your way");
        System.out.println("2. View character");
        System.out.println("3. View level");
        System.out.println("4. To take a break");
    }
    public static void printEnemy (Enemy enemy) {
        System.out.println(
                enemy.getRace() +
                        " ( " + enemy.getLevel() + " lvl" + " ) " +
                        "\nDamage ( " + enemy.getMinDamage() + " - " + enemy.getMaxDamage() + " ⚔ )" +
                        "\nHealth ( " + enemy.getHealth() + " ♥ ) " +
                        "\nArmor ( " + enemy.getArmor() + " ⛊ ) \n");
    }
    public static void printNewLevel (double heroLvl) {
        System.out.println("★ New level " + (int)heroLvl + " (◡_◡) ᕤ ");
    }
    public static String printRelax (int healthPoint) {
        return "\nYou relaxed! Repaired: " + healthPoint + " HP " + " (｡◕‿‿◕｡) \n";
    }
    public static String printDontRelax () {
        return "\nYour character does not require recovery (︶︹︶)\n";
    }
    public static String printGameOwer() {
        return "† Game over!";
    }
    public static void printMenuFight () {
        System.out.println("1. Physical Attack ");
        System.out.println("2. Auto Attack ");
    }
    public static String printHuman () {
        return "Human";
    }
    public static String printElf () {
        return "Elf";
    }
    public static String printDwarf () {
        return "Dwarf";
    }
    public static void printErrorChoiceString () {
        System.out.println("\nError, there is no such choice");
    }
    public static void printErrorNoChoice () {
        System.out.println("\nError, there is no such choice");
    }
    public static String printErrorName () {
        return "Error, name is small";
    }
    public static void printErrorHealthOrArmor () {
        System.out.println("\nError, health or armor cannot be symbols");
    }
}
