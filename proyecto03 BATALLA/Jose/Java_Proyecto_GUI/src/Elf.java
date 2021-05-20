
public class Elf extends Warrior { //Se crea la clase Elf que extiende de la clase Warrior

	public Elf(int id, String name, String race, int hp, int strength, int velocity, int agility, int defense, String url) {
		super();
		this.setWarrior_id(id);
		this.setName(name);
		this.setRace(race);
		this.setStartingLife(hp);
		this.setLife(getStartingLife());
		this.setStrength(strength);
		this.setVelocity(velocity);
		this.setAgility(agility);
		this.setDefense(defense);
		this.setUrl(url);
		
		
	}
	public Elf() {
		super();
		
	}
    
}
