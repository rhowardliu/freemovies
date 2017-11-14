/**
 * Each Cineplex class contains an array of Cinema objects, a cineplex code and a name
 * @author user
 *
 */
public class Cineplex {
	private final Cinema [] cinemas;
	private String cineplexcode;
	private String name; //name to contain location
	
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
}
