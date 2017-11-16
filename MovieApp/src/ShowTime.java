import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * ShowTime class is created whenever the admin adds a ShowTime to a cinema
 * ShowTime class creates an array of Ticket objects automatically when it is constructed
 * ShowTime contains information of the movie title it it is screening, show date, show start time, and showtime location
 * @author user
 *
 */
public class ShowTime implements Serializable {

	private static final long serialVersionUID = 2001719944845781739L;
	private String movietitle;
	private String cineplexname;
	private String cineplexcode;
	private String cinemacode;
	private int cinemarows;
	private int cinemacols;
	private String movieID;
	private CinemaTypeEnum cinematype;
	private String date;
	private DayTypeEnum daytype;
	private int starttime;
<<<<<<< HEAD
	private Ticket [][] tickets;
=======
	private Ticket [][] tickets = new Ticket[40][40];
>>>>>>> branch 'master' of https://github.com/rhowardliu/freemovies.git
	public static List<ShowTime> showtimelist = new ArrayList<ShowTime>();
	public static final File showtimeDatabase = new File ("ShowTime.tmp");
	
	public ShowTime(String movietitle, String movieID, String cineplexname, String cineplexcode, String cinemacode, String date, int starttime, 
			int cinemarows, int cinemacols, CinemaTypeEnum cinematype) throws Exception {
		this.movietitle = movietitle;
		this.movieID = movieID;
		this.cineplexname = cineplexname;
		this.cineplexcode = cineplexcode;
		this.cinemacode = cinemacode;
		this.cinematype = cinematype;
		this.date = date;
		this.daytype = null;
		this.starttime = starttime;
		this.cinemarows = cinemarows;
		this.cinemacols = cinemacols;

		
		
		showtimelist.add(this);
	}
	public Ticket[][] initialiseTickets() {
		for (int i=0; i<cinemarows; i++) {
			for (int j=0; j<cinemacols; j++) 
				tickets[i][j] = new Ticket(movietitle,movieID,date,(i+1),(j+1), false);
		}
		return tickets;
	}
	
	public void updateShowTime() throws Exception {
		this.daytype = Timetable.getTimetableByDate(date).getDayType();
	}

	public String getMovieID() {
		return this.movieID;
	}
	public Integer getShowTimeStartTime() {
		return this.starttime;
	}
	
	public String getShowTimeMovieTitle(){
		return this.movietitle;
	}
	
	public String getShowTimeDate(){
		return this.date;
	}

	public String getCineplexcode() {
		return cineplexcode;
	}

	public String getCinemacode() {
		return cinemacode;
	}

	public void showSeatLayout() {
		initialiseTickets();
		//****this one needd to change cos number of rows and cols are not assumed anymore
		//****its based on the cinema selected instead
		
		//assume 9 rows, 8x2 columns with an aisle in between 
		int al = 65;
		System.out.print("   ");
		int i;
		for (i = 1; i <= cinemacols/2 ;i++) {
			System.out.printf("%02d ", i);
		}
		System.out.print("  ");
		
		while (i<=cinemacols) {
			System.out.printf("%02d ", i);
			i++;
		}
		System.out.print("\n");
		int k =0;
		for (int j = 0; j<cinemarows ; j++) {
			System.out.print((char)(al));
			System.out.print("  ");
			for (k = 0; k < cinemacols/2 ;k++) {
				
				if (tickets[j][k].isBooked() == false)//not booked
					System.out.print(" O ");
				else 
					System.out.print(" X ");
			}
			System.out.print("  ");
			
			while (k<cinemacols) {
			
				if (tickets[j][k].isBooked() == false)//not booked
					{
					System.out.print(" O ");
					}
				else 
					System.out.print(" X ");
				k++;
			}
			System.out.print("  ");
			System.out.print((char)(al));
			al++;
			System.out.print("\n");
		}
			System.out.print("\n");
			System.out.print("  ");
		for (int m = 0; m<(cinemacols*3)/2 ; m++) {
			System.out.print(" ");
		}
		System.out.println("SCREEN");
	}
	
	
	public String getMovieTitle() {
		return this.movietitle;
	}
	
	/**
	 * Method request user to input which seat row and column he wants, along with the age category of the person he is booking for
	 * Method will calculate the price of the ticket based on the cinema type, movie type, day type and the age of the person 
	 * After which, after the movie goer confirms booking of Ticket, a transaction id will be generated and the ticket purchase will be recorded under the user's transaction history
	 */
	//need to change uml diagram. void, not Ticket.
	public Ticket bookTicket() { 
		//first algo ask the user which seat he wants
		System.out.print("\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter desired seat");
		int row = 0;
		do{
			System.out.print("Row (Enter Uppercase): "); 
			row = ((int)sc.next().charAt(0)) -65;
			if (row <0 || row >25) {
				System.out.println("Enter Upper-case only! Please re-enter!");
			}
			
		}while (row <0 || row > 25);
		System.out.print("Column: "); int col = (sc.nextInt() - 1);
		
		//check if the requested seat is already taken
		if (tickets[row][col].isBooked()==true){
			System.out.println("Seat is already taken.");
			return null;
		}
		else{
			//if the requested seat is free, ask for age category for the ticket
			System.out.println("Select Age Category: (1) Adult (2) Child (3) Student (4) Senior");
			AgeCatEnum ticketage = AgeCatEnum.adult;
			int choice = sc.nextInt();
			switch (choice){
				case 1: ticketage = AgeCatEnum.adult; break;
				case 2: ticketage = AgeCatEnum.child; break;
				case 3: ticketage = AgeCatEnum.student; break;
				case 4: ticketage = AgeCatEnum.senior; break;
				default: System.out.println("Invalid choice! Adult Category set by default!"); break;
			}
			//setting price using Ticket class's setPrice method and PriceSetting calPrice method
			
			Movie movie;
			try {
				movie = Movie.searchMovie(movieID);
			} catch (Exception e) {
				System.out.println("Movie ID not found");
				return null;
			}			
			try {
				this.daytype=Timetable.getTimetableByDate(date).getDayType();
			} catch (Exception e) {
				System.out.println("Timetable not found");
				return null;
			}
			tickets[row][col].setPrice(PriceSetting.calPrice(movie.getMovieType(), ticketage, daytype,cinematype));
			//write buy more tickets?
			//display info first then
			//write confirm payment?
			//if cancel, go back to main list
			//if confirm then book, else, return back to screen where it shows buy more tickets?
			tickets[row][col].setBooked(true);
			System.out.println("Ticket booked successfully!");
			Calendar now = Calendar.getInstance();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddhhmm");
			String timestamp = dateFormatter.format(now.getTime());
			printTicketShowTimeDetails(row, col);
			tickets[row][col].setTransactionID(this.cineplexcode.concat(this.cinemacode.concat(timestamp)));
			System.out.println("Transaction ID is " +tickets[row][col].getTransactionID());
			return tickets[row][col];
			//need a method to add the transaction to the user's transactionhistory
		}
	}
	
	/**
	 * Prints out booking details, including price of ticket
	 * @param row
	 * @param col
	 */
	public void printTicketShowTimeDetails(int row, int col){
		System.out.println("==========");
		System.out.println("Booking details:");
		System.out.println("Movie: " + this.movietitle);
		System.out.println("Date: " + this.getShowTimeDate() + "(" + tickets[row][col].getDayType() + ")");
		System.out.println("Seat Row: " + tickets[row][col].getSeatRow() + "\tSeat Column: " + tickets[row][col].getSeatCol());
		System.out.println("Location: " + this.cineplexname);
		System.out.println("Price: " + tickets[row][col].getPrice());
		System.out.println("==========");
	}

	
//	static Comparator<ShowTime> getShowTimeComparator(){
//		return new Comparator<ShowTime>() {
//			public int compare(ShowTime o1, ShowTime o2) {
//				
//				if (o1.getMovieTitle()==o2.movietitle && o1.cineplexname==o2.cineplexname && o1.cineplexcode==o2.cineplexcode
//						&& o1.cinemacode==o2.cinemacode && o1.date ==o2.date && o1.starttime == o2.starttime)
//					return 0;
//				else return 1;
//			}
//		};	
//	}

	static Comparator<ShowTime> getDateComparator(){
		return new Comparator<ShowTime>() {
			public int compare(ShowTime o1, ShowTime o2) {

				String dd1=o1.getShowTimeDate().substring(0, 2);
				String mm1=o1.getShowTimeDate().substring(3,5);
				String yy1=o1.getShowTimeDate().substring(6);
				
				String dd2=o2.getShowTimeDate().substring(0, 2);
				String mm2=o2.getShowTimeDate().substring(3,5);
				String yy2=o2.getShowTimeDate().substring(6);
				int compared = yy1.compareTo(yy2);
				if (compared == 0) {
					compared=mm1.compareTo(mm2);
					if (compared==0) {
						compared=dd1.compareTo(dd2);
					}
				}
				
				return compared;
			}
		};	
	}
	
	
	static Comparator<ShowTime> getTimeComparator(){
		return new Comparator<ShowTime>() {
			public int compare(ShowTime o1, ShowTime o2) {
					return o1.getShowTimeStartTime().compareTo(o2.getShowTimeStartTime());
			}
		};	
	}
	
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(showtimeDatabase);
		showtimelist = or.initialiseDataList(showtimelist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(showtimeDatabase);
		ow.updateDataList(showtimelist);
	}
	


}

