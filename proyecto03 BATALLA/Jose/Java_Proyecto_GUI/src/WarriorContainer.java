
import java.util.ArrayList;

public class WarriorContainer {

    ArrayList<Warrior> warriors;
    
    
    public WarriorContainer() {
        
    }
    
    public WarriorContainer (ArrayList<Warrior> warriors){
        this.warriors = warriors;
    }

    public ArrayList<Warrior> getWarriors() {
        return warriors;
    }
    
    public void add(Warrior warrior) {
    	this.warriors.add(warrior);
    }

}