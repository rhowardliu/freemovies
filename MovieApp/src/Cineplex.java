import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Each Cineplex class contains an array of Cinema objects, a cineplex code and a name
 * @author user
 *
 */
public class Cineplex implements Serializable {
	private static final long serialVersionUID = 598519697823438373L;
	private final Cinema [] cinemas;
	private String cineplexcode;
	private String name; //name to contain location
	public static List<Cineplex> cineplexlist = new ArrayList<Cineplex>();
	public static final File cineplexDatabase = new File ("Cineplex.tmp");
	
	public Cineplex(String name, String cineplexcode, int rows, int cols){
		this.name = name;
		this.cineplexcode = cineplexcode;
		this.cinemas = new Cinema[3];
		//for every cineplex created, 1 platinum cinema and 2 standard cinemas are created
		//each cinema will have a timetable of 365 slots, each slot represents one day of the month
		//each timetable slot is set to weekdays and weekends by default
		//
		cinemas[0] = new Cinema(CinemaTypeEnum._platinum, "01", 7, 7);
		cinemas[1] = new Cinema(CinemaTypeEnum._standard, "02", rows, cols);
		cinemas[2] = new Cinema(CinemaTypeEnum._standard, "03", rows, cols);
	}
	
	public String getCineplexName(){
		return this.name;
	}
	
	public String getCineplexCode(){
		return this.cineplexcode;
	}
	
	public Cinema[] getCinemas(){
		return this.cinemas;
	}
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(cineplexDatabase);
		cineplexlist = or.initialiseDataList(cineplexlist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(cineplexDatabase);
		ow.updateDataList(cineplexlist);
	}
}
