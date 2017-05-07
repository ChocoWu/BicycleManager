package internalFrame;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import internalFrame.UserManger.AddUser;
import internalFrame.UserManger.DeleteUser;

public class UserManager extends JInternalFrame {
	public UserManager()
	{
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 491, 287);
		setTitle("用户管理");
		JTabbedPane tabPane = new JTabbedPane();
		final AddUser addPanel = new AddUser();
		final DeleteUser delPanel = new DeleteUser();
		tabPane.addTab("添加用户", null, addPanel, "添加用户");
		tabPane.addTab("删除用户", null, delPanel, "删除用户");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				delPanel.initTable();
			}
		});
		pack();
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
				{
			public void run()
			{
				JFrame frame=new JFrame();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    desktopPane = new JDesktopPane();
	    		frame.getContentPane().add(desktopPane);
	    		JInternalFrame internalFrame = new UserManager();
	    	    desktopPane.add(internalFrame); 
			}
			JDesktopPane desktopPane;
				});
		
	}
	
	
}
