import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class MovieGoer extends Account {

	private static final long serialVersionUID = 254911253486503839L;
	private String name;
	private String mobilenumber;
	private String email;
	private List <Ticket> transactionhistory;
	public static List<MovieGoer> moviegoerlist = new ArrayList<MovieGoer>();
	public static final File moviegoerDatabase = new File ("MovieGoer.txt");
	
	public MovieGoer(String userID,String password, String name, String mobilenumber, String email){
		super(userID,password);
		this.name = name;
		this.mobilenumber = mobilenumber;
		this.email = email;
		
				
		moviegoerlist.add(this);

	}
	public void movieGoerMainControl() {
		System.out.println("===== Welcome! " +name +"=====");
		do {
		System.out.println("Select option:");
		System.out.println("(1) Search movie");
		System.out.println("(2) View Booking History");
		System.out.println("(3) Log out");
		
		Scanner sc = new Scanner(System.in);
		int mgmainmenuchoice = sc.nextInt();
		
			switch (mgmainmenuchoice){
			case 1: this.searchMovie(); break;
			case 2: this.adminShowTimeControl(); break;
			case 3: this.adminSystemControl(); break;
			case 4: System.out.println("Logging out..."); return;
			default: System.out.println("Invalid choice! Logging out..."); return; 
			}
		} while (true);
	}
	
	public void searchMovie() {
		//need to loop this
		//should i create a if selection is out of range...blah, okay la if got time
		
		System.out.println("Search Movie By: ");
		System.out.println("(1) Movie Title");
		System.out.println("(2) Overall Reviewers' Rating");
		System.out.println("(3) Ticket Sales");
		Scanner sc = new Scanner(System.in);
		int movieListChoice = sc.nextInt();
		System.out.println("Status of Movie");
		System.out.println("(1) Coming Soon");
		System.out.println("(2) Preview");
		System.out.println("(3) Now Showing");
		int statusChoice = sc.nextInt();
		System.out.println("(1) List everything");
		System.out.println("(2) List Top 5");
		int listChoice = sc.nextInt();
		StatusEnum status = null;
		switch (statusChoice) {
		case 1:
			status = StatusEnum.ComingSoon;
			break;
		case 2:
			status = StatusEnum.Preview;
			break;
		case 3:
			status = StatusEnum.NowShowing;
			break;
		}
		
		switch (movieListChoice) {
		case 1:
			if(listChoice == 1) {
				int i=1;
				for (Movie movie : MovieListing.getMovieListByTitle(status)) {
					System.out.println(i +") " +movie.getTitle() );
				}
			}
			if (listChoice == 2) {
					for (int j=0;j<5;j++) {
						System.out.println(j +") " +MovieListing.getMovieListByTitle(status).get(j).getTitle());	
					}
				}
			getMovieChoice(MovieListing.getMovieListByTitle(status));
			break;
		case 2:
			if(listChoice == 1) {
				int i=1;
				for (Movie movie : MovieListing.getMovieListByRating(status)) {
					System.out.println(i +") " +movie.getTitle() );
				}
			}
			if (listChoice == 2) {
					for (int j=0;j<5;j++) {
						System.out.println(j +") " +MovieListing.getMovieListByRating(status).get(j).getTitle());	
					}
				}
			getMovieChoice(MovieListing.getMovieListByRating(status));
			break;
		case 3:
			if(listChoice == 1) {
				int i=1;
				for (Movie movie : MovieListing.getMovieListBySales(status)) {
					System.out.println(i +") " +movie.getTitle() );
				}
			}
			if (listChoice == 2) {
					for (int j=0;j<5;j++) {
						System.out.println(j +") " +MovieListing.getMovieListBySales(status).get(j).getTitle());	
					}
				}
			getMovieChoice(MovieListing.getMovieListBySales(status));
			break;
		}

	}
	
	public void getMovieChoice(List<Movie> movieList) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Movie Choice: ");
		int i = sc.nextInt();
		movieList.get(i-1).getMovieInfo();
		movieList.get(i-1).getAverageRating();
		System.out.println(" ");
		System.out.println("(1) View Individual Reviews and Ratings");
		System.out.println("(2) View ShowTimes");
		System.out.println("(3) Return ");
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			
			break;
		case 2:
			movieList.get(i-1).displayShowTimes();
			break;
		case 3:
			searchMovie();
			break;
		}
		
			
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getMobileNumber(){
		return this.mobilenumber;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void printTransactionHistory(){
		System.out.println("Transaction History of " + this.name + ":\n");
		System.out.println("***");
		List<Ticket> temp_list = transactionhistory;
		List<String> temp_date = new ArrayList<String>(); //this is a list of all dates
		
		if (transactionhistory.isEmpty()){
			System.out.println("No Transaction");
			return;
		}
		else {
			for (Ticket x: temp_list) {
				temp_date.add(x.getDate());
			}
			//the next 3 steps remove the duplicates within temp_date
			Set<String> s = new LinkedHashSet<String>(temp_date);
			temp_date.clear();
			temp_date.addAll(s);
			

	  		for(String theDate : temp_date) {
	   			List<Ticket> daytransaction = new ArrayList<Ticket>();
			  	for(Ticket x : temp_list) {
			  		if (theDate.equals(x.getDate()))
			  			daytransaction.add(x);
	  			}
		  		System.out.println(theDate);
		  		int i = 1;
		  		for (Ticket printx: daytransaction) {
		  			System.out.println(i+". " +printx.getMovietitle()+" " +printx.getPrice()+" "+printx.getTransactionID());
		  		}
			
	  		}
		}
	}
	
	public void addTransaction(Ticket ticket) {
		transactionhistory.add(ticket);
	}
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(moviegoerDatabase);
		moviegoerlist = or.initialiseDataList(moviegoerlist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(moviegoerDatabase);
		ow.updateDataList(moviegoerlist);
	}
	
}