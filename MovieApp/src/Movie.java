import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

enum MovieTypeEnum {
	_3D, BB, digital
}

enum StatusEnum {
	ComingSoon, Preview, NowShowing, EndOfShow	
}

/**
 * Movie class consist of movie ID, duration, title, show status, director name, cast list, synopsis, an array of Review objects, and total ticket sales for that movie
 * @author user
 *
 */
public class Movie implements Serializable {
	private static final long serialVersionUID = -7025004981841146212L;
	private String movieID;
	private int duration;
	private String title;
	private StatusEnum status;
	private String director;
	private List<String> cast;
	private String synopsis;
	private List<MovieReviews> reviews=new ArrayList<MovieReviews>();
	private double averageRating;
	private double totalSales;
	private List<ShowTime> movieShowTime = new ArrayList<ShowTime>();
	private MovieTypeEnum movietype;
	public static List<Movie> movielist = new ArrayList<Movie>();
	public static final File movieDatabase = new File ("Movie.tmp");

	
	public Movie(String movieID, String title, int duration, StatusEnum status, MovieTypeEnum movieType, String director, List<String> cast, String synopsis) {
		this.movieID = movieID;
		movietype =movieType;
		this.duration = duration;
		this.title = title;
		this.status = status;
		this.director = director;
		this.cast=cast;
		this.synopsis = synopsis;
		//average rating
		setAverageRating(this.fetchAverageRating());
		//adding all the relevant reviews into reviews array
		for (MovieReviews x : MovieReviews.reviewslist) {
			if (x.getmovieID().equals(movieID))
				reviews.add(x);
		}
			for (ShowTime x : ShowTime.showtimelist) {
				if (x.getMovieID().equals(movieID))
					movieShowTime.add(x);
		}
		movielist.add(this);
		
	}
	public void setMovieTitle(String movieTitle) {
		title = movieTitle;
	}
	
	public void setMovieType(MovieTypeEnum movieType) {
		movietype = movieType;
	}
	
	public void setMovieDirector (String movieDirector) {
		director = movieDirector;
	}
	
	public void setMovieDuration (int movieDuration) {
		duration = movieDuration;
	}
	
	public void setMovieSynopsis (String movieSynopsis) {
		synopsis = movieSynopsis;
	}
	
	public void setMovieCast (List<String> movieCast) {
		cast = movieCast;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public StatusEnum getStatus() {
		return status;
	}
	
	public String getMovieID() {
		return movieID;
	}
	
	public double getAverageRating() {
		return averageRating;
	}
	
	public List<String> getCast(){
		return cast;
	}
	
	/**
	 * Method to add a movie review to a Movie object
	 * @param rating
	 * @param review
	 */
	public void addMovieReview(double rating, String review) {
		MovieReviews mr = new MovieReviews(movieID, rating, review);
		reviews.add(mr);
		setAverageRating(this.fetchAverageRating());
	}
	

	public List<MovieReviews> getMovieReview() {
		return reviews;
	}
	
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	
	/**
	 * Recalculates the average movie rating and returns the value
	 * @return
	 */
	public double fetchAverageRating() {
		double avgrating;
		double sum = 0;
		int ratingcount =  reviews.size();
		for(MovieReviews x : reviews)
			sum+=x.getRating();
		
		try {
		avgrating = sum/ratingcount;
		}catch (ArithmeticException e) {
			return 0;
		}
		return avgrating;
	}
	
	/**
	 * Prints out the Movie object's info
	 */
	public void getMovieInfo() {
		//Ellen added some stuff here. kiv

		System.out.println("===== Details of " + this.getTitle() + "=====");
		System.out.println("Movie title: " + this.title);
		System.out.println("Movie duration: " + this.duration + " hrs");
		System.out.println("Movie type: " + this.movietype);
		System.out.println("Status: " + this.status);
		System.out.println("Directed by: " + director);
		System.out.print("\n");
		System.out.println("Synopsis: \n" + this.synopsis);
		System.out.print("\n");
		System.out.println("Cast(s): ");

		for (int i = 0; i < cast.size(); i++){
			System.out.println((i+1) + ". " + cast.get(i));
		} 
	}
	

	public List<ShowTime> getShowTimes() {
		return movieShowTime;
	}
	
	public void updateMovieStatus(StatusEnum status){
		this.status = status;
		System.out.println("Movie Status Updated! Movie Status is now " +getStatus());
	}
	
	/**
	 * returns the corresponding Movie object for the movie id entered by the user
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	public static Movie searchMovie(String ID) throws Exception {
		
		for (Movie x : Movie.movielist) {
			if (x.getMovieID().equals(ID))
				return x;
		}
			throw new Exception ("movieID not found");
		
	}
	
	/**
	 * Updates the movie type (e.g. 3D, blockbuster) of the Movie object
	 * @param movietypeenum
	 */
	public void updateMovieType(MovieTypeEnum movietypeenum) {
		this.movietype = movietypeenum;
		System.out.println("Movie Type Updated! Movie Type is now " +getMovieType());
	}
	
	public MovieTypeEnum getMovieType() {
		return movietype;
	}

	
	public double getSales() {
		return totalSales;
	}
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * adds a ShowTime object to the Movie object's array of ShowTimes
	 * @param st
	 */
	public void addShowTimeToMovie( String cineplexname, String
			cineplexcode, String cinemacode, String date, int starttime, 
			int row, int col, CinemaTypeEnum cinematype) {
		
		ShowTime st=null;
		try {
			st = new ShowTime(this.title,this.movieID,cineplexname,cineplexcode,cinemacode,date,starttime,row,col,cinematype);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		movieShowTime.add(st);
	}
	
	/**
	 * Removes a ShowTime object from the Movie object's array of ShowTimes
	 * @param st
	 */
	public void removeShowTimeFromMovie(String cineplexname, String
			cineplexcode, String cinemacode, String date, int starttime, 
			int row, int col, CinemaTypeEnum cinematype){
//		int showtime_index = movieShowTime.lastIndexOf(st);
		
		ShowTime st=null;
		try {
			st = new ShowTime(this.title,this.movieID,cineplexname,cineplexcode,cinemacode,date,starttime,row,col,cinematype);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		movieShowTime.remove(st);
		ShowTime.showtimelist.remove(st);
		ShowTime.showtimelist.remove(st);
	}

/**
 * Prints out the ShowTimes associated with the Movie 
 */
	public ShowTime displayShowTimes(String cineplexcode){
		List<ShowTime> showtimesincineplexchoice = new ArrayList <ShowTime>(); //this is a list of showtimes that are screened in the cineplex of the user's choice
		List<String> sortedshowtimes = new ArrayList<String>(); //this is a list of all the dates available
		
		if (movieShowTime.isEmpty()){
			System.out.println("No Showtime available for all cineplexes.");
			return null;
		}
		
		for (ShowTime x : movieShowTime) {
			if (x.getCineplexcode().equals(cineplexcode))
				showtimesincineplexchoice.add(x); 
		} //this loop creates an array of showtimes that are located at the moviegoer's choice of cineplex
		
		if(showtimesincineplexchoice.isEmpty()) {
			System.out.println("Cineplex selected is not playing " + this.getTitle() + ".");
			return null;
		}

		else {
			//Sorting the showtimes in chronologically order
			Collections.sort(showtimesincineplexchoice,ShowTime.getDateComparator());
			System.out.println(" ===== ShowTime ===== ");
			for (ShowTime x: showtimesincineplexchoice) 
				sortedshowtimes.add(x.getShowTimeDate()); 
			//the next 3 steps remove the duplicates within temp_date
			Set<String> s = new LinkedHashSet<String>(sortedshowtimes);
			sortedshowtimes.clear();
			sortedshowtimes.addAll(s);

	  		for(String theDate : sortedshowtimes) {
	  			displayDailyShowTime(theDate, showtimesincineplexchoice);
	  			System.out.print("\n");
	  			}
	  		
	  		Scanner sc = new Scanner (System.in);
	  		System.out.print("Proceed to book ticket? (Y/N) ");
	  		char bookChoice;
	  		do{
	  			bookChoice = sc.next().charAt(0);
	  				if (bookChoice == 'y' || bookChoice == 'Y') {
	  			
	  			  		System.out.println("================");
	  			  		System.out.println("List of Show Dates");
	  			  		int i=1;
	  			  		for (String theDate: sortedshowtimes) {
	  			  			System.out.println(i +") "+ theDate);
	  			  			i++;
	  			  		}
	  			  		System.out.println("Enter desired Show date: ");
	  			  		int dateChoice = sc.nextInt();
	  			  		System.out.print("Selected Show Date is ");
	  			  		List<ShowTime> dailyshowlist = displayDailyShowTime(sortedshowtimes.get(dateChoice-1),showtimesincineplexchoice);
	  			  		System.out.println("Select Show:");
	  			  		int showChoice = sc.nextInt();
	  			  		return dailyshowlist.get(showChoice-1);
	  				
	  			}
	  				else if (bookChoice == 'n' || bookChoice == 'N') {
	  					return null;
	  				}
	  				else 
	  					System.out.println("Invalid Choice! Please enter again.");
		}while (bookChoice != 'y' || bookChoice != 'Y' || bookChoice != 'N' || bookChoice != 'n');
	}
		return null;		
}
		
		
	

	
	public List<ShowTime> displayDailyShowTime(String date,List<ShowTime> showtimelist){
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<ShowTime> dailyshowtime = new ArrayList<ShowTime>();
		  			for(ShowTime x : showtimelist) {
		  				if (date.equals(x.getShowTimeDate()))
		  					dailyshowtime.add(x);
	  			}
	  			Collections.sort(dailyshowtime,ShowTime.getTimeComparator());
	  			System.out.println(date);
	  			System.out.println("----------");
	  			int i=1;
	  			for(ShowTime x: dailyshowtime) {
	  				int start_time = x.getShowTimeStartTime();
	  				int end_time = start_time + this.getDuration();
	  				System.out.printf("%d. %02d:00 - %02d:00",i, start_time, end_time);
	  				i++;
	  				System.out.print("\n");
	  			}
	  			return dailyshowtime;
	  			
	  		}
	
	
	
	

	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(movieDatabase);
		movielist = or.initialiseDataList(movielist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(movieDatabase);
		ow.updateDataList(movielist);
	}
	
}