package robot_turtle;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
	public Plateau plateau;
	public Cartes cartes;
	public Tortue tortue;
	public int mursPierre;
	public int mursGlace;
	
	public Joueur(Plateau plateau, int nJoueur) {//creer un joueur
		this.plateau = plateau;
		Cartes cartes = new Cartes();
		this.cartes = cartes;
		this.tortue = this.plateau.getTortue(nJoueur);
		this.mursPierre = 4;
		this.mursGlace = 4;
	}
	
	public Tortue getTortue() {
		return this.tortue;
	}
	
	public Cartes getCartes() {
		return this.cartes;
	}
	
	public void completerProgramme(int indiceCarte) {//complete le programme avec une carte
		String carte = this.cartes.prendreCarteMain(indiceCarte); //prend une carte de la main
		this.cartes.ajoutCarteProgramme(carte); //place la carte a la suite du programme
	}
	
	public boolean actionCompleterProgramme() {//interface graphique de laction
		
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		
		int tailleMain = this.cartes.getHand().size();
		boolean actionFaite = false;
		int indiceCarte = -2;
		
		System.out.println("completer le programme avec quelle carte? (-1 pour quitter)");
		
		do {//pour sortir si indiceCarte = -1 ou plus de cartes dans la main
			
			do {//demande de completer le programme avec les cartes de la main
				
				this.cartes.afficherMainJoueur();
				
				try {
					indiceCarte = scanner.nextInt();
				}
				catch (Exception e) {
					scanner.nextLine();
					indiceCarte = -2;
				}
			} while ( !( -1 <= indiceCarte && indiceCarte < tailleMain ));
				
			if(indiceCarte != -1) {//envie donc while ok donc indice ok
				this.completerProgramme(indiceCarte);
				tailleMain -= 1;
				actionFaite = true;
			}
			
		} while (tailleMain != 0 && indiceCarte != -1);
		
		return actionFaite;		
	}
	
	public void executionProgramme() {//execute, vide et renvoie le programme
		Tortue tortue = this.tortue;
		for(String carte : this.cartes.programme) {
			System.out.print(carte+") ");
			if(carte.contentEquals("A")) {
				System.out.println("avancer");
				tortue.avancerTortue();
				plateau.afficherPlateau();
			}
			if(carte.contentEquals("G")) {
				tortue.tournerGauche();
				System.out.println("direction: " + tortue.getDirection());
			}
			if(carte.contentEquals("D")) {
				tortue.tournerDroite();
				System.out.println("direction: " + tortue.getDirection());
			}
			if(carte.contentEquals("L")) {
				tortue.tirerLaser();
				
				System.out.println();
				plateau.afficherPlateau();
			}
		}
		System.out.println("");
		System.out.println("programme executé");
		ArrayList<String> programme = this.cartes.programme;
		programme.clear();
	}
	
	
	public void construireUnMurPierre(int[] position) {
		this.mursPierre -= 1;
		Tuile nouveauMur = new MurPierre(plateau, position);
	}
	
	public void construireUnMurGlace(int[] position) {
		this.mursGlace -= 1;
		Tuile nouveauMur = new MurGlace(plateau, position);
	}
	
	public boolean positionValide(int[] position){//true si la position est libre ou correcte et soccupe d'afficher les messages
		if(plateau.positionOccupe(position)) {//position deja occupée
			System.out.println("position occupée");
			return false;
		}
		else if ( !( (position[0]>=0 && position[0]<=7) && (position[1]>=0 && position[1]<=7) ) ) {//position invalide
			System.out.println("position incorrecte");
			return false;
		}
		else {
			return true;
		}
	}
	
	public int[] demanderPosition() {//demande la position et verifie si la position est correcte ou deja occupee
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		
		int[] position = {0,0};
		do {
			System.out.println("ligne puis colone du mur a placer:");
			
			try {
				position[0] = scanner.nextInt();//ligne
				position[1] = scanner.nextInt();//colone
				
				
			} catch (Exception e) {
				scanner.nextLine();
				position[0] = -1;
			}
			
		} while (!positionValide(position));
		return position;
	}
	
	public int demanderTypeMur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		
		int typeMur;
		
		do {
			System.out.println("type de mur: 1) glace 2) pierre");
			
			try {
				typeMur = scanner.nextInt();
			}
			catch (Exception e) {
				scanner.nextLine();
				typeMur = 3; 
			}
		} while (!(typeMur==2 || typeMur==1));
		
		return typeMur;
	}
	
	public boolean actionPlacerMur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		
		int typeMur = demanderTypeMur();
		
		if(typeMur==2 && this.mursPierre>0) {//pierre
			int[] position = demanderPosition();
			boolean envie = true;
			
			while(plateau.positionBloque(position) && envie) {//ca bloque une tortue ou un joueur
				System.out.println("bloque un joyeau ou une tortue");
				System.out.println("toujours envie de placer un mur? tappez true si oui false si non");
				envie = scanner.nextBoolean();
				if(envie) {
					position = demanderPosition();
				}
			}
			
			if(envie) {//si envie (donc le position ne pose pas de probleme)
				this.construireUnMurPierre(position);
				return true;
			}
			else {//si changement davis
				return false;
			}
		}
		
		else if(typeMur==1 && this.mursGlace>0){//glace
			int[] position = demanderPosition();
			this.construireUnMurGlace(position);
			return true;
		}
		
		return false;
		
	}
	
	
	public void piocherCartes() {//pioches les cartes a la fin du tour pour former la main du joueur
		this.cartes.mainJoueur();
	}
	
	public boolean action(int choix) {//soccupe du tour d'un joueur renvoie true si une action a bien ete executé
		boolean actionFaite = false;
		
		switch (choix) {
		case 1://completer le programme
			actionFaite = this.actionCompleterProgramme();
			return actionFaite;
			
		case 2://executer le programme
			System.out.println("execution du programme");
			
			this.executionProgramme();
			
			return true;
			
		case 3://mettre un mur
			actionFaite = actionPlacerMur();
			return actionFaite;
		default:
			return false;
		}
	}
	
public void actionDefausserSaMain() {//interface graphique de laction
		
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		
		int tailleMain = this.cartes.getHand().size();
		boolean actionFaite = false;
		int indiceCarte = -2;
		
		System.out.println("defausser quelle carte? (-1 pour quitter)");
		
		do {//pour sortir si indiceCarte = -1 ou plus de cartes dans la main
			
			do {//demande de completer le programme avec les cartes de la main
				
				this.cartes.afficherMainJoueur();
				
				try {
					indiceCarte = scanner.nextInt();
				}
				catch (Exception e) {
					scanner.nextLine();
					indiceCarte = -2;
				}
			} while ( !( -1 <= indiceCarte && indiceCarte < tailleMain ));
				
			if(indiceCarte != -1) {//envie donc while ok donc indice ok
				this.cartes.getHand().remove(indiceCarte);
				tailleMain -= 1;
				actionFaite = true;
			}
			
		} while (tailleMain != 0 && indiceCarte != -1);	
	}

	public int getMursPierre() {
		return this.mursPierre;
	}
	public int getMursGlace() {
		return this.mursGlace;
	}
	
	
	
}
