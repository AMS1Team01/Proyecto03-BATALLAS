


abstract class Warrior extends WarriorContainer { //Se crea la clase abstracta Pokemon implementando las interfaces Attackable y Defendible
	//Se definen los atributos de Pokemon
	private int warrior_id;
	private String name;
	private String race;
	private int startingLife;
	private int life;
	private int strength;
	private int defence;
	private int agility;
	private int velocity;
	private String url;
	private Weapon weapon;
	
	//Se definen los getters y setters de Warrior
	
	
	public int getWarrior_id() {
		return warrior_id;
	}

	public void setWarrior_id(int warrior_id) {
		this.warrior_id = warrior_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
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

	public int getDefense() {
		return defence;
	}

	public void setDefense(int defence) {
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
				attackDamage = enemyAttack - this.getDefense();
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

	

	

	@Override
	public String toString() {
		return "\n\nWarrior\nwarrior_id \t=\t " + warrior_id + "\nname \t\t=\t " + name + "\nrace \t\t=\t " + race + "\nstartingLife \t=\t "
				+ startingLife + "\nlife \t\t=\t " + life + "\nstrength \t=\t " + strength + "\ndefence \t=\t " + defence
				+ "\nagility \t=\t " + agility + "\nvelocity \t=\t " + velocity + "\nurl \t\t=\t " + url + "\nweapon \t\t=\t " + weapon;
	}
	
	
	
	
	
	
	
}