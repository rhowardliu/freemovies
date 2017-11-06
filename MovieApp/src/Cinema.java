import java.util.Scanner;

enum CinemaTypeEnum {
	standard, VIP
}

public class Cinema {
	private Timetable [] calender;
	private CinemaTypeEnum cinematype;
	
	public Cinema(CinemaTypeEnum  cinematypeenum){
		this.calender = new Timetable[31];
		this.cinematype = cinematypeenum; break;
	}
	
	public addShowTime(){
		
	}
	
}
