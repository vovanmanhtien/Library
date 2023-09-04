package bean;

import java.sql.Date;

public class User {
	private String id;
	private String fullname;
	private String phonenumber;
	private String position;
	private String addr;
	private Date birthday;
	private String sex;
	private String nation;
	private String email;
	private String cccd;
	private int course;
	private String majors;
	//getter
	public String get_id() {
		return this.id;
	}
	public String get_fullname() {
		return this.fullname;
	}
	public String get_phonenumber() {
		return this.phonenumber;
	}
	public String get_position() {
		return this.position;
	}
	public String get_addr() {
		return this.addr;
	}
	public Date get_birthday() {
		return this.birthday;
	}
	public String get_sex() {
		return this.sex;
	}
	public String get_nation() {
		return this.nation;
	}
	public String get_email() {
		return this.email;
	}
	public String get_cccd() {
		return this.cccd;
	}
	public int get_course() {
		return this.course;
	}
	public String get_majors() {
		return this.majors;
	}
	//setter
	public void set_id(String id) {
		this.id = id;
	}
	public void set_fullname(String fullname) {
		this.fullname = fullname;
	}
	public void set_phonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public void set_position(String position) {
		this.position = position;
	}
	public void set_addr(String addr) {
		this.addr = addr;
	}
	public void set_birthday(Date birthday) {
		this.birthday = birthday;
	}
	public void set_sex(String sex) {
		this.sex = sex;
	}
	public void set_nation(String nation) {
		this.nation = nation;
	}
	public void set_email(String email) {
		this.email = email;
	}
	public void set_cccd(String cccd) {
		this.cccd = cccd;
	}
	public void set_course(int course) {
		this.course = course;
	}
	public void set_majors(String majors) {
		this.majors = majors;
	}
	//constructor
	//dataless constructor
	public User() {
	}
	//all data constructor
	public User(String id, String fullname, String phonenumber, String position, String addr, Date birthday, String sex, String nation, String email, String cccd, int course, String majors) {
		set_id(id);
		set_fullname(fullname);
		set_phonenumber(phonenumber);
		set_position(position);
		set_addr(addr);
		set_birthday(birthday);
		set_sex(sex);
		set_nation(nation);
		set_email(email);
		set_cccd(cccd);
		set_course(course);
		set_majors(majors);
	}
}
