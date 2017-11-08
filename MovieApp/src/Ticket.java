import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	
	//class methods
	public Ticket(int seatrow, int seatcol){
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
	
	
}
