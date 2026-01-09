package printer;

import enemies.Enemy;
import races.Hero;

import javax.swing.*;

public class Printer {

    public static void printLogo() {
        System.out.println("""
                
                ▄▄▄▄   ▄▄▄  ▄▄▄▄  ▄▄ ▄▄   ▄▄▄▄  ▄▄ ▄▄ ▄▄  ▄▄  ▄▄▄▄ ▄▄▄▄▄  ▄▄▄  ▄▄  ▄▄\s
                ██▀██ ██▀██ ██▄█▄ ██▄█▀   ██▀██ ██ ██ ███▄██ ██ ▄▄ ██▄▄  ██▀██ ███▄██\s
                ████▀ ██▀██ ██ ██ ██ ██   ████▀ ▀███▀ ██ ▀██ ▀███▀ ██▄▄▄ ▀███▀ ██ ▀██\s
                                                                                     \s""");
    }

    public static void printStartGame() {
        System.out.print("Start the game (Yes/No) ?: ");
    }

    public static void printHero (Hero hero) {
        System.out.println(
                "\n" + hero.getName() + " " + hero.getRace() +
                        " ( " + hero.getLevel() + " lvl" + " ) " +
                        "\nDamage ( " + hero.getMinDamage() + " - " + hero.getMaxDamage() + " ⚔ )" +
                        "\nHealth ( " + hero.getHealth() + " ♥ ) " +
                        "\nArmor ( " + hero.getArmor() + " ⛊ ) \n");

    }

    public static void printLevel (Hero hero) {
        int exp = (int)(hero.getExp() * 10);
        int expEnd = hero.getExpEnd();
        StringBuilder progressBar = new StringBuilder();
        int count = progressBar.length();

        for (int i = 0; i < exp; i++) {
            progressBar.append("█");
            ++count;
        }

        for (int j = count; j < expEnd; j++) {
            progressBar.append("▒");
        }

        System.out.println("\nlevel - " + hero.getLevel());
        System.out.println(progressBar);
        System.out.println(hero.getExp() + "\n");
        progressBar.setLength(0);
    }

    public static void printHistory (Hero hero) {
        System.out.println(
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

    public static void printErrorChoiceString () {
        System.out.println("\nError, there is no such choice");
    }
    public static void printErrorNoChoice () {
        System.out.println("\nError, there is no such choice");
    }
    public static void printErrorName () {
        System.out.println("\nError, name is small");
    }
    public static void printErrorHealthOrArmor () {
        System.out.println("\nError, health or armor cannot be symbols");
    }
}
