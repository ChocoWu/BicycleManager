package login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import database.DatabaseOp;
import main.BicycleManager;
import model.UserInfo;
import model.UserList;

public class Login extends JFrame{
	    private JLabel titleLabel;
		private JLabel userLabel;
		private static JTextField loginName;
		private JLabel passLabel;
		private JButton login;
		private JButton reset;
    	private static UserList user;
    	private static UserInfo userInfo;//用户的全部信息
    	
		public Login() {
			setTitle("校园自行车管理系统");
			setSize(500, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			final JPanel panel = new LoginPanel();
			panel.setLayout(null);
			getContentPane().add(panel);
			setBounds(500, 300, panel.getWidth(), panel.getHeight());//添加一个容器，然后设置其大小，放置的位置
			
			titleLabel =new JLabel();
			titleLabel.setText("校园自行车管理系统");
			titleLabel.setFont(new Font("", Font.PLAIN, 18));
			titleLabel.setBounds(180, 70, 200, 28);;
			panel.add(titleLabel);
			
			userLabel = new JLabel();
			userLabel.setText("用户名");
			userLabel.setFont(new Font("",Font.PLAIN,16));
			userLabel.setBounds(115, 115, 200, 28);
			panel.add(userLabel);//添加用户名的标签，并设置放的位置
			loginName = new JTextField();
			loginName.setBounds(165, 115, 200, 28);
			panel.add(loginName);//添加用户输入用户名的文本域
			
			passLabel = new JLabel();
			passLabel.setText("密  码");
			passLabel.setFont(new Font("",Font.PLAIN,16));
			passLabel.setBounds(115, 160, 200, 28);
			panel.add(passLabel);//添加密码的标签，并设置放置位置
			JPasswordField userPassword = new JPasswordField();
			userPassword.setBounds(165, 160, 200, 28);
			panel.add(userPassword);
			login = new JButton();
			login.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) 
				{
					user = DatabaseOp.Login(loginName.getText(), userPassword.getText());
					if (user.getPass() == null || user.getlogName() == null) {
						loginName.setText(null);
						userPassword.setText(null);
						return;
					}
					setVisible(false);
					
					new BicycleManager();
				}
			});
			login.setText("登录");
			login.setBounds(163, 195, 60, 28);
			panel.add(login);//添加登录按钮
			
			reset = new JButton();
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					loginName.setText("");
		    		userPassword.setText("");
				}
			});
			reset.setText("重置");
			reset.setBounds(305, 195, 60, 28);
			panel.add(reset);//添加退出按钮
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		}
		
		public static void main(String[] args)
		{
			EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					JFrame frame=new Login();
					frame.setSize(500, 300);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
			
		}

		//返回通过登录得到的信息，比如是那个用户登录的
		public static UserList getUser() {
			// TODO Auto-generated method stub
			return user;
		}
		
		//通过登录可查询到用户的全部信息
		public static UserInfo getUserInfo() {
			// TODO Auto-generated method stub
			userInfo=DatabaseOp.getInfo(loginName.getText());
			return userInfo;
		}	
}
