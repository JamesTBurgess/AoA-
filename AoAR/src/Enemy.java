public class Enemy {
	String Name;
	int Atk;
	int Def;
	int Spd;
	int Vit;
	int Int;
	int Aff;
	int Wlth;
	Attack[] Attacks;
	int numkilled=0;

	public Enemy(String name, int atk, int def, int spd, int intel, int vit, int wlth, Attack[] atks) {
		Name = name;
		Atk = atk;
		Def = def;
		Spd = spd;
		Vit = vit;
		Int = intel;
		Wlth = wlth;
		Attacks = atks;
	}
}