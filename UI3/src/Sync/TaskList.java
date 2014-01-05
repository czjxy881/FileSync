package Sync;
//import FileSystemModel;

import java.awt.*;

import org.jdesktop.swingx.table.TableColumnExt; 

import java.awt.event.*;
import java.io.File;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;
import javax.swing.event.TreeModelListener;
import javax.swing.table.TableModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.*;
import org.jdesktop.swingx.table.ColumnFactory;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

public class TaskList extends JFrame
{
	/**
	 * @wbp.nonvisual location=-3,39
	 */
	private static final Box horizontalBox = Box.createHorizontalBox();
	//static //static TreeTableModel treeModel=new TreeTableModel() {};
	//DefaultTreeTableModel treeTableModel=new DefaultTreeTableModel((TreeTableNode)new JTree().getModel().getRoot());
	//public static JXTreeTable jxTreeTable =new JXTreeTable();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskList frame = new TaskList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public TaskList()
	{
		setDefaultLookAndFeelDecorated(true);
		
		setTitle("任务列表");
		setSize(800,514);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pan=new JPanel();
		setContentPane(pan);
		pan.setLayout(null);
		
		JButton StartButton = new JButton("");
		StartButton.setBounds(20, 0, 76, 38);
		StartButton.setToolTipText("\u5F00\u59CB\u540C\u6B65");
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		StartButton.setIcon(new ImageIcon(TaskList.class.getResource("/Sync/Images/sync.png")));
		StartButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		pan.add(StartButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 48, 484, 156);
		pan.add(scrollPane);
		
		JMenuBar menuBar = new JMenuBar();
		scrollPane.setColumnHeaderView(menuBar);
		
		JCheckBox checkBox = new JCheckBox("\u4EFB\u52A1\u540D\u79F0");
		menuBar.add(checkBox);
		
		JMenuItem menuItem = new JMenuItem("\u6E90\u76EE\u5F55");
		menuBar.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u65B9\u5F0F");
		menuBar.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("\u76EE\u6807\u76EE\u5F55");
		menuBar.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u8FDB\u5EA6");
		menuBar.add(menuItem_3);
		
		JButton PauseButton = new JButton("");
		PauseButton.setIcon(new ImageIcon(TaskList.class.getResource("/Sync/Images/pause.png")));
		PauseButton.setToolTipText("\u6682\u505C\u4EFB\u52A1");
		PauseButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		PauseButton.setBounds(99, 0, 76, 38);
		pan.add(PauseButton);
		
		JButton StopButton = new JButton("");
		StopButton.setIcon(new ImageIcon(TaskList.class.getResource("/Sync/Images/stop.png")));
		StopButton.setToolTipText("\u505C\u6B62\u4EFB\u52A1");
		StopButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		StopButton.setBounds(178, 0, 76, 38);
		pan.add(StopButton);
		
		JButton DeleteButton = new JButton("");
		DeleteButton.setIcon(new ImageIcon(TaskList.class.getResource("/Sync/Images/delete.png")));
		DeleteButton.setToolTipText("\u5220\u9664\u4EFB\u52A1");
		DeleteButton.setBorder(new BevelBorder(BevelBorder.RAISED, Color.LIGHT_GRAY, null, null, null));
		DeleteButton.setBounds(256, 0, 76, 38);
		pan.add(DeleteButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(64, 235, 276, 87);
		pan.add(panel);
		List colum[];
		JXTreeTable sJxTreeTable=new JXTreeTable();
		JScrollPane scrollPane1 = new JScrollPane(sJxTreeTable);
		//contentPane.add(scrollPane1, BorderLayout.CENTER);
		//TreeTableModel model=new FileSystemModel(new File("D:\\"));
		//sJxTreeTable.setTreeTableModel(model);
		
		
		//panel.add(jxTreeTable);
	}
}
