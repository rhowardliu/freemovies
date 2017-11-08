import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Execute {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, InvalidLogin {
		MovieGoer.initialiseDatabase();
		MovieReviews.initialiseDatabase();
		
		Account user = login();	
		if (user.getUserID().equals("admin"))
			user = (Admin) user;
		else user = (MovieGoer) user;
		
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
		if (inputUser.equals("user") && inputPW.equals("pass")) {
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