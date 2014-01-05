package Sync;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.EventQueue;
import java.io.File;
import java.sql.Date;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionListener;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
//import org.jdesktop.swingx.treetable.FileSystemModel;
import org.jdesktop.swingx.treetable.TreeTableModel;

import javax.swing.ScrollPaneConstants;

public class Sync extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sync frame = new Sync();
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
	public Sync() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 95, 424, 159);
		contentPane.add(panel);
		String A,B;
		int k;
		Task a = new Task();
		JXTreeTable sJxTreeTable=new JXTreeTable();
		JScrollPane scrollPane = new JScrollPane(sJxTreeTable);
		scrollPane.setBounds(10, 95, 424, 159);
		panel.add(scrollPane);
		TreeTableModel model=new FileSystemModel(new File("D:\\"));
		MyTree tree =new MyTree(a);
		//sJxTreeTable.setTreeTableModel(model);
		sJxTreeTable.setTreeTableModel(tree);
		
	}
}

class Task{
	String a,b;
	int k;
}
class MyTree extends DefaultTreeTableModel{
	public MyTree(Task a)
	{
		//this();
	}
	public MyTree(File file) {
		// TODO Auto-generated constructor stub
		
	}
	@Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
        case 0:
            return "源目录";
        case 1:
            return "方式";
        case 2:
            return "目标目录";
        case 3:
            return "选择";
       // case 4:
        //	return "checkBox";       
        default:
            return super.getColumnName(column);
        }
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof File) {
            File file = (File) node;
            switch (column) {
            case 0:
                return file.getName();
            case 1:
                return  file.length() ;
            case 2:
                return file.isDirectory();
            case 3:
                return new Date(file.lastModified());
            }
        }
        if(node instanceof Checkbox)
        {
        	Checkbox ck = (Checkbox)node;
        	//ck.
        	return ck.getState();
        }

        return null;
    }
	public Class<?> getColumnClass(int column) {
        switch (column) {
        case 0:
            return String.class;
        case 1:
            return Long.class;
        	//return Checkbox.class;
        case 2:
            return Boolean.class;
        case 3:
            return Date.class;
        case 4:
        	return Boolean.class;
        default:
            return super.getColumnClass(column);
        }
    }
	
}

class FileSystemModel extends AbstractTreeTableModel {
    // The the returned file length for directories.
    private static final Long DIRECTORY = 0L;

    /**
     * Creates a file system model using the root directory as the model root.
     */
    public FileSystemModel() {
        this(new File(File.separator));
    }

    /**
     * Creates a file system model using the specified {@code root}.
     * 
     * @param root
     *            the root for this model; this may be different than the root
     *            directory for a file system.
     */
    public FileSystemModel(File root) {
        super(root);
    }

    private boolean isValidFileNode(Object file) {
        boolean result = false;
        
        if (file instanceof File) {
            File f = (File) file;
            
            while (!result && f != null) {
                result = f.equals(root);
                
                f = f.getParentFile();
            }
        }
        
        return result;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public File getChild(Object parent, int index) {
        if (!isValidFileNode(parent)) {
            throw new IllegalArgumentException("parent is not a file governed by this model");
        }
        
        File parentFile = (File) parent;
        String[] children = parentFile.list();
        
        if (children != null) {
            return new File(parentFile, children[index]);
        }
        
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof File) {
            String[] children = ((File) parent).list();
            
            if (children != null) {
                return children.length;
            }
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
        case 0:
            return String.class;
        case 1:
            return Long.class;
        	//return Checkbox.class;
        case 2:
            return Boolean.class;
        case 3:
            return Date.class;
        case 4:
        	return Boolean.class;
        default:
            return super.getColumnClass(column);
        }
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
        case 0:
            return "Name";
        case 1:
            return "Size";
        case 2:
            return "Directory";
        case 3:
            return "Modification Date";
        case 4:
        	return "checkBox";       
        default:
            return super.getColumnName(column);
        }
    }

    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof File) {
            File file = (File) node;
            switch (column) {
            case 0:
                return file.getName();
            case 1:
                return isLeaf(node) ? file.length() : DIRECTORY;
            case 2:
                return file.isDirectory();
            case 3:
                return new Date(file.lastModified());
            }
        }
        if(node instanceof Checkbox)
        {
        	Checkbox ck = (Checkbox)node;
        	//ck.
        	return ck.getState();
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if (parent instanceof File && child instanceof File) {
            File parentFile = (File) parent;
            File[] files = parentFile.listFiles();
            
            Arrays.sort(files);
            
            for (int i = 0, len = files.length; i < len; i++) {
                if (files[i].equals(child)) {
                    return i;
                }
            }
        }
        
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getRoot() {
        return (File) root;
    }

    /**
     * Sets the root for this tree table model. This method will notify
     * listeners that a change has taken place.
     * 
     * @param root
     *            the new root node to set
     */
    public void setRoot(File root) {
        this.root = root;
        
        modelSupport.fireNewRoot();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf(Object node) {
        if (node instanceof File) {
            //do not use isFile(); some system files return false
            return ((File) node).list() == null;
        }
        
        return true;
    }
}
