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
	//schedule is an array of movie titles i.e. array of strings
	private String [] schedule = new String [24];
	public static List<Timetable> timetablelist = new ArrayList<Timetable>();
	public static final File timetableDatabase = new File ("Timetable.txt");


	public Timetable (Calendar date) {
		this.date = date;
		this.daytype = DayTypeEnum.Weekday;
		int dayoftheweek = date.get(Calendar.DAY_OF_WEEK);
		if (dayoftheweek == 6 || dayoftheweek == 7)
			this.daytype = DayTypeEnum.PH;
		for (int i = 0; i < schedule.length; i++)
			schedule[i] = "-";
		timetablelist.add(this);
	}


	public void switchDayType (DayTypeEnum type) {
		daytype = type;
		System.out.println("Day type has been successfully switched to " +getDayType());
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

	public void addShowTimeToSchedule (Movie movie, int starttime) {
		//Assume the requested slot is available
		boolean isScheduled = false;
		
		//Checking if the requested time slot is taken by other movies	
		for (int i=starttime;i<starttime+movie.getDuration();i++) { 
			if (schedule[i] != "-") 
				isScheduled = true;
		}
		
		if (isScheduled = true) 
			System.out.println("Timing clash, please enter a different timing");
		
		else {
			for (int i = starttime; i < starttime+movie.getDuration(); i++) 
				schedule[i] = movie.getTitle();
		System.out.println("Show time has been successfully added");
		}
	}
	
	public void removeShowTimeFromSchedule (Movie movie, int starttime) {
		if (schedule[starttime] == "-")
			System.out.println("Slot is already empty!");
		
		else {
			for (int i = starttime; i < starttime+ movie.getDuration(); i++)
				schedule[i] = "-";
		}
		System.out.println("Show time has been successfully removed");
	}

	public void displaySchedule() {
		//check with ellen if this is ok
		for (int i = 0 ; i<10; i++) 
			System.out.println("0"+ i + ":00 : " + schedule[i]);
		
		for (int i = 10; i<24; i++) {
			if (schedule[i]!= null)
				System.out.println(i + ":00 : " + schedule[i]);
			else 
				System.out.println(i + ":00 : " + "-");
		}
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
