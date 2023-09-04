package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import bean.Book;
import bean.User;
import bo.feature;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
/*
 * 	hiển thị danh sách sinh viên
 * 	button tìm kiếm - chỉnh sửa - thêm
 */
public class view_managerstudent extends JFrame {
	public static JScrollPane scrollPane = new JScrollPane();
	private feature fea = new feature();
	private JPanel contentPane;
	private JTable table;
	private JTextField tf_search;
	private JComboBox comboBox;
	//button chỉnh sửa thông tin
	private JButton bt_update;
	//button đăng ký mới
	private JButton bt_create;
	private ArrayList<User> listuser = new ArrayList<User>();
	//set size từng cột của table
	public void table_reszie() {
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(180);
		table.getColumnModel().getColumn(3).setPreferredWidth(110);
		table.getColumnModel().getColumn(4).setPreferredWidth(250);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(220);
		table.getColumnModel().getColumn(9).setPreferredWidth(130);
		table.getColumnModel().getColumn(10).setPreferredWidth(50);
		table.getColumnModel().getColumn(11).setPreferredWidth(190);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
	}
	
	/*làm mới bảng bảng dữ liệu*/
	public DefaultTableModel md = new DefaultTableModel();
	public void refresh(ArrayList<User> list) {
		try {
			//lấy i làm số thứ tự
			int i=1;
			//kết nối với database
			fea.connectsql.connect();
			//tạo mới model table
			md = new DefaultTableModel();
			md.addColumn("STT");
			md.addColumn("Mã Sinh Viên");
			md.addColumn("Họ Tên");
			md.addColumn("Số Điện Thoại");
			md.addColumn("Địa Chỉ");
			md.addColumn("Ngày Sinh");
			md.addColumn("Giới Tính");
			md.addColumn("Quốc Tịch");
			md.addColumn("Email");
			md.addColumn("CCCD");
			md.addColumn("Khóa");
			md.addColumn("Ngành Học");
			//duyệt list để đưa dữ liệu vào table
			for(User data:list) {
				String[] dt = new String[12];
				dt[0] = String.valueOf(i);
				dt[1] = data.get_id();
				dt[2] = data.get_fullname();
				dt[3] = data.get_phonenumber();
				dt[4] = data.get_addr();
				java.util.Date date_java = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(data.get_birthday()));
				dt[5] = new SimpleDateFormat("dd-MM-yyyy").format(date_java);
				dt[6] = data.get_sex();
				dt[7] = data.get_nation();
				dt[8] = data.get_email();
				dt[9] = data.get_cccd();
				dt[10] = String.valueOf(data.get_course());
				dt[11] = data.get_majors();
				md.addRow(dt);
				i++;
			}
			table.setModel(md);
			table_reszie();
			scrollPane.setViewportView(table);
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
					view_managerstudent frame = new view_managerstudent();
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
	public view_managerstudent() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listuser = fea.get_inforusers();
				refresh(listuser);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setBounds(100, 50, 1200, 600);
		setBounds(100, 50, 1366, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		bt_update = new JButton("CHỈNH SỬA");
		bt_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//bắt sự kiện click chuột chọn dòng chứa thông tin user
					int rowbook = table.getSelectedRow();			//bắt even click chuột để lấy giá trị row tại table	
					//lấy các thông tin sách từ dòng người dùng click chuột
					String id = String.valueOf(table.getValueAt(rowbook, 1));
					String fullname = String.valueOf(table.getValueAt(rowbook, 2));
					String phonenumber = String.valueOf(table.getValueAt(rowbook, 3));
					String addr = String.valueOf(table.getValueAt(rowbook, 4));
					//chuyển dữ liệu date sang kiểu Date của java với định dạng ngày - tháng - năm
					java.util.Date date_java = new SimpleDateFormat("dd-MM-yyyy").parse((String) table.getValueAt(rowbook, 5));
					//chuyển từ date kiểu java sang date sql
					Date birthday = new Date(date_java.getTime());
					String sex = String.valueOf(table.getValueAt(rowbook, 6));
					String nation = String.valueOf(table.getValueAt(rowbook, 7));
					String email = String.valueOf(table.getValueAt(rowbook, 8));
					String cccd = String.valueOf(table.getValueAt(rowbook, 9));
					int course = Integer.parseInt(String.valueOf(table.getValueAt(rowbook, 10)));
					String majors =String.valueOf(table.getValueAt(rowbook, 11));
					User data = new User(id, fullname, phonenumber, nation, addr, birthday, sex, nation, email, cccd, course, majors);
					view_user_infor_update infor_setting = new view_user_infor_update();
					//gửi thông tin vào frame
					infor_setting.set_user(data);
					infor_setting.setVisible(true);
					//làm mới lại thông tin table
					ArrayList<User> list = fea.get_inforusers();
					refresh(list);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		bt_update.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		bt_create = new JButton("ĐĂNG KÝ");
		bt_create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view_user_infor_create infor_create = new view_user_infor_create();
				infor_create.setVisible(true);
			}
		});
		bt_create.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		tf_search = new JTextField();
		tf_search.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_search.setColumns(10);
		
		JButton btnTmKim = new JButton("TÌM KIẾM");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String text = String.valueOf(tf_search.getText()).trim();
					text = "%"+text+"%";
					String data = String.valueOf(comboBox.getSelectedItem()).trim();
					ArrayList<User> ds = fea.get_user(text, data);
					refresh(ds);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnTmKim.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mã Số Sinh Viên", "Họ Tên", "Khóa Học", "Ngành"}));
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(83)
							.addComponent(tf_search, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(btnTmKim, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(bt_create, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(bt_update)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(63)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1218, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(tf_search, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnTmKim, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(bt_update)
							.addGap(32)
							.addComponent(bt_create, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(81))
		);
		
		scrollPane = new JScrollPane();
		tabbedPane.addTab("STUDENTS", null, scrollPane, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1",  null, null, null, null, null, null, null, null, null, null, null},
				{"2", null, null, null, null, null, null, null, null, null, null, null},
				{"3", null, null, null, null, null, null, null, null, null, null, null},
				{"4", null, null, null, null, null, null, null, null, null, null, null},
				{"5", null, null, null, null, null, null, null, null, null, null, null},
				{"6", null, null, null, null, null, null, null, null, null, null, null},
				{"7", null, null, null, null, null, null, null, null, null, null, null},
				{"8", null, null, null, null, null, null, null, null, null, null, null},
				{"9", null, null, null, null, null, null, null, null, null, null, null},
				{"10", null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 S\u1ED1 Sinh Vi\u00EAn", "H\u1ECD T\u00EAn", "S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "\u0110\u1ECBa Ch\u1EC9", "Ng\u00E0y Sinh", "Gi\u1EDBi T\u00EDnh", "Qu\u1ED1c T\u1ECBch", "Email", "CCCD", "Kh\u00F3a", "Ng\u00E0nh H\u1ECDc"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(94);
		table.getColumnModel().getColumn(4).setPreferredWidth(98);
		table.getColumnModel().getColumn(5).setPreferredWidth(105);
		table.getColumnModel().getColumn(6).setPreferredWidth(68);
		table.getColumnModel().getColumn(7).setPreferredWidth(125);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
