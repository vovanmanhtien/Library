package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bo.feature;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class view_changepassword extends JFrame {

	private JPanel contentPane;
	private String id_user;
	private JPasswordField passnew_1;
	private JPasswordField passnew_2;
	//set id
	public void set_id(String id) {
		this.id_user = id;
	}
	//get id
	public String get_id() {
		return this.id_user;
	}
	//button xác định thay đổi mật khẩu
	JButton bt_change = new JButton();
	//textfield nhập mật khẩu và xác nhận lại mật khẩu
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_changepassword frame = new view_changepassword();
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
	public view_changepassword() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 150, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mật khẩu mới:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(52, 110, 146, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNhpLiMt = new JLabel("Nhập lại mật khẩu mới:");
		lblNhpLiMt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpLiMt.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNhpLiMt.setBounds(36, 162, 162, 30);
		contentPane.add(lblNhpLiMt);
		
		JLabel lbliMtKhu = new JLabel("Đổi mật khẩu");
		lbliMtKhu.setHorizontalAlignment(SwingConstants.CENTER);
		lbliMtKhu.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbliMtKhu.setBounds(214, 11, 162, 30);
		contentPane.add(lbliMtKhu);
		
		passnew_1 = new JPasswordField();
		passnew_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passnew_1.setBounds(208, 100, 251, 30);
		contentPane.add(passnew_1);
		
		passnew_2 = new JPasswordField();
		passnew_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passnew_2.setBounds(208, 154, 251, 30);
		contentPane.add(passnew_2);
		
		bt_change = new JButton("Thay đổi");
		bt_change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lấy mật khẩu mới và nhập lại mật khẩu mới để so sánh
				String pass1 = String.valueOf(passnew_1.getText());
				String pass2 = String.valueOf(passnew_2.getText());
				//chọn đồng ý để thay đổi mật khẩu
				int saveas = JOptionPane.showConfirmDialog(null, "Xác định thay đổi mật khẩu?", "LIBRARY", JOptionPane.YES_NO_OPTION);
				if(saveas==0)
					if(!pass1.equals(pass2))
						JOptionPane.showMessageDialog(null, "Mật khẩu mới và nhập lại mật khẩu mới không giống nhau!");
					else {
						new feature().change_password(get_id(),pass1);
						dispose();
					}
			}
		});
		bt_change.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bt_change.setBounds(238, 224, 104, 30);
		contentPane.add(bt_change);
	}
}
