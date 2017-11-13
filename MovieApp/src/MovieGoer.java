import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.text.SimpleDateFormat;

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
		
				
		moviegoerlist.add(this);

	}
	
	public void mgMainControl(){
		//add the control stuff here
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
		System.out.println("Transaction History of " + this.name + ":\n");
		System.out.println("***");
		List<Ticket> temp_list = transactionhistory;
		List<String> temp_date = new ArrayList<String>(); //this is a list of all dates
		
		if (transactionhistory.isEmpty()){
			System.out.println("No Transaction");
			return;
		}
		else {
			for (Ticket x: temp_list) {
				temp_date.add(x.getDate());
			}
			//the next 3 steps remove the duplicates within temp_date
			Set<String> s = new LinkedHashSet<String>(temp_date);
			temp_date.clear();
			temp_date.addAll(s);
			

	  		for(String theDate : temp_date) {
	   			List<Ticket> daytransaction = new ArrayList<Ticket>();
			  	for(Ticket x : temp_list) {
			  		if (theDate.equals(x.getDate()))
			  			daytransaction.add(x);
	  			}
		  		System.out.println(theDate);
		  		int i = 1;
		  		for (Ticket printx: daytransaction) {
		  			System.out.println(i+". " +printx.getMovietitle()+" " +printx.getPrice()+" "+printx.getTransactionID());
		  		}
			
	  		}
		}
	}
	
	public void addTransaction(Ticket ticket) {
		transactionhistory.add(ticket);
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