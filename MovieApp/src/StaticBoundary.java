import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class StaticBoundary {
	private static File priceFile = new File ("PriceSetting.csv");
	private static File holidayDates = new File ("HolidayDates.csv");
	
	public static void initialisePrices() throws FileNotFoundException {	
		try {
		BufferedReader readPrice = new BufferedReader(new FileReader(priceFile));
		String[] s = readPrice.readLine().split(",");
		Double[] Prices = new Double[PriceSetting.priceTypes];
		 for(int i=0; i<s.length;i++)
			 Prices[i]=(Double.parseDouble(s[i]));
		 
		PriceSetting.setTPAdult(Prices[0]);
		PriceSetting.setTPChild(Prices[1]);
		PriceSetting.setTPStudent(Prices[2]);
		PriceSetting.setTPSenior(Prices[3]);
		PriceSetting.setTP3D(Prices[4]);
		PriceSetting.setTPBB(Prices[5]);
		PriceSetting.setTPHol(Prices[6]);
		PriceSetting.setTPPlatinum(Prices[7]);

			readPrice.close();
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static void initialiseHoliday() throws IOException {
		BufferedReader readPrice = new BufferedReader(new FileReader(holidayDates));
		
		try {
	
			String[] s = readPrice.readLine().split(",");
			for (String x : s) {
				 PriceSetting.addPublicHol(x);
			}
			} catch(NullPointerException e) {				
			}catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			}finally {
				if (readPrice!=null)
					readPrice.close();
			}
	}
	
	public static void updateHoliday() throws IOException {
		List<String> holidayList = PriceSetting.getPublicHol();
		if (holidayList == null) {
			System.out.println("No Holidays :(");
			return;
		}
		BufferedWriter writePrice = new BufferedWriter(new FileWriter(holidayDates));
		String saveString=new String();
		try {
			
			for (String x : holidayList)
				saveString=saveString.concat(x + ",");
				writePrice.write(saveString.substring(0,saveString.length()-1));

		}catch(IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		writePrice.close();
	}

	
	public static void updatePrices() throws IOException {
		BufferedWriter writePrice = new BufferedWriter(new FileWriter(priceFile));
		try {
			writePrice.write(PriceSetting.getTPAdult() + "," + 
					PriceSetting.getTPChild() + "," +
					PriceSetting.getTPStudent()+ "," +
					PriceSetting.getTPSenior()+ "," +
					PriceSetting.getTP3D()+ "," +
					PriceSetting.getTPBB()+ "," +
					PriceSetting.getTPHoliday()+ "," +
					PriceSetting.getTPPlatinum()+ ",");

		}catch(IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
		writePrice.close();
	}
}

