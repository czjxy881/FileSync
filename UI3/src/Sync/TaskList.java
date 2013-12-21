package Sync;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.BevelBorder;

public class TaskList
{
	/**
	 * @wbp.nonvisual location=-3,39
	 */
	private static final Box horizontalBox = Box.createHorizontalBox();

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		JFrame frame=new JFrame();
		frame.setTitle("任务列表");
		frame.setSize(800,514);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pan=new JPanel();
		frame.setContentPane(pan);
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
		scrollPane.setBounds(20, 48, 754, 356);
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
		
		frame.setVisible(true);

	}
}
