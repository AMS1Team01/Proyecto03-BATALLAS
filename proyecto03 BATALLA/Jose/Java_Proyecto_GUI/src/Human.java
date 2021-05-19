
public class Human extends Warrior {

	
	public Human(int id, String name, String race, int hp, int strength, int velocity, int agility, int defense, String url) {
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
	public Human() {
		super();
		
	}
	
	 //Se crea la clase Human que extiende de la clase Warrior

//    public Human() { //Se crea el constructor
//        super();
//        setName();
//        setStartingLife(50);//Se guarda la vida inicial para recuperarla en caso de repetir la partida
//        setLife(getStartingLife());
//        setStrength(5);    
//        setDefence(3);
//        setAgility(6);
//        setVelocity(5);
//    }
    
    
    
}