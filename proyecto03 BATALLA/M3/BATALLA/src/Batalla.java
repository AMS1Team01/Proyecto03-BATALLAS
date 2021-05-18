import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/*Battle Class contains all the methods to develop the combat phase
 * 
 * It allows to separate the logic rules in methods that can be easily improved or modified 
 * regarding future development and upcoming features
 * 
 * ---------------------------------------------------------------------------
 * Main Methods
 * ---------------------------------------------------------------------------
 * 
 * 1- setOrder
 * 2- fight
 * 
 * Aux Methods
 * ---------------------------------------------------------------------------
 * 1- playTurn
 * 2- playTurnBot
 * 3- changeTurn
 * 4- changeTurnBot
 * 
 * */


public class Batalla {
	
	/*This function returns the order value being 0 or 1, to determine the initial order 
	 * and who will attack first
	 * */
	protected static int setOrder(Warrior player, Warrior bot) {
		if(player.getSpeed() > bot.getSpeed()) {
			return  0;
		} else if(player.getSpeed() < bot.getSpeed()) {
			return 1;
		} else {
			if(player.getAgility() > bot.getAgility()) {
				return 0;
			} else if(player.getAgility() < bot.getAgility()) {
				return 1;
			} else {
				int numRandom = (int)(Math.random()*2);
				if (numRandom == 1) {
					return  0;
				} else {
					return 1;
				}
			}
		}
	}
	
	
	/*This method stores all the fight loop methods and functions.
	 * works with order and turn values as the attacks are not tied 
	 * to turn, as the characters have the chance to attack again 
	 * in next turn, thus manipulating this 2 variables is necessary.
	 * 
	 * */
	protected static void fight(Warrior player, Warrior bot) {
		int turn = 0;
		int order = 0;
		while (player.getLife() > 0 & bot.getLife() > 0) {
			turn++;	
			boolean juega = false;
			System.out.println("Turn " + turn);
			System.out.println("Order " + order);
			if (order%2 == 0) {
				Thread.currentThread().interrupt();
				playTurn(player, bot);	
				order = changeTurn(player, bot, order, turn);
//				
			}
			if (order%2 == 1 && (player.getLife() > 0 & bot.getLife() > 0)) {
				turn++;
				System.out.println("Turn " + turn);
				System.out.println("Order " + order);
				Thread.currentThread().interrupt();
				playTurnBot(bot, player);
				order = changeTurnBot(bot, player, order, turn);
			}
			
			
		}
		System.out.println("Combat Result");
		//El ganador
		if (player.getLife() > bot.getLife()) {
		System.out.println("\n" + player.getName() + " wins!\n");
		}else {
		System.out.println("\n" + bot.getName() + " wins!\n");
		}
		
	}
	/* This method prints the turn header and call the methods to attack and defend.*/
	protected static void playTurn(Warrior player, Warrior bot) {
		System.out.println(new String(new char[20]).replace("\0","=") + player.getName() + "'s turn" +new String(new char[20]).replace("\0","="));
		System.out.println(bot.getDefend(player, player.getAttack()));
		System.out.println("\n" + new String(new char[60]).replace("\0","*") + "\n");
	}
	/* This method is the switch for player and bot roles when calling attack and defend methods.*/
	protected static void playTurnBot(Warrior bot, Warrior player) {
		System.out.println(new String(new char[20]).replace("\0","=") + bot.getName() + "'s turn" + new String(new char[20]).replace("\0","="));
		System.out.println(player.getDefend(bot, bot.getAttack()));
		System.out.println("\n" + new String(new char[60]).replace("\0","*") + "\n");
	}
	
	
	/* This function checks conditions after the attack-defend phase, 
	 * and determines in the character will attack again or roles will be switched.
	 * it retunrs the order value incremented by 1 or if switch conditions are true, 
	 * or save value if false, meaning the chracter will attach again.*/
	protected static int changeTurn(Warrior player, Warrior bot, int order, int turn) {
		//if (order%2 == 0) {
			if (player.getSpeed() <= bot.getSpeed()) { 
		
			order++;
			turn++;
		} else {
			int numRandom = (int)(Math.random()*101);
			if ((player.getSpeed() - bot.getSpeed())*10 <= numRandom) {
				order++;
				turn++;
			} else { // En caso que no se cumplan las condiciones de cambio el personaje puede atacar de nuevo.
				System.out.println(player.getName() + " is faster than " + bot.getName() + "!! Another chance to attack!!");
			}
		} return order;
//		} 
		/*if (order%2 == 1 && (player.getLife() > 0 & bot.getLife() > 0)) {
			if (bot.getSpeed() <= player.getSpeed()) {
				order++;
				turn++;
			} else {
				int numRandom = (int)(Math.random()*101);
				if ((bot.getSpeed() - player.getSpeed())*10 <= numRandom) {
					order++;
					turn++;
				} else { // En caso que no se cumplan las condiciones de cambio el personaje puede atacar de nuevo.
					System.out.println(bot.getName() + " es más rapido que " + player.getName() + "!! Tiene otra oportunidad de ataque!");
				}		
			}
		}*/
		}
	
	/* This function returns the order value after checking the switch roles conditions.
	 * */
	protected static int changeTurnBot(Warrior bot, Warrior player, int order, int turn) {
		if (bot.getSpeed() <= player.getSpeed()) {
			order++;
			turn++;
		} else {
			int numRandom = (int)(Math.random()*101);
			if ((bot.getSpeed() - player.getSpeed())*10 <= numRandom) {
				order++;
				turn++;
			} else { // En caso que no se cumplan las condiciones de cambio el personaje puede atacar de nuevo.
				System.out.println(bot.getName() + " es más rapido que " + player.getName() + "!! Tiene otra oportunidad de ataque!");
			}		
		} return order;
	}
		
	
	public static void main(String[] args) {
		new MySQLConnection(MySQLConnection.urlDatos, MySQLConnection.usuario, MySQLConnection.pass);
		
		ArrayList<Warrior> warriors = new ArrayList<Warrior>();
		MySQLConnection.fillWarriorContainer(new WarriorContainer(warriors));
		//System.out.println(warriors.toString());
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		MySQLConnection.fillWeaponContainer(new WeaponContainer(weapons));
		//System.out.println(weapons.toString());
		Scanner sc = new Scanner(System.in);
		String player_name = "";
		String continuar = "";
		
		while (player_name.contentEquals("")) {
			System.out.println("Introduce Jugador");
			if (sc.hasNext()) {
				player_name = sc.next();
			}
		}
		
		//String player_name = "Iwo Test";
		int total_points = 0;
		int combates = 0;
		int ganadas = 0;
		
		Random randomNumber = new Random();
		
		int index = randomNumber.nextInt(9);
		Warrior player = warriors.get(index);
		index = randomNumber.nextInt(9);
		player.setWeapon(weapons.get(index));
		System.out.println("The player selected is: " + player.toString());
		
		do {
			index = randomNumber.nextInt(9);
			Warrior bot = warriors.get(index);
			
			while (player == bot) {
				index = randomNumber.nextInt(9);
				bot = warriors.get(index);
			}
			
		
			index = randomNumber.nextInt(9);
			bot.setWeapon(weapons.get(index));
			while (!bot.getWeapon().getWeapon_race().contains(bot.getRace())) {
				index = randomNumber.nextInt(9);
				bot.setWeapon(weapons.get(index));
			}
			
			System.out.println("The player selected is: " + player.toString());
			System.out.println("The opponent is " + bot.toString());
			
			System.out.println(new String(new char[20]).replace("\0","=") + "BATALLA" + new String(new char[20]).replace("\0","="));
			combates++;
			setOrder(player, bot);
			
			fight(player, bot);
			
			int injuries_caused = Math.abs(bot.getLife() - bot.getStartingLife());
			//injuries_caused = Math.abs(injuries_caused);
			System.out.println("injuries caused " + injuries_caused);
			int injuries_suffered = Math.abs(player.getLife() - player.getStartingLife());
			System.out.println("injuries suffered " + injuries_suffered);
			int battle_points = Math.abs(((player.getLife() + injuries_caused)*100)-injuries_suffered);
			System.out.println("battle_points " + battle_points);
			total_points = total_points + battle_points;
			
			if (combates == 1) {
				MySQLConnection.loadData(player_name, player, bot, injuries_caused, injuries_suffered, battle_points, total_points);
			} else {
				MySQLConnection.updateData(player, bot, injuries_caused, injuries_suffered, battle_points, total_points);
			}
			
			MySQLConnection.showRanking();
			
//			MySQLConnection.updateRanking(MySQLConnection.getPlayerId(), total_points);
			
			if (player.getLife() > 0) {
				System.out.println("¿quieres continuar?");
				if (sc.hasNext()) {
					continuar = sc.next();
			} else {
				continuar = "n";
			}
			}else {
				continuar = "n";
			}
			player.resetStats();
			bot.resetStats();
		} while (continuar.contentEquals("y"));
		
		System.out.println("Partida terminada");
		MySQLConnection.showRanking();
		
		
		
	}



}
