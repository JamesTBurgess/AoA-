public class Attack {
	String Text;
	int Dmg;
	int DamageType;
	public Attack(String text, int damage) {
		Text = text;
		Dmg = damage;
	}
	public String toString() {
		return Text + ", " + Dmg;
	}
}