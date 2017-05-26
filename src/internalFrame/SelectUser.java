 package internalFrame;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import database.DatabaseOp;
import login.Login;
import model.UserInfo;

public class SelectUser extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionBox2;
	private JComboBox conditionBox1;
	private JButton showAllButton;
	private final DefaultTableModel dftm;
	private JButton modify;//�޸İ�ť�������ʱȷ���Ƿ��޸�����
	private JButton delete;//ɾ����Ϣ
	private JTextField bicycleNo;
	private JTextField userName;
	private JTextField logName;
	private JTextField uSex;
	private JTextField uDept;
	private JTextField uMajor;
	private JTextField uNum;
	private JTextField XueBu;
	private JTextField Blocks;
	private JTextField domiNo;	
	
	
	public SelectUser() {
		super();
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setTitle("�û���Ϣ��ѯ");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 640, 475);

		table = new JTable();
//		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(20);
		String[] tableHeads = new String[]{"���ƺ�", "�û���","��¼��", "�Ա�", 
				"ѧԺ", "רҵ","ѧ��","ѧ��", "¥����", "�����","Ȩ��"};
		dftm = (DefaultTableModel) table.getModel();
		dftm.setColumnIdentifiers(tableHeads);
		table.addMouseListener(new MouseAction());
		final JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setAutoscrolls(true);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weighty = 1.0;
		gridBagConstraints_6.insets = new Insets(0, 10, 5, 10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane, gridBagConstraints_6);

		setupComponent(new JLabel(" ѡ���ѯ������"), 0, 0, 1, 1, false);
		conditionBox1 = new JComboBox();
		conditionBox1.setModel(new DefaultComboBoxModel(new String[]{"��¼��",
				"�û�����"}));
		setupComponent(conditionBox1, 1, 0, 1, 30, true);

		conditionBox2 = new JComboBox();
		conditionBox2.setModel(new DefaultComboBoxModel(
				new String[]{"����", "����"}));
		setupComponent(conditionBox2, 2, 0, 1, 30, true);

		conditionContent = new JTextField();
		setupComponent(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		queryButton.addActionListener(new queryAction(dftm));
		queryButton.setText("��ѯ");
		setupComponent(queryButton, 4, 0, 1, 1, false);

		showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = DatabaseOp.getUserInfos();
				updateTable(list, dftm);
			}
		});
		setupComponent(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("��ʾȫ������");
		
		JLabel label_1=new JLabel("���ƺţ�");
		setupComponent(label_1,0,3,1,1,false);
		bicycleNo=new JTextField();
		bicycleNo.setEditable(false);
		setupComponent(bicycleNo,1,3,2,1,true);
		JLabel label_2=new JLabel("�û�����");
		setupComponent(label_2,3,3,1,1,false);
		userName=new JTextField();
		setupComponent(userName,4,3,2,1,true);
		
		JLabel label_3=new JLabel("��¼����");
		setupComponent(label_3,0,4,1,1,false);
		logName=new JTextField();
		setupComponent(logName,1,4,2,1,true);
		logName.setEditable(false);
		JLabel label_4=new JLabel("�Ա�");
		setupComponent(label_4,3,4,1,1,false);
		uSex=new JTextField();
		setupComponent(uSex,4,4,2,1,true);
		
		JLabel label_5=new JLabel("ѧԺ��");
		setupComponent(label_5,0,5,1,1,false);
		uDept=new JTextField();
		setupComponent(uDept,1,5,2,1,true);
		JLabel label_6=new JLabel("רҵ��");
		setupComponent(label_6,3,5,1,1,false);
		uMajor=new JTextField();
		setupComponent(uMajor,4,5,2,1,true);
		
		JLabel label_7=new JLabel("ѧ�ţ�");
		setupComponent(label_7,0,6,1,1,false);
		uNum=new JTextField();
		setupComponent(uNum,1,6,2,1,true);
		JLabel label_8=new JLabel("ѧ����");
		setupComponent(label_8,3,6,1,1,false);
		XueBu=new JTextField();
		setupComponent(XueBu,4,6,2,1,true);
		
		JLabel label_9=new JLabel("¥���ţ�");
		setupComponent(label_9,0,7,1,1,false);
		Blocks=new JTextField();
		setupComponent(Blocks,1,7,2,1,true);
		JLabel label_10=new JLabel("����ţ�");
		setupComponent(label_10,3,7,1,1,false);
		domiNo=new JTextField();
		setupComponent(domiNo,4,7,2,1,true);
		
		modify=new JButton("�޸�");
		setupComponent(modify,2,8,1,1,false);
		modify.addActionListener(new ModifyAction());
		delete=new JButton("ɾ��");
		setupComponent(delete,4,8,1,1,false);
		delete.addActionListener(new DeleteAction());
		
	}
	
	// �������λ�ò���ӵ�������
		private void setupComponent(JComponent component, int gridx, int gridy,
				int gridwidth, int ipadx, boolean fill) {
			final GridBagConstraints gridBagConstrains = new GridBagConstraints();
			gridBagConstrains.gridx = gridx;
			gridBagConstrains.gridy = gridy;
			if (gridwidth > 1)
				gridBagConstrains.gridwidth = gridwidth;
			if (ipadx > 0)
				gridBagConstrains.ipadx = ipadx;
			gridBagConstrains.insets = new Insets(5, 1, 3, 1);
			if (fill)
				gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
			getContentPane().add(component, gridBagConstrains);
		}
		
		//��������
		private void updateTable(List list, final DefaultTableModel dftm){
			int num = dftm.getRowCount();//�õ�TABLE������
			for (int i = 0; i < num; i++)//��ʼ��
				dftm.removeRow(0);
			Iterator iterator = list.iterator();
			UserInfo uInfo;
			while (iterator.hasNext()) {
				List info = (List) iterator.next();
				uInfo = DatabaseOp.getUserInfo((String)info.get(2));//ͨ����ѯ��¼���ҵ������Ϣ
				Vector rowData = new Vector();
				rowData.add(uInfo.getBicycleNo());
				rowData.add(uInfo.getUserName());
				rowData.add(uInfo.getLogName());
				rowData.add(uInfo.getUsex());
				rowData.add(uInfo.getdept());
				rowData.add(uInfo.getmajor());
				rowData.add(uInfo.getUserNum());
				rowData.add(uInfo.getXueBu());
				rowData.add(uInfo.getBlocks());
				rowData.add(uInfo.getdomiNo());
				rowData.add(uInfo.getQuan());
				dftm.addRow(rowData);
			}
		}
		
		//��ѯ����
		private final class queryAction implements ActionListener {
			private final DefaultTableModel dftm;
			private queryAction(DefaultTableModel dftm) {
				this.dftm = dftm;
			}
			public void actionPerformed(final ActionEvent e) {
				String condition, conditionOperation, conditionString;
				List list = null;
				condition = conditionBox1.getSelectedItem().toString().trim();
				conditionOperation = conditionBox2.getSelectedItem().toString().trim();
				conditionString = conditionContent.getText().trim();
				if (!conditionString.isEmpty()) {
					String sql = "select * from UserInfo where ";
					if (condition.equals("��¼��")) {
						if (conditionOperation.equals("����"))
							list = DatabaseOp.findForList(sql + "logName='"+ conditionString + "'");
						else
							list = DatabaseOp.findForList(sql + "logName like '%"+ conditionString + "%'");
					} else {
						if (conditionOperation.equals("����"))
							list = DatabaseOp.findForList(sql + "userName='" + conditionString+ "'");
						else
							list = DatabaseOp.findForList(sql + "userName like '%"+ conditionString + "%'");
					}
					updateTable(list, dftm);
				} 
				else{
					showAllButton.doClick();
					JOptionPane.showMessageDialog(getContentPane(), "�������ѯ���ݣ�");
					return;
				}	
			}
		}
		
		//�޸���Ϣ
		class ModifyAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int op=JOptionPane.showConfirmDialog(SelectUser.this, "ȷ���޸Ĵ���Ϣ��");
				if(op==JOptionPane.OK_OPTION){
					int i=DatabaseOp.update("update UserInfo set userName='"+userName.getText()+"',logName='"
							+logName.getText()+"',userSex='"+uSex.getText()+"',dept='"+uDept.getText()+"',major='"
							+uMajor.getText()+"',userNum='"+uNum.getText()+"',XueBu='"+XueBu.getText()+"',Blocks='"
							+Blocks.getText()+"',domiNo='"+domiNo.getText()+"' where bicycleNo='"+bicycleNo.getText()+"'");

					if(i>0){
						JOptionPane.showMessageDialog(SelectUser.this, "��Ϣ�޸ĳɹ�");
						List list = DatabaseOp.getUserInfos();
						updateTable(list, dftm);
					}else{
						JOptionPane.showMessageDialog(SelectUser.this, "��Ϣ�޸�ʧ��");
					}
				}
			}	
		}
		
		//ɾ����Ϣ
		class DeleteAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int op = JOptionPane.showConfirmDialog(SelectUser.this,
						"ȷ��Ҫɾ�����û���Ϣ��ɾ��ʱ����ͬ���г���Ϣһ��ɾ��)��");
				if(op==JOptionPane.OK_OPTION){
					int i=DatabaseOp.delete("delete from BicycleList where bicycleNo='"+bicycleNo.getText()+"'");
					int j=DatabaseOp.delete("delete from UserInfo where bicycleNo='"+bicycleNo.getText()+"'");
					int k=DatabaseOp.delete("delete from UserList where logName='"+logName.getText()+"'");
					if(i>0 && j>0 && k>0){
					JOptionPane.showMessageDialog(SelectUser.this, "ɾ���ɹ�");
					}else{
						JOptionPane.showMessageDialog(SelectUser.this, "ɾ��ʧ��");
					}
				}else if(op==JOptionPane.NO_OPTION){
					DatabaseOp.delete("delete  from UserInfo where bicycleNo=' "+bicycleNo.getText()+"'");
				}
			}
		}
		
		//������Զ������Ϣ�������б����
		class  MouseAction extends  MouseAdapter{
			public void mouseClicked(final MouseEvent e) {
				int selRow = table.getSelectedRow();
				bicycleNo.setText(table.getValueAt(selRow, 0).toString().trim());
				userName.setText(table.getValueAt(selRow, 1).toString().trim());
				logName.setText(table.getValueAt(selRow, 2).toString().trim());
				uSex.setText(table.getValueAt(selRow, 3).toString().trim());
				uDept.setText(table.getValueAt(selRow, 4).toString().trim());
				uMajor.setText(table.getValueAt(selRow, 5).toString().trim());
				uNum.setText(table.getValueAt(selRow, 6).toString().trim());
				XueBu.setText(table.getValueAt(selRow, 7).toString().trim());
				Blocks.setText(table.getValueAt(selRow, 8).toString().trim());
				domiNo.setText(table.getValueAt(selRow, 9).toString().trim());
			}
			
		}
		
		
		
		
		public static void main(String[] args){
			EventQueue.invokeLater(new Runnable()
					{
				         public void run()
				         {
				        	 JFrame frame = new JFrame();
				     		 frame.setBounds(100, 100, 800, 600); 
				     		 desktopPane = new JDesktopPane();
				    		 frame.getContentPane().add(desktopPane);
				    		 JInternalFrame internalFrame = new SelectUser();
				    		 desktopPane.add(internalFrame);
				    		 internalFrame.setVisible(true);
				        	 frame.setVisible(true);
				         }
			             JDesktopPane desktopPane;
					});
		}


}
