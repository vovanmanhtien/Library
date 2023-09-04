package bean;

import java.sql.Date;

public class Book {
	private String id;
	private String name;
	private String author;
	private String catelory;
	private String company_publishing;
	private Date date_publishing;
	private int quantity;
	private int quantity_available;
	//getter
	public String get_id() {
		return this.id;
	}
	public String get_name() {
		return this.name;
	}
	public String get_author() {
		return this.author;
	}
	public String get_catelory() {
		return this.catelory;
	}
	public String get_company_publishing() {
		return this.company_publishing;
	}
	public Date get_date_publishing() {
		return this.date_publishing;
	}
	public int get_quantity() {
		return this.quantity;
	}
	public int get_quantity_available() {
		return this.quantity_available;
	}
	//setter
	public void set_id(String id) {
		this.id = id;
	}
	public void set_name(String name) {
		this.name = name;
	}
	public void set_author(String author) {
		this.author = author;
	}
	public void set_catelory(String catelory) {
		this.catelory = catelory;
	}
	public void set_company_publishing(String company_publishing) {
		this.company_publishing = company_publishing;
	}
	public void set_date_publishing(Date date_publishing) {
		this.date_publishing = date_publishing;
	}
	public void set_quantity(int quantity) {
		this.quantity = quantity;
	}
	public void set_quantity_available(int quantity_available) {
		this.quantity_available = quantity_available;
	}
	//constructor
	//dataless constructor
	public Book() {
	}
	//all data constructor
	public Book(String id, String name, String author, String catelory, String company_publishing, Date date_publishing, int quantity, int quantity_available) {
		set_id(id);
		set_name(name);
		set_author(author);
		set_catelory(catelory);
		set_company_publishing(company_publishing);
		set_date_publishing(date_publishing);
		set_date_publishing(date_publishing);
		set_quantity(quantity);
		set_quantity_available(quantity_available);
	}
}
