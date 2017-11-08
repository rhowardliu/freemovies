import java.io.Serializable;
import java.util.ArrayList;
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
	private String directedBy;
	private String[] cast;
	private String synopsis;
	private ArrayList<MovieReviews> reviews;
	private double averageRating;
	private double totalSales;
	private ShowTime[] movieShowTime;
	private MovieTypeEnum movietype;
	
	public Movie(String title, StatusEnum status, String directedBy, String[] cast) {
		System.out.print("Movie title: ");
		Scanner sc = new Scanner(System.in);
		this.title = sc.next();
		this.status = status;
		System.out.print("Directed by: ");
		this.directedBy = sc.next();
		this.cast = new String[10];
		System.out.print("Number of cast (List up to 10): ");
		int castnumber = sc.nextInt();
		System.out.print("Cast: ");
		for (int i = 0; i < castnumber; i++){
			this.cast[i] = sc.next();
		}
		//average rating
		averageRating = this.getAverageRating();
		//adding all the relevant reviews into reviews array
		for (MovieReviews x : MovieReviews.reviewslist) {
			if (x.getmovieID()==movieID)
				reviews.add(x);
		}
		
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
				System.out.println("Directed by: " + this.directedBy);
		System.out.println("Cast(s): ");
		for (int i = 0; i < 10; i++){
			System.out.println((i+1) + ". " + cast[i]);
		}
		System.out.println("\n");
	}
	
	public void updateMovieStatus(StatusEnum status){
		this.status = status;
		System.out.println("Movie Status Updated!");
	}
	
	public void updateMovieType(MovieTypeEnum movietypeenum) {
		this.movietype = movietypeenum;
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
	
}
