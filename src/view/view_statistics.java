package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.Account;
import bean.Book;
import bean.History;
import bean.User;
import bo.feature;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/*
 * 	lịch sử mượn - trả sách
 */
public class view_statistics extends JFrame {
	public Account us;
	//set account
	public void set_user(Account us) {
		this.us = us;
	}
	private JPanel contentPane;
	public static JTable table = new JTable();
	public JScrollPane scrollPane = new JScrollPane();
	public feature fea = new feature();
	//button hủy mượn sách, chức năng dành quyền hạn cho sinh viên
	private JButton bt_delete = new JButton();
	//button nhận sách, chức năng quyền hạn cho quản lý
	private JButton bt_receive = new JButton();
	//button trả sách, chức năng quyền hạn cho quản lý
	JButton bt_back = new JButton();
	/*
	 * 	thiết lặp chức năng dựa vào quyền hạn
	 */
	public void set_function() {
		if(us.get_position()=="Sinh Viên") {
			bt_back.setVisible(false);
			bt_receive.setVisible(false);
		}
		else if(us.get_position()=="Quản Lý") {
			bt_delete.setVisible(false);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view_statistics frame = new view_statistics();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*điều chỉnh kích thước table*/
	public static void table_reszie() {
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setPreferredWidth(125);
		table.getColumnModel().getColumn(5).setPreferredWidth(170);
		table.getColumnModel().getColumn(6).setPreferredWidth(98);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 14));	
	}
	//làm mới dữ liệu
	public static void refresh(ArrayList<History> list) {
		try {
			DefaultTableModel md = new DefaultTableModel();
			md.addColumn("ID");
			md.addColumn("Mã sinh viên");
			md.addColumn("Mã Sách");
			md.addColumn("Số lượng");
			md.addColumn("Ngày mượn");
			md.addColumn("Ngày trả");
			md.addColumn("Trạng thái");
			for(History data:list) {
				String[] dt = new String[7];
				dt[0] = data.get_id();
				dt[1] = data.get_id_user();
				dt[2] = data.get_id_book();
				dt[3] = String.valueOf(data.get_quantity());
				java.util.Date date_borrow = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(data.get_date_borrow()));
				dt[4] = new SimpleDateFormat("dd-MM-yyyy").format(date_borrow);
				java.util.Date date_back = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(data.get_date_back()));
				dt[5] = new SimpleDateFormat("dd-MM-yyyy").format(date_back);
				dt[6] = data.get_status();
				md.addRow(dt);
			}
			table.setModel(md);
			table_reszie();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//set dữ liệu cho table dựa trên quyền hạn
	public void set_table() {
		ArrayList<History> list = new ArrayList<History>();
		if(us.get_position()=="Quản Lý") {
			list = fea.get_his();
		}
		else if(us.get_position()=="Sinh Viên") {
		list = fea.get_user_his(us.get_id());list = fea.get_user_his(us.get_id());
		}
		refresh(list);
	}
	/**
	 * Create the frame.
	 */
	public view_statistics() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				set_function();
				set_table();
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 50, 1084, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		tabbedPane.setBounds(70, 32, 932, 432);
		contentPane.add(tabbedPane);
		
		scrollPane = new JScrollPane();
		tabbedPane.addTab("Lịch Sử", null, scrollPane, null);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		
		bt_delete = new JButton("Hủy Mượn");
		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chọn đồng ý để tiếp tục lưu
				int saveas = JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy thông tin mượn sách này?", "LIBRARY", JOptionPane.YES_NO_OPTION);
				//khi bấm đồng ý(có, oke) giá trị trả về cho saveas là 0
				if(saveas==0) {
					//bắt sự kiện click chuột vào row chứa sách muốn mượn để lấy thông tin sách đó
					int rowbook = table.getSelectedRow();
					int id = Integer.parseInt(String.valueOf(table.getValueAt(rowbook, 0)));
					String idbook = String.valueOf(table.getValueAt(rowbook, 2));
					int count = Integer.parseInt(String.valueOf(table.getValueAt(rowbook, 3)));
					String stus = String.valueOf(table.getValueAt(rowbook, 6));
					if(stus.equalsIgnoreCase("Chưa nhận sách")) {
						//tăng số lượng sách lại vì sinh viên đã hủy mượn sách trước khi tới nhận
						new feature().count_book(idbook, (new feature().check_count_book(idbook, count, "Trả")+count), "Trả");
						//chuyển đổi trạng thái từ "CHƯA NHẬN SÁCH" sang "ĐÃ HỦY"
						new feature().cancel_enfor(id);
						ArrayList<History> list = fea.get_user_his(us.get_id());
						refresh(list);	
					}
					else {
						JOptionPane.showMessageDialog(null, "Không thể thay đổi lệnh này, xin kiếm tra lại trạng thái lệnh!");
					}
				}
				
			}
		});
		bt_delete.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bt_delete.setBounds(877, 475, 125, 36);
		contentPane.add(bt_delete);
		
		bt_receive = new JButton("Nhận Sách");
		bt_receive.addActionListener(new ActionListener() {
			//khi sinh viên tới nhận sách, sẽ kích hoạt button thay đổi thông tin(từ CHƯA NHẬN SÁCH sang ĐÃ NHẬN SÁCH)
			public void actionPerformed(ActionEvent e) {
				//chọn đồng ý để tiếp tục lưu
				int saveas = JOptionPane.showConfirmDialog(null, "Xác định sinh viên đã nhận sách?", "LIBRARY", JOptionPane.YES_NO_OPTION);
				//khi bấm đồng ý(có, oke) giá trị trả về cho saveas là 0
				if(saveas==0) {
					//bắt sự kiện click chuột vào row chứa sách muốn mượn để lấy thông tin sách đó
					int rowbook = table.getSelectedRow();
					int id = Integer.parseInt(String.valueOf(table.getValueAt(rowbook, 0)));
					String stus = String.valueOf(table.getValueAt(rowbook, 6));
					System.out.println(stus);
					if(stus.equalsIgnoreCase("Chưa nhận sách")) {
						System.out.println(stus);
						//chuyển đổi trạng thái từ "CHƯA NHẬN SÁCH" sang "ĐÃ NHẬN SÁCH"
						new feature().receive_book(id);
						ArrayList<History> list = fea.get_his();
						refresh(list);	
					}
					else {
						JOptionPane.showMessageDialog(null, "Không thể thay đổi lệnh này, xin kiếm tra lại trạng thái lệnh!");
					}
				}
			}
		});
		bt_receive.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bt_receive.setBounds(687, 476, 125, 36);
		contentPane.add(bt_receive);
		
		bt_back = new JButton("Trả Sách");
		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//chọn đồng ý để tiếp tục lưu
				int saveas = JOptionPane.showConfirmDialog(null, "Sinh viên này đã hoàn trả đủ sách?", "LIBRARY", JOptionPane.YES_NO_OPTION);
				//khi bấm đồng ý(có, oke) giá trị trả về cho saveas là 0
				if(saveas==0) {
					//bắt sự kiện click chuột vào row chứa sách muốn mượn để lấy thông tin sách đó
					int rowbook = table.getSelectedRow();
					int id = Integer.parseInt(String.valueOf(table.getValueAt(rowbook, 0)));
					String idbook = String.valueOf(table.getValueAt(rowbook, 2));
					int count = Integer.parseInt(String.valueOf(table.getValueAt(rowbook, 3)));
					String stus = String.valueOf(table.getValueAt(rowbook, 6));
					if(stus.equalsIgnoreCase("Đã nhận sách")) {
						//tăng số lượng sách lại vì sinh viên đã trả sách
						new feature().count_book(idbook, (new feature().check_count_book(idbook, count, "Trả")+count), "Trả");
						//chuyển đổi trạng thái từ "ĐÃ NHẬN SÁCH" sang "ĐÃ TRẢ SÁCH"
						new feature().back_book(id);
						ArrayList<History> list = fea.get_his();
						refresh(list);	
					}
					else {
						JOptionPane.showMessageDialog(null, "Không thể thay đổi lệnh này, xin kiếm tra lại trạng thái lệnh!");
					}
				}
			}
		});
		bt_back.setFont(new Font("Times New Roman", Font.BOLD, 14));
		bt_back.setBounds(687, 523, 125, 36);
		contentPane.add(bt_back);
	}
}
