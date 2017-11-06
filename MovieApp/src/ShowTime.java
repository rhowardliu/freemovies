import java.time.LocalDateTime;


public class ShowTime {
private LocalDateTime dateTime;
private Movie movie;
private String location;
private DayTypeEnum dayType;

enum DayTypeEnum {
	Weekdays, Weekends, PH
}
public ShowTime(LocalDateTime timeDate, Movie movieName, String place, DayTypeEnum daytype) {
	dateTime = timeDate;
	movie = movieName;
	location = place;
	dayType = daytype;
	
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
			Ticket tic = new Ticket(i,j); 
			if (tic.isBooked() == false) {//not booked
				System.out.print(" O ");
			}
			else 
				System.out.print(" X ");
		}
		System.out.print("   ");
		j++;
		for (j=9;j<17;j++) {
			Ticket tic = new Ticket(i,j); 
			if (tic.isBooked() == false) {//not booked
				System.out.print(" O ");
			}
			else 
				System.out.print(" X ");
		}
		System.out.print("\n");
		
	}
}
public Ticket bookTicket(int row, int col) { //user should input seat they want to book
	Ticket tix = new Ticket(row, col);//should have a function like setBooked() or sth
	System.out.println("Ticket Seat is " +tix.seatRow().seatColumn());
	System.out.println("Ticket Price is " +tix.price());
	tix.setBooked(true);
	System.out.println("Ticket has been booked!");
	
}
public DayTypeEnum displayDayType () {
	return dayType;
}
public String displayLocation() {
	return location;
}

}
