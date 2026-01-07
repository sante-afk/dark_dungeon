import Handlings.Handling;
import Printer.*;
import Race.*;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main () {
        Printer.printLogo();
        Printer.printStartGame();
        Scanner scanner = new Scanner(System.in);
        Handling.handlingStartGame(scanner);


        scanner.close();
    }

}