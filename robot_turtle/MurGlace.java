package robot_turtle;

public class MurGlace extends Tuile{

	public MurGlace(Plateau plateau, int[] position) {//initialise la tortue sur le plateau
		super(plateau, position,3);
		this.plateau.getMursGlace().add(this);
	}
	
	public void laser(Tortue tortue) {
		System.out.println("mur glace detruit " + this.getPosition()[0] + this.getPosition()[1]);
		this.plateau.tuiles.remove(this);
		
	}
}
