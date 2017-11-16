import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * GoldenVillage class contains an array of Cinema objects
 * Cinema objects are constructed automatically on the creation of GoldenVillage class
 * @author user
 *
 */
public class GoldenVillage implements Serializable{

	private static final long serialVersionUID = 6196278472099192227L;
	private static GoldenVillage INSTANCE;
	private Cineplex[] cineplexes;
	public static final File gvDatabase = new File ("GV.tmp");
	
	
	
	private GoldenVillage(){
		cineplexes = new Cineplex[3];
		//soft-code this portion 
		cineplexes[0] = new Cineplex ("GV-Jurong", "J", 10, 18);
		cineplexes[1] = new Cineplex ("GV-Orchard", "O", 15, 18);
		cineplexes[2] = new Cineplex ("GV-Bugis", "B", 10, 18);
	}
	
	/**
	 * Returns GoldenVillage's array of cineplexes
	 * @return
	 */
	
	public static GoldenVillage getInstance() {
		ObjectReader or=null;
		try {
			or = new ObjectReader(gvDatabase);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (INSTANCE==null) {
			try {
				return INSTANCE = (GoldenVillage) or.initialiseObject();
			} catch(NullPointerException | ClassCastException e) {
				System.out.println("new");
				return INSTANCE = new GoldenVillage();
			}
				catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("justgetting");
		return INSTANCE;
	}
	
	public Cineplex[] getCineplexes(){
		return cineplexes;
	}
	
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(gvDatabase);
		ow.writeTheObject(GoldenVillage.getInstance());
	}
}
