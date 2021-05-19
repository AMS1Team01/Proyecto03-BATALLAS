import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {

		new MySQLConnection(MySQLConnection.urlDatos, MySQLConnection.usuario, MySQLConnection.pass);

		ArrayList<Warrior> warriors = new ArrayList<Warrior>();
		MySQLConnection.fillWarriorContainer(new WarriorContainer(warriors));
		// System.out.println(warriors.toString());
		ArrayList<Weapon> weapons = new ArrayList<Weapon>();
		MySQLConnection.fillWeaponContainer(new WeaponContainer(weapons));
		new Ventana(warriors, weapons);
	}
}

class Ventana {
	private Warrior player, bot;
	private Random randomNumber = new Random();
	private JFrame jfUserName, jfOptions, jfCharacter, jfWeapon, jfBattle, jfRanking;
	private JButton jbEnter, jbRanking, jbExit;
	private JButton jbClose;
	private JButton jbPlay, jbCharacter, jbWeapon, jbBack; // Option Panel
	private JButton jbDwarf1, jbDwarf2, jbDwarf3, jbHuman1, jbHuman2, jbHuman3, jbElf1, jbElf2, jbElf3; // Option
																										// Characters
	private JButton jbDagger, jbSword, jbAxe, jbDoubleSwords, jbScimitar, jbBow, jbKatana, jbKnife, jbGreatAxe; // Option
																												// Weapons
	private JPanel jpUserName, jpOptions, jpCharacter, jpWeapon, jpBattleEnemy, jpBattleCharacter, jpBattleActions;
	private JTextArea jtaBattle;
	private JButton jbFight, jbRunAway;
	private String character, weapon;
	private JScrollPane scrollPanelWeapon, scrollPanelCharacter, scrollPanelBattle;
	private JProgressBar pbEnemyHealth, pbEnemyPower, pbEnemyAgility, pbEnemySpeed, pbEnemyDefense; // Enemy Stats
	private JProgressBar pbCharacterHealth, pbCharacterPower, pbCharacterAgility, pbCharacterSpeed, pbCharacterDefense; // Character
																														// Stats
	private ImageIcon imgDwarf1, imgDwarf2, imgDwarf3, imgHuman1, imgHuman2, imgHuman3, imgElf1, imgElf2, imgElf3;
	private ImageIcon imgAxe, imgBow, imgDagger, imgSword, imgDoubleSwords, imgGreatAxe, imgKatana, imgKnife,
			imgScimitar;
	private ArrayList<Warrior> warriors;
	private ArrayList<Weapon> weapons;
	private String userName;
	private JLabel jlCharacterImage;
	private JLabel jlEnemyImage;
	private ImageIcon imgPlayerBattle;
	private ImageIcon imgEnemyBattle;
	private JLabel jlCharacterWeapon;
	private ImageIcon imgPlayerBattleWeapon;
	private JLabel jlEnemyWeapon;
	private ImageIcon imgEnemyBattleWeapon;
	public Ventana(ArrayList<Warrior> warriors, ArrayList<Weapon> weapons) {
		
		this.warriors = warriors;
		this.weapons = weapons;
		
		System.out.println(warriors);
		System.out.println(weapons);
		// Window
		// Username----------------------------------------------------------------
		jfUserName = new JFrame();
		jpUserName = new JPanel();
		jbEnter = new JButton("Enter");
		jbRanking = new JButton("Ranking");
		jbExit = new JButton("Exit");

		// Grid Bag Layout
		GridBagLayout gbLayout = new GridBagLayout();
		jpUserName.setLayout(gbLayout);

		GridBagConstraints gbc = new GridBagConstraints();

		JLabel jlUsername = new JLabel("Username");
		JTextField jtfUserName = new JTextField(20);
		jtfUserName.setToolTipText("Username");

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbLayout.setConstraints(jlUsername, gbc);
		jpUserName.add(jlUsername);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbLayout.setConstraints(jtfUserName, gbc);
		jpUserName.add(jtfUserName);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbLayout.setConstraints(jbEnter, gbc);
		jpUserName.add(jbEnter);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbLayout.setConstraints(jbRanking, gbc);
		jpUserName.add(jbRanking);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbLayout.setConstraints(jbExit, gbc);
		jpUserName.add(jbExit);

		// Action Listener in "Enter" Button
		jbEnter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				userName = jtfUserName.getText();
				if (userName.length() == 0) {
					// TODO CONTROLAR NOMBRE USUARIO VALIDO
					JOptionPane.showMessageDialog(null, "Incorrect username");
				} else {
					System.out.println(userName);
					jfUserName.setVisible(false);
					jfOptions.setVisible(true);
				}
			}
		});

		jbRanking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfUserName.setVisible(false);
				jfRanking.setVisible(true);

			}
		});

		jbExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfUserName.dispose();

			}
		});

		jfUserName.add(jpUserName);

		jfUserName.setSize(350, 500);

		jfUserName.setResizable(false);

		jfUserName.setLocationRelativeTo(null);

		jfUserName.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jfUserName.setVisible(true);

		// Window Options (Play, Character, Weapon, Back)-------------------------------

		jfOptions = new JFrame();
		jpOptions = new JPanel();
		jpOptions.setLayout(gbLayout);

		jbPlay = new JButton("Play");
		jbCharacter = new JButton("Character");
		jbWeapon = new JButton("Weapon");
		jbBack = new JButton("Back");

		jbPlay.setSize(20, 50);
		gbc.gridx = 0;
		gbc.gridy = -1;
		gbLayout.setConstraints(jbPlay, gbc);

		jpOptions.add(jbPlay);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbLayout.setConstraints(jbCharacter, gbc);
		jpOptions.add(jbCharacter);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbLayout.setConstraints(jbWeapon, gbc);
		jpOptions.add(jbWeapon);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbLayout.setConstraints(jbBack, gbc);
		jpOptions.add(jbBack);

		jfOptions.add(jpOptions);

		jbPlay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

//				System.out.println("PLAY");
//				jfBattle.setVisible(true);
				if (player == null) {
					JOptionPane.showMessageDialog(null, "Choose a character!");
				} else if (player.getWeapon() == null) {
					JOptionPane.showMessageDialog(null, "Choose a weapon!");
				} else {
					System.out.println("PLAY");
					
					// Change characters Images
					imgEnemyBattle = new ImageIcon(bot.getUrl());
					jlEnemyImage.setIcon(imgEnemyBattle);
					imgPlayerBattle = new ImageIcon(player.getUrl());
					jlCharacterImage.setIcon(imgPlayerBattle);
					
					// Change character weapons Images
					imgEnemyBattleWeapon = new ImageIcon(bot.getWeapon().getUrl());
					jlEnemyWeapon.setIcon(imgEnemyBattleWeapon);
					
					imgPlayerBattleWeapon = new ImageIcon(player.getWeapon().getUrl());
					jlCharacterWeapon.setIcon(imgPlayerBattleWeapon);
					
					
					// Change ProgressBar Enemy
					pbEnemyPower.setValue(bot.getAttack());
					pbEnemySpeed.setValue(bot.getSpeed());
					pbEnemyAgility.setValue(bot.getAgility());
					pbEnemyDefense.setValue(bot.getDefense());
					
					pbEnemyHealth.setMaximum(bot.getStartingLife());
					pbEnemyHealth.setValue(bot.getStartingLife());
					pbEnemyHealth.setString(bot.getLife()+"/"+bot.getStartingLife());
					
					// Change ProgressBar Player
					pbCharacterPower.setValue(player.getAttack());
					pbCharacterSpeed.setValue(player.getSpeed());
					pbCharacterAgility.setValue(player.getAgility());
					pbCharacterDefense.setValue(player.getDefense());
					pbCharacterHealth.setMaximum(player.getStartingLife());
					pbCharacterHealth.setValue(player.getStartingLife());
					pbCharacterHealth.setString(player.getLife()+"/"+player.getStartingLife());
					
					jfBattle.setVisible(true);
				}
			}
		});

		jbCharacter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				jfOptions.setVisible(false);
				jfCharacter.setVisible(true);
				if (player != null) {
					player.setWeapon(null);
				}
			}
		});

		jbWeapon.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (player == null) {
					JOptionPane.showMessageDialog(null, "Choose a character First!");
				} else {

					if (player.getRace().equals("Dwarf")) {
						jbDagger.setVisible(false);
						jbSword.setVisible(true);
						jbAxe.setVisible(true);
						jbDoubleSwords.setVisible(false);
						jbScimitar.setVisible(false);
						jbBow.setVisible(false);
						jbKatana.setVisible(false);
						jbKnife.setVisible(true);
						jbGreatAxe.setVisible(true);

					} else if (player.getRace().equals("Human")) {
						jbDagger.setVisible(true);
						jbSword.setVisible(true);
						jbAxe.setVisible(true);
						jbDoubleSwords.setVisible(true);
						jbScimitar.setVisible(true);
						jbBow.setVisible(false);
						jbKatana.setVisible(true);
						jbKnife.setVisible(true);
						jbGreatAxe.setVisible(false);
					} else if (player.getRace().equals("Elf")) {
						jbDagger.setVisible(true);
						jbSword.setVisible(true);
						jbAxe.setVisible(false);
						jbDoubleSwords.setVisible(true);
						jbScimitar.setVisible(true);
						jbKatana.setVisible(false);
						jbBow.setVisible(true);
						jbKnife.setVisible(true);
						jbGreatAxe.setVisible(false);
					}

					jfOptions.setVisible(false);
					jfWeapon.setVisible(true);
				}
			}
		});

		jbBack.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				jfOptions.setVisible(false);
				jfUserName.setVisible(true);
				player = null;

			}
		});

		jfOptions.setSize(350, 500);

		jfOptions.setResizable(false);

		jfOptions.setLocationRelativeTo(null);

		jfOptions.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jfOptions.setVisible(false);

		// Character
		// Window----------------------------------------------------------------------
		jfCharacter = new JFrame();
		jpCharacter = new JPanel();
		jpCharacter.setLayout(new BoxLayout(jpCharacter, BoxLayout.Y_AXIS));

		jbDwarf1 = new JButton();
		jbDwarf2 = new JButton();
		jbDwarf3 = new JButton();
		jbHuman1 = new JButton();
		jbHuman2 = new JButton();
		jbHuman3 = new JButton();
		jbElf1 = new JButton();
		jbElf2 = new JButton();
		jbElf3 = new JButton();

		 imgDwarf1 = new ImageIcon(warriors.get(6).getUrl());
		 imgDwarf2 = new ImageIcon(warriors.get(7).getUrl());
		 imgDwarf3 = new ImageIcon(warriors.get(8).getUrl());
		 imgHuman1 = new ImageIcon(warriors.get(0).getUrl());
		 imgHuman2 = new ImageIcon(warriors.get(1).getUrl());
		 imgHuman3 = new ImageIcon(warriors.get(2).getUrl());
		 imgElf1 = new ImageIcon(warriors.get(3).getUrl());
		 imgElf2 = new ImageIcon(warriors.get(4).getUrl());
		 imgElf3 = new ImageIcon(warriors.get(5).getUrl());
		jbDwarf1.setIcon(imgDwarf1);
		jbDwarf2.setIcon(imgDwarf2);
		jbDwarf3.setIcon(imgDwarf3);
		jbHuman1.setIcon(imgHuman1);
		jbHuman2.setIcon(imgHuman2);
		jbHuman3.setIcon(imgHuman3);
		jbElf1.setIcon(imgElf1);
		jbElf2.setIcon(imgElf2);
		jbElf3.setIcon(imgElf3);
		
		jbDwarf1.setToolTipText(warriors.get(6).getName());
		jbDwarf2.setToolTipText(warriors.get(7).getName());
		jbDwarf3.setToolTipText(warriors.get(8).getName());
		jbHuman1.setToolTipText(warriors.get(0).getName());
		jbHuman2.setToolTipText(warriors.get(1).getName());
		jbHuman3.setToolTipText(warriors.get(2).getName());
		jbElf1.setToolTipText(warriors.get(3).getName());
		jbElf2.setToolTipText(warriors.get(4).getName());
		jbElf3.setToolTipText(warriors.get(5).getName());

		jbDwarf1.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbDwarf2.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbDwarf3.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbHuman1.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbHuman2.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbHuman3.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbElf1.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbElf2.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbElf3.setAlignmentX(Component.CENTER_ALIGNMENT);

		jpCharacter.add(jbDwarf1);
		jpCharacter.add(new JLabel("  "));
		jpCharacter.add(jbDwarf2);
		jpCharacter.add(new JLabel("  "));
		jpCharacter.add(jbDwarf3);
		jpCharacter.add(new JLabel("  "));
		jpCharacter.add(jbHuman1);
		jpCharacter.add(new JLabel("  "));
		jpCharacter.add(jbHuman2);
		jpCharacter.add(new JLabel("  "));
		jpCharacter.add(jbHuman3);
		jpCharacter.add(new JLabel("  "));
		jpCharacter.add(jbElf1);
		jpCharacter.add(new JLabel("  "));
		jpCharacter.add(jbElf2);
		jpCharacter.add(new JLabel("  "));
		jpCharacter.add(jbElf3);
		jpCharacter.add(new JLabel("  "));

		scrollPanelCharacter = new JScrollPane(jpCharacter, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		jbDwarf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(6);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				System.out.println("OKEYYYY");
				
				getRandomEnemy();
			}
		});

		jbDwarf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(7);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				getRandomEnemy();
			}
		});

		jbDwarf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(8);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				getRandomEnemy();
			}
		});

		jbHuman1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(0);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				getRandomEnemy();
			}
		});

		jbHuman2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(1);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				getRandomEnemy();
			}
		});

		jbHuman3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(2);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				getRandomEnemy();
			}
		});

		jbElf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(3);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				getRandomEnemy();
			}
		});

		jbElf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(4);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				getRandomEnemy();
			}
		});

		jbElf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player = warriors.get(5);
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				getRandomEnemy();
			}
		});

		jfCharacter.getContentPane().add(scrollPanelCharacter, BorderLayout.CENTER);

		jfCharacter.setSize(350, 500);

		jfCharacter.setResizable(false);

		jfCharacter.setLocationRelativeTo(null);

		jfCharacter.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jfCharacter.setVisible(false);

		// Weapons
		// Window----------------------------------------------------------------

		jfWeapon = new JFrame();
		jpWeapon = new JPanel();
		jpWeapon.setLayout(new BoxLayout(jpWeapon, BoxLayout.Y_AXIS));

		jbDagger = new JButton();
		jbSword = new JButton();
		jbAxe = new JButton();
		jbDoubleSwords = new JButton();
		jbScimitar = new JButton();
		jbBow = new JButton();
		jbKatana = new JButton();
		jbKnife = new JButton();
		jbGreatAxe = new JButton();

		ImageIcon imgWeapon = new ImageIcon("weapon.png");
		ImageIcon imgWeaponCharacter = new ImageIcon("weapon_character.png");

		imgDagger = new ImageIcon(weapons.get(0).getUrl());
		imgSword = new ImageIcon(weapons.get(1).getUrl());
		imgAxe = new ImageIcon(weapons.get(2).getUrl());
		imgDoubleSwords = new ImageIcon(weapons.get(3).getUrl());
		imgScimitar = new ImageIcon(weapons.get(4).getUrl());
		imgBow = new ImageIcon(weapons.get(5).getUrl());
		imgKatana = new ImageIcon(weapons.get(6).getUrl());
		imgKnife = new ImageIcon(weapons.get(7).getUrl());
		imgGreatAxe = new ImageIcon(weapons.get(8).getUrl());

		jbDagger.setIcon(imgDagger);
		jbSword.setIcon(imgSword);
		jbAxe.setIcon(imgAxe);
		jbDoubleSwords.setIcon(imgDoubleSwords);
		jbScimitar.setIcon(imgScimitar);
		jbBow.setIcon(imgBow);
		jbKatana.setIcon(imgKatana);
		jbKnife.setIcon(imgKnife);
		jbGreatAxe.setIcon(imgGreatAxe);

		jbDagger.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbSword.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbAxe.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbDoubleSwords.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbScimitar.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbBow.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbKatana.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbKnife.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbGreatAxe.setAlignmentX(Component.CENTER_ALIGNMENT);

		jbDagger.setToolTipText(weapons.get(0).getName() + ": Strength: " + weapons.get(0).getStrength() + " Speed: "
				+ weapons.get(0).getVelocity());
		jbSword.setToolTipText(weapons.get(1).getName() + ": Strength: " + weapons.get(1).getStrength() + " Speed: "
				+ weapons.get(1).getVelocity());
		jbAxe.setToolTipText(weapons.get(2).getName() + ": Strength: " + weapons.get(2).getStrength() + " Speed: "
				+ weapons.get(2).getVelocity());
		jbDoubleSwords.setToolTipText(weapons.get(3).getName() + ": Strength: " + weapons.get(3).getStrength()
				+ " Speed: " + weapons.get(3).getVelocity());
		jbScimitar.setToolTipText(weapons.get(4).getName() + ": Strength: " + weapons.get(4).getStrength() + " Speed: "
				+ weapons.get(4).getVelocity());
		jbBow.setToolTipText(weapons.get(5).getName() + ": Strength: " + weapons.get(5).getStrength() + " Speed: "
				+ weapons.get(5).getVelocity());
		jbKatana.setToolTipText(weapons.get(6).getName() + ": Strength: " + weapons.get(6).getStrength() + " Speed: "
				+ weapons.get(6).getVelocity());
		jbKnife.setToolTipText(weapons.get(7).getName() + ": Strength: " + weapons.get(7).getStrength() + " Speed: "
				+ weapons.get(7).getVelocity());
		jbGreatAxe.setToolTipText(weapons.get(8).getName() + ": Strength: " + weapons.get(8).getStrength() + " Speed: "
				+ weapons.get(8).getVelocity());

		jpWeapon.add(jbDagger);
		jpWeapon.add(jbSword);
		jpWeapon.add(jbAxe);
		jpWeapon.add(jbDoubleSwords);
		jpWeapon.add(jbScimitar);
		jpWeapon.add(jbBow);
		jpWeapon.add(jbKatana);
		jpWeapon.add(jbKnife);
		jpWeapon.add(jbGreatAxe);

		scrollPanelWeapon = new JScrollPane(jpWeapon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		jbDagger.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(0));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);
				System.out.println("OKEYYYY");

			}
		});

		jbSword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(1));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbAxe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(2));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbDoubleSwords.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(3));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbScimitar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(4));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbBow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(5));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbKatana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(6));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbKnife.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(7));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbGreatAxe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				player.setWeapon(weapons.get(8));
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jfWeapon.getContentPane().add(scrollPanelWeapon, BorderLayout.CENTER);

		jfWeapon.setSize(350, 500);

		jfWeapon.setResizable(false);

		jfWeapon.setLocationRelativeTo(null);

		jfWeapon.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jfWeapon.setVisible(false);

		// Battle Window--------------------------------------------------------------
		jfBattle = new JFrame();

		// Enemy Panel-------------------------------------
		jpBattleEnemy = new JPanel();
		jpBattleEnemy.setBackground(Color.RED);
		jpBattleEnemy.setLayout(null);

		// JLabel Enemy Weapon
		imgEnemyBattleWeapon = new ImageIcon(weapons.get(0).getUrl());
		jlEnemyWeapon = new JLabel();
		jlEnemyWeapon.setIcon(imgEnemyBattleWeapon);
		jlEnemyWeapon.setBounds(265, 35, 128, 128);

		// JLabel Text Power
		JLabel jlEnemyPower = new JLabel("Power");
		jlEnemyPower.setBounds(15, 35, 50, 20);
		jlEnemyPower.setBackground(Color.RED);
		jlEnemyPower.setOpaque(true);

		// Progress Bar Enemy Power
		pbEnemyPower = new JProgressBar(0, 10);
		pbEnemyPower.setValue(4);
		pbEnemyPower.setBounds(80, 39, 120, 15);
		pbEnemyPower.setForeground(Color.RED);

		// JLabel Text Agility
		JLabel jlEnemyAgility = new JLabel("Agility");
		jlEnemyAgility.setBounds(15, 55, 50, 20);
		jlEnemyAgility.setBackground(Color.RED);
		jlEnemyAgility.setOpaque(true);

		// Progress Bar Enemy Agility
		pbEnemyAgility = new JProgressBar(0, 10);
		pbEnemyAgility.setValue(8);
		pbEnemyAgility.setBounds(80, 59, 120, 15);
		pbEnemyAgility.setForeground(Color.MAGENTA);

		// JLabel Text Speed
		JLabel jlEnemySpeed = new JLabel("Speed");
		jlEnemySpeed.setBounds(15, 75, 50, 20);
		jlEnemySpeed.setBackground(Color.RED);
		jlEnemySpeed.setOpaque(true);

		// Progress Bar Enemy Speed
		pbEnemySpeed = new JProgressBar(0, 10);
		pbEnemySpeed.setValue(7);
		pbEnemySpeed.setBounds(80, 79, 120, 15);
		pbEnemySpeed.setForeground(Color.ORANGE);

		// JLabel Text Defense
		JLabel jlEnemyDefense = new JLabel("Defense");
		jlEnemyDefense.setBounds(15, 95, 50, 20);
		jlEnemyDefense.setBackground(Color.RED);
		jlEnemyDefense.setOpaque(true);

		// Progress Bar Enemy Defense
		pbEnemyDefense = new JProgressBar(0, 10);
		pbEnemyDefense.setValue(2);
		pbEnemyDefense.setBounds(80, 99, 120, 15);
		pbEnemyDefense.setForeground(Color.BLUE);

		// Progress Bar Enemy Health
		pbEnemyHealth = new JProgressBar(0, 500);
		pbEnemyHealth.setValue(50);
		pbEnemyHealth.setString("50/500");
		pbEnemyHealth.setBounds(390, 15, 145, 15);
		pbEnemyHealth.setStringPainted(true);
		pbEnemyHealth.setForeground(Color.GREEN);
		jpBattleEnemy.add(pbEnemyHealth);

		// JLabel Enemy Image
		jlEnemyImage = new JLabel();
		imgEnemyBattle =  new ImageIcon(warriors.get(0).getUrl());
		jlEnemyImage.setIcon(imgEnemyBattle);
		jlEnemyImage.setBounds(395, 35, 128, 128);

		// Add elements to the Panel
		jpBattleEnemy.add(jlEnemyImage);
		jpBattleEnemy.add(jlEnemyWeapon);
		jpBattleEnemy.add(jlEnemyPower);
		jpBattleEnemy.add(jlEnemyAgility);
		jpBattleEnemy.add(jlEnemySpeed);
		jpBattleEnemy.add(jlEnemyDefense);
		jpBattleEnemy.add(pbEnemyPower);
		jpBattleEnemy.add(pbEnemyAgility);
		jpBattleEnemy.add(pbEnemySpeed);
		jpBattleEnemy.add(pbEnemyDefense);
		jpBattleEnemy.setBounds(20, 15, 550, 175);

		// Character Panel---------------------------------
		jpBattleCharacter = new JPanel();
		pbCharacterHealth = new JProgressBar(0, 400);

		// JLabel Character
		jlCharacterImage = new JLabel();
		jpBattleCharacter.setBackground(Color.BLUE);
		jpBattleCharacter.setLayout(null);

		// Progress Bar Character
		pbCharacterHealth.setValue(50);
		pbCharacterHealth.setString("50/400");
		pbCharacterHealth.setStringPainted(true);
		pbCharacterHealth.setForeground(Color.GREEN);
		pbCharacterHealth.setBounds(15, 15, 145, 15);

		// JLabel CharacterImage
		
		imgPlayerBattle =  new ImageIcon(warriors.get(0).getUrl());
		jlCharacterImage.setIcon(imgPlayerBattle);
		jlCharacterImage.setBounds(20, 35, 128, 128);

		// JLabel Character Weapon
		imgPlayerBattleWeapon = new ImageIcon(weapons.get(0).getUrl());
		jlCharacterWeapon = new JLabel();
		jlCharacterWeapon.setIcon(imgPlayerBattleWeapon);
		jlCharacterWeapon.setBounds(165, 35, 128, 128);

		// JLabel Text Power
		JLabel jlCharacterPower = new JLabel("Power");
		jlCharacterPower.setBounds(325, 35, 50, 20);
		jlCharacterPower.setBackground(Color.BLUE);
		jlCharacterPower.setOpaque(true);

		// Progress Bar Character Power
		pbCharacterPower = new JProgressBar(0, 10);
		pbCharacterPower.setValue(4);
		pbCharacterPower.setBounds(390, 39, 120, 15);
		pbCharacterPower.setForeground(Color.RED);

		// JLabel Text Agility
		JLabel jlCharacterAgility = new JLabel("Agility");
		jlCharacterAgility.setBounds(325, 55, 50, 20);
		jlCharacterAgility.setBackground(Color.BLUE);
		jlCharacterAgility.setOpaque(true);

		// Progress Bar Character Agility
		pbCharacterAgility = new JProgressBar(0, 10);
		pbCharacterAgility.setValue(8);
		pbCharacterAgility.setBounds(390, 59, 120, 15);
		pbCharacterAgility.setForeground(Color.MAGENTA);

		// JLabel Text Speed
		JLabel jlCharacterSpeed = new JLabel("Speed");
		jlCharacterSpeed.setBounds(325, 75, 50, 20);
		jlCharacterSpeed.setBackground(Color.BLUE);
		jlCharacterSpeed.setOpaque(true);

		// Progress Bar Enemy Speed
		pbCharacterSpeed = new JProgressBar(0, 10);
		pbCharacterSpeed.setValue(7);
		pbCharacterSpeed.setBounds(390, 79, 120, 15);
		pbCharacterSpeed.setForeground(Color.ORANGE);

		// JLabel Text Defense
		JLabel jlCharacterDefense = new JLabel("Defense");
		jlCharacterDefense.setBounds(325, 95, 50, 20);
		jlCharacterDefense.setBackground(Color.BLUE);
		jlCharacterDefense.setOpaque(true);

		// Progress Bar Character Defense
		pbCharacterDefense = new JProgressBar(0, 10);
		pbCharacterDefense.setValue(2);
		pbCharacterDefense.setBounds(390, 99, 120, 15);
		pbCharacterDefense.setForeground(Color.BLUE);

		// Add elements to the JPanel
		jpBattleCharacter.add(jlCharacterImage);
		jpBattleCharacter.add(jlCharacterWeapon);
		jpBattleCharacter.add(jlCharacterPower);
		jpBattleCharacter.add(jlCharacterAgility);
		jpBattleCharacter.add(jlCharacterSpeed);
		jpBattleCharacter.add(jlCharacterDefense);
		jpBattleCharacter.add(pbCharacterHealth);
		jpBattleCharacter.add(pbCharacterPower);
		jpBattleCharacter.add(pbCharacterAgility);
		jpBattleCharacter.add(pbCharacterSpeed);
		jpBattleCharacter.add(pbCharacterDefense);
		jpBattleCharacter.setBounds(20, 205, 550, 175);

		// JTextArea Battle information with scrollBar
		jtaBattle = new JTextArea();
		jtaBattle.setEditable(false);
		jtaBattle.setLineWrap(true);
		jtaBattle.setWrapStyleWord(true);

		scrollPanelBattle = new JScrollPane(jtaBattle, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollPanelBattle.setBounds(20, 400, 420, 140);

		jbFight = new JButton("FIGHT!");

		jbFight.setBackground(Color.GREEN);

		jbFight.setBounds(460, 400, 100, 100);

		jbRunAway = new JButton("Run away");
		jbRunAway.setBackground(Color.YELLOW);
		jbRunAway.setToolTipText("Caution, your points will reset!");
		jbRunAway.setBounds(460, 510, 100, 30);

		jbRunAway.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				jfBattle.setVisible(false);
				character = null;
				weapon = null;
				jfOptions.setVisible(true);
				System.out.println("You run Away");
			}
		});

		// Add panels to Battle Window
		jfBattle.add(jpBattleEnemy);
		jfBattle.add(jpBattleCharacter);
		jfBattle.add(scrollPanelBattle);
		jfBattle.add(jbFight);
		jfBattle.add(jbRunAway);

		// Properties Battle Window
		jfBattle.setLayout(null);
		jfBattle.setSize(600, 600);
		jfBattle.setResizable(false);
		jfBattle.setLocationRelativeTo(null);
		jfBattle.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jfBattle.setVisible(false);

		// Ranking Window

		jfRanking = new JFrame();

		jbClose = new JButton("Close");

		jbClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfRanking.setVisible(false);
				jfUserName.setVisible(true);

			}
		});
		
		jbFight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("añldkfjañslkfjsñlkfjsñlkfjsñdlfjs");
				
			}
		});

		jfRanking.add(jbClose, BorderLayout.SOUTH);

		jfRanking.setSize(600, 600);
		jfRanking.setResizable(false);
		jfRanking.setLocationRelativeTo(null);
		jfRanking.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jfRanking.setVisible(false);

	}
	
	private void getRandomEnemy() {
		
		do {
			int index = randomNumber.nextInt(9);
			bot = warriors.get(index);
		} while (player == bot);

		int indexWeapon;
		do {
			indexWeapon = randomNumber.nextInt(9);
		} while (!weapons.get(indexWeapon).getWeapon_race().contains(bot.getRace()));
		bot.setWeapon(weapons.get(indexWeapon));
	}

}