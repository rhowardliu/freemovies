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
import java.util.TreeSet;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import java.lang.Integer;
import java.lang.Long;


public class MovieGoer extends Account {
	private static final long serialVersionUID = 254911253486503839L;
	private String name;
	private String mobilenumber;
	private String email;
	private List <Ticket> transactionhistory;
	public static List<MovieGoer> moviegoerlist = new ArrayList<MovieGoer>();
	public static final File moviegoerDatabase = new File ("MovieGoer.tmp");
	
	//check if it is pushed out 
	public MovieGoer(String userID,String password, String name, String mobilenumber, String email){
		super(userID,password);
		this.name = name;
		this.mobilenumber = mobilenumber;
		this.email = email;
		transactionhistory = new ArrayList<Ticket>();
		//when a new MovieGoer object is created, it is added to MOBLIMA's database of moviegoers. 
		moviegoerlist.add(this); 
	}
	
	public void movieGoerMainControl() throws Exception {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("========== Welcome " + name + "! ==========\n");
		do {
			System.out.println(" ===== Moviegoer Main Menu =====");
			System.out.println("(1) Search for a movie");
			System.out.println("(2) View your booking history");
			System.out.println("(3) Add review");
			System.out.println("(4) Log out");
			System.out.println("Select option: ");
			switch (sc.nextInt()){
				case 1: this.searchMovie(); break;
				case 2: this.printTransactionHistory(); break;
				case 3: this.addingMovieReview(); break;
				case 4: System.out.println("Logging out..."); return;
				default: System.out.println("Invalid choice! Logging out..."); return; 
				}
		} while (true);
	}
	
	public void searchMovie() throws Exception {
		Scanner sc = new Scanner(System.in);
		StatusEnum selectedshowstatus = null;
		//Moviegoer selects which show status to view
		do {
			System.out.print("\n");
			System.out.println("Show status: ");
			System.out.println("(1) Coming Soon");
			System.out.println("(2) Preview");
			System.out.println("(3) Now Showing");
			System.out.print("Select option: ");
			int showstatuschoice = sc.nextInt();
			switch (showstatuschoice) {
				case 1: selectedshowstatus = StatusEnum.ComingSoon; break;
				case 2: selectedshowstatus = StatusEnum.Preview; break;
				case 3: selectedshowstatus = StatusEnum.NowShowing; break;
				default: System.out.println("Invalid choice! Choose show status again!\n"); break; 
			}
		} while (selectedshowstatus == null);
		
		//Moviegoer selects how the movie list should be arranged
		int movielistchoice = 0;
		List <Movie> sortedmoviearray = new ArrayList<Movie>();
		do {
			System.out.print("\n");
			System.out.println("Search movie by: ");
			System.out.println("(1) Movie Title");
			System.out.println("(2) Overall Reviewers' Rating");
			System.out.println("(3) Ticket Sales");
			System.out.print("Select option: ");
			movielistchoice = sc.nextInt();
			switch(movielistchoice){
				case 1: 
					sortedmoviearray = MovieListing.getMovieListByTitle(selectedshowstatus); 
					break;
				case 2: 
					int count=0;
					for (Movie movie : Movie.movielist) {
						if(Double.isNaN(movie.getAverageRating())==false) {
							count ++;
							break;
						}
					}
					if (count>0) {
						sortedmoviearray = MovieListing.getMovieListByRating(selectedshowstatus);
					}
					else
						System.out.print("\n");
						System.out.println("Sorry, No Rating has been added yet");
					
					break;
				case 3: 
					int count2=0;
					for (Movie movie : Movie.movielist) {
						if(movie.getSales()!=0) {
							count2 ++;
							break;
							}
						}
					if (count2>0) {
						sortedmoviearray = MovieListing.getMovieListBySales(selectedshowstatus); 
					}
					else
						System.out.print("\n");
						System.out.println("Sorry, No Sales yet");
					break;
				default: System.out.println("Invalid choice! Choose again!"); break;
			}
		} while (movielistchoice == 0);
		
		//Moviegoer chooses whether to view all movies or just the top 5 movies
		System.out.println("================");
		System.out.println("(1) List everything");
		System.out.println("(2) List Top 5");
		System.out.println("Select option: ");
		int listchoice = sc.nextInt();
		System.out.println("\n");
		System.out.println("==== Movies ====");
		switch (listchoice){ 
			default: System.out.println("Invalid choice! Listing all relevant movies by default!");
			case 1: { //if moviegoer chose to view all movies
				System.out.println("Listing all relevant movies...\n");
				int i = 1;
				for (Movie movie : sortedmoviearray)
					System.out.println("(" + (i++) + ") " + movie.getTitle());
				break;
			}
			case 2: { //if moviegoer chose to view only top 5 movies
				System.out.println("Listing top 5 movies...\n");
				int printcount = 5;
				if (sortedmoviearray.size() < printcount)
					printcount = sortedmoviearray.size();
				for (int j = 0; j < printcount; j++)
					System.out.println("(" + (j+1) + ") " + sortedmoviearray.get(j).getTitle());
				break;
			}
		}
		//by now, the user should have been able to view the movies according to his preference
		if (selectedshowstatus == StatusEnum.ComingSoon){
			System.out.println("\nMovies that are coming soon are not available in cinemas. Sorry for the inconvenience caused.");
			System.out.println("Press any key to return to main menu.");
			try{System.in.read();}catch(Exception e){	e.printStackTrace();}
			System.out.println("Returning to main menu... "); return;
		}
		getMovieChoice(sortedmoviearray);
		return;
	} //end of searchMovie() method
	
	public void getMovieChoice(List<Movie> movielist) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Movie Choice: ");
		int i = sc.nextInt();
		Movie selectedmovie = movielist.get(i - 1);
		System.out.print("\n");
		selectedmovie.getMovieInfo();
		System.out.print("\n");
		double avgRating = selectedmovie.getAverageRating();
		if (Double.isNaN(avgRating)) {
			System.out.println("Rating : - /5.0");
		}
		else 
			System.out.println("Rating : " + selectedmovie.getAverageRating() +"/5.0");
				System.out.print("\n");
		System.out.println("================");
		System.out.println("(1) View individual reviews and ratings");
		System.out.println("(2) Select cineplex to watch movie");
		System.out.println("(3) Return to MovieGoer Main Menu");
		System.out.print("Select option: "); int choice = sc.nextInt();
		switch (choice) {
			case 1: this.viewIndividualRatingsOfMovie(selectedmovie); break;
			case 2: this.displayCineplexes(selectedmovie); break;
			case 3: System.out.println("Returning to main menu..."); return;
			default: System.out.println("Invalid choice! Returning to main menu..."); return; 
		}	
		return;
	}
	
	public void viewIndividualRatingsOfMovie(Movie selectedmovie) throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.print("\n");
		System.out.println("==== Reviews ====");
		int counter=0;
		if (selectedmovie.getMovieReview().isEmpty()==true) {
			System.out.println("No Reviews Added Yet");
			System.out.print("\n");
		}
		else if (selectedmovie.getMovieReview().size() < 10) {
				counter = selectedmovie.getMovieReview().size();
			for (int k = 0; k < counter; k++) {
				double rating = selectedmovie.getMovieReview().get(k).getRating();			
				System.out.println("(" + (k+1) + ") " );
				System.out.print("Review : " + selectedmovie.getMovieReview().get(k).getReview());
				if (Double.isNaN(rating)) {
					System.out.println("Rating : - /5.0");
				}
				else {
					System.out.println("Rating : " + rating + "/5.0");
				}
				System.out.print("\n");

			}
		}
		
		System.out.println("Proceed to Select Cineplex? (Y/N) ");
		char ans = sc.next().charAt(0);
		switch (ans){
			case 'y':
			case 'Y': displayCineplexes(selectedmovie); break;
			case 'n':
			case 'N': { System.out.println("Returning to main menu..."); return; }
			default: { System.out.println("Invalid choice! Returning to main menu"); return; }	
		}
		return;
	}
	
	public void displayCineplexes(Movie movie) throws Exception {
		Scanner sc = new Scanner(System.in);
		int i=1;
		System.out.print("\n");
		System.out.println("==== Cineplexes ====");
		for(Cineplex cineplex : GoldenVillage.getInstance().getCineplexes())
			System.out.println("(" + (i++) + ") " + cineplex.getCineplexName());
		System.out.print("Enter Cineplex: "); int cineplexchoice = sc.nextInt();
		Cineplex tempcinemplexarray [] = GoldenVillage.getInstance().getCineplexes();
		//user has selected the cinema. now code supposed to display the movie's showtimes that
		//are screened at the cineplex selected
		displayShowTimes(movie, tempcinemplexarray[cineplexchoice - 1].getCineplexCode());
	}
	
	public void displayShowTimes(Movie movie, String cineplexcode) throws Exception {
		Scanner sc = new Scanner (System.in);
		System.out.print("\n");
		ShowTime showTime = movie.displayShowTimes(cineplexcode);
		if (showTime !=null) {
			char tixChoice = 0;
			do{
				showTime.showSeatLayout();
				Ticket ticket = showTime.bookTicket();
				if (ticket == null) {
					System.out.println("null");
				}
				if (ticket != null) {
					
					addTransaction(ticket);
					System.out.println("Buy more Tickets? (Y/N)");
					
					tixChoice = sc.next().charAt(0);
				}
				else 
					System.out.println("Please re-enter desired seat: ");
			
			} while (tixChoice != 'n' && tixChoice != 'N');
		}

	
		else {
			System.out.println("================");
			System.out.println("Returning to main menu...\n");
			return;
		}
		
		return;
	}
	
	public void printTransactionHistory(){
		Scanner sc = new Scanner(System.in);
		System.out.println("===== Transaction History of " + this.name + " =====\n");
		
		List<Ticket> temp_list = transactionhistory;
		List<String> temp_date = new ArrayList<String>(); //this is a list of all dates
		
		if (transactionhistory.isEmpty()==true){
			System.out.println("No Transaction");
			System.out.print("\n");
			return;
		}
		else {
			System.out.println("***");
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
			  	System.out.println();
		  		System.out.println(theDate);
		  		System.out.println("----------");
		  		int i = 1;
		  		for (Ticket printx: daytransaction) {
		  			System.out.println(i+") " +" " +printx.getMovietitle());
		  			System.out.println("Price : " +printx.getPrice());
		  			System.out.println("Transaction ID: " +printx.getTransactionID());
		  			System.out.print("\n");
		  			i++;
		  		}
		  		
			
	  		}
		}
		
	}
	

	public void addingMovieReview(){

		Scanner sc = new Scanner(System.in);
		System.out.println("===== Movie History of " + this.name + " =====\n");
		
		List<Ticket> temp_list = transactionhistory;
		
		
		if (transactionhistory.isEmpty()==true){
			System.out.println("No History. Unable to add review.");
			System.out.print("\n");
			return;
		}
		
		
		else {
			System.out.println("***");
			Collections.reverse(temp_list);
			int i=1;
			Ticket.removeDuplicates(temp_list);
			for(Ticket x : temp_list) {
				System.out.println(i + ") " + x.getMovietitle());
				i++;			
			}
			System.out.println("***");
			System.out.println("Select index of movie:");
			int selection = sc.nextInt();
			Movie movieSelected;
			try {
				movieSelected = Movie.searchMovie(temp_list.get(selection-1).getMovieID());
			} catch (Exception e) {
				System.out.println("Movie not found!");
				return;
			}
			int rating;
			do {
				System.out.print("Enter Rating(0-5): ");
				rating = sc.nextInt();
				if (rating<0 || rating>5)
					System.out.println("Invalid Rating. Only enter (0-5)");
			} while (rating<0 || rating>5);
			
			System.out.println("Enter Review:");
			Scanner scan = new Scanner(System.in).useDelimiter("\\n");
			String review = scan.next();
			movieSelected.addMovieReview(rating, review);
			System.out.println("Review added!");
			System.out.print("\n");
		}
	}	
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(moviegoerDatabase);
		moviegoerlist = or.initialiseDataList(moviegoerlist);
	}
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(moviegoerDatabase);
		ow.updateDataList(moviegoerlist);
	}
	public String getName() { return this.name; }
	public String getMobileNumber() { return this.mobilenumber; }
	public String getEmail() { return this.email; }
	public List<Ticket> getTransactionHistory() { return transactionhistory; }
	public void addTransaction(Ticket ticket) { 
		if (transactionhistory==null){
			transactionhistory=new ArrayList<Ticket>();
		}
		this.transactionhistory.add(ticket); }
	
}