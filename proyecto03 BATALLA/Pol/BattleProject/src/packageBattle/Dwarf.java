package packageBattle;

public class Dwarf extends Warrior { //Se crea la clase Dwarf que extiende de la clase Warrior

	public Dwarf(String name) { //Se crea el constructor
		super();
		setName(name);
		setStartingLife(60);//Se guarda la vida inicial para recuperarla en caso de repetir la partida
		setLife(getStartingLife());
		setStrength(6);	
		setDefence(4);
		setAgility(5);
		setVelocity(3);
	}
	
}