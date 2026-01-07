package Replicas;

import Enemies.Enemy;

public class Replica {
    public static String replicaTour (boolean tour) {
        return tour ? "\n" +
                "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▜\n" +
                "▌   You attack !   ▐\n" +
                "▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟\n" :
                "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▜\n" +
                "▌   Enemy attack ! ▐\n" +
                "▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟\n";
    }

    public static String replicaMet (Enemy enemy, boolean replica) {
        return replica ?
                "You met " + enemy.getName() + "!" :
                "Came across your path " + enemy.getName() + "!";
    }
}
