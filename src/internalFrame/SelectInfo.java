package internalFrame;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import login.Login;
import model.UserInfo;

public class SelectInfo extends JInternalFrame {
	private JTextField bicycleNo;
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
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setBounds(100,100,640,375);
		getContentPane().setLayout(new GridBagLayout());
		addInternalFrameListener(new InternalFrameAdapter(){
			public void internalFrameActivated(InternalFrameEvent e){
				bicycleNo.setText(userInfo.getBicycleNo());
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
		JLabel label_1=new JLabel("车牌号：");
		setupComponent(label_1,0,0,1,1,false);
		bicycleNo=new JTextField();
		setupComponent(bicycleNo,1,0,1,1,true);
		JLabel label_2=new JLabel("用户名：");
		setupComponent(label_2,0,1,1,1,false);
		userName=new JTextField();
		setupComponent(userNum,1,1,1,1,true);
		JLabel label_3=new JLabel("登录名：");
		setupComponent(label_3,0,2,1,1,false);
		logName=new JTextField();
		setupComponent(logName,1,2,1,1,true);
		JLabel label_4=new JLabel("性别：");
		setupComponent(label_4,0,3,1,1,false);
		userSex=new JTextField();
		setupComponent(userSex,1,3,1,1,true);
		JLabel label_5=new JLabel("学院：");
		setupComponent(label_5,0,4,1,1,false);
		dept=new JTextField();
		setupComponent(dept,1,4,1,1,true);
		JLabel label_6=new JLabel("专业：");
		setupComponent(label_6,0,4,1,1,false);
		major=new JTextField();
		setupComponent(major,1,4,1,1,true);
		JLabel label_7=new JLabel("学号：");
		setupComponent(label_7,0,5,1,1,false);
		userNum=new JTextField();
		setupComponent(userNum,1,5,1,1,true);
		JLabel label_8=new JLabel("住址：");
		setupComponent(label_8,0,6,1,1,false);
		address=new JTextField();
		setupComponent(address,1,6,1,1,true);
		
		
	}
	//将组件添加到容器
		private void setupComponent(JComponent component ,int gridx,int gridy,int gridwidth,int ipdax,boolean fill){
			final GridBagConstraints gbc=new GridBagConstraints();
			gbc.gridx=gridx;
			gbc.gridy=gridy;
			if(gridwidth>1)
				gbc.gridwidth=gridwidth;
			if(ipdax>0)
				gbc.ipadx=ipdax;
			gbc.insets=new Insets(5,1,3,1);
			if(fill)
				gbc.fill=GridBagConstraints.HORIZONTAL;
			getContentPane().add(component,gbc);
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
