package Printer;

import Enemies.Enemy;
import Race.Hero;

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

    public static void printEnemy (Enemy enemy) {
        System.out.println(
                enemy.getRace() +
                        " ( " + enemy.getLevel() + " lvl" + " ) " +
                        "\nDamage ( " + enemy.getMinDamage() + " - " + enemy.getMaxDamage() + " ⚔ )" +
                        "\nHealth ( " + enemy.getHealth() + " ♥ ) " +
                        "\nArmor ( " + enemy.getArmor() + " ⛊ ) \n");
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
