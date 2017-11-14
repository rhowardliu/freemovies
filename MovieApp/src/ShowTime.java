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
	private Ticket [][] ticket;
	public static List<ShowTime> showtimelist = new ArrayList<ShowTime>();
	public static final File showtimeDatabase = new File ("ShowTime.txt");
	
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
		for (int i=1; i<cinemarows; i++) {
			for (int j=1; j<cinemacols; j++) 
				ticket [i][j] = new Ticket(movietitle,movieID,date,i,j);
		}
		
		showtimelist.add(this);
	}
	
	public void updateShowTime() throws Exception {
		this.daytype = Timetable.getTimetableByDate(date).getDayType();
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
		
		for (int j = 0; j<cinemarows ; j++) {
			System.out.print((char)(al));
			System.out.print("  ");
			for (i = 1; i <= cinemacols/2 ;i++) {
				if (ticket[i][j].isBooked() == false)//not booked
					System.out.print(" O ");
				else 
					System.out.print(" X ");
			}
			System.out.print("  ");
			
			while (i<=cinemacols) {
				if (ticket[i][j].isBooked() == false)//not booked
					System.out.print(" O ");
				else 
					System.out.print(" X ");
				i++;
			}
			System.out.print("  ");
			System.out.print((char)(al));
			al++;
			System.out.print("\n");
		}
			System.out.print("\n");
			System.out.print("  ");
		for (int k = 0; k<(cinemacols*3)/2 ; k++) {
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter desired seat: ");
		System.out.print("Row: "); int row = sc.nextInt();
		System.out.print("Column: "); int col = sc.nextInt();
		//check if the requested seat is already taken
		
		if (ticket[row][col].isBooked()==true){
			System.out.println("Seat is already taken.");
			return null;
		}
		else{
			//if the requested seat is free, ask for age category for the ticket
			System.out.println("Age Category: (1) Adult (2) Child (3) Student (4) Senior");
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
				movie = Movie.searchMovie(movietitle);
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
			ticket[row][col].setPrice(PriceSetting.calPrice(movie.getMovieType(), ticketage, daytype,cinematype));
			//write buy more tickets?
			//display info first then
			//write confirm payment?
			//if cancel, go back to main list
			//if confirm then book, else, return back to screen where it shows buy more tickets?
			ticket[row][col].setBooked(true);
			System.out.println("Ticket booked successfully!");
			Calendar now = Calendar.getInstance();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddhhmm");
			String timestamp = dateFormatter.format(now.getTime());
			printTicketShowTimeDetails(row, col);
			ticket[row][col].setTransactionID(this.cineplexcode.concat(this.cinemacode.concat(timestamp)));
			System.out.println("Transaction ID is " +ticket[row][col].getTransactionID());
			return ticket[row][col];
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
		System.out.println("Date: " + this.getShowTimeDate() + "(" + ticket[row][col].getDayType() + ")");
		System.out.println("Seat Row: " + ticket[row][col].getSeatRow() + "\tSeat Column: " + ticket[row][col].getSeatCol());
		System.out.println("Location: " + this.cineplexname);
		System.out.println("Price: " + ticket[row][col].getPrice());
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

