public class PriceSetting {
	

	private double ticketPriceAdult = 8.5;
	private double ticketPriceChild = 6.0;
	private double ticketPriceSenior = 4.0;
	private double ticketPremium3D = 3.0;
	private double ticketPremiumBB = 2.0;
	private double ticketPremiumHol = 4.0;
	
	//Constructor
	public PriceSetting() {	
	}
	

	//public methods here is stackable, e.g. p.getPremium3D(p.getAdultPrice());
	public double getAdultPrice() {
		return ticketPriceAdult;
	}
	public double getChildPrice() {
		return ticketPriceChild;
	}
	public double getSeniorPrice() {
		return ticketPriceSenior;
	}
	public double getPremium3D(double price) {
		return price + ticketPremium3D;
	}
	public double getPremiumBB(double price) {
		return price + ticketPremiumBB;
	}
	public double getPremiumHol(double price) {
		return price + ticketPremiumHol;
	}
	
	// below is update methods for admin
	public void setTPAdult(double newprice) {
		ticketPriceAdult = newprice;
	}
	public void setTPChild(double newprice) {
		ticketPriceChild = newprice;
	}
	public void setTPSenior(double newprice) {
		ticketPriceSenior = newprice;
	}
	public void setTP3D(double newprice) {
		ticketPremium3D = newprice;
	}
	public void setTPBB(double newprice) {
		ticketPremiumBB = newprice;
	}
	public void setTPHol(double newprice) {
		ticketPremiumHol = newprice;
	}
}
