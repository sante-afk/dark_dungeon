package handling;
import events.Path;
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

    public static Hero handlingStartGame (Scanner scanner) {
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
                return hero;
            }
        }
        return null;
    }

    public static void handlingPath (Hero hero, Scanner scanner) {
        boolean continuePath = true;

        while (continuePath) {
            Printer.printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: {
                        Path.roll(hero, scanner);
                        continuePath = false;
                        break;
                    }
                    case 2: {
                        Printer.printHero(hero);
                        break;
                    }
                    case 3: {
                        Printer.printLevel(hero);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                Printer.printErrorNoChoice();
            }

        }
    }

    public static double handlingExpReceived (Hero hero, int enemyLvl) {
        DecimalFormat decimalFormat = new DecimalFormat("#.0##");

        if (enemyLvl != 0 && enemyLvl < 10) {
            double enemyHalf = (double)(enemyLvl * 10) / 2 / 10;
            double enemyHalfHalf = enemyHalf / 2;
            double exp = Double.parseDouble(decimalFormat.format(RANDOM.nextDouble(enemyHalfHalf) + 0.1));

            if ( ( (int)(hero.getExp() * 10) + (int)(exp * 10) ) < hero.getExpEnd() ) {
                hero.setExp(Double.parseDouble(decimalFormat.format(hero.getExp() + exp)));
            } else {
                hero.setLevel(hero.getLevel() + 1);
                hero.setMinDamage(RANDOM.nextInt(hero.getMinDamage()) + 1);
                hero.setMaxDamage(RANDOM.nextInt(hero.getMaxDamage()) + 1);
                hero.setExp(0.0);
                hero.setExpEnd(hero.getLevel() * 10);
                if (hero.getHealth() < 100) {
                    hero.setHealth(100, true);
                }
                Printer.printNewLevel(hero.getLevel());
            }
            return exp;
        }
        return 0.0;
    }


}
