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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import database.DatabaseOp;
import internalFrame.management.Item;
import login.Login;
import model.UserInfo;

public class SelectUser extends JInternalFrame {
	private JTable table;
	private JTextField conditionContent;
	private JComboBox conditionBox2;
	private JComboBox conditionBox1;
	private JButton showAllButton;
	public SelectUser() {
		super();
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setTitle("用户信息查询");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 640, 375);

		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		String[] tableHeads = new String[]{"车牌号", "姓名", "性别", "学号",
				"学院", "专业", "学部", "楼栋号", "宿舍号"};
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

		setupComponet(new JLabel(" 选择查询条件："), 0, 0, 1, 1, false);
		conditionBox1 = new JComboBox();
		conditionBox1.setModel(new DefaultComboBoxModel(new String[]{"用户全称",
				"用户简称"}));
		setupComponet(conditionBox1, 1, 0, 1, 30, true);

		conditionBox2 = new JComboBox();
		conditionBox2.setModel(new DefaultComboBoxModel(
				new String[]{"等于", "包含"}));
		setupComponet(conditionBox2, 2, 0, 1, 30, true);

		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);

		final JButton queryButton = new JButton();
		queryButton.addActionListener(new queryAction(dftm));
		queryButton.setText("查询");
		setupComponet(queryButton, 4, 0, 1, 1, false);

		showAllButton = new JButton();
		showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				conditionContent.setText("");
				List list = DatabaseOp.getUserInfos();
				updateTable(list, dftm);
			}
		});
		setupComponet(showAllButton, 5, 0, 1, 1, false);
		showAllButton.setText("显示全部数据");
	}
	// 设置组件位置并添加到容器中
		private void setupComponet(JComponent component, int gridx, int gridy,
				int gridwidth, int ipadx, boolean fill) 
		{
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
		
		//更新数据
		private void updateTable(List list, final DefaultTableModel dftm) 
		{
			int num = dftm.getRowCount();
			for (int i = 0; i < num; i++)
				dftm.removeRow(0);
			Iterator iterator = list.iterator();
			UserInfo yhInfo;
			while (iterator.hasNext()) {
				List info = (List) iterator.next();
				Item item = new Item();
				item.setId((String) info.get(0));
				item.setName((String) info.get(1));
				yhInfo = DatabaseOp.getUserInfo(item);
				Vector rowData = new Vector();
				rowData.add(yhInfo.getId().trim());
				rowData.add(yhInfo.getUserName().trim());
				rowData.add(yhInfo.getUsex().trim());
				rowData.add(yhInfo.getdept().trim());
				rowData.add(yhInfo.getmajor().trim());
				rowData.add(yhInfo.getUserNum().trim());
				rowData.add(yhInfo.getXueBu().trim());
				rowData.add(yhInfo.getBlocks().trim());
				rowData.add(yhInfo.getdomiNo().trim());
				rowData.add(yhInfo.getBicycleNo().trim());
				dftm.addRow(rowData);
			}
		}
		
		//查询动作
		private final class queryAction implements ActionListener {
			private final DefaultTableModel dftm;
			private queryAction(DefaultTableModel dftm) {
				this.dftm = dftm;
			}
			public void actionPerformed(final ActionEvent e) {
				String condition, conditionOperation, conditionString;
				List list = null;
				condition = conditionBox1.getSelectedItem().toString().trim();
				conditionOperation = conditionBox2.getSelectedItem().toString()
						.trim();
				conditionString = conditionContent.getText().trim();
				if (!conditionString.isEmpty()) 
				{
					String sql = "select * from tb_khinfo where ";
					if (condition.equals("用户全称")) {
						if (conditionOperation.equals("等于"))
							list = DatabaseOp.findForList(sql + "yhname='"
									+ conditionString + "'");
						else
							list = DatabaseOp.findForList(sql + "yhname like '%"
									+ conditionString + "%'");
					} else {
						if (conditionOperation.equals("等于"))
							list = DatabaseOp.findForList(sql + "jian='" + conditionString
									+ "'");
						else
							list = DatabaseOp.findForList(sql + "jian like '%"
									+ conditionString + "%'");
					}
					updateTable(list, dftm);
				} 
				else
				{
					showAllButton.doClick();
					JOptionPane.showMessageDialog(getContentPane(), "请输入查询内容！");
					return;
				}
					
			}
		}
		public static void main(String[] args)
		{
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
