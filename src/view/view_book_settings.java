package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Book;
import bo.feature;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.file.ReadOnlyFileSystemException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//frame chỉnh sửa or thêm mới book
public class view_book_settings extends JFrame {
	public String func;				//để xác định buttons đưa vào là gì
	public Book book_setting;
	//setter
	public void set_book(Book data) {
		book_setting = data;
	}
	public void set_func(String func) {
		this.func = func;
	}
	//getter
	public Book get_book() {
		return this.book_setting;
	}
	public String get_func() {
		return this.func;
	}
	private JPanel contentPane;
	private JTextField tf_id;
	private JTextField tf_namebook;
	private JTextField tf_nameauthor;
	private JTextField tf_catelory;
	private JTextField tf_company;
	private JTextField tf_date;
	private JTextField tf_quantity;
	public JButton bt_save = new JButton();
	private JTextField tf_quantity_available;
	//lấy dữ liệu từ các textfield
	//đưa vào thành 1 object book để sử dụng
	public Book get_data() {
		try {
			//lấy dữ liệu từ các textfield được nhập vào
			String id = String.valueOf(tf_id.getText()).trim();
			String namebook = String.valueOf(tf_namebook.getText()).trim();
			String nameauthor = String.valueOf(tf_nameauthor.getText()).trim();
			String catelory = String.valueOf(tf_catelory.getText()).trim();
			String company = String.valueOf(tf_company.getText()).trim();
			int quantity = Integer.valueOf((String.valueOf(tf_quantity.getText()).trim()));
			int quantity_available = Integer.valueOf(String.valueOf(tf_quantity_available.getText().trim()));
			String date = String.valueOf(tf_date.getText()).trim();
			//chuyển dữ liệu date sang kiểu Date của java với định dạng ngày - tháng - năm
			java.util.Date date_book_java = new SimpleDateFormat("dd-MM-yyyy").parse(date);
			//chuyển từ date kiểu java sang date sql
			Date date_book_sql = new Date(date_book_java.getTime());
			//tạo object data từ các dữ liệu nhập vào
			Book data = new Book(id, namebook, nameauthor, catelory, company, date_book_sql, quantity, quantity_available);
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//settext dữ liệu có sẵn từ dòng click chuột vào textfield
	public void set_data(Book data) {
		try {
			tf_id.setText(data.get_id());
			tf_namebook.setText(data.get_name());
			tf_nameauthor.setText(data.get_author());
			tf_catelory.setText(String.valueOf(data.get_catelory()));
			tf_company.setText(data.get_company_publishing());
			tf_quantity.setText(String.valueOf(data.get_quantity()));
			tf_quantity_available.setText(String.valueOf(data.get_quantity_available()));
			String date = new SimpleDateFormat("dd-MM-yyyy").format(data.get_date_publishing());
			tf_date.setText(date);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_book_settings frame = new view_book_settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view_book_settings() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if(func == "Update")
					set_data(book_setting);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 765, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông Tin Sách");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 29, 729, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(45, 68, 136, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên sách:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(45, 111, 136, 32);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Tên tác giả:");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(45, 154, 136, 32);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Thể loại:");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_4.setBounds(45, 197, 136, 32);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Nhà xuất bản:");
		lblNewLabel_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_5.setBounds(45, 240, 136, 32);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_2 = new JLabel("Ngày xuất bản:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(45, 279, 136, 32);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Số lượng:");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2_1.setBounds(45, 322, 136, 32);
		contentPane.add(lblNewLabel_1_2_1);
		
		tf_id = new JTextField();
		tf_id.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_id.setBounds(209, 72, 437, 20);
		contentPane.add(tf_id);
		tf_id.setColumns(10);
		
		tf_namebook = new JTextField();
		tf_namebook.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_namebook.setColumns(10);
		tf_namebook.setBounds(209, 118, 437, 20);
		contentPane.add(tf_namebook);
		
		tf_nameauthor = new JTextField();
		tf_nameauthor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_nameauthor.setColumns(10);
		tf_nameauthor.setBounds(209, 161, 437, 20);
		contentPane.add(tf_nameauthor);
		
		tf_catelory = new JTextField();
		tf_catelory.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_catelory.setColumns(10);
		tf_catelory.setBounds(209, 204, 437, 20);
		contentPane.add(tf_catelory);
		
		tf_company = new JTextField();
		tf_company.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_company.setColumns(10);
		tf_company.setBounds(209, 247, 437, 20);
		contentPane.add(tf_company);
		
		tf_date = new JTextField();
		tf_date.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_date.setColumns(10);
		tf_date.setBounds(209, 286, 437, 20);
		contentPane.add(tf_date);
		
		tf_quantity = new JTextField();
		tf_quantity.setText("0");
		tf_quantity.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_quantity.setColumns(10);
		tf_quantity.setBounds(209, 329, 437, 20);
		contentPane.add(tf_quantity);
		
		bt_save = new JButton("Lưu");
		//hành động khi click vào button Lưu
		bt_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chọn đồng ý để tiếp tục lưu
				int saveas = JOptionPane.showConfirmDialog(null, "Lưu lại những thay đổi?", "LIBRARY", JOptionPane.YES_NO_OPTION);
				//khi bấm đồng ý(có, oke) giá trị trả về cho saveas là 0
				if(saveas==0) {
					//xác định người dùng muốn tạo mới hay thay đổi sách có sẵn
					if(get_func() == "Create"){
						book_setting = get_data();
						//tạo mới thông tin cho 1 sách
						if(new feature().add(book_setting)) {
								JOptionPane.showMessageDialog(null, "Bạn vừa đưa vào danh mục 1 thông tin sách mới!");
								}
						}
						//thay đổi thông tin từ 1 sách có sẵn
						else if(get_func() == "Update"){
							//lấy lại dữ liệu tất cả các trường(bao gồm các thuộc tính đã thay đổi và không(không được thay đổi ID))
							book_setting = get_data();
							//gọi hàm thay đổi dữ liệu
							new feature().change(book_setting);
						}
					dispose();
				}
			}
		});
		bt_save.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bt_save.setBounds(347, 410, 89, 23);
		contentPane.add(bt_save);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Số lượng hiện có:");
		lblNewLabel_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(45, 365, 136, 32);
		contentPane.add(lblNewLabel_1_2_1_1);
		
		tf_quantity_available = new JTextField();
		tf_quantity_available.setText("0");
		tf_quantity_available.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_quantity_available.setColumns(10);
		tf_quantity_available.setBounds(209, 372, 437, 20);
		contentPane.add(tf_quantity_available);
	}
}
