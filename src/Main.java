import handling.Handling;
import menu.Menu;
import printer.*;
import races.Hero;

import java.util.Scanner;

public class Main {
    public static void main () {
        Printer.printLogo();
        Printer.printStartGame();
        Scanner scanner = new Scanner(System.in);
        Hero hero = Handling.handlingStartGame(scanner);
        Menu.menuBegin(hero, scanner);


        scanner.close();
    }

}