import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;


public class Execute {

	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InvalidLogin {
		MovieGoer.initialiseDatabase();
		MovieReviews.initialiseDatabase();
		
		Account user = login();	
		if (user.getUserID().equals("admin"))
			user = (Admin) user;
		else user = (MovieGoer) user;
		
		//
		//
		//
		List<String> movieCast = Arrays.asList("Howard", "NewNew", "Daddy");
		Cineplex jurongPoint = new Cineplex ("Jurong Point");
		//Movie Thor = new Movie (1234, "Thor", 3, StatusEnum.NowShowing, MovieTypeEnum.digital,"Justin Peh", movieCast);
		Map<String, Movie> movies = new HashMap<String, Movie>();
		movies.put("Thor", new Movie (1234, "Thor", 3, StatusEnum.NowShowing, MovieTypeEnum.digital,"Justin Peh", movieCast));
		
		//
		//
		//
		boolean logout=false;
		while (logout != true) {
		System.out.println("Course of action:");
		System.out.println("1) Create/Update/Remove Movie Listing ");
		System.out.println("2) Create/Update/Remove Cinema Showtimes & Movies Shown");//undone
		System.out.println("3) Change Holiday Date");//done
		System.out.println("4) Quit");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.println("1) Create Movie Listing");
			System.out.println("2) Update Movie Listing");
			System.out.println("3) Remove Movie Listing");//not done
			int decision = sc.nextInt();
			switch (decision) {
			case 1: 
				createMovie(); //new Movie object is returned
				//add it into the list of movies showing 
				
				break;
			case 2:
				System.out.println("1) Update Movie Status");
				System.out.println("2) Update Movie Type");
				//Scanner scans = new Scanner(System.in);
				int chce = sc.nextInt();
				System.out.println("Enter Movie ID: ");
				int id = sc.nextInt();
				//String name = sc.next();
				//Movie movieName = movies.get(name);
				Movie movie = Movie.searchMovie(id);
				switch (chce) {
				case 1:
					updateStatus(movie);
					break;
				case 2:
					updateMovieType(movie);
					break;
				}
				
				break;
			case 3:
				break;
			}
			break;
		case 2:
			break;
		case 3:
			switchDayType(jurongPoint);
			break;
		case 4:
			logout = true;
			break;
		}
		
		}
	}
	
	
	public static Account login() throws InvalidLogin {
		Account user;
		String inputUser;
		String inputPw;
		Scanner scan = new Scanner (System.in);
		System.out.println("Enter username:");
		inputUser = scan.nextLine();
		System.out.println("Enter password:");
		inputPw = scan.nextLine();
		
		return user = searchUser(inputUser, inputPw);
			
	}
	
	public static Account searchUser(String inputUser, String inputPW) throws InvalidLogin {
		if (inputUser.equals("admin") && inputPW.equals("pass")) {
			return Admin.getInstance();
		}
		
		else {
			for (Account x : MovieGoer.moviegoerlist) {
				String username = x.getUserID();
				String password = x.getPassword();
				if (username.equals(inputUser)) {
					if (password.equals(inputPW))
						return x;
					throw new InvalidLogin();
				}
			}
		}
		throw new InvalidLogin();
	}
	
	static class InvalidLogin extends Exception{
		public InvalidLogin () {
		super ("Incorrect username/password.");
		}
	}
	public static StatusEnum getInputStatus() {
		System.out.println("1) Coming Soon");
		System.out.println("2) Preview");
		System.out.println("3) Now Showing");
		System.out.println("4) End of Show");
		System.out.println("Enter Status:");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		switch(i) {
		case 1:
			return StatusEnum.ComingSoon;
		case 2:
			return StatusEnum.Preview;
			
		case 3:
			return StatusEnum.NowShowing;
			
		case 4:
			return StatusEnum.EndOfShow;
			
		}
		return null;
	}
	
	public static MovieTypeEnum getInputMovieType() {
		System.out.println("1) 3D");
		System.out.println("2) Blockbuster");
		System.out.println("3) Digital");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		switch(i) {
		case 1:
			return MovieTypeEnum._3D;
		case 2:
			return MovieTypeEnum.BB;
			
		case 3:
			return MovieTypeEnum.digital;
			
		}
		return null;
	}
	
	public static Movie createMovie() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie Name: ");
		String title = sc.next();
		System.out.println("Enter Movie ID: ");
		int movieID = sc.nextInt();
		System.out.println("Enter Duration: ");
		int duration = sc.nextInt();
		System.out.println("Status of Movie: ");
		getInputStatus();
		System.out.println("Movie Type: ");
		getInputMovieType();
		System.out.println("Enter Director Name: ");
		String directorName = sc.next();
		System.out.println("Enter Cast Names: ");
		ArrayList<String> castMembers = new ArrayList<String>();
		while(sc.hasNextLine()){

	           castMembers.add(sc.next());
	       }
		Movie newMovie = new Movie(movieID,title,duration,getInputStatus(), getInputMovieType(),directorName,castMembers);
		return newMovie;
	}
	
	public static void updateStatus(Movie movie) {
		System.out.println("The current status of " +movie.getTitle() +" is : " +movie.getStatus());
		System.out.println("Update to:");
		System.out.println("1) Coming Soon");
		System.out.println("2) Preview");
		System.out.println("3) Now Showing");
		System.out.println("4) End of Show");
		Scanner sc = new Scanner(System.in);
		int statusChoice = sc.nextInt();
		switch (statusChoice) {
		case 1:
			movie.updateMovieStatus(StatusEnum.ComingSoon);
			break;
		case 2:
			movie.updateMovieStatus(StatusEnum.Preview);
			break;
		case 3:
			movie.updateMovieStatus(StatusEnum.NowShowing);
			break;
		case 4:
			movie.updateMovieStatus(StatusEnum.EndOfShow);
			break;
		}
	}
	
	public static void updateMovieType(Movie movie) {
		System.out.println("The current Movie Type of " +movie +" is : " +movie.getMovieType());
		System.out.println("Update to:");
		System.out.println("1) 3D");
		System.out.println("2) Blockbuster");
		System.out.println("3) Digital");
		Scanner sc = new Scanner(System.in);
		int typeChoice = sc.nextInt();
		switch (typeChoice) {
		case 1:
			movie.updateMovieType(MovieTypeEnum._3D);
			break;
		case 2:
			movie.updateMovieType(MovieTypeEnum.BB);
			break;
		case 3:
			movie.updateMovieType(MovieTypeEnum.digital);
			break;
		}
	}
	public static void switchDayType(Cineplex p) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Cineplex: ");//ideally code should not be specific 
		Cinema[] cinema = p.getCinemas();
		System.out.println("Enter Cinema number: ");
		int index = sc.nextInt();
		Timetable[] timetable = cinema[index].getCalendar();
		System.out.println("Enter Month: ");
		int month = sc.nextInt();
		System.out.println("Enter Date: ");
		int date = sc.nextInt(); 
		System.out.println("Day Type: ");
		System.out.println("1) Weekday ");
		System.out.println("2) PH");
		int dayTypeChoice = sc.nextInt();
		switch(dayTypeChoice) {
		case 1:
			timetable[month].switchDayType(DayTypeEnum.Weekday);
			break;
		case 2:
			timetable[month].switchDayType(DayTypeEnum.PH);
			break;
		}
	}
	
}