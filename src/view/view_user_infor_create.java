package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
/*
 * 	hiển thị thông tin user
 * 	button chỉnh sửa
 */
public class view_user_infor_create extends JFrame {
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
	
	//lấy thông tin sau khi đã thay đổi
	public void get_infor() {
		try {
			data = new User();
			System.out.println("get_infor");
			data.set_id(String.valueOf(lb_id.getText()).trim());
			System.out.println(String.valueOf(lb_id.getText()).trim());
			data.set_fullname(String.valueOf(lb_fullname.getText()).trim());
			data.set_sex(String.valueOf(lb_sex.getText()).trim());
			data.set_nation(String.valueOf(lb_nation.getText()).trim());
			data.set_addr(String.valueOf(lb_addr.getText()).trim());
			data.set_cccd(String.valueOf(lb_cccd.getText()).trim());
			data.set_email(String.valueOf(lb_email.getText()).trim());
			data.set_phonenumber(String.valueOf(lb_phonenumber.getText()).trim());
			data.set_majors(String.valueOf(lb_majors.getText()).trim());
			data.set_course(Integer.parseInt(String.valueOf(lb_course.getText()).trim()));
			java.util.Date date_java = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(lb_birthday.getText()).trim());
			Date dt = new Date(date_java.getTime());
			data.set_birthday(dt);
		} catch (Exception e) {
			// TODO: handle exceptione
			e.printStackTrace();
		}
	}
	private JPanel contentPane;
	public JTextField lb_id = new JTextField();
	public JTextField lb_fullname = new JTextField();
	public JTextField lb_sex = new JTextField();
	public JTextField lb_date = new JTextField();
	public JTextField lb_birthday = new JTextField();
	public JTextField lb_nation = new JTextField();
	public JTextField lb_addr = new JTextField();
	public JTextField lb_cccd = new JTextField();
	public JTextField lb_email = new JTextField();
	public JTextField lb_phonenumber = new JTextField();
	private JTextField lb_course = new JTextField();
	private JTextField lb_majors = new JTextField();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_user_infor_create frame = new view_user_infor_create();
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
	public view_user_infor_create() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
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
		
		lb_id = new JTextField();
		lb_id.setHorizontalAlignment(SwingConstants.LEFT);
		lb_id.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_id.setBounds(196, 67, 398, 26);
		contentPane.add(lb_id);
		
		JLabel lblSinhVin = new JLabel("Họ và tên:");
		lblSinhVin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSinhVin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblSinhVin.setBounds(-94, 117, 264, 26);
		contentPane.add(lblSinhVin);
		
		lb_fullname = new JTextField();
		lb_fullname.setHorizontalAlignment(SwingConstants.LEFT);
		lb_fullname.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_fullname.setBounds(196, 117, 398, 26);
		contentPane.add(lb_fullname);
		
		JLabel lblGiiTnh = new JLabel("Giới tính:");
		lblGiiTnh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGiiTnh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblGiiTnh.setBounds(-94, 172, 264, 26);
		contentPane.add(lblGiiTnh);
		
		lb_sex = new JTextField();
		lb_sex.setHorizontalAlignment(SwingConstants.LEFT);
		lb_sex.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_sex.setBounds(196, 172, 398, 26);
		contentPane.add(lb_sex);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		lblNgySinh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgySinh.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgySinh.setBounds(-94, 227, 264, 26);
		contentPane.add(lblNgySinh);
		
		lb_birthday = new JTextField();
		lb_birthday.setHorizontalAlignment(SwingConstants.LEFT);
		lb_birthday.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_birthday.setBounds(196, 227, 398, 26);
		contentPane.add(lb_birthday);
		
		lb_nation = new JTextField();
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
		
		lb_addr = new JTextField();
		lb_addr.setHorizontalAlignment(SwingConstants.LEFT);
		lb_addr.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_addr.setBounds(196, 347, 398, 26);
		contentPane.add(lb_addr);
		
		JLabel lblCccd = new JLabel("CCCD:");
		lblCccd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCccd.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblCccd.setBounds(-94, 410, 264, 26);
		contentPane.add(lblCccd);
		
		lb_cccd = new JTextField();
		lb_cccd.setHorizontalAlignment(SwingConstants.LEFT);
		lb_cccd.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_cccd.setBounds(196, 410, 398, 26);
		contentPane.add(lb_cccd);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblEmail.setBounds(486, 346, 264, 26);
		contentPane.add(lblEmail);
		
		lb_email = new JTextField();
		lb_email.setHorizontalAlignment(SwingConstants.LEFT);
		lb_email.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_email.setBounds(776, 346, 264, 26);
		contentPane.add(lb_email);
		
		JLabel lblinThoiding = new JLabel("Điện thoại/di động:");
		lblinThoiding.setHorizontalAlignment(SwingConstants.RIGHT);
		lblinThoiding.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblinThoiding.setBounds(486, 410, 264, 26);
		contentPane.add(lblinThoiding);
		
		lb_phonenumber = new JTextField();
		lb_phonenumber.setHorizontalAlignment(SwingConstants.LEFT);
		lb_phonenumber.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_phonenumber.setBounds(776, 410, 264, 26);
		contentPane.add(lb_phonenumber);
		
		JLabel Khóa = new JLabel("Khóa:");
		Khóa.setHorizontalAlignment(SwingConstants.RIGHT);
		Khóa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Khóa.setBounds(486, 209, 264, 26);
		contentPane.add(Khóa);
		
		lb_course = new JTextField();
		lb_course.setHorizontalAlignment(SwingConstants.LEFT);
		lb_course.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_course.setBounds(776, 209, 264, 26);
		contentPane.add(lb_course);
		
		JLabel lblNgnhHc = new JLabel("Ngành học:");
		lblNgnhHc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNgnhHc.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNgnhHc.setBounds(486, 273, 264, 26);
		contentPane.add(lblNgnhHc);
		
		lb_majors = new JTextField();
		lb_majors.setHorizontalAlignment(SwingConstants.LEFT);
		lb_majors.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lb_majors.setBounds(776, 273, 264, 26);
		contentPane.add(lb_majors);
		
		bt_update = new JButton("Lưu Lại");
		bt_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chọn đồng ý để tiếp tục lưu
				int saveas = JOptionPane.showConfirmDialog(null, "Đăng ký mới với thông tin này?", "LIBRARY", JOptionPane.YES_NO_OPTION);
				//khi bấm đồng ý(có, oke) giá trị trả về cho saveas là 0
				if(saveas==0) {
					//lấy thông tin đã nhập vào
					get_infor();
					new feature().func_create_user(data);
					dispose();
				}

			}
		});
		bt_update.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bt_update.setBounds(486, 473, 131, 37);
		contentPane.add(bt_update);
	}
}
