import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Admin extends Account {

	private static final long serialVersionUID = 6340675425878862000L;
	public static final Admin INSTANCE = new Admin(); 
	
	private Admin () {
		super("admin", "pass");
	}
		
	public static Admin getInstance() {
		return INSTANCE;
	}
	
	public void adminMainControl(){
		System.out.println("===== Logged in as admin =====");
		System.out.println("Select option:");
		System.out.println("(1) Add/Update/Remove movies from movie listing");
		System.out.println("(2) Add/Remove cinema showtimes");
		System.out.println("(3) Configure system settings");
		System.out.println("(4) Log out");
		//need ask howard help initialise and populate database of goldenvillage
		Scanner sc = new Scanner(System.in);
		int adminmainmenuchoice = sc.nextInt();
		do {
			switch (adminmainmenuchoice){
			case 1: this.adminMovieControl(); break;
			case 2: this.adminShowTimeControl(); break;
			case 3: this.adminSystemControl(); break;
			case 4: System.out.println("Logging out..."); return;
			default: System.out.println("Invalid choice! Logging out..."); return; 
			}
		} while (true);
	}
	
	public void adminMovieControl(){
		System.out.println(" ===== Add/Update/Remove movie from movie listing =====");
		System.out.println("Select option: ");
		System.out.println("(1) Add a movie");
		System.out.println("(2) Update movie's show status");
		System.out.println("(3) Return to admin main menu");
		Scanner sc = new Scanner (System.in);
		int choice = sc.nextInt();
		do {
			switch (choice){
				case 1: this.addMovie(); break;
				case 2: this.updateMovie(); break;
				case 3: { System.out.println("Returning to admin main menu..."); return; }
				default: { System.out.println("Invalid choice! Returning to admin main menu..."); return; }
			} //end of switch bracket
		} while(true); //end of do-while bracket
	} //end of A/U/R Movie menu
	
	public void addMovie(){
		System.out.println(" ===== Add movie =====");
		Scanner sc = new Scanner (System.in);
		//need auto generate ID
		//need ask howard to check which class and which method to call to add new movie object to?
		System.out.print("Movie ID: "); int movieid = sc.nextInt(); sc.nextLine();
		System.out.print("Movie title: "); String movietitle = sc.nextLine();
		System.out.print("Movie duration: "); int movieduration = sc.nextInt();sc.nextLine();
		//choosing movie status
		System.out.print("Movie status: (1) Preview (2) Now Showing");
        String input = sc.nextLine();        
        StatusEnum moviestatus = null;
        try{
        moviestatus = StatusEnum.valueOf(input);
        }
        catch (IllegalArgumentException e){
            System.out.println("Invalid choice");
            return;
        }
        //choose finish movie status
        
        System.out.print("Directed by: "); String moviedirector = sc.nextLine();
        String [] moviecast = new String [10];
        System.out.println("Cast: ");
        int i = 0;
		do{
        	moviecast[i] = sc.nextLine();
        	if (moviecast[i] == "end")
        		i = 10;
        	i++;
        } while(i < 10);
		List<String> moviecastlist = new ArrayList<String>(Arrays.asList(moviecast));
        System.out.println("Synopsis: "); String moviesynopsis = sc.nextLine();
        Movie movietoadd = new Movie (movieid, movietitle, movieduration, moviestatus, moviedirector, moviecastlist, moviesynopsis);
        //howard now need your help to add the "movietoadd" variable to the movielisting 
	}
	
	public void updateMovie(){
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== Update movie ===== ");
		System.out.print("Enter ID of movie to update: ");
		int movieid = sc.nextInt();
		//howard need to scan the movielisting for the movie id to update
		//howard need to throw error if the id is not found
		Movie selectedmovie;//howard need convert whatever searched into movie object
		System.out.println("Choose new status for " + selectedmovie.getTitle() + ": (1) Preview (2) Now Showing (3) End of Showing");
		String input = sc.nextLine();        
		StatusEnum newstatus = null;
		try{
			newstatus = StatusEnum.valueOf(input);
		}
		catch (IllegalArgumentException e){
			System.out.println("Invalid choice");
		    return;
		}
		selectedmovie.updateMovieStatus(newstatus);
	}
	
	public void adminShowTimeControl(){
		System.out.println(" ===== Add/Update/Remove Showtimes =====");
		System.out.println("Select option: ");
		System.out.print("(1) Add showtime");
		System.out.println("(2) Remove showtime");
		System.out.println("(3) Return to admin main menu");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		do {
			switch(choice){
				case 1: this.updateShowTime(1); break;
				case 2: this.updateShowTime(2); break;
				case 3: { System.out.println("Returning to admin main menu..."); return; }
				default: { System.out.println("Invalid choice! Going to admin main menu..."); return; }
			}
		} while(true);
	}
	
	public void updateShowTime(int i){
		if (i == 1)
			System.out.println(" ===== Add Showtime =====");
		else if (i == 2)
			System.out.println(" ===== Remove Showtime =====");
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		//need to double check this line below
		Cineplex [] cineplexes = GoldenVillage.getCineplexes();
		System.out.println("Select cineplex");
		for (int j = 0; i < cineplexes.length; i++)
			System.out.println("(" + i+1 + ") " + cineplexes[i].getCineplexName());
		int cineplexchoice = sc.nextInt();
		String cineplexname = cineplexes[cineplexchoice - 1].getCineplexName();
		String cineplexcode = cineplexes[cineplexchoice - 1].getCineplexCode();
		Cinema [] tempcinemaarray = cineplexes[cineplexchoice - 1].getCinemas();
			
		System.out.println("Select cinema");
		for (int j = 0; i < tempcinemaarray.length ; i++)
			System.out.println("(" + i+1 + ")" + tempcinemaarray[i].getCinemaCode());
		int cinemachoice = sc.nextInt();
		String cinemacode = tempcinemaarray[cinemachoice - 1].getCinemaCode();
		Timetable [] tempcalendararray = tempcinemaarray[cinemachoice - 1].getCalendar();
			
		System.out.println("Select date:");
		for (int j = 0; i < tempcalendararray.length; i++)
			System.out.println("(" + i + 1 + ")" + dateFormatter.format(tempcalendararray[i].getDate().getTime()));
		//need to fetch the DayTypeEnum daytype from here to pass to constructor of ShowTime
		int datechoice = sc.nextInt();
		String date = dateFormatter.format(tempcalendararray[datechoice - 1].getDate().getTime());
		tempcalendararray[datechoice - 1].displaySchedule();
		
		System.out.println("Select movie (by ID): ");
		//howard list down the movies using movielisting's method
		int movieid = sc.nextInt();
		//can use the search movie method to check if the inputted movie title exist
		Movie moviechoice; //howard whatever that converts movieid to movie class
		System.out.println("Time: ");
		int starttime = sc.nextInt();
		if (i == 1){ //if admin wanted to add showtime
			tempcalendararray[datechoice - 1].addShowTimeToSchedule(moviechoice, starttime);
			moviechoice.addShowTimeToMovie(new ShowTime(moviechoice.getTitle(), cineplexname, cineplexcode, cinemacode, date, daytype, starttime, tempcinemaarray[cinemachoice - 1].getNumberOfRows(), tempcinemaarray[cinemachoice - 1].getNumberOfCols()));
		}
		else if (i == 2) { //if admin wanted to remove showtime
			tempcalendararray[datechoice - 1].removeShowTimeFromSchedule(moviechoice, starttime);
			//the below Movie object method need your help implement
			moviechoice.removeShowTimeFromMovie(/*put a showtime object here*/);
		}
	}
	
	public void adminSystemControl(){
		System.out.println(" ===== Settings ===== ");
		System.out.println("Select option: ");
		System.out.println("(1) Add holiday");
		System.out.println("(2) Clear all holidays");
		System.out.println("(3) Update pricing policy");
		System.out.println("(4) Return to admin main menu");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		do{
			switch(choice){
				case 1: addHoliday(); break;
				case 2: clearHolidays(); break;
				case 3: updatePrices(); break;
				case 4: { System.out.println("Returning to admin main menu..."); return; }
				default: { System.out.println("Invalid choice! Returning to admin main menu"); return; }
			}
		} while (true);
	}
	
	public void addHoliday(){
		System.out.println(" ===== Add Holiday ===== ");
		System.out.println("Enter a date to enter as holiday");
		
	}
	
	static class InvalidChoice extends Exception{
		public InvalidChoice () {
		super ("Invalid choice!");
		}
	}
	
}
