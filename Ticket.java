import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


enum AgeCatEnum{
	student, adult, child, senior
}

public class Ticket implements Serializable, dataStorage {

	private static final long serialVersionUID = 9031951939120214545L;
	//class attributes
	private int seatrow, seatcol;
	private String transactionID;
	private AgeCatEnum agecat;
	private boolean isBooked;
	private double price;
	private ShowTime show;
	public static List<Ticket> ticketlist = new ArrayList<Ticket>();
	public static final File ticketsDatabase = new File ("Ticket.txt");
	private ShowTime showtime;
	
	//class methods
	public Ticket(int seatrow, int seatcol, ShowTime showtime){
		this.showtime = showtime;
		this.seatrow = seatrow;
		this.seatcol = seatcol;
		this.transactionID = null;
		this.agecat = null;
		this.isBooked = false;
		this.price = 0;
		ticketlist.add(this);
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setAge(AgeCatEnum age){
		this.agecat = age;
	}
	
	public void setTransactionID(String transid){
		this.transactionID = transid;
	}
	
	public void bookTicket(){
		this.isBooked = true;
	}
	
	public boolean bookedStatus() {
		return isBooked;
	}
	
	public int getSeatRow(){
		return this.seatrow;
	}
	
	public int getSeatCol(){
		return this.seatcol;
	}
	
	public AgeCatEnum getageCat() {
		return agecat;
	}
	
	public String getTransactionID(){
		return this.transactionID;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	//will need to change code for this
	/*public String getShowtime(){
		return this.show;
	}*/
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(ticketsDatabase);
		ticketlist = or.initialiseDataList(ticketlist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(ticketsDatabase);
		ow.updateDataList(ticketlist);
	}
	
	public void printTicketShowTimeDetails(){
		System.out.println("Date: " + showtime.getShowDateTime() + "(" + showtime.getDayType() + ")");
		System.out.println("Movie: " + showtime.getMovie());
		System.out.println("Location: " + showtime.getLocation());
	}

	
}
