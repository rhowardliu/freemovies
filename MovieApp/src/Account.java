import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author user
 *
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -6912793731024847631L;
	private String userID;
	private String password;
	
	/**
	 * Create new Account object with a userID (String) and password (String) attribute
	 * @param userID
	 * @param password
	 */
	public Account(String userID, String password) {
		this.userID = userID;
		this.password=password;
	}
	
	/**
	 * Get userID attribute value from Account object
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Sets a new userID for the Account object
	 * @param userID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	/**
	 * Retrieve password from the Account object
	 * @return password 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets new password for the Account object
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	
}
