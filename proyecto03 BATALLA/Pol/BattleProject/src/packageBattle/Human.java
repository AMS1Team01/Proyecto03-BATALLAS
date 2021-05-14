package packageBattle;

public class Human extends Warrior { //Se crea la clase Human que extiende de la clase Warrior

	public Human(String name) { //Se crea el constructor
		super();
		setName(name);
		setStartingLife(50);//Se guarda la vida inicial para recuperarla en caso de repetir la partida
		setLife(getStartingLife());
		setStrength(5);	
		setDefence(3);
		setAgility(6);
		setVelocity(5);
	}
	
}