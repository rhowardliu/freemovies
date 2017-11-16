import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 * Admin class derived from Account class
 * Admin class has class methods that allow it extra priviledges to add/remove/update ShowTimes to Cinema classes, add/remove/update Movies to MovieListing class, and change pricing policies in PriceSetting class
 * @author user
 *
 */
public class Admin extends Account {

	private static final long serialVersionUID = 6340675425878862000L;
	private static final Admin INSTANCE = new Admin(); 
	
	/**
	 * Constructor method for Admin object. It is simple userID = "admin", password = "pass" for login purposes
	 */
	private Admin () {
		super("admin", "pass");
	}
	
	/**
	 * Calls constructor for Admin object and returns the newly created Admin object to the main Execute class
	 * @return
	 */
	public static Admin getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Admin main control method that serves as the admin's main menu. It allows admin to select between
	 * (1) Add/Update movies from movie listing
	 * (2) Add/Remove cinema showtimes
	 * (3) Configure system settings (add holiday dates, change pricing policy)
	 * (4) Log out (i.e. returns control back to Execute class)
	 * If invalid choice is selected, admin will be logged out by default
	 * @throws Exception
	 */
	public void adminMainControl() throws Exception{
		do {
			System.out.println("===== Admin Main Menu =====");
			System.out.println("(1) Add/Update/View movies from movie listing");
			System.out.println("(2) Add/Remove/View cinema showtimes");
			System.out.println("(3) Configure system settings");
			System.out.println("(4) Log out");
			System.out.print("Select option:");
			//need ask howard help initialise and populate database of goldenvillage
			Scanner sc = new Scanner(System.in);
			int adminmainmenuchoice = sc.nextInt();
		
			switch (adminmainmenuchoice){
				case 1: this.adminMovieControl(); break;
				case 2: this.adminShowTimeControl(); break;
				case 3: this.adminSystemControl(); break;
				case 4: System.out.println("Logging out..."); return;
				default: System.out.println("Invalid choice! Logging out..."); return; 
			}
		} while (true);
	}
	
	/**
	 * Allows user to selected between
	 * (1) Add movie
	 * (2) Update movie's details
	 * (3) Return to admin's main menu
	 * An invalid choice will bring the user back to the admin's main menu
	 */
	public void adminMovieControl(){
		do {
			System.out.println(" ===== Add/Update/Remove movie from movie listing =====");
			System.out.println("Select option: ");
			System.out.println("(1) Add a movie");
			System.out.println("(2) Update a movie's details");
			System.out.println("(3) View all movies in list");
			System.out.println("(4) Return to admin main menu");
			Scanner sc = new Scanner (System.in);
			int choice = sc.nextInt();
			switch (choice){
				case 1: this.addMovie(); return;
				case 2: this.updateMovieDetails(); break; 
				case 3: { //prints out all movies in the movelist and their show status
					System.out.println("===== All movies (show status) =====");
					int i = 1;
					for (Movie movietoshow : Movie.movielist)
						System.out.println("(" + (i++) + ") " + movietoshow.getTitle() + " (" + movietoshow.getStatus() + ")");
				}
				case 4: { System.out.println("Returning to admin main menu..."); return; }
				default: { System.out.println("Invalid choice! Returning to admin main menu..."); return; }
				} //end of switch bracket
		} while(true); //end of do-while bracket
	} //end of A/U/R Movie menu
	
	/**
	 * Request user for movie information and passes the user's inputs into the constructor method for the new Movie object
	 * New Movie object is added to the movie listing
	 */
	public void addMovie(){
		System.out.println(" ===== Add movie =====");
		Scanner sc = new Scanner (System.in);
		//need ask howard to check which class and which method to call to add new movie object to?
		System.out.print("Movie ID: "); String movieid = sc.nextLine();
		System.out.print("Movie title: "); String movietitle = sc.nextLine();
		System.out.print("Movie type: \n"); MovieTypeEnum movietype = this.getInputMovieType();
		System.out.print("Movie duration (in hours): "); int movieduration = sc.nextInt();sc.nextLine();
		System.out.println("Movie status:");
		StatusEnum moviestatus = this.getInputStatus();       
        System.out.print("Directed by: "); String moviedirector = sc.nextLine();
		ArrayList<String> moviecastlist = this.createMovieCastList();
        System.out.println("Synopsis: "); String moviesynopsis = sc.nextLine();
        Movie movietoadd = new Movie (movieid, movietitle, movieduration, moviestatus, movietype, moviedirector, moviecastlist, moviesynopsis); 
        System.out.println("Movie added!");
        System.out.println("Returning to main menu...\n");
        return;
	}
	
	/**
	 * Gets user's desired show status and return it as a StatusEnum type value
	 * @return StatusEnum 
	 */
	public StatusEnum getInputStatus() {
		System.out.println("(1) Coming Soon");
		System.out.println("(2) Preview");
		System.out.println("(3) Now Showing");
		System.out.println("(4) End of Show");
		System.out.print("Enter Status:");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		switch(i) {
			case 1: return StatusEnum.ComingSoon;
			case 2: return StatusEnum.Preview;
			case 3: return StatusEnum.NowShowing;
			case 4: return StatusEnum.EndOfShow;
			default: { System.out.println("Invalid choice! Set to Coming Soon by default."); return StatusEnum.ComingSoon; }	
		}
	}
	
	/**
	 * Gets user's desired movie type and return it as a MovieTypeEnum type value
	 * @return MovieTypeEnum 
	 */
	
	public MovieTypeEnum getInputMovieType() {
		System.out.println("(1) 3D");
		System.out.println("(2) Blockbuster");
		System.out.println("(3) Digital");
		System.out.print("Enter movie type: ");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		switch(i) {
			case 1: return MovieTypeEnum._3D;
			case 2: return MovieTypeEnum.BB;	
			case 3: return MovieTypeEnum.digital;
			default: { System.out.println("Invalid choice! Set to Digital by default."); return MovieTypeEnum.digital; }	
		}
	}
	
	public ArrayList<String> createMovieCastList(){
		ArrayList<String> moviecastlist = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Cast Names (done to end): ");
		try {
			while(true){
				String s = sc.nextLine();
				if (s.equalsIgnoreCase("done"))
					break;
				else
					moviecastlist.add(s);
		      }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return moviecastlist;
	}
	
	/**
	 * Allows admin to change a Movie object's attribute values by keying in the Movie object's movie ID
	 * Informs the user when the attribute values has been updated
	 */
	public void updateMovieDetails(){
		//ellen need fill up code over here
		//need a switch statement here. one of the switch options should include updateMovieStatus method (found right below this method)
		Scanner sc = new Scanner(System.in);
		Movie selectedmovie=null;
		do {
			System.out.println("Enter MovieID to update: ");
			String movieid = sc.nextLine();
			try {
				selectedmovie = Movie.searchMovie(movieid);
				System.out.println("Movie selected: " + selectedmovie.getTitle() + ".\n");
			}catch(Exception e) {
				System.out.println("Movie not found.");
				return;
			}
		} while(selectedmovie==null);

		boolean quit= false;
		while (quit == false) {	
			System.out.println("\n");
			System.out.println("===== Select features to update =====");
			System.out.println("(1) Movie Title");
			System.out.println("(2) Movie Status");
			System.out.println("(3) Movie Type ");
			System.out.println("(4) Movie Director ");
			System.out.println("(5) Movie Cast ");
			System.out.println("(6) Movie Synopsis ");
			System.out.println("(7) Movie Duration ");
			System.out.println("(8) Display Updated Movie Details ");
			System.out.println("(9) Quit ");
			System.out.println("Enter Selection: ");
			int detailChoice = sc.nextInt();
			sc.nextLine();
			switch(detailChoice) {
			case 1:
				System.out.println("Enter New Movie Title:");
				String newMovieTitle = sc.nextLine();
				selectedmovie.setMovieTitle(newMovieTitle);
				System.out.println("Movie Title has been updated");
				break;
			case 2:
				updateMovieStatus(selectedmovie);
				System.out.println("Movie Status has been updated");
				break;
			case 3:
				selectedmovie.setMovieType(getInputMovieType());
				System.out.println("Movie Type has been updated");
				break;
			case 4:
				System.out.println("Enter New Movie Director:");
				String newMovieDirector = sc.nextLine();
				selectedmovie.setMovieDirector(newMovieDirector);
				System.out.println("Movie Director has been updated");
				break;
			case 5:
				System.out.println("Current Cast Input:");
				for (int i = 0; i < selectedmovie.getCast().size(); i++) //prints out current cast list
					System.out.println((i+1) + ". " + selectedmovie.getCast().get(i));
				System.out.println("(1) Update Individual Cast Member:");
				System.out.println("(2) Re-enter All Cast Members:");
				System.out.println("Enter Choice:");
				int castChoice = sc.nextInt();
				switch (castChoice) {
					case 1: updateIndividualCast(selectedmovie); System.out.println("Movie cast list has been updated."); break;
					case 2: selectedmovie.setMovieCast(this.createMovieCastList()); System.out.println("Movie cast list has been updated."); break;
					default: System.out.println("Invalid choice! Returning to main menu..."); break;
				}
				break;
			case 6:
				System.out.println("Enter New Movie Synopsis:");
				sc.nextLine();
				String newMovieSynopsis = sc.nextLine();
				selectedmovie.setMovieSynopsis(newMovieSynopsis);
				System.out.println("Movie Synopsis has been updated");
				break;
			case 7:
				System.out.println("Enter New Movie Duration:");
				int newMovieDuration = sc.nextInt();
				selectedmovie.setMovieDuration(newMovieDuration);
				System.out.println("Movie Duration has been updated");
				break;
			case 8:
				selectedmovie.getMovieInfo();
				break;
			case 9:
				quit = true;
				break;
			}
		}
		return;
	}
	
	/**
	 * Method to allow admin to update the cast members of a Movie object
	 * @param selectedmovie
	 */
	public static void updateIndividualCast(Movie selectedmovie) {
		Scanner sc = new Scanner(System.in);
		char ans;
		while(true){
			System.out.println("Enter Index of Cast Name to be updated:");
			int index = sc.nextInt();
			System.out.println("(1) Delete Cast");
			System.out.println("(2) Re-enter Cast Name");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				selectedmovie.getCast().remove(index-1);
				System.out.println("Cast has been deleted");
				break;
			case 2:
				System.out.println("Enter Updated Cast Name: ");
				sc.nextLine();
				String castName = sc.nextLine();
				selectedmovie.getCast().set(index-1, castName);
				System.out.println("Cast Name has been updated ");
				break;
			}
			System.out.println("Updated Cast Input:");
			for (int i = 0; i < selectedmovie.getCast().size(); i++){
				System.out.println((i+1) + ". " + selectedmovie.getCast().get(i));
			}
			System.out.println("Update another individual cast member? (Y/N)");
			if (sc.next().equalsIgnoreCase("n"))
				break;
		} 
		
	}
	
	/**
	 * Allows admin to update a Movie object's show status
	 * @param selectedmovie
	 */
	public static void updateMovieStatus(Movie selectedmovie) {
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== Update movie ===== ");
		System.out.println("The current status of " + selectedmovie.getTitle() +" is : " + selectedmovie.getStatus());
		System.out.println("Update to:");
		System.out.println("(1) Coming Soon");
		System.out.println("(2) Preview");
		System.out.println("(3) Now Showing");
		System.out.println("(4) End of Show");
		int newstatus = sc.nextInt();
		switch (newstatus) {
			case 1: selectedmovie.updateMovieStatus(StatusEnum.ComingSoon); break;
			case 2: selectedmovie.updateMovieStatus(StatusEnum.Preview); break;
			case 3: selectedmovie.updateMovieStatus(StatusEnum.NowShowing); break;
			case 4: selectedmovie.updateMovieStatus(StatusEnum.EndOfShow); break;
		}
	}
	
	/**
	 * Allows admin to choose between
	 * (1) Adding showtime to cinema
	 * (2) Removing showtime from cinema
	 * (3) Return to admin main menu
	 * An invalid choice will return the user back to the admin main menu
	 */
	public void adminShowTimeControl() throws Exception{
		do {
			System.out.println(" ===== Add/Update/Remove Showtimes =====");
			System.out.println("(1) Add showtime");
			System.out.println("(2) Remove showtime");
			System.out.println("(3) View all showtimes");
			System.out.println("(4) Return to admin main menu");
			System.out.print("Select option: ");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			switch(choice){
				case 1: this.updateShowTime(1); break;
				case 2: this.updateShowTime(2); break;
				case 3: {
					for (Movie x : Movie.movielist)
						x.displayShowTimesForAdmin();
					break;
				}
				case 4: { System.out.println("Returning to admin main menu..."); return; }
				default: { System.out.println("Invalid choice! Going to admin main menu..."); return; }
			}
		} while(true);
	}
	
	/**
	 * Request user to input cineplex, cinema, date and time to add or remove a showtime
	 * 
	 * @param i
	 */
	@SuppressWarnings("null")
	public void updateShowTime(int i) throws Exception{ //start of updateShowTime method
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Scanner sc = new Scanner(System.in);
		
		if (i == 1)
			System.out.println(" ===== Add Showtime =====");
		else if (i == 2)
			System.out.println(" ===== Remove Showtime =====");
		
		Cineplex [] cineplexes = GoldenVillage.getCineplexes();
		System.out.println("Cineplexes: ");
		for (int j = 0; j < cineplexes.length; j++) //lists out all the cineplexes
			System.out.println("(" + (j+1) + ") " + cineplexes[j].getCineplexName());
		System.out.print("Select cineplex: "); int cineplexchoice = sc.nextInt();
		Cineplex chosencineplex = cineplexes[cineplexchoice - 1]; 
		String cineplexname = chosencineplex.getCineplexName();
		String cineplexcode = chosencineplex.getCineplexCode();
		
		Cinema [] tempcinemaarray = chosencineplex.getCinemas();
		System.out.println("Cinemas: ");
		for (int j = 0; j < tempcinemaarray.length ; j++)
			System.out.println("(" + (j+1) + ")" + tempcinemaarray[j].getCinemaCode());
		System.out.print("Select cinema: "); int cinemachoice = sc.nextInt();
		Cinema chosencinema = tempcinemaarray[cinemachoice - 1];
		String cinemacode = chosencinema.getCinemaCode();
		Timetable [] chosencalendar = chosencinema.getCalendar();
		
		Timetable chosentimetable = null;

		System.out.println("Select date:");
		System.out.print("Enter day (XX) :"); int day = sc.nextInt();
		System.out.print("Enter month (XX) :"); int month = sc.nextInt();
		System.out.print("Enter year (XXXX) :"); int year = sc.nextInt();
		String date = String.format("%02d-%02d-%d",day,month,year);
		for (Timetable x : chosencalendar){
			if (x.getDateString() == date)
				chosentimetable = x;
		}
		if (chosentimetable == null) {
			System.out.println("Timetable not found");
			System.out.println("Returning to main menu...");
			return;
		}
		
//		try {
//			tt = Timetable.getTimetableByDate(date);
//		}catch(Exception e) {
//			System.out.println("Timetable not found");
//		}
//		}while(tt==null);
		
		System.out.println("Schedule on " + date + ":");
		chosentimetable.displaySchedule(); //displays schedule for a particular day
		Movie moviechoice = null; String movieid = null;
		do {
			System.out.println("Enter movieID: ");
			movieid = sc.next();
			try {
				moviechoice = Movie.searchMovie(movieid);
				System.out.println(moviechoice.getTitle() + " has been selected. \n");
			}catch(Exception e) {
				System.out.println("Movie not found");
			}
		} while(moviechoice == null);
		
		boolean available = false;
		do {
			System.out.println(moviechoice.getTitle() + "'s schedule on " + date);
			System.out.print("Select start hour (24-hour format): "); int starttime = sc.nextInt();
			//create a ShowTime object so that it will be added to the database
			ShowTime st_to_add = new ShowTime (moviechoice.getTitle(), movieid, cineplexname, cineplexcode, cinemacode, date, starttime, chosencinema.getNumberOfRows(), chosencinema.getNumberOfCols(),chosencinema.getCinemaType());
			
			if (i == 1){ //if admin wanted to add showtime		
				available = chosentimetable.addShowTimeToSchedule(moviechoice, starttime);
				if (available == true) //if there is no clash on the chosentimetable
					moviechoice.addShowTimeToMovie(cineplexname, cineplexcode, cinemacode, date, starttime, chosencinema.getNumberOfRows(), chosencinema.getNumberOfCols(), chosencinema.getCinemaType());
				else {
					System.out.println("Showtime was not added due to clash in schedule!");
					System.out.println("Returning back... to somewhere");
					return;
				}
			}
			
			else if (i == 2) { //if admin wanted to remove showtime	
				available = chosentimetable.removeShowTimeFromSchedule(moviechoice, starttime);
				if (available==true) //if there is no clash on the chosentimetable
					moviechoice.removeShowTimeFromMovie(cineplexname, cineplexcode, cinemacode, date, starttime, chosencinema.getNumberOfRows(), chosencinema.getNumberOfCols(),chosencinema.getCinemaType());
				else {
					System.out.println("Showtime was not added due to clash in schedule!");
					System.out.println("Returning back... to somewhere");
					return;
				}
			} 
		} while (available == false);
	}
	
	public void adminSystemControl() throws Exception{

		Scanner sc = new Scanner(System.in);
		
		do{	
			System.out.println(" ===== Settings ===== ");	
			System.out.println("(1) Add holiday");
			System.out.println("(2) Remove Holiday");
			System.out.println("(3) Clear all holidays");
			System.out.println("(4) Update pricing policy");
			System.out.println("(5) Return to admin main menu");
			System.out.println("Select option: ");
			int choice = sc.nextInt();
			switch(choice){
				case 1: addHoliday(); break; 
				case 2: removeHoliday();break;
				case 3: clearHolidays(); break; 
				case 4: updatePrices(); break; //ellen need create method for this
				case 5: { System.out.println("Returning to admin main menu..."); return; }
				default: { System.out.println("Invalid choice! Returning to admin main menu"); return; }
			}
		} while (true);
	}
	
	/**
	 * Allows admin to add a holiday date which will be added to the PriceSetting class
	 * @throws Exception
	 */
	public void addHoliday() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== Add Holiday ===== ");
		System.out.println("Enter a date to enter as holiday");
		System.out.println("Enter Day (XX) : ");
		int day = sc.nextInt();
		System.out.println("Enter Month (XX) : ");
		int month = sc.nextInt();
		System.out.println("Enter Year (XXXX) : ");
		int year = sc.nextInt();
		String date = String.format("%02d-%02d-%04d",day,month,year);
		if (Timetable.getTimetableByDate(date).getDayType() != DayTypeEnum.PH) {
			PriceSetting.addPublicHol(date);
			System.out.println(date +" set as Public Holiday");
			Timetable.getTimetableByDate(date).setPublicHoliday(DayTypeEnum.PH);
			return;
		}
		else {
			System.out.println("Date is already set as a Holiday");
			return;
		}
	
	
		//yet to do

		//ellen help
	}
	/**
	 * User is requested for a date to remove its holiday status
	 * PriceSetting class is updated accordingly 
	 * @throws Exception
	 */
	public void removeHoliday() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== Remove Holiday ===== ");
		System.out.println("Enter a date to remove as holiday");
		System.out.println("Enter Day (XX) : ");
		int day = sc.nextInt();
		System.out.println("Enter Month (XX) : ");
		int month = sc.nextInt();
		System.out.println("Enter Year (XXXX) : ");
		int year = sc.nextInt();
		String date = String.format("%02d-%02d-%04d",day,month,year);
		if (PriceSetting.getPublicHol().contains(date) == true) {
			Timetable.getTimetableByDate(date).setPublicHoliday(DayTypeEnum.Weekday);
			System.out.println(date + " has been removed as a holiday.");
		}
		else 
			System.out.println("Date entered is not set as public holiday");
	}
	
	/**
	 * Removes holiday status from all dates in the year
	 * @throws Exception
	 */
	public void clearHolidays() throws Exception{
		System.out.println(" ===== Clear Holiday ===== ");
		for (int i=0; i<PriceSetting.getPublicHol().size();i++) {
			Timetable.getTimetableByDate(PriceSetting.getPublicHol().get(i)).setPublicHoliday(DayTypeEnum.Weekday);
		}
			PriceSetting.clearPublicHol();
		System.out.println("Public Holidays all rest back to their respective day type.");
	}
	
	/**
	 * To allow admin to update price settings in PriceSetting class
	 */
	public void updatePrices(){
		Scanner sc = new Scanner (System.in);
		boolean quit = false;
		while (quit != true) {
			System.out.println(" ===== Update Pricing Policy ===== ");
			//ellen help
			System.out.println("(1) Adult");
			System.out.println("(2) Child");
			System.out.println("(3) Student");
			System.out.println("(4) Senior");
			System.out.println("(5) Premium 3D");
			System.out.println("(6) Premium Blockbuster");
			System.out.println("(7) Premium Holiday");
			System.out.println("(8) Platinum");
			System.out.println("(9) Quit");
			System.out.println("Enter Price Category to be updated: ");
			int catChoice = sc.nextInt();
			switch (catChoice) {
			case 1:
				System.out.println("Enter new price for Adult Category");
				double adultPrice = sc.nextDouble();
				PriceSetting.setTPAdult(adultPrice);
				System.out.println("Adult Category price has been updated to " +PriceSetting.getTPAdult());
				break;
			case 2:
				System.out.println("Enter new price for Child Category");
				double childPrice = sc.nextDouble();
				PriceSetting.setTPChild(childPrice);
				System.out.println("Child Category price has been updated to " +PriceSetting.getTPChild());
				break;
			case 3:
				System.out.println("Enter new price for Student Category");
				double studentPrice = sc.nextDouble();
				PriceSetting.setTPStudent(studentPrice);
				System.out.println("Student Category price has been updated to " +PriceSetting.getTPStudent());
				break;
			case 4:
				System.out.println("Enter new price for Senior Category");
				double seniorPrice = sc.nextDouble();
				PriceSetting.setTPSenior(seniorPrice);
				System.out.println("Senior Category price has been updated to " +PriceSetting.getTPSenior());
				break;
			case 5:
				System.out.println("Enter new price for Premium 3D Category");
				double threeDPrice = sc.nextDouble();
				PriceSetting.setTP3D(threeDPrice);
				System.out.println("Premium 3D Category price has been updated to " +PriceSetting.getTP3D());
				break;
			case 6:
				System.out.println("Enter new price for Premium Blockbuster Category");
				double BBPrice = sc.nextDouble();
				PriceSetting.setTPBB(BBPrice);
				System.out.println("Premium Blockbuster Category price has been updated to " +PriceSetting.getTPBB());
				break;
			case 7:
				System.out.println("Enter new price for Premium Holiday Category");
				double holidayPrice = sc.nextDouble();
				PriceSetting.setTPAdult(holidayPrice);
				System.out.println("Premium Holiday Category price has been updated to " +PriceSetting.getTPHoliday());
				break;
			case 8:
				System.out.println("Enter new price for Cinema Platinum Category");
				double platinumPrice = sc.nextDouble();
				PriceSetting.setTPPlatinum(platinumPrice);
				System.out.println("Cinema Platinum Category price has been updated to " +PriceSetting.getTPPlatinum());
				break;
			case 9:
				{quit = true;
				System.out.println("Returning to system settings menu...");
				break;}
			default: { System.out.println("Invalid choice! Going to system settings menu..."); break; }
			}
		}
	}
	
	static class InvalidChoice extends Exception{
		public InvalidChoice () {
		super ("Invalid choice!");
		}
	}
}

