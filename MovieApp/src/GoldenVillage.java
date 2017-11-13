import java.util.Scanner;
import java.text.SimpleDateFormat;

public class GoldenVillage {
	private static Cineplex[] cineplexes;
	
	public GoldenVillage(){
		cineplexes = new Cineplex[3];
		//soft-code this portion 
		cineplexes[0] = new Cineplex ("GV-Jurong", "J", 10, 18);
		cineplexes[1] = new Cineplex ("GV-Orchard", "O", 15, 18);
		cineplexes[2] = new Cineplex ("GV-Bugis", "B", 10, 18);
	}
	
	public static Cineplex[] getCineplexes(){
		return cineplexes;
	}
	
}
