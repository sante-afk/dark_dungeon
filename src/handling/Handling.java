package handling;
import events.Path;
import printer.Printer;
import races.Hero;
import frames.CreateHero;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void handlingCreateHero (
            String[] nameHero,
            int[] healthHero,
            int[] armorHero,
            String race,
            int level,
            double exp,
            int expEnd,
            int minDamage,
            int maxDamage) {
        File hero = new File("data/hero.txt");

        try {
            hero.createNewFile();
            FileWriter write = new FileWriter(hero);
            write.write(nameHero[0]);
            write.write(healthHero[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                    case 4: {
                        if (hero.getHealth() >= 100) {
                            Printer.printDontRelax();
                        } else {
                            int healthPoint = handlingHealing(hero);
                            handlingArmorRegen(hero);
                            Printer.printRelax(healthPoint);
                        }
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
                hero.setMinDamage(hero.getMinDamage() + RANDOM.nextInt(hero.getMinDamage() + 1)) ;
                hero.setMaxDamage(hero.getMaxDamage() + RANDOM.nextInt(hero.getMaxDamage() + 1));
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

    public static int handlingHealing (Hero hero) {
        int heroHealth = hero.getHealth();
        int healthPoint = 100 - heroHealth;
        if (heroHealth < 100) {
            hero.setHealth(100, true);
        }
        return healthPoint;
    }

    public static void handlingArmorRegen (Hero hero) {
        hero.setArmor(100, true);
    }

}
