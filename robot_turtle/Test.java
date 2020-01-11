package robot_turtle;

import java.util.Scanner;

public class Test {
	
	
	
	public static void main(String[] args) {
		int nbrJoueurs = 2;
		Jeu jeu = new Jeu(nbrJoueurs);
		test(jeu);
		
	}
	
	
	public static void test(Jeu jeu) {
		Joueur joueur = jeu.joueurs.get(0);
		Plateau plateau = jeu.plateau;
		
		boolean continuer = true;
		System.out.println("0) jeu");
		System.out.println("1) mettre un mur");
		System.out.println("2) avancer");
		System.out.println("3) tirer laser");
		System.out.println("4) tourner");
		System.out.println("5) afficher plateau");
		System.out.println("6) afficher directions tortues");
		System.out.println("7) afficher tuiles separement");
		System.out.println("8) update position");
		System.out.println("9) changer joueur");
		plateau.afficherPlateau();
		while(continuer && !plateau.finDuJeu()) {
			Scanner scanner = new Scanner(System.in);
			
			int choix = scanner.nextInt();
			
			switch (choix) {
			case -1:
				continuer = false;
				break;
			case 0:
				jeu.run();
				break;
			case 1://mettre un mur
				joueur.actionPlacerMur();
				plateau.afficherPlateau();
				break;
			case 2://avancer
				joueur.getTortue().avancerTortue();
				plateau.afficherPlateau();
				System.out.println("joueur n"+ jeu.joueurs.indexOf(joueur) +" direction :"+ joueur.getTortue().getDirection());
				break;
			case 3://laser
				joueur.getTortue().tirerLaser();
				plateau.afficherPlateau();
				for (int i = 0; i < plateau.nbrJoueurs; i++) {
					System.out.println("direction tortue n°" + i + ": " + plateau.getTortue(i).getDirection() );
				}
				break;
			case 4://tourner
				System.out.println("1)Droite 2)Gauche");
				int DG = scanner.nextInt();
				if(DG == 1) {
					joueur.getTortue().tournerDroite();
				}
				else {
					joueur.getTortue().tournerGauche();
				}
				System.out.println("joueur n"+ jeu.joueurs.indexOf(joueur) +" direction :"+ joueur.getTortue().getDirection());
				break;
			case 5:
				plateau.afficherPlateau();
				break;
			case 6:
				for (int i = 0; i < plateau.nbrJoueurs; i++) {
					System.out.println("direction tortue n°" + i + ": " + plateau.getTortue(i).getDirection() );
				}
				break;
			case 7:
				plateau.afficherTortuesJoyeauxMurs();
				break;
			case 8:
				System.out.println("ligne puis colonne");
				int[] position11 = {scanner.nextInt(),scanner.nextInt()};
				joueur.tortue.updatePosition(position11);
				plateau.afficherPlateau();
				break;
			case 9:
				System.out.println("quel joueur?");
				for(int j = 0;j<plateau.nbrJoueurs;j++) {
					System.out.print(j);
				}
				int nJoueur = scanner.nextInt();
				joueur = jeu.joueurs.get(nJoueur);
				plateau.afficherPlateau();
				System.out.println("joueur n"+ jeu.joueurs.indexOf(joueur) +" direction :"+ joueur.getTortue().getDirection());
				break;
			
			
				
			default:
				break;
			}
		}
		System.out.println("vous avez quittez ou fin du jeu");
	}

	public void afficherPosition(int[] position) {
		System.out.println(position[0]+ "" + position[1]);
	}
}
