import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
		
		//adding all the relevant tickets into transactionhistory
				for (Ticket x : Ticket.ticketlist) {
					if (x.getuserID()==this.getUserID())
						transactionhistory.add(x);
				}
		
		
		
		moviegoerlist.add(this);

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
		int i = transactionhistory.size()-1;
		System.out.println("Transaction History of " + this.name + ":\n");
		System.out.println("***");
		while(i>=0) {
			Ticket temp = transactionhistory.get(i);
			System.out.print(temp.getShowDateTime() + " " + temp.getTransactionID());
			System.out.printf(":%s $%6.2f",temp.getMovie().getTitle(), temp.getPrice());

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