package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import internalFrame.CorrectPassword;
import internalFrame.CreatedIcon;
import login.Login;

public class BicycleManager extends JFrame {
	private JFrame frame;
	private JPanel panel;
	private JLabel backLabel;
	private Map<String,JInternalFrame> map=new HashMap<String, JInternalFrame>();
	
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
	
	
	public BicycleManager()
	{
		super("校园自行车管理系统");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(600,800);
		setVisible(true);
		JLabel titleLabel=createLabel();
		getContentPane().add(titleLabel);
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		add(menuBar, BorderLayout.NORTH);
//		JToolBar toolBar = createToolBar(); // 调用创建工具栏的方法
//		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 400, 300);
		label.setIcon(null); // 窗体背景
		

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ BicycleManager.this.getClass().getResource("backImage.jpg")
						+ "'></html>");
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
		
		
	}
	// 添加子窗体的方法
	public static void addIFame(JInternalFrame iframe) {
			DESKTOP_PANE.add(iframe);
	}
	public JMenuBar createMenu()
	{
		JMenuBar menuBar=new JMenuBar();
	
		JMenu selectInfo=new JMenu();
		selectInfo.setIcon(CreatedIcon.add("selectInfo.png"));
		selectInfo.add(MenuActions.SELECT_USER);
		selectInfo.add(MenuActions.SELECT_BICYCLE);
		
		JMenu operation=new JMenu();
		operation.setIcon(CreatedIcon.add("management.png"));
		operation.add(MenuActions.USER_MANAGE);
		operation.add(MenuActions.CZY_GL);
		operation.add(MenuActions.QUAN_MANAGE);
		
		JMenu modify=new JMenu();
		modify.setIcon(CreatedIcon.add("modify.png"));
		modify.add(MenuActions.MODIFY_PASS);
		JMenu exit=new JMenu();
		exit.setIcon(CreatedIcon.add("exit.png"));
		exit.add(MenuActions.EXIT);
		JMenu info=new JMenu();//从数据库中得到姓名
		info.setIcon(CreatedIcon.add("userInfo.png"));
		
		menuBar.add(selectInfo);
		menuBar.add(operation);
		menuBar.add(modify);
		menuBar.add(info);
		menuBar.add(exit);
		return menuBar;
	}
	

	//创建主页标题
	private JLabel createLabel()
	{
		JLabel label=new JLabel();
		label.setText("校园自行车管理系统");
		label.setFont(new Font("微软雅黑",Font.BOLD,28));
//		label.setBounds(frame.getWidth()/2, frame.getHeight()/2, 300, 400);
		return label;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
			JFrame frame=new Login();
			frame.setSize(500, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
