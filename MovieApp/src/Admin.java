
public class Admin extends Account {

	private static final long serialVersionUID = 6340675425878862000L;
	public static final Admin INSTANCE = new Admin(); 
	
	private Admin () {
		super("admin", "pass");
	}
		
	public static Admin getInstance() {
		return INSTANCE;
	}
	
	
	
}
