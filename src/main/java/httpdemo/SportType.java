package httpdemo;

public class SportType {
	private int id;
	private String name;
	
	public SportType(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
