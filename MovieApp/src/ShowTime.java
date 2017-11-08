import java.time.LocalDateTime;

public class ShowTime {
private LocalDateTime dateTime;
private Movie movie;
private String location;
private Ticket [][] seatLayout;

public ShowTime(LocalDateTime timeDate, Movie movieName, String place) {
	dateTime = timeDate;
	movie = movieName;
	location = place;
	int i,j;
	for (i=0; i<9; i++) {
		for (j=0; j<17; j++) {
			seatLayout [i][j] = new Ticket(i,j, this);
		}
	}
	
}

public LocalDateTime getShowDateTime() {
	return dateTime;
}

public Movie getMovie(){
	return this.movie;
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
		count++;
	}
	
	for (i=1; i<10; i++) {
		System.out.print((char)(al));//prints character vertically
		al++;
		for (j=1;j<9;j++) {
			
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
			Ticket tic = new Ticket(i,j, this); 
			if (seatLayout[i][j].bookedStatus() == false) {//not booked
				System.out.print(" O ");
			}
			else 
				System.out.print(" X ");
		}
		System.out.print("\n");
		
	}
}

//need to change uml diagram. void, not Ticket.
public void bookTicket(int row, int col) { //user should input seat they want to book, 
	Ticket tix = new Ticket(row, col, this);//not too sure if we gonna create array for this or what
	System.out.println("Ticket Seat is " +tix.getSeatRow() +tix.getSeatCol());
	System.out.println("Ticket Price is " +tix.getPrice());
	tix.bookTicket();;
	System.out.println("Ticket has been booked!");
	System.out.println("Transaction ID is " +tix.getTransactionID());
	
}

public String displayLocation() {
	return location;
}


}


