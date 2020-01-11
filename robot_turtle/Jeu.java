package robot_turtle;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
	public int nbrJoueurs;
	public ArrayList<Joueur> joueurs= new ArrayList<>();
	public Plateau plateau;
	
	public Jeu(int nbrJoueurs) {//initialise Plateau et Joueurs
		this.nbrJoueurs = nbrJoueurs;
		
		//initialisation plateau
		Plateau plateau = new Plateau(nbrJoueurs);
		this.plateau = plateau;
				
		//initialisation des joueurs
		for (int i=0;i<this.nbrJoueurs;i++) { 
			Joueur joueur = new Joueur(plateau,i);
			this.joueurs.add(joueur);
		}	
	}
	
	public int menuAction() {//renvoie l'indice d'action choisie
		Scanner scanner = new Scanner(System.in);
		int choix = 0;
		
		do {
			System.out.println();
			System.out.println("1) completer le programme");
			System.out.println("2) executer le programme");
			System.out.println("3) construire un mur");
			
			try {
				choix = scanner.nextInt();
			}
			catch (Exception e) {
				scanner.nextLine();
				choix = 0;
			}
							
		}while( !(choix>=1 && choix<=3));
		
		return choix;
	}
	
	private boolean menuDefausse(Joueur joueur) {
		
		Scanner scanner = new Scanner(System.in);
		int choix = 0;
		
		do {
			System.out.println();
			System.out.println("defausser sa main? 0 non 1 oui");
			System.out.println(joueur.getCartes().getHand());
			
			try {
				choix = scanner.nextInt();
			}
			catch (Exception e) {
				System.out.println("not ok");
				scanner.nextLine();
				choix = 2;
			}
							
		}while( !(choix==1 || choix==0) );
		
		if (choix == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public void run() {
		plateau.afficherTuiles();
		plateau.afficherPlateau();
		
		Random r = new Random();
		
		int indiceJoueur = r.nextInt(nbrJoueurs);
		while(!plateau.finDuJeu()) {
			
			indiceJoueur = (1 + indiceJoueur) % this.nbrJoueurs;
			Joueur joueur = this.joueurs.get(indiceJoueur);
			
			boolean actionFaite = false;
			
			while(!actionFaite) {
				System.out.println();
				System.out.println("joueur n°"+ indiceJoueur +"     "
						+ "main : " + joueur.getCartes().getHand() + "    "
						+ "direction :"+ joueur.getTortue().getDirection()+"    "
						+ "murs pierre/glace :"+ joueur.getMursPierre()+"/"+joueur.getMursGlace());
				int choixAction = menuAction();
				actionFaite = joueur.action(choixAction);
			}
			System.out.println("action faite avec succes");
			
			boolean choixDefausse = menuDefausse(joueur);
			if(choixDefausse) {
				joueur.actionDefausserSaMain();
				System.out.println();
				System.out.println("main defaussée");
			}
			
			joueur.piocherCartes();
			
			plateau.afficherPlateau();
		}
	}
}
