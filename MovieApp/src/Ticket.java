import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

enum AgeCatEnum{
	student, adult, child, senior
}

/**
 * Tickets created automatically when a ShowTime is created
 * When a MovieGoer books a Ticket through ShowTime, the Ticket will be assigned a price and will be assigned to the MovieGoer's transactionhistory
 * @author user
 *
 */
public class Ticket implements Serializable {

	private static final long serialVersionUID = 9031951939120214545L;

	private String userID;
	
	private String movietitle;
	private String movieID;
	private String date;
	private int seatrow, seatcol;
	private String transactionID;
	private AgeCatEnum agecat;
	private DayTypeEnum dayType;
	private boolean isBooked;
	private double price;
	public static List<Ticket> ticketlist = new ArrayList<Ticket>();
	public static final File ticketsDatabase = new File ("Ticket.tmp");
	
	public Ticket(String movietitle, String movieID, String date,int seatrow, int seatcol, boolean booked) {
		this.movietitle=movietitle;
		this.movieID = movieID;
		this.date=date;
		this.seatrow = seatrow;
		this.seatcol = seatcol;
		this.transactionID = null;
		this.agecat = null;
		this.isBooked = booked;
		this.price = 0;
	}
	
	public String getuserID() {
		return userID;
	}
	
	public void setAge(AgeCatEnum age){
		this.agecat = age;
	}
	
	public void setTransactionID(String transid){
		this.transactionID = transid;
	}
			
	public boolean isBooked() {
		return this.isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
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
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public DayTypeEnum getDayType() {
		return dayType;
	}
	

	public String getMovietitle() {
		return movietitle;
	}


	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(ticketsDatabase);
		ticketlist = or.initialiseDataList(ticketlist);
	}
	
	
	public String getDate() {
		return date;
	}
	
	public String getMovieID() {
		return movieID;
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(ticketsDatabase);
		ow.updateDataList(ticketlist);
	}
	
	public static List<Ticket> removeDuplicates(List<Ticket> listWithDuplicates) {
	    /* Set of all attributes seen so far */
	    Set<String> attributes = new HashSet<String>();
	    /* All confirmed duplicates go in here */
	    List duplicates = new ArrayList<Ticket>();

	    for(Ticket x : listWithDuplicates) {
	        if(attributes.contains(x.getMovietitle())) {
	            duplicates.add(x);
	        }
	        attributes.add(x.getMovietitle());
	    }
	    /* Clean list without any dups */
	    listWithDuplicates.removeAll(duplicates);
	    return listWithDuplicates;
	}


		
}
