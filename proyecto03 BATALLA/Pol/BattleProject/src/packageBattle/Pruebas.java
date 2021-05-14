package packageBattle;
import java.util.Random;
public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var wc = new WarriorContainer();
		var guerreros = wc.getWarriors();
		Random numAleatorio = new Random();
		
		System.out.println(numAleatorio.nextInt(11));
		System.out.println();
		System.out.println(guerreros.get(1));
//		for (String guerrero : guerreros) {
//			System.out.println(guerrero);
//		}
	}
	

}
