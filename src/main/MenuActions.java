package main;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import database.DatabaseOp;
import internalFrame.CorrectPassword;
import internalFrame.CzyGL;
import internalFrame.QuanXianManagement;
import internalFrame.SelectBicycle;
import internalFrame.SelectUser;
import internalFrame.UserManager;
import internalFrame.UserManger.AddUser;

public class MenuActions {
	private static Map<String, JInternalFrame> frames;
	public static CorrectPassAction MODIFY_PASS;
	public static UserManageAction USER_MANAGE;
	public static CzyGLAction CZY_GL;
	public static QuanXianAction QUAN_MANAGE;
	public static SelectUserAction SELECT_USER;
	public static SelectBicycleAction SELECT_BICYCLE;
	public static ExitAction EXIT;
	
	static
	{
		frames=new HashMap<String, JInternalFrame>();
		MODIFY_PASS=new CorrectPassAction();
		USER_MANAGE=new UserManageAction();
		CZY_GL=new CzyGLAction();
		QUAN_MANAGE=new QuanXianAction();
		SELECT_USER=new SelectUserAction();
		SELECT_BICYCLE=new SelectBicycleAction();
		EXIT=new ExitAction();
	}
	
	
	
	//更改密码
	private static class CorrectPassAction extends AbstractAction
	{
		CorrectPassAction()
		{
			putValue(Action.NAME,"更改密码");
			putValue(Action.SHORT_DESCRIPTION,"更改密码");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("更改密码")||frames.get("更改密码").isClosed()) {
				CorrectPassword iframe=new CorrectPassword();
				frames.put("更改密码", iframe);
				BicycleManager.addIFame(frames.get("更改密码"));
			}
		}
	}
	
	//用户管理
	private static class  UserManageAction extends AbstractAction
	{
		UserManageAction(){
			putValue(Action.NAME,"用户管理");
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(!frames.containsKey("用户管理") || frames.get("用户管理").isClosed()){
				UserManager iframe=new UserManager();
				frames.put("用户管理", iframe);
				BicycleManager.addIFame(frames.get("用户管理"));
			}
		}
	}
	
	//操作员管理事件
	private static class CzyGLAction extends AbstractAction
	{
		CzyGLAction()
		{
			super("操作员管理",null);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!frames.containsKey("操作员管理")||frames.get("操作员管理").isClosed()){
				CzyGL iframe=new CzyGL();
				frames.put("操作员管理", iframe);
				BicycleManager.addIFame(frames.get("操作员管理"));
			}
		}
	}
	
	//权限修改
	private static class QuanXianAction extends AbstractAction{
		QuanXianAction()
		{
			super("权限管理",null);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(!frames.containsKey("权限管理")|| frames.get("权限管理").isClosed())
			{
				QuanXianManagement iframe=new QuanXianManagement();
				frames.put("权限管理", iframe);
				BicycleManager.addIFame(frames.get("权限管理"));
			}
		}
	}
	
	//查找用户
	private static class SelectUserAction extends AbstractAction{
		SelectUserAction (){
			super("用户信息查询",null);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(!frames.containsKey("用户信息查询")||frames.get("用户信息查询").isClosed())
			{
				SelectUser iframe=new SelectUser();
				frames.put("用户信息查询", iframe);
				BicycleManager.addIFame(frames.get("用户信息查询"));
			}
		}
	}
	
	
	//查找自行车
	private static class SelectBicycleAction extends AbstractAction{
		SelectBicycleAction(){
			super("自行车信息查询",null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!frames.containsKey("自行车信息查询")||frames.get("自行车信息查询").isClosed())
			{
				SelectBicycle iframe=new SelectBicycle();
				frames.put("自行车信息查询", iframe);
				BicycleManager.addIFame(frames.get("自行车信息查询"));
			}
		}
	}
	
	//退出系统
	private static class ExitAction extends AbstractAction{
		@SuppressWarnings("unused")
		public ExitAction() {
			super("退出系统", null);
			putValue(Action.LONG_DESCRIPTION, "退出校园自行车管理系统");
			putValue(Action.SHORT_DESCRIPTION, "退出系统");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
			DatabaseOp.close();
		}
	}
	
		
}
