package events;

import menu.Menu;
import races.Hero;

import java.util.Random;
import java.util.Scanner;

import static events.Fight.Fight;

public class Path {

    private static final Random RANDOM = new Random();

    public static void roll (Hero hero, Scanner scanner) {
        boolean chanceFight = RANDOM.nextBoolean();

        if (chanceFight) {
            Fight(hero, scanner);
            Menu.menuHistory(hero, scanner);
        } else {
            Menu.menuHistory(hero, scanner);
        }
    }


}
