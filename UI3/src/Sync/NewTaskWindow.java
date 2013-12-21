package Sync;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NewTaskWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	public static JComboBox comboBox=new JComboBox<>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewTaskWindow frame = new NewTaskWindow(comboBox);
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
	public NewTaskWindow(JComboBox jc) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u4EFB\u52A1\u540D\u79F0");
		label.setBounds(31, 10, 54, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(95, 7, 121, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u540C\u6B65\u65B9\u5F0F");
		label_1.setBounds(31, 58, 54, 15);
		contentPane.add(label_1);
		
		JRadioButton radioButton = new JRadioButton("\u5355\u5411\u540C\u6B65");
		radioButton.setSelected(true);
		radioButton.setBounds(95, 54, 121, 23);
		contentPane.add(radioButton);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u53CC\u5411\u540C\u6B65");
		rdbtnNewRadioButton.setBounds(218, 54, 121, 23);
		contentPane.add(rdbtnNewRadioButton);
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(radioButton);
		
		JLabel label_2 = new JLabel("\u6E90\u76EE\u5F55");
		label_2.setBounds(31, 103, 54, 15);
		contentPane.add(label_2);
		
		JComboBox comboBox = new JComboBox();//源目录
		comboBox.setEditable(true);
		comboBox.setBounds(95, 100, 198, 21);
		contentPane.add(comboBox);
		comboBox.setSelectedItem(jc.getSelectedItem());
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(303, 99, 69, 23);
		contentPane.add(btnBrowse);
		
		JLabel label_3 = new JLabel("\u76EE\u6807\u76EE\u5F55");
		label_3.setBounds(31, 136, 54, 15);
		contentPane.add(label_3);
		
		JComboBox comboBox_1 = new JComboBox();//目标目录
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(95, 131, 198, 21);
		contentPane.add(comboBox_1);
		JButton button = new JButton("Browse");
		button.setBounds(303, 130, 69, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u4E0B\u4E00\u6B65");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaskList.main(null);
			}
		});
		button_1.setBounds(218, 200, 93, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u53D6\u6D88");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_2.setBounds(331, 200, 93, 23);
		contentPane.add(button_2);
		
		JFileChooser jfc1=new JFileChooser();
		jfc1.setBounds(10, 41, 32, -25);
		button_1.add(jfc1);	
	}
}
