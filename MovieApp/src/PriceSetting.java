
public class PriceSetting {
	
	private static double ticketPriceAdult = 8.5;
	private static double ticketPriceChild = 6.0;
	private static double ticketPriceStudent = 7.0;
	private static double ticketPriceSenior = 4.0;
	private static double ticketPremium3D = 3.0;
	private static double ticketPremiumBB = 2.0;
	private static double ticketPremiumHol = 4.0;
	
	public static double calPrice(MovieTypeEnum movietype, AgeCatEnum agecat,
	DayTypeEnum daytype) {
		//Initialize price
		double price = 0;
		
		//calculate price base on whether it is weekday or PH
		if (daytype == DayTypeEnum.PH)
			price += ticketPremiumHol;
		
		//calculate price base on movie type
		if (Movie.getMovieType() == MovieTypeEnum._3D)
			price += ticketPremium3D;
		else
			price += ticketPremiumBB;
		
		//calculate price base on age category
		if (agecat == AgeCatEnum.student)
			price += ticketPriceStudent;
		else if (agecat == AgeCatEnum.adult) 
			price += ticketPriceAdult;
		else if (agecat == AgeCatEnum.child)
			price += ticketPriceChild;
		else if (agecat == AgeCatEnum.student)
			price += ticketPriceSenior;
		
		return price;
	}
		
	//public methods here is stackable, e.g. p.getPremium3D(p.getAdultPrice());
	public static double getAdultPrice() {
		return ticketPriceAdult;
	}
	public static double getChildPrice() {
		return ticketPriceChild;
	}
	public static double getSeniorPrice() {
		return ticketPriceSenior;
	}
	public static double getStudentPrice() {
		return ticketPriceStudent;
	}
	public static double getPremium3D(double price) {
		return price + ticketPremium3D;
	}
	public static double getPremiumBB(double price) {
		return price + ticketPremiumBB;
	}
	public static double getPremiumHol(double price) {
		return price + ticketPremiumHol;
	}
	
	// below is update methods for admin
	public static void setTPAdult(double newprice) {
		ticketPriceAdult = newprice;
	}
	public static void setTPChild(double newprice) {
		ticketPriceChild = newprice;
	}
	public static void setTPSenior(double newprice) {
		ticketPriceSenior = newprice;
	}
	public static void setTPStudent(double newprice) {
		ticketPriceStudent = newprice;
	}
	public static void setTP3D(double newprice) {
		ticketPremium3D = newprice;
	}
	public static void setTPBB(double newprice) {
		ticketPremiumBB = newprice;
	}
	public static void setTPHol(double newprice) {
		ticketPremiumHol = newprice;
	}

}
