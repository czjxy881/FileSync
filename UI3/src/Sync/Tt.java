package tt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tt extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tt frame = new Tt();
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
	public Tt() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));
		
		JButton btnNewButton = new JButton("\u65B0\u5EFA\u4EFB\u52A1");
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//hello.getFrames();
				NewTask.main(null);
			}
		}
			);
		
		JButton btnNewButton2 = new JButton("\u4EFB\u52A1\u5217\u8868");
		contentPane.add(btnNewButton2);
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TastList.main(null);
			}
		});
		JButton btnNewButton_1 = new JButton("\u5907\u7528");
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u5907\u75282");
		contentPane.add(btnNewButton_2);
	}
}
