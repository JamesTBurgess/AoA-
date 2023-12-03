import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileThing {
	static File f;
	static Writer f2;
	
	public void save(int index, String savedata) throws IOException {
		String s = "";
		PrintWriter p;
		switch(index) {
			case 1:
				f = new File("saves/slot1.dat");
				f2 = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
				s = encrypt(savedata);
				p = new PrintWriter(f, "utf-8"); //PrintWriter used to clear file before rewriting
				p.flush();
				f2.write(s);
				f2.close();
				break;
			case 2:
				f = new File("saves/slot2.dat");
				f2 = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
				s = encrypt(savedata);
				p = new PrintWriter(f, "utf-8"); //PrintWriter used to clear file before rewriting
				p.flush();
				f2.write(s);
				f2.close();
				break;
			case 3:
				f = new File("saves/slot3.dat");
				f2 = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
				s = encrypt(savedata);
				p = new PrintWriter(f, "utf-8"); //PrintWriter used to clear file before rewriting
				p.flush();
				f2.write(s);
				f2.close();
				break;
		}
	}
	
	public void SaveAchievements(String s) throws IOException {
		PrintWriter p;
		f = new File("data/unlocks.dat");
		f2 = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
		String[] s1 = s.split("\n");
		s = "";
		for (String a:s1) {
			s+= encrypt(a) + "\n";
		}
		p = new PrintWriter(f,"utf-8");
		p.flush();
		f2.write(s);
		p.close();
		f2.close();
	}
	
	public void saveGhost(String s) throws IOException {
		PrintWriter p;
		Scanner ghostScan = new Scanner(new File("ghosts/ghostNumber.dat"), "utf-8");
		int i = Integer.parseInt(decrpyt(ghostScan.next()));
		String file = "ghosts/ghost" + i + ".dat";
		f = new File(file);
		f2 = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
		s = encrypt(s);
		p = new PrintWriter(f, "utf-8"); //PrintWriter used to clear file before rewriting
		p.flush();
		f2.write(s);
		p.close();
		ghostScan.close();
		f2.close();
	}
	
	public void color(int i,int g, int h) throws IOException {
		PrintWriter p;
		f = new File("data/Preferences.dat");
		f2 = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
		p = new PrintWriter(f, "utf-8");
		p.flush();
		f2.write("" + i+"\n"+g+"\n"+h);
		p.close();
		f2.close();
	}
	
	public void GhostNum() throws IOException {
		PrintWriter p;
		Scanner ghostScan = new Scanner(new File("ghosts/ghostNumber.dat"), "utf-8");
		String s = ghostScan.next();
		int i = Integer.parseInt(decrpyt(s));
		i++;
		if(i==4) {
			i=1;
		}
		f = new File("ghosts/ghostNumber.dat");
		f2 = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
		p = new PrintWriter(f, "utf-8"); //PrintWriter used to clear file before rewriting
		p.flush();
		f2.write(encrypt(""+i));
		p.close();
		ghostScan.close();
		f2.close();
	}
	
	public void KillGhost(int i) throws IOException {
		PrintWriter p;
		File f = new File("ghosts/ghost"+i+".dat");
		f2 = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
		p = new PrintWriter(f, "utf-8"); //PrintWriter used to clear file before rewriting
		p.flush();
		f2.write(encrypt("empty"));
		p.close();
		f2.close();
	}
	
	public String load(int slot) throws IOException {
		Scanner sc; 
		String s;
		switch(slot) {
		case 1:
			f = new File("saves/slot1.dat");
			sc = new Scanner(f, "utf-8"); //initialize file reader. this is done here so the nextLine always uses the first line of the file
			s = decrpyt(sc.nextLine());
			sc.close();
			return s;
		case 2:
			f = new File("saves/slot2.dat");
			sc = new Scanner(f, "utf-8"); //initialize file reader. this is done here so the nextLine always uses the first line of the file
			s = decrpyt(sc.nextLine());
			sc.close();
			return s;
		case 3:
			f = new File("saves/slot3.dat");
			sc = new Scanner(f, "utf-8"); //initialize file reader. this is done here so the nextLine always uses the first line of the file
			s = decrpyt(sc.nextLine());
			sc.close();
			return s;
		}
		return "";
	}
	public String encrypt(String s) { //encrypts strings by adding a large number to the char value. This will prevent people from editing game saves and giving themselves infinite health.
		String s2 = "";
		for(int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c != ';') {
				c += 4560;
			}
			s2 += c;
		}
		return s2;
	}
	public String decrpyt(String s) { //decrypts by subtracting the same number that encrypt adds. A ; can be used to separate data in the string.
		String s2 = "";
		for(int i = 0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (c != ';' && c != 10) {
				c -= 4560;
			}
			s2 += c;
		}
		return s2;
	}


}