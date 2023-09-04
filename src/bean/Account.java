package bean;

//LOGIN
public class Account {
	private String id;
	private String password;
	private String position;
	//getter
	public String get_id() {
		return this.id;
	}
	public String get_password() {
		return this.password;
	}
	public String get_position() {
		return this.position;
	}
	//setter
	public void set_id(String id) {
		this.id = id;
	}
	public void set_password(String password) {
		this.password = password;
	}
	public void set_position(String position) {
		this.position = position;
	}
	//constructor
	//dataless constructor 
	public Account(){
	}
	//all data constructor
	public Account(String id, String password, String position){
		set_id(id);
		set_password(password);
		set_position(position);
	}
}
