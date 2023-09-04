package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Account;
import bean.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class view_usermain extends JFrame {
	private Account us;
	//set user
	public void set_user(Account dt) {
		this.us = dt;
	}
	public JMenuBar menuBar = new JMenuBar();
	public JMenu mnNewMenu = new JMenu();
	public JMenuItem mntmNewMenuItem = new JMenuItem();
	public JMenuItem menuitem_history = new JMenuItem();
	public JMenuItem menuitem_infor = new JMenuItem();
	public JMenuItem menuitem_user = new JMenuItem();
	public JMenuItem menuitem_backlogin = new JMenuItem();
	private JPanel contentPane;
	/*thêm hoặc ẩn nút chức năng tùy vào người sử dụng*/
	public void check() {
		if(us.get_position() != "Quản Lý") {
			menuitem_user.setVisible(false);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_usermain frame = new view_usermain();
					frame.setLocationRelativeTo(null);
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
	public view_usermain() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				check();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 900, 500);
		
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Chức Năng");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Danh mục sách");
		//action để mở danh mục sách: tìm kiếm và đăng ký mượn sách ở đây
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					view_books book = new view_books();
					book.set_user(us);
					book.setLocationRelativeTo(null);
					book.setVisible(true);
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
		//chức năng dành cho quản lý, để quản lý thông tin sinh viên
		menuitem_user = new JMenuItem("Danh sách sinh viên");
		menuitem_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_managerstudent view_infor_student = new view_managerstudent();
				view_infor_student.setLocationRelativeTo(null);
				view_infor_student.setVisible(true);
			}
		});
		mnNewMenu.add(menuitem_user);
		
		menuitem_history = new JMenuItem("Lịch Sử");
		menuitem_history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_statistics his = new view_statistics();
				his.set_user(us);
				his.setVisible(true);
			}
		});
		mnNewMenu.add(menuitem_history);
		
		menuitem_infor = new JMenuItem("Thông Tin");
		menuitem_infor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_user_infor user = new view_user_infor();
				user.set_id(us.get_id());
				user.setLocationRelativeTo(null);
				user.setVisible(true);
			}
		});
		mnNewMenu.add(menuitem_infor);
		
		menuitem_backlogin = new JMenuItem("Đăng Xuất");
		menuitem_backlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chọn đồng ý để thoát khỏi phiên đăng nhập này
				int back = JOptionPane.showConfirmDialog(null, "Bấm đồng ý để đăng xuất?", "LIBRARY", JOptionPane.YES_NO_OPTION);
				if(back==0) {
					dispose();
					view_login new_login = new view_login();
					new_login.setVisible(true);
				}
			}
		});
		mnNewMenu.add(menuitem_backlogin);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
