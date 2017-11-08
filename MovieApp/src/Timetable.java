import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

enum DayTypeEnum{
	Weekday, PH
}

public class Timetable implements Serializable{

	private static final long serialVersionUID = -31706793024846802L;
	private DayTypeEnum daytype;
	private Calendar date;
	private Movie[] timetable = new Movie[24];
	private Cinema cinema;
	public static List<Timetable> timetablelist = new ArrayList<Timetable>();
	public static final File timetableDatabase = new File ("Timetable.txt");

	public Timetable (Calendar date, Cinema cinema) {
		this.cinema = cinema;
		this.date = date;
		this.daytype = DayTypeEnum.Weekday;
		int dayoftheweek = date.get(Calendar.DAY_OF_WEEK);
		if (dayoftheweek == 6 || dayoftheweek == 7)
			this.daytype = DayTypeEnum.PH;
		timetablelist.add(this);
	}

	public DayTypeEnum getDayType(){
		return this.daytype;
	}
	
	public Calendar getDate(){
		return this.date;
	}
	
	public void setPublicHoliday (DayTypeEnum type) {
		daytype = type;
	}

	public void addShowTime (Movie movie, int starttime) {
		//Assume the requested slot is available
		boolean isScheduled = false;
		
		//Checking if the requested time slot is taken by other movies	
		for (int i=starttime;i<starttime+movie.getDuration();i++) { 
			if (timetable[i] != null) 
				isScheduled = true;
		}
		
		if (isScheduled = true) 
			System.out.println("Timing clash, please enter a different timing");
		
		else {
			for (int i = starttime; i < starttime+movie.getDuration(); i++) 
				timetable[i] = movie;
		System.out.println("Show time has been successfully added");
		}
		
		//this line make sure the movie is aware that the admin has added a new showtime under its name
		movie.addShowTime(new ShowTime(this,starttime, this.cinema.getCineplex().getCineplexName(), movie));
	}

	public void displayadminShowTime() {
		for (int i = 0 ; i<10; i++) {
			if (timetable[i]!=null)
				System.out.println("0"+ i + ":00 : " + timetable[i].getTitle());
			else 
				System.out.println("0" + i + ":00 : " + "-");
		}
		
		for (int i = 10; i<24; i++) {
			if (timetable[i]!=null)
				System.out.println(i + ":00 : " + timetable[i].getTitle());
			else 
				System.out.println(i + ":00 : " + "-");
		}
	}
	
	public Movie[] getTimetable(){
		return timetable;
	}
	

	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(timetableDatabase);
		timetablelist = or.initialiseDataList(timetablelist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(timetableDatabase);
		ow.updateDataList(timetablelist);
	}

}
