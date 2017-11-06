import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Execute {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		String username;
		String password;
//		Account admin = new Account("admin", "password");
//		Account admin2 = new Account("hi","pass");
//		Account admin3 = new Account ("err", "pass");
//		ObjectWriter admin_writer = new ObjectWriter(Account.accountDatabase);
//		admin_writer.writeTheObject(admin);
////		admin_writer.writeObject(admin2);
////		admin_writer.writeObject(admin3);
		Account.initialiseDatabase();
		Scanner scanner = new Scanner (System.in);
		System.out.println("Enter username");
		username=scanner.nextLine();
		System.out.println("Enter password");
		password = scanner.nextLine();
		Account admin2 = new Account(username,password);
		Account.updateDatabase();
		System.out.println("Your data has been saved");
		System.out.println(Account.accountlist.size());
		for(Account x: Account.accountlist)
			System.out.println(x.getUserID());
		
	}
	
}