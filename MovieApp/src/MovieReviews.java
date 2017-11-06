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
	private List<Double> rating;
	private List<String> review;
	public static List<MovieReviews> reviewslist = new ArrayList<MovieReviews>();
	public static final File reviewDatabase = new File ("MovieReviews.txt");
	
	public MovieReviews(int movieID, ArrayList<Double> rating, ArrayList<String> review) {
		this.movieID=movieID;
		this.review=review;
	}
	public int getmovieID() {
		return movieID;
	}
	public double getAvgRating() throws ArithmeticException{

			double sum = 0;
			double numberOfRatings = (double) rating.size();
			
			if (numberOfRatings==0)
				throw new ArithmeticException("Dividing by zero.");
			for(int i = 0; i<numberOfRatings;i++)
				sum+= rating.get(i);
			return sum/numberOfRatings;
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

