import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieReviews implements Serializable, dataStorage{

	private static final long serialVersionUID = 5368997632500891145L;
	private int movieID;
	private double rating;
	private String review;
	public static List<MovieReviews> reviewslist = new ArrayList<MovieReviews>();
	public static final File reviewDatabase = new File ("MovieReviews.txt");
	
	public MovieReviews(int movieID, double rating, String review) {
		this.movieID=movieID;
		this.rating = rating;
		this.review = review;
		reviewslist.add(this);
	}

	public int getmovieID() {
		return movieID;
	}
	public double getRating(){
		return rating;
	}
	
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(reviewDatabase);
		reviewslist = or.initialiseDataList(reviewslist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(reviewDatabase);
		ow.updateDataList(reviewslist);
	}
}

