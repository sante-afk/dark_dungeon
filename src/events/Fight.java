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

        if (tour) {
            if (!autoFight) {
                ArrayList<String> tourHero = TourHero(hero, enemy, choice, autoFight);
                fight.addAll(tourHero);
            }
            tour = false;
        }

        if (!tour && enemy.getHealth() > 0) {
            ArrayList<String> tourEnemy = TourEnemy(hero, enemy, choice, autoFight);
            fight.addAll(tourEnemy);
            tour = true;
        }

        return fight;
    }
    public static ArrayList<String> TourHero (Hero hero, Enemy enemy, int[] choice, boolean autoFight) {
        ArrayList<String> tourHero = new ArrayList<>();

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
                        ArrayList<String> monsterDegended = new ArrayList<>();
                        monsterDegended.add("⛊ The monster defended itself ⛊");
                        return monsterDegended;
                    } else {
                        int armoryChanceDefence = enemy.getHealth() - enemyDefense;
                        int chanceInDefenseHalf = RANDOM.nextInt(0, enemy.getHealth() + 1);
                        if (chanceInDefenseHalf < armoryChanceDefence) {
                            double halfDamage = (double)heroDamage / 2;
                            enemy.setHealth(enemy.getHealth() - (int)halfDamage);
                            tourHero.add("The monster partially defended itself ⛊" +
                                    "\n" + "\uD83D\uDDE1 You deal " + (int)halfDamage + " damage!" +
                                    "\n" + enemy.getName() + " has ( " + enemy.getHealth() + " ♥ " + enemy.getArmor() + " ⛊ " + ") " + "\n");
                            if (enemy.getHealth() <= 0) {
                                tourHero.add("☠ " + enemy.getName() + " defeated!");
                                int enemyLvl = enemy.getLevel();
                                double receivedExp = Handling.handlingExpReceived(hero, enemyLvl);
                                tourHero.add("EXP " + Math.round(receivedExp * 1000) + " received \n");
                                return tourHero;
                            }
                            return tourHero;
                        } else {
                            enemy.setHealth(enemy.getHealth() - heroDamage);
                            tourHero.add(
                                    "\uD83D\uDDE1 You deal " + heroDamage + " damage!" +
                                    "\n" + enemy.getName() + " has ( " + enemy.getHealth() + " ♥ )\n");
                            if (enemy.getHealth() <= 0) {
                                tourHero.add("☠ " + enemy.getName() + " defeated!");
                                int enemyLvl = enemy.getLevel();
                                double receivedExp = Handling.handlingExpReceived(hero, enemyLvl);
                                tourHero.add("EXP " + Math.round(receivedExp * 1000) + " received \n");
                                return tourHero;
                            }
                            return tourHero;
                        }
                    }
                } else {
                    enemy.setHealth(enemy.getHealth() - heroDamage);
                    tourHero.add(
                            "\uD83D\uDDE1 You deal " + heroDamage + " damage!" +
                                    "\n" + enemy.getName() + " has ( " + enemy.getHealth() + " ♥ )\n");
                    if (enemy.getHealth() <= 0) {
                        tourHero.add("☠ " + enemy.getName() + " defeated!");
                        int enemyLvl = enemy.getLevel();
                        double receivedExp = Handling.handlingExpReceived(hero, enemyLvl);
                        tourHero.add("EXP " + Math.round(receivedExp * 1000) + " received \n");
                        return tourHero;
                    }
                    return tourHero;
                }

            }
            if (choice[0] == 2) {
//                AutoFight(hero, enemy, choice);
            }
        } catch (NumberFormatException e) {
            Printer.printErrorChoiceString();
        }
        return tourHero;
    }

    public static ArrayList<String> TourEnemy (Hero hero, Enemy enemy, int[] choice, boolean autoFight) {
        boolean chanceInDefense = RANDOM.nextBoolean();
        ArrayList<String> tourEnemy = new ArrayList<>();
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
                tourEnemy.add("⛊ You were able to defend yourself ⛊");
                return tourEnemy;
            } else {
                int armoryChanceDefence = hero.getHealth() - heroDefense;
                int chanceInDefenseHalf = RANDOM.nextInt(0, hero.getHealth() + 1);
                if (chanceInDefenseHalf < armoryChanceDefence) {
                    double halfDamage = (double)enemyDamage / 2;
                    hero.setHealth((hero.getHealth() - (int)halfDamage), false);
                    tourEnemy.add(
                            "You were able to partially defend yourself ⛊" +
                            "\n" + "\uD83D\uDDE1 Enemy deal " + (int)halfDamage + " damage!" +
                            "\n" + hero.getName() + " has ( " + hero.getHealth() + " ♥ " + hero.getArmor() + " ⛊ " + ") " + "\n");
                    if (hero.getHealth() <= 0) {
                        tourEnemy.add("† Game over!");
                        return tourEnemy;
//
                    }
                    return tourEnemy;
                } else {
                    int damageDeduction = hero.getHealth() - enemyDamage;
                    hero.setHealth(damageDeduction, false);
                    tourEnemy.add(
                            "\uD83D\uDDE1 Enemy deal " + enemyDamage + " damage!" +
                            "\n" + hero.getName() + " has ( " + hero.getHealth() + " ♥ )\n");
                    if (hero.getHealth() <= 0) {
                        tourEnemy.add("† Game over!");
                        return tourEnemy;
//
                    }
                    return tourEnemy;
                }
            }
        } else {
            int damageDeduction = hero.getHealth() - enemyDamage;
            hero.setHealth(damageDeduction, false);
            tourEnemy.add("\uD83D\uDDE1 Enemy deal " + enemyDamage + " damage!" +
                    "\n" + hero.getName() + " has ( " + hero.getHealth() + " ♥ )\n");
            if (hero.getHealth() <= 0) {
                tourEnemy.add("† Game over!");
                return tourEnemy;
//
            }
            return tourEnemy;
        }
    }

//    public static void AutoFight (Hero hero, Enemy enemy, int[] choice) {
//        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {
//            boolean autoFight = true;
//            choice = new int[]{1};
//            if (hero.getHealth() > 0) {
//                TourHero(hero, enemy, choice, autoFight);
//            }
//            if (enemy.getHealth() > 0) {
//                TourEnemy(hero, enemy, choice, autoFight);
//            }
//        }
//    }

}
