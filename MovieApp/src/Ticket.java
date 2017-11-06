enum AgeCatEnum{
	student, adult, child, senior
}

public class Ticket {
	
	//class attributes
	private int seatrow, seatcol;
	private String transactionID;
	private AgeCatEnum agecat;
	private boolean isBooked;
	private double price;
	private Showtime show;
	
	//class methods
	public Ticket(int seatrow, int seatcol, Showtime show){
		this.seatrow = seatrow;
		this.seatcol = seatcol;
		this.transactionID = null;
		this.agecat = null;
		this.isBooked = false;
		this.price = 0;
		this.show = show;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public void bookTicket(){
		this.isBooked = true;
	}
}
