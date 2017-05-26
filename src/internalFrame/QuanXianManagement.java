package internalFrame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import database.DatabaseOp;
import login.Login;
import model.UserList;

public class QuanXianManagement extends JInternalFrame {
	private JTextField LogName;//�û���¼����
	private JComboBox quanXian;
	private JTextField username;//�û�����
	private JPasswordField password;//��¼����
	private JButton modifyButton;//�޸İ�ť
	private JButton closeButton;//�رհ�ť
	private JComboBox userCombo;//ѡ���û�
	private UserList user;
//	private String logName=Login.getUser().getlogName();
	
	public QuanXianManagement()
	{
		super();
		//���弤���¼�
		addInternalFrameListener(new InternalFrameAdapter(){
			public void internalFrameActivated( final InternalFrameEvent e){
				initCombox();
			}
		});
		setClosable(true);
		setIconifiable(true);
		setVisible(true);
		setBounds(10, 10, 500, 250);
		setTitle("Ȩ�޹���");
		setLayout(new GridBagLayout());
		setVisible(true);
		
		final JLabel LoginName=new JLabel();
		LoginName.setText("��¼����:");
		setupComponent(LoginName,0,0,1,0,false);
		LogName = new JTextField();
		LogName.setEditable(false);
		setupComponent(LogName, 1, 0, 1, 100, true);
		
		final JLabel Username=new JLabel();
		Username.setText("�û�����");
		setupComponent(Username,0,1,1,0,false);
		username=new JTextField();
		username.setEditable(false);
		setupComponent(username,1,1,1,100,true);
		
		final JLabel pass=new JLabel();
		pass.setText("���룺");
		setupComponent(pass,0,2,1,0,false);
		password=new JPasswordField();
		password.setEditable(false);
		setupComponent(password,1,2,1,100,true);
		
		setupComponent(new JLabel("Ȩ�ޣ�"), 0, 3, 1, 0, false);
		quanXian = new JComboBox(new String[]{"����Ա", "�û�"});
		setupComponent(quanXian, 1, 3, 1, 100, true);

		setupComponent(new JLabel("ѡ���û�"), 0, 4, 1, 0, false);
		userCombo = new JComboBox();
		userCombo.setPreferredSize(new Dimension(80, 21));
		// �����û���Ϣ������ѡ����ѡ���¼�
		userCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doUserSelectAction();
			}
		});
		setupComponent(userCombo,1,4,2,0,true);
		
		modifyButton = new JButton("�޸�");
		closeButton = new JButton("�ر�");
		setupComponent(modifyButton,0,5,1,0,false);
		setupComponent(closeButton,3,5,1,0,false);
		closeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				QuanXianManagement.this.doDefaultCloseAction();
			}
				});
		modifyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				//����ť���¼�
				UserList userList=new UserList();
				int index=quanXian.getSelectedIndex();//�õ�ѡ���������
				
				if(index==0)
					userList.setQuan("1");
				else
					userList.setQuan("0");
				System.out.println(userList.getQuan());
				userList.setlogName(user.getlogName());
				userList.setuserName(user.getuserName());
				userList.setPass(user.getPass());
				if(DatabaseOp.updateUser(userList)>=1)
					JOptionPane.showMessageDialog(QuanXianManagement.this, "�޸ĳɹ�");
				else
					JOptionPane.showMessageDialog(null, "�޸�ʧ��");
			}
				});
		
	}
	
	//��ʼ���û��б�,�����ݿ����Ϣ��ӵ�JComboBox��
	public void initCombox(){
//		List list=DatabaseOp.findForList("select * from UserList");
		ResultSet rs=DatabaseOp.findForResult("select * from UserList");
		List<String> list=new ArrayList<String>();
		userCombo.removeAllItems();
////	Iterator<String> iter=list.iterator();
//		while(iter.hasNext()){
//			List element = iter.next();//ǿ������ת��
//			userCombo.addItem(iter.next());
//		}
		try {
			while(rs.next()){
				list.add(rs.getString(2));
//				userCombo.addItem(rs.getString(2));
			}
			for(Iterator<String> iter=list.iterator();iter.hasNext();){
				userCombo.addItem(iter.next());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// �������λ�ò���ӵ�������
	private void setupComponent(JComponent component, int gridx,int gridy,int gridwidth,int ipadx,boolean fill){
		final GridBagConstraints gridBC=new GridBagConstraints();
		gridBC.gridx=gridx;
		gridBC.gridy=gridy;
		if(gridwidth>1)
			gridBC.gridwidth=gridwidth;
		if(ipadx>0)
			gridBC.ipadx=ipadx;
		gridBC.insets=new Insets(5,1,3,1);
		if(fill)
			gridBC.fill=GridBagConstraints.HORIZONTAL;
		add(component,gridBC);
	}
	
	private void doUserSelectAction(){
		Object selectedItem=userCombo.getSelectedItem();
		user=DatabaseOp.getUser(selectedItem.toString());
		LogName.setText(user.getlogName());
		username.setText(user.getuserName());
		password.setText(user.getPass());
//		if(user.getQuan().equals("1"))
//			quanXian.setSelectedIndex(0);
//		else
//			quanXian.setSelectedIndex(1);
	}
	
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				JFrame frame=new JFrame();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    desktopPane = new JDesktopPane();
	    		frame.getContentPane().add(desktopPane);
	    		JInternalFrame internalFrame = new QuanXianManagement();
	    	    desktopPane.add(internalFrame); 
			}
			JDesktopPane desktopPane;
				});
		
	}
}
