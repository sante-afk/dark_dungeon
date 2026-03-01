package icons;

import frames.Menu;

import javax.swing.*;
import java.awt.*;

public class IconManager {

    // Icons hero
    public static final ImageIcon iconHuman = loadIcon("/icons/human.png", 120, 140);
    public static final ImageIcon iconElf = loadIcon("/icons/elf.png", 120, 140);
    public static final ImageIcon iconDwarf = loadIcon("/icons/dwarf.png", 120, 140);

    // Icons enemy
    public static final ImageIcon iconBandit = loadIcon("/icons/bandit.png", 120, 140);
    public static final ImageIcon iconCyclops = loadIcon("/icons/cyclops.png", 120, 140);
    public static final ImageIcon iconRat = loadIcon("/icons/rat.png", 120, 140);
    public static final ImageIcon iconOrc = loadIcon("/icons/orc.png", 120, 140);
    public static final ImageIcon iconGoblin = loadIcon("/icons/goblin.png", 120, 140);

    public static final ImageIcon iconElementalAir = loadIcon("/icons/elementalAir.png", 120, 140);
    public static final ImageIcon iconElementalWater = loadIcon("/icons/elementalWater.png", 120, 140);
    public static final ImageIcon iconElementalFire = loadIcon("/icons/elementalFire.png", 120, 140);
    public static final ImageIcon iconElementalEarth = loadIcon("/icons/elementalEarth.png", 120, 140);

    public static final ImageIcon iconHp = loadIcon("/icons/hp.png", 33, 35);
    public static final ImageIcon iconArmor = loadIcon("/icons/armor.png", 25, 26);

    private static ImageIcon loadIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(Menu.class.getResource(path));
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static ImageIcon getEnemyIcon(String race) {
        if (race.contains("Rat")) return iconRat;
        if (race.contains("Goblin")) return iconGoblin;
        if (race.contains("Bandit")) return iconBandit;
        if (race.contains("Cyclops")) return iconCyclops;
        if (race.contains("Orc")) return iconOrc;
        if (race.contains("Water Elemental")) return iconElementalWater;
        if (race.contains("Earth Elemental")) return iconElementalEarth;
        if (race.contains("Air Elemental")) return iconElementalAir;
        if (race.contains("Fire Elemental")) return iconElementalFire;
        return null;
    }
}
