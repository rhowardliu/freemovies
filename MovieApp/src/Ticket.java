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

enum DayTypeEnum {
	Weekday, PH
}


public class Ticket implements Serializable, dataStorage {

	private static final long serialVersionUID = 9031951939120214545L;

	//class attributes
	private String userID;
	private int seatrow, seatcol;
	private String transactionID;
	private AgeCatEnum agecat;
	private DayTypeEnum dayType;
	private boolean isBooked;
	private double price;
	private ShowTime show;
	public static List<Ticket> ticketlist = new ArrayList<Ticket>();
	public static final File ticketsDatabase = new File ("Ticket.txt");
	private ShowTime showtime;
	
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
	
	public String getuserID() {
		return userID;
	}
	
	public void calPrice(){
		double price = 0;
		if (agecat == AgeCatEnum.student) {
			price = PriceSetting.getStudentPrice();
		}
		else if (agecat == AgeCatEnum.adult) {
			price = PriceSetting.getAdultPrice();
		}
		else if (agecat == AgeCatEnum.child) {
			price = PriceSetting.getChildPrice();
		}
		else if (agecat == AgeCatEnum.student) {
			price = PriceSetting.getSeniorPrice();
		}
		if (dayType == DayTypeEnum.PH) {
			price = PriceSetting.getPremiumHol(price);
		}
		if (Movie.getMovieType() == MovieTypeEnum._3D) {
			price = PriceSetting.getPremium3D(price);
		}
		else if (Movie.getMovieType() == MovieTypeEnum.BB) {
			price = PriceSetting.getPremiumBB(price);
		}
		
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
	

	public DayTypeEnum getDayType() {
		return dayType;
	}
	
	public void switchDayType (DayTypeEnum type) {
		dayType = type;
	}
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(ticketsDatabase);
		ticketlist = or.initialiseDataList(ticketlist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(ticketsDatabase);
		ow.updateDataList(ticketlist);

	}
	
	public void printTicketShowTimeDetails(){
		System.out.println("Date & Time: " + showtime.getShowDateTime() + "(" + getDayType() + ")");
		System.out.println("Movie: " + showtime.getMovie());
		System.out.println("Location: " + showtime.getLocation());
		System.out.println("Price: " + getPrice());
	}

	
}
