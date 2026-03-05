package frames;

import enemies.*;
import printer.Printer;
import races.*;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Random;

import static music.musicManager.stopMusic;

public class CreateHero {

    private static final Random RANDOM = new Random();

    public static JPanel createHero (Clip clip, JFrame window) {
        JPanel panelName = new JPanel();
        panelName.setBackground(Color.BLACK);
        panelName.setSize(new Dimension(1000, 750));
        panelName.setLayout(new BorderLayout());

//      label
        JLabel labelName = new JLabel("Enter the name");
        labelName.setPreferredSize(new Dimension(100,150));
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Black.ttf")).deriveFont(50f);
            labelName.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        labelName.setForeground(Color.WHITE);
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelName.add(labelName, BorderLayout.NORTH);

        // container
        JPanel container = new JPanel();
        container.setBackground(Color.BLACK);
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelName.add(container);

//      container Input
        JPanel conInput = new JPanel();
        conInput.setBackground(Color.BLACK);
        conInput.setLayout(new BoxLayout(conInput, BoxLayout.Y_AXIS));
        conInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(conInput, BorderLayout.NORTH);

//      input
        JTextField inputName = new JTextField(15);
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(30f);
            inputName.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        inputName.setHorizontalAlignment(SwingConstants.CENTER);
        inputName.setBackground(Color.BLACK);
        inputName.setForeground(Color.WHITE);
        inputName.setPreferredSize(new Dimension(70, 40));
        conInput.add(inputName);
        conInput.add(Box.createVerticalStrut(20));


//      OK
        final String[] nameHero = new String[1];
        JButton bOK = new JButton(Printer.printMenuOK());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            bOK.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        bOK.setForeground(Color.BLACK);
        bOK.setPreferredSize(new Dimension(100, 20));
        bOK.setAlignmentX(Component.CENTER_ALIGNMENT);
        conInput.add(bOK);
        bOK.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon warning = new ImageIcon(
                        new ImageIcon(Menu.class.getResource("/icons/warning-icon.png"))
                                .getImage()
                                .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
                );

                if (inputName.getText().length() > 2) {
                    nameHero[0] = inputName.getText();
                    labelName.setVisible(false);
                    inputName.setVisible(false);
                    bOK.setVisible(false);
                    panelName.removeAll();

                    JPanel chooseRace = chooseRace(nameHero, clip, window);
                    panelName.add(chooseRace);
                } else {
                    String errorName = Printer.printErrorName();
                    JOptionPane.showMessageDialog(
                            panelName,
                            errorName,
                            "WARNING",
                            JOptionPane.WARNING_MESSAGE,
                            warning);
                }
            }
        });

        return panelName;
    }

    public static JPanel chooseRace (String[] nameHero, Clip clip, JFrame window) {
        JPanel panelRace = new JPanel();
        panelRace.setBackground(Color.BLACK);
        panelRace.setSize(new Dimension(1000, 750));
        panelRace.setLayout(new BorderLayout());

        JButton bHuman = new JButton(Printer.printHuman());
        JButton bElf = new JButton(Printer.printElf());
        JButton bDwarf = new JButton(Printer.printDwarf());


        // container
        JPanel container = new JPanel();
        container.setBackground(Color.BLACK);
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        container.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelRace.add(container);


//      label
        JLabel labelName = new JLabel("Choose a race");
        labelName.setPreferredSize(new Dimension(100,150));
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Black.ttf")).deriveFont(50f);
            labelName.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        labelName.setForeground(Color.WHITE);
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelRace.add(labelName, BorderLayout.NORTH);

//      human
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            bHuman.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        bHuman.setForeground(Color.BLACK);
        bHuman.setPreferredSize(new Dimension(100, 20));
        container.add(bHuman);
        bHuman.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            labelName.setVisible(false);
            bHuman.setVisible(false);
            bElf.setVisible(false);
            bDwarf.setVisible(false);
            panelRace.removeAll();

            JPanel panelAttributes = chooseAttributes(nameHero, 1, clip, window);
            panelRace.add(panelAttributes);
            }
        });


//      Elf
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            bElf.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        bElf.setForeground(Color.BLACK);
        bElf.setPreferredSize(new Dimension(100, 20));
        container.add(bElf);
        bElf.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            labelName.setVisible(false);
            bHuman.setVisible(false);
            bElf.setVisible(false);
            bDwarf.setVisible(false);
            panelRace.removeAll();

            JPanel panelAttributes = chooseAttributes(nameHero, 2, clip, window);
            panelRace.add(panelAttributes);
            }
        });


//      Dwarf
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            bDwarf.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        bDwarf.setForeground(Color.BLACK);
        bDwarf.setPreferredSize(new Dimension(100, 20));
        container.add(bDwarf);
        bDwarf.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            labelName.setVisible(false);
            bHuman.setVisible(false);
            bElf.setVisible(false);
            bDwarf.setVisible(false);
            panelRace.removeAll();

            JPanel panelAttributes = chooseAttributes(nameHero, 3, clip, window);
            panelRace.add(panelAttributes);
            }
        });


        return panelRace;
    }

    public static JPanel chooseAttributes (String[] nameHero, int race, Clip clip, JFrame window) {
        JPanel panelAttributes = new JPanel();
        panelAttributes.setBackground(Color.BLACK);
        panelAttributes.setBounds(250,200,700,300);
        panelAttributes.setLayout(new BorderLayout());

//      label
        JLabel labelName = new JLabel("Enter to attributes");
        labelName.setPreferredSize(new Dimension(500,150));
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Black.ttf")).deriveFont(40f);
            labelName.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        labelName.setForeground(Color.WHITE);
        labelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelAttributes.add(labelName, BorderLayout.NORTH);

//      container
        JPanel container = new JPanel();
        container.setBackground(Color.BLACK);
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelAttributes.add(container, BorderLayout.CENTER);

//      HP label
        JLabel hpLabel = new JLabel("HP");
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(24f);
            hpLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        hpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hpLabel.setBackground(Color.BLACK);
        hpLabel.setForeground(Color.WHITE);
        hpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(hpLabel);

//      HP
        JTextField hp = new JTextField();
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            hp.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        hp.setForeground(Color.WHITE);
        hp.setBackground(Color.RED);
        hp.setMaximumSize(new Dimension(200, 40));
        hp.setAlignmentX(Component.CENTER_ALIGNMENT);
        hp.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(hp);
        container.add(Box.createVerticalStrut(30));


//      Armor label
        JLabel armorLabel = new JLabel("Armor");
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(24f);
            armorLabel.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        armorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        armorLabel.setForeground(Color.WHITE);
        armorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(armorLabel);

//      Armor
        JTextField armor = new JTextField();
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            armor.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        armor.setHorizontalAlignment(SwingConstants.CENTER);
        armor.setForeground(Color.BLACK);
        armor.setBackground(Color.CYAN);
        armor.setMaximumSize(new Dimension(200, 40));
        armor.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(armor);
        container.add(Box.createVerticalStrut(20));

        int[] armorHero = {0};
        int[] healthHero = {0};

//      OK
        JButton bOK = new JButton(Printer.printMenuOK());
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    Menu.class.getResourceAsStream("/fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            bOK.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        bOK.setHorizontalAlignment(SwingConstants.CENTER);
        bOK.setForeground(Color.BLACK);
        bOK.setAlignmentX(Component.CENTER_ALIGNMENT);
        bOK.setPreferredSize(new Dimension(100, 20));
        bOK.setMaximumSize(new Dimension(100, 20));
        container.add(bOK);
        bOK.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon notification = new ImageIcon(
                        new ImageIcon(Menu.class.getResource("/icons/notification-icon.png"))
                                .getImage()
                                .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
                );

                ImageIcon warning = new ImageIcon(
                        new ImageIcon(Menu.class.getResource("/icons/warning-icon.png"))
                                .getImage()
                                .getScaledInstance(30, 30, Image.SCALE_SMOOTH)
                );
                if (healthHero[0] == 0 || armorHero[0] == 0) {
                    try {
                        healthHero[0] = Integer.parseInt(hp.getText());
                        armorHero[0] = Integer.parseInt(armor.getText());
                        if (healthHero[0] < 100) {
                            JOptionPane.showMessageDialog(
                                    panelAttributes,
                                    "health will be 100 by default",
                                    "Notification",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    notification);
                            healthHero[0] = 100;
                        }
                        if (armorHero[0] < 100) {
                            JOptionPane.showMessageDialog(panelAttributes,
                                    "armor will be 100 by default",
                                    "Notification",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    notification);

                            armorHero[0] = 100;
                        }
                    } catch (NumberFormatException a) {
                        JOptionPane.showMessageDialog(
                                panelAttributes,
                                "You have not set all attributes",
                                "WARNING",
                                JOptionPane.WARNING_MESSAGE,
                                warning);
                    }
                }

                Hero hero = CreateHeroClass(nameHero, race, healthHero, armorHero);
                    labelName.setVisible(false);
                    container.setVisible(false);
                    armorLabel.setVisible(false);
                    hp.setVisible(false);
                    bOK.setVisible(false);
                    panelAttributes.removeAll();

                    if (clip != null) {
                        stopMusic(clip);
                    }

                    JPanel gameFrame = Game.gameFrame(hero, clip, window);
                    panelAttributes.add(gameFrame);
//              }
            }
        });

        return panelAttributes;
    }

    public static Hero CreateHeroClass (String[] nameHero, int race, int[] healthHero, int[] armorHero) {

        int level = 1;
        double exp = 0.0;
        int expEnd = level * 10;

        int minDamage = RANDOM.nextInt(1, 5) + 1;
        int maxDamage = RANDOM.nextInt(minDamage, 10) + 1;

        switch (race) {
            case 1:
                String raceHuman = "Human";
                Hero human = new Human(nameHero[0], healthHero[0], armorHero[0], raceHuman,
                        level, exp, expEnd, minDamage, maxDamage);
                human.setName(nameHero[0]);
                human.setHealth(healthHero[0], true);
                human.setArmor(armorHero[0], true);
                human.setRace(raceHuman);
                human.setLevel(level);
                human.setMinDamage(minDamage);
                human.setMaxDamage(maxDamage);
                return human;
            case 2:
                String raceElf = "Elf";
                Hero elf = new Elf(nameHero[0], healthHero[0], armorHero[0], raceElf,
                        level, exp, expEnd, minDamage, maxDamage);
                elf.setName(nameHero[0]);
                elf.setHealth(healthHero[0], true);
                elf.setArmor(armorHero[0], true);
                elf.setRace(raceElf);
                elf.setLevel(level);
                elf.setMinDamage(minDamage);
                elf.setMaxDamage(maxDamage);
                return elf;
            case 3:
                String raceDwarf = "Dwarf";
                Hero dwarf = new Dwarf(nameHero[0], healthHero[0], armorHero[0], raceDwarf,
                        level, exp, expEnd, minDamage, maxDamage);
                dwarf.setName(nameHero[0]);
                dwarf.setHealth(healthHero[0], true);
                dwarf.setArmor(armorHero[0], true);
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
        int enemies = 5;
        int enemyRandom = RANDOM.nextInt(enemies) + 1;
        int raceRandom = RANDOM.nextInt(enemies) + 1;

        int levelRandom = RANDOM.nextInt(hero.getLevel() + 2) + 1;
        int minDamage = RANDOM.nextInt(1, hero.getMinDamage() + 1);
        int maxDamage = RANDOM.nextInt(minDamage, hero.getMaxDamage() + 1);

        if (levelRandom > hero.getLevel()) {
            minDamage += 1;
            maxDamage += 1;
        }

        int health = 0;
        int armor = 0;

        if (levelRandom > hero.getLevel()) {
            health = RANDOM.nextInt(hero.getHealth() / 2, hero.getHealth() + 1);
            armor = RANDOM.nextInt(hero.getArmor() / 2, hero.getArmor() + 1);
        } else {
            health = RANDOM.nextInt(1, hero.getHealth());
            armor = RANDOM.nextInt(1, hero.getArmor());
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
