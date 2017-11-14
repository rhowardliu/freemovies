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
		MovieGoer.initialiseDatabase();
		MovieReviews.initialiseDatabase();
		Movie.initialiseDatabase();
		ShowTime.initialiseDatabase();
		Ticket.initialiseDatabase();
		Timetable.initialiseDatabase();
		
		GoldenVillage.getInstance();
		
//		MovieGoer chuaye = new MovieGoer("miintfrappe","miintfrappe","ellen","999","aa@aa.com");
//		
//		new Movie("A1234", "Avatar", 2,StatusEnum.NowShowing, MovieTypeEnum._3D, "Stephen Spielburg", new ArrayList(Arrays.asList("Peter")), "Humans invade Aliens");
		Account user = login();	
		
		
		if (user.getUserID().equals("admin")){
			Admin admin = (Admin) user;
			admin.adminMainControl();
		}
		else {
			MovieGoer moviegoer = (MovieGoer) user;
			moviegoer.movieGoerMainControl();
		}
		
		MovieGoer.updateDatabase();
		MovieReviews.updateDatabase();
		Movie.updateDatabase();
		ShowTime.updateDatabase();
		Ticket.updateDatabase();
		Timetable.updateDatabase();
		
	}
	public static Account login() throws InvalidLogin {
		Account user;
		String inputUser;
		String inputPw;
		Scanner scan = new Scanner (System.in);
		System.out.println("Enter username:");
		inputUser = scan.nextLine();
		System.out.println("Enter password:");
		inputPw = scan.nextLine();
		
		return user = searchUser(inputUser, inputPw);
			
	}
	
	public static Account searchUser(String inputUser, String inputPW) throws InvalidLogin {
		if (inputUser.equals("admin") && inputPW.equals("pass")) {
			return Admin.getInstance();
		}
		
		else {
			for (Account x : MovieGoer.moviegoerlist) {
				String username = x.getUserID();
				String password = x.getPassword();
				if (username.equals(inputUser)) {
					if (password.equals(inputPW))
						return x;
					throw new InvalidLogin();
				}
			}
		}
		throw new InvalidLogin();
	}
	
	
	
	static class InvalidLogin extends Exception{
		public InvalidLogin () {
		super ("Incorrect username/password.");
		}
	}
	
}