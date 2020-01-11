package com.company;

import javax.swing.JFrame;


public class Fenetre extends JFrame{


    public Fenetre(){

        this.setTitle("RobotTurtle");
        this.setSize(1250, 850);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}