package packageBattle;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Battle {

	
	
	public static void main(String[] args) {
		
		//Instanciar WarriorContainer
		var wac = new WarriorContainer();
		var allWarriors = wac.getWarriors();
		
		//Instanciar WeaponContainer
		var wec = new WeaponContainer();
		var allWeapons = wec.getWeapons();
		
		//Instanciar Random
		Random randomNumber = new Random();
		
		Elf pAttacker = new Elf("JoseColetas");
		Dwarf pDefender = new Dwarf("IwoGorras");
		
		int turn = 1;
		System.out.println(new String(new char[47]).replace("\0","=") + "BATALLA" + new String(new char[47]).replace("\0","="));

		
		//Elegimos quien va atacar por el metodo de quien mas velocidad tiene
		if(pAttacker.getSpeed() > pDefender.getSpeed()) {
			System.out.println(pDefender.getDefend(pAttacker, pAttacker.getAttack()));
		} else if(pAttacker.getSpeed() < pDefender.getSpeed()) {
			System.out.println(pAttacker.getDefend(pDefender, pDefender.getAttack()));
		} else {
			if(pAttacker.getAgility() > pDefender.getAgility()) {
				System.out.println(pDefender.getDefend(pAttacker, pAttacker.getAttack()));
			} else if(pAttacker.getAgility() < pDefender.getAgility()) {
				System.out.println(pAttacker.getDefend(pDefender, pDefender.getAttack()));
			} else {
				int numRandom = (int)(Math.random()*2);
				if (numRandom == 1) {
					System.out.println(pDefender.getDefend(pAttacker, pAttacker.getAttack()));
				} else {
					System.out.println(pAttacker.getDefend(pDefender, pDefender.getAttack()));
				}
			}
		}
		
		System.out.println("\n" + new String(new char[65]).replace("\0","*"));
		
		//Mientras los dos pokemons tienen vida, atacan uno a otro		
		while (pDefender.getLife() > 0 & pAttacker.getLife() > 0) {
			turn++;	
			if (pAttacker.getSpeed() <= pDefender.getSpeed()) {
				System.out.println(pAttacker.getDefend(pDefender, pDefender.getAttack()));
				Warrior aux = pAttacker;
				pAttacker = pDefender;
				pDefender = aux;
			} else {
				int numRandom = (int)(Math.random()*101);
				if ((pAttacker.getSpeed() - pDefender.getSpeed())*10 > numRandom) {
					System.out.println(pDefender.getDefend(pAttacker, pAttacker.getAttack()));
				} else {
					System.out.println(pAttacker.getDefend(pDefender, pDefender.getAttack()));
					Warrior aux = pAttacker;
					pAttacker = pDefender;
					pDefender = aux;
				}		
			}
			
			System.out.println("\n" + new String(new char[65]).replace("\0","*"));
		
		}
	
		//El ganador
		if (pAttacker.getLife() > pDefender.getLife()) {
		System.out.println("\nHa ganado " + pAttacker.getName() + "\n");
		}else {
		System.out.println("\nHa ganado " + pDefender.getName() + "\n");
		}
		System.out.println("Resum del combat");
		//Opcio tornar a inici o finalitzar aplicacio
	}
	
}

class Weapon extends WeaponContainer {
	
	private String name;
	private int strength;
	private int velocity;
	
	public Weapon() {
		
	}
	
	public Weapon(String name, int strength, int velocity) {
		super();
		this.name = name;
		this.strength = strength;
		this.velocity = velocity;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getVelocity() {
		return velocity;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
		
}

abstract class Warrior extends WarriorContainer { //Se crea la clase abstracta Pokemon implementando las interfaces Attackable y Defendible
	//Se definen los atributos de Pokemon
	private String name;
	private int startingLife;
	private int life;
	private int strength;
	private int defence;
	private int agility;
	private int velocity;
	private String url;
	private Weapon weapon;
	
	//Se definen los getters y setters de Warrior
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartingLife() {
		return startingLife;
	}

	public void setStartingLife(int startingLife) {
		this.startingLife = startingLife;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void resetStats() {
		this.setLife(this.getStartingLife());
	}
	
	
	
	public String getDefend(Warrior enemy, int enemyAttack) { //Se crea el metodo getDefend de la interface Defendible
		
		int attackDamage = 0;//El danyo final
		String phrase = "";//El mensaje
		
		//Si el numero aleatorio es mayor o igual que la precision, el ataque falla
		int precision = enemy.getAgility()*10;
		if ((int)(Math.random()*101) >= precision) {
			phrase = enemy.getName() + " ha fallado el ataque";
		} else {//Si no falla, depende de clase que nos ataca calculamos el danyo y damos el mensaje
			phrase = enemy.getName() + " ha acertado el ataque";
			
			//Si el numero aleatorio es mayor o igual que la precision, el ataque falla
			int evasion = this.getAgility();
			if ((int)(Math.random()*51) < evasion) {
				phrase += "\n" + this.getName() + " ha esquivado el ataque";
			} else {//Si no falla, depende de clase que nos ataca calculamos el danyo y damos el mensaje
				attackDamage = enemyAttack - this.getDefence();
				phrase += "\nThe defender " + this.getName() + " ha recibido " + attackDamage + " puntos de danyo";
				
				int lifePoints = this.getLife() - attackDamage;
				this.setLife(lifePoints);
								
				if (lifePoints <= 0) {//Si el danyo es mayor que los puntos de vida que tiene el defensor ponemos vida = 0 y damos el mensaje
					this.setLife(0);
					phrase += "\nAttacker's life: " + enemy.getLife();
					phrase += "\nDefender's life: " + this.getLife();
					phrase += "\n" + this.getName() + " se ha debilitado";
				} else {
					phrase += "\nAttacker's life: " + enemy.getLife();
					phrase += "\nDefender's life: " + this.getLife();
				}
				}
		
		}
		
		return phrase;
		
	}
	
	public int getAttack() { //Se crea el metodo getAttack
		int damage = getStrength();
					
		if (this.getWeapon().getName() == "Sword") {
			damage += 1;	
		} else if (this.getWeapon().getName() == "Axe") {
			damage += 3;
		} else if (this.getWeapon().getName() == "Double Swords") {
			damage += 2;
		} else if (this.getWeapon().getName() == "Scimitar") {
			damage += 1;
		} else if (this.getWeapon().getName() == "Bow") {
			damage += 1;
		} else if (this.getWeapon().getName() == "Katana") {
			damage += 1;
		} else if (this.getWeapon().getName() == "Two-handed ax") {
			damage += 5;
		}
		return damage;
	}
	
	public int getSpeed() { //Se crea el metodo getSpeed
		int speed = getVelocity();
		
		if (this.getWeapon().getName() == "Dagger") {
			speed += 3;	
		} else if (this.getWeapon().getName() == "Sword") {
			speed += 1;
		} else if (this.getWeapon().getName() == "Double Swords") {
			speed += 2;
		} else if (this.getWeapon().getName() == "Scimitar") {
			speed += 2;
		} else if (this.getWeapon().getName() == "Bow") {
			speed += 5;
		} else if (this.getWeapon().getName() == "Katana") {
			speed += 3;
		} else if (this.getWeapon().getName() == "Knive") {
			speed += 4;
		}
		return speed;
	}
	
}
