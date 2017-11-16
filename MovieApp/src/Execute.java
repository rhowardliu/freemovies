import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Execute {
	public static void main(String[] args) throws Exception {
		//Initialising MOBLIMA's database
		System.out.println("MOBLIMA is fetching its databases...");
		StaticBoundary.initialisePrices();
		MovieGoer.initialiseDatabase();
		MovieReviews.initialiseDatabase();
		ShowTime.initialiseDatabase();
		Movie.initialiseDatabase();
		Ticket.initialiseDatabase();
		GoldenVillage.getInstance();
		System.out.println("MOBLIMA initialised!\n\n");
		//MOBLIMA's database initialise
		
		System.out.println("===== Welcome to MOBLIMA =====");
		
//		MovieGoer chuaye = new MovieGoer("miintfrappe","miintfrappe","ellen","999","aa@aa.com");
//		Movie Avatar = new Movie("A1234", "Avatar", 2,StatusEnum.NowShowing, MovieTypeEnum._3D, "Stephen Spielburg", new ArrayList(Arrays.asList("Peter")), "Humans invade Aliens");
//		MovieGoer chuaye = new MovieGoer("miintfrappe","miintfrappe","ellen","999","aa@aa.com");
//		
//		Now Showing
//		Movie ahBoystoMen = new Movie("A1243", "Ah Boys to Men", 1,StatusEnum.NowShowing, MovieTypeEnum.BB, "Dong", new ArrayList(Arrays.asList("whew", "shag")), "BMT");
//		Movie Thor = new Movie("A1235", "Thor", 2,StatusEnum.NowShowing, MovieTypeEnum.digital, "Hwdbby", new ArrayList(Arrays.asList("hwdbby", "justin")), "It's Christ Hemsworth cmon");
//		Movie JusticeLeague = A1245;
//		Movie Blade Runner = A1246
//		Movie Kingsman = A1247;
//		Movie Conjuring = new Movie("A1238", "Conjuring", 3,StatusEnum.NowShowing, MovieTypeEnum.digital, "Jian", new ArrayList(Arrays.asList("what", "is", "going, on")), "Boo");
//		Movie hsm = new Movie("A1242", "High School Musical", 2,StatusEnum.NowShowing, MovieTypeEnum.BB, "Zhao", new ArrayList(Arrays.asList("yay")), "We're all in this together");

		
		//Coming Soon
		//Movie StarWars = A1248;
//		Movie thePromise = new Movie("A1239", "The Promise", 3,StatusEnum.ComingSoon, MovieTypeEnum._3D, "Ling", new ArrayList(Arrays.asList("whee", "hee", "ho", "la")), "Back to claim the promise");
//		Movie fAndF = new Movie("A1244", "Fast and Furious", 2,StatusEnum.ComingSoon, MovieTypeEnum._3D, "Nicela", new ArrayList(Arrays.asList("hand", "some", "girl")), "dap");

		
		
		//Preview
//		Movie Annabelle = new Movie("A1240", "Annabelle", 2,StatusEnum.Preview, MovieTypeEnum._3D, "Justin", new ArrayList(Arrays.asList("what", "up")), "Chucky revived");
//		Movie Mylittlepony = new Movie("A1241", "Junkenstein", 3,StatusEnum.Preview, MovieTypeEnum.BB, "Peh", new ArrayList(Arrays.asList("justin", "is", "a", "legend")), "My servants never die");

		
		//End of Show
//		Movie Avatar = new Movie("A1234", "Avatar", 2,StatusEnum.NowShowing, MovieTypeEnum._3D, "Stephen Spielburg", new ArrayList(Arrays.asList("Peter")), "Humans invade Aliens");
//		Movie Avengers = new Movie("A1236", "Avengers", 2,StatusEnum.NowShowing, MovieTypeEnum.digital, "Howard", new ArrayList(Arrays.asList("newnew")), "Mutants unite");
//		Movie Barbie = new Movie("A1237", "Barbie", 2,StatusEnum.NowShowing, MovieTypeEnum.digital, "Liu", new ArrayList(Arrays.asList("xuanxuan", "blum")), "I'm a barbie girl in a barbie world");

//		Movie Conjuring = new Movie("A1238", "Conjuring", 3,StatusEnum.NowShowing, MovieTypeEnum.digital, "Jian", new ArrayList(Arrays.asList("what", "is", "going, on")), "Boo");
//		Movie thePromise = new Movie("A1239", "The Promise", 3,StatusEnum.ComingSoon, MovieTypeEnum._3D, "Ling", new ArrayList(Arrays.asList("whee", "hee", "ho", "la")), "Back to claim the promise");
//		Movie Annabelle = new Movie("A1240", "Annabelle", 2,StatusEnum.Preview, MovieTypeEnum._3D, "Justin", new ArrayList(Arrays.asList("what", "up")), "Chucky revived");
//		Movie Junkenstein = new Movie("A1241", "Junkenstein", 3,StatusEnum.Preview, MovieTypeEnum.BB, "Peh", new ArrayList(Arrays.asList("justin", "is", "a", "legend")), "My servants never die");
//		Movie hsm = new Movie("A1242", "High School Musical", 2,StatusEnum.NowShowing, MovieTypeEnum.BB, "Zhao", new ArrayList(Arrays.asList("yay")), "We're all in this together");
//		Movie ahBoystoMen = new Movie("A1243", "Ah Boys to Men", 1,StatusEnum.NowShowing, MovieTypeEnum.BB, "Dong", new ArrayList(Arrays.asList("whew", "shag")), "BMT");
//		Movie fAndF = new Movie("A1244", "Fast and Furious", 2,StatusEnum.ComingSoon, MovieTypeEnum._3D, "Nicela", new ArrayList(Arrays.asList("hand", "some", "girl")), "dap");
//		Avatar.addMovieReview(4.5, "awesome blue people");
//		Thor.addMovieReview(4.0, "I love Chris Hemsworth. I love loki more.");
//		Avengers.addMovieReview(4.1, "FIGHT!");
//		Barbie.addMovieReview(4.5, "I feel like a Bimbo now");
//		Conjuring.addMovieReview(4.2, "magic~ simply amazing");
//		thePromise.addMovieReview(4.3, "similar to the ring. not too bad.");
//		Annabelle.addMovieReview(4.4, "chucky scarier nia");
//		Junkenstein.addMovieReview(4.5, "mercy is amazing");
//		hsm.addMovieReview(4.6, "bibbity boppity boo");
//		ahBoystoMen.addMovieReview(3.7, "I love singapore");
//		fAndF.addMovieReview(3.9, "exciting");
//		new ShowTime("Avatar", "A1234", "GV-Jurong", "J", "01", "a", 2, 5, 6, CinemaTypeEnum._platinum);
//		Account user = login();	
//		new Movie("A1234", "Avatar", 2,StatusEnum.NowShowing, MovieTypeEnum._3D, "Stephen Spielburg", new ArrayList(Arrays.asList("Peter")), "Humans invade Aliens");
		
		Account user = null; //initialising user as null to allow user to log in again if he keys in the wrong username or password
		do {
			try { user = login(); }
			catch (InvalidLogin e) { System.out.println(e.toString()); }
		} while(user==null);

		if (user.getUserID().equals("admin")){ //if logged in as admin, control is passed to adminMainControl
			Admin admin = (Admin) user;
			admin.adminMainControl();
		}
		
		else { //if logged in as a moviegoer, control is passed to movieGoerMainControl
			MovieGoer moviegoer = (MovieGoer) user;
			moviegoer.movieGoerMainControl();
		}
		
		//After user logs out from MOBLIMA, MOBLIMA will save the latest changes made to its databases
		System.out.println("Saving changes...");
		MovieGoer.updateDatabase();
		MovieReviews.updateDatabase();
		Movie.updateDatabase();
		ShowTime.updateDatabase();
		Ticket.updateDatabase();
		Timetable.updateDatabase();
		StaticBoundary.updatePrices();
		System.out.println("Changes saved!");
	}
	
	public static Account login() throws InvalidLogin {
		Account user = null;
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter username:");
		String inputUser = sc.nextLine();
		System.out.print("Enter password:");
		String inputPw = sc.nextLine();
		return user = searchUser(inputUser, inputPw);		
	}
	
	public static Account searchUser(String inputUser, String inputPW) throws InvalidLogin {
		if (inputUser.equals("admin") && inputPW.equals("pass")) //login check for admin account
			return Admin.getInstance();
		
		else { //checking user input against moviegoer account database
			for (Account x : MovieGoer.moviegoerlist) {
				String username = x.getUserID(); String password = x.getPassword();
				if (username.equals(inputUser) && password.equals(inputPW)) 
						return x;
			}
		}
		
		//if login fails as an admin or moviegoer then InvalidLogin will be thrown
		throw new InvalidLogin();
	}
	
	//Custom made exception for InvalidLogin
	static class InvalidLogin extends Exception{
		public InvalidLogin () {
			super ("Incorrect username/password.");
		}
	}
	
}