public class Movie {
	private String title;
	private String status;
	private String directedBy;
	private String[] cast;
	private String synopsis;
	private ReviewArray allMovieReviews;
	private double totalRating;
	private int ratingCount;
	private double totalSales;
	private ShowTime[] movieShowTime;
	private enum movieType{
		_3D, BB
	}
	
	public Movie(String title, String status, String directedBy, String[] cast) {
		this.title = title;
		this.status = status;
		this.directedBy = directedBy;
		this.cast = cast;
	}
	
	public double getAverageRating() {
		return totalRating /ratingCount;
	}
	
	public String getMovieInfo() {
		return synopsis;
	}
	
	public void updateMovieStatus(String status){
		this.status = status;
		System.out.println("Movie Status Updated!");
	}
	
	public void updateMovieType(String movietype) {
		
	}
	
	public void addShowTime() {
		
	}
	
	public double getSales() {
		return totalSales;
	}
	
	

}
