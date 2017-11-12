import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

enum DayTypeEnum{
	Weekday, PH
}

public class Timetable implements Serializable{	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private static final long serialVersionUID = -31706793024846802L;
	private static final String showEmpty = "-";
	private DayTypeEnum daytype;
	private Calendar date;
	private String date_string;
	//schedule is an array of movie titles i.e. array of strings
	private String [] schedule = new String [24];
	public static List<Timetable> timetablelist = new ArrayList<Timetable>();
	public static final File timetableDatabase = new File ("Timetable.txt");


	public Timetable (Calendar date) {
		this.date = date;
		int dayoftheweek = date.get(Calendar.DAY_OF_WEEK);
		if (dayoftheweek == 6 || dayoftheweek == 7)
			this.daytype = DayTypeEnum.PH;
		else
			this.daytype = DayTypeEnum.Weekday;
		for (int i = 0; i < schedule.length; i++)
			schedule[i] = showEmpty;
		timetablelist.add(this);
		
		date_string = dateFormat.format(date);
	}
	
	public static Timetable getTimetableByDate(String date) throws Exception {
		
		for (Timetable x: timetablelist)
			if (x.getDateString().equals(date))
				return x;
		throw new Exception ("timetable not found");
	}


	public void switchDayType (DayTypeEnum type) {
		daytype = type;
		System.out.println("Day type has been successfully switched to " +getDayType());
	}


	public DayTypeEnum getDayType(){
		return this.daytype;
	}
	
	public String getDateString() {
		return date_string;
	}
	
	public Calendar getDate(){
		return this.date;
	}
	
	public void setPublicHoliday (DayTypeEnum type) {
		daytype = type;
	}

	public boolean addShowTimeToSchedule (Movie movie, int starttime) {
		
		//Checking if the requested time slot is taken by other movies	
		for (int i=starttime;i<starttime+movie.getDuration();i++) { 
			if (schedule[i].equals(showEmpty)) {
				System.out.println("Timing clash, please enter a different timing");
				return false;
			}
		}
		
			for (int i = starttime; i < starttime+movie.getDuration(); i++) 
				schedule[i] = movie.getTitle();
		System.out.println("Show time has been successfully added");
		return true;
	}
	
	
	public boolean removeShowTimeFromSchedule (Movie movie, int starttime) {
		if (schedule[starttime].equals(showEmpty)) {
			System.out.println("Slot is already empty!");
			return false;
		}
		else {
			for (int i = starttime; i < starttime+ movie.getDuration(); i++)
				schedule[i] = showEmpty;
		}
		System.out.println("Show time has been successfully removed");
		return true;
	}

	public void displaySchedule() {
		for (int i = 0 ; i<24; i++) 
			System.out.printf("%02d:00 : ,%s", i, schedule[i]);
		}
	
	public String[] getTimetable(){
		return schedule;
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
