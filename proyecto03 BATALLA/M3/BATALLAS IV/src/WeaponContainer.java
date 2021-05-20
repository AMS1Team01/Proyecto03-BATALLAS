
import java.util.ArrayList;

public class WeaponContainer {

    ArrayList<Weapon> weapons;
    
    public WeaponContainer() {
        
    }
    
    public WeaponContainer (ArrayList<Weapon> weapons){
        this.weapons = weapons;
    }
    
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }
    
    public void add(Weapon weapon) {
    	this.weapons.add(weapon);
    }

}
