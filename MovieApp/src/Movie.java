import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum MovieTypeEnum {
	_3D, BB, digital
}

enum StatusEnum {
	ComingSoon, Preview, NowShowing, EndOfShow
}

public class Movie implements Serializable, dataStorage {
	private static final long serialVersionUID = -7025004981841146212L;
	private int movieID;
	private String title;
	private StatusEnum status;
	private String director;
	private List<String> cast;
	private String synopsis;
	private ArrayList<MovieReviews> reviews;
	private double averageRating;
	private double totalSales;
	private ShowTime[] movieShowTime;
	private static MovieTypeEnum movietype;
	public static List<Movie> movielist = new ArrayList<Movie>();
	public static final File movieDatabase = new File ("Movie.txt");
	
	public Movie(int movieID, String title, StatusEnum status, String director, List<String> cast) {
		this.movieID = movieID;
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
	public static MovieTypeEnum getMovieType() {
		return movietype;
	}
	
	public void addShowTime() {

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
	

	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(movieDatabase);
		movielist = or.initialiseDataList(movielist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(movieDatabase);
		ow.updateDataList(movielist);
	}
	
}
