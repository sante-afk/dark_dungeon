package frames;

import enemies.Enemy;
import events.Path;
import printer.Printer;
import races.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Game {

    private static final Random RANDOM = new Random();

    public static JPanel gameFrame(Hero hero) {
        JPanel gameFrame = new JPanel();
        gameFrame.setLayout(new BorderLayout());

        // Icons hero
        ImageIcon iconHuman = new ImageIcon(new ImageIcon("icons/human.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconElf = new ImageIcon(new ImageIcon("icons/elf.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconDwarf = new ImageIcon(new ImageIcon("icons/dwarf.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));

        // Icons enemy
        ImageIcon iconBandit = new ImageIcon(new ImageIcon("icons/bandit.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconCyclops = new ImageIcon(new ImageIcon("icons/cyclops.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconRat = new ImageIcon(new ImageIcon("icons/rat.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconOrc = new ImageIcon(new ImageIcon("icons/orc.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconGoblin = new ImageIcon(new ImageIcon("icons/goblin.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));

        ImageIcon iconElementalAir = new ImageIcon(new ImageIcon("icons/elementalAir.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconElementalWater = new ImageIcon(new ImageIcon("icons/elementalWater.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconElementalFire = new ImageIcon(new ImageIcon("icons/elementalFire.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
        ImageIcon iconElementalEarth = new ImageIcon(new ImageIcon("icons/elementalEarth.png")
                .getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));


        // Other Icons
        ImageIcon iconHp = new ImageIcon(new ImageIcon("icons/hp.png")
                .getImage().getScaledInstance(33, 35, Image.SCALE_SMOOTH));
        ImageIcon iconArmor = new ImageIcon(new ImageIcon("icons/armor.png")
                .getImage().getScaledInstance(25, 26, Image.SCALE_SMOOTH));

        // battlePlace
        JPanel battlePlace = new JPanel();
        battlePlace.setLayout(new BorderLayout());
        battlePlace.setBackground(Color.MAGENTA);
        battlePlace.setPreferredSize(new Dimension(0, 400));
        gameFrame.add(battlePlace);

        // log game
        JTextArea gameLog = new JTextArea (Printer.printHistory(hero));
        gameLog.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
        gameLog.setWrapStyleWord(true);
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(20f);
            gameLog.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        gameLog.setForeground(Color.WHITE);
        gameLog.setBackground(Color.BLACK);

        // panel scroll
        JScrollPane containerScroll = new JScrollPane(gameLog);
        containerScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        containerScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        battlePlace.add(containerScroll, BorderLayout.CENTER);

        // panelHero
        JPanel panelHero = new JPanel();
        panelHero.setBackground(Color.BLACK);
        panelHero.setLayout(new BoxLayout(panelHero, BoxLayout.Y_AXIS));
        panelHero.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHero.setPreferredSize(new Dimension(250, 300));
        gameFrame.add(panelHero, BorderLayout.EAST);

        // Portrait hero
        JLabel portraitHero = new JLabel();
        String raceHero = hero.getRace();
        switch (raceHero) {
            case "Human" -> {
                portraitHero.setIcon(iconHuman);
            }
            case "Elf" -> {
                portraitHero.setIcon(iconElf);
            }
            case "Dwarf" -> {
                portraitHero.setIcon(iconDwarf);
            }
        }
        portraitHero.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelHero.add(portraitHero);
        panelHero.add(Box.createVerticalStrut(30));

        // Name
        JLabel nameHeroLabel = new JLabel("Name: " + hero.getName());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            nameHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        nameHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameHeroLabel.setForeground(Color.WHITE);
        panelHero.add(nameHeroLabel);

        // Race
        JLabel raceHeroLabel = new JLabel("Race: " + hero.getRace());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            raceHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        raceHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        raceHeroLabel.setForeground(Color.WHITE);
        panelHero.add(raceHeroLabel);

        // Health
        JLabel healthHeroLabel = new JLabel("Health: " + hero.getHealth());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            healthHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        healthHeroLabel.setIcon(iconHp);
        healthHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthHeroLabel.setForeground(Color.WHITE);
        panelHero.add(healthHeroLabel);

        // Armor
        JLabel armorHeroLabel = new JLabel("Armor: " + hero.getArmor());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            armorHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        armorHeroLabel.setIcon(iconArmor);
        armorHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        armorHeroLabel.setForeground(Color.WHITE);
        panelHero.add(armorHeroLabel);

        // Level
        JLabel levelHeroLabel = new JLabel("LvL: " + hero.getLevel());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            levelHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        levelHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        levelHeroLabel.setForeground(Color.WHITE);
        panelHero.add(levelHeroLabel);

        // panelMenu
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(Color.BLUE);
        panelMenu.setLayout(new GridLayout(1, 4));
        panelMenu.setPreferredSize(new Dimension(0, 100));
        gameFrame.add(panelMenu, BorderLayout.SOUTH);


        JButton btnContinue = new JButton("Continue on your way");
        JButton btnSleep = new JButton("To take a break");
        JButton btnAttack = new JButton("Physical Attack");
        JButton btnAutoAttack = new JButton("Auto Attack");
        final int[] choice = {0};

        // Button attack
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnAttack.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        btnAttack.setVisible(false);
        panelMenu.add(btnAttack);
        btnAttack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = 1;
            }
        });

        // Button auto attack
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnAutoAttack.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        btnAutoAttack.setVisible(false);
        panelMenu.add(btnAutoAttack);
        btnAttack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = 2;
            }
        });

        Enemy enemy = CreateHero.CreateEnemy(hero);

        // Button To take a break
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnSleep.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        btnSleep.setHorizontalAlignment(SwingConstants.CENTER);
        panelMenu.add(btnSleep);

        // panelEnemy
        JPanel panelEnemy = new JPanel();
        panelEnemy.setBackground(Color.BLACK);
        panelEnemy.setLayout(new BoxLayout(panelEnemy, BoxLayout.Y_AXIS));
        panelEnemy.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEnemy.setPreferredSize(new Dimension(250, 300));
        gameFrame.add(panelEnemy, BorderLayout.WEST);

        // Portrait enemy
        JLabel portraitEnemy = new JLabel();
        String raceEnemy = enemy.getRace();
        if (raceEnemy.contains("Rat")) {
            portraitEnemy.setIcon(iconRat);
        } else if (raceEnemy.contains("Goblin")) {
            portraitEnemy.setIcon(iconGoblin);
        } else if (raceEnemy.contains("Bandit")) {
            portraitEnemy.setIcon(iconBandit);
        } else if (raceEnemy.contains("Water Elemental")) {
            portraitEnemy.setIcon(iconElementalWater);
        } else if (raceEnemy.contains("Earth Elemental")) {
            portraitEnemy.setIcon(iconElementalEarth);
        } else if (raceEnemy.contains("Air Elemental")) {
            portraitEnemy.setIcon(iconElementalAir);
        } else if (raceEnemy.contains("Fire Elemental")) {
            portraitEnemy.setIcon(iconElementalFire);
        }
        portraitEnemy.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEnemy.add(portraitEnemy);
        panelEnemy.setVisible(false);
        panelEnemy.add(Box.createVerticalStrut(30));

        // Name
        JLabel nameEnemyLabel = new JLabel("Name: " + enemy.getName());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            nameEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        nameEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameEnemyLabel.setForeground(Color.WHITE);
        nameEnemyLabel.setVisible(false);
        panelEnemy.add(nameEnemyLabel);

        // Race
        JLabel raceEnemyLabel = new JLabel("Race: " + enemy.getRace());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            raceEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        raceEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        raceEnemyLabel.setForeground(Color.WHITE);
        raceEnemyLabel.setVisible(false);
        panelEnemy.add(raceEnemyLabel);

        // Health
        JLabel healthEnemyLabel = new JLabel("Health: " + enemy.getHealth());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            healthEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        healthEnemyLabel.setIcon(iconHp);
        healthEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthEnemyLabel.setForeground(Color.WHITE);
        healthEnemyLabel.setVisible(false);
        panelEnemy.add(healthEnemyLabel);

        // Armor
        JLabel armorEnemyLabel = new JLabel("Armor: " + enemy.getArmor());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            armorEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        armorEnemyLabel.setIcon(iconArmor);
        armorEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        armorEnemyLabel.setForeground(Color.WHITE);
        armorEnemyLabel.setVisible(false);
        panelEnemy.add(armorEnemyLabel);

        // Level
        JLabel levelEnemyLabel = new JLabel("LvL: " + enemy.getLevel());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            levelEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        levelEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        levelEnemyLabel.setForeground(Color.WHITE);
        levelEnemyLabel.setVisible(false);
        panelEnemy.add(levelEnemyLabel);

        // Button Continue
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnContinue.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        panelMenu.add(btnContinue);





        btnContinue.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean chanceFight = RANDOM.nextBoolean();

                if (chanceFight) {
                    boolean autoFight = false;
                    gameLog.setText("");

                    btnContinue.setVisible(false);
                    btnSleep.setVisible(false);

                    btnAttack.setVisible(true);
                    btnAutoAttack.setVisible(true);
                    panelEnemy.setVisible(true);
                    portraitEnemy.setVisible(true);
                    nameEnemyLabel.setVisible(true);
                    raceEnemyLabel.setVisible(true);
                    healthEnemyLabel.setVisible(true);
                    armorEnemyLabel.setVisible(true);
                    levelEnemyLabel.setVisible(true);


                    String[] replicas = Path.roll(hero, enemy, chanceFight, new int[]{choice[0]}, autoFight);
                    gameLog.setText(Arrays.toString(replicas));
                }


            }
        });

        return gameFrame;
    }
}
