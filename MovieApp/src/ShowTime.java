import java.time.LocalDateTime;


public class ShowTime {
private LocalDateTime dateTime;
private Movie movie;
private String location;
private DayTypeEnum dayType;
private Ticket [][] seatLayout;

enum DayTypeEnum {
	Weekdays, Weekends, PH
}

public ShowTime(LocalDateTime timeDate, Movie movieName, String place, DayTypeEnum daytype) {
	dateTime = timeDate;
	movie = movieName;
	location = place;
	dayType = daytype;
	int i,j;
	for (i=0; i<9; i++) {
		for (j=0; j<17; j++) {
			seatLayout [i][j] = new Ticket(i,j, Showtime);
		}
	}
	
}
public void switchDayType (DayTypeEnum type) {
	dayType = type;
}
public LocalDateTime getShowDateTime() {
	return dateTime;
}
public void showSeatLayout() {
	//assume 9 rows, 8x2 columns with an aisle in between 
	int i, j;
	for (i=0; i<9; i++) {
		for (j=0;j<8;j++) {
			 //need a function in Ticket to check if booked already, sth like bookedStatus()
			if (seatLayout[i][j].bookedStatus() == false) {//not booked
				System.out.print(" O ");
			}
			else 
				System.out.print(" X ");
		}
		System.out.print("   ");
		j++;
		for (j=9;j<17;j++) {
			Ticket tic = new Ticket(i,j); 
			if (seatLayout[i][j].bookedStatus() == false) {//not booked
				System.out.print(" O ");
			}
			else 
				System.out.print(" X ");
		}
		System.out.print("\n");
		
	}
}
public Ticket bookTicket(int row, int col) { //user should input seat they want to book
	Ticket tix = new Ticket(row, col);//not too sure if we gonna create array for this or what
	System.out.println("Ticket Seat is " +tix.getSeatRow() +tix.getSeatCol());
	System.out.println("Ticket Price is " +tix.getPrice());
	tix.bookTicket();;
	System.out.println("Ticket has been booked!");
	System.out.println("Transaction ID is " +tix.getTransactionID());
	
}
public DayTypeEnum displayDayType () {
	return dayType;
}
public String displayLocation() {
	return location;
}

}
