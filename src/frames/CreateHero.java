package frames;

import enemies.*;
import handling.Handling;
import printer.Printer;
import races.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class CreateHero {

    private static final Random RANDOM = new Random();

    public static JPanel createHeroFrame () {
        JPanel topCharacter = new JPanel();
        topCharacter.setBackground(Color.BLACK);
        topCharacter.setBounds(250,200,500,300);
        topCharacter.setLayout(new BorderLayout());

//      label
        JLabel labelName = new JLabel("Enter the name");
        labelName.setPreferredSize(new Dimension(100,150));
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Black.ttf")).deriveFont(50f);
            labelName.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        labelName.setForeground(Color.WHITE);
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        topCharacter.add(labelName, BorderLayout.NORTH);

        JPanel paneLInput = new JPanel();
        paneLInput.setBackground(Color.BLACK);
        paneLInput.setLayout(new BorderLayout());
        topCharacter.add(paneLInput, BorderLayout.CENTER);

//      input
        JTextField fieldName = new JTextField(15);
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(30f);
            fieldName.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        fieldName.setPreferredSize(new Dimension(0,40));
        fieldName.setHorizontalAlignment(SwingConstants.CENTER);
        fieldName.setBackground(Color.BLACK);
        fieldName.setForeground(Color.WHITE);
        paneLInput.add(fieldName, BorderLayout.NORTH);


//      OK
        JButton bOK = new JButton(Printer.printMenuOK());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            bOK.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        bOK.setForeground(Color.black);
        bOK.setPreferredSize(new Dimension(0, 50));
        topCharacter.add(bOK, BorderLayout.SOUTH);

        return topCharacter;
    }

    public static Hero CreateHero (int race, Scanner scanner) {
        String nameHero;
        int healthHero = 0;
        int armorHero = 0;

        System.out.print("Enter the name: ");
        nameHero = scanner.nextLine();

        System.out.print("Enter the health: ");
        String enterHealth = scanner.nextLine();
        if (enterHealth.isEmpty()) {
            healthHero = 0;
        } else {
            healthHero = Integer.parseInt(enterHealth);
        }

        System.out.print("Enter the armor: ");
        String enterArmor = scanner.nextLine();
        if (enterArmor.isEmpty()) {
            armorHero = 0;
        } else {
            armorHero = Integer.parseInt(enterArmor);
        }

        int level = 1;
        double exp = 0.0;
        int expEnd = level * 10;

        int minDamage = RANDOM.nextInt(1, 5) + 1;
        int maxDamage = RANDOM.nextInt(minDamage, 10) + 1;

        switch (race) {
            case 1:
                String raceHuman = "Human";
                Hero human = new Human(nameHero, healthHero, armorHero, raceHuman,
                        level, exp, expEnd, minDamage, maxDamage);
                human.setName(nameHero);
                human.setHealth(healthHero, true);
                human.setArmor(armorHero, true);
                human.setRace(raceHuman);
                human.setLevel(level);
                human.setMinDamage(minDamage);
                human.setMaxDamage(maxDamage);
                return human;
            case 2:
                String raceElf = "Elf";
                Hero elf = new Elf(nameHero, healthHero, armorHero, raceElf,
                        level, exp, expEnd, minDamage, maxDamage);
                elf.setName(nameHero);
                elf.setHealth(healthHero, true);
                elf.setArmor(armorHero, true);
                elf.setRace(raceElf);
                elf.setLevel(level);
                elf.setMinDamage(minDamage);
                elf.setMaxDamage(maxDamage);
                return elf;
            case 3:
                String raceDwarf = "Dwarf";
                Hero dwarf = new Dwarf(nameHero, healthHero, armorHero, raceDwarf,
                        level, exp, expEnd, minDamage, maxDamage);
                dwarf.setName(nameHero);
                dwarf.setHealth(healthHero, true);
                dwarf.setArmor(armorHero, true);
                dwarf.setRace(raceDwarf);
                dwarf.setLevel(level);
                dwarf.setMinDamage(minDamage);
                dwarf.setMaxDamage(maxDamage);
                return dwarf;
            default:
                return null;
        }
    }

    public static Enemy CreateEnemy (Hero hero) {
        int enemies = Handling.handlingEnemies();
        int enemyRandom = RANDOM.nextInt(enemies) + 1;
        int raceRandom = RANDOM.nextInt(enemies) + 1;

        int levelRandom = RANDOM.nextInt(1, hero.getLevel() + 1);
        int minDamage = RANDOM.nextInt(1, hero.getMinDamage() + 1);
        int maxDamage = RANDOM.nextInt(minDamage, hero.getMaxDamage() + 1);

        if (levelRandom > hero.getLevel() && levelRandom == hero.getLevel()) {
            levelRandom += 1;
            minDamage += 1;
            maxDamage += 1;
        }

        int health = 0;
        int armor = 0;

        if (levelRandom > hero.getLevel() && levelRandom == hero.getLevel()) {
            health = RANDOM.nextInt(1, (hero.getHealth() / 2) + 1);
            armor = RANDOM.nextInt(1, (hero.getArmor() / 2) + 1);
        } else {
            health = RANDOM.nextInt(1, hero.getHealth() + 1);
            armor = RANDOM.nextInt(1, hero.getArmor() + 1);
        }

        switch (enemyRandom) {
            case 1 -> {
                String race;
                switch (raceRandom) {
                    case 2 -> race = "Giant Rat";
                    case 3 -> race = "Shadow Rat";
                    case 4 -> race = "Skaven";
                    default -> race = "Plague Rat";
                }
                String name = "Rat";
                Rat rat = new Rat( name, health, armor, race, levelRandom, minDamage, maxDamage );
                rat.setName(name);
                rat.setHealth(health);
                rat.setArmor(armor);
                rat.setRace(race);
                rat.setLevel(levelRandom);
                rat.setMinDamage(minDamage);
                rat.setMaxDamage(maxDamage);
                return rat;
            }
            case 2 -> {
                String race;
                switch (raceRandom) {
                    case 2 -> race = "Goblin Scout";
                    case 3 -> race = "Goblin Shaman";
                    case 4 -> race = "Hobgoblin";
                    default -> race = "Goblin Grunt";
                }
                String name = "Goblin";
                Goblin goblin = new Goblin( name, health, armor, race, levelRandom, minDamage, maxDamage );
                goblin.setName(name);
                goblin.setHealth(health);
                goblin.setArmor(armor);
                goblin.setRace(race);
                goblin.setLevel(levelRandom);
                goblin.setMinDamage(minDamage);
                goblin.setMaxDamage(maxDamage);
                return goblin;
            }
            case 3 -> {
                String race;
                switch (raceRandom) {
                    case 2 -> race = "Bandit Archer";
                    case 3 -> race = "Bandit Mage";
                    case 4 -> race = "Bandit Leader";
                    default -> race = "Bandit Thug";
                }
                String name = "Bandit";
                Bandit bandit = new Bandit( name, health, armor, race, levelRandom, minDamage, maxDamage);
                bandit.setName(name);
                bandit.setHealth(health);
                bandit.setArmor(armor);
                bandit.setRace(race);
                bandit.setLevel(levelRandom);
                bandit.setMinDamage(minDamage);
                bandit.setMaxDamage(maxDamage);
                return bandit;
            }
            case 4 -> {
                String race;
                switch (raceRandom) {
                    case 2 -> race = "Water Elemental";
                    case 3 -> race = "Earth Elemental";
                    case 4 -> race = "Air Elemental";
                    default -> race = "Fire Elemental";
                }
                String name = "Elemental";
                Elemental elemental = new Elemental( name, health, armor, race, levelRandom, minDamage, maxDamage);
                elemental.setName(name);
                elemental.setHealth(health);
                elemental.setArmor(armor);
                elemental.setRace(race);
                elemental.setLevel(levelRandom);
                elemental.setMinDamage(minDamage);
                elemental.setMaxDamage(maxDamage);
                return elemental;
            }
        }
        return null;
    }
}
