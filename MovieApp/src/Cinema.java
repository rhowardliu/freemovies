import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

enum CinemaTypeEnum {
	_standard, _platinum
}

/**
 * Cinema class contains an array of Timetable objects, its cinema type (CinemaTypeEnum), cinema code and number of rows and columns in the cinema
 * @author user
 *
 */
public class Cinema implements Serializable {
	private static final long serialVersionUID = -4163671305903195219L;
	private Timetable [] calendar; // timetable is the schedule for the day
	private CinemaTypeEnum cinematype;
	private String cinemacode;
	private int numberofrows;
	private int numberofcols;
	public static List<Cinema> cinemalist = new ArrayList<Cinema>();
	public static final File cinemaDatabase = new File ("Cinema.tmp");

	
	/**
	 * Constructor for Cinema class
	 * @param cinematypeenum
	 * @param cinemacode
	 * @param rows
	 * @param cols
	 */
	
	public Cinema(CinemaTypeEnum  cinematypeenum, String cinemacode, int rows, int cols){
		this.cinematype = cinematypeenum;
		this.cinemacode = cinemacode;
		this.calendar = new Timetable[365];
		this.numberofrows = rows;
		this.numberofcols = cols;
		//set the date and day of each slot of the timetable
		Calendar firstjan = Calendar.getInstance();
		firstjan.set(Calendar.MONTH, Calendar.JANUARY);
		//firstjan.set(Calendar.YEAR, 2017);
		firstjan.set(Calendar.DAY_OF_MONTH, 1);
		//sets first slot of the calendar as 1 Jan
		calendar[0] = new Timetable(firstjan);
		
		//sets second slot as 1 day after 1 Jan and so on
		for (int i = 1; i < calendar.length ; i++){
			Calendar calTemp;
			calTemp = (Calendar) firstjan.clone();
			calTemp.add(Calendar.DAY_OF_YEAR,i);
			calendar[i] = new Timetable(calTemp);
		}
	}

	
	public CinemaTypeEnum getCinemaType(){
		return this.cinematype;
	}
	
	public String getCinemaCode(){
		return this.cinemacode;
	}
	
	public int getNumberOfRows(){
		return this.numberofrows;
	}
	
	public int getNumberOfCols(){
		return this.numberofcols;
	}
	
	public Timetable[] getCalendar(){
		return this.calendar;
	}
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(cinemaDatabase);
		cinemalist = or.initialiseDataList(cinemalist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(cinemaDatabase);
		ow.updateDataList(cinemalist);
	}

}
