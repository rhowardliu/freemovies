import java.util.Calendar;

enum DayTypeEnum{
	Weekday, PH
}

public class Timetable {

private DayTypeEnum daytype;
private Calendar date;
private Movie[] timetable = new Movie[24];
private Cinema cinema;


public Timetable (Calendar date, Cinema cinema) {
	this.cinema = cinema;
	this.date = date;
	this.daytype = DayTypeEnum.Weekday;
	int dayoftheweek = date.get(Calendar.DAY_OF_WEEK);
	if (dayoftheweek == 6 || dayoftheweek == 7)
		this.daytype = DayTypeEnum.PH;
	
}

public void switchDayType (DayTypeEnum type) {
	daytype = type;
	System.out.println("Day type has been successfully switched to " +getDayType());
}

public DayTypeEnum getDayType() {
	return daytype;
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
	movie.addShowTime(new ShowTime(date,starttime, this.cinema.getCineplex().getCineplexName(), movie));
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



}
