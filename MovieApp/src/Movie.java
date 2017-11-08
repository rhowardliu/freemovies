import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

enum MovieTypeEnum {
	_3D, BB, digital
}

enum StatusEnum {
	ComingSoon, Preview, NowShowing, EndOfShow
}

public class Movie implements Serializable {
	private static final long serialVersionUID = -7025004981841146212L;
	private int movieID;
	private int duration;
	private String title;
	private StatusEnum status;
	private String director;
	private List<String> cast;
	private String synopsis;
	private ArrayList<MovieReviews> reviews;
	private double averageRating;
	private double totalSales;
	private List<ShowTime> movieShowTime = new ArrayList<ShowTime>();
	private MovieTypeEnum movietype;
	public static List<Movie> movielist = new ArrayList<Movie>();
	public static final File movieDatabase = new File ("Movie.txt");
	
	public Movie(int movieID, String title, int duration, StatusEnum status, String director, List<String> cast) {
		this.movieID = movieID;
		this.duration = duration;
		this.title = title;
		this.status = status;
		System.out.print("Directed by: ");
		this.director = director;
		this.cast=cast;
		//average rating
		averageRating = this.getAverageRating();
		//adding all the relevant reviews into reviews array
		for (MovieReviews x : MovieReviews.reviewslist) {
			if (x.getmovieID()==movieID)
				reviews.add(x);
		}
		movielist.add(this);
		
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getMovieID() {
		return movieID;
	}
	
	public void addMovieReview(double rating, String review) {
		MovieReviews mr = new MovieReviews(movieID, rating, review);
		reviews.add(mr);
	}
	
	public double getAverageRating() {
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
		System.out.println("Movie title: " + this.title);
		System.out.println("Status: " + this.status);
		System.out.println("Synopsis" + this.synopsis);
				System.out.println("Directed by: " + director);
		System.out.println("Cast(s): ");
		for (int i = 0; i < 10; i++){
			System.out.println((i+1) + ". " + cast.get(i));
		}
		System.out.println("\n");
	}
	
	public void updateMovieStatus(StatusEnum status){
		this.status = status;
		System.out.println("Movie Status Updated!");
	}
	
	public static Movie searchMovie(int ID) throws NullPointerException {
		
		for (Movie x : Movie.movielist) {
			if (x.getMovieID() == ID)
				return x;
		}
			throw new NullPointerException ("movieID not found");
		
	}
	
	public void updateMovieType(MovieTypeEnum movietypeenum) {
		this.movietype = movietypeenum;
	}
	public MovieTypeEnum getMovieType() {
		return movietype;
	}

	public void addReview(Double rating, String review) {
		new MovieReviews(movieID, rating, review);
	}
	
	public double getSales() {
		return totalSales;
	}
	
	public String getTitle() {
		return title;
	}
	

	public void addShowTime(ShowTime st) {
		movieShowTime.add(st);
	}
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(movieDatabase);
		movielist = or.initialiseDataList(movielist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(movieDatabase);
		ow.updateDataList(movielist);
	}

	public void displayShowTimes(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		List<ShowTime> temp_list = movieShowTime;
		int i= temp_list.size()-1;
		List<String> temp_date = new ArrayList<String>();
		if (movieShowTime.isEmpty()){
			System.out.println("No Showtime available");
			return;
		}
		else {
			System.out.println(" -ShowTime- ");
			temp_date.add(dateFormat.format(temp_list.get(i--).getDate().getTime()));
			
			while(i>=0) {
		    	String date = dateFormat.format(temp_list.get(i).getDate().getTime());
				if (!temp_date.contains(date))
					temp_date.add(date);
				i--;
				}
	  		Collections.sort(temp_date);
	  		for(int j = 0; j < temp_date.size();j++) {
	   			List<ShowTime> dailyshowtime = new ArrayList<ShowTime>();
	  			String theDate = temp_date.get(j);
	  			for(ShowTime x : temp_list) {
	  				if (theDate.equals(dateFormat.format(x.getDate().getTime())))
	  					dailyshowtime.add(x);
	  			}
	  			Collections.sort(dailyshowtime);
	  			System.out.println(theDate);
	  			System.out.println("----------");
	  			for(ShowTime x: dailyshowtime) {
	  				int start_time = x.getShowDateTime();
	  				int end_time = start_time + x.getMovie().duration;
	  				System.out.printf("%02d:00 - %02d:00", start_time, end_time);
	  			}
	  		}
		}
		
	
	}
}
