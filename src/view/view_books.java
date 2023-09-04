package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import bean.Account;
import bean.Book;
import bo.feature;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*	hiển thị thông tin sách
 * 	button mượn - chỉnh sửa - tạo mới - tìm kiếm
 */
public class view_books extends JFrame {
	private Account us;
	//set user
	public void set_user(Account dt) {
		this.us = dt;
	}
	private JPanel contentPane;
	private JTable table = new JTable();
	private JTextField tf_search;
	public JComboBox comboBox;
	public static view_books frame;
	public static JScrollPane scrollPane = new JScrollPane();
	public JButton btnMnSch = new JButton();
	public static feature fea = new feature();
	public JButton bt_add = new JButton();
	public JButton bt_edit = new JButton();
	/*điều chỉnh kích thước table*/
	public void table_reszie() {
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(125);
		table.getColumnModel().getColumn(5).setPreferredWidth(170);
		table.getColumnModel().getColumn(6).setPreferredWidth(98);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(100);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
	}
	/*thêm hoặc ẩn nút chức năng tùy vào người sử dụng*/
	public void set_function() {
		if(us.get_position() != "Quản Lý") {
			bt_add.setVisible(false);
			bt_edit.setVisible(false);
		}
	}
	/*làm mới bảng dữ liệu sách*/
	public DefaultTableModel md = new DefaultTableModel();
	public void refresh(ArrayList<Book> list) {
		try {
			fea.connectsql.connect();
			md = new DefaultTableModel();
			int i=0;
			md.addColumn("STT");
			md.addColumn("Mã Sách");
			md.addColumn("Tên Sách");
			md.addColumn("Tác Giả");
			md.addColumn("Thể Loại");
			md.addColumn("Nhà Xuất Bản");
			md.addColumn("Ngày Xuất Bản");
			md.addColumn("Số Lượng");
			md.addColumn("Số lượng hiện có");
			for(Book data:list) {
				String[] dt = new String[9];
				i++;
				dt[0] = String.valueOf(i);
				dt[1] = data.get_id();
				dt[2] = data.get_name();
				dt[3] = data.get_author();
				dt[4] = data.get_catelory();
				dt[5] = data.get_company_publishing();
				//tạo 1 dateformat định dạng năm-tháng-ngày cho date sql
				java.util.Date date_java = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(data.get_date_publishing()));
				//chuyển đổi từ năm - tháng - ngày sang định dạng ngày - tháng - năm để hiển thị
				dt[6] = new SimpleDateFormat("dd-MM-yyyy").format(date_java);
				dt[7] = String.valueOf(data.get_quantity());
				dt[8] = String.valueOf(data.get_quantity_available());
				md.addRow(dt);
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
				frame = new view_books();
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public view_books() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					set_function();
					refresh(fea.get_inforbooks());
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
//				frame.setExtendedState(JFrame.NORMAL);
//				frame.setVisible(true);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setBounds(100, 50, 1084, 600);
		setBounds(100, 50, 1366, 768);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tf_search = new JTextField();
		tf_search.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tf_search.setColumns(10);
		
		JButton btnTmKim = new JButton("TÌM KIẾM");
		btnTmKim.addMouseListener(new MouseAdapter() {
			//lấy dữ liệu nhập ở ô tìm kiếm, và ở combobox
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String text = String.valueOf(tf_search.getText()).trim();
					text = "%"+text+"%";
					String data = String.valueOf(comboBox.getSelectedItem()).trim();
					ArrayList<Book> ds = fea.get_book(text, data);
					refresh(ds);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnTmKim.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mã Sách", "Tên Sách", "Thể Loại", "Năm Xuất Bản", "Nhà Xuất Bản"}));
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
//chức năng đăng ký mượn sách
		btnMnSch = new JButton("MƯỢN SÁCH");
		btnMnSch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//bắt sự kiện click chuột vào row chứa sách muốn mượn để lấy thông tin sách đó
				int rowbook = table.getSelectedRow();
				String idbook = String.valueOf(table.getValueAt(rowbook, 1));
				int count;
				try {
					//nếu số lượng khác 0 thì lên danh sách mượn sách đang chọn với số lượng đã nhập cho user
					count = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng bạn muốn mượn!"));
					if(count!=0) {
						//gọi hàm xử lý đăng ký mượn sách
						fea.enfor(us.get_id(), idbook, count);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
			}
		});
		btnMnSch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnMnSch.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		bt_add = new JButton("THÊM");
		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bt_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				view_book_settings book_setting = new view_book_settings();
				book_setting.set_func("Create");
				book_setting.setVisible(true);
				ArrayList<Book> list = fea.get_inforbooks();
				refresh(list);
			}
		});
		bt_add.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		bt_edit = new JButton("CHỈNH SỬA");
		bt_edit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					//bắt sự kiện click chuột chọn sách
					int rowbook = table.getSelectedRow();			//bắt even click chuột để lấy giá trị row tại table	
					//lấy các thông tin sách từ dòng người dùng click chuột
					String idbook = String.valueOf(table.getValueAt(rowbook, 1));
					String name = String.valueOf(table.getValueAt(rowbook, 2));
					String author = String.valueOf(table.getValueAt(rowbook, 3));
					String catelory = String.valueOf(table.getValueAt(rowbook, 4));
					String company = String.valueOf(table.getValueAt(rowbook, 5));
					//chuyển dữ liệu date sang kiểu Date của java với định dạng ngày - tháng - năm
					java.util.Date date_java = new SimpleDateFormat("dd-MM-yyyy").parse((String) table.getValueAt(rowbook, 6));
					//chuyển từ date kiểu java sang date sql
					Date date = new Date(date_java.getTime());
					int quantity = Integer.parseInt(String.valueOf(table.getValueAt(rowbook, 7)));
					int quantity_available =Integer.parseInt(String.valueOf(table.getValueAt(rowbook, 8)));
					Book data = new Book(idbook, name, author, catelory, company, date, quantity, quantity_available);
					view_book_settings book_setting = new view_book_settings();
					book_setting.set_func("Update");
					book_setting.set_book(data);
					book_setting.setVisible(true);
					ArrayList<Book> list = fea.get_inforbooks();
					refresh(list);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		bt_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bt_edit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(69)
							.addComponent(tf_search, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnTmKim, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnMnSch))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(bt_add, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
								.addComponent(bt_edit, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(53)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1245, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_search, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTmKim, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(bt_add, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMnSch, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(bt_edit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(78))
		);
		
		scrollPane = new JScrollPane();
		tabbedPane.addTab("BOOKS", null, scrollPane, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", null, null, null, null, null, null, null},
				{"2", null, null, null, null, null, null, null},
				{"3", null, null, null, null, null, null, null},
				{"4", null, null, null, null, null, null, null},
				{"5", null, null, null, null, null, null, null},
			},
			new String[] {
				"STT", "M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch", "T\u00E1c Gi\u1EA3", "Th\u1EC3 Lo\u1EA1i", "Nh\u00E0 Xu\u1EA5t B\u1EA3n", "Ng\u00E0y Xu\u1EA5t B\u1EA3n", "S\u1ED1 L\u01B0\u1EE3ng", "S\u1ED1 L\u01B0\u1EE3ng Hi\u1EC7n C\u00F3"
			}
		));
		table_reszie();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
}
