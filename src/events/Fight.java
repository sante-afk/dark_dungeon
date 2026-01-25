package events;

import enemies.Enemy;
import handling.Handling;
import frames.Menu;
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
            if (tour) {
                boolean autoFight = false;
                if (!autoFight) {
                    int choice = 0;
                    while (choice == 0) {
                        try {
                            System.out.println(Replica.replicaTour(tour));
                            Menu.menuFight();
                            choice = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            Printer.printErrorNoChoice();
                        }
                    }
                    TourHero(scanner, hero, enemy, autoFight, choice);
                }
                tour = false;
            }
            if (!tour && enemy.getHealth() > 0) {
                TourEnemy(scanner, hero, enemy);
                tour = true;
            }
        }
    }
    public static void TourHero (Scanner scanner, Hero hero, Enemy enemy, boolean autoFight, int choice) {
        try {
            if (choice == 1 || autoFight) {
                boolean chanceInDefense = RANDOM.nextBoolean();
                int heroDamage = hero.physDamage(hero.getMinDamage(), hero.getMaxDamage());
                int enemyDefense = 0;
                if (enemy.getArmor() < 0) {
                    enemyDefense = 0;
                } else {
                    enemyDefense = RANDOM.nextInt(0, enemy.getArmor() + 1);
                }
                if (chanceInDefense) {
                    if (enemyDefense >= enemy.getHealth() && enemy.getHealth() != 0 && enemy.getArmor() != 0) {
                        enemy.setArmor(enemyDefense - heroDamage);
                        System.out.println("⛊ The monster defended itself ⛊");
                    } else {
                        int armoryChanceDefence = enemy.getHealth() - enemyDefense;
                        int chanceInDefenseHalf = RANDOM.nextInt(0, enemy.getHealth() + 1);
                        if (chanceInDefenseHalf < armoryChanceDefence) {
                            double halfDamage = (double)heroDamage / 2;
                            enemy.setHealth(enemy.getHealth() - (int)halfDamage);
                            System.out.println("The monster partially defended itself ⛊");
                            System.out.println("\uD83D\uDDE1 You deal " + (int)halfDamage + " damage!");
                            System.out.println(enemy.getName() + " has ( " + enemy.getHealth() + " ♥ " + enemy.getArmor() + " ⛊ " + ") " + "\n");
                        } else {
                            enemy.setHealth(enemy.getHealth() - heroDamage);
                            System.out.println("\uD83D\uDDE1 You deal " + heroDamage + " damage!");
                            System.out.println(enemy.getName() + " has ( " + enemy.getHealth() + " ♥ )\n");
                        }
                    }
                } else {
                    enemy.setHealth(enemy.getHealth() - heroDamage);
                    System.out.println("\uD83D\uDDE1 You deal " + heroDamage + " damage!");
                    System.out.println(enemy.getName() + " has ( " + enemy.getHealth() + " ♥ )\n");
                }
                if (enemy.getHealth() <= 0) {
                    System.out.println("☠ " + enemy.getName() + " defeated!");
                    int enemyLvl = enemy.getLevel();
                    double receivedExp = Handling.handlingExpReceived(hero, enemyLvl);
                    System.out.println("EXP " + Math.round(receivedExp * 1000) + " received \n");
                }
            }
            if (choice == 2) {
                AutoFight(scanner, hero, enemy);
            }
        } catch (NumberFormatException e) {
            Printer.printErrorChoiceString();
        }
    }

    public static void TourEnemy (Scanner scanner, Hero hero, Enemy enemy) {
        boolean chanceInDefense = RANDOM.nextBoolean();
        int heroDefense = 0;
        if (hero.getArmor() < 0) {
            heroDefense = 0;
        } else {
            heroDefense = RANDOM.nextInt(0, hero.getArmor() + 1);
        }
        int enemyDamage = enemy.physDamage(enemy.getMinDamage(), enemy.getMaxDamage());
        if (chanceInDefense) {
            if (heroDefense >= hero.getHealth() && hero.getHealth() != 0 && hero.getArmor() !=0 ) {
                int heroArmor = hero.getArmor() - enemyDamage;
                hero.setArmor(heroArmor, false);
                System.out.println("⛊ You were able to defend yourself ⛊");
            } else {
                int armoryChanceDefence = hero.getHealth() - heroDefense;
                int chanceInDefenseHalf = RANDOM.nextInt(0, hero.getHealth() + 1);
                if (chanceInDefenseHalf < armoryChanceDefence) {
                    double halfDamage = (double)enemyDamage / 2;
                    hero.setHealth((hero.getHealth() - (int)halfDamage), false);
                    System.out.println("You were able to partially defend yourself ⛊");
                    System.out.println("\uD83D\uDDE1 Enemy deal " + (int)halfDamage + " damage!");
                    System.out.println(hero.getName() + " has ( " + hero.getHealth() + " ♥ " + hero.getArmor() + " ⛊ " + ") " + "\n");
                } else {
                    int damageDeduction = hero.getHealth() - enemyDamage;
                    hero.setHealth(damageDeduction, false);
                    System.out.println("\uD83D\uDDE1 Enemy deal " + enemyDamage + " damage!");
                    System.out.println(hero.getName() + " has ( " + hero.getHealth() + " ♥ )\n");
                }
            }
        } else {
            int damageDeduction = hero.getHealth() - enemyDamage;
            hero.setHealth(damageDeduction, false);
            System.out.println("\uD83D\uDDE1 Enemy deal " + enemyDamage + " damage!");
            System.out.println(hero.getName() + " has ( " + hero.getHealth() + " ♥ )\n");
        }
        if (hero.getHealth() <= 0) {
            Printer.printGameOwer(scanner);
        }
    }

    public static void AutoFight (Scanner scanner, Hero hero, Enemy enemy) {
        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            boolean autoFight = true;
            if (hero.getHealth() > 0) {
                TourHero(scanner, hero, enemy, autoFight, 1);
            }
            if (enemy.getHealth() > 0) {
                TourEnemy(scanner, hero, enemy);
            }
        }
    }

}
