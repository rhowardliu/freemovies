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

public class Movie implements Serializable {
	private static final long serialVersionUID = -7025004981841146212L;
	private String movieID;
	private int duration;
	private String title;
	private StatusEnum status;
	private String director;
	private List<String> cast;
	private String synopsis;
	private List<MovieReviews> reviews;
	private double averageRating;
	private double totalSales;
	private List<ShowTime> movieShowTime = new ArrayList<ShowTime>();
	private MovieTypeEnum movietype;
	public static List<Movie> movielist = new ArrayList<Movie>();
	public static final File movieDatabase = new File ("Movie.txt");

	
	public Movie(String movieID, String title, int duration, StatusEnum status, MovieTypeEnum movieType, String director, List<String> cast, String synopsis) {
		this.movieID = movieID;
		movietype =movieType;
		this.duration = duration;
		this.title = title;
		this.status = status;
		System.out.print("Directed by: ");
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
	
	public void addMovieReview(double rating, String review) {
		MovieReviews mr = new MovieReviews(movieID, rating, review);
		reviews.add(mr);
		setAverageRating(this.fetchAverageRating());
	}
	
	
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	
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
	
	public void getMovieInfo() {
		//Ellen added some stuff here. kiv
		System.out.println("Movie title: " + this.title);
		System.out.println("Movie duration: " + this.duration);
		System.out.println("Movie type: " + this.movietype);
		System.out.println("Status: " + this.status);
		System.out.println("Synopsis" + this.synopsis);
				System.out.println("Directed by: " + director);
		System.out.println("Cast(s): ");
		for (int i = 0; i < cast.size(); i++){
			System.out.println((i+1) + ". " + cast.get(i));
		} 
		System.out.println("\n");
	}
	
	public void updateMovieStatus(StatusEnum status){
		this.status = status;
		System.out.println("Movie Status Updated! Movie Status is now " +getStatus());
	}
	
	public static Movie searchMovie(String ID) throws Exception {
		
		for (Movie x : Movie.movielist) {
			if (x.getMovieID().equals(ID))
				return x;
		}
			throw new Exception ("movieID not found");
		
	}
	
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
	

	public void addShowTimeToMovie(ShowTime st) {
		movieShowTime.add(st);
	}
	
	public void removeShowTimeFromMovie(ShowTime st){
		int showtime_index = movieShowTime.lastIndexOf(st);
		movieShowTime.remove(st);
		ShowTime.showtimelist.remove(st);
		ShowTime.showtimelist.remove(st);
	}
		
	public void displayShowTimes(){
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<ShowTime> temp_list = movieShowTime;
		int i= temp_list.size()-1;
		List<String> temp_date = new ArrayList<String>(); //this is a list of all the dates available
		
		if (movieShowTime.isEmpty()){
			System.out.println("No Showtime available");
			return;
		}
		else {
			Collections.sort(temp_list,ShowTime.getDateComparator());
			System.out.println(" -ShowTime- ");
			for (ShowTime x: temp_list) {
				temp_date.add(x.getShowTimeDate());
			}
			//the next 3 steps remove the duplicates within temp_date
			Set<String> s = new LinkedHashSet<String>(temp_date);
			temp_date.clear();
			temp_date.addAll(s);

//			List<String> temp_date = temp.stream().distinct().collect(Collectors.toList());

	  		for(int j = 0; j < temp_date.size();j++) {
	   			List<ShowTime> dailyshowtime = new ArrayList<ShowTime>();
	  			String theDate = temp_date.get(j);
	  			for(ShowTime x : temp_list) {
	  				if (theDate.equals(x.getShowTimeDate()))
	  					dailyshowtime.add(x);
	  			}
	  			Collections.sort(dailyshowtime,ShowTime.getTimeComparator());
	  			System.out.println(theDate);
	  			System.out.println("----------");
	  			for(ShowTime x: dailyshowtime) {
	  				int start_time = x.getShowTimeStartTime();
	  				int end_time = start_time + this.getDuration();
	  				System.out.printf("%02d:00 - %02d:00", start_time, end_time);
	  			}
	  		}
		}
	}
	
	public void displayShowTimes(String date){
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<ShowTime> dailyshowtime = new ArrayList<ShowTime>();
		  			for(ShowTime x : movieShowTime) {
		  				if (date.equals(x.getShowTimeDate()))
		  					dailyshowtime.add(x);
	  			}
	  			Collections.sort(dailyshowtime,ShowTime.getTimeComparator());
	  			System.out.println("----------");
	  			for(ShowTime x: dailyshowtime) {
	  				int start_time = x.getShowTimeStartTime();
	  				int end_time = start_time + this.getDuration();
	  				System.out.printf("%02d:00 - %02d:00", start_time, end_time);
	  			}
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