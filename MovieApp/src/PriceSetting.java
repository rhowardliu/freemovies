import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PriceSetting {
	
	private static double ticketPriceAdult = 8.5;
	private static double ticketPriceChild = 6.0;
	private static double ticketPriceStudent = 7.0;
	private static double ticketPriceSenior = 4.0;
	private static double ticketPremium3D = 3.0;
	private static double ticketPremiumBB = 2.0;
	private static double ticketPremiumHol = 4.0;
	private static double ticketPlatinum = 2.0;
	private static List<String> publicHol = new ArrayList<String>();
	
	public static double calPrice(MovieTypeEnum movietype, AgeCatEnum agecat,
	DayTypeEnum daytype, CinemaTypeEnum cinematype) {
		//Initialize price
		double price = 0;
		
		//calculate price based on cinema type
		if (cinematype == CinemaTypeEnum._platinum)
			price += ticketPlatinum;
		
		//calculate price base on whether it is weekday or PH
		if (daytype == DayTypeEnum.PH)
			price += ticketPremiumHol;
		
		//calculate price base on movie type
		if (movietype == MovieTypeEnum._3D)
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
	public static double getTPAdult() {
		return ticketPriceAdult;
	}
	public static double getTPChild() {
		return ticketPriceChild;
	}
	public static double getTPStudent() {
		return ticketPriceStudent;
	}
	public static double getTPSenior() {
		return ticketPriceSenior;
	}
	public static double getTP3D() {
		return ticketPremium3D;
	}
	public static double getTPBB() {
		return ticketPremium3D;
	}
	public static double getTPHoliday() {
		return ticketPremiumHol;
	}
	public static void addPublicHol(String date) {
		publicHol.add(date);
	}
	public static void clearPublicHol() {
		publicHol.clear();
	}
	public static List<String> getPublicHol() {
		return publicHol;
	}

	public static double getTPPlatinum() {
		return ticketPlatinum;
	}

	public static void setTPPlatinum(double ticketPlatinum) {
		PriceSetting.ticketPlatinum = ticketPlatinum;
	}
	

}
