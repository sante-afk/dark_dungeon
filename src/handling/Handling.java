package handling;

import events.Fight;
import printer.Printer;
import races.Hero;
import create.Create;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

import static printer.Printer.printStartGame;

public class Handling {

    private static final Random RANDOM = new Random();


    public static int handlingEnemies () {
        File dir = new File("src/Enemies");
        File[] arrFiles = dir.listFiles();
        if (arrFiles != null){
            return arrFiles.length - 1;
        }
        return 0;
    }

    public static void handlingStartGame (Scanner scanner) {
        String enterGame = scanner.nextLine().trim();
        boolean enter = true;
        boolean create = false;
        int race = 0;

        while (enter) {
            try {
                if (enterGame.equals("Yes") || enterGame.isEmpty()) {
                    System.out.println("Choose a race ");
                    System.out.print("Human - 1, ");
                    System.out.print("Elf - 2, ");
                    System.out.print("Dwarf - 3: ");
                    race = Integer.parseInt(scanner.nextLine());
                    create = true;
                    enter = false;
                } else if (enterGame.equals("No")) {
                    System.out.println("bye bye ( ╥﹏╥) ノシ");
                    break;
                } else {
                    System.out.println("what? (ㆆ _ ㆆ)\n");
                    printStartGame();
                    enterGame = scanner.nextLine().trim();
                }
            } catch (NumberFormatException e) {
                Printer.printErrorChoiceString();
            }
        }
        while (create) {
            Hero hero = null;

            try {
                hero = Create.CreateHero(race, scanner);
            } catch (NumberFormatException e) {
                Printer.printErrorHealthOrArmor();
            } catch (IllegalArgumentException e) {
                Printer.printErrorName();
            }

            if (hero != null) {
                Printer.printHero(hero);
                Fight.Fight(hero, scanner);
            }
        }

    }

    public static double handlingExpReceived (Hero hero, double heroExp, int enemyLvl) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0##");

        if (enemyLvl != 0 && enemyLvl < 10) {
            double enemyHalf = enemyLvl / 2;
            double enemyHalfHalf = enemyHalf / 2;
            double exp = Double.parseDouble(decimalFormat.format(RANDOM.nextDouble(0.1, enemyHalfHalf) + 0.1));

            int heroLvl = hero.getLevel();
            if (heroExp < Double.parseDouble(String.valueOf(heroLvl))) {
                hero.setExp(heroExp + exp);
            } else {
                hero.setLevel(heroLvl + 1);
            }
            return exp;
        }
        return 0.0;
    }


}
