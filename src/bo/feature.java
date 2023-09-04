package bo;

import java.beans.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import bean.Account;
import bean.Book;
import bean.History;
import bean.User;
import dao.dao_sql;
import view.view_book_settings;

public class feature {
	public dao_sql connectsql = new dao_sql();
	//kiem tra xem id da ton tai hay chua
	//neu co thi tra ve true
	//chua ton tai tra ve false
	public boolean checkid(String id) {
		try {
			String sqlquery = "use LIBRARI\r\n"
					+ "select * from Account\r\n"
					+ "where Account.id = ?";
			PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
			cmd.setString(1, id);
			ResultSet rs = cmd.executeQuery();
			if((rs.next())==false) return false;
			else return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	//check idbook đã tồn tai hay chưa
	//nếu có trả về true
	//chưa tồn tại trả về false
	public boolean checkid_book(String id) {
		try {
			String sqlquery = "use LIBRARI\r\n"
					+ "select * from Books\r\n"
					+ "where Books.id = ?";
			PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
			cmd.setString(1, id);
			ResultSet rs = cmd.executeQuery();
			if((rs.next())==false) return false;
			else return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	//check id user đã tồn tai hay chưa
	//nếu có trả về true
	//chưa tồn tại trả về false
	public boolean checkid_user(String id) {
		try {
			String sqlquery = "use LIBRARI\r\n"
					+ "select * from Information\r\n"
					+ "where Information.id = ?";
			PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
			cmd.setString(1, id);
			ResultSet rs = cmd.executeQuery();
			if((rs.next())==false) return false;
			else return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	//trả kết qua khi lấy thông tin user từ database bằng Id nhập vào
	public ResultSet get_rsult_user(String id) {
		User data = new User();
		try {
			String sqlquery = "use LIBRARI\r\n"
					+"select * from Information\r\n"
					+"where Information.Id = ?";
			PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
			cmd.setString(1, id);
			ResultSet rs = cmd.executeQuery();
			return rs;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//đưa thông tin user lấy được vào class User
	public User get_user(String id) {
		User data;
		ResultSet rs = get_rsult_user(id);
		try {
			if(rs.next()) {
				String fullname = rs.getString("fullname");
				String addr = rs.getString("addr");
				Date birthday = rs.getDate("birthday");
				String cccd = rs.getString("cccd");
				String email = rs.getString("email");
				String nation = rs.getString("nation");
				String phonenumber = rs.getString("phonenumber");
				String position = rs.getString("position");
				String sex = rs.getString("sex");
				int course = rs.getInt("course");
				String majors = rs.getString("majors");
				data = new User(id, fullname, phonenumber, position, addr, birthday, sex, nation, email, cccd, course, majors);
				return data;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//LOGIN
	//kiểm tra xem tài khoản, mật khẩu, chức năng của người dùng nhập vào có tồn tại đúng với trong csdl
	public boolean login(Account user) {
		try {
			Account ac_us = user;
			String sqlquery = "use LIBRARI\r\n"
					+ "select Account.id from Account, Information\r\n"
					+ "where Account.id = ? and Account.pass = ? and Information.position = ? and Account.id = Information.id";
			PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
			cmd.setString(1, user.get_id());
			cmd.setString(2, user.get_password());
			cmd.setString(3, user.get_position());
			ResultSet rs  = cmd.executeQuery();
			if((rs.next())==false) return false;
			else return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
	//lấy thông tin tất cả sách
	public ArrayList<Book> get_inforbooks(){
		ArrayList<Book> list = new ArrayList<Book>();
		try {
			String sqlquery = "use LIBRARI\r\n"
					+ "select * from Books";
			PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
			ResultSet rs  = cmd.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String fullnam = rs.getString("fullname");
				String author = rs.getString("author");
				String catelory = rs.getString("catelory");
				String compay_publishing = rs.getString("company_publishing");
				Date date_publishing = rs.getDate("date_publishing");
				int quantity = rs.getInt("quantity");
				int quantity_available = rs.getInt("quantity_available");
				Book data = new Book(id, fullnam, author, catelory, compay_publishing, date_publishing, quantity, quantity_available);
				list.add(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
//lấy thông tin sách theo yêu cầu
//lấy thông tin sách theo mã sách
	public ResultSet rsult(String text, String sqlquery) {
		ResultSet rs = null;
		try {
			PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
			cmd.setString(1, text);
			rs  = cmd.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
	}
//lấy thông tin tất cả user theo yêu cầu đưa vào arraylist kiểu ob user
	public ArrayList<User> get_user(String text, String dt){
		ArrayList<User> list = new ArrayList<User>();
		try {
				ResultSet rs = null;
				//sử dụng sw-case để tìm kiếm theo case(đối tượng chọn muốn tìm kiếm theo loại nào mã sách-tên sách-...)
				switch (dt) {
				case "Mã Số Sinh Viên": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Information\r\n"
							+ "where Information.id like ?";
					rs  = rsult(text,sqlquery);
					break;
				}
				case "Họ Tên": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Information\r\n"
							+ "where Information.fullname like ?";
					rs  = rsult(text,sqlquery);
					break;
				}
				case "Khóa Học": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Information\r\n"
							+ "where Information.course like ?";
					rs  = rsult(text,sqlquery);
					break;
				}
				case "Ngành": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Information\r\n"
							+ "where Information.majors like ?";
					rs  = rsult(text,sqlquery);
					break;
				}
					default:
						break;
					}
					while(rs.next()) {
						String id = rs.getString("id");
						String fullname = rs.getString("fullname");
						String phonenumber = rs.getString("phonenumber");
						String addr = rs.getString("addr");
						String sex = rs.getString("sex");
						Date birthday = rs.getDate("birthday");
						String nation = rs.getString("nation");
						String email = rs.getString("email");
						String cccd = rs.getString("cccd");
						int course = rs.getInt("course");
						String majors = rs.getString("majors");
						User data = new User(id, fullname, phonenumber, nation, addr, birthday, sex, nation, email, cccd, course, majors);
						list.add(data);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return list;
			}
//lấy thông tin tất cả sách đưa vào arraylist kiểu ob book
		public ArrayList<Book> get_book(String text, String dt){
			ArrayList<Book> list = new ArrayList<Book>();
			try {
				ResultSet rs = null;
				//sử dụng sw-case để tìm kiếm theo case(đối tượng chọn muốn tìm kiếm theo loại nào mã sách-tên sách-...)
				switch (dt) {
				case "Mã Sách": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Books\r\n"
							+ "where Books.id like ?";
					rs  = rsult(text,sqlquery);
					break;
				}
				case "Tên Sách": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Books\r\n"
							+ "where Books.fullname like ?";
					rs  = rsult(text,sqlquery);
					break;
				}
				case "Thể Loại": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Books\r\n"
							+ "where Books.catelory like ?";
					rs  = rsult(text,sqlquery);
					break;
				}
				case "Năm Xuất Bản": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Books\r\n"
							+ "where Books.date_publishing like ?";
					rs  = rsult(text,sqlquery);
					break;
				}
				case "Nhà Xuất Bản": {
					String sqlquery = "use LIBRARI\r\n"
							+ "select * from Books\r\n"
							+ "where YEAR(Books.company_publishing) = ?";
					rs  = rsult(text,sqlquery);
					break;
				}
				default:
					break;
				}
				while(rs.next()) {
					String id = rs.getString("id");
					String fullnam = rs.getString("fullname");
					String author = rs.getString("author");
					String catelory = rs.getString("catelory");
					String compay_publishing = rs.getString("company_publishing");
					Date date_publishing = rs.getDate("date_publishing");
					int quantity = rs.getInt("quantity");
					int quantity_available = rs.getInt("quantity_available");
					Book data = new Book(id, fullnam, author, catelory, compay_publishing, date_publishing, quantity, quantity_available);
					list.add(data);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
		}
//lấy số lượng id mượn sách hiện tại
		public int select_idhistory() {
			try {
				String sqlquery = "select count(id) as id from History";
				PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
				ResultSet rs = cmd.executeQuery();
				if(rs.next()) {
					int id = rs.getInt("id");
					return id;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return 0;
		}
//thêm 1 lịch sử mượn sách
		public void book_borrow(String id_user, String id_book, int quantity) {
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				Date dt_borrow = Date.valueOf(java.time.LocalDate.now());		//lấy ngày lúc lập phiếu mượn
				Date dt_back = Date.valueOf(java.time.LocalDate.now());			//thời gian trả tối đa là 1 năm sau ngày mượn
				dt_back.setYear(dt_back.getYear()+1);
				String status = "Chưa nhận sách";
				String sqlupdate = "USE LIBRARI\r\n"
						+ "INSERT INTO History(id, id_user, id_book, quantity, date_borrow, date_back, stus)\r\n"
						+ "VALUES\r\n"
						+ "(?, ?, ?, ?, ?, ?, ?)";
				Calendar c = Calendar.getInstance();
				PreparedStatement cmd = connectsql.cn.prepareStatement(sqlupdate);
				cmd.setInt(1, (select_idhistory()));
				cmd.setString(2, id_user);
				cmd.setString(3, id_book);
				cmd.setInt(4, quantity);
				cmd.setDate(5, dt_borrow);
				cmd.setDate(6, dt_back);
				cmd.setString(7, status);
				cmd.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//kiểm tra số lượng sách hiện tại có đủ để cho mượn với số lượng sinh viên đưa ra
		public int check_count_book(String idbook, int count,String func) {
			try {
				String sqlcheck = "SELECT quantity_available FROM Books\r\n"
						+ "WHERE Books.id = ?";
				PreparedStatement cmd = connectsql.cn.prepareStatement(sqlcheck);
				cmd.setString(1, idbook);
				ResultSet rs  = cmd.executeQuery();
				rs.next();
				int quantity_available = rs.getInt("quantity_available");
				if(func == "Mượn") {
					if(quantity_available>=count) return (quantity_available-count);
					else return -1;
				}
				else if(func=="Trả") {
					return quantity_available;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return -1;
		}
//tăng, giảm số lượng sách tồn tại trong kho tương ứng với số lượng sách sinh viên đã trả, đã mượn
		public void count_book(String idbook, int count, String func) {
			try {
				connectsql.connect();
				java.sql.Statement statement = connectsql.cn.createStatement();
				String sqlupdate = "USE LIBRARI\r\n"
						+ "UPDATE Books\r\n"
						+ "SET Books.quantity_available ="+ count +"\r\n"
						+ "WHERE Books.id = '"+idbook+"'";
				statement.executeUpdate(sqlupdate);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//sinh viên hủy đăng ký mượn sách
		public void cancel_enfor(int id) {
			try {
				connectsql.connect();
				java.sql.Statement statement = connectsql.cn.createStatement();
				String sqlupdate = "USE LIBRARI\r\n"
						+ "update History\r\n"
						+ "set stus = N'Đã hủy'\r\n"
						+ "where History.id = "+id;
				statement.executeUpdate(sqlupdate);
				JOptionPane.showMessageDialog(null, "Bạn đã hủy mượn sách thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//sinh viên trả sách, set trạng thái sang đã trả sách
		public void back_book(int id) {
			try {
				connectsql.connect();
				java.sql.Statement statement = connectsql.cn.createStatement();
				String sqlupdate = "USE LIBRARI\r\n"
						+ "update History\r\n"
						+ "set stus = N'Đã trả sách'\r\n"
						+ "where History.id = "+id;
				statement.executeUpdate(sqlupdate);
				JOptionPane.showMessageDialog(null, "Đã thay đổi trạng thái thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//sinh viên tới nhận sách, set trạng thái sang đã nhận sách
		public void receive_book(int id) {
			try {
				connectsql.connect();
				java.sql.Statement statement = connectsql.cn.createStatement();
				String sqlupdate = "USE LIBRARI\r\n"
						+ "update History\r\n"
						+ "set stus = N'Đã nhận sách'\r\n"
						+ "where History.id = "+id;
				statement.executeUpdate(sqlupdate);
				JOptionPane.showMessageDialog(null, "Đã thay đổi trạng thái thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//xử lý đăng ký mượn sách
		public void enfor(String id_user, String id_book, int quantity) {
			try {
				int count = check_count_book(id_book, quantity, "Mượn");
				if(count>=0) {
					book_borrow(id_user, id_book, quantity);
					count_book(id_book, count, "Mượn");
					//thời hạn của 1 người kể từ lúc bắt đầu mượn sách đến ngày nhận sách cuối cùng là 7 ngày
					//nếu sau 7 ngày vẫn chưa tới mượn thì lệnh mượn sách trên sẽ chuyển từ trạng thái
					//"Chưa nhận sách" sang trạng thái "Đã hủy"
					JOptionPane.showMessageDialog(null, "Trong vòng 7 ngày nếu bạn không đến nhận sách, lần mượn sách này sẽ bị hủy!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Số lượng sách không đủ để đăng ký!");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
//lấy lịch sử mượn-trả sách của 1 user
		public ArrayList<History> get_user_his(String id_user){
			ArrayList<History> list = new ArrayList<History>();
			try {
				String sqlquery = "select * from History\r\n"
						+ "where History.id_user = ?";
				PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
				cmd.setString(1, id_user);
				ResultSet rs  = cmd.executeQuery();
				while(rs.next()) {
					String id = rs.getString("id");
					String id_book = rs.getString("id_book");
					int quantity = rs.getInt("quantity");
					Date date_borrow = rs.getDate("date_borrow");
					Date date_back = rs.getDate("date_back");
					String status = rs.getString("stus");
					History data = new History(id, id_user, id_book, quantity, date_borrow, date_back, status);
					list.add(data);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return list;
		}
//lấy lịch sử mượn-trả sách của tất cả user
				public ArrayList<History> get_his(){
					ArrayList<History> list = new ArrayList<History>();
					try {
						String sqlquery = "select * from History";
						PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
						ResultSet rs  = cmd.executeQuery();
						while(rs.next()) {
							String id = rs.getString("id");
							String id_book = rs.getString("id_book");
							String id_user =rs.getString("id_user");
							int quantity = rs.getInt("quantity");
							Date date_borrow = rs.getDate("date_borrow");
							Date date_back = rs.getDate("date_back");
							String status = rs.getString("stus");
							History data = new History(id, id_user, id_book, quantity, date_borrow, date_back, status);
							list.add(data);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					return list;
				}
//thêm sách mới
				public void create_book(Book data) {
					try {
						//thêm 1 dòng dữ liệu sách mới
						String sqlupdate = "INSERT INTO Books(id, fullname, author, catelory, company_publishing, date_publishing, quantity, quantity_available)\r\n"
								+ "VALUES\r\n"
								+ "	(?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement cmd = connectsql.cn.prepareStatement(sqlupdate);
						cmd.setString(1, data.get_id());
						cmd.setString(2, data.get_name());
						cmd.setString(3, data.get_author());
						cmd.setString(4, data.get_catelory());
						cmd.setString(5, data.get_company_publishing());
						cmd.setDate(6, data.get_date_publishing());
						cmd.setInt(7, data.get_quantity());
						cmd.setInt(8, data.get_quantity_available());
						cmd.executeUpdate();
						cmd.close();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
//lấy thông tin sách từ frame book_settings để thêm sách mới vào
				public boolean add(Book data) {
					try {
						if(!checkid_book(data.get_id())) {
							create_book(data);
						}
						else {
							JOptionPane.showMessageDialog(null, "ID đã tồn tại!");
							return false;
						}
					} catch (Exception e) {
						// TODO: handle exceptionecheckid_book
						e.printStackTrace();
					}
					return true;
				}
//thay đổi thông tin sách
				public void update_book(Book data) {
					try {
						connectsql.connect();
						java.sql.Statement statement = connectsql.cn.createStatement();
						String sqlupdate = "use LIBRARI\r\n"
								+ "update Books\r\n"
								+ "SET \r\n"
								+ "fullname = '"+(data.get_name())+"', \r\n"
								+ "author = '"+(data.get_author())+"', \r\n"
								+ "catelory = '"+(data.get_catelory())+"', \r\n"
								+ "company_publishing = '"+(data.get_company_publishing())+"', \r\n"
								+ "date_publishing = '"+(data.get_date_publishing())+"', \r\n"
								+ "quantity = "+(data.get_quantity())+", \r\n"
								+ "quantity_available = "+(data.get_quantity_available())+"\r\n"
								+ "WHERE ID = '"+(data.get_id())+"'";
						statement.executeUpdate(sqlupdate);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
//cập nhật các thông tin thay đổi của sách
				public void change(Book data) {
					update_book(data);
				}
//lấy thông tin tất cả user(sinh viên)
				public ArrayList<User> get_inforusers(){
					ArrayList<User> list = new ArrayList<User>();
					try {
						String sqlquery = "USE LIBRARI\r\n"
								+ "SELECT * FROM Information\r\n"
								+ "WHERE Information.position = 'Sinh Viên'";
						PreparedStatement cmd = connectsql.cn.prepareStatement(sqlquery);
						ResultSet rs  = cmd.executeQuery();
						while(rs.next()) {
							String id = rs.getString("id");
							String fullnam = rs.getString("fullname");
							String phonenumber = rs.getString("phonenumber");
							String addr = rs.getString("addr");
							Date birthday = rs.getDate("birthday");
							String sex = rs.getString("sex");
							String nation = rs.getString("nation");
							String email = rs.getString("email");
							String cccd = rs.getString("cccd");
							int course = rs.getInt("course");
							String majors = rs.getString("majors");
							User data = new User(id, fullnam, phonenumber, nation, addr, birthday, sex, nation, email, cccd, course, majors);
							list.add(data);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					return list;
				}
//cập nhật thông tin sinh viên
				public void update_user(User data) {
					try {
						connectsql.connect();
						java.sql.Statement statement = connectsql.cn.createStatement();
						String sqlupdate = "USE LIBRARI\r\n"
								+ "UPDATE Information\r\n"
								+ "SET \r\n"
								+ "phonenumber = '"+(data.get_phonenumber())+"', \r\n"
								+ "addr = N'"+(data.get_addr())+"', \r\n"
								+ "sex = N'"+(data.get_sex())+"', \r\n"
								+ "nation = N'"+(data.get_nation())+"', \r\n"
								+ "birthday = '"+(data.get_birthday())+"', \r\n"
								+ "email = '"+(data.get_email())+"', \r\n"
								+ "cccd = "+(data.get_cccd())+"\r\n"
								+ "WHERE ID = '"+(data.get_id())+"'";
						statement.executeUpdate(sqlupdate);
						JOptionPane.showMessageDialog(null, "Thay đổi thông tin thành công!");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
/*
 * 	đăng ký tài khoản đăng nhập cho sinh viên mới
 * 	mật khẩu mặt định là 123456
 */
				public void create_account(String id) {
					try {
						String sqlcreate = "INSERT INTO Account(id, pass)\r\n"
								+ "VALUES\r\n"
								+ "(?, ?)";
						PreparedStatement cmd = connectsql.cn.prepareStatement(sqlcreate);
						cmd.setString(1, id);
						cmd.setString(2, "123456");
						cmd.executeUpdate();
						cmd.close();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
//đăng ký thông tin sinh viên mới
				public void create_infor_user(User data) {
					try {
						//thêm 1 dòng dữ liệu sách mới
						String sqlcreate = "INSERT INTO Information(id, fullname, phonenumber, position, addr, birthday, sex, nation, email, cccd, course, majors)\r\n"
								+ "VALUES\r\n"
								+ "	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement cmd = connectsql.cn.prepareStatement(sqlcreate);
						cmd.setString(1, data.get_id());
						cmd.setString(2, data.get_fullname());
						cmd.setString(3, data.get_phonenumber());
						cmd.setString(4, "Sinh Viên");
						cmd.setString(5, data.get_addr());
						cmd.setDate(6, data.get_birthday());
						cmd.setString(7, data.get_sex());
						cmd.setString(8, data.get_nation());
						cmd.setString(9, data.get_email());
						cmd.setString(10, data.get_cccd());
						cmd.setInt(11, data.get_course());
						cmd.setString(12, data.get_majors());
						cmd.executeUpdate();
						cmd.close();
						JOptionPane.showMessageDialog(null, "Tạo thông tin mới thành công!");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
//function để gọi hàm đăng ký sinh viên mới
				public void func_create_user(User data) {
					//check id nếu mã sinh viên chưa tồn tại trong hệ thông thì tạo mới thông tin
					if(!checkid_user(data.get_id())) {
						//tạo account để đăng nhập
						create_account(data.get_id());
						//đưa thông tin vào hệ thống
						create_infor_user(data);
					}
					else
						JOptionPane.showMessageDialog(null, "Mã sinh viên đã tồn tại trong hệ thống!");
				}
//thay đổi mật khẩu
				public void change_password(String id, String password) {
					try {
						connectsql.connect();
						java.sql.Statement statement = connectsql.cn.createStatement();
						String sqlupdate = "USE LIBRARI\r\n"
								+"UPDATE Account\r\n"
								+"SET Account.pass = '"+password+"'\r\n"
								+"WHERE Account.id ='"+id+"'";
						statement.executeUpdate(sqlupdate);
						JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công!");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
}








