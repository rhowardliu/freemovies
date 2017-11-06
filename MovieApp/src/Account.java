import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable, dataStorage {

	private static final long serialVersionUID = -6912793731024847631L;
	private String userID;
	private String password;
	private enum Status {Admin, MovieGoer};
	private String email;
	private String phoneNo;
	private Status status;
	public static List<Account> accountlist = new ArrayList<Account>();
	public static final File accountDatabase = new File ("account.txt");
	public Account(String userID, String password) {
		this.userID = userID;
		this.password=password;
		this.setStatus(Status.Admin);
		accountlist.add(this);
	}
	
	public Account(String userID, String password,String email, String phoneNo) {
		this.userID = userID;
		this.password=password;
		this.setStatus(Status.MovieGoer);
		this.email=email;
		this.phoneNo=phoneNo;
		accountlist.add(this);
	}
	
	public static void initialiseDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectReader or = new ObjectReader(accountDatabase);
		accountlist = or.initialiseDataList(accountlist);
	}
	
	public static void updateDatabase() throws FileNotFoundException, IOException {
		ObjectWriter ow = new ObjectWriter(accountDatabase);
		ow.updateDataList(accountlist);
	}
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
