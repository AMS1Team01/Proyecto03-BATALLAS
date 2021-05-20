
class Weapon extends WeaponContainer {
    
	private int weapon_id;
    private String name;
    private int strength;
    private int velocity;
    private String weapon_race;
    private String url;
    
    public Weapon() {
        
    }
    
    public Weapon(int id, String name, int strength, int velocity, String weapon_race, String url) {
        super();
        this.weapon_id = id;
        this.name = name;
        this.strength = strength;
        this.velocity = velocity;
        this.weapon_race = weapon_race;
        this.url = url;
    }

    public int getWeapon_id() {
		return weapon_id;
	}

	public void setWeapon_id(int weapon_id) {
		this.weapon_id = weapon_id;
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
    
	public String getWeapon_race() {
		return weapon_race;
	}

	public void setWeapon_race(String weapon_race) {
		this.weapon_race = weapon_race;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "\nWeapon \n[weapon_id\t=\t" + weapon_id + "\nname\t\t=\t" + name + "\nstrength\t=\t" + strength
				+ "\nvelocity\t=\t" + velocity + "\nweapon_race\t=\t" + weapon_race + "\nurl\t\t=\t" + url + "]\n";
	}
	
	
        
}
