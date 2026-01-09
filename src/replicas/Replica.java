package replicas;

import enemies.Enemy;

public class Replica {
    public static String replicaTour (boolean tour) {
        return tour ? "\n" +
                "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▜\n" +
                "▌   You attack !   ▐\n" +
                "▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟" :
                "▛▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▜\n" +
                "▌   Enemy attack ! ▐\n" +
                "▙▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▟";
    }

    public static String replicaMet (Enemy enemy, boolean replica) {
        return replica ?
                "\nYou met " + enemy.getName() + "!" :
                "\nCame across your path " + enemy.getName() + "!";
    }
}
