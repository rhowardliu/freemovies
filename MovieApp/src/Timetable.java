import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

enum DayTypeEnum{
	Weekday, PH
}

public class Timetable implements Serializable{	
	
	private static final long serialVersionUID = -31706793024846802L;
	private static final String showEmpty = "-";
	private static final String inProgress = "Movie In Progress";
	private DayTypeEnum daytype;
	private Calendar date;
	private String date_string;
	//schedule is an array of movie titles i.e. array of strings
	private String [] schedule = new String [24];
	

	public Timetable (Calendar date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.date = date;
		int dayoftheweek = date.get(Calendar.DAY_OF_WEEK);
		if (dayoftheweek == 6 || dayoftheweek == 7)
			this.daytype = DayTypeEnum.PH;
		else
			this.daytype = DayTypeEnum.Weekday;
		for (int i = 0; i < schedule.length; i++)
			schedule[i] = showEmpty;
		
		date_string = dateFormat.format(date.getTime());
		
		
	}
	
	public static List<Timetable> getTimetableByDate(String date) throws Exception {
		List<Timetable> tt = new ArrayList<Timetable>();
		
		for (Cineplex x : GoldenVillage.getInstance().getCineplexes()) {
			for (Cinema y : x.getCinemas()) {
				for (Timetable z : y.getCalendar()) {
					if (z.getDateString().equals(date))
							tt.add(z);
				}
			}
		}
		
		return tt;
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
	
	public void setPublicHoliday (boolean isHoliday) {
		if (isHoliday==true)
			daytype = DayTypeEnum.PH;
		else
			daytype = DayTypeEnum.Weekday;
				
		}
	

	public boolean addShowTimeToSchedule (Movie movie, int starttime) {	
		//Checking if the requested time slot is taken by other movies	
		for (int i=starttime;i<starttime+movie.getDuration();i++) { 
			if (!schedule[i].equals( showEmpty) ) {
				System.out.println("Timing clash, please enter a different timing");
				return false;
			}
		} //terminates here if there is a clash of timetable and returns false
		
		schedule[starttime] = movie.getTitle();
		for (int i = starttime+1; i < starttime+movie.getDuration(); i++) 
			schedule[i] = inProgress;
		System.out.println("Show time has been successfully added");
		return true;
	}
	
	
	public boolean removeShowTimeFromSchedule (Movie movie, int starttime) {
		if (schedule[starttime].equals(showEmpty)) {
			System.out.println("Slot is already empty!");
			return false;
		}
		
		else if (schedule[starttime].equals(inProgress)){
			System.out.println("Please choose the movie start time");
			return false;
		}

		else if (schedule[starttime].equals(movie.getTitle())) {
			for (int i = starttime; i < starttime+ movie.getDuration(); i++)
				schedule[i] = showEmpty;
			System.out.println("Show time has been successfully removed");
			return true;
		}

		else {
			System.out.println("Please choose the correct movie");
			return false;
		}
	}

	public void displaySchedule() {
		for (int i = 0 ; i<24; i++) 
			System.out.printf("%02d:00 : %s\n", i, schedule[i]);
		}
	
	public String[] getTimetable(){
		return schedule;
	}
	
	static Comparator<String> getDateComparator(){
		return new Comparator<String>() {
			public int compare(String o1, String o2) {

				String dd1=o1.substring(0, 2);
				String mm1=o1.substring(3,5);
				String yy1=o1.substring(6);
				
				String dd2=o2.substring(0, 2);
				String mm2=o2.substring(3,5);
				String yy2=o2.substring(6);
				int compared = yy1.compareTo(yy2);
				if (compared == 0) {
					compared=mm1.compareTo(mm2);
					if (compared==0) {
						compared=dd1.compareTo(dd2);
					}
				}
				
				return compared;
			}
		};	
	}
}
