package com.company;

public class Plateau {
    static String[][] plateau;
    static int nombre_joueur=2;

    public static void initialisationplateau() {
        plateau = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                plateau[i][j] =" ";
            }
        }

        if (nombre_joueur==2){
            plateau[1][7]="tortue_rouge";
            plateau[5][7]="tortue_verte";
            plateau[3][0]="rubi";
            for (int j=0; j<8;j++){
                plateau[7][j]="mur";
            }
        }

        if (nombre_joueur==3){
            plateau[0][7]="tortue_rouge";
            plateau[3][7]="tortue_verte";
            plateau[6][7]="tortue_violette";
            plateau[0][0]="rubi";
            plateau[3][0]="rubi";
            plateau[6][0]="rubi";
            for (int j=0; j<8;j++){
                plateau[7][j]="mur";
            }
        }

        if (nombre_joueur==4){
            plateau[0][7]="tortue_rouge";
            plateau[2][7]="tortue_verte";
            plateau[5][7]="tortue_violette";
            plateau[7][7]="tortue_jaune";
            plateau[1][0]="rubi";
            plateau[6][0]="rubi";
        }
    }
}