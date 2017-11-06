import java.util.Scanner;

enum MovieTypeEnum {
	_3D, BB, digital
}

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
	private MovieTypeEnum movietype;
	
	public Movie(String title, String status, String directedBy, String[] cast) {
		System.out.print("Movie title: ");
		Scanner sc = new Scanner(System.in);
		this.title = sc.next();
		this.status = "Preview";
		System.out.print("Directed by: ");
		this.directedBy = sc.next();
		this.cast = new String[10];
		System.out.print("Number of cast (List up to 10): ");
		int castnumber = sc.nextInt();
		System.out.print("Cast: ");
		for (int i = 0; i < castnumber; i++){
			this.cast[i] = sc.next();
		}
		this.updateMovieType();
	}
	
	public double getAverageRating() {
		return totalRating /ratingCount;
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
	
	public void updateMovieStatus(String status){
		this.status = status;
		System.out.println("Movie Status Updated!");
	}
	
	public void updateMovieType() {
		System.out.println("Select movie type:\n(1)3D\n(2)Blockbuster\n(3)Digital");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice){
			case 1: this.movietype = MovieTypeEnum._3D;break;
			case 2: this.movietype = MovieTypeEnum.BB; break;
			case 3: this.movietype = MovieTypeEnum.digital; break;
			case 4: {
				System.out.println("Invalid choice! Movie type set to digital by default!");
				this.movietype = MovieTypeEnum.digital;
				break;
			}
		}
		
	}
	
	public void addShowTime() {
		
	}
	
	public double getSales() {
		return totalSales;
	}
	
}
