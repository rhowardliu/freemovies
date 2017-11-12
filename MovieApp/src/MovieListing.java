import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieListing {
	
	private static List<Movie> setTemp(StatusEnum status){ //returns list of movie based on its status
		List<Movie> temp = new ArrayList<Movie>();
		switch (status) {
		case ComingSoon:
			for (Movie x : Movie.movielist) {
				if (x.getStatus().equals(StatusEnum.ComingSoon))
					temp.add(x);
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
	
	public static List<Movie> getMovieListByRating(StatusEnum status) { //sorts movie by rating
		List<Movie> temp = setTemp(status);
		
		Collections.sort(temp,getRatingComparator());
		return temp;
	}
	
	public static List<Movie> getMovieListByTitle(StatusEnum status) { //sorts movie by title
		List<Movie> temp = setTemp(status);
		Collections.sort(temp,getTitleComparator());
		return temp;
	}
	
	public static List<Movie> getMovieListBySales(StatusEnum status) { //sorts movie by sales
		List<Movie> temp = setTemp(status);
		Collections.sort(temp,getSalesComparator());
		return temp;
	}

	static Comparator<Movie> getTitleComparator(){
		return new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
		};	
	}
			
	static Comparator<Movie> getRatingComparator(){
		return new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				return Double.compare(o2.getAverageRating(), o1.getAverageRating());
			}
		};
	}
		
	static Comparator<Movie> getSalesComparator(){
		return new Comparator<Movie>() {
			public int compare(Movie o1, Movie o2) {
				return Double.compare(o2.getSales(), o1.getSales());
			}
		};
	}
}
