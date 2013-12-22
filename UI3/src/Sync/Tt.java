package tt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class Tt extends DefaultMutableTreeNode {

	private static final long serialVersionUID = 6999685634761062308L;

	private class MyFile {// ����ڲ�����ô�������JTree�ڵ��select����ʱ,����ѡ�ļ����и�ʽת��
		private File file;

		public MyFile(File file) {
			this.file = file;
		}

		public String toString() {
			String name = file.getName().trim();
			if (name.length() == 0)
				name = file.getAbsolutePath().trim();
			return name;
		}
	}

	DefaultMutableTreeNode treeRoot;
	DefaultTableModel tableModel;
	JTable table;
	DefaultMutableTreeNode parent;
	Object[][] list = { {} };

	public void treeMake() throws UnknownHostException { // ��ӻ�����&&�������
		InetAddress local = InetAddress.getLocalHost();
		treeRoot = new DefaultMutableTreeNode(local.getHostName());
		final JTree tree = new JTree(treeRoot);
		JScrollPane scrolltree = new JScrollPane(tree);
		scrolltree.setPreferredSize(new Dimension(200, 300));
		String[] row = { "�ļ�", "����", "����޸�����" };
		tableModel = new DefaultTableModel(list, row);
		table = new JTable(tableModel);
		JScrollPane scrollTable = new JScrollPane(table);
		scrollTable.setPreferredSize(new Dimension(500, 500));
		JFrame jf = new JFrame();
		jf.add(BorderLayout.WEST, scrolltree);
		jf.add(BorderLayout.CENTER, scrollTable);
		jf.setSize(600, 600);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��ӵ��Ա���Ӳ��
		for (char i = 'c'; i < 'z'; i++) {
			String path = i + ":/";
			if (((new File(path)).exists())) {
				DefaultMutableTreeNode diskNode = new DefaultMutableTreeNode(
						new MyFile(new File(path)));
				treeRoot.add(diskNode);
				readfiles(new File(path), diskNode);
			}
		}
		tree.addTreeSelectionListener(new TreeSelectionListener() {// ���listener
			public void valueChanged(TreeSelectionEvent arg0) {
				// TODO �Զ����ɷ������
				TreePath path = tree.getSelectionPath();
				if (path == null)
					return;
				DefaultMutableTreeNode selectnode = (DefaultMutableTreeNode) path
						.getLastPathComponent();
				if (!selectnode.isLeaf()) {
					// ������������ж�
					if (!(selectnode.getUserObject() instanceof MyFile)) {
						return;
					}
					File file_select = ((MyFile) selectnode.getUserObject()).file;
					// ��ȡ�ļ������ļ�����²�ڵ�
					readfiles(file_select, selectnode);
					DefaultMutableTreeNode firstchild = (DefaultMutableTreeNode) selectnode
							.getFirstChild();
					selectnode.remove(firstchild);
					tableModel.setRowCount(0);
					File[] fileList = file_select.listFiles();
					list = fu(fileList);
					for (int i = 0; i < fileList.length; i++) {
						tableModel.addRow(list[i]);
					}
				} else {
					tableModel.setRowCount(0);
					try {
						File file_select = ((MyFile) selectnode.getUserObject()).file;
						String sizeFile = size(file_select);
						Object re[][] = { { file_select.getName(), sizeFile,
								lastTime(file_select) } };
						list = re;
						tableModel.addRow(list[0]);
					} catch (IOException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					}
				}
			}
		});
	}

	public void readfiles(File file, DefaultMutableTreeNode node) {// ��ȡ��ѡ�ڵ�,��ȡ�ӽڵ�
		File list[] = file.listFiles();
		if (list == null)
			return;
		for (int i = 0; i < list.length; i++) {
			File file_inlist = list[i];
			// String filename = file_inlist.getName();
			if (file_inlist.isDirectory()) {
				parent = new DefaultMutableTreeNode(new MyFile(file_inlist));
				// ��ӿհ��ļ��нڵ� ʹ�ӽڵ���ʾΪ�ļ���
				File stubadd = null;
				DefaultMutableTreeNode stub = new DefaultMutableTreeNode(
						stubadd);
				parent.add(stub);
				node.add(parent);
			} else {
				DefaultMutableTreeNode son = new DefaultMutableTreeNode(
						new MyFile(file_inlist));
				node.add(son);
			}
		}
	}

	public String size(File file) throws IOException {// ��ȡ�ļ��Ĵ�С
		FileInputStream fileLength = new FileInputStream(file);
		String sizefile = fileLength.available() + "�ֽ�";
		return sizefile;
	}

	public Date lastTime(File file) {
		long lastModified = file.lastModified();// ȡ�����һ���޸ĵ�ʱ��
		Date date = new Date(lastModified);
		date.setTime(lastModified);
		return date;
	}

	public Object[][] fu(File[] file) {

		Object[][] m = new Object[file.length][3];
		for (int i = 0; i < file.length; i++) {
			m[i][0] = file[i].getName();
			try {
				// ����������,�����Ŀ¼,��ôȡ��С?����Ҫ��if
				// m[i][1] = size(file[i]);
				if (file[i].isDirectory()) {
					m[i][1] = "Ŀ¼";
				} else {
					m[i][1] = size(file[i]);
				}
			} catch (IOException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			m[i][2] = lastTime(file[i]);
		}
		return m;
	}

	public int getColumnCount() {
		// TODO �Զ����ɷ������
		return 3;
	}

	public int getRowCount(File[] file) {
		// TODO �Զ����ɷ������
		return file.length;
	}

	public Object getValueAt(int row, int col) {
		// TODO �Զ����ɷ������
		return list[row][col];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method
		Tt disk = new Tt();
		try {
			disk.treeMake();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}