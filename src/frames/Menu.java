package frames;
import handling.Handling;
import printer.Printer;
import races.Hero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Menu {

    public static void menuFrame () {
        JPanel panelLogo = new JPanel();
        panelLogo.setBackground(Color.BLACK);
        panelLogo.setLayout(new BorderLayout());
        panelLogo.setBounds(250,0,500,250);

        JPanel panelMenuYes = new JPanel();
        panelMenuYes.setBackground(Color.BLACK);
        panelMenuYes.setLayout(new BorderLayout());
        panelMenuYes.setBounds(398,270,100,20);

        JPanel panelMenuNo = new JPanel();
        panelMenuNo.setBackground(Color.BLACK);
        panelMenuNo.setLayout(new BorderLayout());
        panelMenuNo.setBounds(505,270,100,20);


//      frame start
        JFrame window = new JFrame();
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageLogo = new ImageIcon("icons/dd_icon.png");
        window.getContentPane().setBackground(Color.BLACK);
        window.setTitle(Printer.printLogo());
        window.setSize(1000, 750);
        window.setResizable(true);
        window.setIconImage(imageLogo.getImage());
        window.add(panelLogo);
        window.add(panelMenuYes);
        window.add(panelMenuNo);


//      logo game
        JLabel logo = new JLabel(Printer.printLogo());
        logo.setSize( 500, 250);
        try {
            Font logoFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Black.ttf")).deriveFont(50f);
            logo.setFont(logoFont);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        logo.setForeground(Color.WHITE);
        logo.setVerticalAlignment(JLabel.CENTER);
        logo.setHorizontalAlignment(JLabel.CENTER);
        panelLogo.add(logo);


//      print start game
        JLabel printStartGame = new JLabel(Printer.printStartGame());
        printStartGame.setSize(200,50);
        try {
            Font mainText = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            printStartGame.setFont(mainText);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        printStartGame.setForeground(Color.WHITE);
        printStartGame.setVerticalAlignment(JLabel.BOTTOM);
        printStartGame.setHorizontalAlignment(JLabel.CENTER);
        panelLogo.add(printStartGame);


//      start game
        JButton yes = new JButton(Printer.printMenuYes());
        try {
            Font fontInput = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            yes.setFont(fontInput);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        yes.setForeground(Color.BLACK);
        yes.setHorizontalAlignment(JButton.CENTER);
        yes.setVerticalAlignment(JButton.CENTER);
        panelMenuYes.add(yes);
        yes.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLogo.setVisible(false);
                panelMenuNo.setVisible(false);
                panelMenuYes.setVisible(false);

                JPanel createHero = CreateHero.createHeroFrame();
                window.add(createHero);

            }
        });


//      end game
        JButton no = new JButton(Printer.printMenuNo());
        try {
            Font fontInput = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Cinzel-Regular.ttf")).deriveFont(14f);
            no.setFont(fontInput);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        no.setForeground(Color.BLACK);
        no.setHorizontalAlignment(JButton.CENTER);
        no.setVerticalAlignment(JButton.CENTER);
        panelMenuNo.add(no);
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            }
        });


//        Scanner scanner = new Scanner(System.in);
//        Hero hero = Handling.handlingStartGame(scanner);
//        Menu.menuBegin(hero, scanner);
//        scanner.close();

        window.setVisible(true);
    }

    public static void menuBegin (Hero hero, Scanner scanner) {
        JTextField history = new JTextField();
        Printer.printHistory(hero);
        menuHistory(hero, scanner);
    }

    public static void menuHistory (Hero hero, Scanner scanner) {
        Handling.handlingPath(hero, scanner);
    }

    public static void menuFight () {
        Printer.printMenuFight();
    }
}
