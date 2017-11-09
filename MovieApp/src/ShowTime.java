import java.util.Calendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class ShowTime implements Comparable <ShowTime>{
	private String movietitle;
	private String cineplexname;
	private String cineplexcode;
	private String cinemacode;
	private String date;
	private int starttime;
	private Ticket [][] seatLayout;
	
	public ShowTime(String movietitle, String cineplexname, String cineplexcode, String cinemacode, String date, int starttime, int cinemarows, int cinemacols) {
		this.movietitle = movietitle;
		this.cineplexname = cineplexname;
		this.cineplexcode = cineplexcode;
		this.cinemacode = cinemacode;
		this.date = date;
		this.starttime = starttime;
		for (int i=1; i<cinemarows; i++) {
			for (int j=1; j<cinemacols; j++) 
				seatLayout [i][j] = new Ticket(i,j);
		}
	}

	public int getShowTimeStartTime() {
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

@Override
public int compareTo(ShowTime other) {
	//changed this to getShowTimeDate
	int compared = this.getShowTimeDate().compareTo(other.getShowTimeDate());
	return compared;
}


	
}




