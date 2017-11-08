import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		System.out.println("Printing Transaction History of " + this.name + ":\n");
		for (int i = 0; i <= 50; i++){
			if (transactionhistory[i] == null)
				break;
			System.out.println(transactionhistory[i].getTransactionID()+"\t"+transactionhistory[i].getPrice());
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
	
}