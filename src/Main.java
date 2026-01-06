import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        System.out.println(
                "▗▄▄▄  ▗▞▀▜▌ ▄▄▄ █  ▄     ▗▄▄▄  █  ▐▌▄▄▄▄  ▗▞▀▚▖ ▄▄▄  ▄▄▄▄  \n" +
                "▐▌  █ ▝▚▄▟▌█    █▄▀      ▐▌  █ ▀▄▄▞▘█   █ ▐▛▀▀▘█   █ █   █ \n" +
                "▐▌  █      █    █ ▀▄     ▐▌  █      █   █ ▝▚▄▄▖▀▄▄▄▀ █   █ \n" +
                "▐▙▄▄▀           █  █     ▐▙▄▄▀          ▗▄▖                \n" +
                "                                       ▐▌ ▐▌               \n" +
                "                                        ▝▀▜▌               \n" +
                "                                       ▐▙▄▞▘               \n");
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
            CreateHero(race, scanner);
        } else {
            System.out.println("bye bye ( ╥﹏╥) ノシ");
        };
    }

    public static <T> T CreateHero (int race, Scanner scanner) {
        System.out.print("Enter the name: ");
        String nameHero = scanner.nextLine();

        System.out.print("Enter the health: ");
        int healthHero = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter the armor: ");
        int armorHero = Integer.parseInt(scanner.nextLine());

        if (race == 1) {
            Human human = new Human(nameHero, healthHero, armorHero);
            return (T) human;
        }
        if (race == 2) {
            Elf elf = new Elf(nameHero, healthHero, armorHero);
            return (T) elf;
        }
        if (race == 3) {
            Dwarf dwarf = new Dwarf(nameHero, healthHero, armorHero);
            return (T) dwarf;
//            System.out.println(
//                    "Dwarf: " +
//                            "Name ( " + nameHero + " ) " +
//                            "Health ( " + healthHero + " ♥ ) " +
//                            "Armor ( " + armorHero + " \uD83D\uDEE1 ) " );
        }
        return null;
    }
}




