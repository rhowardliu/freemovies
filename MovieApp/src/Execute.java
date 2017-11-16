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
		GoldenVillage.getInstance();
		StaticBoundary.initialisePrices();
		StaticBoundary.initialiseHoliday();
		MovieGoer.initialiseDatabase();
		MovieReviews.initialiseDatabase();
		ShowTime.initialiseDatabase();
		Movie.initialiseDatabase();
		Ticket.initialiseDatabase();
		System.out.println("MOBLIMA initialised!\n\n");
		//MOBLIMA's database initialise
		
		System.out.println("===== Welcome to MOBLIMA =====");
		
//		MOVIEGOERS AKA US
//		MovieGoer ellen = new MovieGoer("miintfrappe","miintfrappe","ellen","999","aa@aa.com");
//		MovieGoer gerald = new MovieGoer("gerald", "gerald", "gerald", "998", "bb@bb.com");
//		MovieGoer howard = new MovieGoer("howard", "howard", "howard", "997", "cc@bb.com");
//		MovieGoer wansin = new MovieGoer("wansin", "wansin", "wansin", "996", "dd@bb.com");
//		MovieGoer justin = new MovieGoer("justin", "justin", "justin", "996", "ee@bb.com");
//		
//		//NOWSHOWING
//		Movie Avatar = new Movie("A1234", "Avatar", 2,StatusEnum.NowShowing, MovieTypeEnum._3D, "Stephen Spielburg", new ArrayList(Arrays.asList("Peter")), "Humans invade Aliens");
//		Movie Thor = new Movie("A1235", "Thor", 2,StatusEnum.NowShowing, MovieTypeEnum.digital, "Hwdbby", new ArrayList(Arrays.asList("hwdbby", "justin")), "It's Christ Hemsworth cmon");
//		Movie Avengers = new Movie("A1236", "Avengers", 2,StatusEnum.NowShowing, MovieTypeEnum.digital, "Howard", new ArrayList(Arrays.asList("newnew")), "Mutants unite");
//		Movie Barbie = new Movie("A1237", "Barbie", 2,StatusEnum.NowShowing, MovieTypeEnum.digital, "Liu", new ArrayList(Arrays.asList("xuanxuan", "blum")), "I'm a barbie girl in a barbie world");
//		Movie ahBoystoMen = new Movie("A1243", "Ah Boys to Men", 1,StatusEnum.NowShowing, MovieTypeEnum.BB, "Dong", new ArrayList(Arrays.asList("whew", "shag")), "BMT");
//		Movie hsm = new Movie("A1242", "High School Musical", 2,StatusEnum.NowShowing, MovieTypeEnum.BB, "Zhao", new ArrayList(Arrays.asList("yay")), "We're all in this together");
//		Movie Conjuring = new Movie("A1238", "Conjuring", 3,StatusEnum.NowShowing, MovieTypeEnum.digital, "Jian", new ArrayList(Arrays.asList("what", "is", "going, on")), "Boo");
//		
//		//COMINGSOON
//		Movie thePromise = new Movie("A1239", "The Promise", 3,StatusEnum.ComingSoon, MovieTypeEnum._3D, "Ling", new ArrayList(Arrays.asList("whee", "hee", "ho", "la")), "Back to claim the promise");
//		Movie fAndF = new Movie("A1244", "Fast and Furious", 2,StatusEnum.ComingSoon, MovieTypeEnum._3D, "Nicela", new ArrayList(Arrays.asList("hand", "some", "girl")), "dap");
//		
//		//PREVIEW
//		Movie Annabelle = new Movie("A1240", "Annabelle", 2,StatusEnum.Preview, MovieTypeEnum._3D, "Justin", new ArrayList(Arrays.asList("what", "up")), "Chucky revived");
//		Movie Mylittlepony = new Movie("A1246", "My Little Pony", 2,StatusEnum.Preview, MovieTypeEnum.BB, "Glitter", new ArrayList(Arrays.asList("Ruby", "Sapphire", "DIamond", "Torpez")), "Rainbows");
//		Movie Junkenstein = new Movie("A1241", "Junkenstein", 3,StatusEnum.Preview, MovieTypeEnum.BB, "Peh", new ArrayList(Arrays.asList("justin", "is", "a", "legend")), "My servants never die");
//		

		
		
		Account user = null; //initialising user as null to allow user to log in again if he keys in the wrong username or password
		do {
			try { user = login(); }
			catch (InvalidLogin e) { System.out.println(e.toString()); }
		} while(user==null);

		if (user.getUserID().equals("admin")){ //if logged in as admin, control is passed to adminMainControl
			Admin admin = (Admin) user;
			System.out.println("Logging in...\n");
			admin.adminMainControl();
		}
		
		else { //if logged in as a moviegoer, control is passed to movieGoerMainControl
			MovieGoer moviegoer = (MovieGoer) user;
			System.out.println("Logging in...\n");
			moviegoer.movieGoerMainControl();
		}
		
		//After user logs out from MOBLIMA, MOBLIMA will save the latest changes made to its databases
		System.out.println("Saving changes...");
		MovieGoer.updateDatabase();
		MovieReviews.updateDatabase();
		Movie.updateDatabase();
		ShowTime.updateDatabase();
		Ticket.updateDatabase();
		GoldenVillage.updateDatabase();
		StaticBoundary.updatePrices();
		StaticBoundary.updateHoliday();
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