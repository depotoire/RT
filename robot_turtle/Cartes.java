package robot_turtle;

import java.util.ArrayList;
import java.util.Random;

public class Cartes {
	public Random rand = new Random(); //generateur de chiffre aleatoire
	
	public ArrayList<String> pioche = new ArrayList<>(); //pioche associé au joueur
	public ArrayList<String> hand = new ArrayList<>(); //main du joueur
	public ArrayList<String> programme = new ArrayList<>(); //programme du joueur
	
	
	public Cartes() {//prepare le paquet de cartes
		this.initialisationPioche();
	}

	public void initialisationPioche() { //initialise la pioche et pioche la main du joueur
		this.pioche.clear();
		
		for(int j=0; j<18;j++) {	//on ajoute 18 cartes Avancer (les bleues)
			this.pioche.add("A");
		}

		for(int j=0; j<8;j++) {		//on ajoute 8 cartes tourner à Gauche
			this.pioche.add("G");
		}

		for(int j=0; j<8;j++) {		//on ajoute 8 cartes tourner à Droite
			this.pioche.add("D");
		}

		for(int j=0; j<3;j++) {		//on ajoute 8 cartes Laser
			this.pioche.add("L");
		}
		
		this.mainJoueur();
	}
	
	public String piocherCarte() { //pioche une carte au hasard dans la pioche
		
		int size = this.pioche.size(); //nombre de carte dans la pioche
		int index = rand.nextInt(size); //indice d'un carte au hasard de la pioche
		
		if(size==0 || this.pioche.isEmpty()) { //on reinitialise la pioche s'il n'y a plus de carte a piocher
			initialisationPioche();
		}
		
		String cartePioche = pioche.remove(index); //carte piochée aleatoirement
		
		return cartePioche;
	}
	
public void mainJoueur() { // pioche des cartes de la pioche du joueur pour former sa main
		
		while (this.hand.size()<5) {//tant que le joueur n'a pas 5 cartes en main on pioche une carte
			this.hand.add(piocherCarte());
		}
		
	}
	
	public void afficherPioche() { //affiche la pioche
		
		System.out.print("size: " + this.pioche.size() + " ");
		
		for(String carte : this.pioche) {//on parcours les cartes de la pioche pour les afficher
			System.out.print(carte);
		}
		
		System.out.println("");
	}
	
public void afficherMainJoueur() { //affiche la main du joueur
		
		for(int i = 0; i<this.hand.size(); i++) {//on parcours les cartes de la pioche pour les afficher
			System.out.print(i+")" + this.hand.get(i) + " ");
		}
		
		System.out.println("");
	}


	
	public String prendreCarteMain(int indiceCarte) {//retire la carte de la main du joueur
		return this.hand.remove(indiceCarte);
	}
	
	public ArrayList<String> getHand() {
		return this.hand;
	}
	
	public void ajoutCarteProgramme(String carte) {//ajoute une carte au programme
		this.programme.add(carte);
	}
	
	

}
