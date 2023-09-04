package bean;

import java.sql.Date;

public class History {
	private String id;
	private String id_user;
	private String id_book;
	private int quantity;
	private Date date_borrow;
	private Date date_back;
	private String status;
	//getter
	public String get_id() {
		return this.id;
	}
	public String get_id_user() {
		return this.id_user;
	}
	public String get_id_book() {
		return this.id_book;
	}
	public int get_quantity() {
		return this.quantity;
	}
	public Date get_date_borrow() {
		return this.date_borrow;
	}
	public Date get_date_back() {
		return this.date_back;
	}
	public String get_status() {
		return this.status;
	}
	//setter
	public void set_id(String id) {
		this.id = id;
	}
	public void set_id_user(String id_user) {
		this.id_user = id_user;
	}
	public void set_id_book(String id_book) {
		this.id_book = id_book;
	}
	public void set_quantity(int quantity){
		this.quantity = quantity;
	}
	public void set_date_borrow(Date date_borrow) {
		this.date_borrow = date_borrow;
	}
	public void set_date_back(Date date_back) {
		this.date_back = date_back;
	}
	public void set_status(String status) {
		this.status = status;
	}
	//constructor
	//dataless constructor
	public History() {
	}
	//all data constructor
	public History(String id, String id_user, String id_book, int quantity, Date date_borrow, Date date_back, String status) {
		set_id(id);
		set_id_user(id_user);
		set_id_book(id_book);
		set_quantity(quantity);
		set_date_borrow(date_borrow);
		set_date_back(date_back);
		set_status(status);
	}
}
