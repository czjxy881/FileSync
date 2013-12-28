

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.FileSystemModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.SimpleFileSystemModel;
import org.jdesktop.swingx.treetable.TreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

import javax.swing.JScrollPane;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JXTreeTable sJxTreeTable=new JXTreeTable();
		JScrollPane scrollPane = new JScrollPane(sJxTreeTable);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		DefaultMutableTreeTableNode      root = new DefaultMutableTreeTableNode("JTree");
		DefaultMutableTreeTableNode      parent;
		ArrayList<String> name=new ArrayList<>(5);
		name.add("1");
		name.add("2");
	        parent = new DefaultMutableTreeTableNode("colors");
	        root.add(parent);
	        parent.add(new DefaultMutableTreeTableNode("blue"));
	        parent.add(new DefaultMutableTreeTableNode("violet"));
	        parent.add(new DefaultMutableTreeTableNode("red"));
	        parent.add(new DefaultMutableTreeTableNode("yellow"));

	        parent = new DefaultMutableTreeTableNode("sports");
	        root.add(parent);
	        parent.add(new DefaultMutableTreeTableNode("basketball"));
	        parent.add(new DefaultMutableTreeTableNode("soccer"));
	        parent.add(new DefaultMutableTreeTableNode("football"));
	        parent.add(new DefaultMutableTreeTableNode("hockey"));

	        parent = new DefaultMutableTreeTableNode("food");
	        root.add(parent);
	        parent.add(new DefaultMutableTreeTableNode("hot dogs"));
	        parent.add(new DefaultMutableTreeTableNode("pizza"));
	        parent.add(new DefaultMutableTreeTableNode("ravioli"));
	        parent.add(new DefaultMutableTreeTableNode(){
	            public Object getValueAt(int column) {
	                return column;
	            }
	            public int getColumnCount() {
	                return 2;
	            }
	        });

		TreeTableModel model=new FileSystemModel(new File("D:\\"));
		sJxTreeTable.setTreeTableModel(model);
		//contentPane.add(sJxTreeTable);
	}

}
