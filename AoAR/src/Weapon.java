public class Weapon {
	String Name;
	int BaseDam;
	int APDrain;
	int ManaCost;
	int Healing;
	int NumHits;
	int Affinity;
	int WeaponType;
	int HitChance;
	int AmmoCost;
	boolean legendary;
	int durability;
	int damageType;
	int associatedStat;

	public Weapon(String name, int dmg, int ap, int mana, int healing, int hits, int aff, int type, int hitchance, boolean leg, int amm, int dur) {
		Name=name;
		BaseDam=dmg;
		APDrain=ap;
		ManaCost=mana;
		Healing=healing;
		NumHits=hits;
		Affinity=aff;
		WeaponType=type;
		HitChance=hitchance;
		legendary=leg;
		AmmoCost=amm;
		durability = dur;
	}
}