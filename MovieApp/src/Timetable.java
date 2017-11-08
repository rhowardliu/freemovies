import java.util.Calendar;

enum DayTypeEnum {
	Weekday, PH
}

public class Timetable {

private DayTypeEnum daytype;
private Calendar date;
private Integer[] timetable = new Integer[24];


public Timetable (Calendar date) {
	this.date = date;
	this.daytype = DayTypeEnum.Weekday;
	int dayoftheweek = date.get(Calendar.DAY_OF_WEEK);
	if (dayoftheweek == 6 || dayoftheweek == 7)
		this.daytype = DayTypeEnum.PH;
}

public void switchDayType (DayTypeEnum type) {
	daytype = type;
}

public void addShowTime (int movieID, int start, int end) {
	//Assume the requested slot is available
	boolean isScheduled = false;
	
	//Checking if the requested time slot is taken by other movies	
	for (int i=start;i<end;i++) { 
		if (timetable[i] != null) 
			isScheduled = true;
	}
	
	if (isScheduled = true) 
		System.out.println("Timing clash, please enter a different timing");
	else {
			for (i=start;i<end;i++) { 
				timetable[i]=movieID;
	}
		System.out.println("Show time has been successfully added");
		}
	
}
public void displayShowTime() {
	int i;
	for (i=0;i<10;i++) {
		if (timetable[i]!=null)
			System.out.println("0"+ i + ":00 : " + timetable[i]);
		else 
			System.out.println("0" + i + ":00 : " + "-");
	}
	
	for (i=10;i<24;i++) {
		if (timetable[i]!=null)
			System.out.println(i + ":00 : " + timetable[i]);
		else 
			System.out.println(i + ":00 : " + "-");
	}
}
}
