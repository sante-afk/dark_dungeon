package events;

import enemies.Enemy;
import handling.Handling;
import menu.Menu;
import printer.Printer;
import races.Hero;
import replicas.Replica;
import create.Create;

import java.util.Random;
import java.util.Scanner;

public class Fight {

    private static final Random RANDOM = new Random();

    public static void Fight (Hero hero, Scanner scanner) {
        Enemy enemy = Create.CreateEnemy(hero);

        boolean met = RANDOM.nextBoolean();
        boolean tour = RANDOM.nextBoolean();

        System.out.println(Replica.replicaMet(enemy, met));

        if (enemy != null) {
            Printer.printEnemy(enemy);
        }

        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println(Replica.replicaTour(tour));

            if (tour) {
                Menu.menuFight();
                try {
                    int choice = Integer.parseInt(scanner.nextLine());
                    if (choice == 1) {
                        int heroDamage = hero.physDamage(hero.getMinDamage(), hero.getMaxDamage());
                        enemy.setHealth(enemy.getHealth() - heroDamage);
                        System.out.println("\uD83D\uDDE1 You deal " + heroDamage + " damage!");
                        System.out.println(enemy.getName() + " has " + enemy.getHealth() + " HP left \n");
                        if (enemy.getHealth() <= 0) {
                            System.out.println("☠ " + enemy.getName() + " defeated!");
                            int enemyLvl = enemy.getLevel();
                            double receivedExp = Handling.handlingExpReceived(hero, enemyLvl);
                            System.out.println("EXP " + Math.round(receivedExp * 1000) + " received ");
                            break;
                        }
                        tour = false;
                    }
                    if (choice == 2) {
                        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
                            int heroDamage = hero.physDamage(hero.getMinDamage(), hero.getMaxDamage());
                            enemy.setHealth(enemy.getHealth() - heroDamage);
                            System.out.println("\uD83D\uDDE1 You deal " + heroDamage + " damage!");
                            System.out.println(enemy.getName() + " has " + enemy.getHealth() + " HP left \n");
                            if (enemy.getHealth() <= 0) {
                                System.out.println("☠ " + enemy.getName() + " defeated!");
                                int enemyLvl = enemy.getLevel();
                                double receivedExp = Handling.handlingExpReceived(hero, enemyLvl);
                                System.out.println("EXP " + Math.round(receivedExp * 1000) + " received ");
                                break;
                            }
                            int enemyDamage = enemy.physDamage(enemy.getMinDamage(), enemy.getMaxDamage());
                            int damageDeduction = hero.getHealth() - enemyDamage;
                            hero.setHealth(damageDeduction, false);
                            System.out.println("\uD83D\uDDE1 Enemy deal " + enemyDamage + " damage!");
                            System.out.println(hero.getName() + " has " + hero.getHealth() + " HP left \n");
                            if (hero.getHealth() <= 0) {
                                Printer.printGameOwer(scanner);
                                break;
                            }
                        }
                    }
                    if (choice != 1 && choice != 2) {
                        Printer.printErrorNoChoice();
                    }
                } catch (NumberFormatException e) {
                    Printer.printErrorChoiceString();
                }
            }
            if (!tour) {
                int enemyDamage = enemy.physDamage(enemy.getMinDamage(), enemy.getMaxDamage());
                int damageDeduction = hero.getHealth() - enemyDamage;
                hero.setHealth(damageDeduction, false);
                System.out.println("\uD83D\uDDE1 Enemy deal " + enemyDamage + " damage!");
                System.out.println(hero.getName() + " has " + hero.getHealth() + " HP left \n");
                if (hero.getHealth() <= 0) {
                    Printer.printGameOwer(scanner);
                    break;
                }
                tour = true;
            }
        }
    }

}
