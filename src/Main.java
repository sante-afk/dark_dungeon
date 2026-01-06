import Race.*;
import Enemies.*;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random RANDOM = new Random();
    public static void main () {
        System.out.println("                                                                      \n" +
                "▄▄▄▄   ▄▄▄  ▄▄▄▄  ▄▄ ▄▄   ▄▄▄▄  ▄▄ ▄▄ ▄▄  ▄▄  ▄▄▄▄ ▄▄▄▄▄  ▄▄▄  ▄▄  ▄▄ \n" +
                "██▀██ ██▀██ ██▄█▄ ██▄█▀   ██▀██ ██ ██ ███▄██ ██ ▄▄ ██▄▄  ██▀██ ███▄██ \n" +
                "████▀ ██▀██ ██ ██ ██ ██   ████▀ ▀███▀ ██ ▀██ ▀███▀ ██▄▄▄ ▀███▀ ██ ▀██ \n" +
                "                                                                      ");
        System.out.print("Start the game (Yes/No) ?: ");
        Scanner scanner = new Scanner(System.in);
        String enterGame = scanner.nextLine().trim();

        Hero hero = null;
        if (enterGame.equals("Yes")) {
            System.out.println("Choose a race ");
            System.out.print("Human - 1, ");
            System.out.print("Elf - 2, ");
            System.out.print("Dwarf - 3: ");
            int race = scanner.nextInt();
            scanner.nextLine();
            hero = CreateHero(race, scanner);
            if (hero != null) {
                System.out.println(
                        "\n" + hero.getName() + " " + hero.getRace() +
                        " ( " + hero.getLevel() + " lvl" + " ) " +
                        "\nDamage ( " + hero.getMinDamage() + " - " + hero.getMaxDamage() + " ⚔ )" +
                        "\nHealth ( " + hero.getHealth() + " ♥ ) " +
                        "\nArmor ( " + hero.getArmor() + " ⛊ ) \n");
            }
        } else {
            System.out.println("bye bye ( ╥﹏╥) ノシ");
        }

        Fight(hero, scanner);
        scanner.nextLine();

    }

    public static Hero CreateHero (int race, Scanner scanner) {
        System.out.print("Enter the name: ");
        String nameHero = scanner.nextLine();

        System.out.print("Enter the health: ");
        int healthHero = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the armor: ");
        int armorHero = Integer.parseInt(scanner.nextLine());

        int level = 1;
        int minDamage = RANDOM.nextInt(5) + 1;
        int maxDamage = RANDOM.nextInt(5, 10) + 1;

        switch (race) {
            case 1:
                String raceHuman = "Human";
                Hero human = new Human(nameHero, healthHero, armorHero, raceHuman, level, minDamage, maxDamage);
                human.setName(nameHero);
                human.setHealth(healthHero);
                human.setArmor(armorHero);
                human.setRace(raceHuman);
                human.setLevel(level);
                human.setMinDamage(minDamage);
                human.setMaxDamage(maxDamage);
                return human;
            case 2:
                String raceElf = "Elf";
                Hero elf = new Elf(nameHero, healthHero, armorHero, raceElf, level, minDamage, maxDamage);
                elf.setName(nameHero);
                elf.setHealth(healthHero);
                elf.setArmor(armorHero);
                elf.setRace(raceElf);
                elf.setLevel(level);
                elf.setMinDamage(minDamage);
                elf.setMaxDamage(maxDamage);
                return elf;
            case 3:
                String raceDwarf = "Dwarf";
                Hero dwarf = new Dwarf(nameHero, healthHero, armorHero, raceDwarf, level, minDamage, maxDamage);
                dwarf.setName(nameHero);
                dwarf.setHealth(healthHero);
                dwarf.setArmor(armorHero);
                dwarf.setRace(raceDwarf);
                dwarf.setLevel(level);
                dwarf.setMinDamage(minDamage);
                dwarf.setMaxDamage(maxDamage);
                return dwarf;
            default:
                return null;
        }
    }

    public static void Fight (Hero hero, Scanner scanner) {
        int lvlHero = hero.getLevel();
        int minDamageHero = hero.getMinDamage();
        int maxDamageHero = hero.getMaxDamage();
        Enemy enemy = CreateEnemy(lvlHero, minDamageHero, maxDamageHero);

        boolean replicaRandom = RANDOM.nextBoolean();
        boolean attackFirst = RANDOM.nextBoolean();

        String replicaGoes = attackFirst ? "\n" +
                "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▜\n" +
                "▌You attack first! ▐\n" +
                "▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟" :
                enemy.getName() + " attacks first! \n";

        String replica = replicaRandom ?
                "You met " + enemy.getName() + "!" :
                "Came across your path " + enemy.getName() + "!";
        System.out.println(replica);
        System.out.println(
                enemy.getRace() +
                        " ( " + enemy.getLevel() + " lvl" + " ) " +
                        "\nDamage ( " + enemy.getMinDamage() + " - " + enemy.getMaxDamage() + " ⚔ )" +
                        "\nHealth ( " + enemy.getHealth() + " ♥ ) " +
                        "\nArmor ( " + enemy.getArmor() + " ⛊ ) ");


        int minDamageEnemy = enemy.getMinDamage();
        int maxDamageEnemy = enemy.getMaxDamage();

        System.out.println(replicaGoes);
        while (hero.getHealth() > 0 && enemy.getHealth() > 0) {

            if (attackFirst) {
                System.out.println("1. Physical Attack ");
                int choise = Integer.parseInt(scanner.nextLine());

                if (choise == 1) {
                    int heroDamage = hero.physDamage(minDamageHero, maxDamageHero);
                    enemy.setHealth(enemy.getHealth() - heroDamage);

                    System.out.println(" \uD83D\uDDE1 You deal " + heroDamage + " damage!");
                    System.out.println(enemy.getName() + " has " + enemy.getHealth() + " HP left \n");

                    if (enemy.getHealth() <= 0) {
                        System.out.println(" ☠ " + enemy.getName() + " defeated!");
                        break;
                    }
                }
            } else {
                int enemyDamage = enemy.physDamage(minDamageEnemy, maxDamageEnemy);
                hero.setHealth(hero.getHealth() - enemyDamage);

                System.out.println(" \uD83D\uDDE1 Enemy deal " + enemyDamage + " damage!");
                System.out.println(hero.getName() + " has " + hero.getHealth() + " HP left \n");

                if (hero.getHealth() <= 0) {
                    System.out.println(" ☠ Game over!");
                    break;
                }
            }
            attackFirst = !attackFirst;
        }

        scanner.nextLine();
    }

    public static Enemy CreateEnemy (int lvlHero, int minDamageHero, int maxDamageHero) {
        int enemies = handlingEnemies();
        int enemyRandom = RANDOM.nextInt(enemies) + 1;
        int raceRandom = RANDOM.nextInt(enemies) + 1;

        int levelRandom = RANDOM.nextInt(lvlHero) + 1;
        int minDamage = RANDOM.nextInt(minDamageHero) + 1;
        int maxDamage = RANDOM.nextInt(minDamage, maxDamageHero) + 1;

        if (levelRandom == lvlHero) {
            levelRandom += 1;
            minDamage += 1;
            maxDamage += 1;
        }


        switch (enemyRandom) {
            case 1 -> {
                String race;
                switch (raceRandom) {
                    case 2 -> race = "Giant Rat";
                    case 3 -> race = "Shadow Rat";
                    case 4 -> race = "Skaven";
                    default -> race = "Plague Rat";
                }
                return new Rat(
                        "Rat",
                        RANDOM.nextInt(10) + 1,
                        RANDOM.nextInt(10) + 1,
                        race,
                        levelRandom,
                        minDamage,
                        maxDamage
                        );
            }
            case 2 -> {
                String race;
                switch (raceRandom) {
                    case 2 -> race = "Goblin Scout";
                    case 3 -> race = "Goblin Shaman";
                    case 4 -> race = "Hobgoblin";
                    default -> race = "Goblin Grunt";
                }
                return new Goblin(
                        "Goblin",
                        RANDOM.nextInt(20) + 1,
                        RANDOM.nextInt(20) + 1,
                        race,
                        levelRandom,
                        minDamage,
                        maxDamage);
            }
            case 3 -> {
                String race;
                switch (raceRandom) {
                    case 2 -> race = "Bandit Archer";
                    case 3 -> race = "Bandit Mage";
                    case 4 -> race = "Bandit Leader";
                    default -> race = "Bandit Thug";
                }
                return new Bandit(
                        "Bandit",
                        RANDOM.nextInt(30) + 1,
                        RANDOM.nextInt(30) + 1,
                        race,
                        levelRandom,
                        minDamage,
                        maxDamage);
            }
            case 4 -> {
                String race;
                switch (raceRandom) {
                    case 2 -> race = "Water Elemental";
                    case 3 -> race = "Earth Elemental";
                    case 4 -> race = "Air Elemental";
                    default -> race = "Fire Elemental";
                }
                return new Elemental(
                        "Elemental",
                        RANDOM.nextInt(40) + 1,
                        RANDOM.nextInt(40) + 1,
                        race,
                        levelRandom,
                        minDamage,
                        maxDamage);
            }
        }
        return null;
    }

    public static int handlingEnemies () {
        File dir = new File("src/Enemies");
        File[] arrFiles = dir.listFiles();
        return arrFiles.length - 1;
    }
}