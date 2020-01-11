package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;


public class Menu extends JPanel{
    private static Fenetre fenetre_menu = new Fenetre();


    public static void menu() throws IOException {
        JButton bouton1 = new JButton("Jouer");
        JButton bouton2 = new JButton("quitter");

        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    menu_jouer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        bouton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    fenetre_menu.dispose();
            }
        });

        Panneau container = new Panneau("C:\\Users\\felix\\IdeaProjects\\RobotTurtle\\src\\com\\company\\images\\background.jpeg");
        container.setBackground(Color.white);
        container.add(bouton1);
        container.add(bouton2);
        container.setLayout(null);
        bouton1.setBounds(475, 250, 300, 60);
        bouton2.setBounds(475, 450, 300, 60);

        fenetre_menu.setContentPane(container);
        fenetre_menu.repaint();
        fenetre_menu.revalidate();
        fenetre_menu.setVisible(true);
    }

    private static void menu_jouer() throws IOException {
        JButton bouton1 = new JButton("2 Joueurs");
        JButton bouton2 = new JButton("3 Joueurs");
        JButton bouton3 = new JButton("4 Joueurs");
        JButton bouton4 = new JButton("Retour");
        bouton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    menu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    menu_jeu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Panneau container = new Panneau("C:\\Users\\felix\\IdeaProjects\\RobotTurtle\\src\\com\\company\\images\\background.jpeg");
        container.setBackground(Color.white);
        container.add(bouton1);
        container.add(bouton2);
        container.add(bouton3);
        container.add(bouton4);
        container.setLayout(null);
        bouton1.setBounds(475, 150, 300, 60);
        bouton2.setBounds(475, 300, 300, 60);
        bouton3.setBounds(475, 450, 300, 60);
        bouton4.setBounds(475, 600, 300, 60);

        fenetre_menu.setContentPane(container);
        fenetre_menu.setVisible(true);

    }

    public static void menu_jeu() throws IOException {

        Panneau plateau_de_jeu = new Panneau("C:\\Users\\felix\\IdeaProjects\\RobotTurtle\\src\\com\\company\\images\\fond_plateau.jpg");
        plateau_de_jeu.setPreferredSize(new Dimension(800, 800));

        JPanel interface_jeu = new JPanel();
        interface_jeu.setBackground(Color.lightGray);
        interface_jeu.setPreferredSize(new Dimension(400, 800));

        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(1200, 800 ));
        container.setBackground(Color.WHITE);
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;

        container.add(plateau_de_jeu, gbc);
        gbc.gridx = 1;
        container.add(interface_jeu, gbc);




        fenetre_menu.setContentPane(container);
        fenetre_menu.setVisible(true);
    }

    



}
