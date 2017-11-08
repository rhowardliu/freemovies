import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Execute {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Account.initialiseDatabase();
		MovieReviews.initialiseDatabase();
		
	
		
		
	}
	
	
	public boolean login() throws InvalidLogin {
		Account user;
		String input_username;
		String input_password;
		Scanner scan = new Scanner (System.in);
		System.out.println("Enter username:");
		input_username = scan.nextLine();
		System.out.println("Enter password:");
		input_password = scan.nextLine();
		user = searchUser(input_username,input_password);
		if (user==null)
			throw new InvalidLogin();
		return false;
	}
	
	public Account searchUser(String inputUser, String inputPW) {
		for (Account x : Account.accountlist) {
			String username = x.getUserID();
			String password = x.getPassword();
			if (username.equals(inputUser)) {
				if (password.equals(inputPW))
					return x;
				return null;
			}
		}
		return null;
	}
	
	class InvalidLogin extends Exception{
		public InvalidLogin () {
		super ("Incorrect username/password.");
		}
	}
	
}