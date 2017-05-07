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
    	private static UserInfo userInfo;//�û���ȫ����Ϣ
    	
		public Login() {
			setTitle("У԰���г�����ϵͳ");
			setSize(500, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			final JPanel panel = new LoginPanel();
			panel.setLayout(null);
			getContentPane().add(panel);
			setBounds(500, 300, panel.getWidth(), panel.getHeight());//���һ��������Ȼ���������С�����õ�λ��
			
			titleLabel =new JLabel();
			titleLabel.setText("У԰���г�����ϵͳ");
			titleLabel.setFont(new Font("", Font.PLAIN, 18));
			titleLabel.setBounds(180, 70, 200, 28);;
			panel.add(titleLabel);
			
			userLabel = new JLabel();
			userLabel.setText("�û���");
			userLabel.setFont(new Font("",Font.PLAIN,16));
			userLabel.setBounds(115, 115, 200, 28);
			panel.add(userLabel);//����û����ı�ǩ�������÷ŵ�λ��
			loginName = new JTextField();
			loginName.setBounds(165, 115, 200, 28);
			panel.add(loginName);//����û������û������ı���
			
			passLabel = new JLabel();
			passLabel.setText("��  ��");
			passLabel.setFont(new Font("",Font.PLAIN,16));
			passLabel.setBounds(115, 160, 200, 28);
			panel.add(passLabel);//�������ı�ǩ�������÷���λ��
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
			login.setText("��¼");
			login.setBounds(163, 195, 60, 28);
			panel.add(login);//��ӵ�¼��ť
			
			reset = new JButton();
			reset.addActionListener(new ActionListener() {
				public void actionPerformed(final ActionEvent e) {
					loginName.setText("");
		    		userPassword.setText("");
				}
			});
			reset.setText("����");
			reset.setBounds(305, 195, 60, 28);
			panel.add(reset);//����˳���ť
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

		//����ͨ����¼�õ�����Ϣ���������Ǹ��û���¼��
		public static UserList getUser() {
			// TODO Auto-generated method stub
			return user;
		}
		
		//ͨ����¼�ɲ�ѯ���û���ȫ����Ϣ
		public static UserInfo getUserInfo() {
			// TODO Auto-generated method stub
			userInfo=DatabaseOp.getInfo(loginName.getText());
			return userInfo;
		}	
}
