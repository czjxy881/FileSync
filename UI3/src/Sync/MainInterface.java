package Sync;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import java.awt.Window.Type;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	public static JComboBox comboBox = new JComboBox();/*czq set comboBox to static*/
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
		
		comboBox.setEditable(true);
		comboBox.setBounds(90, 0, 395, 21);
		panel.add(comboBox);
		//comboBox.addItem("a");
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setIcon(new ImageIcon(MainInterface.class.getResource("/Sync/Images/goback.jpg")));
		btnNewButton_2.setBounds(70, 0, 16, 21);
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 48, 62, 262);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnn = new JButton("<html>新建<br>任务<html>");
		btnn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewTaskWindow newTaskWindow=new NewTaskWindow(comboBox);
				newTaskWindow.setVisible(true);
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
		
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.setBounds(77, 55, 413, 253);
		//contentPane.add(scrollPane);
		FileTree fileTree=new FileTree();
		FileTreeModel model=new FileTreeModel(new DefaultMutableTreeNode(new FileNode("root",null,null,true)));
        fileTree.setModel(model);
        fileTree.setCellRenderer(new FileTreeRenderer());
        JScrollPane scrollPane = new JScrollPane(fileTree);
		scrollPane.setBounds(77, 55, 413, 253);
		contentPane.add(scrollPane);

	}
}

class FileTree extends JTree {
    public TreePath mouseInPath;
    protected FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    public FileTree(){
        setRootVisible(false);
      //  addTreeExpansionListener(tel);
        addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                DefaultMutableTreeNode lastTreeNode =(DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                FileNode fileNode = (FileNode) lastTreeNode.getUserObject();
                
                //System.out.println(fileNode.file.getAbsolutePath() );
                /*czq set comboBox to static*/
                MainInterface.comboBox.setSelectedItem(fileNode.file.getAbsolutePath() ); //jxy set selected
                MainInterface.comboBox.addItem(fileNode.file.getAbsolutePath());
                
                if (!fileNode.isInit) {
                    File[] files;
                    if (fileNode.isDummyRoot) {
                        files = fileSystemView.getRoots();
                    } else {
                        files = fileSystemView.getFiles(
                                ((FileNode) lastTreeNode.getUserObject()).file,
                                false);
                    }
                    for (int i = 0; i < files.length; i++) {
                    	//files[i].
                        FileNode childFileNode = new FileNode(
                        		
                                fileSystemView.getSystemDisplayName(files[i]),
                                fileSystemView.getSystemIcon(files[i]), files[i],
                                false);
                        DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);
                        lastTreeNode.add(childTreeNode);
                    }
                    //閫氱煡妯″瀷鑺傜偣鍙戠敓鍙樺寲
                    DefaultTreeModel treeModel1 = (DefaultTreeModel) getModel();
                    treeModel1.nodeStructureChanged(lastTreeNode);
                }
                //鏇存敼鏍囪瘑锛岄伩鍏嶉噸澶嶅姞杞�
                fileNode.isInit = true;
            }
            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                TreePath path=getPathForLocation(e.getX(), e.getY());

                if(path!=null){
                    if(mouseInPath!=null){
                        Rectangle oldRect=getPathBounds(mouseInPath);
                        mouseInPath=path;
                        repaint(getPathBounds(path).union(oldRect));
                    }else{
                        mouseInPath=path;
                        Rectangle bounds=getPathBounds(mouseInPath);
                        repaint(bounds);
                    }
                }else if(mouseInPath!=null){
                    Rectangle oldRect=getPathBounds(mouseInPath);
                    mouseInPath=null;
                    repaint(oldRect);
                }
            }
        });
    }
}
class FileNode{
    public FileNode(String name,Icon icon,File file,boolean isDummyRoot){
        this.name=name;this.icon=icon;this.file=file;this.isDummyRoot=isDummyRoot;
    }
    public boolean isInit;
    public boolean isDummyRoot;
    public String name;
    public Icon icon;
    public File file;
}
class FileTreeRenderer extends DefaultTreeCellRenderer{
    public FileTreeRenderer(){
    }
    @Override
    public Component getTreeCellRendererComponent(javax.swing.JTree tree,
                                                  java.lang.Object value,
                                                  boolean sel,
                                                  boolean expanded,
                                                  boolean leaf,
                                                  int row,
                                                  boolean hasFocus){

        FileTree fileTree=(FileTree)tree;
        JLabel label= (JLabel) super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);

        DefaultMutableTreeNode node=(DefaultMutableTreeNode)value;
        FileNode fileNode=(FileNode)node.getUserObject();
        label.setText(fileNode.name);
        label.setIcon(fileNode.icon);

        label.setOpaque(false);
        if(fileTree.mouseInPath!=null&&
                fileTree.mouseInPath.getLastPathComponent().equals(value)){
            label.setOpaque(true);
            label.setBackground(new Color(255,0,0,90));
        }
        return label;
    }
}
class FileTreeModel extends DefaultTreeModel {
    public FileTreeModel(TreeNode root) {
        super(root);
         FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            File[] files=fileSystemView.getRoots();
        for (int i = 0; i < files.length; i++) {
            FileNode childFileNode = new FileNode(fileSystemView.getSystemDisplayName(files[i]), fileSystemView.getSystemIcon(files[i]), files[i], false);
            DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);
            ((DefaultMutableTreeNode)root).add(childTreeNode);
        }
    }
    @Override
    public boolean isLeaf(Object node) {
        DefaultMutableTreeNode treeNode=(DefaultMutableTreeNode)node;
        FileNode fileNode=(FileNode)treeNode.getUserObject();
        if(fileNode.isDummyRoot)return false;
        return fileNode.file.isFile();
    }
}