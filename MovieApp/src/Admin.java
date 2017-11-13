import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Admin extends Account {

	private static final long serialVersionUID = 6340675425878862000L;
	private static final Admin INSTANCE = new Admin(); 
	
	private Admin () {
		super("admin", "pass");
	}
		
	public static Admin getInstance() {
		return INSTANCE;
	}
	
	public void adminMainControl() throws Exception{
		System.out.println("===== Logged in as admin =====");
		do {
		System.out.println("Select option:");
		System.out.println("(1) Add/Update/Remove movies from movie listing");
		System.out.println("(2) Add/Remove cinema showtimes");
		System.out.println("(3) Configure system settings");
		System.out.println("(4) Log out");
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
	
	public void adminMovieControl(){
		do {
			System.out.println(" ===== Add/Update/Remove movie from movie listing =====");
			System.out.println("Select option: ");
			System.out.println("(1) Add a movie");
			System.out.println("(2) Update a movie's details"); //ellen this option needs to allow admin to update any kind of movie attributes like cast, movietype etc
			System.out.println("(3) Return to admin main menu");
			Scanner sc = new Scanner (System.in);
			int choice = sc.nextInt();
			switch (choice){
				case 1: this.addMovie(); break;
				case 2: this.updateMovieDetails(); break; //ellen need fill up this method with the code
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
		System.out.print("Movie ID: "); String movieid = sc.nextLine();
		System.out.print("Movie title: "); String movietitle = sc.nextLine();
		System.out.println("Movie type: "); MovieTypeEnum movietype = this.getInputMovieType();
		System.out.print("Movie duration: "); int movieduration = sc.nextInt();sc.nextLine();
		System.out.print("Movie status:");
		StatusEnum moviestatus = this.getInputStatus();       
        System.out.print("Directed by: "); String moviedirector = sc.nextLine();
		System.out.println("Enter Cast Names: ");
		ArrayList<String> moviecastlist = new ArrayList<String>();
		while(sc.hasNextLine()){
			moviecastlist.add(sc.next());
	      }
        System.out.println("Synopsis: "); String moviesynopsis = sc.nextLine();
        Movie movietoadd = new Movie (movieid, movietitle, movieduration, moviestatus, movietype, moviedirector, moviecastlist, moviesynopsis); 
	}
	
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
	
	public MovieTypeEnum getInputMovieType() {
		System.out.println("(1) 3D");
		System.out.println("(2) Blockbuster");
		System.out.println("(3) Digital");
		System.out.println("Enter movie type: ");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		switch(i) {
			case 1: return MovieTypeEnum._3D;
			case 2: return MovieTypeEnum.BB;	
			case 3: return MovieTypeEnum.digital;
			default: { System.out.println("Invalid choice! Set to Digital by default."); return MovieTypeEnum.digital; }	
		}
	}
	
	public void updateMovieDetails(){
		//ellen need fill up code over here
		//need a switch statement here. one of the switch options should include updateMovieStatus method (found right below this method)
		Scanner sc = new Scanner(System.in);
		Movie selectedmovie=null;
		do {
			System.out.println("Enter MovieID to update: ");
			String movieid = sc.next();
			try {
				selectedmovie = Movie.searchMovie(movieid);
			}catch(Exception e) {
				System.out.println("Movie not found");;
			}
		} while(selectedmovie!=null);
		
		
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
		boolean quit = false;
		while (quit = false) {
			switch(detailChoice) {
			case 1:
				System.out.println("Enter New Movie Title:");
				String newMovieTitle = sc.next();
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
				String newMovieDirector = sc.next();
				selectedmovie.setMovieDirector(newMovieDirector);
				System.out.println("Movie Director has been updated");
				break;
			case 5:
				System.out.println("Current Cast Input:");
				for (int i = 0; i < selectedmovie.getCast().size(); i++){
					System.out.println((i+1) + ". " + selectedmovie.getCast().get(i));
				}
				System.out.println("(1) Update Individual Cast Member:");
				System.out.println("(2) Re-enter All Cast Members:");
				System.out.println("Enter Choice:");
				int castChoice = sc.nextInt();
				switch (castChoice) {
				case 1:
					updateIndividualCast(selectedmovie);
					break;
					
				case 2:
					System.out.println("Enter Cast Names:");
					ArrayList<String> moviecastlist = new ArrayList<String>();
					while(sc.hasNextLine()){
						moviecastlist.add(sc.next());
				      }
					selectedmovie.setMovieCast(moviecastlist);
					break;
				}
				
				break;
			case 6:
				System.out.println("Enter New Movie Synopsis:");
				String newMovieSynopsis = sc.next();
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
	
	}
	
	public static void updateIndividualCast(Movie selectedmovie) {
		Scanner sc = new Scanner(System.in);
		char ans;
		do {
			System.out.println("Enter Index of Cast Name to be updated:");
			int index = sc.nextInt();
			System.out.println("(1) Delete Cast");
			System.out.println("(2) Re-enter Cast Name");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				selectedmovie.getCast().remove(index-1);
				System.out.println("Cast has been deleted");
				break;
			case 2:
				System.out.println("Enter Updated Cast Name: ");
				String castName = sc.next();
				selectedmovie.getCast().set(index-1, castName);
				System.out.println("Cast Name has been updated ");
				break;
			}
			System.out.println("Updated Cast Input:");
			for (int i = 0; i < selectedmovie.getCast().size(); i++){
				System.out.println((i+1) + ". " + selectedmovie.getCast().get(i));
			}
			System.out.println("Update another individual cast member? (Y/N)");
			ans = sc.next().charAt(0);
			
		} while (ans != 'n' || ans != 'N');
		
	}

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
	
	public void adminShowTimeControl() throws Exception{
		do {
			System.out.println(" ===== Add/Update/Remove Showtimes =====");
			System.out.println("Select option: ");
			System.out.print("(1) Add showtime");
			System.out.println("(2) Remove showtime");
			System.out.println("(3) Return to admin main menu");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
		
			switch(choice){
				case 1: this.updateShowTime(1); break;
				case 2: this.updateShowTime(2); break;
				case 3: { System.out.println("Returning to admin main menu..."); return; }
				default: { System.out.println("Invalid choice! Going to admin main menu..."); return; }
			}
		} while(true);
	}
	
	public void updateShowTime(int i) throws Exception{
		if (i == 1)
			System.out.println(" ===== Add Showtime =====");
		else if (i == 2)
			System.out.println(" ===== Remove Showtime =====");
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		//need to double check this line below
		Cineplex [] cineplexes = GoldenVillage.getCineplexes();
		System.out.println("Select cineplex");
		for (int j = 0; j < cineplexes.length; j++)
			System.out.println("(" + j+1 + ") " + cineplexes[j].getCineplexName());
		int cineplexchoice = sc.nextInt();
		String cineplexname = cineplexes[cineplexchoice - 1].getCineplexName();
		String cineplexcode = cineplexes[cineplexchoice - 1].getCineplexCode();
		Cinema [] tempcinemaarray = cineplexes[cineplexchoice - 1].getCinemas();
			
		System.out.println("Select cinema");
		for (int j = 0; j < tempcinemaarray.length ; j++)
			System.out.println("(" + j+1 + ")" + tempcinemaarray[j].getCinemaCode());
		int cinemachoice = sc.nextInt();
		String cinemacode = tempcinemaarray[cinemachoice - 1].getCinemaCode();
		Timetable [] tempcalendararray = tempcinemaarray[cinemachoice - 1].getCalendar();
		
		Timetable tt = null;
		String date;
		do {
		System.out.println("Select date:");
		System.out.println("Enter day (XX) :");
		int day = sc.nextInt();
		System.out.println("Enter month (XX) :");
		int month = sc.nextInt();
		System.out.println("Enter year (XXXX) :");
		int year = sc.nextInt();
		date = String.format("%02d-%d-%d",day,month,year);
		
		try {
			tt = Timetable.getTimetableByDate(date);
		}catch(Exception e) {
			System.out.println("Timetable not found");
		}
		}while(tt!=null);
		
		tt.displaySchedule(); //displays schedule for a particular day
		Movie moviechoice = null;
		do {
			System.out.println("Enter movieID: ");
			String movieid = sc.next();
			try {
				moviechoice = Movie.searchMovie(movieid);
			}catch(Exception e) {
				System.out.println("Movie not found");
			}
		} while(moviechoice!=null);
	
		System.out.println(moviechoice.getTitle() + "'s schedule on " + date);
		System.out.println("Select timeslot:");
		int starttime = sc.nextInt();
		ShowTime st_to_add = new ShowTime (moviechoice.getTitle(), cineplexname, cineplexcode, cinemacode, date, starttime, tempcinemaarray[cinemachoice - 1].getNumberOfRows(), tempcinemaarray[cinemachoice - 1].getNumberOfCols());
		boolean available;
		if (i == 1){ //if admin wanted to add showtime		
	
			available = tt.addShowTimeToSchedule(moviechoice, starttime);
			if (available == true)
				moviechoice.addShowTimeToMovie(st_to_add);
		}
		else if (i == 2) { //if admin wanted to remove showtime

			available = tt.removeShowTimeFromSchedule(moviechoice, starttime);
			if (available==true)
			moviechoice.removeShowTimeFromMovie(st_to_add);
		}
	}
	
	public void adminSystemControl() throws Exception{
		System.out.println(" ===== Settings ===== ");
		System.out.println("Select option: ");
		System.out.println("(1) Add holiday");
		System.out.println("(2) Remove Holiday");
		System.out.println("(3) Clear all holidays");
		System.out.println("(4) Update pricing policy");
		System.out.println("(5) Return to admin main menu");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		do{
			switch(choice){
				case 1: addHoliday(); break; //ellen need create method for this
				case 2: removeHoliday();break;
				case 3: clearHolidays(); break; //ellen need create method for this
				case 4: updatePrices(); break; //ellen need create method for this
				case 5: { System.out.println("Returning to admin main menu..."); return; }
				default: { System.out.println("Invalid choice! Returning to admin main menu"); return; }
			}
		} while (true);
	}
	
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
		String date = String.format("%02d-%d-%d",day,month,year);
		if (Timetable.getTimetableByDate(date).getDayType() != DayTypeEnum.PH) {
			PriceSetting.addPublicHol(date);
			System.out.println(date +"set as Public Holiday");
			Timetable.getTimetableByDate(date).setPublicHoliday(DayTypeEnum.PH);
		}
		else 
			System.out.println("Date is already set as a Holiday");
	
	
		//yet to do

		//ellen help
	}
	
	public void removeHoliday() throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println(" ===== Remove Holiday ===== ");
		System.out.println("Enter a date to enter as holiday");
		System.out.println("Enter Day (XX) : ");
		int day = sc.nextInt();
		System.out.println("Enter Month (XX) : ");
		int month = sc.nextInt();
		System.out.println("Enter Year (XXXX) : ");
		int year = sc.nextInt();
		String date = String.format("%02d-%d-%d",day,month,year);
		if (PriceSetting.getPublicHol().contains(date)) {
			Timetable.getTimetableByDate(date).setPublicHoliday(DayTypeEnum.Weekday);
		}
		else 
			System.out.println("Date entered is not set as public holiday");
	}
	
	public void clearHolidays() throws Exception{
		System.out.println(" ===== Clear Holiday ===== ");
		//ellen help. this method supposed to reset all public holidays back to their normal days. if a public holiday is on a weekend already it needs to be reset to a weekend too.
		//cannot change everyday into weekday by accident take note
		
		for (int i=0; i<PriceSetting.getPublicHol().size();i++) {
			Timetable.getTimetableByDate(PriceSetting.getPublicHol().get(i)).setPublicHoliday(DayTypeEnum.Weekday);
		}
			PriceSetting.clearPublicHol();
		System.out.println("Public Holidays all rest back to their respective day type.");
	}
	
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
			System.out.println("(8) Quit");
			System.out.println("Enter Price Category to be updated: ");
			int catChoice = sc.nextInt();
			switch (catChoice) {
			case 1:
				System.out.println("Enter new price for Adult Category");
				double adultPrice = sc.nextInt();
				PriceSetting.setTPAdult(adultPrice);
				System.out.println("Adult Category price has been updated to " +PriceSetting.getTPAdult());
				break;
			case 2:
				System.out.println("Enter new price for Child Category");
				double childPrice = sc.nextInt();
				PriceSetting.setTPChild(childPrice);
				System.out.println("Child Category price has been updated to " +PriceSetting.getTPChild());
				break;
			case 3:
				System.out.println("Enter new price for Student Category");
				double studentPrice = sc.nextInt();
				PriceSetting.setTPStudent(studentPrice);
				System.out.println("Student Category price has been updated to " +PriceSetting.getTPStudent());
				break;
			case 4:
				System.out.println("Enter new price for Senior Category");
				double seniorPrice = sc.nextInt();
				PriceSetting.setTPSenior(seniorPrice);
				System.out.println("Senior Category price has been updated to " +PriceSetting.getTPSenior());
				break;
			case 5:
				System.out.println("Enter new price for Premium 3D Category");
				double threeDPrice = sc.nextInt();
				PriceSetting.setTP3D(threeDPrice);
				System.out.println("Premium 3D Category price has been updated to " +PriceSetting.getTP3D());
				break;
			case 6:
				System.out.println("Enter new price for Premium Blockbuster Category");
				double BBPrice = sc.nextInt();
				PriceSetting.setTPBB(BBPrice);
				System.out.println("Premium Blockbuster Category price has been updated to " +PriceSetting.getTPBB());
				break;
			case 7:
				System.out.println("Enter new price for Premium Holiday Category");
				double holidayPrice = sc.nextInt();
				PriceSetting.setTPAdult(holidayPrice);
				System.out.println("Premium Holiday Category price has been updated to " +PriceSetting.getTPHoliday());
				break;
			case 8:
				quit = true;
				break;
			}
		}
	}
}
	static class InvalidChoice extends Exception{
		public InvalidChoice () {
		super ("Invalid choice!");
		}
	}
	
}
