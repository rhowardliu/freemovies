import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ShowTime implements Serializable, Comparable <ShowTime>{
	private static final long serialVersionUID = 4963733738112261278L;
	private Integer time;
	private Movie movie;
	private String location;
	private Ticket [][] seatLayout;
	private Timetable timetable;
	public static List<ShowTime> showtimelist = new ArrayList<ShowTime>();
	public static final File showtimeDatabase = new File ("ShowTime.txt");
	
	
	public ShowTime(Timetable timetable, int Time, String location, Movie movie) {
		this.timetable = timetable;
		time = Time;
		this.movie = movie;
		this.location = location;
		int i,j;
		for (i=1; i<10; i++) {
			for (j=1; j<18; j++) 
				seatLayout [i][j] = new Ticket(i,j, this);
		}
		showtimelist.add(this);
	}
	

public Calendar getDate() {
	return timetable.getDate();
}


public Integer getShowDateTime() {
	return time;
}


public String getLocation(){
	return this.location;
}

public void showSeatLayout() {
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
	
	public Movie getMovie() {
		return movie;
	}

	//need to change uml diagram. void, not Ticket.
	public void bookTicket() { 
		//first algo ask the user which seat he wants
		Scanner sc = new Scanner(System.in);
		System.out.print("Row: "); int row = sc.nextInt();
		System.out.print("Column: "); int col = sc.nextInt();
		//check if the requested seat is already taken
		if (seatLayout[row][col].isBooked() == true) {
			System.out.println("Seat is already taken!");
			return;
		}
		else {
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
			//may need to change Movie to movie cos of the static method problem
			seatLayout[row][col].setPrice(PriceSetting.calPrice(movie.getMovieType(), ticketage, this.timetable.getDayType()));
			System.out.println("Ticket booked successfully!");
			printTicketShowTimeDetails(row, col);
			System.out.println("Transaction ID is " +seatLayout[row][col].getTransactionID());
			Ticket.ticketlist.add(seatLayout[row][col]);
		}
		
	}
	
	public void printTicketShowTimeDetails(int row, int col){
		System.out.println("==========");
		System.out.println("Booking details:");
		System.out.println("Movie: " + this.movie.getTitle());
		System.out.println("Date & Time: " + getShowDateTime() + "(" + seatLayout[row][col].getDayType() + ")");
		System.out.println("Seat Row: " + seatLayout[row][col].getSeatRow() + "\tSeat Column: " + seatLayout[row][col].getSeatCol());
		System.out.println("Location: " + this.location);
		System.out.println("Price: " + seatLayout[row][col].getPrice());
		System.out.println("==========");
	}

@Override
public int compareTo(ShowTime other) {
	int compared = this.getShowDateTime().compareTo(other.getShowDateTime());
	return compared;
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




