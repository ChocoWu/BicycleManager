package internalFrame;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.DatabaseOp;
import internalFrame.management.Item;
import model.BicycleList;

public class SelectBicycle extends JInternalFrame {
	private JTable table;
	private JLabel bicycle;//自行车车牌号
	private JTextField condition;
	private JComboBox conditionName;
	private JComboBox conditionOperation;
	private DefaultTableModel dftm;
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
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		String[] tableHeads = new String[]{"车牌号", "状态"};
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		dftm.setColumnIdentifiers(tableHeads);
		
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
		
		
		final JButton queryButton=new JButton();
		queryButton.setText("查询");
		queryButton.addActionListener(new queryAciton());
		setupComponent(queryButton,4,0,1,1,false);
		
		final JButton showAllInfo=new JButton();
		showAllInfo.setText("显示所有的数据");
		showAllInfo.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				condition.setText("");
				List list=DatabaseOp.getBicycleInfos();
				updateTable(list, dftm);
			}
				});
		setupComponent(showAllInfo,5,0,1,1,false);
	}
	
	//将组件添加到容器
	private void setupComponent(JComponent component ,int gridx,int gridy,int gridwidth,int ipdax,boolean fill)
	{
		final GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx=gridx;
		gbc.gridy=gridy;
		if(gridwidth>1)
		{
			gbc.gridwidth=gridwidth;
		}
		if(ipdax>0)
		{
			gbc.ipadx=ipdax;
		}
		gbc.insets=new Insets(5,1,3,1);
		if(fill)
		{
			gbc.fill=GridBagConstraints.HORIZONTAL;
		}
		getContentPane().add(component,gbc);
	}
	
	//查询按钮的监听动作
	 private final class  queryAciton implements ActionListener
	 {
		private DefaultTableModel dtm;
		
		private void queryAction()
		{
			this.dtm=dtm;
		}
		public void actionPerformed(ActionEvent e)
		{
			String conName,conOperation,content;
			conName=conditionName.getSelectedItem().toString().trim();
			conOperation=conditionOperation.getSelectedItem().toString().trim();
			content=condition.getText().trim();
			List list=null;
			list=searchInfo(conName,conOperation,content,list);
			updateTable(list,dtm);
		}
		private List searchInfo(String conName,String conOperation,String content,List list)
		{
			String sql="seclect * from BicycleList where";
			if(conOperation.equals("等于"))
			{
				if(conName.equals("车牌号"))
					//从数据库中查询
					list = DatabaseOp.findForList(sql + "BicycleNo='" + content + "'");
				else
					//从数据库中查询
					list=DatabaseOp.findForList(sql+"ZhuangTai='"+content+"'");
			}else{
				if(conName.equals("车牌号"))
					list=DatabaseOp.findForList(sql+"BicycleNo like '%"+content+"'");
				else
					list=DatabaseOp.findForList(sql+"ZhuangTai like '%"+content+"'");
			}	
			return list;
		}
	 }
	 
	 private void updateTable(List list, final DefaultTableModel dtm)
	 {
		int num=dtm.getRowCount();//得到行数
		for(int i=0;i<num;i++)
		{
			dtm.removeRow(i);//清除目前行的内容
		}
		Iterator iterator=list.iterator();
		BicycleList bicycleInfo;
		while(iterator.hasNext())
		{
			List Info=(List)iterator.next();
			Item item=new Item();
			item.setId((String)Info.get(0));
			item.setName((String)Info.get(1));
			bicycleInfo=DatabaseOp.getBicycleInfo(item);
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

	
}
