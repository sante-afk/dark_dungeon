package events;

import enemies.Enemy;
import races.Hero;

import java.util.ArrayList;
import java.util.Random;

import static events.Fight.Fight;

public class Path {



    public static ArrayList<String> roll (Hero hero, Enemy enemy, boolean chanceFight, int[] choice, boolean autoFight) {

        if (chanceFight) {
            ArrayList<String> fight = Fight(hero, enemy, choice, autoFight);
            return fight;
        }

        return null;
    }


}
