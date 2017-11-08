import java.util.Calendar;

//import java.time.LocalDateTime;

public class ShowTime implements Comparable<ShowTime>{
//private LocalDateTime dateTime;
private Integer time;
private Movie movie;
private String location;
private Ticket [][] seatLayout;
private Calendar date;


public ShowTime(Calendar date, int time, String location, Movie movie) {
	this.date = date;
	this.time = time;
	this.movie = movie;
	this.location = location;
	int i,j;
	for (i=1; i<10; i++) {
		for (j=1; j<18; j++) {
			seatLayout [i][j] = new Ticket(i,j, this.movie);
		}
	}
	
}


public Calendar getDate() {
	return date;
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
			if (seatLayout[i][j].bookedStatus() == false) {//not booked
				System.out.print(" O ");
			}
			else 
				System.out.print(" X ");
		}
		System.out.print("\n");
		
	}
}

public Movie getMovie() {
	return movie;
}

//need to change uml diagram. void, not Ticket.
public void bookTicket(int row, int col) { //user should input seat they want to book, 
//not too sure if we gonna create array for this or what
	
	seatLayout[row][col].bookTicket();
	if (seatLayout[row][col].bookTicket() == true){
	printTicketShowTimeDetails(row, col);
	System.out.println("Transaction ID is " +seatLayout[row][col].getTransactionID());
	}
}

public void printTicketShowTimeDetails(int row, int col){
	System.out.println("Movie: " + getMovie());
	System.out.println("Date & Time: " + getShowDateTime() + "(" + seatLayout[row][col].getDayType() + ")");
	System.out.println("Ticket Seat is " +seatLayout[row][col].getSeatRow() +" , " +seatLayout[row][col].getSeatRow());
	System.out.println("Location: " + getLocation());
	System.out.println("Price: " + seatLayout[row][col].getPrice());
	}

@Override
public int compareTo(ShowTime other) {
	int compared = this.getShowDateTime().compareTo(other.getShowDateTime());
	return compared;
}




	
}




