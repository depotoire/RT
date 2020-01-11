package robot_turtle;

public class Tuile {
	public Plateau plateau;
	public String type;
	public int[] position;
	
	public Tuile(Plateau plateau,int[] position,int type) {//creer une tuile 0T 1Joyeau 2P 3G
		this.plateau = plateau;
		this.position = position;
		if(type == 0) {
			this.type = "O";
		}
		else if(type == 1) {
			this.type = "J";
		}
		else if(type == 2) {
			this.type = "P";
		}
		else if(type == 3) {
			this.type = "G";
		}
		this.plateau.getTuiles().add(this);
	}
	
	public int[] getPosition() {
		return this.position;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void collision(Tortue tortue) {
		tortue.tournerGauche();
		tortue.tournerGauche();
	}

	public void laser(Tortue tortue) {
		
	}
	
	
}
