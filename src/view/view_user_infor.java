package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.User;
import bo.feature;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * 	hiển thị thông tin user
 */
public class view_user_infor extends JFrame {
	private String id;
	//get id
	public String get_id() {
		return this.id;
	}
	//set id
	public void set_id(String id) {
		this.id = id;
	}
	//new User
	public User infor_user() {
		User data;
		try {
			feature fea = new feature();
			data = fea.get_user(get_id());
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	//set thông tin
	public void setinfor(){
		try {
			User infor = infor_user();
			lb_id.setText(infor.get_id());
			lb_fullname.setText(infor.get_fullname());
			lb_sex.setText(infor.get_sex());
			java.util.Date date_java = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(infor.get_birthday()));
			//chuyển đổi từ năm - tháng - ngày sang định dạng ngày - tháng - năm để hiển thị
			String date = new SimpleDateFormat("dd-MM-yyyy").format(date_java);
			lb_birthday.setText(date);
			lb_nation.setText(infor.get_nation());
			lb_addr.setText(infor.get_addr());
			lb_cccd.setText(infor.get_cccd());
			lb_email.setText(infor.get_email());
			lb_phonenumber.setText(infor.get_phonenumber());
			lb_course.setText(String.valueOf(infor.get_course()));
			lb_majors.setText(infor.get_majors());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private JPanel contentPane;
	public JLabel lb_id = new JLabel();
	public JLabel lb_fullname = new JLabel();
	public JLabel lb_sex = new JLabel();
	public JLabel lb_date = new JLabel();
	public JLabel lb_birthday = new JLabel();
	public JLabel lb_nation = new JLabel();
	public JLabel lb_addr = new JLabel();
	public JLabel lb_cccd = new JLabel();
	public JLabel lb_email = new JLabel();
	public JLabel lb_phonenumber = new JLabel();
	private JLabel lb_course = new JLabel();
	private JLabel lb_majors = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_user_infor frame = new view_user_infor();
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
	public view_user_infor() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setinfor();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 1084, 600);
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
		
		lb_sex = new JLabel("");
		lb_sex.setHorizontalAlignment(SwingConstants.LEFT);
		lb_sex.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_sex.setBounds(196, 172, 398, 26);
		contentPane.add(lb_sex);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgySinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgySinh.setBounds(-94, 227, 264, 26);
		contentPane.add(lblNgySinh);
		
		lb_birthday = new JLabel("");
		lb_birthday.setHorizontalAlignment(SwingConstants.LEFT);
		lb_birthday.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_birthday.setBounds(196, 227, 398, 26);
		contentPane.add(lb_birthday);
		
		lb_nation = new JLabel("");
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
		
		lb_addr = new JLabel("");
		lb_addr.setHorizontalAlignment(SwingConstants.LEFT);
		lb_addr.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_addr.setBounds(196, 347, 398, 26);
		contentPane.add(lb_addr);
		
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCccd.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCccd.setBounds(-94, 410, 264, 26);
		contentPane.add(lblCccd);
		
		lb_cccd = new JLabel("");
		lb_cccd.setHorizontalAlignment(SwingConstants.LEFT);
		lb_cccd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_cccd.setBounds(196, 410, 398, 26);
		contentPane.add(lb_cccd);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEmail.setBounds(486, 346, 264, 26);
		contentPane.add(lblEmail);
		
		lb_email = new JLabel("");
		lb_email.setHorizontalAlignment(SwingConstants.LEFT);
		lb_email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_email.setBounds(776, 346, 264, 26);
		contentPane.add(lb_email);
		
		JLabel lblinThoiding = new JLabel("Điện thoại/di động:");
		lblinThoiding.setHorizontalAlignment(SwingConstants.RIGHT);
		lblinThoiding.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblinThoiding.setBounds(486, 410, 264, 26);
		contentPane.add(lblinThoiding);
		
		lb_phonenumber = new JLabel("");
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
		
		JButton btnNewButton = new JButton("Chỉnh Sửa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_user_infor_update infor_update = new view_user_infor_update();
				infor_update.set_user(infor_user());
				dispose();
				infor_update.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(486, 475, 108, 36);
		contentPane.add(btnNewButton);
	}
}
