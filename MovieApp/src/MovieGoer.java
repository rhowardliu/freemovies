
public class MovieGoer extends Account {
	private String name;
	private String mobilenumber;
	private String email;
	private Ticket [] transactionhistory;
	
	public MovieGoer(String userID, String name, String mobilenumber, String email){
		super(userID);
		this.name = name;
		this.mobilenumber = mobilenumber;
		this.email = email;
		this.transactionhistory = new Ticket[50];
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
	public void addTransaction(Ticket ticket){
		for (int i = 0; i <= 50; i++){
			if (transactionhistory[i] == null)
				transactionhistory[i] = ticket;
		}
	}
}