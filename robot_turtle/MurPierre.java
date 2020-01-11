package robot_turtle;

public class MurPierre extends Tuile{
	public MurPierre(Plateau plateau, int[] position) {//initialise la tortue sur le plateau
		super(plateau, position,2);
		this.plateau.getMursPierre().add(this);
	}
}
