import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Sorts the movie listing in alphabetical order, total ticket sales or rating depending on the user's choice
 * @author user
 *
 */
public class MovieListing {
	
	private static List<Movie> setTemp(StatusEnum status){ //returns list of movie based on its status
		List<Movie> temp = new ArrayList<Movie>(); //creates a new List of Movies called temp
		switch (status) { //based on the show status chosen by the user in MovieGoer method
		case ComingSoon:
			for (Movie x : Movie.movielist) { //for every movie in the static list of movies in Movie class
				if (x.getStatus().equals(StatusEnum.ComingSoon)) //if that movie's status is "Coming Soon"
					temp.add(x); //add that movie to the newly made array of Movies called temp
			}
			break;
		case Preview:
			for (Movie x : Movie.movielist) {
				if (x.getStatus().equals(StatusEnum.Preview))
					temp.add(x);
			}
			break;
		case NowShowing:
			for (Movie x : Movie.movielist) {
				if (x.getStatus().equals(StatusEnum.NowShowing))
					temp.add(x);
			}
			break;
		case EndOfShow:
			for (Movie x : Movie.movielist) {
				if (x.getStatus().equals(StatusEnum.EndOfShow))
					temp.add(x);
			}
			break;
		}
		return temp;
	}
	
	/**
	 * sorts the movie List by rating
	 * @param status
	 * @return
	 */
	public static List<Movie> getMovieListByRating(StatusEnum status) { //sorts movie by rating
		List<Movie> temp = setTemp(status); //setTemp (status) returns a list of movies with the same status that the moviegoer wants to see
		
		Collections.sort(temp,getRatingComparator()); //sorts the array by rating
		return temp; 
	}
	
	/**
	 * sorts the movie List by title in alphabetical order
	 * @param status
	 * @return
	 */
	public static List<Movie> getMovieListByTitle(StatusEnum status) { //sorts movie by title
		List<Movie> temp = setTemp(status);
		Collections.sort(temp,getTitleComparator()); //this line sorts temp's movies by title
		return temp;
	}
	
	/**
	 * sorts the movie List by ticket sales
	 * @param status
	 * @return
	 */
	public static List<Movie> getMovieListBySales(StatusEnum status) { //sorts movie by sales
		List<Movie> temp = setTemp(status);
		Collections.sort(temp,getSalesComparator());
		return temp;
	}
	
	/**
	 * Compares two movie titles to sort them alphabetically
	 * @return
	 */
	static Comparator<Movie> getTitleComparator(){
		return new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
		};	
	}
	
	/**
	 * Compares two movie's ratings
	 * @return
	 */
	static Comparator<Movie> getRatingComparator(){
		return new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				return Double.compare(o2.getAverageRating(), o1.getAverageRating());
			}
		};
	}
	
	/**
	 * Compares two movie's ticket sales
	 * @return
	 */
	static Comparator<Movie> getSalesComparator(){
		return new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				return Double.compare(o2.getSales(), o1.getSales());
			}
		};
	}
}
