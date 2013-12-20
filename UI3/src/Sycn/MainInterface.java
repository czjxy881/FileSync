package Sycn;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTree;



public class MainInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface frame = new MainInterface();
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
	public MainInterface() {
		setTitle("\u4E3B\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		setBounds(100, 100, 546, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 485, 40);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("<html>返回<br>桌面<html>");
		btnNewButton.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton.setBounds(0, 0, 50, 36);
		panel.add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(90, 0, 395, 21);
		panel.add(comboBox);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\Michael\\Desktop\\QQ\u56FE\u724720131214110529.jpg"));
		btnNewButton_2.setBounds(70, 0, 16, 21);
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 48, 62, 262);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnn = new JButton("<html>新建<br>任务<html>");
		btnn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewTaskWindow.main(null);
			}
		});
		btnn.setMargin(new Insets(0, 0, 0, 0));
		btnn.setBounds(0, 33, 52, 52);
		panel_1.add(btnn);
		
		JButton btnNewButton_1 = new JButton("<html>任务<br>列表<html>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskList.main(null);
			}
		});
		btnNewButton_1.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton_1.setBounds(0, 95, 52, 52);
		panel_1.add(btnNewButton_1);
		
		JButton button_1 = new JButton("<html>历史<br>版本<html>");
		button_1.setMargin(new Insets(0, 0, 0, 0));
		button_1.setBounds(0, 157, 52, 52);
		panel_1.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 55, 413, 253);
		contentPane.add(scrollPane);
		
		JTree tree = new JTree();
		scrollPane.setRowHeaderView(tree);
		

	}
}
