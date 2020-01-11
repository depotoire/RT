package robot_turtle;

public class Tortue extends Tuile{
	public static char[] directionsPossibles = {'N','O','S','E'}; //liste lisible de direction (tourner a gauche = +1)
	public int direction; //indice de la direction actuelle de la tortue
	public int indiceTortue;
	
	public Tortue(Plateau plateau, int[] position) {//initialise la tortue sur le plateau
		super(plateau,position,0);
		this.plateau.getTortues().add(this);
		this.indiceTortue = this.plateau.getTortues().indexOf(this);
		this.type = Integer.toString(this.indiceTortue);
		this.direction = 2;
	}
	
	public char getDirection() {//renvoie la direction en lettre
		return Tortue.directionsPossibles[this.direction];
	}
	public int getIndice() {//renvoie la direction en lettre
		return this.indiceTortue;
	}
	
	
	public void tournerGauche() {//tourne a gauche
		this.direction = (this.direction+1)%4;
	}
	
	public void tournerDroite() {//tourne a droite
		this.direction = (this.direction+3)%4;
	}
	
	@Override
	public void collision(Tortue tortue) {//gere la collision quand on lui rentre dedans
		this.reset();
		tortue.reset();
		System.out.println("colision, direction autre tortue: " + tortue.getDirection());
	}
	
	@Override
	public void laser(Tortue tortue) {//reagis au laser
		if (this.plateau.getNbrJoueurs() == 2) {
			this.tournerGauche();
			this.tournerGauche();
			System.out.println("direction tortue touchée: " + this.getDirection());
		}
		else {
			this.reset();
		}
	}
	
	public int[] positionCaseEnFace() {//donne la position de la case en face de la tortue s'il y en a une, renvoie la case actuelle sinon
		return plateau.positionCaseEnFace(this.position, this.direction);
	}
	
	public void updatePosition(int[] position) {//MAJ la position de la tortue avec la position entree
		this.position = position;
	}
	
	public void avancerTortue() { //avance si la tortue le peut et gere la collision sinon
		int[] positionFuture = this.positionCaseEnFace();
		
		if( plateau.memePositions(positionFuture, this.position) ) {//si la tortue est au bord du plateau
			this.reset();
			System.out.println("heurte le bord, direction: " + this.getDirection());
		}
		
		else {//s'il y a une case en face de la tortue
			if (plateau.positionOccupe(positionFuture)) {//si la case est occupée on gere la collision
				Tuile tuile = plateau.getTuileByPosition(positionFuture);
				tuile.collision(this);
				System.out.println("la case en face est occupée, direction: " + this.getDirection());
				
			}
			else {//si la case est libre on se deplace
				this.updatePosition(positionFuture);
			}
		}
	}
	
	private void reset() {
		this.plateau.resetPositionTortue(this);
		this.direction = 2;
	}


	public int[] positionTuileEnFace() { //renvoie la position du premiere objet en face de la tortue sil y en a un
		int[] positionTeste = this.getPosition( );
		
		while( !plateau.memePositions(plateau.positionCaseEnFace(positionTeste, this.direction),positionTeste) ) {//tant qu'il y a une case en face
			System.out.println(positionTeste + "/" + plateau.positionCaseEnFace(positionTeste, this.direction));
			
			positionTeste = plateau.positionCaseEnFace(positionTeste, this.direction);
			if(plateau.positionOccupe(positionTeste)) {
				return positionTeste;
			}
		}
		return null;
	}
	
	public void tirerLaser() {//tire un laser et gere si une yuile a été touchée
		int[] positionTuileEnFace = this.positionTuileEnFace();
		if(positionTuileEnFace!=null) {//s'il y a un objet en face de la tortue
			Tuile tuile = plateau.getTuileByPosition(positionTuileEnFace);
			tuile.laser(this);
			System.out.println("direction: " + this.getDirection());
		}
	}
	
	
	public void afficherPosition(int[] position) {
		System.out.println(position[0]+ "" + position[1]);
	}
	
}
