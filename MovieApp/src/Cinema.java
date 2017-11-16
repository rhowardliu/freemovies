import java.util.Calendar;

enum CinemaTypeEnum {
	_standard, _platinum
}

/**
 * Cinema class contains an array of Timetable objects, its cinema type (CinemaTypeEnum), cinema code and number of rows and columns in the cinema
 * @author user
 *
 */
public class Cinema {
	private Timetable [] calendar; //every cinema has an array of Timetables
	//every Timetable object has a Calendar date attribute
	private CinemaTypeEnum cinematype;
	private String cinemacode;
	private int numberofrows;
	private int numberofcols;
	
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

}
