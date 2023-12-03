import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.TimeUnit;



public class Adventure_Of_Ages implements ActionListener {

	static Random rand = new Random();
	static JTextField jta2;
	static JTextArea jta;
	static String feedstring = "";
	static FileThing file = new FileThing();
	static JLabel textField;

	static boolean gamerunning = false;
	static int HP;
	static int MaxHP;

	static int Mana;
	static int MaxMana;

	static int AP;
	static int MaxAP;

	static String Name;
	static int Attack;
	static int Strength;
	static int Dexterity;
	static int Constitution;
	static int Defense;
	static int Speed;
	static int Intelligence;
	static int Vitality;
	static int Luck;

	static int XP;
	static int XPtoNext;
	static int Level;
	static int XPThreshold;

	static int arrows;
	static int bullets;
	static int throwables;
	static int maps;
	static int crystals;
	static int cures;
	static int[] potions = new int[3];
	static int manaPotions;
	static String[] potionType = {"potion", "healing salve", "healing tome", "stimpak"};
	static int potTypeIndex;
	static int PotionsConsumed;
	static int money;

	static String EnemyModifier1;
	static String EnemyModifier2;
	static boolean satanDead;

	static int diseaseLevel = -1;
	static int[] diseaseDamage =  {5,0,0,10,12,10,15,17,10,25,15,20};
	static int[] diseaseAtkBuff = {0,-2,2,-1,0,0,-2,-2,-3,0,-4,-8};
	static int[] diseaseDefBuff = {0,-3,-4,-2,0,0,-4,-5,-5,0,-2,-6};
	static int[] diseaseSpdBuff = {2,0,2,-3,0,0,-1,-1,1,0,-6,-3};
	static int[] diseaseIntBuff = {-4,0,-3,0,4,0,0,0,0,0,-2,0};
	static int[] diseaseVitBuff = {-2,-1,0,-4,-2,0,0,0,-3,-2,-5,-8};
	static String[] diseaseNames = {"parasites","scurvy","rabies","pneumonia","tuberculosis","influenza","dysentery","cholera","malaria","smallpox","bubonic plague","ebola virus"};
	static int diseaseTime;

	static String Opponent;
	static int OpponentHealth;
	static int ea;
	static int ed;
	static int es;
	static int ei;
	static int ev;
	static int ew;
	static Attack[] eats;

	static ArrayList <Enemy> enemies = new ArrayList <Enemy>();
	static ArrayList <Enemy> bosses = new ArrayList <Enemy>();
	static ArrayList <Enemy> finalbosses = new ArrayList <Enemy>();
	static String[] finales1 = {"The wormhole spits you out right in front of Satan, sitting on his throne. He laughs, then stands up menacingly.\nYou know what to do.","The wormhole deposits you at the top of Mount Olympus. Zeus watches you with a scornful eye, despising this tresspasser.","It's hard to tell where you were transported, but it is dark, and cold. You look up, and you've never been more terrified.","Waves crash against the gloomy beach while a thick fog covers the land. You look towards the water as an eldritch nightmare emerges from the sea."};
	static String[] finales2 = {"Satan is dead at your hands, leaving you to rule hell. Your benign actions usher in an era of peace between the realms of afterlife.","Zeus has fallen, thanks to you. Now, you take up his mantle as king of the Gods and rule from Olympus with grace and kindness.","The grim reaper has met the very fate he served. Now, it is your task to collect the souls of the dead. Mortals can only pray you are more generous than he.","Cthulhu has been eradicated, for now. The lovecraftian horrors that terrorized this land run in fear, and you are regarded as a hero."};
	static ArrayList <Weapon> weapons = new ArrayList <Weapon>();
	static ArrayList <Enemy> bossesKilled = new ArrayList <Enemy>();
	static HashMap<String, Integer> killedEnemies = new HashMap<String, Integer>();

	static String[] inputs = {"a level 1 healing item", "a level 2 healing item", "a level 3 healing item", "one map", "a cure", "a crystal"};
	static String[] outputs = {"a level 1 healing item", "a level 2 healing item", "a level 3 healing item", "a map", "a cure", "a crystal", "1000 gold", "50 bullets", "50 arrows", "50 throwable weapons", "the "} ;
	static String[] demands = {"100 level 1 healing items", "20 level 2 healing items", "3 level 3 healing items", "5 cures", "1 crystal", "5 maps"};

	static ArrayList<Weapon> inventory = new ArrayList<Weapon>();
	static ArrayList<Integer> durabilities = new ArrayList<Integer>();

	static ArrayList<Biome> biomes = new ArrayList<Biome>();
	static int area;
	static int areaProgress;
	static int areaSize;
	static int mapMod;

	static ArrayList<Weapon> Weapons = new ArrayList<Weapon>();

	static boolean fb = false;
	static boolean notrun = true;
	static boolean enemybrn = false;
	static boolean enemyfrz = false;
	static boolean enemypsn = false;

	static int mod = 0;

	static int[] numkilled;

	static int textSpeed;
	static int textSpeedindex;

	static int difficulty;

	static int GhostXP;
	static boolean GhostFight;

	static double audioSetting;
	static int audioInt;

	static JFrame frame;
	static JPanel panel;
	static JScrollPane jsp;
	static JScrollPane jsp2;
	static JScrollPane jsp3;
	static Container contentPane;


	static int ColorIndex;
	static Color[] backgrounds = {new Color(219, 219, 219), new Color(31, 36, 64), new Color(74, 55, 30), new Color(56, 10, 10), new Color(103, 111, 111), new Color(50, 78, 51), new Color(183, 221, 225), new Color(96, 106, 106), new Color(214, 182, 102), new Color(71, 148, 62), new Color(46, 79, 42), new Color(245,203,17), new Color(113,26,19), new Color(134,137,133), new Color(54,96,39)};
	static Color[] foregrounds = {Color.WHITE, new Color(14, 16, 53), new Color(28, 69, 18), new Color(123, 15, 15), new Color(193, 11, 11), new Color(89, 123, 81), new Color(149, 184, 217), new Color(18, 16, 25), new Color(201, 132, 72), new Color(157, 150, 144), Color.BLACK, new Color(255,235,105), Color.BLACK, new Color(194,198,192), new Color(102,183,75)};
	static Color[] borders = {Color.BLACK, new Color(127, 130, 181), new Color(161, 201, 161), new Color(242, 179, 53), new Color(255, 226, 5), new Color(105, 225, 61), Color.WHITE, new Color(86, 230, 86), new Color(110, 60, 13), new Color(117, 57, 0), new Color(52, 117, 59), new Color(112, 0, 181), Color.RED, new Color(46,73,37), new Color(89,59,15)};

	static ArrayList<Weapon> m = new ArrayList<Weapon>();
	static ArrayList<Weapon> r = new ArrayList<Weapon>();
	static ArrayList<Weapon> h = new ArrayList<Weapon>();
	static ArrayList<Weapon> ml = new ArrayList<Weapon>();
	static ArrayList<Weapon> rl = new ArrayList<Weapon>();
	static ArrayList<Weapon> hl = new ArrayList<Weapon>();

	static boolean un1 = false;
	static boolean un2 = false;
	static boolean un3 = false;
	static boolean un4 = false;
	static boolean un5 = false;
	static boolean un6 = false;
	static boolean un7 = false;
	static boolean un8 = false;
	static boolean un9 = false;
	static boolean un10 = false;
	static boolean un11 = false;
	static boolean un12 = false;
	static boolean un13 = false;
	static boolean un14 = false;
	static boolean un15 = false;
	static boolean un16 = false;
	static boolean un17 = false;
	static boolean un18 = false;
	static boolean un19 = false;
	static boolean un20 = false;

	static int armorModifier = 0;
	static int[] resistances;
	static String[] damageTypes;

	static int[] armors;
	static int[] arDur;
	static ArrayList<Armor> Armors = new ArrayList<Armor>();

	static class SwingOpenImage {
		public static JFrame SOI(String filePath)
		{
			frame = new JFrame("Adventure of Ages: Forest of Death");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {frame.setIconImage(ImageIO.read(new File("media/AoA icon.png")));}catch(Exception e){e.printStackTrace();}    
			ImageIcon img = null;
			try{img = new ImageIcon(new URL("file:media/logo.gif"));}catch (Exception e) {System.out.println(e.getStackTrace());}             
			JLabel lbl = new JLabel(img);
			frame.setResizable(false);
			frame.getContentPane().add(lbl, BorderLayout.CENTER);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			try {Thread.sleep(3000);} catch(Exception e) {System.out.println(e.getStackTrace());}
			frame.remove(lbl);
			return frame;
		}
	}

	public Adventure_Of_Ages(JFrame frame) {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		contentPane = frame.getContentPane();
		contentPane = new JPanel();
		((JComponent) contentPane).setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		panel = new JPanel();
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {30, 100, 0, 0, 100, 30};
		gbl_panel.rowHeights = new int[] {30, 50, 30, 30, 30, 30};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		textField = new JLabel();
		textField.setVerticalAlignment(SwingConstants.CENTER);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setOpaque(true);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 1;
		jsp3 = new JScrollPane(textField);
		panel.add(jsp3, gbc_textField_2);

		jta = new JTextArea();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.weighty = 2.0;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		jta.setEditable(false);
		jsp = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		panel.add(jsp, gbc_textField);
		jta.setColumns(10);
		DefaultCaret caret = (DefaultCaret)jta.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		jta2 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.weighty = 0.45;
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		jta2.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
		jsp2 = new JScrollPane(jta2);
		panel.add(jsp2, gbc_textField_1);
		jta2.setColumns(10);
		jta2.addActionListener(this);
		frame.setResizable(true);
		setColors();
		frame.setVisible(true);
	}

	public static void vibrate(Frame frame, int vl, int vv) { 
		try { 
			final int originalX = frame.getLocationOnScreen().x; 
			final int originalY = frame.getLocationOnScreen().y; 
			for(int i = 0; i < vl; i++) { 
				Thread.sleep(10); 
				frame.setLocation(originalX, originalY + vv); 
				Thread.sleep(10); 
				frame.setLocation(originalX, originalY - vv);
				Thread.sleep(10); 
				frame.setLocation(originalX + vv, originalY);
				Thread.sleep(10); 
				frame.setLocation(originalX, originalY); 
			} 
		} 
		catch (Exception err) { 
			err.printStackTrace(); 
		} 
	}

	public static void main(String[] args) throws IOException {
		Scanner color = new Scanner(new File("data/Preferences.dat"));
		ColorIndex = color.nextInt();
		audioInt = color.nextInt();
		audioSetting = ((double)audioInt) / 100;
		textSpeedindex = color.nextInt();
		switch(textSpeedindex) {
		case 1:
			textSpeed = 15;
			break;
		case 2:
			textSpeed = 8;
			break;
		case 3:
			textSpeed = 3;
			break;
		}

		color.close();
		new Adventure_Of_Ages(SwingOpenImage.SOI("media/Logo-Gif.gif"));
		//FileThing f = new FileThing();
		//f.save(4, "");
		LoadEnemies();
		start();
	}

	public static void start() throws IOException {
		while(true) {
			Prnt("Adventure of Ages");
			boolean b = true;
			while (b) {
				Prnt("Type \"Load\" to load a saved game. Type \"New\" to start a new game. Type \"Settings\" to open the settings menu.");
				String s = inputter();
				switch(s.toLowerCase()) {
				case"l":
				case "load":
					b = false;
					loadState();
					break;
				case "n":
				case "new":
					b = false;
					ArrayList<Object> o = init();
					Adventure(o);
					break;
				case"s":
				case "settings":
					settings();
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void Adventure(ArrayList<Object> o) throws IOException {
		Name = (String)o.get(0);
		Attack = (int)o.get(1);
		Defense = (int)o.get(2);
		Speed = (int)o.get(3);
		Intelligence = (int)o.get(4);
		Vitality = (int)o.get(5);
		setCharacterStats();
		money = (int)o.get(6);
		maps = (int)o.get(7);
		cures = (int)o.get(8);
		bullets = (int)o.get(9);
		arrows = (int)o.get(10);
		throwables = (int)o.get(11);
		crystals = (int)o.get(12);
		diseaseLevel = (int)o.get(13);
		diseaseTime = (int)o.get(14);
		ArrayList<Integer> a = (ArrayList<Integer>)o.get(15);
		for (int i = 0; i<a.size();i++) {
			inventory.add(Weapons.get(a.get(i)));
		}
		durabilities = (ArrayList<Integer>)o.get(16);
		MaxAP = (int)o.get(17);
		ArrayList<String> e = (ArrayList<String>)o.get(18);
		String[] en = new String[e.size()];

		ArrayList<Integer> enemieskil = (ArrayList<Integer>)o.get(19);
		for(int i = 0; i < en.length; i++)
			killedEnemies.put(e.get(i), enemieskil.get(i));
		ArrayList<Integer> v = (ArrayList<Integer>)o.get(20);
		for(int x: v) {
			bossesKilled.add(bosses.get(x));
		}
		PotionsConsumed = (int)o.get(21);
		manaPotions = (int)o.get(22);
		potions = (int[])o.get(23);
		potTypeIndex = (int)o.get(24);
		Level = (int)o.get(25);
		XP = (int)o.get(26);
		XPtoNext = (int)o.get(27);
		XPThreshold = (int)o.get(28);
		HP = (int)o.get(29);
		Mana = (int)o.get(30);
		area = (int)o.get(31);
		areaProgress = (int)o.get(32);
		areaSize = (int)o.get(33);
		mapMod = (int)o.get(34);
		boolean loadedState = (boolean)o.get(35);
		numkilled = (int[])o.get(36);
		difficulty = (int)o.get(37);
		Luck = (int)o.get(38);

		gamerunning = true;
		if(loadedState) {
			for (areaProgress = areaProgress; areaProgress< areaSize; areaProgress++) {
				Prnt(Name + " " + biomes.get(area).contin);
				tick();
				actionPrompt();
				if(!gamerunning) {
					return;
				}
				if(roll(1,100) < 65 - mapMod - calculatePity() - Luck) {
					if (roll(1,5) == 1) {
						ghostFight();
						if(!gamerunning) {
							return;
						}
					} else if(roll(1,100) < 75 || Level == 1 || areaProgress <= 5 - difficulty) {
						enemyEncounter();
						if(!gamerunning) {
							return;
						}
					} else {
						bossEncounter();
						if(!gamerunning) {
							return;
						}
					}
				} else {
					int roll = roll(1,100);
					if (roll > 50) {
						event(roll(1,5),area);
						if(!gamerunning) {
							return;
						}
					} else if (roll <= 50 && roll < 20) {
						event(roll(6,10),area);
						if(!gamerunning) {
							return;
						}
					} else {
						event(roll(11,13),area);
						if(!gamerunning) {
							return;
						}
					}
				}

			}
			Prnt(biomes.get(area).exit + "\n");
		}

		int merc = roll(1,100);
		if(merc < 30 + calculatePity() + Luck) {
			merchant();
			if(!gamerunning) {
				return;
			}
		}

		while(true) {
			areaProgress = 0;
			areaSize = roll(7,14);
			int rollednum = roll(0,biomes.size()-1);
			area = rollednum;
			Prnt("\n" + Name + " " + biomes.get(area).enter);
			boolean m = mapPrompt();
			mapMod = 0;
			if (m) {
				mapMod = 25;
			}
			for (areaProgress = 0; areaProgress< areaSize; areaProgress++) {
				Prnt(Name + " " + biomes.get(area).contin);
				tick();
				actionPrompt();
				if(!gamerunning) {
					return;
				}
				if(roll(1,100) < 65 - mapMod - calculatePity() - Luck) {
					if (roll(1,5) == 1 && (roll(1,2)==1 && area == 11)) {
						ghostFight();
						if(!gamerunning) {
							return;
						}
					} else if(roll(1,100) < 75 || Level == 1 || areaProgress <= 5 - difficulty) {
						enemyEncounter();
						if(!gamerunning) {
							return;
						}
					} else {
						bossEncounter();
						if(!gamerunning) {
							return;
						}
					}
				} else {
					int roll = roll(1,100);
					if (roll > 50) {
						event(roll(1,5),area);
						if(!gamerunning) {
							return;
						}
					} else if (roll <= 50 && roll < 20) {
						event(roll(6,10),area);
						if(!gamerunning) {
							return;
						}
					} else {
						event(roll(11,13),area);
						if(!gamerunning) {
							return;
						}
					}
				}

			}
			Prnt(biomes.get(area).exit);

			merc = roll(1,100);
			if(merc < 30 + calculatePity() + Luck) {
				merchant();
			}
		}
	}

	public static void actionPrompt() throws IOException {
		boolean inp = false;
		while (!inp) {
			Prnt("Type \"continue\" to continue on, \"potions\" to open the potion menu, \"inventory\" to open your inventory, and \"switch\" to switch your weapons. Type \"Save\" to save your game. Type \"Settings\" to open the settings menu.");
			String input = inputter();
			switch (input.toLowerCase()) {
			case"c":
			case "continue":
				inp = true;
				break;
			case"p":
			case "potions":
				inp = false;
				potion();
				break;
			case"i":
			case "inventory":
				inventory();
				break;
			case"sw":
			case "switch":
				weaponSwitch();
				break;
			case"s":
			case "save":
				save();
				break;
			case "break":
				durabilities.set(0, 0);
				tick();
				if(!gamerunning) {
					return;
				}
				break;
			case"set":
			case "settings":
				settings();
				break;
			case "die":
				HP = 0;
				deathCheck();
				if(!gamerunning) {
					return;
				}
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
	}

	public static boolean mapPrompt() {
		boolean b = false;
		while(!b) {
			Prnt("Use a map? ("+maps+" remaining) Type \"yes\" to use one and \"no\" to not.");
			String inp = inputter();
			switch(inp.toLowerCase()) {
			case"y":
			case "yes":
				if(maps > 0) {
					Prnt("You use a map");
					maps--;
					return true;
				}else {
					Prnt("You have no maps left!");
				}
				b = true;
				break;
			case"n":
			case "no":
				Prnt("You don't use a map.");
				return false;
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
		return false;
	}
	public static void setCharacterStats() {
		MaxHP = Vitality * 5;
		HP = MaxHP;
		MaxMana = Intelligence * 2;
		Mana = MaxMana;
	}

	public static ArrayList<Object> init() throws IOException {
		boolean inp = false;
		while(!inp) {
			String s = ("Select Difficulty: Type \"Normal\" for the standard experience, or \"INSANE\" for a LUDICROUS CHALLENGE");
			if (un1) { 
				s += ". Type \"LUDICROUS\" for a NEARLY IMPOSSIBLE CHALLENGE!!!!!";
			}
			Prnt(s);
			String diff = inputter();
			switch(diff.toLowerCase()) {
			case"n":
			case "normal":
				difficulty = 1;
				inp = true;
				break;
			case"i":
			case "insane":
				difficulty = 2;
				inp = true;
				break;
			case"l":
			case "ludicrous":
				if(un1) {
					difficulty = 4;
					inp = true;
				} else {
					Prnt("Invalid difficulty");
					vibrate(frame,1,3);
				}
				break;
			default:
				Prnt("Invalid difficulty");
				vibrate(frame,1,3);
			}
		}
		Prnt("Please input your character's name: ");
		Name = inputter();
		inp = false;
		while(!inp) {
			ArrayList<String> s1 = new ArrayList<String>();
			s1.add("Please input your character's class. Available classes are: Knight, Barbarian, Guard, Archer, Gunslinger, Assassin, Mage, Sorcerer,");
			s1.add(" Pyromancer");
			s1.add(". ");
			if(un2) {
				s1.add(2, ", Rockstar");
			}
			if(un3) {
				s1.add(2, ", Bandit");
			}
			if(un4) {
				s1.add(2, ", Dragonborn");
			}
			if(un5) {
				s1.add(2, ", Alchemist");
			}
			if(un6) {
				s1.add(2, ", Berserker");
			}
			if(un7) {
				s1.add(2, ", Brewer");
			}
			if(un8) {
				s1.add(2, ", Mobster");
			}
			if(un9) {
				s1.add(2, ", Insectoid");
			}
			if(un10) {
				s1.add(2, ", Doomed Marine");
			}
			if(un11) {
				s1.add(2, ", Aristocrat");
			}
			if(un12) {
				s1.add(2, ", Space Wizard");
			}
			if(un13) {
				s1.add(2, ", Sniper");
			}
			String s = "";
			for(int i = 0; i < s1.size(); i++) {
				s += s1.get(i);
				if(i == s1.size() - 3) {
					s += " and";
				}
			}
			Prnt(s);
			String clas = inputter();
			switch (clas.toLowerCase()) {
			case"kn":
			case "knight":
				Attack = 16;
				Defense = 14;
				Speed = 8;
				Intelligence = 14;
				Vitality = 22;
				setCharacterStats();
				inventory.add(Weapons.get(0));
				durabilities.add(Weapons.get(0).durability);
				arrows = 0;
				bullets = 0;
				throwables = 0;
				maps = 4;
				potions[0] = 10;
				inp=true;
				break;
			case"ba":
			case "barbarian":
				Attack = 19;
				Defense = 13;
				Speed = 8;
				Intelligence = 9;
				Vitality = 18;
				setCharacterStats();
				inventory.add(Weapons.get(1));
				durabilities.add(Weapons.get(1).durability);
				arrows = 0;
				bullets = 0;
				throwables = 0;
				maps = 2;
				potions[0] = 14;
				inp=true;
				break;
			case"gu":
			case "guard":
				Attack = 13;
				Defense = 16;
				Speed = 9;
				Intelligence = 12;
				Vitality = 21;
				setCharacterStats();
				inventory.add(Weapons.get(2));
				durabilities.add(Weapons.get(2).durability);
				arrows = 0;
				bullets = 0;
				throwables = 0;
				maps = 6;
				potions[0]=8;
				inp=true;
				break;
			case"ar":
			case "archer":
				Attack = 12;
				Defense = 10;
				Speed = 11;
				Intelligence = 15;
				Vitality = 17;
				setCharacterStats();
				inventory.add(Weapons.get(3));
				durabilities.add(Weapons.get(3).durability);
				arrows = 35;
				bullets = 0;
				throwables = 0;
				maps = 5;
				potions[0]=16;
				inp=true;
				break;
			case"gn":
			case "gunslinger":
				Attack = 12;
				Defense = 10;
				Speed = 13;
				Intelligence = 18;
				Vitality = 19;
				setCharacterStats();
				inventory.add(Weapons.get(4));
				durabilities.add(Weapons.get(4).durability);
				arrows = 0;
				bullets = 100;
				throwables = 0;
				maps = 4;
				potions[0]=11;
				inp=true;
				break;
			case"as":
			case "assassin":
				Attack = 20;
				Defense = 6;
				Speed = 16;
				Intelligence = 17;
				Vitality = 15;
				setCharacterStats();
				inventory.add(Weapons.get(5));
				durabilities.add(Weapons.get(5).durability);
				arrows = 0;
				bullets = 0;
				throwables = 37;
				maps = 5;
				potions[0]=17;
				inp=true;
				break;
			case"mg":
			case "mage":
				Attack = 17;
				Defense = 11;
				Speed = 7;
				Intelligence = 27;
				Vitality = 16;
				setCharacterStats();
				inventory.add(Weapons.get(6));
				durabilities.add(Weapons.get(6).durability);
				arrows = 0;
				bullets = 0;
				throwables = 0;
				maps = 6;
				potions[0]=14;
				inp=true;
				break;
			case"sr":
			case "sorcerer":
				Attack = 17;
				Defense = 14;
				Speed = 10;
				Intelligence = 24;
				Vitality = 18;
				setCharacterStats();
				inventory.add(Weapons.get(7));
				durabilities.add(Weapons.get(7).durability);
				arrows = 0;
				bullets = 0;
				throwables = 0;
				maps = 4;
				potions[0]=12;
				inp=true;
				break;
			case"py":
			case "pyromancer":
				Attack = 23;
				Defense = 8;
				Speed = 12;
				Intelligence = 19;
				Vitality = 14;
				setCharacterStats();
				inventory.add(Weapons.get(8));
				durabilities.add(Weapons.get(8).durability);
				arrows = 0;
				bullets = 0;
				throwables = 0;
				maps = 2;
				potions[0]=15;
				inp=true;
				break;
			case"rk":
			case "rockstar":
				if (un2) {
					Attack = 14;
					Defense = 12;
					Speed = 12;
					Intelligence = 12;
					Vitality = 20;
					setCharacterStats();
					inventory.add(Weapons.get(65));
					durabilities.add(Weapons.get(65).durability);
					arrows = 0;
					bullets = 0;
					throwables = 0;
					maps = 3;
					potions[0]=12;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"bn":
			case "bandit":
				if (un3) {
					Attack = 18;
					Defense = 9;
					Speed = 15;
					Intelligence = 16;
					Vitality = 15;
					setCharacterStats();
					inventory.add(Weapons.get(64));
					durabilities.add(Weapons.get(64).durability);
					arrows = 0;
					bullets = 0;
					throwables = 0;
					maps = 6;
					potions[0]=7;
					money = 500;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"db":
			case "dragonborn":
				if (un4) {
					Attack = 17;
					Defense = 10;
					Speed = 9;
					Intelligence = 16;
					Vitality = 21;
					setCharacterStats();
					inventory.add(Weapons.get(14));
					durabilities.add(Weapons.get(14).durability);
					arrows = 0;
					bullets = 0;
					throwables = 0;
					maps = 6;
					potions[0]=13;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"al":
			case "alchemist":
				if (un5) {
					Attack = 10;
					Defense = 16;
					Speed = 15;
					Intelligence = 22;
					Vitality = 16;
					setCharacterStats();
					inventory.add(Weapons.get(28));
					durabilities.add(Weapons.get(28).durability);
					arrows = 0;
					bullets = 0;
					throwables = 0; 
					maps = 5;
					potions[0] = 9;
					potions[1] = 6;
					potions[2] = 1;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"be":
			case "berserker":
				if (un6) {
					Attack = 18;
					Defense = 6;
					Speed = 18;
					Intelligence = 4;
					Vitality = 18;
					setCharacterStats();
					inventory.add(Weapons.get(71));
					durabilities.add(Weapons.get(71).durability);
					arrows = 0;
					bullets = 0;
					throwables = 25;
					maps = 0;
					potions[0]=17;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"bw":
			case "brewer":
				if (un7) {
					Attack = 15;
					Defense = 13;
					Speed = 10;
					Intelligence = 15;
					Vitality = 17;
					setCharacterStats();
					inventory.add(Weapons.get(70));
					durabilities.add(Weapons.get(70).durability);
					arrows = 0;
					bullets = 0;
					throwables = 44;
					maps = 4;
					potions[0]=12;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"mb":
			case "mobster":
				if (un8) {
					Attack = 17;
					Defense = 10;
					Speed = 10;
					Intelligence = 16;
					Vitality = 20;
					setCharacterStats();
					inventory.add(Weapons.get(62));
					durabilities.add(Weapons.get(62).durability);
					arrows = 0;
					bullets = 120;
					throwables = 0;
					maps = 4;
					potions[0]=10;
					money = 750;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"in":
			case "insectoid":
				if (un9) {
					Attack = 13;
					Defense = 16;
					Speed = 9;
					Intelligence = 14;
					Vitality = 21;
					setCharacterStats();
					inventory.add(Weapons.get(48));
					durabilities.add(Weapons.get(48).durability);
					arrows = 0;
					bullets = 0;
					throwables = 0;
					maps = 1;
					potions[0]=7;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"ds":
			case "doomed marine":
				if (un10) {
					Attack = 27;
					Defense = 17;
					Speed = 19;
					Intelligence = 10;
					Vitality = 25;
					setCharacterStats();
					inventory.add(Weapons.get(15));
					durabilities.add(Weapons.get(15).durability);
					arrows = 0;
					bullets = 35;
					throwables = 0;
					maps = 4;
					potions[0]=15;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"ac":
			case "aristocrat":
				if (un11) {
					Attack = 15;
					Defense = 9;
					Speed = 16;
					Intelligence = 14;
					Vitality = 17;
					setCharacterStats();
					inventory.add(Weapons.get(79));
					durabilities.add(Weapons.get(79).durability);
					arrows = 0;
					bullets = 0;
					throwables = 0;
					maps = 5;
					potions[0]=10;
					money = 1000;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"sw":
			case "space wizard":
				if (un12) {
					Attack = 18;
					Defense = 8;
					Speed = 15;
					Intelligence = 21;
					Vitality = 19;
					setCharacterStats();
					inventory.add(Weapons.get(74));
					durabilities.add(Weapons.get(74).durability);
					arrows = 0;
					bullets = 0;
					throwables = 0;
					maps = 6;
					potions[0]=10;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			case"sn":
			case "sniper":
				if (un13) {
					Attack = 26;
					Defense = 10;
					Speed = 9;
					Intelligence = 15;
					Vitality = 16;
					setCharacterStats();
					inventory.add(Weapons.get(34));
					durabilities.add(Weapons.get(34).durability);
					arrows = 0;
					bullets = 25;
					throwables = 0;
					maps = 4;
					potions[0]=10;
					inp=true;
				} else {
					Prnt("This is not a valid class.");
					vibrate(frame,1,3);
					inp=false;
				}
				break;
			default:
				Prnt("This is not a valid class.");
				vibrate(frame,1,3);
				inp=false;
			}
		}
		potions[1] = 0;
		potions[2] = 0;
		MaxMana = 10;
		Level = 0;
		levelUp();
		potTypeIndex = roll(0,3);
		Prnt("You start your journey with " + maps + " maps and " + potions[0] + " level 1 " + potionType[potTypeIndex] + "s.");
		if(Name.equals("JBtheNinth")||Name.equals("BossTester")) {
			crystals = 1;
			money = 100000;
			Attack = 1000;
			Defense = 1000;
			Speed = 1000;
			Intelligence = 1000;
			Vitality = 1000;
			Luck = 100000;
			setCharacterStats();
		}
		ArrayList<Object> o = new ArrayList<Object>();
		o.add(Name);
		o.add(Attack);
		o.add(Defense);
		o.add(Speed);
		o.add(Intelligence);
		o.add(Vitality);
		o.add(money);
		o.add(maps);
		o.add(cures);
		o.add(bullets);
		o.add(arrows);
		o.add(throwables);
		o.add(0);
		o.add(-1);
		o.add(0);
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(Weapons.indexOf(inventory.get(0)));
		o.add(a);
		o.add(durabilities);
		o.add(10);
		o.add(new ArrayList<String>());
		o.add(new ArrayList<Integer>());
		o.add(new ArrayList<Enemy>());
		o.add(0);
		o.add(manaPotions);
		o.add(potions);
		o.add(potTypeIndex);
		o.add(Level);
		o.add(0);
		o.add(XPtoNext);
		o.add(XPThreshold);
		o.add(MaxHP);
		o.add(MaxMana);
		o.add(1);
		o.add(0);
		o.add(10);
		o.add(0);
		o.add(false);
		o.add(new int[biomes.size()]);
		o.add(difficulty);
		o.add(Luck);
		inventory.clear();
		return o;
	}

	public static void event(int i, int a) throws IOException {
		switch (i) {
		case 1:
			trap(a);
			if(!gamerunning) {
				return;
			}
			break;
		case 2:
			forge();
			break;
		case 3:
			altar();
			if(!gamerunning) {
				return;
			}
			break;
		case 4:
			priest();
			break;
		case 5:
			trainer();
			break;
		case 6:
			lady();
			break;
		case 7:
			chest();
			break;
		case 8:
			slab();
			break;
		case 9:
			gambler();
			break;
		case 10:
			moneyMan();
			break;
		case 11:
			oldMan();
			if(!gamerunning) {
				return;
			}
			break;
		case 12:
			vista();
			break;
		case 13:
			LegVendor();
			break;
		default:
			break;
		}
	}

	public static void combat() throws IOException {
		tick();
		if (Speed >= es) {
			playerTurn();
			if (OpponentHealth > 0 && notrun)
				enemyTurn();
			if(!gamerunning) {
				return;
			}
		} else {
			enemyTurn();
			if(!gamerunning) {
				return;
			}
			playerTurn();
		}
	}

	public static void playerTurn() {
		AP = MaxAP;
		while (AP > 0) {
			if(OpponentHealth < 1) {
				break;
			}
			Prnt(Opponent + " is at " + OpponentHealth + " hp. You have " + AP + " / " + MaxAP + " movement points left this turn.");
			boolean inputgood = false;
			while(!inputgood) {
				Prnt("Type \"Potions\" to open the potion menu. Type \"Combat\" to open the combat menu");
				String input = (inputter());
				switch (input.toLowerCase()) {
				case"p":
				case "potions":
					potion();
					inputgood = true;
					break;
				case"c":
				case "combat":
					pcombat();
					inputgood = true;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
					inputgood = false;
				}
			}
		}
	}

	public static void pcombat() {
		String s = "";
		if(durabilities.size()>0) {
			if(durabilities.get(0)<1) {
				s+=("Your ");  
				Prnt(s+inventory.get(0).Name + " broke!");
				durabilities.remove(0);
				inventory.remove(0);
			}
		}
		HP = (int)HP;
		Prnt(Opponent + " is at " + OpponentHealth + " hp. You have " + AP + " / " + MaxAP + " movement points.");
		boolean inp = false;
		while (!inp) {
			try {
				boolean b = true;
				int i = inventory.get(0).WeaponType;
				switch(i) {
				case 4:
					if(arrows < inventory.get(0).AmmoCost) {
						b = false;
						Prnt("Not enough ammo to use your " + inventory.get(0).Name + "! Type \"Switch\" to switch weapons, and \"Punch\" to punch the enemy. Type \"Cancel\" to do nothing. Type \"Run\" to try to run.");
					}
					break;
				case 5:
					if(bullets < inventory.get(0).AmmoCost || (inventory.get(0).Name.equalsIgnoreCase("coingun") && money < 10)) {
						b = false;
						Prnt("Not enough ammo to use your " + inventory.get(0).Name + "! Type \"Switch\" to switch weapons, and \"Punch\" to punch the enemy. Type \"Cancel\" to do nothing. Type \"Run\" to try to run.");
					}
					break;
				case 6:
					if(throwables < inventory.get(0).AmmoCost) {
						b = false;
						Prnt("Not enough ammo to use your " + inventory.get(0).Name + "! Type \"Switch\" to switch weapons, and \"Punch\" to punch the enemy. Type \"Cancel\" to do nothing. Type \"Run\" to try to run.");
					}
					break;
				case 7:
					if(Mana < inventory.get(0).ManaCost) {
						b = false;
						Prnt("Not enough mana to use your " + inventory.get(0).Name + "! Type \"Switch\" to switch weapons, and \"Punch\" to punch the enemy. Type \"Cancel\" to do nothing. Type \"Run\" to try to run.");
					}
					break;
				case 8:
					if(Mana < inventory.get(0).ManaCost) {
						b = false;
						Prnt("Not enough mana to use your " + inventory.get(0).Name + "! Type \"Switch\" to switch weapons, and \"Punch\" to punch the enemy. Type \"Cancel\" to do nothing. Type \"Run\" to try to run.");
					}
					break;
				case 9:
					if(Mana < inventory.get(0).ManaCost) {
						b = false;
						Prnt("Not enough mana to use your " + inventory.get(0).Name + "! Type \"Switch\" to switch weapons, and \"Punch\" to punch the enemy. Type \"Cancel\" to do nothing. Type \"Run\" to try to run.");
					}
					break;
				}
				if (b) {
				s = "Type \"Light Attack\" to attack with your " + inventory.get(0).Name;
				
				s+=", \"Signature Attack\" to use a powerful attack with your " + inventory.get(0).Name;
				
				s+=", \"Switch\" to switch weapons, and \"Punch\" to punch the enemy. Type \"Cancel\" to do nothing. Type \"Run\" to try to run.";
				Prnt(s);
				}
			} catch (Exception e) {
				Prnt("No weapons! Type \"Punch\" to punch the enemy. Type \"Cancel\" to do nothing. Type \"Run\" to try to run.");
			}
			String input = (inputter());
			switch (input.toLowerCase()) {
			case"l":
			case "light attack":
				if(inventory.size()==0) {
					Prnt("No weapon to attack with!");
					break;
				}
				inp = true;
				durabilities.set(0,durabilities.get(0)-2);
				switch(inventory.get(0).WeaponType) {
				case 1:
					AP-=inventory.get(0).APDrain;
					for(int i = 0; i<inventory.get(0).NumHits;i++) {
						int roll = roll(0,100);
						int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
						if(roll<hitc + Luck + calculatePity()) {
							roll = roll(1,8);
							if(roll == 1) {
								int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75),Attack,ed);
								Prnt("Critical hit! "+dmg+" damage!");
								vibrate(frame,2,4);
								OpponentHealth-=dmg;
							}else {
								int dmg = calculateDamage(inventory.get(0).BaseDam,Attack,ed);
								OpponentHealth-=dmg;
								Prnt("Hit! "+dmg+" damage!");
							}
						}else {
							Prnt("Miss! 0 damage!");
						}
					}
					break;
				case 2:
					AP-=inventory.get(0).APDrain;
					for(int i = 0; i<inventory.get(0).NumHits;i++) {
						int roll = roll(0,100);
						int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
						if(roll<hitc + Luck + calculatePity()) {
							roll = roll(1,8);
							if(roll == 1) {
								int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*1.5),Attack,ed);
								Prnt("Critical hit! "+dmg+" damage!");
								vibrate(frame,2,4);
								OpponentHealth-=dmg;
							}else {
								int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.5),Attack,ed);
								OpponentHealth-=dmg;
								Prnt("Hit! "+dmg+" damage!");
							}
						}else {
							Prnt("Miss! 0 damage!");
						}
					}
					break;
				case 3:
					AP-=inventory.get(0).APDrain;
					for(int i = 0; i<inventory.get(0).NumHits;i++) {
						int roll = roll(0,100);
						int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
						if(roll<hitc + Luck + calculatePity()) {
							roll = roll(1,8);
							if(roll == 1) {
								int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*.75),Attack,ed);
								Prnt("Critical hit! "+dmg+" damage!");
								vibrate(frame,2,4);
								OpponentHealth-=dmg;
							}else {
								int dmg = calculateDamage((int)(inventory.get(0).BaseDam*.75),Attack,ed);
								OpponentHealth-=dmg;
								Prnt("Hit! "+dmg+" damage!");
							}
						}else {
							Prnt("Miss! 0 damage!");
						}
					}
					break;
				case 4:
					if(arrows>inventory.get(0).AmmoCost) {
						AP-=inventory.get(0).APDrain;
						arrows-=inventory.get(0).AmmoCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75),Attack,ed);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage(inventory.get(0).BaseDam,Attack,ed);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough ammo!");
					}
					break;
				case 5:
					if(inventory.get(0).Name.toLowerCase().equals("coingun")) {
						if(money < 10) {
							Prnt("Not enough ammo!");
							break;
						}
						money -= 10;
					}
					if(bullets>=inventory.get(0).AmmoCost) {
						AP-=inventory.get(0).APDrain;
						bullets-=inventory.get(0).AmmoCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(00,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*1.5),Attack,ed);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.5),Attack,ed);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough ammo!");
					}
					break;
				case 6:
					if(throwables>inventory.get(0).AmmoCost) {
						AP-=inventory.get(0).APDrain;
						throwables-=inventory.get(0).AmmoCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*.75),Attack,ed);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*.75),Attack,ed);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}

							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough ammo!");
					}
					break;
				case 7:
					if(Mana>inventory.get(0).ManaCost) {
						AP-=inventory.get(0).APDrain;
						Mana-=inventory.get(0).ManaCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75),Attack,ed);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage(inventory.get(0).BaseDam,Attack,ed);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
								HP+=inventory.get(0).Healing;
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough mana!");
					}
					break;
				case 8:
					if(Mana>inventory.get(0).ManaCost) {
						AP-=inventory.get(0).APDrain;
						Mana-=inventory.get(0).ManaCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*1.5),Attack,ed);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.5),Attack,ed);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
								HP+=inventory.get(0).Healing;
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough mana!");
					}
					break;
				case 9:
					if(Mana>inventory.get(0).ManaCost) {
						AP-=inventory.get(0).APDrain;
						Mana-=inventory.get(0).ManaCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*.75),Attack,ed);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*.75),Attack,ed);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
								HP+=inventory.get(0).Healing;
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough mana!");
					}
					break;
				}
				break;
			case"s":
			case "signature attack":
				if(inventory.size()==0) {
					Prnt("No weapon to attack with!");
					break;
				}
				inp = true;
				durabilities.set(0,durabilities.get(0)-5);
				switch(inventory.get(0).WeaponType) {
				case 1:
					AP-=inventory.get(0).APDrain;
					for(int i = 0; i<inventory.get(0).NumHits * 2;i++) {
						int roll = roll(0,100);
						int hitc = calculateHitChance(inventory.get(0).HitChance-5,Intelligence,es);
						if(roll<hitc + Luck + calculatePity()) {
							roll = roll(1,8);
							if(roll == 1) {
								int dmg = calculateDamage((int)((inventory.get(0).BaseDam+ed)*1.75),Attack,0);
								Prnt("Critical hit! "+dmg+" damage!");
								vibrate(frame,2,4);
								OpponentHealth-=dmg;
							}else {
								int dmg = calculateDamage(inventory.get(0).BaseDam+ed,Attack,0);
								OpponentHealth-=dmg;
								Prnt("Hit! "+dmg+" damage!");
							}
						}else {
							Prnt("Miss! 0 damage!");
						}
					}
					break;
				case 2:
					AP-=inventory.get(0).APDrain;
					for(int i = 0; i<inventory.get(0).NumHits;i++) {
						int roll = roll(0,100);
						int hitc = calculateHitChance(inventory.get(0).HitChance-15,Intelligence,es);
						if(roll<hitc + Luck + calculatePity()) {
							roll = roll(1,8);
							if(roll == 1) {
								int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*2),Attack,ed);
								Prnt("Critical hit! "+dmg+" damage!");
								vibrate(frame,2,4);
								OpponentHealth-=dmg;
							}else {
								int dmg = calculateDamage((int)(inventory.get(0).BaseDam*2),Attack,ed);
								OpponentHealth-=dmg;
								Prnt("Hit! "+dmg+" damage!");
							}
						}else {
							Prnt("Miss! 0 damage!");
						}
					}
					break;
				case 3:
					AP-=inventory.get(0).APDrain;
					for(int i = 0; i<inventory.get(0).NumHits;i++) {
						int roll = roll(0,100);
						int hitc = calculateHitChance(inventory.get(0).HitChance-15,Intelligence,es);
						if(roll<hitc + Luck + calculatePity()) {
							switch(inventory.get(0).Affinity) {
							case 1:
								Prnt(Opponent+" was afflicted by frostbite!");
								enemyfrz=true;
								break;
							case 2:
								Prnt(Opponent+" was poisoned!");
								enemypsn=true;
								break;
							case 3:
								Prnt(Opponent+" was burned!");
								enemybrn=true;
								break;
							case 4:
								roll = roll(1,3);
								switch(roll) {
								case 1:
									Prnt(Opponent+" was afflicted by frostbite!");
									enemyfrz=true;
									break;
								case 2:
									Prnt(Opponent+" was poisoned!");
									enemypsn=true;
									break;
								case 3:
									Prnt(Opponent+" was burned!");
									enemybrn=true;
									break;
								}
							}
						}else {
							Prnt("Miss! 0 damage!");
						}
					}
					break;
				case 4:
					if(arrows>inventory.get(0).AmmoCost) {
						AP-=inventory.get(0).APDrain;
						arrows-=inventory.get(0).AmmoCost;
						for(int i = 0; i<inventory.get(0).NumHits * 2;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance-5,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)((inventory.get(0).BaseDam+ed)*1.75),Attack,0);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage(inventory.get(0).BaseDam+ed,Attack,0);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough ammo!");
					}
					break;
				case 5:
					if(inventory.get(0).Name.toLowerCase().equals("coingun")) {
						if(money < 10) {
							Prnt("Not enough ammo!");
							break;
						}
						money -= 10;
					}
					if(bullets>=inventory.get(0).AmmoCost) {
						AP-=inventory.get(0).APDrain;
						bullets-=inventory.get(0).AmmoCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance-15,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*2),Attack,ed);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*2),Attack,ed);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough ammo!");
					}
					break;
				case 6:
					if(throwables>inventory.get(0).AmmoCost) {
						AP-=inventory.get(0).APDrain;
						throwables-=inventory.get(0).AmmoCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								switch(inventory.get(0).Affinity) {
								case 1:
									Prnt(Opponent+" was afflicted by frostbite!");
									enemyfrz=true;
									break;
								case 2:
									Prnt(Opponent+" was poisoned!");
									enemypsn=true;
									break;
								case 3:
									Prnt(Opponent+" was burned!");
									enemybrn=true;
									break;
								case 4:
									roll = roll(1,3);
									switch(roll) {
									case 1:
										Prnt(Opponent+" was afflicted by frostbite!");
										enemyfrz=true;
										break;
									case 2:
										Prnt(Opponent+" was poisoned!");
										enemypsn=true;
										break;
									case 3:
										Prnt(Opponent+" was burned!");
										enemybrn=true;
										break;
									}
								}
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough ammo!");
					}
					break;
				case 7:
					if(Mana>inventory.get(0).ManaCost) {
						AP-=inventory.get(0).APDrain;
						Mana-=inventory.get(0).ManaCost;
						for(int i = 0; i<inventory.get(0).NumHits * 2;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance-5,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)((inventory.get(0).BaseDam+ed)*1.75),Attack,0);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage((inventory.get(0).BaseDam+ed),Attack,0);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
								HP+=inventory.get(0).Healing;
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough mana!");
					}
					break;
				case 8:
					if(Mana>inventory.get(0).ManaCost) {
						AP-=inventory.get(0).APDrain;
						Mana-=inventory.get(0).ManaCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance-15,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								roll = roll(1,8);
								if(roll == 1) {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*1.75*2),Attack,ed);
									Prnt("Critical hit! "+dmg+" damage!");
									vibrate(frame,2,4);
									OpponentHealth-=dmg;
								}else {
									int dmg = calculateDamage((int)(inventory.get(0).BaseDam*2),Attack,ed);
									OpponentHealth-=dmg;
									Prnt("Hit! "+dmg+" damage!");
								}
								HP+=inventory.get(0).Healing;
							}else {
								Prnt("Miss! 0 damage!");
							}
						}
					}else {
						Prnt("Not enough mana!");
					}
					break;
				case 9:
					if(Mana>inventory.get(0).ManaCost) {
						AP-=inventory.get(0).APDrain;
						Mana-=inventory.get(0).ManaCost;
						for(int i = 0; i<inventory.get(0).NumHits;i++) {
							int roll = roll(0,100);
							int hitc = calculateHitChance(inventory.get(0).HitChance-15,Intelligence,es);
							if(roll<hitc + Luck + calculatePity()) {
								switch(inventory.get(0).Affinity) {
								case 1:
									Prnt(Opponent+" was afflicted by frostbite!");
									enemyfrz=true;
									break;
								case 2:
									Prnt(Opponent+" was poisoned!");
									enemypsn=true;
									break;
								case 3:
									Prnt(Opponent+" was burned!");
									enemybrn=true;
									break;
								case 4:
									roll = roll(1,3);
									switch(roll) {
									case 1:
										Prnt(Opponent+" was afflicted by frostbite!");
										enemyfrz=true;
										break;
									case 2:
										Prnt(Opponent+" was poisoned!");
										enemypsn=true;
										break;
									case 3:
										Prnt(Opponent+" was burned!");
										enemybrn=true;
										break;
									}
								}
							}else {
								Prnt("Miss!");
							}
						}
					}else {
						Prnt("Not enough mana!");
					}
					break;
				}
				break;
			case"sw":
			case "switch":
				inp = true;
				weaponSwitch();
				AP-=1;
				break;
			case"p":
			case "punch":
				inp=true;
				AP-=10;
				for(int i = 0; i<1;i++) {
					int roll = roll(0,100);
					int hitc = calculateHitChance(60,Intelligence,es);
					if(roll<hitc + Luck + calculatePity()) {
						int dmg = calculateDamage(50,Attack,ed);
						OpponentHealth-=dmg;
						Prnt("Hit! "+dmg+" damage!");
					}else {
						Prnt("Miss! 0 damage!");
					}
				}
				break;
			case"c":
			case "cancel":
				inp = true;
				Prnt("You do nothing");
				break;
			case"r":
			case "run":
				inp = true;
				if(fb) {
					Prnt("Nowhere to run to!");
				}else {
					Prnt("You try to run...");
					if(calculateRunChance(Speed, HP, es)) {
						Prnt("...and succeed!");
						notrun = false;     
					}else {
						Prnt("...but fail!");
					}
					AP -= 100;
				}
				break;
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
				inp = false;
				break;
			}
			if(HP>MaxHP) {
				HP = MaxHP;
			}
		}
	}

	public static void potion() {
		Prnt("You have " + potions[0] + " level 1 " + potionType[potTypeIndex] + "s, " + potions[1] + " level 2 " + potionType[potTypeIndex] + "s, " + potions[2] + " level 3 " + potionType[potTypeIndex] + "s, and " + manaPotions + " mana potions left.");
		boolean inp = false;
		while (!inp) {
			try {
				Prnt("Type \"Level 1\" to use a level 1 " + potionType[potTypeIndex] + ", \"Level 2\" to use a level 2 " + potionType[potTypeIndex] + ", \"Level 3\" to use a level 3 " + potionType[potTypeIndex] + ", and \"Mana\" to use a mana potion. Type \"Cancel\" to use no potions.");

				String input = inputter();
				switch (input.toLowerCase()) {
				case"1":
				case "level 1":
					inp = true;
					if(potions[0] < 1) {
						Prnt("you have none left!");
					}else {
						if(HP != MaxHP) {
							Prnt("You use a level 1 " + potionType[potTypeIndex]);
							Prnt("Recovered " + 1 * (int)(50 * ((Vitality / 20.0))) + " hp!");
							HP += 1 * (int)(50 * ((Vitality / 20.0)));
							if(HP > MaxHP) {
								HP = MaxHP;
							}
							PotionsConsumed++;
							potions[0] -= 1;
							AP -= 5;
						}else {
							Prnt("You are already at max hp!");
						}
					}
					break;
				case"2":
				case "level 2":
					inp = true;
					if(potions[1] < 1) {
						Prnt("you have none left!");
					}else {
						if(HP != MaxHP) {
							Prnt("You use a level 2 " + potionType[potTypeIndex]);
							Prnt("Recovered " + 2 * (int)(50 * ((Vitality / 20.0))) + " hp!");
							HP += 2 * (int)(50 * (Vitality / 20.0));
							if(HP > MaxHP) {
								HP = MaxHP;
							}
							PotionsConsumed++;
							potions[1] -= 1;
							AP -= 5;
						}else {
							Prnt("You are already at max hp!");
						}
					}
					break;
				case"3":
				case "level 3":
					inp = true;
					if(potions[2] < 1) {
						Prnt("you have none left!");
					}else {
						if(HP != MaxHP) {
							Prnt("You use a level 3 " + potionType[potTypeIndex]);
							Prnt("Recovered " + 3 * (int)(50 * ((Vitality / 20.0))) + " hp!");
							HP += 3 * (int)(50 * ((Vitality / 20.0)));
							if(HP > MaxHP) {
								HP = MaxHP;
							}
							PotionsConsumed++;
							potions[2] -= 1;
							AP -= 5;
						}else {
							Prnt("You are already at max hp!");
						}
					}
					break;
				case"m":
				case "mana":
					inp = true;
					if(manaPotions < 1) {
						Prnt("you have none left!");
					}else {
						if(Mana != MaxMana) {
							Prnt("You use a mana potion.");
							Prnt("Recovered " + 50 + " mana!");
							Mana += 50;
							if(HP > MaxHP) {
								HP = MaxHP;
							}
							PotionsConsumed++;
							manaPotions -= 1;
							AP -= 5;
						}else {
							Prnt("You are already at max mana!");
						}
					}
					break;
				case"c":
				case "cancel":
					Prnt("You use no potions");
					inp = true;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
					break;
				}
			}catch(Exception e) {
				Prnt("Error processing input. Please try again.");
				vibrate(frame,1,5);
				inputter();
				inp = false;
			}
		}
	}

	public static int randomize(int r) {
		r = r + roll(-2,2);
		return r;
	}

	public static int roll(int lo, int hi) {
		int res = rand.nextInt(hi - lo + 1) + lo;
		return res;
	}

	public static void tick() throws IOException {
		AchievementCheck(false);
		MaxHP = Vitality * 5;
		for(int f = 0;f<durabilities.size();f++) {
			if(durabilities.get(f)<1) {
				Prnt("Your "+inventory.get(f).Name+" broke!");
				durabilities.remove(f);
				inventory.remove(f);
			}
		}
		if (diseaseTime == 0 && diseaseLevel >= 0) {
			recover();
		}
		if(diseaseLevel >= 0) {
			diseaseTime -= 1;
			Prnt(Name + " is suffering from " + diseaseNames[diseaseLevel] + " (" + diseaseTime + " turns remaining)");
			int r = randomize(diseaseDamage[diseaseLevel]);
			if(r < 0) {
				r = 0;
			}
			Prnt(r + " damage!");
			HP -= r;
			vibrate(frame,3,(int)Math.ceil((r) / 15.0));
			deathCheck();
			if(!gamerunning) {
				return;
			}
			potion();
			Prnt("Use a cure? (" + cures + " remaining)");
			boolean inp = false;
			while(!inp) {
				try {
					Prnt("Type \"Yes\" to use a cure and \"No\" to not use one.");
					String input = inputter();
					switch(input.toLowerCase()) {
					case"y":
					case "yes":
						inp = true;
						if(cures > 0) {
							Prnt("You use a cure.");
							cures -= 1;
							recover();
						}else {
							Prnt("You have no cures!");
						}
						break;
					case"n":
					case "no":
						inp = true;
						Prnt("You don't use a cure");
						break;
					default:
						Prnt("Invalid input!");
						vibrate(frame,1,3);
					}
				}catch(Exception e) {
					Prnt("Error processing input. Please try again.");
					vibrate(frame,1,5);
					inputter();
					inp = false;
				}
			}
		}
		Mana += Intelligence / 4;
		if (Mana > MaxMana) {
			Mana = MaxMana;
		}
	}

	public static void recover() {
		Prnt("You have recovered from " + diseaseNames[diseaseLevel]);
		Attack -= diseaseAtkBuff[diseaseLevel];
		Defense -= diseaseDefBuff[diseaseLevel];
		Speed -= diseaseSpdBuff[diseaseLevel];
		Intelligence -= diseaseIntBuff[diseaseLevel];
		Vitality -= diseaseVitBuff[diseaseLevel];
		diseaseLevel = -1;

	}

	public static void enemyinit(Enemy current, int enemod1, int enemod2) {
		ea = current.Atk;
		ed = current.Def;
		es = current.Spd;
		ei = current.Int;
		ev = current.Vit;
		for(int i = 0;i<Level * 2;i++) {
			int rolledStat = roll(0,4);
			switch(rolledStat) {
			case 0:
				ea++;
				break;
			case 1:
				ed++;
				break;
			case 2:
				es++;
				break;
			case 3:
				ei++;
				break;
			case 4:
				ev++;
				break;
			}
		}
		ew = randomize(current.Wlth);
		eats = current.Attacks;
		String s1 = "";
		String s2 = "";
		switch(enemod1) {
		case 0:
			s1 = "";
			break;
		case 1:
			s1 = "miniscule ";
			es *= 1.5;
			ev *= .5;
			es = (int) es;
			break;
		case 2:
			s1 = "tiny ";
			es *= 1.25;
			ev *= .75;
			es = (int) es;
			break;
		case 3:
			s1 = "small ";
			es *= 1.125;
			ev *= .875;
			es = (int) es;
			break;
		case 4:
			s1 = "large ";
			es *= .875;
			ev *= 1.125;
			es = (int) es;
			break;
		case 5:
			s1 = "enormous ";
			es *= .75;
			ev *= 1.25;
			es = (int) es;
			break;
		case 6:
			s1 = "titanic ";
			es *= .5;
			ev *= 1.5;
			es = (int) es;
			break;
		}
		//"","diseased ","rabid ","razorclawed ","venomous ",
		//"scrawny ","buff ","destructive ","puny ","ravenous ","weak ","powerful ",
		//"insane ","acidic ","indestructible ",""
		switch(enemod2) {
		case 0:
			s2 = "";
			break;
		case 1:
			s2 = "diseased ";
			ea *= 1.1;
			ev *= .85;
			ed *= .7;
			break;
		case 2:
			s2 = "rabid ";
			ea *= 1.25;
			ev *= .9;
			break;
		case 3:
			s2 = "razorclawed ";
			ea *= 1.3;
			break;
		case 4:
			s2 = "venomous ";
			ea *= 1.45;
			break;
		case 5:
			s2 = "scrawny ";
			ev *= .75;
			es *= 1.1;
			ea *= .8;
			ed *= .6;
			break;
		case 6:
			s2 = "buff ";
			ea *= 1.35;
			ed *= 1.4;
			ev *= 1.25;
			break;
		case 7:
			s2 = "destructive ";
			ea *= 1.5;
			ed *= 1.35;
			ev *= .9;
			break;
		case 8:
			s2 = "puny ";
			ea *= .75;
			ev *= .9;
			break;
		case 9:
			s2 = "ravenous ";
			ea *= 1.4;
			ed *= .9;
			es *= 1.25;
			break;
		case 10:
			s2 = "weak ";
			ea *= .75;
			break;
		case 11:
			s2 = "powerful ";
			ea *= 1.25;
			break;
		case 12:
			s2 = "insane ";
			ea *= 1.3;
			es *= 1.2;
			ei *= .75;
			break;
		case 13:
			s2 = "acidic ";
			ev *= .85;
			ea *= 1.5;
			break;
		case 14:
			s2 = "indestructible ";
			ed *= 3;
			ev *= 1.75;
			break;
		case 15:
			s2 = "vile ";
			ea *= 1.15;
			ed *= 3;
			es *= 1.1;
			break;
		case 16:
			s2 = "savage ";
			ea *= 1.6;
			break;
		case 17:
			s2 = "hellish ";
			ea *= 1.35;
			ed *= 1.65;
			ev *= 1.2;
			break;
		case 18:
			s2 = "versed ";
			ei *= 4;
			break;
		case 19:
			s2 = "foolish ";
			ei = 0;
			break;
		case 20:
			s2 = "festering ";
			ea *= 1.1;
			ed *= 0.75;
			break;
		}
		ea *= difficulty;
		ed *= difficulty;
		es *= difficulty;
		ei *= difficulty;
		ev *= difficulty;
		Opponent = current.Name;
		if(!(Opponent.equals("Death")||Opponent.equals("War")||Opponent.equals("Elvis")||Opponent.equals("Famine")||Opponent.equals("Pestilence")))
			Opponent = "The " + s1 + s2 + current.Name;
		OpponentHealth = randomize(ev * 5);
	}

	public static void FBinit(Enemy current) {
		fb = true;
		ea = current.Atk;
		ed = current.Def;
		es = current.Spd;
		ei = current.Int;
		ev = current.Vit;
		ew = current.Wlth;
		eats = current.Attacks;
		Opponent = current.Name;
		OpponentHealth = randomize(ev * 5);
	}

	public static void enemyEncounter() throws IOException {
		if(numkilled[area] >= 15) {
			bossEncounter();
			if(!gamerunning) {
				return;
			}
		} else {
			enemypsn = false;
			enemybrn = false;
			enemyfrz = false;
			int index = 0;
			if(areaProgress < 2 && Level == 1 && difficulty == 1) {
				index = roll(0,2);
			} else if (areaProgress < 5 && Level == 1 && difficulty == 1) {
				index = roll(0,7);
			} else {
				index = roll(0,9);
			}
			index += (10 * area);
			int mod1 = roll(0,6);
			int mod2 = roll(0,20);
			enemyinit(enemies.get(index), mod1, mod2);
			String s = Name + " encounters ";
			if(!(Opponent.equals("Death")||Opponent.equals("War")||Opponent.equals("Elvis")||Opponent.equals("Famine")||Opponent.equals("Pestilence")))
				s += "a hostile ";
			s+=enemies.get(index).Name;
			Prnt(s);
			Prnt(Opponent + " attacks!");
			while(OpponentHealth > 0 && notrun) {
				combat();
				if(!gamerunning) {
					return;
				}
			}
			if (!notrun) {
				notrun = true;
			} else {
				numkilled[area]++;
				Prnt(Opponent + " was killed!");
				gainXP(100);
				if(difficulty != 1) {
					ew *= (difficulty/1.5);
				}
				s = Opponent + " dropped " + ew + " gold";
				money += ew;
				int die = roll(1,20);
				if (die ==1) {
					Weapon w = obtainWeapon(roll(1,3),false);
					s += " and the " + w.Name;
					inventory.add(w);
					durabilities.add((int)Math.ceil(roll(50,75)/ 100.0) * w.durability);
				} else if (die > 1 && die < 4) {
					s += " and assorted ranged equipment";
					arrows += roll(15,45);
					bullets += roll(15,45);
					throwables += roll(15,45);
				} else if (die > 3 && die < 9) {
					int mapIncr = roll(1,3);
					s += " and " + mapIncr + " maps";
					maps += mapIncr;
				} else if (die > 8 && die < 14){
					int potIncr = roll(2,6);
					s += " and " + potIncr + " level 1 " + potionType[potTypeIndex] + "s";
					potions[0] += potIncr;
				}
				s += "!";
				Prnt(s);
				if (Opponent.endsWith("demon") && inventory.get(0).Name.toLowerCase().equals("super shotgun")) {
					AchievementCheck(true);
				}
				if(killedEnemies.containsKey(enemies.get(index).Name)) {
					killedEnemies.put(enemies.get(index).Name, killedEnemies.get(enemies.get(index).Name)+1);
				} else {
					killedEnemies.put(enemies.get(index).Name, 1);
				}
			}
		}
	}

	public static void ghostFight() throws IOException {
		enemypsn = false;
		enemybrn = false;
		enemyfrz = false;
		int i = roll(1,3);
		initGhost(i);
		if(GhostFight) {
			Prnt(Name + " encounters the wandering ghost of "+Opponent+"!");
			Prnt(Opponent + " attacks!");
			while(OpponentHealth > 0 && notrun) {
				combat();
				if(!gamerunning) {
					return;
				}
			}
			if (!notrun) {
				notrun = true;
			} else {
				Prnt(Opponent + " was killed!");
				gainXP(GhostXP);
				if(difficulty != 1) {
					ew *= (difficulty/1.5);
				}
				String s = Opponent + " dropped " + ew + " gold";
				Prnt(s);
				money += ew;
				file.KillGhost(i);
			}
		}else {
			enemyEncounter();
			if(!gamerunning) {
				return;
			}
		}

	}

	public static void initGhost(int i) throws IOException {
		Scanner ghost = new Scanner(new File("ghosts/ghost"+i+".dat"), "utf-8");
		String s = file.decrpyt(ghost.nextLine());
		ghost.close();
		if(s.equals("empty")) {
			GhostFight = false;
			return;
		}
		String[]stats = s.split(";");
		Opponent = stats[0];
		if(!(Opponent.charAt(0)>=33 && Opponent.charAt(0) <= 125))
			Opponent = Opponent.substring(1);
		ea = Integer.parseInt(stats[1]);
		ed = Integer.parseInt(stats[2]);
		es = Integer.parseInt(stats[3]);
		ei = Integer.parseInt(stats[4]);
		ev = Integer.parseInt(stats[5]);
		ea *= difficulty;
		ed *= difficulty;
		es *= difficulty;
		ei *= difficulty;
		ev *= difficulty;
		OpponentHealth = ev * 5;
		ew = Integer.parseInt(stats[6]);
		GhostXP = Integer.parseInt(stats[7]);
		Weapon w = Weapons.get(Integer.parseInt(stats[8]));
		Attack atk1 = new Attack(" attacks with their "+w.Name,(w.BaseDam*w.NumHits));
		Weapon w1 = Weapons.get(Integer.parseInt(stats[9]));
		Attack atk2 = new Attack(" attacks with their "+w1.Name,(w1.BaseDam*w1.NumHits));
		Attack[] a = {atk1,atk2};
		eats = a;
		GhostFight = true;
	}

	public static void bossEncounter() throws IOException {
		enemypsn = false;
		enemybrn = false;
		enemyfrz = false;
		int index = 0;
		boolean legalBoss = false;
		Enemy boss1 = bosses.get(area*4);
		Enemy boss2 = bosses.get(area*4 + 1);
		Enemy boss3 = bosses.get(area*4 + 2);
		Enemy boss4 = bosses.get(area*4 + 3);
		if(bossesKilled.contains(boss1) && bossesKilled.contains(boss2) && bossesKilled.contains(boss3) && bossesKilled.contains(boss4)) {
			if(numkilled[area] >= 15) {
				Prnt("An enemy was supposed to fight you, but nobody showed up!");
			} else {
				enemyEncounter();
				if(!gamerunning) {
					return;
				}
			}
		} else {
			while (!legalBoss) {
				index = roll(0,3);
				index += (4 * area);
				if(bossesKilled.contains(bosses.get(index))) {
					legalBoss = false;
				} else {
					legalBoss = true;
				}
			}
			int mod1 = roll(0,6);
			int mod2 = roll(0,20);
			enemyinit(bosses.get(index), mod1, mod2);
			int potion = roll(5,10);
			potions[0] += potion;
			Prnt(Name + " finds " + potion + " level 1 " + potionType[potTypeIndex] + "s lying on the ground!");
			String s = Name + " encounters ";
			if(!(Opponent.equals("Death")||Opponent.equals("War")||Opponent.equals("Elvis")||Opponent.equals("Famine")||Opponent.equals("Pestilence")))
				s+="a hostile ";
			s+= bosses.get(index).Name + ". This battle looks tough!";
			Prnt(s);
			Prnt(Opponent + " attacks!");
			while(OpponentHealth > 0 && notrun) {
				combat();
				if(!gamerunning) {
					return;
				}
			}
			if (!notrun) {
				notrun = true;
			} else {
				gainXP(500);
				Prnt(Opponent + " was killed!");
				if(difficulty != 1) {
					ew *= (difficulty/1.5);
				}
				s = Opponent + " dropped " + ew + " gold";
				money += ew;
				int potIncr = roll(2,6);
				s += " and " + potIncr + " level 2 " + potionType[potTypeIndex] + "s";
				potions[1] += potIncr;
				s += "!";
				Prnt(s);
				bossesKilled.add(bosses.get(index));
				if(bossesKilled.size() == bosses.size()) {
					Prnt(Name + " finds a crystal embedded in the creature's body!");
					crystals++;
				}
			}
		}
	}

	public static void enemyTurn() throws IOException {
		if(enemybrn) {
			Prnt(Opponent+" is suffering from a burn!");
			int dmg = calculateDamage(10,Attack,ed);
			OpponentHealth-=dmg;
			Prnt(dmg+" damage!");
		}
		if(enemypsn) {
			Prnt(Opponent+" is suffering from poison!");
			int dmg = calculateDamage(7,Attack,ed);
			OpponentHealth-=dmg;
			Prnt(dmg+" damage!");

		}
		if(enemyfrz) {
			Prnt(Opponent+" is suffering from frostbite!");
			int dmg = calculateDamage(5,Attack,ed);
			OpponentHealth-=dmg;
			Prnt(dmg+" damage!");
		}
		if(OpponentHealth > 0) {
			int attackLength = eats.length;
			int die = roll(0,attackLength-1);
			Attack atk = eats[die];
			Prnt(Opponent + " " + atk.Text + "!");
			int hitChance = (int)(25.0/((double)atk.Dmg/100));
			hitChance = calculateHitChance(hitChance, ei, Speed);
			die = roll (1,100);
			if (die < hitChance - calculatePity() - Luck) {
				String s =  "";
				int damage = (int)(calculateDamage(atk.Dmg,ea,Defense));
				damage = randomize(damage);
				if (Attack > ea && roll(1,100) <= (Attack - ea) + Luck) {
					damage = 1;
					s += "Pathetic ";
				} else {
					if(crit(ei)) {
						vibrate(frame,2,4);
						s += "Critical ";
						damage = (int)(damage*1.25);
						vibrate(frame,2,4);
					}
				}
				s += ("Hit! " + damage + " damage!");
				Prnt(s);
				int initHP = HP;
				HP -= damage;
				vibrate(frame,3,(int)Math.ceil(damage / 15.0));
				if (mod == 1 || mod == 2 || mod == 4 || mod == 13 || mod == 15 || mod == 20 || area == 4) {
					if (roll (1,6) == 1)
						catchDisease();
				}
				if((damage >= (MaxHP * 0.85) && initHP >= MaxHP * 0.85 )) {
					if(HP<1) {
						HP = 1;
						if (roll(0,75) < Luck) {
							HP = 50;
							Prnt(Name + " survives with a great deal of health remaining!");
						} else
							Prnt(Name + " survives with a sliver of health remaining!");
					}
				}
				deathCheck();
				if(!gamerunning) {
					return;
				}
			} else {
				Prnt("Miss! 0 damage!");
			}
		}
	}

	public static boolean crit(int i) {
		return((12+(i/7)) >= roll(1,100));
	}

	public static int calculateDamage(int basedamage, int atk, int def) {
		double dmg = ((basedamage)+10*(Math.sqrt(atk)))-5*(Math.sqrt(def));
		dmg = randomize((int)dmg);
		if (dmg < 1) {
			dmg = 0;
		}
		return (int) dmg;
	}

	public static int calculateHitChance(int h, int i, int s) {
		double hit = ((55)+5*(Math.sqrt(i)))-7*(Math.sqrt(s));
		if(hit<1)
			hit = 1;
		return (int)hit;
	}

	public static boolean calculateRunChance(int spd, int hp, int espd) {
		boolean tryToRun = roll(1,100) < (40 + ((spd - espd) / 2) + calculatePity() + Luck) || roll(1,100) < 1;
		return tryToRun;
	}

	public static int calculatePity() {
		int hpdiff = MaxHP - HP;
		return hpdiff / 5 + Luck;
	}

	public static boolean calculateCriticalHit(int Intel) {
		int crt = (int)(9 + (Math.ceil((double)Intel / 5)));
		int roll = roll(1,100);
		if(crt > roll) {
			return true;

		}
		return false;
	}

	public static void levelUp() throws IOException {
		Level += 1;
		if(Level > 1) {
			Prnt(Name + " leveled up!");
		}
		XP -= XPThreshold;
		XPThreshold = 17 * (Level * Level) + 560;
		if(difficulty ==4) {
			XPThreshold *= 2;
		}
		XPtoNext = XPThreshold - XP;
		int roll;
		roll = roll(0,1);
		if(roll == 1&&Level > 1) {
			Prnt("Attack increased by 1!");
		}
		Attack += roll;
		roll = roll(0,1);
		if(roll == 1&&Level > 1) {
			Prnt("Defense increased by 1!");
		}
		Defense += roll;
		roll = roll(0,1);
		if(roll == 1&&Level > 1) {
			Prnt("Speed increased by 1!");
		}
		Speed += roll;
		roll = roll(0,1);
		if(roll == 1&&Level > 1) {
			Prnt("Intelligence increased by 1!");
		}
		Intelligence += roll;
		roll = roll(0,1);
		if(roll == 1&&Level > 1) {
			Prnt("Vitality increased by 1!");
		}
		Vitality += roll;
		roll = roll(0,1);
		if (roll == 1&&Level > 1) {
			Prnt(Name+"'s luck increased!");
		}
		Luck++;
		switch (Luck) {
		case 5:
			Prnt (Name + " is now slightly lucky!");
			break;
		case 10:
			Prnt (Name + " is now pretty lucky!");
			break;
		case 15:
			Prnt (Name + " is now very lucky!");
			break;
		case 20:
			Prnt (Name + " is now extremely lucky!");
			break;
		}
		setCharacterStats();
		if(diseaseLevel >= 0) {
			diseaseTime = 0;
			tick();
		}
	}

	public static void deathCheck() throws IOException {
		if (HP <=0) {
			vibrate(frame,2,8);
			Prnt("You died! Not bad for being only level " + Level + ".");
			if(roll(1,3) <= 2) {
				Prnt("The ghost of " + Name + " roams the land...");
				String s = "";
				s += Name + ";";
				s += Attack + ";";
				s += Defense + ";";
				s += Speed + ";";
				s += Intelligence + ";";
				s += Vitality + ";";
				s += money + ";";
				s += (XP + Level*1000) + ";";
				s += Weapons.indexOf(inventory.get(0)) + ";";
				if(inventory.size()==1) {
					s += 0 + ";";
				} else {
					s += Weapons.indexOf(inventory.get(1)) + ";";
				}
				file.saveGhost(s);
				file.GhostNum();
			}
			Prnt("\n\n");
			startPrompt();
			if (!gamerunning) {
				return;
			}
		}
	}

	public static void merchant() {
		Prnt("");
		int roll = roll(1,100);
		if(roll > 50) {
			roll = roll(1,2);
		} else if (roll <= 50 && roll > 20) {
			roll = roll(3,4);
		} else {
			roll = 5;
		}
		int initmon = money;
		boolean shopping = true;
		switch(roll) {
		case 1:
			roll = roll(1,3);
			roll *= (difficulty);
			Prnt("You come across a crippled looking knight. He looks to be selling goods.\n\"Hello there, traveller. I have weapons for sale, if you have money\"");
			Prnt("The merchant is selling level 1 " + potionType[potTypeIndex] + "s (" + roll * 20 + " gold), weapons (" + roll * 250 + " gold), arrows (" + roll * 10 + " gold for 3), bullets (" + roll * 5 + " gold for 3), throwables (" + roll * 15 + " gold for 3)and Quick Boots (" + roll * 750 + " gold).");
			while(shopping) {
				Mana += 5;
				if(Mana > MaxMana) {
					Mana = MaxMana;
				}
				if(money < 1) {
					Prnt("You don't have any money!");
					shopping = !shopping;
					break;
				}
				Prnt("Type \"Healing Item\" to buy " + potionType[potTypeIndex] + "s, \"Weapon\" to buy a weapon, \"Arrow\" to buy arrows, \"Bullet\" to buy bullets, \"Throwable\" to buy throwable weapons, and \"Quick Boots\" to buy Quick Boots. Type \"Leave\" to leave.");
				String buy = (inputter());
				switch(buy.toLowerCase()) {
				case"h":
				case "healing item":
					Prnt("How many do you buy?");
					int pot = Integer.parseInt(inputter());
					if(money > pot * 20 * roll) {
						Prnt("You buy " + pot + " " + potionType[potTypeIndex] + "s.");
						potions[0] += pot;
						money -= pot * 20 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"w":
				case "weapon":
					if(money > 250 * roll) {
						Weapon w=Weapons.get(0);
						money -= 250 * roll;
						boolean inp = false;
						while(!inp) {
							Prnt("What type of weapon would you like to buy? Type \"Melee\" to buy a melee weapon, \"Ranged\" to buy a ranged weapon, and \"Magic\" to buy a magic weapon.");
							String type = (inputter());
							switch (type.toLowerCase()) {
							case"m":
							case "melee":
								w=obtainWeapon(1,false);
								inp=true;
								durabilities.add((int)Math.ceil(roll(75,90)/ 100.0) * w.durability);
								inventory.add(w);
								break;
							case"r":
							case "ranged":
								w=obtainWeapon(2,false);
								inp=true;
								durabilities.add((int)Math.ceil(roll(75,90)/ 100.0) * w.durability);
								inventory.add(w);
								break;
							case"mg":
							case "magic":
								w=obtainWeapon(3,false);
								inp=true;
								durabilities.add((int)Math.ceil(roll(75,90)/ 100.0) * w.durability);
								inventory.add(w);
								break;
							default:
								inp=false;
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
						}
						Prnt("The merchant reaches into a satchel and hands you the "+w.Name);
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"a":
				case "arrow":
					Prnt("How many do you buy?");
					int arr = Integer.parseInt(inputter());
					if(money > arr * 10 * roll) {
						Prnt("You buy " + arr*3 + " arrows.");
						arrows += arr*3;
						money -= arr * 20 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"b":
				case "bullet":
					Prnt("How many do you buy?");
					int bul = Integer.parseInt(inputter());
					if(money > bul * 5 * roll) {
						Prnt("You buy " + bul*3 + " bullets.");
						bullets += bul*3;
						money -= bul * 5 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"t":
				case "throwable":
					Prnt("How many do you buy?");
					int kni = Integer.parseInt(inputter());
					if(money > kni * 15 * roll) {
						Prnt("You buy " + kni*3 + " throwables.");
						throwables += 3*kni;
						money -= kni * 15 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"qb":
				case "quick boots":
					if(money > 750 * roll) {
						if(MaxAP < 40) {
							Prnt("You buy Quick Boots.");
							money -= 750 * roll;
							MaxAP += 5;
						}else {
							Prnt("You can't get any quicker!");
							vibrate(frame,2,4);
						}
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"l":
				case "leave":
					Prnt("You leave the shop.");
					shopping = !shopping;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
					break;
				}
			}
			if(money == initmon) {
				Prnt("\"Too stingy to buy anything, eh? Go fall off a cliff.\"");
			}else {
				Prnt("\"Thanks for the business.\"");
			}
			break;
		case 2:
			roll = roll(1,3);
			roll *= (difficulty);
			Prnt("You find an alchemist in front of his brewing table. Maybe you can buy some potions here.\n\"Greetings, traveller. Care to buy some potions?\"");
			Prnt("The merchant is selling level 1 " + potionType[potTypeIndex] + "s (" + roll * 20 + " gold), level 2 " + potionType[potTypeIndex] + "s (" + roll * 60 + " gold), level 3 " + potionType[potTypeIndex] + "s (" + roll * 100 + " gold), cures (" + roll * 150 + " gold), and mana potions (" + roll * 250 + " gold).");
			while(shopping) {
				Mana += 5;
				if(Mana > MaxMana) {
					Mana = MaxMana;
				}
				if(money < 1) {
					Prnt("You don't have any money!");
					shopping = !shopping;
					break;
				}
				Prnt("Type \"Level 1\" to buy level 1 " + potionType[potTypeIndex] + "s, \"Level 2\" to buy level 2 " + potionType[potTypeIndex] + "s, \"Level 3\" to buy level 3 " + potionType[potTypeIndex] + "s, \"Cure\" to buy cures, and \"Mana\" to buy mana potions. Type \"Leave\" to leave.");
				String buy = (inputter());
				switch(buy.toLowerCase()) {
				case"1":
				case "level 1":
					Prnt("How many do you buy?");
					int pot = Integer.parseInt(inputter());
					if(money > pot * 20 * roll) {
						Prnt("You buy " + pot + " level 1 " + potionType[potTypeIndex] + "s.");
						potions[0] += pot;
						money -= pot * 20 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"2":
				case "level 2":
					Prnt("How many do you buy?");
					pot = Integer.parseInt(inputter());
					if(money > pot * 60 * roll) {
						Prnt("You buy " + pot + " level 2 " + potionType[potTypeIndex] + "s.");
						potions[1] += pot;
						money -= pot * 60 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"3":
				case "level 3":
					Prnt("How many do you buy?");
					pot = Integer.parseInt(inputter());
					if(money > pot * 100 * roll) {
						Prnt("You buy " + pot + " level 3 " + potionType[potTypeIndex] + "s.");
						potions[2] += pot;
						money -= pot * 100 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"c":
				case "cure":
					Prnt("How many do you buy?");
					pot = Integer.parseInt(inputter());
					if(money > pot * 150 * roll) {
						Prnt("You buy " + pot + " cures.");
						cures += pot;
						money -= pot * 150 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"m":
				case "mana":
					Prnt("How many do you buy?");
					pot = Integer.parseInt(inputter());
					if(money > pot * 250 * roll) {
						Prnt("You buy " + pot + " mana potions.");
						manaPotions += pot;
						money -= pot * 250 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"l":
				case "leave":
					Prnt("You leave the shop.");
					shopping = !shopping;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
					break;
				}
			}
			if(money == initmon) {
				Prnt("\"Don't wanna buy? Oh, that's too bad...\"");
			}else {
				Prnt("\"Thanks.\"");
			}
			break;
		case 3:
			roll = roll(1,3);
			roll *= (difficulty);
			Prnt("You stop by a rickety tent on the side of a dirt path. The man inside looks to be selling all kinds of things.\n\"Oh, hello there! Anything catch yer eye?\"");
			Prnt("The merchant is selling " + potionType[potTypeIndex] + "s (" + 20 * roll + " gold), maps (" + 1000 * roll + " gold), and crystals (" + 10000 * roll + " gold).");
			while(shopping) {
				Mana += 5;
				if(Mana > MaxMana) {
					Mana = MaxMana;
				}
				if(money < 1) {
					Prnt("You don't have any money!");
					shopping = !shopping;
					break;
				}
				Prnt("Type \"Healing Item\" to buy " + potionType[potTypeIndex] + "s, \"Map\" to buy maps, and \"Crystal\" to buy crystals. Type \"Leave\" to leave.");
				String buy = (inputter());
				switch(buy.toLowerCase()) {
				case"h":
				case "healing item":
					Prnt("How many do you buy?");
					int pot = Integer.parseInt(inputter());
					if(money > pot * 20 * roll) {
						Prnt("You buy " + pot + " level 1 " + potionType[potTypeIndex] + "s.");
						potions[0] += pot;
						money -= pot * 20 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"m":
				case "map":
					Prnt("How many do you buy?");
					pot = Integer.parseInt(inputter());
					if(money > pot * 1000 * roll) {
						Prnt("You buy " + pot + " maps.");
						maps += pot;
						money -= pot * 1000 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"c":
				case "crystal":
					if(money > 10000 * roll) {
						Prnt("You buy a crystal.");
						crystals++;
						money -= 10000 * roll;
					}else {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}
					break;
				case"l":
				case "leave":
					Prnt("You leave the shop.");
					shopping = !shopping;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
					break;
				}
			}
			if(money == initmon) {
				Prnt("\"Leavin' so soon? Be safe out there.\"");
			}else {
				Prnt("\"Thank ye'.\"");
			}
			break;
		case 4:
			double moneyModifier = 1;
			Prnt("There is a bloody altar in the middle of the path. For some reason, it seems to be speaking.\n\"You there, traveller! I can give you great power, but first, you must pay me.\"");
			Prnt("It seems you can pay money to enhance your abilities.");
			while (shopping) {
				int enhmod = ((Attack + Defense + Speed + Intelligence + Vitality) / 10) * Level * difficulty;
				enhmod *= moneyModifier;
				enhmod = (int) enhmod;
				Mana += 5;
				if(Mana > MaxMana) {
					Mana = MaxMana;
				}
				if(money < 1) {
					Prnt("You don't have any money!");
					shopping = !shopping;
					break;
				}
				Prnt("You have " + money + " gold remaining. Type \"Enhance\" to increase one of your stats (" + enhmod + " gold). Type \"Leave\" to leave.");
				String buy = (inputter());
				switch(buy.toLowerCase()) {
				case"e":
				case "enhance":
					if(money < enhmod) {
						Prnt("You can't afford that!");
						vibrate(frame,2,4);
					}else {
						Prnt("Type \"Attack\" to increase your attack stat, \"Defense\" to increase your defense stat, \"Speed\" to increase your speed stat, \"Intelligence\" to increase your intelligence stat, \"Vitality\" to increase your vitality stat, ");
						String pot = inputter();
						switch(pot.toLowerCase()) {
						case"a":
						case "attack":
							Attack++;
							Prnt("Attack stat is now " + Attack + ".");
							break;
						case"d":
						case "defense":
							Prnt("Defense stat is now " + Defense + ".");
							Defense++;
							break;
						case"s":
						case "speed":
							Prnt("Speed stat is now " + Speed + ".");
							Speed++;
							break;
						case"i":
						case "intelligence":
							Prnt("Intelligence stat is now " + Intelligence + ".");
							Intelligence++;
							MaxMana = Intelligence * 10;
							Mana = MaxMana;
							break;
						case"v":
						case "vitality":
							Prnt("Vitality stat is now " + Vitality + ".");
							Vitality++;
							MaxHP = Vitality * 5;
							HP = MaxHP;
							break;
						default:
							Prnt("Invalid input!");
							vibrate(frame,1,3);
						}
						money -= enhmod;
						moneyModifier *= 1.15;
					}
					break;
				case"l":
				case "leave":
					Prnt("You leave the shop.");
					shopping = !shopping;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
					break;
				}
			}
			if(money == initmon) {
				Prnt("\"...\"");
			}else {
				Prnt("\"...\"");
			}
			break;
		case 5:
			roll = roll(1,3);
			double mod = roll / (double)(difficulty);
			roll = (int) mod;
			if (roll == 0) {
				roll = 1;
			}
			Prnt("There is a man huddled on the side of the path. On his back he wears a massive backpack. He tells you, \"I'll buy anything you've got!\"");
			Prnt("It seems you can sell your items to this man.");
			while (shopping) {
				Mana += 5;
				if(Mana > MaxMana) {
					Mana = MaxMana;
				}
				Prnt("Type \"Sell\" to sell an item. Type \"Leave\" to leave.");
				String buy = (inputter());
				switch(buy.toLowerCase()) {
				case"s":
				case "sell":
					ArrayList<String> s = new ArrayList<String>();
					s.add("What do you sell? Type \"Cancel\" to sell nothing");
					boolean mp,cr,cy,ar,bu,th,we,p1,p2,p3,mn;
					mp = false;
					cr = false;
					cy = false;
					ar = false;
					bu = false;
					th = false;
					we = false;
					p1 = false;
					p2 = false;
					p3 = false;
					mn = false;
					if (maps > 0) {
						mp = true;
						s.add( "\"Maps\" to sell maps");
					}
					if (crystals > 0) {
						cy = true;
						s.add("\"Crystals\" to sell crystals");
					}
					if (cures > 0) {
						cr = true;
						s.add("\"Cures\" to sell cures");
					}
					if (inventory.size() > 0) {
						we = true; 
						s.add("\"Weapons\" to sell weapons");
					}
					if (arrows > 0) {
						ar = true; 
						s.add("\"Arrows\" to sell arrows");
					}
					if (bullets > 0) {
						bu = true; 
						s.add("\"Bullets\" to sell bullets");
					}
					if (throwables > 0) {
						th = true; 
						s.add("\"Throwabless\" to sell throwables");
					}
					if (potions[0] > 0) {
						p1 = true; 
						s.add("\"Healing 1\" to sell level 1 healing items");
					}
					if (potions[1] > 0) {
						p2 = true;
						s.add("\"Healing 2\" to sell level 2 healing items");
					}
					if (potions[2] > 0) {
						p3 = true;
						s.add("\"Healing 3\" to sell level 3 healing items");
					}
					if (manaPotions > 0) {
						mn = true;
						s.add("\"Mana\" to sell mana potions");
					}
					String s1 = "";
					for (int i = 0; i < s.size(); i++) {
						s1 += s.get(i);
						if(i < s.size()-2) {
							s1+= ", ";
						} else if (i == s.size() - 2) {
							s1 += ", and ";
						}
					}
					s1 += ".";
					if(mp||cr||cy||we||p1||p2||p3||mn||ar||th||bu) {
						Prnt(s1);
						s1 = inputter().toLowerCase();
						switch(s1) {
						case"m":
						case "maps":
							if(mp) {
								Prnt("The man is offering " + (roll * 550) + " gold for every map you sell. How many maps would you like to sell? You have " + maps);
								try {
									int i = Integer.parseInt(inputter());
									if (maps >= i) {
										maps -= i;
										int goldGain = i * roll * 550;
										money += goldGain;
										Prnt("You sell " + i + " maps for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"c":
						case "cures":
							if(cr) {
								Prnt("The man is offering " + (roll * 75) + " gold for every cure you sell. How many cures would you like to sell? You have " + cures);
								try {
									int i = Integer.parseInt(inputter());
									if (cures >= i) {
										cures -= i;
										int goldGain = i * roll * 75;
										money += goldGain;
										Prnt("You sell " + i + " cures for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"cr":
						case "crystals":
							if(cy) {
								Prnt("The man is offering " + (roll * 6500) + " gold for every crystal you sell. How many crystals would you like to sell? You have" + crystals);
								try {
									int i = Integer.parseInt(inputter());
									if (crystals >= i) {
										crystals -= i;
										int goldGain = i * roll * 6500;
										money += goldGain;
										Prnt("You sell " + i + " crystals for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"w":
						case "weapons":
							if(we) {
								for (int i = 0; i < inventory.size(); i++) {
									Weapon w = inventory.get(i);
									int gold = (int)(w.BaseDam * w.NumHits * (w.HitChance/100.0) * roll * 2);
									Prnt("Type " + (i+1) + " to sell your " + inventory.get(i).Name + ". (" + gold + " gold offered)");	
								}
								Prnt("Type \"0\" to sell nothing.");
								try {
									int i = Integer.parseInt(inputter()) - 1;
									if(i < 0) {
										Prnt("You sell no weapons.");
										break;
									}
									Weapon w = inventory.get(i);
									int gold = (int)(w.BaseDam * w.NumHits * (w.HitChance/100.0) * roll * 2);
									money += gold;
									inventory.remove(i);
									durabilities.remove(i);
									Prnt("You sell your " + w.Name + " for " + gold + " gold.");
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);										
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"a":
						case "arrows":
							if(ar) {
								Prnt("The man is offering " + (roll * 2) + " gold for every arrow you sell. How many arrows would you like to sell? You have " + arrows);
								try {
									int i = Integer.parseInt(inputter());
									if (arrows >= i) {
										arrows -= i;
										int goldGain = i * roll * 2;
										Prnt("You sell " + i + " arrows for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"b":
						case "bullets":
							if(bu) {
								Prnt("The man is offering " + (roll * 3) + " gold for every bullet you sell. How many bullets would you like to sell? You have " + bullets);
								try {
									int i = Integer.parseInt(inputter());
									if (bullets >= i) {
										bullets -= i;
										int goldGain = i * roll * 3;
										Prnt("You sell " + i + " bullets for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"t":
						case "throwables":
							if(th) {
								Prnt("The man is offering " + (roll * 1) + " gold for every throwable you sell. How many throwables would you like to sell? You have " + throwables);
								try {
									int i = Integer.parseInt(inputter());
									if (throwables >= i) {
										throwables -= i;
										int goldGain = i * roll * 1;
										Prnt("You sell " + i + " throwables for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"1":
						case "healing 1":
							if(p1) {
								Prnt("The man is offering " + (roll * 8) + " gold for every level 1 healing item you sell. How many level 1 healing items would you like to sell? You have " + potions[0]);
								try {
									int i = Integer.parseInt(inputter());
									if (potions[0] >= i) {
										potions[0] -= i;
										int goldGain = i * roll * 8;
										Prnt("You sell " + i + " level 1 healing items for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"2":
						case "healing 2":
							if(p2) {
								Prnt("The man is offering " + (roll * 65) + " gold for every level 2 healing item you sell. How many level 2 healing items would you like to sell? You have " + potions[1]);
								try {
									int i = Integer.parseInt(inputter());
									if (potions[1] >= i) {
										potions[1] -= i;
										int goldGain = i * roll * 65;
										Prnt("You sell " + i + " level 2 healing items for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"3":
						case "healing 3":
							if(p3) {
								Prnt("The man is offering " + (roll * 85) + " gold for every level 3 healing item you sell. How many level 3 healing items would you like to sell? You have " + potions[2]);
								try {
									int i = Integer.parseInt(inputter());
									if (potions[2] >= i) {
										potions[2] -= i;
										int goldGain = i * roll * 85;
										Prnt("You sell " + i + " level 3 healing items for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						case"mn":
						case "mana":
							if(mn) {
								Prnt("The man is offering " + (roll * 150) + " gold for every mana potion you sell. How many mana potions would you like to sell? You have " + manaPotions);
								try {
									int i = Integer.parseInt(inputter());
									if (manaPotions >= i) {
										manaPotions -= i;
										int goldGain = i * roll * 150;
										Prnt("You sell " + i + " mana potions for " + goldGain + " gold.");
									} else {
										Prnt("You don't have enough!");
										vibrate(frame,2,4);
									}
								} catch (Exception e) {
									Prnt("Invalid input!");
									vibrate(frame,1,3);
								}
							} else {
								Prnt("Invalid input!");
								vibrate(frame,1,3);
							}
							break;
						default:
							Prnt("Invalid input!");
						}
					} else {
						Prnt("Nothing to sell!");
					}
					break;
				case"l":
				case "leave":
					Prnt("You leave the shop.");
					shopping = !shopping;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
					break;
				}
			}
			if(money == initmon) {
				Prnt("\"Nothing to sell? That's too bad.\"");
			}else {
				Prnt("\"Thanks for the items!\"");
				Prnt("The man stuffs everyhting into his backpack and runs away in a frenzy.");
			}
			break;
		}
	}

	public static void inventory() {
		switch(difficulty) {
		case 1:
			Prnt("Difficulty: Normal");
			break;
		case 2:
			Prnt("Difficulty: Insane!");
			break;
		case 4:
			Prnt("Difficulty: LUDICROUS!!!");
			break;
		}
		Prnt(Name);
		Prnt(Attack + " attack\n" + Defense + " defense\n" + Speed + " speed\n" + Intelligence + " intelligence\n" + Vitality + " vitality");
		Prnt("Level " + Level);
		Prnt(XP + " xp (" + XPtoNext + " to next level)");
		Prnt(money + " gold");
		Prnt(Mana + " / " + MaxMana + " mana");
		Prnt(HP + " / " + MaxHP + " hp");
		if(diseaseLevel >= 0) {
			Prnt("Suffering from " + diseaseNames[diseaseLevel - 1] + " (" + diseaseTime + " turns remaining)");
		}
		try {
			Prnt("Carrying " + inventory.get(0).Name);
		} catch (Exception e) {
			Prnt("Carrying nothing");
		}
		Prnt(arrows+" arrows");
		Prnt(bullets+" bullets");
		Prnt(throwables+" thrown weapons");
		Prnt(cures + " cures");
		Prnt(potions[0] + " level 1 " + potionType[potTypeIndex] + "s");
		Prnt(potions[1] + " level 2 " + potionType[potTypeIndex] + "s");  
		Prnt(potions[2] + " level 3 " + potionType[potTypeIndex] + "s");
		Prnt(maps + " maps");
		Prnt(crystals + " crystals");
		for(int i = 0; i < inventory.size(); i++) {
			Prnt(inventory.get(i).Name + " (" + durabilities.get(i) + " durability)");
		}
	}

	public static void weaponSwitch() {
		String s = "";
		if(inventory.size() > 1) {
			for(int i = 1; i < inventory.size(); i++) {
				s="";
				s+="Press \"" + i + "\" to switch to your ";
				Prnt(s + inventory.get(i).Name + " (" + durabilities.get(i) + " durability) ("+inventory.get(i).APDrain+" AP cost)");
			}
			boolean inp = false;
			while(!inp){
				try {
					int input = Integer.parseInt(inputter());
					if(input > inventory.size()) {
						Prnt("Invalid input!");
						vibrate(frame,1,3);
					}else {
						Prnt("Weapon switched.");
						Weapon w = inventory.get(0);
						inventory.set(0, inventory.get(input));
						inventory.set(input, w);
						int dur = durabilities.get(0);
						durabilities.set(0, durabilities.get(input));
						durabilities.set(input, dur);
						inp = true;
					}
				}catch(Exception e) {
					Prnt("Error processing input. Please try again.");
					vibrate(frame,1,5);
					inp = false;
				}
			}

		}else {
			Prnt("You don't have any other weapons!");
		}
	}

	public static void catchDisease() {
		if(diseaseLevel < 0) {
			diseaseLevel = roll(0,11);
			Prnt("You have contracted " + diseaseNames[diseaseLevel] + "!");
			Attack += diseaseAtkBuff[diseaseLevel];
			Defense += diseaseDefBuff[diseaseLevel];
			Speed += diseaseSpdBuff[diseaseLevel];
			Intelligence += diseaseIntBuff[diseaseLevel];
			Vitality += diseaseVitBuff[diseaseLevel];
			diseaseTime = roll(10,25);
		}
	}

	public static void checkForLevelUp() throws IOException {
		while (XP >= XPThreshold){
			XPtoNext = XPThreshold - XP;
			levelUp();
		}
	}

	public static void trap(int index) throws IOException {
		int randomTrapRoll = roll(0,6);
		double trapmult;
		if(randomTrapRoll < 3) {
			trapmult = 0.75;
			Prnt(Name + " " + biomes.get(index).trap1);
		} else if (randomTrapRoll < 5) {
			trapmult = 1;
			Prnt(Name + " " + biomes.get(index).trap2);
		} else {
			trapmult = 1.5;
			Prnt(Name + " " + biomes.get(index).trap3);
		}
		int dmg = (int)(calculateDamage(25,(roll(3,5) + Level)*difficulty,Defense) * trapmult);
		if(dmg < 2) {
			dmg = 2;
		}
		dmg = randomize(dmg);
		HP -= (int)(dmg);
		vibrate(frame,2,(int)Math.ceil( dmg / 15.0));
		Prnt((int)(dmg) + " damage!");
		deathCheck();
		if(!gamerunning) {
			return;
		}
		if(dmg != 0)
			potion();
		gainXP(25);

	}

	public static void chest() {
		String s = "";
		int goldgain = roll(Level * 70,Level * 250);
		if(difficulty != 1) {
			goldgain *= (difficulty/1.5);
		}
		s+= Name + " finds a chest. inside they find " + goldgain + " gold";
		money += goldgain;
		s = s.trim();
		int roll = roll(1,100);
		if (25 < roll&&roll <= 50) {
			int pots = roll(3,5);
			s+=" and " + pots + " level 1 potions.";
			potions[0] += pots;
		}else if (12 < roll&&roll <= 25) {
			int type = roll(1,3);
			Weapon w = obtainWeapon(type,false);
			s+=" and the " + w.Name;
			int dura = (int)Math.ceil(roll(70,85)/ 100.0) * w.durability;
			inventory.add(w);
			durabilities.add(dura);
		}else if(roll <= 12) {
			int sec = roll (4,6);
			s+=" and " + sec * 10 + " arrows, bullets, and throwables";
			arrows += 10 * sec;
			bullets += 10 * sec;
			throwables += 10 * sec;
		}
		s = s.trim();
		Prnt(s);
	}

	public static void vista() throws IOException {
		int die = roll(1,4);
		switch (die) {
		case 1:
			Prnt(Name + " " + biomes.get(area).vista1);
			break;
		case 2:
			Prnt(Name + " " + biomes.get(area).vista2);
			break;
		case 3:
			Prnt(Name + " " + biomes.get(area).vista3);
			break;
		case 4:
			Prnt(Name + " " + biomes.get(area).vista4);
			break;
		}
		Prnt(Name + " is feeling inspired!");
		gainXP(150);
	}

	public static void forge() {
		Prnt("You come across an old man at a forge. He offers to reforge you weapon for " + 500*difficulty + " gold. Reforge your weapon? Type \"Yes\" for yes and \"No\" for no.");
		boolean inp = false;
		while(!inp) {
			try {
				String input = (inputter());
				switch(input.toLowerCase()) {
				case"y":
				case "yes":
					inp = true;
					if(money >= 500*difficulty) {
						if(inventory.size()>0) {
							Prnt("You pay for a reforge.");
							money -= 500*difficulty;
							int dura = (int)Math.ceil(roll(85,100)/ 100.0) * inventory.get(0).durability;
							durabilities.set(0, dura);
						} else {
							Prnt("You have no weapon to reforge!");
						}
					}else {
						Prnt("You don't have enough money!");
						vibrate(frame,2,4);
					}
					break;
				case"n":
				case "no":
					inp = true;
					Prnt("You don't reforge your weapon.");
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
			}catch(Exception e) {
				Prnt("Error processing input. Please try again.");
				vibrate(frame,1,5);
				inputter();
				inp = false;
			}
		}

	}

	public static void LegVendor() {
		Prnt(Name + " finds a man holding a large bag. He becksons to you. \"Hey, kid. Wanna buy a powerful weapon?\"");
		Prnt("Buy the weapon? Type \"Yes\" to buy and \"No\" to leave. ("+5000*difficulty+" gold).");
		boolean inp = false;
		while(!inp) {
			String confirm = inputter();
			switch (confirm.toLowerCase()) {
			case"y":
			case "yes":
				inp = true;
				if(money >= 5000*difficulty){
					Prnt("You pay the money. The man reaches into his bag...");
					money -= 5000*difficulty;
					if(roll(1,12) == 1) {
						Prnt("...and pulls out a bunch of rocks!");
					} else {
						Weapon w = obtainWeapon(roll(1,3),true);
						Prnt("... and pulls out the " + w.Name);
						inventory.add(w);
						durabilities.add(w.durability);
					}
				}else {
					Prnt("You don't have enough money!");
					vibrate(frame,2,4);
				}
				break;
			case"n":
			case "no":
				inp = true;
				Prnt("You leave the man standing.");
				break;
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
	}

	public static void priest() throws IOException {
		Prnt(Name + " meets a shady looking priest. He is selling XP\n\"Need to level up? I got you covered. All I'm gonna need is " + ((XPThreshold - XP) * difficulty / 3) + " gold.\"\nGive him the money? Type \"Yes\" for yes and \"No\" for no.");
		boolean inp = false;
		while(!inp) {
			String confirm = inputter();
			switch (confirm.toLowerCase()) {
			case"y":
			case "yes":
				inp = true;
				if(money >= (XPThreshold - XP) * difficulty / 3){
					Prnt("You pay the money. You feel your power increase.");
					money -= (XPThreshold - XP) * difficulty / 3;
					XP = XPThreshold;
					checkForLevelUp();
				}else {
					Prnt("You don't have enough money!");
					vibrate(frame,2,4);
				}
				break;
			case"n":
			case "no":
				inp = true;
				Prnt("You don't pay the priest.");
				break;
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
	}

	public static void lady() {
		Prnt("You find a lady frantically looking around for something. Upon seeing you, she runs towards you.");
		int roll = roll(0,5);
		Prnt("\"Excuse me, can you please spare " + demands[roll] + "? I really need it and I'll pay good money for that.\"\nGive the lady the items? Type \"Yes\" for yes and \"No\" for no.");
		switch(roll) {
		case 0:
			Prnt("You have " + potions[0] + " left.");
			break;
		case 1:
			Prnt("You have " + potions[1] + " left.");
			break;
		case 2:
			Prnt("You have " + potions[2] + " left.");
			break;
		case 3:
			Prnt("You have " + cures + " left.");
			break;
		case 4:
			Prnt("You have " + crystals + " left.");
			break;
		case 5:
			Prnt("You have " + maps + " left.");
			break;
		}
		boolean inp = false;
		while(!inp) {
			try {
				//100 level 1 healing items", "20 level 2 healing items", "3 level 3 healing items", "5 cures", "1 crystal", "5 maps
				String input = (inputter());
				switch(input.toLowerCase()) {
				case"y":
				case "yes":
					inp = true;
					boolean give = false;
					switch (roll) {
					case 0:
						if(potions[0] > 99) {
							Prnt("You hand over the items");
							potions[0] -= 100;
							give = true;
						}else {
							vibrate(frame,2,4);
							Prnt("You don't have enough!");
						}
						break;
					case 1:
						if(potions[1] > 19) {
							Prnt("You hand over the items");
							potions[1] -= 20;
							give = true;
						}else {
							vibrate(frame,2,4);
							Prnt("You don't have enough!");
						}
						break;
					case 2:
						if(potions[2] > 2) {
							Prnt("You hand over the items");
							potions[2] -= 3;
							give = true;
						}else {
							vibrate(frame,2,4);
							Prnt("You don't have enough!");
						}
						break;
					case 3:
						if(cures > 4) {
							Prnt("You hand over the items");
							cures -= 5;
							give = true;
						}else {
							vibrate(frame,2,4);
							Prnt("You don't have enough!");
						}
						break;
					case 4:
						if(crystals > 0) {
							Prnt("You hand over the items");
							crystals -= 1;
							give = true;
						}else {
							vibrate(frame,2,4);
							Prnt("You don't have enough!");
						}
						break;
					case 5:
						if(maps > 4) {
							Prnt("You hand over the items");
							maps -= 5;
							give = true;
						}else {
							vibrate(frame,2,4);
							Prnt("You don't have enough!");
						}
						break;
					}
					if(give) {
						int runchance = roll(1,8);
						if(runchance == 1) {
							vibrate(frame,2,4);
							Prnt("The lady takes off with your items!");
						}else {
							Prnt("The lady thanks you and hands over 10,000 gold.");
							money += 10000;
						}
					}
					break;
				case"n":
				case "no":
					inp = true;
					Prnt("You don't give the lady your stuff. She says she can get it cheaper somewhere else anyway.");
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
			}catch(Exception e) {
				Prnt("Error processing input. Please try again.");
				vibrate(frame,1,5);
				inputter();
				inp = false;
			}
		}
	}

	public static void gambler() {
		Prnt("You find a man who asks you if you want to play a game. Play the game? Type \"yes\" for yes and \"no\" for no.");
		boolean inp = false;
		while(!inp) {
			try {
				String input = (inputter());
				switch(input.toLowerCase()) {
				case"y":
				case "yes":
					inp = true;
					int gamblein = roll(0,5);
					Prnt("The man says, \"If you give me " + inputs[gamblein] + ", I'll give you a surprise.\"");
					Prnt("Give him " + inputs[gamblein] + "? Press \"Yes\" for yes and \"No\" for no.");
					switch(gamblein) {
					case 0:
						Prnt("You have " + potions[0] + " left.");
						break;
					case 1:
						Prnt("You have " + potions[1] + " left.");
						break;
					case 2:
						Prnt("You have " + potions[2] + " left.");
						break;
					case 3:
						Prnt("You have " + maps + " left.");
						break;
					case 4:
						Prnt("You have " + cures + " left.");
						break;
					case 5:
						Prnt("You have " + crystals + " left.");
						break;
					}
					String confirm = (inputter()).toLowerCase();
					if(confirm.equals("yes")||confirm.equals("y")) {
						int gamb = 0;
						switch (gamblein) {
						case 0:
							if (potions[0] > 0) {
								potions[0] -= 1;
								Prnt("You give the man one level one healing item.");
								gamb = 1;
							}else {
								vibrate(frame,2,4);
								Prnt("You have none!");
								gamb = 0;
							}
							break;
						case 1:
							if (potions[1] > 0) {
								potions[1] -= 1;
								Prnt("You give the man one level two healing item.");
								gamb = 1;
							}else {
								vibrate(frame,2,4);
								Prnt("You have none!");
								gamb = 0;
							}
							break;
						case 2:
							if (potions[2] > 0) {
								potions[2] -= 1;
								Prnt("You give the man one level two healing item.");
								gamb = 1;
							}else {
								vibrate(frame,2,4);
								Prnt("You have none!");
								gamb = 0;
							}
							break;    
						case 3:
							if (maps > 0) {
								maps -= 1;
								Prnt("You give the man one map.");
								gamb = 1;
							}else {
								vibrate(frame,2,4);
								Prnt("You have none!");
								gamb = 0;
							}
							break;  
						case 4:
							if (cures > 0) {
								cures -= 1;
								Prnt("You give the man one cure.");
								gamb = 1;
							}else {
								vibrate(frame,2,4);
								Prnt("You have none!");
								gamb = 0;
							}
							break;
						case 5:
							if (crystals > 0) {
								crystals -= 1;
								Prnt("You give the man one crystal.");
								gamb = 1;
							}else {
								Prnt("You have none!");
								vibrate(frame,2,4);
								gamb = 0;
							}
							break;
						}
						if (gamb == 1) {
							int gambleout = roll(0,10);
							String s = "";
							Weapon w = obtainWeapon(roll(1,3),false);
							if (gambleout == 10) {
								s = w.Name;
							}
							Prnt("The man gives you " + outputs[gambleout] + s + " in return.");
							switch (gambleout) {
							case 0:
								potions[0] += 1;
								break;
							case 1:
								potions[1] += 1;
							case 2:
								potions[2] += 1;
								break;
							case 3:
								maps += 1;
								break;
							case 4:
								cures += 1;
								break;
							case 5:
								crystals += 1;
								break;
							case 6:
								money += 1000;
								break;
							case 7:
								bullets += 50;
								break;
							case 8:
								arrows += 50;
								break;
							case 9:
								throwables += 50;
								break;
							case 10:
								inventory.add(w);
								durabilities.add(w.durability);
								break;
							}
						}
					}else {
						Prnt("You don't give the man anything.");
					}
					break;
				case"n":
				case "no":
					inp = true;
					Prnt("You don't play.");
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
			}catch(Exception e) {
				Prnt("Error processing input. Please try again.");
				vibrate(frame,1,5);
				inputter();
				inp = false;
			}
		}
	}

	public static void oldMan() {
		Prnt("You see an old man sitting against a wall. Talk to him? Type \"Yes\" for yes and \"No\" for no.");
		boolean inp = false;
		while(!inp) {
			try {
				String input = (inputter());
				switch(input.toLowerCase()) {
				case"y":
				case "yes":
					inp = true;
					int kill = roll (1,13 + (Luck/5));
					if(kill == 1) {
						Prnt("The old man stands up and plunges a flaming greatsword into your chest.");
						Prnt(MaxHP + " damage!");
						vibrate(frame,2,4);
						HP = 0;
						deathCheck();
						if(!gamerunning) {
							return;
						}
					}else {
						Prnt("You have a friendly conversation with the man. He even gives you some healing supplies!");
						potions[0] += roll(13,17);
						potions[1] += roll(4,8);
						potions[2] += roll(0,3);
						cures += 1;
					}
					break;
				case"n":
				case "no":
					inp = true;
					Prnt("You back away slowly.");
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
			}catch(Exception e) {
				Prnt("Error processing input. Please try again.");
				vibrate(frame,1,5);
				inputter();
				inp = false;
			}
		}
	}

	public static void gainXP(int xp) throws IOException {
		double dxp = xp * (1 + (Attack/25.0));
		xp = (int)(dxp);
		Prnt(Name + " gained " + xp + " XP!");
		XP += xp;
		XPtoNext = XPThreshold - XP;
		checkForLevelUp();
	}

	public static void trainer() throws IOException {
		Prnt(Name + " encounters a man standing in the middle of the path. He says he can train you, for a price.");
		int trainingType = roll(1,5);
		String s = "Train ";
		switch(trainingType) {
		case 1:
			s += "attack?";
			break;
		case 2:
			s += "defense?";
			break;
		case 3:
			s += "speed?";
			break;
		case 4:
			s += "intelligence?";
			break;
		case 5:
			s += "vitality?";
			break;
		}
		s += " (" + 250 * difficulty + " gold) Type \"Yes\" for yes and \"No\" for no.";
		Prnt(s);
		boolean inp = false;
		while(!inp) {
			s = inputter().toLowerCase();
			switch(s) {
			case"y":
			case "yes":
				inp = true;
				if(money >= 250*difficulty) {
					money -= 250*difficulty;
					switch(trainingType) {
					case 1:
						Attack++;
						Prnt("Attack increased by 1!");
						break;
					case 2:
						Defense++;
						Prnt("Defense increased by 1!");
						break;
					case 3:
						Speed++;
						Prnt("Speed increased by 1!");
						break;
					case 4:
						Intelligence++;
						Prnt("Intelligence increased by 1!");
						break;
					case 5:
						Vitality++;
						HP += 5;
						Prnt("Vitality increased by 1!");
						break;
					}
				} else {
					Prnt("You don't have enough money!");
					vibrate(frame,2,4);
				}
				break;
			case"n":
			case "no":
				inp = true;
				Prnt("You tell the man you don't need training.");
				break;
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
	}

	public static void slab() throws IOException {
		String s = "";
		int words = roll(6,19);
		for( int i = 0; i<words;i++) {
			int length = roll(2, 8);
			for(int f = 0; f<length; f++) {
				s += (char)(roll(340, 778));  
			}
			s += " ";
		}
		Prnt(Name + " finds an ancient slab sitting in the ground. It reads: \"" + s.trim() + "\".");
		Prnt("Try to decipher the text? Type \"Yes\" for yes and \"No\" for no.");
		boolean inp = false;
		while(!inp) {
			String input = (inputter());
			switch(input.toLowerCase()) {
			case"y":
			case "yes":
				if(roll (1, 100) < 35 + Intelligence) {
					Prnt("You barely manage to make out what is being said.");
					switch(roll(1,3)) {
					case 1:
						Prnt("You learn of new combat and survival techniques!");
						gainXP(1500);
						checkForLevelUp();
						break;
					case 2:
						Prnt("The slab points out directions to a nearby chest of valuables!");
						s = "Inside the chest is ";
						switch(roll(1,5)) {
						case 1:
							int gold = roll(500,1000);
							if(difficulty != 1) {
								gold *= (difficulty/1.5);
							}
							money += gold;
							s += gold + " gold!";
							break;
						case 2:
							Weapon w = obtainWeapon(roll(1,3), false);
							s += "the " + w.Name + "!";
							inventory.add(w);
							durabilities.add(w.durability);
							break;
						case 3:
							int pot = roll(2,6);
							int potType = roll(1,3);
							s += pot + " level " + potType + " " + potionType[potTypeIndex] + "s!";
							potions[potType-1] += pot;
							break;
						case 4:
							int map = roll(2,5);
							maps += map;
							s += map + " maps!";
							break;
						case 5:
							crystals++;
							s += "a crystal!";
							break;
						}
						Prnt(s);
						break;
					case 3:
						switch(roll(1,5)) {
						case 1:
							Prnt ("The slab contains instructions on improving strength and combat prowess!\nAttack increased by 5!");
							Attack += 5;
							break;
						case 2:
							Prnt ("The slab contains defensive strategies and tips!\nDefense increased by 5!");
							Defense += 5;
							break;
						case 3:
							Prnt ("The slab contains an encantation that makes the user extremely swift!\nSpeed increased by 5!");
							Speed += 5;
							break;
						case 4:
							Prnt ("The slab contains the knowledge of many generations past!\nIntelligence increased by 5!");
							Intelligence += 5;
							break;
						case 5:
							Prnt ("The slab contains a wealth of information about improving healthiness!\nVitality increased by 5!");
							Vitality += 5;
							HP += 25;
							break;
						}
					}

					inp = true;
				}else {
					Prnt("You have no idea where to even begin. The slab remains cryptic.");
				}
				inp = true;
				break;
			case"n":
			case "no":
				Prnt("You don't even try to decode the slab.");
				inp = true;
				break;
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
	}

	public static void moneyMan() throws IOException {
		Prnt("You find a man who asks you if you want to play a game. Play the game? Type \"yes\" for yes and \"no\" for no.");
		boolean inp = false;
		while(!inp) {
			try {
				String input = (inputter());
				switch(input.toLowerCase()) {
				case "y":
				case "yes":
					inp = true;
					int gold = roll(750, 1000) * difficulty;
					Prnt("The man says, \"If you give me " + gold + " gold, I'll give you a surprise.\"");
					Prnt("Give him " + gold + " gold? Press \"Yes\" for yes and \"No\" for no.");
					String confirm = (inputter()).toLowerCase();
					switch(confirm) {
					case"y":
					case "yes":
						if(money > gold) {
							money -= gold;
							if(Luck >= 25) {
								gold *= 3;
							} else {
								int i = roll(Luck/5 + 1,5);
								switch(i) {
								case 1:
									gold = (int)(gold * 0.25);
									break;
								case 2:
									gold = (int)(gold * 0.5);
									break;
								case 3:
									gold = (int)(gold * 1);
									break;
								case 4:
									gold = (int)(gold * 2);
									break;
								case 5:
									gold = (int)(gold * 3);
									break;
								}
							}
							Prnt(Name + " reveives " + gold + " gold back.");
							money += gold;
						} else {
							Prnt("You don't have enough money!");
							vibrate(frame, 2, 4);
						}
						break;
					case "n":
					case "no":
						Prnt("You don't hand over any money.");
						break;
					default:
						Prnt("Invalid input!");
						vibrate(frame,1,3);
					}
					break;
				case"n":
				case "no":
					inp = true;
					Prnt("You don't play.");
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
			}catch(Exception e) {
				Prnt("Error processing input. Please try again.");
				vibrate(frame,1,5);
				inputter();
				inp = false;
			}
		}
	}

	public static void altar() throws IOException {
		Prnt(Name + " finds an ancient altar in the ground. It seems to have some kind of slot for a crystal. \nUse a crystal? Type \"Yes\" for yes and \"No\" for no. (" + crystals + " remaining)");
		boolean inp = false;
		while(!inp) {
			try {
				String input = (inputter());
				switch(input.toLowerCase()) {
				case"y":
				case "yes":
					if(crystals > 0) {
						Prnt("You insert the crystal into it's slot. It clicks in place, and a wormhole opens in front of you.");
						finale();
						if(!gamerunning) {
							return;
						}
						inp = true;
					}else {
						Prnt("You have no crystals to use!");
					}
					inp = true;
					break;
				case"n":
				case "no":
					Prnt("You back away from the altar slowly");
					inp = true;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
			}catch(Exception e) {
				Prnt("Error processing input. Please try again.");
				vibrate(frame,1,5);
				inputter();
				inp = false;
			}
		}
	}

	public static void finale() throws IOException {
		enemypsn = false;
		enemybrn = false;
		enemyfrz = false;
		Prnt(Name + " is sucked into the wormhole!");
		int pots = roll(22,37);
		Prnt(Name + " finds " + pots + " level 2 " + potionType[potTypeIndex] + "s");
		potions[1] += pots;
		int currenten = roll(0,3);
		Enemy current = finalbosses.get(currenten);
		FBinit(current);
		Prnt(finales1[currenten]);
		Prnt(Opponent + " attacks! Brace yourself.");
		while(OpponentHealth > 0) {
			combat();
			if(!gamerunning) {
				return;
			}
		}
		if(HP > 0 && OpponentHealth < 0) {
			try {
				vibrate(frame,2,5);
				Thread.sleep(500);
				vibrate(frame,2,7);
				Thread.sleep(500);
				vibrate(frame,2,10);
			}catch (Exception e) {
				e.printStackTrace();
			}
			satanDead = true;
			Prnt(finales2[currenten]);
			Prnt("Victory!");
			try {
				Prnt("Final stats: \nName: " + Name + "\nCurrent weapon: " + inventory.get(0).Name + "\nLevel " + Level + "\n" + money + " gold\nPotions drank: " + PotionsConsumed);
			} catch (Exception e) {
				Prnt("Final stats: \nName: " + Name + "\nCurrent weapon: none\nLevel " + Level + "\n" + money + " gold\nPotions drank: " + PotionsConsumed);
			}
			Prnt("Press enter to continue.");
			String s = inputter();
			Prnt("\n\nEnemies killed:");
			for(int i = 0; i < enemies.size(); i++) {
				if (killedEnemies.containsKey(enemies.get(i).Name)) {    
					Prnt(enemies.get(i).Name + " - " + killedEnemies.get(enemies.get(i).Name) + " killed");
				}
			}
			Prnt("\nCreated by Tyler Burgess");
			Prnt("\n\n\nThank you for playing Adventure of Ages. \n\n");
			AchievementCheck(false);
			startPrompt();
			if(!gamerunning) {
				return;
			}
		}
	}

	public static Weapon obtainWeapon(int type, boolean legendary) {
		switch(type) {
		case 1:
			if(roll(1,100) <= 2 + Luck/5 || legendary) {
				int roll = roll(0,ml.size()-1);
				return (ml.get(roll));
			}
			int roll = roll(0,m.size()-1);
			return m.get(roll);
		case 2:
			if(roll(1,100) <= 2 + Luck/5 || legendary) {
				roll = roll(0,rl.size()-1);
				switch(rl.get(roll).WeaponType) {
				case 4:
					arrows += 20;
					break;
				case 5:
					bullets += 35;
					break;
				case 6:
					throwables += 20;
					break;
				}
				return (rl.get(roll));
			}
			roll = roll(0,r.size()-1);
			switch(r.get(roll).WeaponType) {
			case 4:
				arrows += 20;
				break;
			case 5:
				bullets += 35;
				break;
			case 6:
				throwables += 20;
				break;
			}
			return r.get(roll);
		case 3:
			if(roll(1,100) <= 2 + Luck/5 || legendary) {
				roll = roll(0,hl.size()-1);
				return (hl.get(roll));
			}
			roll = roll(0,h.size()-1);
			return h.get(roll);
		}
		return Weapons.get(0);
	}

	public static void Prnt(String s) {
		if(gamerunning) {
			String s1 = Name + "     Level " + Level + "     " + HP + "/" + MaxHP + " HP     " + Mana + "/" + MaxMana + " mana     " + money + " gold";
			if (!(s1.equals(textField.getText())))
				textField.setText(Name + "     Level " + Level + "     " + HP + "/" + MaxHP + " HP     " + Mana + "/" + MaxMana + " mana     " + arrows + " arrows     " + bullets + " bullets     " + throwables + " throwables     " + money + " gold");
		} else {
			textField.setText("Adventure of Ages");
		}
		int i;
		jta.setCaretPosition(jta.getDocument().getLength());
		for(i = 0; i < s.length(); i++){
			jta.append(String.format("%c", s.charAt(i)));
			String soundName = "media/soundblip.wav";    
			AudioInputStream audioInputStream;
			if( i%7 ==0 ) {
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					float dB = (float) (Math.log(audioSetting) / Math.log(10.0) * 20.0);
					gainControl.setValue(dB);
					clip.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try{
				Thread.sleep(textSpeed);//0.5s pause between characters
			}catch(InterruptedException ex){
				Thread.currentThread().interrupt();
			}
		}
		jta.append("\n");

	}

	public static String inputter() {
		while(!checkinput()) {
			jta2.requestFocus();
			jta.append("");
		}
		String s = jta2.getText().split("\n")[0];
		jta.append(s+"\n");
		jta2.setText(null);
		feedstring = "";
		return s.trim();
	}

	public static boolean checkinput() {
		if(!feedstring.equals(""))
			return true;
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		feedstring = jta2.getText();
	}

	public static void loadState() throws IOException {
		boolean faultyinput = true;
		boolean error = false;
		while(faultyinput) {
			try {
				Prnt("Which slot do you want to load?");
				int load = Integer.parseInt(inputter());
				ArrayList<Object> vals = new ArrayList<Object>();
				switch(load) {
				case 1:
					String s = file.load(1);
					try {
						vals = interpret(s);
					} catch(Exception e) {
						Prnt("An error occurred while loading.");
						vibrate(frame,1,5);
						Prnt(e.toString());
						error = true;
						break;
					}
					faultyinput = false;
					Adventure(vals);
					if(!gamerunning) {
						return;
					}
					break;
				case 2:
					s = file.load(2);
					try {
						vals = interpret(s);
					} catch(Exception e) {
						Prnt("An error occurred while loading.");
						vibrate(frame,1,5);
						error = true;
						break;
					}
					faultyinput = false;
					Adventure(vals);
					if(!gamerunning) {
						return;
					}
					break;
				case 3:
					s = file.load(3);
					try {
						vals = interpret(s);
					} catch(Exception e) {
						Prnt("An error occurred while loading.");
						vibrate(frame,1,5);
						error = true;
						break;
					}
					faultyinput = false;
					Adventure(vals);
					if(!gamerunning) {
						return;
					}
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
				Adventure(vals);
				if(!gamerunning) {
					return;
				}
			} catch(Exception e) {
				if(!error) {
					Prnt("Invalid input!");
					vibrate(frame,1,3);

				}
				error = false;
			}
		}
	}

	public static ArrayList<Object> interpret(String s) {
		ArrayList<Object> s2 = new ArrayList<Object>();
		String[] splitted = s.split(";");
		String n = splitted[0];
		int a = Integer.parseInt(splitted[1]);
		int d = Integer.parseInt(splitted[2]);
		int sp = Integer.parseInt(splitted[3]);
		int i = Integer.parseInt(splitted[4]);
		int v = Integer.parseInt(splitted[5]);
		int g = Integer.parseInt(splitted[6]);
		int m = Integer.parseInt(splitted[7]);
		int c = Integer.parseInt(splitted[8]);
		int b = Integer.parseInt(splitted[9]);
		int ar = Integer.parseInt(splitted[10]);
		int t = Integer.parseInt(splitted[11]);
		int cr = Integer.parseInt(splitted[12]);
		int ds = Integer.parseInt(splitted[13]);
		int dst = Integer.parseInt(splitted[14]);
		int in = Integer.parseInt(splitted[15]);
		ArrayList<Integer> w = new ArrayList <Integer> ();
		ArrayList<Integer> du = new ArrayList <Integer> ();
		int offset = 16;
		for(int f = 0; f < in * 2; f+=2) {
			w.add(Integer.parseInt(splitted[16+f]));
			du.add(Integer.parseInt(splitted[17+f]));
			offset += 2;
		}
		int ap = Integer.parseInt(splitted[offset]);
		offset++;
		int en = Integer.parseInt(splitted[offset]);
		offset++;
		ArrayList<String> e = new ArrayList <String> ();
		ArrayList<Integer> k = new ArrayList <Integer> ();
		for(int f = 0; f < en; f++) {
			e.add((splitted[offset]));
			k.add(Integer.parseInt(splitted[offset+1]));
			offset += 2;
		}
		int bo = Integer.parseInt(splitted[offset]);
		offset++;
		ArrayList<Integer> kb = new ArrayList <Integer> ();
		for(int f = 0; f < bo; f++) {
			kb.add(Integer.parseInt(splitted[offset]));
			offset ++;
		}
		int p = Integer.parseInt(splitted[offset]);
		offset++;
		int mp = Integer.parseInt(splitted[offset]);
		offset++;
		int p1 = Integer.parseInt(splitted[offset]);
		offset++;
		int p2 = Integer.parseInt(splitted[offset]);
		offset++;
		int p3 = Integer.parseInt(splitted[offset]);
		offset++;
		int pt = Integer.parseInt(splitted[offset]);
		offset++;
		int l = Integer.parseInt(splitted[offset]);
		offset++;
		int x = Integer.parseInt(splitted[offset]);
		offset++;
		int xn = Integer.parseInt(splitted[offset]);
		offset++;
		int xt = Integer.parseInt(splitted[offset]);
		offset++;
		int h = Integer.parseInt(splitted[offset]);
		offset++;
		int mn = Integer.parseInt(splitted[offset]);
		offset++;
		int are = Integer.parseInt(splitted[offset]);
		offset++;
		int arp = Integer.parseInt(splitted[offset]);
		offset++;
		int ars = Integer.parseInt(splitted[offset]);
		offset++;
		int mm = Integer.parseInt(splitted[offset]);
		int[] potion = {p1, p2, p3};
		offset++;
		int kil0 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil1 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil2 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil3 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil4 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil5 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil6 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil7 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil8 = Integer.parseInt(splitted[offset]);
		offset++;
		int kil9 = Integer.parseInt(splitted[offset]);
		offset++;
		int[] kil = {kil0,kil1,kil2,kil3,kil4,kil5,kil6,kil7,kil8,kil9};
		int dif = Integer.parseInt(splitted[offset]);
		int lk = Integer.parseInt(splitted[offset]);
		offset++;
		s2.add(n);
		s2.add(a);
		s2.add(d);
		s2.add(sp);
		s2.add(i);
		s2.add(v);
		s2.add(g);
		s2.add(m);
		s2.add(c);
		s2.add(b);
		s2.add(ar);
		s2.add(t);
		s2.add(cr);
		s2.add(ds);
		s2.add(dst);
		s2.add(w);
		s2.add(du);
		s2.add(ap);
		s2.add(e);
		s2.add(k);
		s2.add(kb);
		s2.add(p);
		s2.add(mp);
		s2.add(potion);
		s2.add(pt);
		s2.add(l);
		s2.add(x);
		s2.add(xn);
		s2.add(xt);
		s2.add(h);
		s2.add(mn);
		s2.add(are);
		s2.add(arp);
		s2.add(ars);
		s2.add(mm);
		s2.add(true);
		s2.add(kil);
		s2.add(dif);
		s2.add(lk);
		return s2;
	}

	public static void save() throws IOException {
		String s = "";

		s+= Name + ";";
		s+= Attack + ";";
		s+= Defense + ";";
		s+= Speed + ";";
		s+= Intelligence + ";";
		s+= Vitality + ";";
		s+= money + ";";
		s+= maps + ";";
		s+= cures + ";";
		s+= bullets + ";";
		s+= arrows + ";";
		s+= throwables + ";";
		s+= crystals + ";";
		s+= diseaseLevel + ";";
		s+= diseaseTime + ";";
		s+= inventory.size() + ";";
		for(int i = 0; i < inventory.size(); i++) {
			s+= Weapons.indexOf(inventory.get(i)) + ";";
			s+= durabilities.get(i) + ";";
		}
		s+= MaxAP + ";";
		s += killedEnemies.size() + ";";
		String[] firstKey = (String[]) killedEnemies.keySet().toArray(new String[killedEnemies.keySet().size()]);
		for (int i = 0; i< killedEnemies.size(); i++) {
			s+= firstKey[i] + ";";
			s+= killedEnemies.get(firstKey[i]) + ";";
		}
		s += bossesKilled.size()+ ";";
		for(Enemy b:bossesKilled) {
			s += bosses.indexOf(b) + ";";
		}
		s+= PotionsConsumed + ";";
		s+= manaPotions + ";";
		s+= potions[0] + ";";
		s+= potions[1] + ";";
		s+= potions[2] + ";";
		s+= potTypeIndex + ";";
		s+= Level + ";";
		s+= XP + ";";
		s+= XPtoNext + ";";
		s+= XPThreshold + ";";
		s+= HP + ";";
		s+= Mana + ";";
		s+= area + ";";
		s+= areaProgress + ";";
		s+= areaSize + ";";
		s+= mapMod + ";";
		s+= numkilled[0] + ";";
		s+= numkilled[1] + ";";
		s+= numkilled[2] + ";";
		s+= numkilled[3] + ";";
		s+= numkilled[4] + ";";
		s+= numkilled[5] + ";";
		s+= numkilled[6] + ";";
		s+= numkilled[7] + ";";
		s+= numkilled[8] + ";";
		s+= numkilled[9] + ";";
		s+= difficulty + ";";
		s+= Luck + ";";

		boolean faultyinput = true;
		while(faultyinput) {
			Prnt("Which slot do you want to save to?");
			try {
				int load = Integer.parseInt(inputter());
				switch(load) {
				case 1:
					file.save(1,s);
					faultyinput = false;
					break;
				case 2:
					file.save(2,s);
					faultyinput = false;
					break;
				case 3:
					file.save(3,s);
					faultyinput = false;
					break;
				default:
					Prnt("Invalid input!");
					vibrate(frame,1,3);
				}
				Prnt("File saved!");
			} catch (Exception e) {
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
	}

	public static void LoadEnemies() throws FileNotFoundException {
		FileThing f = new FileThing();
		File enemiesF = new File("data/enemies.dat");
		Scanner en = new Scanner(enemiesF, "utf-8");
		while(en.hasNextLine()) {
			String s = f.decrpyt(en.nextLine());
			String[] enemyArray = s.split(";");
			String eName = enemyArray[0];
			int eAtk = Integer.parseInt(enemyArray[1]);
			int eDef = Integer.parseInt(enemyArray[2]);
			int eSpd = Integer.parseInt(enemyArray[3]);
			int eInt = Integer.parseInt(enemyArray[4]);
			int eVit = Integer.parseInt(enemyArray[5]);
			int eWlt = Integer.parseInt(enemyArray[6]);
			int attacksSize = Integer.parseInt(enemyArray[7]);
			Attack[] attacks = new Attack[attacksSize];
			int count = 0;
			for (int i = 8; i < enemyArray.length; i+=2) {
				String nameOfAttack = enemyArray[i];
				int attackDamage = Integer.parseInt(enemyArray[i+1]);
				attacks[count] = new Attack(nameOfAttack, attackDamage);
				count++;
			}
			enemies.add(new Enemy(eName,eAtk,eDef,eSpd,eInt,eVit,eWlt,attacks));
		}
		File biomesF = new File("data/biomes.dat");
		Scanner bi = new Scanner(biomesF, "utf-8");
		while(bi.hasNext()) {
			String s = f.decrpyt(bi.nextLine());
			String[] biomeArray = s.split(";");
			String intr = biomeArray[0];
			String cont = biomeArray[1];
			String exit = biomeArray[2];
			String tra1 = biomeArray[3];
			String tra2 = biomeArray[4];
			String tra3 = biomeArray[5];
			String vis1 = biomeArray[6];
			String vis2 = biomeArray[7];
			String vis3 = biomeArray[8];
			String vis4 = biomeArray[9];
			int indx = Integer.parseInt(biomeArray[10]);
			biomes.add(new Biome(intr,cont,exit,tra1,tra2,tra3,vis1,vis2,vis3,vis4,indx));
		}
		File bossesF = new File("data/bosses.dat");
		Scanner bo = new Scanner(bossesF, "utf-8");
		while(bo.hasNext()) {
			String s = f.decrpyt(bo.nextLine());
			String[] enemyArray = s.split(";");
			String eName = enemyArray[0];
			int eAtk = Integer.parseInt(enemyArray[1]);
			int eDef = Integer.parseInt(enemyArray[2]);
			int eSpd = Integer.parseInt(enemyArray[3]);
			int eInt = Integer.parseInt(enemyArray[4]);
			int eVit = Integer.parseInt(enemyArray[5]);
			int eWlt = Integer.parseInt(enemyArray[6]);
			int attacksSize = Integer.parseInt(enemyArray[7]);
			Attack[] attacks = new Attack[attacksSize];
			int count = 0;
			for (int i = 8; i < enemyArray.length; i+=2) {
				String nameOfAttack = enemyArray[i];
				int attackDamage = Integer.parseInt(enemyArray[i+1]);
				attacks[count] = new Attack(nameOfAttack, attackDamage);
				count++;
			}
			bosses.add(new Enemy(eName,eAtk,eDef,eSpd,eInt,eVit,eWlt,attacks));
		}
		File fbossesF = new File("data/fbosses.dat");
		Scanner fbo = new Scanner(fbossesF, "utf-8");
		while(fbo.hasNext()) {
			String s = f.decrpyt(fbo.nextLine());
			String[] enemyArray = s.split(";");
			String eName = enemyArray[0];
			int eAtk = Integer.parseInt(enemyArray[1]);
			int eDef = Integer.parseInt(enemyArray[2]);
			int eSpd = Integer.parseInt(enemyArray[3]);
			int eInt = Integer.parseInt(enemyArray[4]);
			int eVit = Integer.parseInt(enemyArray[5]);
			int eWlt = Integer.parseInt(enemyArray[6]);
			int attacksSize = Integer.parseInt(enemyArray[7]);
			Attack[] attacks = new Attack[attacksSize];
			int count = 0;
			for (int i = 8; i < enemyArray.length; i+=2) {
				String nameOfAttack = enemyArray[i];
				int attackDamage = Integer.parseInt(enemyArray[i+1]);
				attacks[count] = new Attack(nameOfAttack, attackDamage);
				count++;
			}
			finalbosses.add(new Enemy(eName,eAtk,eDef,eSpd,eInt,eVit,eWlt,attacks));
		}
		File weaponsF = new File("data/weapons.dat");
		Scanner weap = new Scanner(weaponsF, "utf-8");
		while(weap.hasNext()) {
			String s = f.decrpyt(weap.nextLine());
			String[] weapArray = s.split(";");
			String wname = weapArray[0];
			int dmg = Integer.parseInt(weapArray[1]);
			int ap = Integer.parseInt(weapArray[2]);
			int mana = Integer.parseInt(weapArray[3]);
			int hlng = Integer.parseInt(weapArray[4]);
			int nhits = Integer.parseInt(weapArray[5]);
			int aff = Integer.parseInt(weapArray[6]);
			int type = Integer.parseInt(weapArray[7]);
			int hc = Integer.parseInt(weapArray[8]);
			boolean leg = Boolean.parseBoolean(weapArray[9]);
			int ammocost = Integer.parseInt(weapArray[10]);
			int durability = Integer.parseInt(weapArray[11]);
			Weapons.add(new Weapon(wname,dmg,ap,mana,hlng,nhits,aff,type,hc,leg,ammocost,durability));
			int gold = (int)(dmg * nhits * (hc/100.0));
		}
		Weapons.remove(0);
		for(Weapon w:Weapons) {
			boolean b = true;
			if((w.Name.toLowerCase().equals("coingun")&&!un14)||(w.Name.toLowerCase().equals("golden sword")&&!un15)||(w.Name.toLowerCase().equals("thunderblade")&&!un16)||(w.Name.toLowerCase().equals("apocalypse flame")&&!un17)||(w.Name.toLowerCase().equals("minigun")&&!un18)||(w.Name.toLowerCase().equals("microgun")&&!un19)||(w.Name.toLowerCase().equals("slam shotgun")&&!un20)) {
				b = false;
			}
			if (b) {
				switch (w.WeaponType) {
				case 1:
					if (!w.legendary) {
						m.add(w);
					}else {
						ml.add(w);
					}
					break;
				case 2:
					if (!w.legendary) {
						m.add(w);
					}else {
						ml.add(w);
					}
					break;
				case 3:
					if (!w.legendary) {
						m.add(w);
					}else {
						ml.add(w);
					}
					break;
				case 4:
					if (!w.legendary) {
						r.add(w);
					}else {
						rl.add(w);
					}
					break;
				case 5:
					if (!w.legendary) {
						r.add(w);
					}else {
						rl.add(w);
					}
					break;
				case 6:
					if (!w.legendary) {
						r.add(w);
					}else {
						rl.add(w);
					}
					break;
				case 7:
					if (!w.legendary) {
						h.add(w);
					}else {
						ml.add(w);
					}
					break;
				case 8:
					if (!w.legendary) {
						h.add(w);
					}else {
						hl.add(w);
					}
					break;
				case 9:
					if (!w.legendary) {
						h.add(w);
					}else {
						hl.add(w);
					}
					break;
				}
			}
		}
		File achievements = new File("data/unlocks.dat");
		Scanner ac = new Scanner(achievements,"utf-8");
		int ctr = 0;
		while(ac.hasNext()) {
			ctr++;
			switch(ctr) {
			case 1:
				un1 = Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 2:
				un2 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 3:
				un3 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 4:
				un4 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 5:
				un5 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 6:
				un6 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 7:
				un7 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 8:
				un8 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 9:
				un9 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 10:
				un10 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 11:
				un11 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 12:
				un12 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 13:
				un13 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 14:
				un14 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 15:
				un15 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 16:
				un16 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 17:
				un17 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 18:
				un18 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 19:
				un19 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			case 20:
				un20 =Integer.parseInt(f.decrpyt(ac.nextLine())) != 0;
				break;
			}
		}
		enemies.remove(0);
		bosses.remove(0);
		finalbosses.remove(0);
		biomes.remove(0);
		weap.close();
		en.close();
		bo.close();
		fbo.close();
		bi.close();
		ac.close();
	}

	public static void setColors() {
		jta.setBackground(foregrounds[ColorIndex]);
		jta.setForeground(borders[ColorIndex]);
		jta.setBorder(BorderFactory.createLineBorder(borders[ColorIndex]));
		jsp.setBorder(BorderFactory.createLineBorder(borders[ColorIndex]));
		jta2.setBackground(foregrounds[ColorIndex]);
		jta2.setForeground(borders[ColorIndex]);
		jta2.setCaretColor(borders[ColorIndex]);
		jta2.setBorder(BorderFactory.createLineBorder(borders[ColorIndex]));
		jsp2.setBorder(BorderFactory.createLineBorder(borders[ColorIndex]));
		textField.setBackground(foregrounds[ColorIndex]);
		textField.setForeground(borders[ColorIndex]);
		textField.setBorder(BorderFactory.createLineBorder(borders[ColorIndex]));
		jsp3.setBorder(BorderFactory.createLineBorder(borders[ColorIndex]));
		panel.setBackground(backgrounds[ColorIndex]);
		frame.getContentPane().setBackground(backgrounds[ColorIndex]);
	}

	public static void settings() throws IOException {
		Scanner color;
		boolean b = true;
		while (b) {
			Prnt("Type \"Color Settings\" to change the display color. Type \"Change Sound Volume\" to change the game volume. Type \"Change text speed\" to change how fast text appears. Type \"Achievements\" to view your achievements. Type \"Exit\" to exit the settings menu");
			String s = inputter();
			switch(s.toLowerCase()) {
			case"c":
			case "color settings":
				boolean c = false;
				while(!c) {
					Prnt("Change to which color scheme? Available colors are: Classic, Dungeon, Forest, Volcano, Kingdom, Plague, Ice, Lovecraft, Wasteland, Desert, Prehistory, Jungle, and Graveyard.");
					String input = inputter();
					switch (input.toLowerCase()) {
					case"c":
					case "classic":
						file.color(0,audioInt,textSpeedindex);
						c = true;
						color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"d":
					case "dungeon":
						file.color(1,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"f":
					case "forest":
						file.color(2,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"v":
					case "volcano":
						file.color(3,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"k":
					case "kingdom":
						file.color(4,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"p":
					case "plague":
						file.color(5,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"i":
					case "ice":
						file.color(6,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"l":
					case "lovecraft":
						file.color(10,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"w":
					case "wasteland":
						file.color(7,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"ds":
					case "desert":
						file.color(8,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"pr":
					case "prehistory":
						file.color(9,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/colorSetting.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"m":
					case "merchant":
						file.color(11,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"h":
					case "hell":
						file.color(12,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"g":
					case "graveyard":
						file.color(13,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					case"j":
					case "jungle":
						file.color(14,audioInt,textSpeedindex);
						c = true;color = new Scanner(new File("data/Preferences.dat"));
						ColorIndex = color.nextInt();
						color.close();
						setColors();
						Prnt("Color setting changed.");
						break;
					default:
						Prnt("Invalid input!");
						vibrate(frame,1,3);
					}
				}
				break;
			case"v":
			case "change sound volume":
				c = false;
				while(!c) {
					Prnt("Type a number between 0 and 100 to set the volume. 0 mutes the game, and 100 sets the volume to max.");
					try { 
						int input = Integer.parseInt(inputter());
						audioInt = input;
						audioSetting = ((double)input) / 100;
						c = true;
					} catch (Exception e) {
						Prnt("Invalid input!");
						vibrate(frame,1,3);
					}
				}
				Prnt("Volume changed!");
				file.color(ColorIndex, audioInt, textSpeedindex);
				break;
			case"t":
			case "change text speed":
				c = false;
				while(!c) {
					Prnt("Set text speed to slow, normal, or fast?");
					String input = inputter();
					switch (input.toLowerCase()) {
					case"s":
					case "slow":
						textSpeedindex = 1;
						file.color(ColorIndex,audioInt,textSpeedindex);
						c = true;
						switch(textSpeedindex) {
						case 1:
							textSpeed = 15;
							break;
						case 2:
							textSpeed = 8;
							break;
						case 3:
							textSpeed = 3;
							break;
						}
						Prnt("Text speed changed!");
						break;
					case"n":
					case "normal":
						textSpeedindex = 2;
						file.color(ColorIndex,audioInt,textSpeedindex);
						c = true;
						switch(textSpeedindex) {
						case 1:
							textSpeed = 15;
							break;
						case 2:
							textSpeed = 8;
							break;
						case 3:
							textSpeed = 3;
							break;
						}
						Prnt("Text speed changed!");
						break;
					case"f":
					case "fast":
						textSpeedindex = 3;
						file.color(ColorIndex,audioInt,textSpeedindex);
						c = true;
						switch(textSpeedindex) {
						case 1:
							textSpeed = 15;
							break;
						case 2:
							textSpeed = 8;
							break;
						case 3:
							textSpeed = 3;
							break;
						}
						Prnt("Text speed changed!");
						break;
					default:
						Prnt("Invalid input!");
						vibrate(frame,1,3);
					}
				}
				break;
			case"a":
			case "achievements":
				achievements();
				break;
			case"e":
			case "exit":
				b = false;
				break;
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
	}

	public static void AchievementCheck(boolean doomAchieved) throws IOException {
		if(doomAchieved) {
			if(!un10) {
				Prnt("Achievement earned! \"Eternally Slaying\". Doomed Marine class unlocked!");
				un10 = true;
			}
		}
		if(satanDead) {
			if(difficulty == 2)
				if(!un1) {
					Prnt("Achievement earned! \"Hardcore\". LUDICROUS difficulty unlocked!");
					un1 = true;
				}
			if(inventory.get(0).Name.toLowerCase().equals("electric guitar"))
			if(!un2) {
				Prnt("Achievement earned! \"School of Rock\". Rockstar class unlocked!");
				un2 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("knives"))
			if(!un3) {
				Prnt("Achievement earned! \"Criminal\". Bandit class unlocked!");
				un3 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("flame jet"))
			if(!un4) {
				Prnt("Achievement earned! \"Draconic\". Dragonborn class unlocked!");
				un4 = true;
			}
			if(potions[0] + potions[1] + potions[2] >= 300)
			if(!un5) {
				Prnt("Achievement earned! \"Healing for Days\". Alchemist class unlocked!");
				un5 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("hatchets"))
			if(!un6) {
				Prnt("Achievement earned! \"Barbaric\". Berserker class unlocked!");
				un6 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("beer kegs"))
			if(!un7) {
				Prnt("Achievement earned! \"Drunken combat\". Brewer class unlocked!");
				un7 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("tommy gun"))
			if(!un8) {
				Prnt("Achievement earned! \"Gangsta'\". Mobster class unlocked!");
				un8 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("maggots"))
			if(!un9) {
				Prnt("Achievement earned! \"That's Gross!\". Insectoid class unlocked!");
				un9 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("Super Shotgun"))
			if(!un10) {
				Prnt("Achievement earned! \"Eternally Slaying\". Doomed Marine class unlocked!");
				un10 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("laser sword"))
			if(!un12) {
				Prnt("Achievement earned! \"Space Battles\". Space Wizard class unlocked!");
				un12 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("sniper rifle"))
			if(!un13) {
				Prnt("Achievement earned! \"It's a Good Job, Mate\". Sniper class unlocked!");
				un13 = true;
			}
			if(inventory.get(0).Name.toLowerCase().equals("thunder"))
			if(!un16) {
				Prnt("Achievement earned! \"Shocking\". Thunderblade unlocked!");
				un16 = true;
				m.add(Weapons.get(81));
			}
			if(inventory.get(0).Name.toLowerCase().equals("death ray"))
			if(!un17) {
				Prnt("Achievement earned! \"Destroyer of Worlds\". Apocalypse Flame unlocked!");
				un17 = true;
				m.add(Weapons.get(92));
			}
			if(inventory.get(0).Name.toLowerCase().equals("assault rifle"))
			if(!un18) {
				Prnt("Achievement earned! \"Lotta Bullets\". Minigun unlocked!");
				un18 = true;
				m.add(Weapons.get(82));
			}
			if(inventory.get(0).Name.toLowerCase().equals("minigun"))
			if(!un19) {
				Prnt("Achievement earned! \"Lotta Lotta Bullets\". Microgun unlocked!");
				un19 = true;
				m.add(Weapons.get(83));
			}
			if(inventory.get(0).Name.toLowerCase().equals("shotgun"))
			if(!un20) {
				Prnt("Achievement earned! \"Buckshot\". Slam Shotgun unlocked!");
				un20 = true;
				m.add(Weapons.get(84));
			}
		}
		if(money >= 1000000) {
			if(!un11) {
				Prnt("Achievement earned! \"Richest\". Aristocrat class unlocked!");
				un11 = true;
			}
		}
		if(money >= 100000) {
			if(!un14) {
				Prnt("Achievement earned! \"Richer\". Coingun unlocked!");
				un14 = true;
				r.add(Weapons.get(79));
			}
		}
		if(money >= 10000) {
			if(!un15) {
				Prnt("Achievement earned! \"Rich\". Golden Sword unlocked!");
				un15 = true;
				r.add(Weapons.get(80));
			}
		}
		String s = "";
		if(un1) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un2) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un3) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un4) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un5) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un6) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un7) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un8) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un9) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un10) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un11) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un12) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un13) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un14) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un15) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un16) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un17) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un18) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un19) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}if(un20) {
			s+= "1\n";
		}else {
			s+= "0\n";
		}
		file.SaveAchievements(s);
	}

	public static void achievements() {
		Prnt("Achievements earned:");
		if(un1||un2||un3||un4||un5||un6||un7||un9||un10||un11||un12||un13||un14||un15||un16||un17||un18||un19||un20) {
			if(un1) {
				Prnt("Hardcore- Finish the game on insane difficulty");
			}
			if(un2) {
				Prnt("School of Rock- Finish the game with the Electric Guitar");
			}
			if(un3) {
				Prnt("Criminal- Finish the game with the Knives");
			}
			if(un4) {
				Prnt("Draconic- Finish the game with the Flame Jet");
			}
			if(un5) {
				Prnt("Healing for Days- Finish the game with 300 or more potions");
			}
			if(un6) {
				Prnt("Barbaric- Finish the game with the Hatchets");
			}
			if(un7) {
				Prnt("Drunken Combat- Finish the game with the Beer Kegs");
			}
			if(un8) {
				Prnt("Gangsta'- Finish the game with the Tommy Gun");
			}
			if(un9) {
				Prnt("That's Gross- Finish the game with the Maggots");
			}
			if(un10) {
				Prnt("Eternally Slaying- Kill a Demon with the Super Shotgun");
			}
			if(un15) {
				Prnt("Rich- Obtain 10,000 gold in one run");
			}
			if(un14) {
				Prnt("Richer- Obtain 100,000 gold in one run");
			}
			if(un11) {
				Prnt("Richest- Obtain 1,000,000 gold in one run");
			}
			if(un12) {
				Prnt("Space Battles- Finish the game with the Laser Sword");
			}
			if(un13) {
				Prnt("It's a Good Job, Mate- Finish the game with the Sniper Rifle");
			}
			if(un16) {
				Prnt("Shocking- Finish the game with the Thunder");
			}
			if(un17) {
				Prnt("Destroyer of Worlds- Finish the game with the Death Ray");
			}
			if(un18) {
				Prnt("Lotta Bullets- Finish the game with the Assault Rifle");
			}
			if(un19) {
				Prnt("Lotta Lotta Bullets- Finish the game with the Minigun");
			}
			if(un20) {
				Prnt("Buckshot- Finish the game with the Shotgun");
			}
		} else {
			Prnt("No achievements!");
		}
	}

	public static void startPrompt() throws IOException {
		Prnt("Play again? (Yes/No)");
		boolean b = true;
		while (b) {
			String s = inputter();
			switch(s.toLowerCase()) {
			case"y":
			case "yes":
				money = 0;
				Level = 0;
				XP = 0;
				inventory.clear();
				durabilities.clear();
				Luck = 0;
				bossesKilled.clear();
				killedEnemies.clear();
				numkilled = new int[biomes.size()];
				Prnt("\n");
				gamerunning = false;
				return;
			case"n":
			case "no":

				Prnt("Closing game...");
				try {
					Thread.sleep(500);
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.exit(0);
				break;
			default:
				Prnt("Invalid input!");
				vibrate(frame,1,3);
			}
		}
	}
}
