package robot_turtle;

public class Joyau extends Tuile{
	public Joyau(Plateau plateau, int[] position) {//initialise la tortue sur le plateau
		super(plateau, position,1);
		this.plateau.getJoyaux().add(this);
	}
	
	@Override
	public void collision(Tortue tortue) {
		if(this.plateau.joyaux.indexOf(this) != this.plateau.tortues.indexOf(tortue)) {
			tortue.tournerGauche();
			tortue.tournerGauche();
			System.out.println("direction: " + tortue.getDirection());
		}
		else {
			tortue.updatePosition(this.position);
		}
	}
	
	public void laser(Tortue tortue) {
		tortue.tournerGauche();
		tortue.tournerGauche();
		System.out.println("direction: " + tortue.getDirection());
	}
}
