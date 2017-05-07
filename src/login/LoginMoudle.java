package login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginMoudle extends JFrame {
	private JButton button1;
	private JButton button2;
	private Image image1;
	private Image image2;
	private JLabel text;
	private JPanel panel;
	public LoginMoudle()
	{
		panel=new JPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		text=new JLabel();
		text.setText("校园自行车管理系统");
		text.setBounds(140, 100, 180, 50);
		text.setFont(new Font("微软雅黑",Font.BOLD,16));
		panel.add(text);
		image1=new ImageIcon("").getImage();
		button1=new JButton();
		button1.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				//添加按钮的动作
			}
				});
		button1.setText("管理员登录");
		button1.setBounds(90, 170, 120, 30);
		button1.setFont(new Font("微软雅黑",Font.PLAIN,14));
		panel.add(button1);
		image2=new ImageIcon("").getImage();
		button2=new JButton();
		button2.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				//添加按钮动作
			}
				});
		button2.setText("用户登录");
		button2.setBounds(230, 170, 120, 30);
		button2.setFont(new Font("微软雅黑",Font.PLAIN,14));
		panel.add(button2);
		image2=new ImageIcon("").getImage();
	}
	//测试
//	public static void main(String[] args)
//	{
//		EventQueue.invokeLater(new Runnable()
//		{
//			public void run()
//			{
//				JFrame frame=new LoginMoudle();
//				frame.setSize(500, 300);
//				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame.setVisible(true);
//			}
//		});
//	}
}
