import java.util.Random;

/* Warrior Class made to instance the Warrior objects and the methods tied to the characters.
 * */
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
	
	/*This method sets back the life points to the maximum*/
	public void resetStats() {
		this.setLife(this.getStartingLife());
	}
	
	/*This method handles the probabilities of both launching an attack and defending that attack. 
	 * If attack is successfully launched and is not defended, damage will be dealt.
	 * 
	 * The damage is calculated by the difference between attacker's enemyAtttack and a 
	 * random defense within the defender's defense attribute range.
	 * */
	public String getDefend(Warrior enemy, int enemyAttack) { //Defending function
		Random randomNumber = new Random();
		
		int attackDamage = 0;//value to store the final damage
		String phrase = "";// value to store the appropriate output.
		
		//If the random number is equal or higher than the precision, the attack fails.
		int precision = enemy.getAgility()*10;
//		System.out.println("Attacker's precision is: " + precision );
		if ((int)(Math.random()*101) >= precision) {
			phrase = enemy.getName() + " can't find an opening to attack!";
		} else {//If is successfull, we calculate the damage regarding the race and weapon.
			phrase = enemy.getName() + " is attacking!";
			
			/* If the random number is is equal or higher than the evasion, 
			 the attack is defended and deals no damage.*/
			int evasion = this.getAgility();
//			System.out.println("Defender's evasion is: " + evasion);
			if ((int)(Math.random()*51) < evasion) {
				phrase += "\n" + this.getName() + " dodged!";
			} else {// If success, we calculate the final damage using the attack damage and the defense
				int defense = randomNumber.nextInt(this.getDefense());
				attackDamage = enemyAttack - defense;
				phrase += "\nHIT! " + this.getName() + " lost " + attackDamage + " life points";
				
				int lifePoints = this.getLife() - attackDamage;
				this.setLife(lifePoints);
								
				if (lifePoints <= 0) {//Si el danyo es mayor que los puntos de vida que tiene el defensor ponemos vida = 0 y damos el mensaje
					this.setLife(0);
					phrase += "\n"+ enemy.getName() +"'s life: " + enemy.getLife();
					phrase += "\n"+ this.getName() +"'s life: " + this.getLife();
					phrase += "\n" + this.getName() + " is DEAD!!";
				} else {
					phrase += "\n"+ enemy.getName() +"'s life: " + enemy.getLife();
					phrase += "\n"+ this.getName() +"'s life: " + this.getLife();
				}
				}
		}
		return phrase;
		
	}
	
	/* This function returns the total attack value which is the sum of warrior and weapon strength attributes*/
	public int getAttack() { //Attack function
		int damage = this.getStrength() + this.getWeapon().getStrength();
//		System.out.println("Este personaje " + this.getName() + " hace " + damage + " puntos de danyo");
		return damage;
					

	}
	
	/* This function returns the total speed value which is the sum of warrior and weapon velocity attributes*/
	public int getSpeed() { //Se crea el metodo getSpeed
		int speed = this.getVelocity() + this.getWeapon().getVelocity();
//		System.out.println( "este personaje " + this.getName() + " tiene : " + speed + " Velocidad.");
		return speed;
		

	}

	

	

	@Override
	public String toString() {
		return "\n\nWarrior\nwarrior_id \t=\t " + warrior_id + "\nname \t\t=\t " + name + "\nrace \t\t=\t " + race + "\nstartingLife \t=\t "
				+ startingLife + "\nlife \t\t=\t " + life + "\nstrength \t=\t " + strength + "\ndefence \t=\t " + defence
				+ "\nagility \t=\t " + agility + "\nvelocity \t=\t " + velocity + "\nurl \t\t=\t " + url + "\nweapon \t\t=\t " + weapon;
	}
	
	
	
	
	
	
	
}