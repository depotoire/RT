package robot_turtle;

import java.util.ArrayList;

public class Plateau {
	public static String[][] plateau = new String[8][8];
	public ArrayList<Tortue> tortues = new ArrayList<>();
	public ArrayList<Joyau> joyaux = new ArrayList<>();
	public ArrayList<Tuile> mursPierre = new ArrayList<>();
	public ArrayList<Tuile> mursGlace = new ArrayList<>();
	public ArrayList<Tuile> tuiles = new ArrayList<>();
	

	static int[][][] toutesLesPositionsInitiales = {{{0}},{{1}},{{0,1},{0,5}},{{0,0},{0,3},{0,6}},{{0,0},{0,2},{0,5},{0,7}}};
	public int nbrJoueurs;
	

	
	public Plateau(int nbrJoueurs) {//initialise a le plateau au debut de la partie
		//on nettoie le plateau
		tuiles.clear();
		tortues.clear();
		
		this.nbrJoueurs = nbrJoueurs; 
		
		int[][][] toutesLesPositionsJoyaux = {{{0}},{{1}},{{7,3},{7,3}},{{7,0},{7,3},{7,6}},{{7,1},{7,1},{7,6},{7,6}}}; //toutes les positions de joyaux par nombre de joueurs
		
		for(int[] positionJoyeau : toutesLesPositionsJoyaux[nbrJoueurs]) { //on enregistre la position des joyaux pour cette partie
			Joyau joyeau = new Joyau(this, positionJoyeau);
		}
				
		for(int i=0;i<this.nbrJoueurs;i++) { //creer les tortues
			Tortue tortue = new Tortue(this,Plateau.toutesLesPositionsInitiales[this.nbrJoueurs][i]);
		}
		
		if(nbrJoueurs != 4) {
			for (int i = 0; i < 8; i++) {
				int[] position = {i,7};
				MurPierre mur = new MurPierre(this,position);
			}
		}
		MAJPlateau();
	}
	
	public ArrayList<Tuile> getTuiles() {
		return this.tuiles;
	}
	public ArrayList<Tortue> getTortues() {
		return this.tortues;	
	}
	public ArrayList<Tuile> getMursPierre() {
		return this.mursPierre;
	}
	public ArrayList<Tuile> getMursGlace() {
		return this.mursGlace;
	}
	public ArrayList<Joyau> getJoyaux() {
		return this.joyaux;
	}
	public int getNbrJoueurs() {
		return this.nbrJoueurs;
	}
	
	public void MAJPlateau() {//met a jour le plateau pour lafficher
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				plateau[i][j]=" ";
			}
		}
		
		for(Tuile tuile : this.tuiles) {
			int[] positionTuile = tuile.getPosition();
			
			plateau[positionTuile[0]][positionTuile[1]] = tuile.getType();
		}
	}
	
	public void afficherPlateau() {//affice le plateau
		this.MAJPlateau();
		
		System.out.println("  01234567");
		System.out.println(" +--------+");

		for (int i=0; i<8;i++) {
			System.out.print(i+"|");
			for (int j=0; j<8; j++) {
				System.out.print(plateau[i][j]);
			}
			System.out.println("|");
		}
		System.out.println(" +--------+");
	}
	
	public Tortue getTortue(int joueur) {//renvoie la tortue du joueur numero x
		return this.tortues.get(joueur);
	}
	
	public boolean memePositions(int[] position1, int[] position2) {
		return position1[0] == position2[0] && position1[1] == position2[1]; 
	}
	
	public Tuile getTuileByPosition(int[] position) {//renvoie la tuile assiciée a la position, renvoie null sinon
		for(Tuile tuile : this.tuiles) {
			if( this.memePositions(position,tuile.getPosition()) ) {
				return tuile;
			}
		}
		return null;
	}
	
	public int[] positionCaseEnFace(int[] position, int direction) {//donne la position de la case en face si on sort pas du plateau, renvoie la case actuelle sinon
		int positionFuture0 = position[0];
		int positionFuture1 = position[1];
		
		if (direction == 0 && position[0]!=0) {//si N et pas au bord du plateau
			positionFuture0 -= 1; //on monte d'une ligne donc -1 ligne
			int[] positionFuture = {positionFuture0,positionFuture1};
			return positionFuture;
		} 
		
		else if (direction==1 && position[1]!=0) {//si O et pas au bord du plateau
			positionFuture1 -= 1; //on descend d'une colonne (on se deplace selon la ligne)
			int[] positionFuture = {positionFuture0,positionFuture1};
			return positionFuture;
		}
		
		else if (direction==2 && position[0]!=7) {//si S et pas au bord du plateau
			positionFuture0 += 1; //on descend d'une ligne donc +1 ligne
			int[] positionFuture = {positionFuture0,positionFuture1};
			return positionFuture;
		}
		else if (direction==3 && position[1]!=7) {//si E et pas au bord du plateau
			positionFuture1 += 1; //on monte d'une colonne (on reste selon la ligne)
			int[] positionFuture = {positionFuture0,positionFuture1};
			return positionFuture;
		}
		else {
			int[] positionFuture = {positionFuture0,positionFuture1};
			return positionFuture;
		}
	}
	
	public boolean positionOccupe(int[] position) {//revoie true si occupée, false si libre
		for(Tuile tuile : this.tuiles) {
			if( this.memePositions(position, tuile.getPosition()) ) {
				return true;
			}
		}
		return false;
	}
	
	public boolean positionOccupeParMur(int[] position) {//revoie true si occupée, false si libre
		for(Tuile mur : this.mursPierre) {
			if( memePositions(position, mur.getPosition()) ) {
				return true;
			}
		}
		for(Tuile mur : this.mursGlace) {
			if(memePositions(position, mur.getPosition())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean positionBloque(int[] positionTestee) {//test si la case ou on veut mettre le mur bloque le joyeau
		return false;
	}


	public void afficherTuiles() {
		for(Tuile tuile : this.tuiles) {
			System.out.print(tuile.getType() + tuile.getPosition()[0] + tuile.getPosition()[1] + "/");
		}
		System.out.println();
		
	}
	
	public void afficherTortuesJoyeauxMurs() {
		System.out.print("joyaux: ");
		for(Tuile joyeau : this.joyaux) {
			System.out.print("" + joyeau.getPosition()[0] + joyeau.getPosition()[1] + "/");
		}
		System.out.println();
		
		System.out.print("tortues: ");
		for(Tortue tortue : this.tortues) {
			System.out.print("" + tortue.getPosition()[0] + tortue.getPosition()[1] + tortue.getDirection() + "/");
		}
		System.out.println();
		
		System.out.print("murs pierre: ");
		for(Tuile mur : this.mursPierre) {
			System.out.print("" +mur.getPosition()[0] + mur.getPosition()[1] + "/");
		}
		System.out.println();
		System.out.print("murs glace: ");
		for(Tuile mur : this.mursGlace) {
			System.out.print("" +mur.getPosition()[0] + mur.getPosition()[1] + "/");
		}
		System.out.println();
		
	}
	
	public void resetPositionTortue(Tortue tortue) {
		int indiceTortue = this.tortues.indexOf(tortue);
		tortue.updatePosition(Plateau.toutesLesPositionsInitiales[this.nbrJoueurs][indiceTortue]);
	}
	
	public boolean finDuJeu() {
		for(int i=0;i<this.nbrJoueurs;i++) {
			if(this.joyaux.get(i).getPosition()[0] == this.tortues.get(i).getPosition()[0] && this.joyaux.get(i).getPosition()[1] == this.tortues.get(i).getPosition()[1]) {//une tortue est sur un joyeau
				System.out.println();
				System.out.println("le joueur n°" + i +" a gagné");
				return true;
			}
		}
		return false;
	}

	
}


