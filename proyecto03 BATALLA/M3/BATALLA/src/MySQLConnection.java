import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/* This Class basic function is to connect and manage data between the game "BATALLAS" and it's database
 * Thus, several methods and implementations needed besides the basic connection
 * 
 * To utilize this class mysql-connector-java-8.0.15.jar is required.
 * 
 * Index 
 * 
 * Main methods
 * ------------------------------
 * 1- MySQLConnection constructor
 * 2- getData
 * 3- fillWarriorContainer
 * 4- fillWeaponContainer
 * 5- saveBattleData
 * 6- saveToRanking
 * 
 * Auxiliary methods
 * -------------------------------
 * 6-getData
 * 
 * NotImplemented methods
 * -------------------------------
 * 
 * created by TEAM01-AMS1
 * 
 * */
public class MySQLConnection { 
	
	private static String urlDatos = "jdbc:mysql://localhost/batallas?serverTimezone=UTC";
	private static String usuario = "root";
	private static String pass = "toor";
	private static Connection conn = null;
	private static Statement stmnt = null;
	private static ResultSet rs = null;
	
	// Create a connection to use during the main application execution
	
	public MySQLConnection(String urlDatos, String usuario, String pass){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("driver correcto");
			conn = DriverManager.getConnection(urlDatos, usuario, pass);
			//System.out.println("conn correcto");
			stmnt = conn.createStatement();
			//System.out.println("statemnet correcto");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: Ha ocurrido un error al cargar el driver.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("ERROR: Ha ocurrido un error al crear la conexion.");
			e.printStackTrace();
		}
	}
	
	// This method works as a test to get data from a table, using table race as example
	// TODO in further development a test method that matches any table will be made.
	
	public static void getData() {
		String query = "select * from race;";
		try {
			rs = stmnt.executeQuery(query);
			while (rs.next()) {
				System.out.println("race_id = " + rs.getInt(1) + 
						" race_name = " + rs.getString(2) + 
						" hp = " + rs.getInt(3) + 
						" strength = " + rs.getInt(4) + 
						" speed = " + rs.getInt(5)+ 
						" agility = " + rs.getInt(6)+ 
						" defense = " + rs.getInt(7));
			}
		} catch (SQLException e) {
			System.out.println("ERROR: please contact developer");
			e.printStackTrace();
		}
		
	}
	
	/* This method throws a query to get all needed data to create a Warrior object and stores the result in a ResultSet.
	 * For each result it checks the Warrior race to use the appropriate constructor as each child class has its own stats.
	 * Then the Warrior object is added to WarriorContainer class ArrayList*/
	
	public static void fillWarriorContainer(WarriorContainer wc) {
		String query = "select warrior_id, warrior_name, warriors.race_id, race_name, hp, strength, speed, agility, defense, warrior_image_path "
				+ "from warriors inner join race on warriors.race_id = race.race_id;";
		try {
			rs = stmnt.executeQuery(query);
			while (rs.next()) {
				if (rs.getInt(3) == 1) {
					wc.add(new Human(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getString(10)));
				}else if (rs.getInt(3) == 2) {
					wc.add(new Elf(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getString(10)));

				}else if (rs.getInt(3) == 3) {
					wc.add(new Dwarf(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getString(10)));

				}
				
			}
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al importar los datos de Guerreros");
			e.printStackTrace();
		}
		
	}
	
	
	/* This method throws a query to get all the needed data to create Weapon object and stores the result in a ResultSet.
	 * For each result, a Weapon object is created and added to WeaponContainer class ArrayList*/
	
	public static void fillWeaponContainer(WeaponContainer wc) {
		String query = "select weapon_id, weapon_name, strength, speed, weapon_race, weapon_image_path from weapons;";
		try {
			rs = stmnt.executeQuery(query);
			while (rs.next()) {
					wc.add(new Weapon(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6)));	
			}
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al importar los datos de Armas");
			e.printStackTrace();
		}
		
	}
	
	 
	
	/* This method creates the insert query for the player table : 
	 * 
	 * player_id (int), player_name(String) is what is needed 
	 * 
	 * TODO is possible we need to implement a method top generate a random Id for the player 
	 * */
	
	public static void savePlayer(int player_id, String player_name) {
		String insert = "Insert into players values (" + player_id + ", \"" + player_name + "\");";
		
	}
	
	/* This methos creates the inset string to save the data in the ranking table: 
	 * 
	 * player_id (int), total_points(Int), warrior_id(int) are the fields needed.
	 *  
	 * TODO we'll need to use the value created by the method to generate a random id for the player 
	 * to match both tables */
	
	public static void saveToRanking(int player_id, int total_points, int warrior_id) {
		String insert = "Insert into ranking values (" + player_id + ", " + total_points + ", " + warrior_id + ");";
	}
	
	
	
	/* This method stores the battle data in the batallas table.
	 * 
	 * battle_id(int), player_id(int), warrior_id(int), weapon_id(int), opponent_id(int), 
	 * opponent_weapon_id(int), injuries_caused(int), injuries_suffered(int), battle_points(int) are the fields needed
	 * 
	 * TODO use the same player_id generated for the previous methods 
	 * 
	 * */
	
	public static void saveBattleData(int battle_id, int player_id, int warrior_id, int weapon_id, 
			int opponent_id, int opponent_weapon_id, int injuries_caused, int injuries_suffered, int battle_points) {
		String insert = "Insert into ranking values (" 
			+ battle_id + ", " 
			+ player_id + ", " 
			+ warrior_id + ", " 
			+ weapon_id + ", " 
			+ opponent_id + ", " 
			+ opponent_weapon_id + ", " 
			+ injuries_caused + ", " 
			+ injuries_suffered + ", " 
			+ battle_points + ");";
	}
	
	/* This method executes the query needed to get the ranking details: 
	 * position, player_name, total_points, warrior_name, player_id.
	 * 
	 * the result should be displayed within a GUI.
	 * 
	 * TODO Establish number of results shown. 
	 * 		Establish if there will be an option(button) to show ALL existing results
	 * 		Establish if we want to feature an option to filter ranking by player name within a search field.
	 * 
	 */
	
	public static void showRanking() {
		int row = 0;
		String query = "select players.player_name, ranking.total_points, warriors.warrior_name, players.player_id "
				+ "from players "
				+ "inner join ranking on players.player_id = ranking.player_id "
				+ "inner join warriors on ranking.warrior_id = warriors.warrior_id;";
		try {
			rs = stmnt.executeQuery(query);
			while (rs.next()) {
				
			}
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al realizar la consulta. Contacte con el desarrollador Pol Tell Jove");
			e.printStackTrace();
		}
	}
	
	/* Main method as a test for the Class functionality and testing only, as it will be implemented in its own way in the main application method*/
	
	public static void main(String[] args) {
		MySQLConnection conexion = new MySQLConnection(urlDatos, usuario, pass);
		ArrayList<Warrior> warriors = new ArrayList<Warrior>();
//		WarriorContainer wc = new WarriorContainer(warriors);
//		fillWarriorContainer(wc);
		fillWarriorContainer(new WarriorContainer(warriors));
		System.out.println(warriors.toString());
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		fillWeaponContainer(new WeaponContainer(weapons));
		System.out.println(weapons.toString());
		
	}

}
