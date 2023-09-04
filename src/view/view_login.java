package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import bean.Account;
import bo.feature;
import dao.dao_sql;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/*
 * 	đăng nhập
 */
public class view_login extends JFrame {

	protected static final String Account = null;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtPassword;
	private JTextField txtngNhp;
	private JPasswordField passwordField;
	private JTextField textField_id;
	public JComboBox object_user = new JComboBox();
	public JButton Button_Login = new JButton();
	public feature fea = new feature();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_login frame = new view_login();	
					frame.setLocationRelativeTo(null);		//căn giữa frame
					frame.setVisible(true);					//hiển thị frame ra màn hình
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public view_login() {
		setTitle("LIBRARY");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				view_login fr_login = new view_login();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(500, 200, 450, 300);
		setBounds(400, 150, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBorder(new LineBorder(Color.BLACK,0));
		txtId.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtId.setHorizontalAlignment(SwingConstants.LEFT);
		txtId.setText("TÀI KHOẢN");
		txtId.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBorder(new LineBorder(Color.BLACK,0));
		txtPassword.setEditable(false);
		txtPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtPassword.setText("MẬT KHẨU");
		txtPassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtPassword.setColumns(10);
		
		txtngNhp = new JTextField();
		//setBorder(new LineBorder(Color.BLACK,0)); 
		//dòng code trên để xóa đường viền jtextfield
		txtngNhp.setBorder(new LineBorder(Color.BLACK,0));
		txtngNhp.setText("LIBRARY");
		txtngNhp.setHorizontalAlignment(SwingConstants.CENTER);
		txtngNhp.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtngNhp.setEditable(false);
		txtngNhp.setColumns(10);
		
		passwordField = new JPasswordField();
		
		textField_id = new JTextField();
		textField_id.setColumns(10);
		
		Button_Login = new JButton("Đăng Nhập");
		Button_Login.addMouseListener(new MouseAdapter() {
			//khi click vào button ĐĂNG NHẬP
			//kiểm tra tài khoản và mật khẩu USER nhập có tồn tại trong csdl không!
			@Override
			public void mouseClicked(MouseEvent e) {
				//lấy id và password từ text của USER nhập vào
				String id = textField_id.getText().toLowerCase();
				String password = passwordField.getText();
				String position = (String) object_user.getSelectedItem();	//lấy đối tượng tử dụng từ jcombobox
				Account user = new Account(id, password, position);					//khởi tạo objcet user lưu id và pass mà user nhập vào
				if((fea.login(user))==true) {
						dispose();
						view_usermain usermain = new view_usermain();
						usermain.set_user(user);
						usermain.setVisible(true);
					}
				else JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu sai!");
				//JOptionPane.showMessageDialog(null,String.valueOf(passwordField.getText()));
			}
		});
		Button_Login.setForeground(new Color(255, 255, 255));
		Button_Login.setBackground(new Color(0, 102, 255));
		Button_Login.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		object_user = new JComboBox();
		object_user.setModel(new DefaultComboBoxModel(new String[] {"Sinh Viên", "Quản Lý"}));
		object_user.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(225)
					.addComponent(txtngNhp, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addGap(241))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtPassword, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(txtId, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(object_user, 0, 250, Short.MAX_VALUE)
						.addComponent(Button_Login, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(passwordField)
							.addComponent(textField_id, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
					.addGap(135))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addComponent(txtngNhp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(textField_id))
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(Button_Login, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(object_user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(61))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
