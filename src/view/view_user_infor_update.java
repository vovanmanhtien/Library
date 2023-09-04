package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Utilities;

import bean.User;
import bo.feature;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
/*
 * 	hiển thị thông tin user
 * 	button chỉnh sửa
 */
public class view_user_infor_update extends JFrame {
	private User data;
	private String func;
	
	//get
	public User get_user() {
		return this.data;
	}
	//set
	public void set_user(User data) {
		this.data = data;
	}
	public JButton bt_update = new JButton();
	//chọn item này để bật chức năng thay đổi mật khẩu
	private JMenuItem item_changepassword = new JMenuItem();
	//set thông tin
	public void setinfor(){
		try {
			lb_id.setText(data.get_id());
			lb_fullname.setText(data.get_fullname());
			lb_sex.setText(data.get_sex());
			java.util.Date date_java = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(data.get_birthday()));
			//chuyển đổi từ năm - tháng - ngày sang định dạng ngày - tháng - năm để hiển thị
			String date = new SimpleDateFormat("dd-MM-yyyy").format(date_java);
			lb_birthday.setText(date);
			lb_nation.setText(data.get_nation());
			lb_addr.setText(data.get_addr());
			lb_cccd.setText(data.get_cccd());
			lb_email.setText(data.get_email());
			lb_phonenumber.setText(data.get_phonenumber());
			lb_course.setText(String.valueOf(data.get_course()));
			lb_majors.setText(data.get_majors());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//lấy thông tin sau khi đã thay đổi
	public void get_infor() {
		try {
			data.set_sex(String.valueOf(lb_sex.getText()).trim());
			data.set_nation(String.valueOf(lb_nation.getText()).trim());
			data.set_addr(String.valueOf(lb_addr.getText()).trim());
			data.set_cccd(String.valueOf(lb_cccd.getText()).trim());
			data.set_email(String.valueOf(lb_email.getText()).trim());
			data.set_phonenumber(String.valueOf(lb_phonenumber.getText()).trim());
			java.util.Date date_java = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(lb_birthday.getText()).trim());
			Date dt = new Date(date_java.getTime());
			data.set_birthday(dt);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private JPanel contentPane;
	public JLabel lb_id = new JLabel();
	public JLabel lb_fullname = new JLabel();
	public JTextField lb_sex = new JTextField();
	public JTextField lb_date = new JTextField();
	public JTextField lb_birthday = new JTextField();
	public JTextField lb_nation = new JTextField();
	public JTextField lb_addr = new JTextField();
	public JTextField lb_cccd = new JTextField();
	public JTextField lb_email = new JTextField();
	public JTextField lb_phonenumber = new JTextField();
	private JLabel lb_course = new JLabel();
	private JLabel lb_majors = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_user_infor_update frame = new view_user_infor_update();
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
	public view_user_infor_update() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setinfor();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 1084, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Bảo mật");
		menuBar.add(mnNewMenu);
		
		item_changepassword = new JMenuItem("Thay đổi mật khẩu");
		item_changepassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//bật frame tạo mật khẩu mới
					view_changepassword change_password = new view_changepassword();
					change_password.setVisible(true);
					change_password.set_id(data.get_id());
			}
		});
		mnNewMenu.add(item_changepassword);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("THÔNG TIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 11, 1048, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblMSinh = new JLabel("Mã sinh viên:");
		lblMSinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMSinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblMSinh.setBounds(-94, 67, 264, 26);
		contentPane.add(lblMSinh);
		
		lb_id = new JLabel("");
		lb_id.setHorizontalAlignment(SwingConstants.LEFT);
		lb_id.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_id.setBounds(196, 67, 398, 26);
		contentPane.add(lb_id);
		
		JLabel lblSinhVin = new JLabel("Họ và tên:");
		lblSinhVin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSinhVin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSinhVin.setBounds(-94, 117, 264, 26);
		contentPane.add(lblSinhVin);
		
		lb_fullname = new JLabel("");
		lb_fullname.setHorizontalAlignment(SwingConstants.LEFT);
		lb_fullname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_fullname.setBounds(196, 117, 398, 26);
		contentPane.add(lb_fullname);
		
		JLabel lblGiiTnh = new JLabel("Giới tính:");
		lblGiiTnh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGiiTnh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGiiTnh.setBounds(-94, 172, 264, 26);
		contentPane.add(lblGiiTnh);
		
		lb_sex = new JTextField("");
		lb_sex.setHorizontalAlignment(SwingConstants.LEFT);
		lb_sex.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_sex.setBounds(196, 172, 398, 26);
		contentPane.add(lb_sex);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgySinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgySinh.setBounds(-94, 227, 264, 26);
		contentPane.add(lblNgySinh);
		
		lb_birthday = new JTextField("");
		lb_birthday.setHorizontalAlignment(SwingConstants.LEFT);
		lb_birthday.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_birthday.setBounds(196, 227, 398, 26);
		contentPane.add(lb_birthday);
		
		lb_nation = new JTextField("");
		lb_nation.setHorizontalAlignment(SwingConstants.LEFT);
		lb_nation.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_nation.setBounds(196, 283, 398, 26);
		contentPane.add(lb_nation);
		
		JLabel lblQucTch = new JLabel("Quốc tịch:");
		lblQucTch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQucTch.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblQucTch.setBounds(-94, 283, 264, 26);
		contentPane.add(lblQucTch);
		
		JLabel lblaCh = new JLabel("Địa chỉ:");
		lblaCh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblaCh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblaCh.setBounds(-94, 347, 264, 26);
		contentPane.add(lblaCh);
		
		lb_addr = new JTextField("");
		lb_addr.setHorizontalAlignment(SwingConstants.LEFT);
		lb_addr.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_addr.setBounds(196, 347, 398, 26);
		contentPane.add(lb_addr);
		
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCccd.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCccd.setBounds(-94, 410, 264, 26);
		contentPane.add(lblCccd);
		
		lb_cccd = new JTextField("");
		lb_cccd.setHorizontalAlignment(SwingConstants.LEFT);
		lb_cccd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_cccd.setBounds(196, 410, 398, 26);
		contentPane.add(lb_cccd);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEmail.setBounds(486, 346, 264, 26);
		contentPane.add(lblEmail);
		
		lb_email = new JTextField("");
		lb_email.setHorizontalAlignment(SwingConstants.LEFT);
		lb_email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_email.setBounds(776, 346, 264, 26);
		contentPane.add(lb_email);
		
		JLabel lblinThoiding = new JLabel("Điện thoại/di động:");
		lblinThoiding.setHorizontalAlignment(SwingConstants.RIGHT);
		lblinThoiding.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblinThoiding.setBounds(486, 410, 264, 26);
		contentPane.add(lblinThoiding);
		
		lb_phonenumber = new JTextField("");
		lb_phonenumber.setHorizontalAlignment(SwingConstants.LEFT);
		lb_phonenumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_phonenumber.setBounds(776, 410, 264, 26);
		contentPane.add(lb_phonenumber);
		
		JLabel Khóa = new JLabel("Khóa:");
		Khóa.setHorizontalAlignment(SwingConstants.RIGHT);
		Khóa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Khóa.setBounds(486, 209, 264, 26);
		contentPane.add(Khóa);
		
		lb_course = new JLabel("");
		lb_course.setHorizontalAlignment(SwingConstants.LEFT);
		lb_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_course.setBounds(776, 209, 264, 26);
		contentPane.add(lb_course);
		
		JLabel lblNgnhHc = new JLabel("Ngành học:");
		lblNgnhHc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgnhHc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgnhHc.setBounds(486, 273, 264, 26);
		contentPane.add(lblNgnhHc);
		
		lb_majors = new JLabel("");
		lb_majors.setHorizontalAlignment(SwingConstants.LEFT);
		lb_majors.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_majors.setBounds(776, 273, 264, 26);
		contentPane.add(lb_majors);
		
		bt_update = new JButton("Lưu Lại");
		bt_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chọn đồng ý để tiếp tục lưu
				int saveas = JOptionPane.showConfirmDialog(null, "Lưu lại những thay đổi?", "LIBRARY", JOptionPane.YES_NO_OPTION);
				//khi bấm đồng ý(có, oke) giá trị trả về cho saveas là 0
				if(saveas==0) {
					//cập nhật lại những thông tin có thể thay đổi của user
					get_infor();
					new feature().update_user(data);
					view_user_infor new_user_infor = new view_user_infor();
					new_user_infor.set_id(data.get_id());
					dispose();
					new_user_infor.setVisible(true);	
				}
				else {
					
				}

			}
		});
		bt_update.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bt_update.setBounds(486, 473, 131, 37);
		contentPane.add(bt_update);
	}
}
