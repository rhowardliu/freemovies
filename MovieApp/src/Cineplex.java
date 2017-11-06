
public class Cineplex {
	private Cinema [] cinemas;
	private String name; //name to contain location
	
	public Cineplex(String name){
		this.cinemas = new Cinema[3];
		for (int i = 0; i <= 3; i++){
			this.cinemas[i] = new Cinema();
		}
		this.name = name;
	}
	
	public String getCineplexName(){
		return this.name;
	}
	
}
