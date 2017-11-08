//import java.time.LocalDateTime;

public class ShowTime {
//private LocalDateTime dateTime;
private int time;
private Movie movie;
private String location;
private Ticket [][] seatLayout;
private Integer[] customerTimetable = new Integer[12];

public ShowTime(int Time, Movie movieName, String place) {
	time = Time;
	movie = movieName;
	location = place;
	int i,j;
	for (i=1; i<10; i++) {
		for (j=1; j<18; j++) {
			seatLayout [i][j] = new Ticket(i,j);
		}
	}
	
}

public int getShowDateTime() {
	return time;
}

public Movie getMovie(){
	return this.movie;//need to change to movieID when howard is done
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

public void displayShowTimes(Timetable t){
	int i=0, j, k;
	Integer[] timetable = t.getTimetable();
	if (timetable == null){
		System.out.println("No Showtime available");
	}
	else 
		for (j=0;j<24;j++){
			if (timetable[j] != null && timetable[j] == null){
				customerTimetable[i] = timetable[j];
				i++;
			}
			else if (timetable[j] != null && timetable[j] != customerTimetable[i-1]){
				customerTimetable[i] = timetable[j];
				i++;
			}
			System.out.println("Show Timings are: ");
		for (k=0;k<i;k++){
			System.out.println((k+1) +". " + timetable[k]);
			
		}
	}
	
	
}

}


