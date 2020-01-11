package robot_turtle;

import java.awt.Color;
import java.util.Scanner;
import javax.swing.JFrame;

import java.awt.Color; 
import javax.swing.JFrame;
import javax.swing.JPanel;
 

public class Main {
	
	public static void main(String[] args) {
		int nbrJoueurs = menuNbrJoueurs();
		Jeu jeu = new Jeu(nbrJoueurs);
		jeu.run();
	}
	
	public static int menuNbrJoueurs() {
		Scanner scanner = new Scanner(System.in);
		
		
		int choixNbrJoueurs = 0;
		
		do {
			System.out.println("2, 3 ou 4 joueurs?");
			
			try {
				choixNbrJoueurs = scanner.nextInt();
			}
			catch (Exception e) {
				scanner.nextLine();
				choixNbrJoueurs = 0;
			}
		}
		while(!(choixNbrJoueurs>=2 && choixNbrJoueurs<=4));//tant que le choix n'est pas entre 1 et 3
		
		return choixNbrJoueurs;
	}

}