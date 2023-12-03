public class Biome {
	
	String enter;
	String contin;
	String exit;
	String trap1;
	String trap2;
	String trap3;
	String vista1;
	String vista2;
	String vista3;
	String vista4;
	int index;
	
	public Biome(String intro, String cont, String end, String t1, String t2, String t3, String v1, String v2, String v3, String v4, int biomeIndex) {
		enter = intro;
		contin = cont;
		exit = end;
		trap1 = t1;
		trap2 = t2;
		trap3 = t3;
		vista1 = v1;
		vista2 = v2;
		vista3 = v3;
		vista4 = v4;
		index = biomeIndex;
	}
	
	public String toString() {
		return enter;
	}
}