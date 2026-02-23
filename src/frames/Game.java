package frames;

import Icons.IconManager;
import enemies.Enemy;
import events.Path;
import printer.Printer;
import races.Hero;
import replicas.Replica;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static Icons.IconManager.*;
import static events.Fight.*;
import static handling.Handling.handlingHealing;
import static handling.Handling.handlingPath;

public class Game {

    private static final Random RANDOM = new Random();
    private static Clip musicClip;

    private static JButton btnContinue;
    private static JButton btnSleep;
    private static JButton btnAttack;
    private static JButton btnAutoAttack;

    private static Enemy enemy;
    private static JLabel portraitEnemy;
    private static JLabel nameEnemyLabel;
    private static JLabel raceEnemyLabel;
    private static JLabel healthEnemyLabel;
    private static JLabel armorEnemyLabel;
    private static JLabel levelEnemyLabel;
    private static JPanel panelEnemy;

    public static JPanel gameFrame(Hero hero) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JPanel gameFrame = new JPanel();
        gameFrame.setBackground(Color.BLACK);
        gameFrame.setLayout(new BorderLayout());


        // path music
        stopMusic();
        boolean randomPathMusic = RANDOM.nextBoolean();
        String sound = randomPathMusic ? "sounds/mainPath.wav" : "sounds/path.wav";
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

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
        gameLog.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 20));
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


        btnContinue = new JButton("Continue on your way");
        btnSleep = new JButton("To take a break");
        btnAttack = new JButton("Physical Attack");
        btnAutoAttack = new JButton("Auto Attack");
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

        final Enemy[] enemy = {CreateHero.CreateEnemy(hero)};

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
        panelEnemy = new JPanel ();
        panelEnemy.setBackground(Color.BLACK);
        panelEnemy.setLayout(new BoxLayout(panelEnemy, BoxLayout.Y_AXIS));
        panelEnemy.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEnemy.setPreferredSize(new Dimension(250, 300));
        gameFrame.add(panelEnemy, BorderLayout.WEST);

        // Portrait enemy
        portraitEnemy = new JLabel();
        String raceEnemy = enemy[0].getRace();
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
        nameEnemyLabel = new JLabel("Name: " + enemy[0].getName());
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
        raceEnemyLabel = new JLabel("Race: " + enemy[0].getRace());
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
        healthEnemyLabel = new JLabel("Health: " + enemy[0].getHealth());
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
        armorEnemyLabel = new JLabel("Armor: " + enemy[0].getArmor());
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
        levelEnemyLabel = new JLabel("LvL: " + enemy[0].getLevel());
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


        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean chanceFight = RANDOM.nextBoolean();
                enemy[0] = CreateHero.CreateEnemy(hero);
                updateEnemyUI(enemy[0]);

                if (chanceFight) {
                    stopMusic();

                    playFightMusic();


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

                    choice[0] = 1;
                    Fight(hero, enemy[0], choice, false);
                    healthHeroLabel.setText("HP: " + hero.getHealth());
                    armorHeroLabel.setText("Armor: " + hero.getArmor());
                    healthEnemyLabel.setText("HP: " + enemy[0].getHealth());
                    armorEnemyLabel.setText("Armor: " + enemy[0].getArmor());

                    boolean randomReplic = RANDOM.nextBoolean();
                    String replica = Replica.replicaMet(enemy[0], randomReplic);
                    gameLog.setText(replica);

                    ArrayList<String> replicas = Path.roll(hero, enemy[0], chanceFight, choice, autoFight);
                    gameLog.setText(String.valueOf(replicas));
                }
            }
        });
        btnAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enemy[0].getHealth() > 0) {
                    if (hero.getHealth() <= 0 || enemy[0].getHealth() <= 0) {
                        return;
                    }

                    ArrayList<String> roundLog = new ArrayList<>();
                    choice[0] = 1;
                    roundLog.addAll(TourHero(hero, enemy[0], choice, false));

                    if (enemy[0].getHealth() > 0) {
                        roundLog.addAll(TourEnemy(hero, enemy[0], choice, false));
                    }

                    gameLog.setText(String.join("\n", roundLog));
                    healthHeroLabel.setText("HP: " + hero.getHealth());
                    armorHeroLabel.setText("Armor: " + hero.getArmor());
                    levelHeroLabel.setText("LVL: " + hero.getLevel());
                    healthEnemyLabel.setText("HP: " + enemy[0].getHealth());
                    armorEnemyLabel.setText("Armor: " + enemy[0].getArmor());

                    if (enemy[0].getHealth() <= 0 || hero.getHealth() <= 0) {
                        endBattle(hero, enemy[0]);
                    }
                }
            }
        });
        btnSleep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = 4;
                String log = handlingPath(hero, enemy[0], false, choice);
                gameLog.setText(String.join("\n", log));
                healthHeroLabel.setText("HP: " + hero.getHealth());
            }
        });

        return gameFrame;
    }


    private static void updateEnemyUI(Enemy enemy) {
        String raceEnemy = enemy.getRace();
        portraitEnemy.setIcon(IconManager.getEnemyIcon(raceEnemy));
        nameEnemyLabel.setText("Name: " + enemy.getName());
        raceEnemyLabel.setText("Race: " + enemy.getRace());
        healthEnemyLabel.setText("HP: " + enemy.getHealth());
        armorEnemyLabel.setText("Armor: " + enemy.getArmor());
        levelEnemyLabel.setText("LvL: " + enemy.getLevel());
        panelEnemy.revalidate();
        panelEnemy.repaint();
    }

    private static void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            musicClip.close();
        }
    }

    private static void endBattle(Hero hero, Enemy enemy) {
        btnAttack.setVisible(false);
        btnAutoAttack.setVisible(false);
        panelEnemy.setVisible(false);
        portraitEnemy.setVisible(false);
        nameEnemyLabel.setVisible(false);
        raceEnemyLabel.setVisible(false);
        healthEnemyLabel.setVisible(false);
        armorEnemyLabel.setVisible(false);
        levelEnemyLabel.setVisible(false);

        btnContinue.setVisible(true);
        btnSleep.setVisible(true);

        stopMusic();
        playPathMusic();
    }

    private static void playFightMusic () {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("sounds/fight.wav").getAbsoluteFile());
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException r) {
            r.printStackTrace();
        }
    }

    private static void playPathMusic () {
        boolean randomPathMusic = RANDOM.nextBoolean();
        String sound = randomPathMusic ? "sounds/mainPath.wav" : "sounds/path.wav";
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException s) {
            s.printStackTrace();
        }
    }
}
