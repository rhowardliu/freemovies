
public class Timetable {
private Integer[] timetable = new Integer[24];


public Timetable () {
	
}

public void addShowTime (int movieID, int start, int end) {
	int i;
	boolean count=false;
	for (i=start;i<end;i++) { 
		if (timetable[i] != null) {
			count = true;
	}
	}
	
		if (count = true) {
			System.out.println("Timing clash, please enter a different timing");
		}
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
