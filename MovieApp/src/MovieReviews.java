import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class MovieReviews implements Serializable{


	private static final long serialVersionUID = 5368997632500891145L;
	private int movieID;
	private ArrayList<Double> rating;
	private ArrayList<String> review;
	
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
	

}

