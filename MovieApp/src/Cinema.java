import java.util.Calendar;

enum CinemaTypeEnum {
	standard, VIP
}

public class Cinema {
	private Timetable [] calendar;
	


	private CinemaTypeEnum cinematype;
	private Cineplex cineplex;
	
	public Cinema(CinemaTypeEnum  cinematypeenum, Cineplex cineplex){
		this.cineplex=cineplex;
		this.cinematype = cinematypeenum;
		this.calendar = new Timetable[31];
		//set the date and day of each slot of the timetable
		Calendar firstdec2017 = Calendar.getInstance();
		firstdec2017.set(Calendar.MONTH, Calendar.DECEMBER);
		firstdec2017.set(Calendar.YEAR, 2017);
		firstdec2017.set(Calendar.DAY_OF_MONTH, 1);
		 System.out.println("Date is " + firstdec2017.getTime());
		//sets first slot of the calendar as 1 Dec 2017
		calendar[0] = new Timetable(firstdec2017, this);
		
		//sets second slot as 1 day after 1 Dec 2017 and so on
		for (int i = 1; i < calendar.length ; i++){
			Calendar calTemp;
			calTemp = (Calendar) firstdec2017.clone();
			calTemp.add(Calendar.DAY_OF_YEAR,i);
			calendar[i] = new Timetable(calTemp, this);
		}
	}
<<<<<<< HEAD
	
	public Timetable[] getCalendar() {
		return calendar;
	}
=======
>>>>>>> branch 'master' of https://github.com/rhowardliu/freemovies.git

	public Cineplex getCineplex() {
		return cineplex;
	}

}
