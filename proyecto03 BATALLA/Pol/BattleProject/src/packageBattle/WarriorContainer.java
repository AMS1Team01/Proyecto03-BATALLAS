package packageBattle;

import java.util.ArrayList;

public class WarriorContainer {

	private ArrayList<Warrior> warriors;
	
	public WarriorContainer() {
		
	}
	
	public WarriorContainer (ArrayList<Warrior> warriors){
		this.warriors = warriors;
	}

	public ArrayList<Warrior> getWarriors() {
		return warriors;
	}

}
