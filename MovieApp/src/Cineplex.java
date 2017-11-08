
public class Cineplex {
	private final Cinema [] cinemas;
	
	private String name; //name to contain location
	
	public Cineplex(String name){
		this.name = name;
		this.cinemas = new Cinema[3];
		//for every cineplex created, 1 vip cinema and 2 standard cinemas are created
		//each cinema will have a timetable of 31 slots, each slot represents one day of the month
		//each timetable slot is weekday by default
		//
		cinemas[0] = new Cinema(CinemaTypeEnum.VIP, this);
		cinemas[1] = new Cinema(CinemaTypeEnum.standard, this);
		cinemas[2] = new Cinema(CinemaTypeEnum.standard, this);
	}
	
	public String getCineplexName(){
		return this.name;
	}
	
	public Cinema[] getCinemas() {
		return cinemas;
	}

}
