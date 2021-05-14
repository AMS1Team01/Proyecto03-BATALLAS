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
		
		//Se crean las arrays y variables que se van a utilizar
		Scanner input = new Scanner(System.in);
		ArrayList<String> menu = new ArrayList<String>();
		menu.add("Play");
		menu.add("Choose warrior");
		menu.add("Choose weapon");
		menu.add("Exit");
		
		//Se pide datos del usuario
		String nickNamePlayer = input.next();		
			
		//Se crea el bot y se le asigna un nombre y un arma aleatorios de las ArrayList warriors y weapons
		String nickName = "Bot";
		if (nickName == nickNamePlayer) {
			nickName = "CPU";
		}
		User bot = new User(nickName, allWarriors.get(randomNumber.nextInt(11)), allWeapons.get(randomNumber.nextInt(8)));
		System.out.println(new String(new char[41]).replace("\0","=") + "BATALLA" + new String(new char[41]).replace("\0","="));
				
		//Se crea el usuario
		nickName = nickNamePlayer;
		User player = new User(nickName, allWarriors.get(), allWeapons.get(randomNumber.nextInt(8)));
		ArrayList<User> players = new ArrayList<User>();
		Warrior pAttacker;
		Warrior pDefender;
		while (true) {
			//Se imprime el menu utilizando una array, porque cuando acaba la primera partida se anyade la opcion de repetir 
			for (String optionMenu : menu) {
				System.out.println(menu.indexOf(optionMenu)+1 + ". " + optionMenu);
			}
			
			//Se comprueba lo que pone el usuario. Si no es correcto, sale el mensaje de error correspondiente
			if (input.hasNextInt()) {
	        	int optionMenu  = input.nextInt();
	        	if (optionMenu == 1) {
	        		System.out.println("\nTu oponente va ser el " + bot.getNickName());
	        		
	        		//Se crea el equipo del usuario
    				if (warrior.race_id == 1) {
    					player.setWarrior(new Dwarf(input.next()));
    				} else if (warrior.race_id == 2) {
    					player.setWarrior(new Human(input.next()));
    				} else {
    					player.setWarrior(new Elf(input.next()));
    				}
    				
	    		
    				//Creamos el equipo del bot
	        		do {
	        			int warriorId = (int)(Math.random()*11 + 1);
	        		}
	        		while (warriorId == warriorPlayerId);
	        		
	    			if (warrior(warriorId).race_id == 1) {
    					bot.setWarrior(new Dwarf(input.next()));
	    			} else if (warrior(warriorId).race_id == 2) {
    					bot.setWarrior(new Human(input.next()));
    				} else {
    					bot.setWarrior(new Elf(input.next()));
    				}
	    				

	    			//Elegimos quien va atacar por el metodo de quien mas velocidad tiene
	    			if(player.getWarrior().getVelocity() + player.getWeapon().getVelocity() > bot.getWarrior().getVelocity() + bot.getWeapon().getVelocity()) {
	    				players.add(player);
	    				players.add(bot);
	    			} else if(player.getWarrior().getVelocity() + player.getWeapon().getVelocity() < bot.getWarrior().getVelocity() + bot.getWeapon().getVelocity()) {
	    				players.add(bot);
	    				players.add(player);
	    			} else {
	    				if(player.getWarrior().getAgility() > bot.getWarrior().getAgility()) {
		    				players.add(player);
		    				players.add(bot);
		    			} else if(player.getWarrior().getAgility() < bot.getWarrior().getAgility()) {
		    				players.add(bot);
		    				players.add(player);
		    			} else {
		    				int numRandom = (int)(Math.random()*2);
		    				if (numRandom == 1) {
		    					players.add(player);
			    				players.add(bot);
		    				} else {
		    					players.add(bot);
			    				players.add(player);
		    				}
		    			}
	    			}
	    		//Opcion de salir
	        	}else if(optionMenu == 4) {
	        		break;
	        	
	        	//}else if(optionMenu == 2 && menu.size() == 3) {
	        		
	        	//Mensaje de numero incorrecto
	        	}else {
	        		System.out.println("No existe la opcion " + optionMenu);
	        		continue;
	        	}
			//Mensaje de que lo que ha puesto el usuario no es un numero
			}else {
				System.out.println("Debe ser un numero, la opcion " + input.next() + " no es valida!");
				continue;
			}
						
			int turn = 0;
			boolean called1 = false;
			boolean called2 = false;
			//Mientras los dos pokemons tienen vida, atacan uno a otro
			turn++;
			System.out.println(pDefender.getDefend(pAttacker, pAttacker.getAttack()));
			System.out.println("\n" + new String(new char[65]).replace("\0","*"));
			//Comprobamos  que el pokemon defensor  no ha muerto del ataque anterior y puede atacar
			
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
				
		}
	
		//El ganador
		if (player.getWarrior().getLife() > bot.getWarrior().getLife()) {
		System.out.println("\nHa ganado " + player.getNickName() + "\n");
		}else {
		System.out.println("\nHa ganado " + bot.getNickName() + "\n");
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
