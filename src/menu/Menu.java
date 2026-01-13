package menu;
import handling.Handling;
import printer.Printer;
import races.Hero;

import java.util.Scanner;

public class Menu {

    public static void menuBegin (Hero hero, Scanner scanner) {
        Printer.printHistory(hero);
        menuHistory(hero, scanner);
    }

    public static void menuHistory (Hero hero, Scanner scanner) {
        Handling.handlingPath(hero, scanner);
    }

    public static void menuFight () {
        Printer.printMenuFight();
    }
}
