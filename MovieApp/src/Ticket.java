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
	private ShowTime show;
	
	//class methods
	public Ticket(int seatrow, int seatcol){
		this.seatrow = seatrow;
		this.seatcol = seatcol;
		this.transactionID = null;
		this.agecat = null;
		this.isBooked = false;
		this.price = 0;
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
}
