package internalFrame;

import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import login.Login;
import model.UserList;
import model.UserInfo;

public class SelectInfo extends JInternalFrame {
	private JTextField bicycleNum;
	private JTextField userName;
	private JTextField logName;
	private JTextField userSex;
	private JTextField dept;
	private JTextField major;
	private JTextField userNum;
	private JTextField address;
	private UserInfo userInfo=Login.getUserInfo();
	public SelectInfo()
	{
		super("用户信息");
		addInternalFrameListener(new InternalFrameAdapter(){
			public void internalFrameActivated(InternalFrameEvent e){
				bicycleNum.setText(userInfo.getBicycleNo());
				userName.setText(userInfo.getUserName());
				userSex.setText(userInfo.getUsex());
				logName.setText(userInfo.getLogName());
				dept.setText(userInfo.getdept());
				major.setText(userInfo.getmajor());
				userNum.setText(userInfo.getUserNum());
				address.setText(userInfo.getXueBu()+"-" +userInfo.getBlocks()+"-"+userInfo.getdomiNo());
			};
			
				});
		setVisible(true);
		setIconifiable(true);
		setClosable(true);
		
	}
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			         public void run() {
			        	 JFrame frame = new JFrame();
			     		 frame.setBounds(100, 100, 800, 600); 
			     		 desktopPane = new JDesktopPane();
			    		 frame.getContentPane().add(desktopPane);
			    		 JInternalFrame internalFrame = new SelectInfo();
			    		 desktopPane.add(internalFrame);
			    		 internalFrame.setVisible(true);
			        	 frame.setVisible(true);
			         }
		             JDesktopPane desktopPane;
				});
	}

}
