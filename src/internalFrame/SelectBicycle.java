package internalFrame;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableModel;

import database.DatabaseOp;
import internalFrame.czyGL.DeleteCzy;
import model.BicycleList;

public class SelectBicycle extends JInternalFrame {
	private JTable table;
	private JTextField bicycleNo;//���г����ƺ�
	private JTextField zhuangTai;//���г�״̬
	private JTextField condition;
	private JComboBox conditionName;
	private JComboBox conditionOperation;
	private DefaultTableModel dftm;
	private JButton queryButton;
	private JButton showAllInfo;
	private JButton delete;//ʵ��ɾ�������г�
	private JButton modify;//ʵ�����г�״̬���޸�
	public SelectBicycle()
	{
		super();
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setTitle("���г���Ϣ��ѯ");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 640, 375);
		
		table = new JTable();
//		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.setRowHeight(20);
		String[] tableHeads = new String[]{"���ƺ�", "״̬"};
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		dftm.setColumnIdentifiers(tableHeads);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				String bNo, zhta;
				int selRow = table.getSelectedRow();
				bNo=table.getValueAt(selRow, 0).toString().trim();
				zhta=table.getValueAt(selRow, 1).toString().trim();
				bicycleNo.setText(bNo);
				zhuangTai.setText(zhta);
			}
		});
		
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
		conditionName=new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"���ƺ�","״̬"}));
		setupComponent(conditionName,1,0,1,30,true);
		
		conditionOperation =new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"����","����"}));
		setupComponent(conditionOperation,2,0,1,30,true);
		
		condition=new JTextField();
		setupComponent(condition,3,0,1,140,true);
		
		
		queryButton=new JButton();
		queryButton.setText("��ѯ");
		queryButton.addActionListener(new queryAciton(dftm));
		setupComponent(queryButton,4,0,1,1,false);
		
		showAllInfo=new JButton();
		showAllInfo.setText("��ʾ���е�����");
		showAllInfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				condition.setText("");
				List list=DatabaseOp.getBicycleInfos();
				updateTable(list, dftm);
			}
				});
		setupComponent(showAllInfo,5,0,1,1,false);
		
		JLabel bicycle=new JLabel("���ƺţ�");
		setupComponent(bicycle,0,3,1,0,false);
		bicycleNo=new JTextField();
		setupComponent(bicycleNo,1,3,2,0,true);
		bicycleNo.setEditable(false);
		JLabel zhuangtai=new JLabel("״̬��");
		setupComponent(zhuangtai,3,3,1,0,false);
		zhuangTai=new JTextField();
		zhuangTai.setEditable(true);
		setupComponent(zhuangTai,4,3,2,0,true);
		delete=new JButton("ɾ��");
		delete.addActionListener(new DeleteAction());
		setupComponent(delete,1,4,1,0,false);
		modify=new JButton("�޸�");
		modify.addActionListener(new ModifyAction());
		setupComponent(modify,3,4,1,0,false);
		
		
	}
	
	//ɾ����ť�����¼�
	private final class DeleteAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int op = JOptionPane.showConfirmDialog(SelectBicycle.this,
					"ȷ��Ҫɾ�������г���Ϣ��ɾ��ʱ����ͬ������Ϣһ��ɾ��)��");
			if(op==JOptionPane.OK_OPTION){
				int i=DatabaseOp.delete("delete from BicycleList where bicycleNo='"+bicycleNo.getText()+"'");
				int j=DatabaseOp.delete("delete from UserInfo where bicycleNo='"+bicycleNo.getText()+"'");
				
				if(i>0 || j>0){
					JOptionPane.showMessageDialog(SelectBicycle.this, "ɾ���ɹ�");
				}else{
					JOptionPane.showMessageDialog(SelectBicycle.this, "ɾ��ʧ��");
				}
			}else if(op==JOptionPane.NO_OPTION){
				DatabaseOp.delete("delete  from BicycleList where bicycleNo=' "+bicycleNo.getText()+"'");
			}
		}
	}
	
	//�޸İ�ť�����¼�
	private final class ModifyAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int op = JOptionPane.showConfirmDialog(SelectBicycle.this,
					"ȷ���޸���Ϣ��");
			if(op==JOptionPane.OK_OPTION){
				if(DatabaseOp.update("update BicycleList set ZhuangTai='"+zhuangTai.getText()
				+"'"+" where BicycleNo='"+bicycleNo.getText()+"'")>0){
					JOptionPane.showMessageDialog(SelectBicycle.this, "��Ϣ�޸ĳɹ�");
				}else{
					JOptionPane.showMessageDialog(SelectBicycle.this,"��Ϣ�޸�ʧ��");
				}
			}
		}
	}
	
	//�������ӵ�����
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
	
	//��ѯ��ť�ļ�������
	 private final class  queryAciton implements ActionListener {
		private DefaultTableModel dftm;
		private queryAciton(DefaultTableModel dftm){
			this.dftm=dftm;
		}
		public void actionPerformed(ActionEvent e){
			String conName,conOperation,content;
			conName=conditionName.getSelectedItem().toString().trim();//ѡ�񡰳��ƺš����ߡ�״̬����ѯ
			conOperation=conditionOperation.getSelectedItem().toString().trim();//�������ߵ���
			content=condition.getText().trim();//����Ĳ�ѯ����
			List list=null;
			if(!content.isEmpty()){
				String sql="select * from BicycleList where ";
				if(conName.equals("���ƺ�")){
					if(conOperation.equals("����"))
						list=DatabaseOp.findForList(sql+"bicycleNo='"+content+"'");
					else
						list=DatabaseOp.findForList(sql+"bicycleNo like '%"+content+"%'");
				}else{
					if(conOperation.equals("����"))
						list=DatabaseOp.findForList(sql+"ZhuangTai='"+content+"'");
					else
						list=DatabaseOp.findForList(sql+"ZhuangTai like '%"+content+"%'");		
				}
				updateTable(list,dftm);
			}else{
				showAllInfo.doClick();
				JOptionPane.showMessageDialog(getContentPane(), "�������ѯ������");
				
			}
		}
	 }
	 
	 private void updateTable(List list, final DefaultTableModel dtm){
		int num=dtm.getRowCount();//�õ�����
		for(int i=0;i<num;i++)
			dtm.removeRow(0);//���Ŀǰ�е�����
		Iterator iterator=list.iterator();
		BicycleList bicycleInfo;
		while(iterator.hasNext()){
			List Info=(List)iterator.next();
			bicycleInfo=DatabaseOp.getBicycleInfo((String)Info.get(0));
			Vector vector=new Vector();
			vector.add(bicycleInfo.getBicycleNo());
			vector.add(bicycleInfo.getZhuangTai());
			dtm.addRow(vector);
		}
	 }
	 public static void main(String[] args){
			EventQueue.invokeLater(new Runnable(){
				         public void run() {
				        	 JFrame frame = new JFrame();
				     		 frame.setBounds(100, 100, 800, 600); 
				     		 desktopPane = new JDesktopPane();
				    		 frame.getContentPane().add(desktopPane);
				    		 JInternalFrame internalFrame = new SelectBicycle();
				    		 desktopPane.add(internalFrame);
				    		 internalFrame.setVisible(true);
				        	 frame.setVisible(true);
				         }
			             JDesktopPane desktopPane;
					});
		}
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}

	
}
