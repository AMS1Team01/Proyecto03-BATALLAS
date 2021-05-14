package packageBattle;

public class Elf extends Warrior { //Se crea la clase Elf que extiende de la clase Warrior

	public Elf(String name) { //Se crea el constructor
		super();
		setName(name);
		setStartingLife(40);//Se guarda la vida inicial para recuperarla en caso de repetir la partida
		setLife(getStartingLife());
		setStrength(4);	
		setDefence(2);
		setAgility(7);
		setVelocity(7);
	}
	
}