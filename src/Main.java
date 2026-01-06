import race.Elf;
import race.Hero;
import race.Dwarf;
import race.Human;

import java.util.Scanner;

public class Main {
    public static void main () {
        System.out.println("                                                                      \n" +
                "▄▄▄▄   ▄▄▄  ▄▄▄▄  ▄▄ ▄▄   ▄▄▄▄  ▄▄ ▄▄ ▄▄  ▄▄  ▄▄▄▄ ▄▄▄▄▄  ▄▄▄  ▄▄  ▄▄ \n" +
                "██▀██ ██▀██ ██▄█▄ ██▄█▀   ██▀██ ██ ██ ███▄██ ██ ▄▄ ██▄▄  ██▀██ ███▄██ \n" +
                "████▀ ██▀██ ██ ██ ██ ██   ████▀ ▀███▀ ██ ▀██ ▀███▀ ██▄▄▄ ▀███▀ ██ ▀██ \n" +
                "                                                                      ");
        System.out.print("Start the game (Yes/No) ?: ");
        Scanner scanner = new Scanner(System.in);
        String enterGame = scanner.nextLine().trim();

        if (enterGame.equals("Yes")) {
            System.out.println("Choose a race ");
            System.out.print("Human - 1, ");
            System.out.print("Elf - 2, ");
            System.out.print("Dwarf - 3: ");
            int race = scanner.nextInt();
            scanner.nextLine();
            Hero hero = CreateHero(race, scanner);

            if (hero != null) {
                System.out.println(
                        hero.getRace() +
                            " ( " + hero.getName() + " ) " +
                            "Health ( " + hero.getHealth() + " ♥ ) " +
                            "Armor ( " + hero.getArmor() + " \uD83D\uDEE1 ) " );
            }
        } else {
            System.out.println("bye bye ( ╥﹏╥) ノシ");
        }
        scanner.nextLine();
    }

    public static Hero CreateHero (int race, Scanner scanner) {
        System.out.print("Enter the name: ");
        String nameHero = scanner.nextLine();

        System.out.print("Enter the health: ");
        int healthHero = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the armor: ");
        int armorHero = Integer.parseInt(scanner.nextLine());

        switch (race) {
            case 1:
                Hero human = new Human(nameHero, healthHero, armorHero, "Human");
                human.setName(nameHero);
                human.setHealth(healthHero);
                human.setArmor(armorHero);
                return human;
            case 2:
                Hero elf = new Elf(nameHero, healthHero, armorHero, "Elf");
                elf.setName(nameHero);
                elf.setHealth(healthHero);
                elf.setArmor(armorHero);
                return elf;
            case 3:
                Hero dwarf = new Dwarf(nameHero, healthHero, armorHero, "Dwarf");
                dwarf.setName(nameHero);
                dwarf.setHealth(healthHero);
                dwarf.setArmor(armorHero);
                return dwarf;
            default:
                return null;
        }
    }


}




