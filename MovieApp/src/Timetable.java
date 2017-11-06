
public class Timetable {
private Integer[] timetable = new Integer[24];


public Timetable () {
	
}

public void addShowTime (int movieID, int start, int end) {
	int i;
	for (i=start;i<end;i++) {
		timetable[i]=movieID;
	}
	
}
public void displayShowTime() {
	int i;
	for (i=0;i<24;i++) {
		if (timetable[i]!=null)
			System.out.println(i + " : " + timetable[i]);
		else 
			System.out.println(i + " : " + "-");
	}
}
}
