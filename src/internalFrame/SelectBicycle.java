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
	private JTextField bicycleNo;//自行车车牌号
	private JTextField zhuangTai;//自行车状态
	private JTextField condition;
	private JComboBox conditionName;
	private JComboBox conditionOperation;
	private DefaultTableModel dftm;
	private JButton queryButton;
	private JButton showAllInfo;
	private JButton delete;//实现删除此自行车
	private JButton modify;//实现自行车状态的修改
	public SelectBicycle()
	{
		super();
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setTitle("自行车信息查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 640, 375);
		
		table = new JTable();
//		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		table.setRowHeight(20);
		String[] tableHeads = new String[]{"车牌号", "状态"};
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
		
		setupComponent(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
		conditionName=new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"车牌号","状态"}));
		setupComponent(conditionName,1,0,1,30,true);
		
		conditionOperation =new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"包含","等于"}));
		setupComponent(conditionOperation,2,0,1,30,true);
		
		condition=new JTextField();
		setupComponent(condition,3,0,1,140,true);
		
		
		queryButton=new JButton();
		queryButton.setText("查询");
		queryButton.addActionListener(new queryAciton(dftm));
		setupComponent(queryButton,4,0,1,1,false);
		
		showAllInfo=new JButton();
		showAllInfo.setText("显示所有的数据");
		showAllInfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				condition.setText("");
				List list=DatabaseOp.getBicycleInfos();
				updateTable(list, dftm);
			}
				});
		setupComponent(showAllInfo,5,0,1,1,false);
		
		JLabel bicycle=new JLabel("车牌号：");
		setupComponent(bicycle,0,3,1,0,false);
		bicycleNo=new JTextField();
		setupComponent(bicycleNo,1,3,2,0,true);
		bicycleNo.setEditable(false);
		JLabel zhuangtai=new JLabel("状态：");
		setupComponent(zhuangtai,3,3,1,0,false);
		zhuangTai=new JTextField();
		zhuangTai.setEditable(true);
		setupComponent(zhuangTai,4,3,2,0,true);
		delete=new JButton("删除");
		delete.addActionListener(new DeleteAction());
		setupComponent(delete,1,4,1,0,false);
		modify=new JButton("修改");
		modify.addActionListener(new ModifyAction());
		setupComponent(modify,3,4,1,0,false);
		
		
	}
	
	//删除按钮监听事件
	private final class DeleteAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int op = JOptionPane.showConfirmDialog(SelectBicycle.this,
					"确认要删除该自行车信息（删除时会连同车主信息一并删除)？");
			if(op==JOptionPane.OK_OPTION){
				int i=DatabaseOp.delete("delete from BicycleList where bicycleNo='"+bicycleNo.getText()+"'");
				int j=DatabaseOp.delete("delete from UserInfo where bicycleNo='"+bicycleNo.getText()+"'");
				
				if(i>0 || j>0){
					JOptionPane.showMessageDialog(SelectBicycle.this, "删除成功");
				}else{
					JOptionPane.showMessageDialog(SelectBicycle.this, "删除失败");
				}
			}else if(op==JOptionPane.NO_OPTION){
				DatabaseOp.delete("delete  from BicycleList where bicycleNo=' "+bicycleNo.getText()+"'");
			}
		}
	}
	
	//修改按钮监听事件
	private final class ModifyAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int op = JOptionPane.showConfirmDialog(SelectBicycle.this,
					"确认修改信息？");
			if(op==JOptionPane.OK_OPTION){
				if(DatabaseOp.update("update BicycleList set ZhuangTai='"+zhuangTai.getText()
				+"'"+" where BicycleNo='"+bicycleNo.getText()+"'")>0){
					JOptionPane.showMessageDialog(SelectBicycle.this, "信息修改成功");
				}else{
					JOptionPane.showMessageDialog(SelectBicycle.this,"信息修改失败");
				}
			}
		}
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
	
	//查询按钮的监听动作
	 private final class  queryAciton implements ActionListener {
		private DefaultTableModel dftm;
		private queryAciton(DefaultTableModel dftm){
			this.dftm=dftm;
		}
		public void actionPerformed(ActionEvent e){
			String conName,conOperation,content;
			conName=conditionName.getSelectedItem().toString().trim();//选择“车牌号”或者“状态“查询
			conOperation=conditionOperation.getSelectedItem().toString().trim();//包含或者等于
			content=condition.getText().trim();//输入的查询条件
			List list=null;
			if(!content.isEmpty()){
				String sql="select * from BicycleList where ";
				if(conName.equals("车牌号")){
					if(conOperation.equals("等于"))
						list=DatabaseOp.findForList(sql+"bicycleNo='"+content+"'");
					else
						list=DatabaseOp.findForList(sql+"bicycleNo like '%"+content+"%'");
				}else{
					if(conOperation.equals("等于"))
						list=DatabaseOp.findForList(sql+"ZhuangTai='"+content+"'");
					else
						list=DatabaseOp.findForList(sql+"ZhuangTai like '%"+content+"%'");		
				}
				updateTable(list,dftm);
			}else{
				showAllInfo.doClick();
				JOptionPane.showMessageDialog(getContentPane(), "请输入查询的内容");
				
			}
		}
	 }
	 
	 private void updateTable(List list, final DefaultTableModel dtm){
		int num=dtm.getRowCount();//得到行数
		for(int i=0;i<num;i++)
			dtm.removeRow(0);//清除目前行的内容
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
