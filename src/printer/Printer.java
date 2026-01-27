package printer;

import enemies.Enemy;
import handling.Handling;
import frames.Menu;
import races.Hero;

import java.util.Scanner;

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

    public static void printLevel (Hero hero) {
        int paintBar = 10;
        int exp = (int)(hero.getExp() * 10);
        StringBuilder progressBar = new StringBuilder();
        int count = 0;
        int expPersent = (exp * 10) / paintBar / hero.getLevel();

        for (int i = 0; i < expPersent; i++) {
            progressBar.append("█");
            ++count;
        }

        for (int j = count; j < paintBar; j++) {
            progressBar.append("▒");
        }

        System.out.println("\nlevel - " + hero.getLevel());
        System.out.println(progressBar);
        System.out.println((int)(hero.getExp() * 1000) + " - " + (hero.getExpEnd()) * 100 + "\n");
        progressBar.setLength(0);
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


    public static void printRelax (int healthPoint) {
        System.out.println("\nYou relaxed! Repaired: " + healthPoint + " HP " + " (｡◕‿‿◕｡) \n");
    }

    public static void printDontRelax () {
        System.out.println("\nYour character does not require recovery (︶︹︶)\n");
    }

    public static void printGameOwer(Scanner scanner) {
        System.out.println("† Game over!\n");
        boolean game = true;

        while (game) {
            try {
                System.out.print("Start new game (Yes/No) ?: ");
                String chose = scanner.nextLine();

                if (chose.equals("Yes") || chose.isEmpty()) {
                    Hero hero = Handling.handlingStartGame(scanner);
                    Menu.menuBegin(hero, scanner);
                    game = false;
                }
                if (chose.equals("No")) {
                    System.out.println("bye bye ( ╥﹏╥) ノシ");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nError, there is no such choice");
            }
        };
    }

    public static void printMenuFight () {
        System.out.println("1. Physical Attack ");
        System.out.println("2. Auto Attack ");
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
