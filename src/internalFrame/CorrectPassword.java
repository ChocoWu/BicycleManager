package internalFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

//import com.lzw.dao.Dao;

import login.Login;
import model.UserInfo;
import model.UserList;

public class CorrectPassword extends JInternalFrame {
	private JLabel logName;//����
	private JLabel userName;//ѧ�ţ�����
//	private JLabel BicycleNum;//���ƺ�
	private JPasswordField oldPass;
	private JPasswordField newPass2;
	private JPasswordField newPass1;
	private UserList user=Login.getUser();
	public CorrectPassword()
	{
		super();
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameActivated(final InternalFrameEvent e) {
				logName.setText("<html><b>" + user.getlogName()
						+ "</b></html>");
				userName.setText("<html><b>" + user.getuserName()
						+ "</b></html>");
//				BicycleNum.setText("<html><b>"+user.getBicycleNo()
//				        +"<b><html>");
			}
		});	
		
		setIconifiable(true);
		setTitle("��������");
		setClosable(true);
		setVisible(true);
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 300, 228);
//		final JLabel label_1 = new JLabel();
//		label_1.setFont(new Font("", Font.PLAIN, 14));
//		label_1.setForeground(Color.RED);
//		label_1.setText("<html></html>");//Ƕ����ҳ
//		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();//��۹�����
//		gridBagConstraints_1.weighty = 1.0;//weighty = 0.0; �����ڷŴ�ʱ���߶Ȳ���,�е�Ȩ�أ�ͨ�����������������η����е�ʣ��ռ�
//		gridBagConstraints_1.gridwidth = 4;//��ռ4����Ԫ��,�����ռ������Ҳ������Ŀ��
//		gridBagConstraints_1.gridy= 0;//����ĺ�������
//		gridBagConstraints_1.gridx = 0;//�����������
//		getContentPane().add(label_1, gridBagConstraints_1);
		
		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.PLAIN, 14));//��������
		label_2.setText("��¼����");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridy = 1;//ָ�������ʾ���򶥲��ĵ�Ԫ������������ĵ�Ԫ���gridy = 0��
		gridBagConstraints_2.gridx = 0;//ָ�����������ʾ����ǰԵ�ĵ�Ԫ���������еĵ�һ����Ԫ�����gridx = 0��
		getContentPane().add(label_2, gridBagConstraints_2);
		
		logName = new JLabel();
		logName.setPreferredSize(new Dimension(80,21));//����һ���߶�Ϊ80�����Ϊ21��label
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.weighty = 1.0;
		gridBagConstraints_3.ipadx = 30;//����ڲ����ռ䣬�����������С�����Ӷ��Ŀռ�,�����ĺ�����
		gridBagConstraints_3.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_3.fill = GridBagConstraints.HORIZONTAL;//�����������ڶ����ܳ������ʱ��ͨ�� fill��ֵ���趨��䷽ʽ�����ĸ�ֵ
		gridBagConstraints_3.gridy = 1;
		gridBagConstraints_3.gridx = 1;
		getContentPane().add(logName, gridBagConstraints_3);
		
		final JLabel label_3 = new JLabel();
		label_3.setFont(new Font("", Font.PLAIN, 14));
		label_3.setText("�û�������");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.gridx = 0;
		getContentPane().add(label_3, gridBagConstraints_4);

		userName = new JLabel();
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.gridy = 2;
		gridBagConstraints_5.gridx = 1;
		gridBagConstraints_5.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(userName, gridBagConstraints_5);
        
//		final JLabel label_4 = new JLabel();
//		label_4.setFont(new Font("", Font.PLAIN, 14));
//		label_4.setText("��  ��  �ţ�");
//		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
//		gridBagConstraints_6.gridy = 3;
//		gridBagConstraints_6.gridx = 0;
//		getContentPane().add(label_4, gridBagConstraints_6);

//		BicycleNum = new JLabel();
//		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
//		gridBagConstraints_7.gridy = 3;
//		gridBagConstraints_7.gridx = 1;
//		gridBagConstraints_7.fill = GridBagConstraints.HORIZONTAL;
//		getContentPane().add(BicycleNum, gridBagConstraints_7);
//		
		final JLabel label_5 = new JLabel();
		label_5.setFont(new Font("", Font.PLAIN, 14));
		label_5.setText("��  ��  �룺");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.gridy = 4;
		gridBagConstraints_8.gridx = 0;
		getContentPane().add(label_5, gridBagConstraints_8);

		oldPass = new JPasswordField();
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.weighty = 1.0;
		gridBagConstraints_9.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_9.gridwidth = 3;
		gridBagConstraints_9.gridy = 4;
		gridBagConstraints_9.gridx = 1;
		getContentPane().add(oldPass, gridBagConstraints_9);

		final JLabel label_6 = new JLabel();
		label_6.setFont(new Font("", Font.PLAIN, 14));
		label_6.setText("��  ��  �룺");
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.gridy = 5;
		gridBagConstraints_10.gridx = 0;
		getContentPane().add(label_6, gridBagConstraints_10);

		newPass1 = new JPasswordField();
		newPass1.setFont(new Font("", Font.PLAIN, 14));
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.weighty = 1.0;
		gridBagConstraints_11.ipadx = 30;
		gridBagConstraints_11.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_11.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_11.gridwidth = 3;
		gridBagConstraints_11.gridy = 5;
		gridBagConstraints_11.gridx = 1;
		getContentPane().add(newPass1, gridBagConstraints_11);

		final JLabel label_7 = new JLabel();
		label_7.setFont(new Font("", Font.PLAIN, 14));
		label_7.setText("ȷ�������룺");
		final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
		gridBagConstraints_12.gridy = 6;
		gridBagConstraints_12.gridx = 0;
		getContentPane().add(label_7, gridBagConstraints_12);
		
		newPass2 = new JPasswordField();
		newPass2.setFont(new Font("", Font.PLAIN, 14));
		final GridBagConstraints gridBagConstraints_13 = new GridBagConstraints();
		gridBagConstraints_13.weighty = 1.0;
		gridBagConstraints_13.ipadx = 30;
		gridBagConstraints_13.insets = new Insets(0, 0, 0, 10);
		gridBagConstraints_13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints_13.gridwidth = 3;
		gridBagConstraints_13.gridy = 6;
		gridBagConstraints_13.gridx = 1;
		getContentPane().add(newPass2, gridBagConstraints_13);
		

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (oldPass.getText().equals(user.getPass())) {
					if (newPass1.getText().equals(newPass2.getText())) {
						user.setPass(newPass1.getText());
						//Dao.updateUser(user);
						oldPass.setText(null);
						newPass1.setText(null);
						newPass2.setText(null);
						JOptionPane.showMessageDialog(getContentPane(), "�����޸ĳɹ���");
					}else {
						JOptionPane.showMessageDialog(getContentPane(), "������������벻һ�£����������롣");
					}
				}else {
					JOptionPane.showMessageDialog(getContentPane(), "����������������������롣");
				}
			}
		});
		button.setText("ȷ��");
		final GridBagConstraints gridBagConstraints_14 = new GridBagConstraints();
		gridBagConstraints_14.weighty = 1.0;
		gridBagConstraints_14.anchor = GridBagConstraints.EAST;
		gridBagConstraints_14.gridy = 7;
		gridBagConstraints_14.gridx = 0;
		getContentPane().add(button, gridBagConstraints_14);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				oldPass.setText(null);
				newPass1.setText(null);
				newPass2.setText(null);
			}
		});
		button_1.setText("����");
		final GridBagConstraints gridBagConstraints_15 = new GridBagConstraints();
		gridBagConstraints_15.gridwidth = 2;
		gridBagConstraints_15.weighty = 1.0;
		gridBagConstraints_15.gridy = 7;
		gridBagConstraints_15.gridx = 2;
		getContentPane().add(button_1, gridBagConstraints_15);
	}

	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){
			         public void run() {
			        	 JFrame frame = new JFrame();
			     		 frame.setBounds(100, 100, 800, 600); 
			     		 desktopPane = new JDesktopPane();
			    		 frame.getContentPane().add(desktopPane);
			    		 JInternalFrame internalFrame = new CorrectPassword();
			    		 desktopPane.add(internalFrame);
			    		 internalFrame.setVisible(true);
			        	 frame.setVisible(true);
			         }
		             JDesktopPane desktopPane;
				});
	}
}

