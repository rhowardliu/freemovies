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
	
	//switch to admin class?
	public static void addShowTime(){ 
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		
		System.out.println("Select cineplex");
		for (int i = 0; i < cineplexes.length; i++)
			System.out.println("(" + i+1 + ") " + cineplexes[i].getCineplexName());
		int cineplexchoice = sc.nextInt();
		String cineplexname = cineplexes[cineplexchoice - 1].getCineplexName();
		String cineplexcode = cineplexes[cineplexchoice - 1].getCineplexCode();
		Cinema [] tempcinemaarray = cineplexes[cineplexchoice - 1].getCinemas();
		
		System.out.println("Select cinema");
		for (int i = 0; i < tempcinemaarray.length ; i++)
			System.out.println("(" + i+1 + ")" + tempcinemaarray[i].getCinemaCode());
		int cinemachoice = sc.nextInt();
		String cinemacode = tempcinemaarray[cinemachoice - 1].getCinemaCode();
		Timetable [] tempcalendararray = tempcinemaarray[cinemachoice - 1].getCalendar();
		
		System.out.println("Select date:");
		for (int i = 0; i < tempcalendararray.length; i++)
			System.out.println("(" + i + 1 + ")" + dateFormatter.format(tempcalendararray[i].getDate().getTime()));
		//need to fetch the DayTypeEnum daytype from here to pass to constructor of ShowTime
		int datechoice = sc.nextInt();
		String date = dateFormatter.format(tempcalendararray[datechoice].getDate().getTime());
		tempcalendararray[datechoice - 1].displaySchedule();
		System.out.println("Select movie: ");
		//list down the movies using movielisting's method
		//temp line below
		String movietitle = sc.nextLine();
		//can use the search movie method to check if the inputted movie title exist
		Movie moviechoice; //whatever that converts movietitle to movie class
		System.out.print(" time: ");
		int starttime = sc.nextInt();
		tempcalendararray[datechoice - 1].addShowTimeToSchedule(moviechoice, starttime);
		//afte this line, the showtime will be added
		moviechoice.addShowTimeToMovie(new ShowTime(movietitle, cineplexname, cineplexcode, cinemacode, date, daytype, starttime, tempcinemaarray[cinemachoice - 1].getNumberOfRows(), tempcinemaarray[cinemachoice - 1].getNumberOfCols()));
	}
}
