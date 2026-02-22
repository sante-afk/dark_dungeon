package events;

import enemies.Enemy;
import handling.Handling;
import printer.Printer;
import races.Hero;
import replicas.Replica;

import java.util.ArrayList;
import java.util.Random;

public class Fight {

    private static final Random RANDOM = new Random();

    public static ArrayList<String> Fight (Hero hero, Enemy enemy, int[] choice, boolean autoFight) {

        boolean met = RANDOM.nextBoolean();
        boolean tour = RANDOM.nextBoolean();

        ArrayList<String> fight = new ArrayList<>();
        
        String replica = Replica.replicaMet(enemy, met);
        fight.add(replica);

        if (enemy != null) {
            Printer.printEnemy(enemy);
        }

        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            if (tour) {
                if (!autoFight) {
                    String[] tourHero = TourHero(hero, enemy, choice, autoFight);
                }
                tour = false;
            }
            if (!tour && enemy.getHealth() > 0) {
                String[] tourEnemy = TourEnemy(hero, enemy, choice, autoFight);
                tour = true;
            }
        }
        return fight;
    }
    public static String[] TourHero (Hero hero, Enemy enemy, int[] choice, boolean autoFight) {
        try {
            if (choice[0] == 1 || autoFight) {
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
            if (choice[0] == 2) {
                AutoFight(hero, enemy, choice);
            }
        } catch (NumberFormatException e) {
            Printer.printErrorChoiceString();
        }
        return new String[0];
    }

    public static String[] TourEnemy (Hero hero, Enemy enemy, int[] choice, boolean autoFight) {
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
//            Printer.printGameOwer(scanner);
        }
        return null;
    }

    public static void AutoFight (Hero hero, Enemy enemy, int[] choice) {
        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
            boolean autoFight = true;
            choice = new int[]{1};
            if (hero.getHealth() > 0) {
                TourHero(hero, enemy, choice, autoFight);
            }
            if (enemy.getHealth() > 0) {
                TourEnemy(hero, enemy, choice, autoFight);
            }
        }
    }

}
