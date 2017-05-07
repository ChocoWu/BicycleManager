package internalFrame.UserManger;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.DatabaseOp;
import model.UserInfo;
import model.UserList;

public class AddUser extends JPanel {
	private JTextField Uname;//姓名
	private JComboBox Usex;//性别
	private JTextField Udep;//学院
	private JTextField Umaj;//专业
	private JTextField Unum;//学号
	private JTextField BicycleNo;//自行车车牌号
	private JComboBox XueBu;//学部
	private JComboBox block;//楼栋号
	private JComboBox domiNo;//宿舍号
	private JPasswordField pass1;
	private JPasswordField pass2;
	private JTextField logName;
	private JButton sure;
	private JButton reset;
	public AddUser()
	{
		super();
		setLayout(new GridBagLayout());
		setBounds(0,0,280,236);
		final JLabel label_1=new JLabel();
		label_1.setText("车牌号：");
		label_1.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_1=new GridBagConstraints();
		gridBc_1.gridy=0;
		gridBc_1.gridx=0;
		add(label_1,gridBc_1);
		BicycleNo=new JTextField();
		final GridBagConstraints gridBc_2=new GridBagConstraints();
		gridBc_2.weighty = 1.0;
		gridBc_2.insets = new Insets(0, 0, 0, 10);
		gridBc_2.fill = GridBagConstraints.HORIZONTAL;
		gridBc_2.gridwidth = 2;
		gridBc_2.gridy = 0;
		gridBc_2.gridx = 1;
		add(BicycleNo,gridBc_2);
		
		final JLabel label_2=new JLabel();
		label_2.setText("姓名：");
		label_2.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_3=new GridBagConstraints();
		gridBc_3.gridy=1;
		gridBc_3.gridx=0;
		add(label_2,gridBc_3);
		Uname=new JTextField();
		final GridBagConstraints gridBc_4=new GridBagConstraints();
		gridBc_4.weighty = 1.0;
		gridBc_4.insets = new Insets(0, 0, 0, 10);
		gridBc_4.fill = GridBagConstraints.HORIZONTAL;
		gridBc_4.gridwidth = 2;
		gridBc_4.gridy = 1;
		gridBc_4.gridx = 1;
		add(Uname,gridBc_4);
		
		final JLabel label_3=new JLabel();
		label_3.setText("性别");
		label_3.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_5=new GridBagConstraints();
		gridBc_5.gridy=1;
		gridBc_5.gridx=2;
		add(label_3,gridBc_5);
		Usex=new JComboBox();
		Usex.setModel(new DefaultComboBoxModel(new String[]{"男","女 "}));
		Usex.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_6=new GridBagConstraints();
		gridBc_6.gridy=1;
		gridBc_6.gridx=3;
		add(Usex,gridBc_6);
		
		final JLabel label_10=new JLabel();
		label_10.setText("昵称：");
		label_10.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_21=new GridBagConstraints();
		gridBc_21.gridy=2;
		gridBc_21.gridx=0;
		add(label_10,gridBc_21);
		logName=new JTextField();
		final GridBagConstraints gridBc_22=new GridBagConstraints();
		gridBc_22.weighty = 1.0;
		gridBc_22.insets = new Insets(0, 0, 0, 10);
		gridBc_22.fill = GridBagConstraints.HORIZONTAL;
		gridBc_22.gridwidth = 2;
		gridBc_22.gridy = 2;
		gridBc_22.gridx = 1;
		add(logName,gridBc_22);
		
		final JLabel label_4=new JLabel();
		label_4.setText("学院：");
		label_4.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_7=new GridBagConstraints();
		gridBc_7.gridy=3;
		gridBc_7.gridx=0;
		add(label_4,gridBc_7);
		Udep=new JTextField();
		final GridBagConstraints gridBc_8=new GridBagConstraints();
		gridBc_8.weighty = 1.0;
		gridBc_8.insets = new Insets(0, 0, 0, 10);
		gridBc_8.fill = GridBagConstraints.HORIZONTAL;
		gridBc_8.gridwidth = 2;
		gridBc_8.gridy = 3;
		gridBc_8.gridx = 1;
		add(Udep,gridBc_8);
		
		final JLabel label_5=new JLabel();
		label_5.setText("专业:");;
		label_5.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_9=new GridBagConstraints();
		gridBc_9.gridy=4;
		gridBc_9.gridx=0;
		add(label_5,gridBc_9);
		Udep=new JTextField();
		final GridBagConstraints gridBc_10=new GridBagConstraints();
		gridBc_10.weighty = 1.0;
		gridBc_10.insets = new Insets(0, 0, 0, 10);
		gridBc_10.fill = GridBagConstraints.HORIZONTAL;
		gridBc_10.gridwidth = 2;
		gridBc_10.gridy = 4;
		gridBc_10.gridx = 1;
		add(Udep,gridBc_10);
		
		final JLabel label_6=new JLabel();
		label_6.setText("学号：");;
		label_6.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_11=new GridBagConstraints();
		gridBc_11.gridy=5;
		gridBc_11.gridx=0;
		add(label_6,gridBc_11);
		Unum=new JTextField();
		final GridBagConstraints gridBc_12=new GridBagConstraints();
		gridBc_12.weighty = 1.0;
		gridBc_12.insets = new Insets(0, 0, 0, 10);
		gridBc_12.fill = GridBagConstraints.HORIZONTAL;
		gridBc_12.gridwidth = 2;
		gridBc_12.gridy = 5;
		gridBc_12.gridx = 1;
		add(Unum,gridBc_12);
		
		final JLabel label_11=new JLabel();
		label_11.setText("密码：");
		label_11.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_23=new GridBagConstraints();
		gridBc_23.gridy=6;
		gridBc_23.gridx=0;
		add(label_11,gridBc_23);
		pass1=new JPasswordField();
		final GridBagConstraints gridBc_24=new GridBagConstraints();
		gridBc_24.weighty = 1.0;
		gridBc_24.insets = new Insets(0, 0, 0, 10);
		gridBc_24.fill = GridBagConstraints.HORIZONTAL;
		gridBc_24.gridwidth = 2;
		gridBc_24.gridy = 6;
		gridBc_24.gridx = 1;
		add(pass1,gridBc_24);
		
		final JLabel label_12=new JLabel();
		label_12.setText("确认密码：");
		label_12.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_25=new GridBagConstraints();
		gridBc_25.gridy=7;
		gridBc_25.gridx=0;
		add(label_12,gridBc_25);
		pass2=new JPasswordField();
		final GridBagConstraints gridBc_26=new GridBagConstraints();
		gridBc_26.weighty = 1.0;
		gridBc_26.insets = new Insets(0, 0, 0, 10);
		gridBc_26.fill = GridBagConstraints.HORIZONTAL;
		gridBc_26.gridwidth = 2;
		gridBc_26.gridy = 7;
		gridBc_26.gridx = 1;
		add(pass2,gridBc_26);
		
		final JLabel label_7=new JLabel();
		label_7.setText("学部:");
		label_7.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_13=new GridBagConstraints();
		gridBc_13.gridy=8;
		gridBc_13.gridx=0;
		add(label_7,gridBc_13);
		XueBu=new JComboBox();
		XueBu.setModel(new DefaultComboBoxModel(new String[]{"信息学部","文理学部 ","工学部"}));
		XueBu.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_14=new GridBagConstraints();
		gridBc_14.gridy=8;
		gridBc_14.gridx=1;
		add(XueBu,gridBc_14);
		
		final JLabel label_8=new JLabel();
		label_8.setText("楼栋号：");
		label_8.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_15=new GridBagConstraints();
		gridBc_15.gridy=8;
		gridBc_15.gridx=2;
		add(label_8,gridBc_15);
		block=new JComboBox();
		block.setModel(new DefaultComboBoxModel(new String[]{"1舍","2舍 ","3舍"}));
		block.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_16=new GridBagConstraints();
		gridBc_16.gridy=8;
		gridBc_16.gridx=3;
		add(block,gridBc_16);
		
		final JLabel label_9=new JLabel();
		label_9.setText("宿舍号：");
		label_9.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_17=new GridBagConstraints();
		gridBc_17.gridy=8;
		gridBc_17.gridx=4;
		add(label_9,gridBc_17);
		domiNo=new JComboBox();
		domiNo.setModel(new DefaultComboBoxModel(new String[]{"318","317 ","316"}));
		domiNo.setFont(new Font("微软雅黑",Font.BOLD,14));
		final GridBagConstraints gridBc_18=new GridBagConstraints();
		gridBc_18.gridy=8;
		gridBc_18.gridx=5;
		add(domiNo,gridBc_18);
		
		sure=new JButton();
		sure.addActionListener(new SureButtonActionListener());
		sure.setText("确定");
		final GridBagConstraints gridBc_19 = new GridBagConstraints();
		gridBc_19.weighty = 1.0;
		gridBc_19.anchor = GridBagConstraints.EAST;
		gridBc_19.gridy = 9;
		gridBc_19.gridx = 2;
		add(sure, gridBc_19);
		
		reset=new JButton();
		reset.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				clear();
			}
				});
		reset.setText("重置");
		final GridBagConstraints gridBc_20 = new GridBagConstraints();
		gridBc_20.weighty = 1.0;
		gridBc_20.anchor = GridBagConstraints.WEST;
		gridBc_20.gridy = 9;
		gridBc_20.gridx = 4;
		add(reset, gridBc_20);
	}
	//重置的操作
	public void clear()
	{
		BicycleNo.setText("");
		Uname.setText("");
		logName.setText("");
		Udep.setText("");
		Umaj.setText("");
		Unum.setText("");
		pass1.setText("");
		pass2.setText("");
	}
	
	//添加确认添加信息按钮的监听事件
	private final class SureButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(Uname.getText().equals("") ||Udep.getText().equals("") 
					|| Umaj.getText().equals("")||BicycleNo.getText().equals("")
					|| logName.getText().equals("") || pass1.getText().equals("")
					|| pass2.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "请填写全部信息");
				return;
			}
			if(!pass1.getText().equals(pass2.getText()))
			{
				JOptionPane.showMessageDialog(null, "两次输入的密码不一致，请重新输入");
			}
			ResultSet users=DatabaseOp.query("select * from userList where logName='"+logName.getText().trim()+"'");
			try{
				if(users.next())
				{
					System.out.println("error");
					JOptionPane.showMessageDialog(AddUser.this,"昵称已经存在，请重新添加",
							"添加客户信息",JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			ResultSet set=DatabaseOp.query("select max(id) from userInfo");
			String id=null;
			try{
				if(set!=null && set.next())
				{
					String sid=set.getString(1);
					if(sid==null)
						id="yh1001";
					else{
						String str=sid.substring(2);
						id="yh"+(Integer.parseInt(str)+1);
					}
				}
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
			UserInfo userInfo=new UserInfo();
			UserList userList=new UserList();
			userList.setuserName(Uname.getText().trim());
			userList.setlogName(logName.getText().trim());
			userList.setPass(pass1.getText().trim());
			userList.setQuan("b");
			
			userInfo.setId(id);
			userInfo.setUserName(Uname.getText().trim());
			userInfo.setUsex(Usex.getSelectedItem().toString().trim());
			userInfo.setdept(Udep.getText().trim());
			userInfo.setmajor(Umaj.getText().trim());
			userInfo.setUserNum(Unum.getText().trim());
			userInfo.setBicycleNo(BicycleNo.getText().trim());
			userInfo.setXueBu(XueBu.getSelectedItem().toString().trim());
			userInfo.setBlocks(block.getSelectedItem().toString().trim());
			userInfo.setdomiNo(domiNo.getSelectedItem().toString().trim());
			DatabaseOp.addUser(userInfo);
			DatabaseOp.addCzy(userList);
			JOptionPane.showMessageDialog(AddUser.this, "用户添加成功", "添加用户信息", JOptionPane.INFORMATION_MESSAGE);
			reset.doClick();
			
		}
	}

}
