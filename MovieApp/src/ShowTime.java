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
	private String dd;
	private String mm;
	private String yy;
	private String date;
	private DayTypeEnum daytype;
	private int starttime;
	private Ticket [][] seatLayout;
	public static List<ShowTime> showtimelist = new ArrayList<ShowTime>();
	public static final File showtimeDatabase = new File ("ShowTime.txt");
	
	public ShowTime(String movietitle, String cineplexname, String cineplexcode, String cinemacode, String date, int starttime, int cinemarows, int cinemacols) throws Exception {
		this.movietitle = movietitle;
		this.cineplexname = cineplexname;
		this.cineplexcode = cineplexcode;
		this.cinemacode = cinemacode;
		this.date = date;
		dd=date.substring(0, 2);
		mm=date.substring(3,5);
		yy=date.substring(6);
		this.starttime = starttime;
		for (int i=1; i<cinemarows; i++) {
			for (int j=1; j<cinemacols; j++) 
				seatLayout [i][j] = new Ticket(i,j);
		}
		
		this.daytype = Timetable.getTimetableByDate(date).getDayType();
		
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
	

	public void showSeatLayout() {
		//****this one needd to change cos number of rows and cols are not assumed anymore
		//****its based on the cinema selected instead
		
		//assume 9 rows, 8x2 columns with an aisle in between 
		int i, j, k, l=0, count=1, al=65;
		//prints no. in increasing order horizontally 
		while (l<2) {
			System.out.print(" ");
			for (k=0;k<8;k++) {
				System.out.print(count);
				count++;
		}
		
		for (i=1; i<10; i++) {
			System.out.print((char)(al));//prints character vertically
			al++;
			for (j=1;j<9;j++) {
				
				 //need a function in Ticket to check if booked already, sth like bookedStatus()
				if (seatLayout[i][j].isBooked() == false)//not booked
					System.out.print(" O ");
				else 
					System.out.print(" X ");
			}
			System.out.print("   ");
			j++;
			for (j=9;j<17;j++) {
				if (seatLayout[i][j].isBooked() == false)//not booked
					System.out.print(" O ");
				else 
					System.out.print(" X ");
			}
			System.out.print("\n");
			
		}
		}
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
	public void bookTicket() { 
		//first algo ask the user which seat he wants
		Scanner sc = new Scanner(System.in);
		System.out.print("Row: "); int row = sc.nextInt();
		System.out.print("Column: "); int col = sc.nextInt();
		//check if the requested seat is already taken
		if (seatLayout[row][col].isBooked() == false){
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
			//need to convert String movietitle into Movie movie here again
			seatLayout[row][col].setPrice(PriceSetting.calPrice(movie.getMovieType(), ticketage, this.timetable.getDayType()));
			//write buy more tickets?
			//display info first then
			//write confirm payment?
			//if cancel, go back to main list
			//if confirm then book, else, return back to screen where it shows buy more tickets?
			seatLayout[row][col].setBooked(true);
			System.out.println("Ticket booked successfully!");
			Calendar now = Calendar.getInstance();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMddhhmm");
			String timestamp = dateFormatter.format(now.getTime());
			printTicketShowTimeDetails(row, col);
			seatLayout[row][col].setTransactionID(this.cineplexcode.concat(this.cinemacode.concat(timestamp)));
			System.out.println("Transaction ID is " +seatLayout[row][col].getTransactionID());
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
		System.out.println("Date: " + this.getShowTimeDate() + "(" + seatLayout[row][col].getDayType() + ")");
		System.out.println("Seat Row: " + seatLayout[row][col].getSeatRow() + "\tSeat Column: " + seatLayout[row][col].getSeatCol());
		System.out.println("Location: " + this.cineplexname);
		System.out.println("Price: " + seatLayout[row][col].getPrice());
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
				int compared = o1.yy.compareTo(o2.yy);
				if (compared == 0) {
					compared=o1.mm.compareTo(o2.mm);
					if (compared==0) {
						compared=o1.dd.compareTo(o2.dd);
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

