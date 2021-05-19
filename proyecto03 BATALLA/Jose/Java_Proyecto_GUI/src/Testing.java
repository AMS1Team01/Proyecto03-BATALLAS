import java.util.ArrayList;
import java.util.Random;


import java.lang.Math.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Testing {
	private static String urlDatos = "jdbc:mysql://localhost/batallas?serverTimezone=UTC";
	private static String usuario = "root";
	private static String pass = "toor";
	private static Connection conn = null;
	private static Statement stmnt = null;
	private static ResultSet rs = null;
	
	/* 
	 * Este es el codigo de batalla de Pol corregido y comentado para que lo 
	 * reviseis/testeeis y dejeis vuestros comentarios.
	 * 
	 * Este hecho con placeholders de guerreros y armas de ejemplo. 
	 * 
	 * */
	public static void main(String[] args) {
		MySQLConnection conexion = new MySQLConnection(urlDatos, usuario, pass);
		ArrayList<Warrior> warriors = new ArrayList<Warrior>();
		MySQLConnection.fillWarriorContainer(new WarriorContainer(warriors));
		System.out.println(warriors.toString());
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		MySQLConnection.fillWeaponContainer(new WeaponContainer(weapons));
		System.out.println(weapons.toString());
		
		String player_name = "Iwo Test";
		
		
//		String player_name = "Pol Siestresao";
//		Weapon Bow = new Weapon(6, "Bow", 1, 5, "Elf", "C:/Path");
//		Weapon Axe = new Weapon(3, "Axe", 3, 0, "Human,Dwarf", "C:/path");
//		Elf bot = new Elf(1, "JoseColetas", "Elf", 40, 4, 7, 7, 2, null);
//		Dwarf player = new Dwarf(2, "IwoGorras", "Dwarf", 60, 6, 3, 5, 4, null);
//		player.setWeapon(Axe);
//		bot.setWeapon(Bow);
		int total_points = 0;
		
		Random randomNumber = new Random();
		
		int index = randomNumber.nextInt(9);
		Warrior player = warriors.get(index);
		index = randomNumber.nextInt(9);
		Warrior bot = warriors.get(index);
		index = randomNumber.nextInt(9);
		player.setWeapon(weapons.get(index));
		index = randomNumber.nextInt(9);
		bot.setWeapon(weapons.get(index));
		
		System.out.println("The player selected is: " + player.toString() + "and uses a \n" + player.getWeapon().toString());
		System.out.println("The opponent is " + player.toString() + "and uses a \n" + player.getWeapon().toString());
		

		/* Variable turno para marcar el tuno en el que ocurren las cosas para la base de datos
		 * 
		 * variable orden para gestionar que personaje esta atacando en cada turno. 
		 * 
		 * Era necesrio hacerlo por separado porque a menudo un personaje vuelve a atacar y no 
		 * podriamos medirlo con el turno, ademas para gestionar el orden puede que el valor 
		 * inicial sea 1 o 0 y eso se guardaria mal en la base de datos
		 * 
		 * */
		int turn = 0;
		int order = 0;
		System.out.println(turn);
		System.out.println(new String(new char[47]).replace("\0","=") + "BATALLA" + new String(new char[47]).replace("\0","="));

		
		/* Usamos un metodo que determina quein empieza segun su velocidad, luego agilidad, 
		 * luego valor aleatorio*/
		
		if(player.getSpeed() > bot.getSpeed()) {
			order = 0;
		} else if(player.getSpeed() < bot.getSpeed()) {
			order = 1;
		} else {
			if(player.getAgility() > bot.getAgility()) {
				order = 0;
			} else if(player.getAgility() < bot.getAgility()) {
				order = 1;
			} else {
				int numRandom = (int)(Math.random()*2);
				if (numRandom == 1) {
					order = 0;
				} else {
					order = 1;
				}
			}
		}
		
		
		
		/* Mientras amos jugadores tengan vida mayor que 0 se ejecutara el loop de combate */
		
		while (player.getLife() > 0 & bot.getLife() > 0) {
			turn++;	
			System.out.println("Turno " + turn);
			if (order%2 == 0) {
				System.out.println(new String(new char[40]).replace("\0","=") + "Torn de "+ player.getName() + new String(new char[40]).replace("\0","="));
				System.out.println(bot.getDefend(player, player.getAttack()));
				System.out.println("\n" + new String(new char[100]).replace("\0","*") + "\n");	
				
			// aqui se comprueban las condiciones de cambio de turno
				
			if (player.getSpeed() <= bot.getSpeed()) { 
				order++;
				turn++;
			} else {
				int numRandom = (int)(Math.random()*101);
				if ((player.getSpeed() - bot.getSpeed())*10 <= numRandom) {
					order++;
					turn++;
				} else { // En caso que no se cumplan las condiciones de cambio el personaje puede atacar de nuevo.
					System.out.println(player.getName() + " es más rapido que " + bot.getName() + "!! Tiene otra oportunidad de ataque!");
				}
			}
			} 
			if (order%2 == 1 && (player.getLife() > 0 & bot.getLife() > 0)) { 
				System.out.println(new String(new char[40]).replace("\0","=") + "Torn de "+ bot.getName() + new String(new char[40]).replace("\0","="));
				System.out.println(player.getDefend(bot, bot.getAttack()));
				System.out.println("\n" + new String(new char[100]).replace("\0","*") + "\n");
				
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
			}
			
			
		}
		
		int injuries_caused = Math.abs(bot.getLife() - bot.getStartingLife());
		//injuries_caused = Math.abs(injuries_caused);
		System.out.println("injuries caused " + injuries_caused);
		int injuries_suffered = Math.abs(player.getLife() - player.getStartingLife());
		System.out.println("injuries suffered " + injuries_suffered);
		int battle_points = Math.abs(((player.getLife() + injuries_caused)*100)-injuries_suffered);
		System.out.println("battle_points " + battle_points);
		total_points = total_points + battle_points; 
		
		MySQLConnection.savePlayer(player_name);
		int player_id = MySQLConnection.getPlayerId();
		MySQLConnection.saveBattleData(player_id, player.getWarrior_id(), player.getWeapon().getWeapon_id(), bot.getWarrior_id(), bot.getWeapon().getWeapon_id(), 
				injuries_caused, injuries_suffered, battle_points);
		MySQLConnection.saveToRanking(player_id, total_points, player.getWarrior_id());
		
		MySQLConnection.showRanking();
		
		System.out.println("Resum del combat");
		//El ganador
		if (player.getLife() > bot.getLife()) {
		System.out.println("\nHa ganado " + player.getName() + "\n");
		}else {
		System.out.println("\nHa ganado " + bot.getName() + "\n");
		}
		
		//Opcio tornar a inici o finalitzar aplicacio
	}
	
}
	


