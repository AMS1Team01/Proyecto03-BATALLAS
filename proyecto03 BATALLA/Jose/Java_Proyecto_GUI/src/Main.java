import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		new Ventana();
	}
}

class Ventana {

	private JFrame jfUserName, jfOptions, jfCharacter, jfWeapon, jfBattle, jfRanking;
	private JButton jbEnter, jbRanking, jbExit;
	private JButton jbClose;
	private JButton jbPlay, jbCharacter, jbWeapon, jbBack; // Option Panel
	private JButton jbDwarf1, jbDwarf2, jbDwarf3, jbHuman1, jbHuman2, jbHuman3, jbElf1, jbElf2, jbElf3; // Option Characters
	private JButton jbDagger, jbSword, jbAxe, jbDoubleSwords, jbScimitar, jbBow, jbKatana, jbKnife, jbGreatAxe; // Option Weapons
	private JPanel jpUserName, jpOptions, jpCharacter, jpWeapon, jpBattleEnemy, jpBattleCharacter, jpBattleActions;
	private JTextArea jtaBattle;
	private JButton jbFight, jbRunAway;
	private String character, weapon;
	private JScrollPane scrollPanelWeapon, scrollPanelCharacter, scrollPanelBattle;
	private JProgressBar pbEnemyHealth, pbEnemyPower, pbEnemyAgility, pbEnemySpeed, pbEnemyDefense; // Enemy Stats
	private JProgressBar pbCharacterHealth, pbCharacterPower, pbCharacterAgility, pbCharacterSpeed, pbCharacterDefense; // Character Stats
	private ImageIcon imgCharacter;
	private ImageIcon imgAxe, imgBow, imgDagger, imgSword, imgDoubleSwords, imgGreatAxe, imgKatana, imgKnife, imgScimitar;

	public Ventana() {

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
				String username = jtfUserName.getText();
				if (username.length() == 0) {
					//TODO CONTROLAR NOMBRE USUARIO VALIDO
					JOptionPane.showMessageDialog(null, "Incorrect username");
				} else {
					System.out.println(username);
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
				if (character == null) {
				JOptionPane.showMessageDialog(null, "Choose a character!");
				}else if(weapon == null) {
					JOptionPane.showMessageDialog(null, "Choose a weapon!");
				}else {
					System.out.println("PLAY");
					jfBattle.setVisible(true);
				}
			}
		});

		jbCharacter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				jfOptions.setVisible(false);
				jfCharacter.setVisible(true);
				weapon = null;
			}
		});

		jbWeapon.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (character == null) {
					JOptionPane.showMessageDialog(null, "Choose a character First!");
				} else {

					if (character.equals("Dwarf")) {
						jbDagger.setVisible(false);
						jbSword.setVisible(true);
						jbAxe.setVisible(true);
						jbDoubleSwords.setVisible(false);
						jbScimitar.setVisible(false);
						jbBow.setVisible(false);
						jbKatana.setVisible(false);
						jbKnife.setVisible(true);
						jbGreatAxe.setVisible(true);

					} else if (character.equals("Human")) {
						jbDagger.setVisible(true);
						jbSword.setVisible(true);
						jbAxe.setVisible(true);
						jbDoubleSwords.setVisible(true);
						jbScimitar.setVisible(true);
						jbBow.setVisible(false);
						jbKatana.setVisible(true);
						jbKnife.setVisible(true);
						jbGreatAxe.setVisible(false);
					} else if (character.equals("Elf")) {
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
				character = null;
				weapon = null;

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

		imgCharacter = new ImageIcon("128_character_example.png");

		jbDwarf1.setIcon(imgCharacter);
		jbDwarf2.setIcon(imgCharacter);
		jbDwarf3.setIcon(imgCharacter);
		jbHuman1.setIcon(imgCharacter);
		jbHuman2.setIcon(imgCharacter);
		jbHuman3.setIcon(imgCharacter);
		jbElf1.setIcon(imgCharacter);
		jbElf2.setIcon(imgCharacter);
		jbElf3.setIcon(imgCharacter);

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
				character = "Dwarf";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);
				System.out.println("OKEYYYY");

			}
		});

		jbDwarf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				character = "Dwarf";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbDwarf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				character = "Dwarf";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbHuman1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				character = "Human";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbHuman2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				character = "Human";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbHuman3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				character = "Human";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbElf1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				character = "Elf";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbElf2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				character = "Elf";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbElf3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				character = "Elf";
				jfCharacter.setVisible(false);
				jfOptions.setVisible(true);

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
		
		imgDagger = new ImageIcon("Dagger.png");
		imgSword = new ImageIcon("Sword.png");
		imgAxe = new ImageIcon("Axe.png");
		imgDoubleSwords = new ImageIcon("Double Swords.png");
		imgScimitar = new ImageIcon("Scimitar.png");
		imgBow = new ImageIcon("Bow.png");
		imgKatana = new ImageIcon("Katana.png");
		imgKnife = new ImageIcon("Knife.png");
		imgGreatAxe = new ImageIcon("Great Axe.png");
		
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
		
		jbDagger.setToolTipText("Dagger: Speed: +3 ");
//		jbSword.

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
				weapon = "Dagger";
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);
				System.out.println("OKEYYYY");

			}
		});

		jbSword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				weapon = "Sword";
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbAxe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				weapon = "Axe";
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbDoubleSwords.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				weapon = "DoubleSwords";
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbScimitar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				weapon = "Scimitar";
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbBow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				weapon = "Bow";
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbKatana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				weapon = "Katana";
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbKnife.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				weapon = "Knife";
				jfWeapon.setVisible(false);
				jfOptions.setVisible(true);

			}
		});

		jbGreatAxe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				weapon = "GreatAxe";
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
		JLabel jlEnemyWeapon = new JLabel();
		jlEnemyWeapon.setIcon(imgWeapon);
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
		var jlEnemyImage = new JLabel();
		jlEnemyImage.setIcon(imgCharacter);
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

		// JLabel Character Image
		var jlCharacterImage = new JLabel();
		jpBattleCharacter.setBackground(Color.BLUE);
		jpBattleCharacter.setLayout(null);

		// Progress Bar Character
		pbCharacterHealth.setValue(50);
		pbCharacterHealth.setString("50/400");
		pbCharacterHealth.setStringPainted(true);
		pbCharacterHealth.setForeground(Color.GREEN);
		pbCharacterHealth.setBounds(15, 15, 145, 15);

		// JLabel CharacterImage
		jlCharacterImage.setIcon(imgCharacter);
		jlCharacterImage.setBounds(20, 35, 128, 128);

		// JLabel Character Weapon
		JLabel jlCharacterWeapon = new JLabel();
		jlCharacterWeapon.setIcon(imgWeaponCharacter);
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
		jbRunAway.setBounds(460, 510, 100, 30 );
		
		jbRunAway.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				jfBattle.setVisible(false);
				character = null;
				weapon= null;
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
		
		jfRanking.add(jbClose, BorderLayout.SOUTH);
		
		
		jfRanking.setSize(600, 600);
		jfRanking.setResizable(false);
		jfRanking.setLocationRelativeTo(null);
		jfRanking.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jfRanking.setVisible(false);
		

	}

}