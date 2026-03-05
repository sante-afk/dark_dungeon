package frames;

import enemies.Enemy;
import events.Path;
import printer.Printer;
import races.Hero;
import replicas.Replica;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static icons.IconManager.*;
import static events.Fight.*;
import static handling.Handling.handlingPath;
import static java.awt.SystemColor.window;
import static music.musicManager.*;

public class Game {

    private static final Random RANDOM = new Random();
    private static Clip musicClip;

    private static JButton btnContinue;
    private static JButton btnSleep;
    private static JButton btnAttack;
    private static JButton btnAutoAttack;
    private static JButton btnPrintLvL;

    private static Clip clipFight;
    private static Clip clipPath;
    private static JLabel portraitEnemy;
    private static JLabel nameEnemyLabel;
    private static JLabel raceEnemyLabel;
    private static JLabel healthEnemyLabel;
    private static JLabel armorEnemyLabel;
    private static JLabel levelEnemyLabel;
    private static JPanel panelEnemy;
    private static Enemy enemy;

    public static JPanel gameFrame(Hero hero, Clip clip, JFrame window) {
        JPanel gameFrame = new JPanel();
        gameFrame.setBackground(Color.BLACK);
        gameFrame.setLayout(new BorderLayout());


        // path music
        if (clip != null) {
            stopMusic(clip);
        }
        clipPath = playPathMusic();

        // battlePlace
        JPanel battlePlace = new JPanel();
        battlePlace.setLayout(new BorderLayout());
        battlePlace.setBackground(Color.MAGENTA);
        battlePlace.setPreferredSize(new Dimension(0, 400));
        gameFrame.add(battlePlace);

        JTextPane gameLog = new JTextPane();
        gameLog.setText(Printer.printHistory(hero));
        gameLog.setEditable(false);
        gameLog.setFont(new Font("Dialog", Font.PLAIN, 25));
        gameLog.setForeground(Color.WHITE);
        gameLog.setBackground(Color.BLACK);
        gameLog.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        StyledDocument doc = gameLog.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

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
        panelHero.add(Box.createVerticalStrut(30));
        panelHero.add(portraitHero);
        panelHero.add(Box.createVerticalStrut(30));

        // Name
        JLabel nameHeroLabel = new JLabel("Name: " + hero.getName());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            nameHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        nameHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameHeroLabel.setForeground(Color.WHITE);
        panelHero.add(nameHeroLabel);

        // Race
        JLabel raceHeroLabel = new JLabel("Race: " + hero.getRace());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            raceHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        raceHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        raceHeroLabel.setForeground(Color.WHITE);
        panelHero.add(raceHeroLabel);

        // Health
        JLabel healthHeroLabel = new JLabel("Health: " + hero.getHealth());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            healthHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        healthHeroLabel.setIcon(iconHp);
        healthHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthHeroLabel.setForeground(Color.WHITE);
        panelHero.add(healthHeroLabel);

        // Armor
        JLabel armorHeroLabel = new JLabel("Armor: " + hero.getArmor());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            armorHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        armorHeroLabel.setIcon(iconArmor);
        armorHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        armorHeroLabel.setForeground(Color.WHITE);
        panelHero.add(armorHeroLabel);

        // Level
        JLabel levelHeroLabel = new JLabel("LvL: " + hero.getLevel());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            levelHeroLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        levelHeroLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        levelHeroLabel.setForeground(Color.WHITE);
        panelHero.add(levelHeroLabel);

        // panelMenu
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(Color.BLACK);
        panelMenu.setLayout(new GridLayout(1, 4));
        panelMenu.setPreferredSize(new Dimension(0, 100));
        gameFrame.add(panelMenu, BorderLayout.SOUTH);

        btnContinue = new JButton("Continue on your way");
        btnSleep = new JButton("To take a break");
        btnAttack = new JButton("Physical Attack");
        btnAutoAttack = new JButton("Auto Attack");
        btnPrintLvL = new JButton("Status LVL");
        final int[] choice = {0};

        // Button attack
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnAttack.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        btnAttack.setVisible(false);
        panelMenu.add(btnAttack);
        btnAttack.setPreferredSize(new Dimension(120, 30));
        btnAttack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = 1;
            }
        });

        // Button auto attack
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnAutoAttack.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        btnAutoAttack.setVisible(false);
        panelMenu.add(btnAutoAttack);
        btnAutoAttack.setPreferredSize(new Dimension(120, 30));
        btnAttack.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = 2;
            }
        });


        // Button To take a break
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnSleep.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        btnSleep.setPreferredSize(new Dimension(120, 30));
        btnSleep.setHorizontalAlignment(SwingConstants.CENTER);
        panelMenu.add(btnSleep);

        // Button print lvl hero
        try {
            Font lvlText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnPrintLvL.setFont(lvlText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        btnPrintLvL.setPreferredSize(new Dimension(120, 30));
        btnPrintLvL.setHorizontalAlignment(SwingConstants.CENTER);
        panelMenu.add(btnPrintLvL);


        // panelEnemy
        panelEnemy = new JPanel ();
        panelEnemy.setBackground(Color.BLACK);
        panelEnemy.setLayout(new BoxLayout(panelEnemy, BoxLayout.Y_AXIS));
        panelEnemy.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEnemy.setPreferredSize(new Dimension(250, 300));
        gameFrame.add(panelEnemy, BorderLayout.WEST);

        // Portrait enemy
        portraitEnemy = new JLabel();
        String raceEnemy = "";
        if (enemy != null) {
            raceEnemy = enemy.getRace();
        }
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
        panelEnemy.add(Box.createVerticalStrut(30));
        panelEnemy.add(portraitEnemy);
        panelEnemy.setVisible(false);
        panelEnemy.add(Box.createVerticalStrut(30));

        // Name
        nameEnemyLabel = new JLabel("Name: " );
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            nameEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        nameEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameEnemyLabel.setForeground(Color.WHITE);
        nameEnemyLabel.setVisible(false);
        panelEnemy.add(nameEnemyLabel);

        // Race
        raceEnemyLabel = new JLabel("Race: " );
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            raceEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        raceEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        raceEnemyLabel.setForeground(Color.WHITE);
        raceEnemyLabel.setVisible(false);
        panelEnemy.add(raceEnemyLabel);

        // Health
        healthEnemyLabel = new JLabel("Health: " );
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            healthEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        healthEnemyLabel.setIcon(iconHp);
        healthEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        healthEnemyLabel.setForeground(Color.WHITE);
        healthEnemyLabel.setVisible(false);
        panelEnemy.add(healthEnemyLabel);

        // Armor
        armorEnemyLabel = new JLabel("Armor: " );
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            armorEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        armorEnemyLabel.setIcon(iconArmor);
        armorEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        armorEnemyLabel.setForeground(Color.WHITE);
        armorEnemyLabel.setVisible(false);
        panelEnemy.add(armorEnemyLabel);

        // Level
        levelEnemyLabel = new JLabel("LvL: " );
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(18f);
            levelEnemyLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        levelEnemyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        levelEnemyLabel.setForeground(Color.WHITE);
        levelEnemyLabel.setVisible(false);
        panelEnemy.add(levelEnemyLabel);

        // Button Continue
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            btnContinue.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        btnContinue.setPreferredSize(new Dimension(120, 30));
        panelMenu.add(btnContinue);

        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean chanceFight = RANDOM.nextBoolean();

                if (chanceFight) {
                    enemy = CreateHero.CreateEnemy(hero);
                    if (enemy != null) {
                        updateEnemyUI(enemy);
                    }

                    if (clipPath != null) {
                        stopMusic(clipPath);
                    }
                    clipFight = playFightMusic();

                    boolean autoFight = false;
                    gameLog.setText("");

                    btnContinue.setVisible(false);
                    btnSleep.setVisible(false);
                    btnPrintLvL.setVisible(false);

                    btnAttack.setVisible(true);
//                    btnAutoAttack.setVisible(true);
                    panelEnemy.setVisible(true);
                    portraitEnemy.setVisible(true);
                    nameEnemyLabel.setVisible(true);
                    raceEnemyLabel.setVisible(true);
                    healthEnemyLabel.setVisible(true);
                    armorEnemyLabel.setVisible(true);
                    levelEnemyLabel.setVisible(true);

                    choice[0] = 1;
                    if (enemy == null) {
                        return;
                    }
                    healthHeroLabel.setText("HP: " + hero.getHealth());
                    armorHeroLabel.setText("Armor: " + hero.getArmor());

                    if (hero.getHealth() <= 0) {
                        gameOver(window, gameFrame, clipFight);
                    };

                    if (enemy != null) {
                        Fight(hero, enemy, choice, false);
                        healthEnemyLabel.setText("HP: " + enemy.getHealth());
                        armorEnemyLabel.setText("Armor: " + enemy.getArmor());
                    }
                    boolean randomReplic = RANDOM.nextBoolean();
                    String replica = Replica.replicaMet(enemy, randomReplic);
                    gameLog.setText(replica);

                    ArrayList<String> replicas = Path.roll(hero, enemy, chanceFight, choice, autoFight);
                    gameLog.setText(String.valueOf(replicas));

                    if (hero.getHealth() <= 0) {
                        gameOver(window, gameFrame, clipFight);
                    };

                }

            }
        });
        btnAttack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enemy != null) {
                    if (enemy.getHealth() > 0) {
                        if (hero.getHealth() <= 0 || enemy.getHealth() <= 0) {
                            return;
                        }

                        ArrayList<String> roundLog = new ArrayList<>();
                        choice[0] = 1;
                        roundLog.addAll(TourHero(hero, enemy, choice, false));

                        if (enemy.getHealth() > 0) {
                            roundLog.addAll(TourEnemy(hero, enemy, choice, false));
                        }

                        gameLog.setText(String.join("\n", roundLog));
                        healthHeroLabel.setText("HP: " + hero.getHealth());
                        armorHeroLabel.setText("Armor: " + hero.getArmor());
                        levelHeroLabel.setText("LVL: " + hero.getLevel());
                        healthEnemyLabel.setText("HP: " + enemy.getHealth());
                        armorEnemyLabel.setText("Armor: " + enemy.getArmor());

                        if (enemy.getHealth() <= 0 || hero.getHealth() <= 0) {
                            endBattle(hero, window, gameFrame, enemy, clipFight);
                            clipPath = playPathMusic();
                        }
                    }
                } else {
                    enemy = CreateHero.CreateEnemy(hero);
                }

            }
        });
        btnSleep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = 4;
                String log = handlingPath(hero, enemy, false, choice);
                gameLog.setText(String.join("\n", log));
                healthHeroLabel.setText("HP: " + hero.getHealth());
            }
        });
        btnPrintLvL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLog.setText(Printer.printLevel(hero));
            }
        });

        return gameFrame;
    }

    private static void updateEnemyUI(Enemy enemy) {
        String raceEnemy = "";
        if (enemy != null) {
            raceEnemy  = enemy.getRace();
        }
        portraitEnemy.setIcon(getEnemyIcon(raceEnemy));
        nameEnemyLabel.setText("Name: " + enemy.getName());
        raceEnemyLabel.setText("Race: " + enemy.getRace());
        healthEnemyLabel.setText("HP: " + enemy.getHealth());
        armorEnemyLabel.setText("Armor: " + enemy.getArmor());
        levelEnemyLabel.setText("LvL: " + enemy.getLevel());
        panelEnemy.revalidate();
        panelEnemy.repaint();
    }


    private static void endBattle(Hero hero, JFrame window, JPanel gameFrame, Enemy enemy, Clip clipFight) {
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
        btnPrintLvL.setVisible(true);

        if (clipFight != null) {
            stopMusic(clipFight);
        }

        if (hero.getHealth() <= 0) {
            if (clipPath != null) {
                stopMusic(clipPath);
            }
            gameOver(window, gameFrame, clipFight);
        }
    }

    private static void gameOver(JFrame window, JPanel gameFrame, Clip clipFight) {

        int dialog = JOptionPane.showConfirmDialog(
                gameFrame,
                "Start new game?",
                "Game Over",
                JOptionPane.YES_NO_OPTION
        );
        if (dialog == JOptionPane.YES_OPTION) {
            if (clipFight != null) {
                stopMusic(clipFight);
            }
            window.dispose();
            Menu.menuFrame();
        } else {
            window.dispose();
        }
    }

}
