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
	
	
	
	//��������
	private static class CorrectPassAction extends AbstractAction
	{
		CorrectPassAction()
		{
			putValue(Action.NAME,"��������");
			putValue(Action.SHORT_DESCRIPTION,"��������");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (!frames.containsKey("��������")||frames.get("��������").isClosed()) {
				CorrectPassword iframe=new CorrectPassword();
				frames.put("��������", iframe);
				BicycleManager.addIFame(frames.get("��������"));
			}
		}
	}
	
	//�û�����
	private static class  UserManageAction extends AbstractAction
	{
		UserManageAction(){
			putValue(Action.NAME,"�û�����");
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(!frames.containsKey("�û�����") || frames.get("�û�����").isClosed()){
				UserManager iframe=new UserManager();
				frames.put("�û�����", iframe);
				BicycleManager.addIFame(frames.get("�û�����"));
			}
		}
	}
	
	//����Ա�����¼�
	private static class CzyGLAction extends AbstractAction
	{
		CzyGLAction()
		{
			super("����Ա����",null);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!frames.containsKey("����Ա����")||frames.get("����Ա����").isClosed()){
				CzyGL iframe=new CzyGL();
				frames.put("����Ա����", iframe);
				BicycleManager.addIFame(frames.get("����Ա����"));
			}
		}
	}
	
	//Ȩ���޸�
	private static class QuanXianAction extends AbstractAction{
		QuanXianAction()
		{
			super("Ȩ�޹���",null);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(!frames.containsKey("Ȩ�޹���")|| frames.get("Ȩ�޹���").isClosed())
			{
				QuanXianManagement iframe=new QuanXianManagement();
				frames.put("Ȩ�޹���", iframe);
				BicycleManager.addIFame(frames.get("Ȩ�޹���"));
			}
		}
	}
	
	//�����û�
	private static class SelectUserAction extends AbstractAction{
		SelectUserAction (){
			super("�û���Ϣ��ѯ",null);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(!frames.containsKey("�û���Ϣ��ѯ")||frames.get("�û���Ϣ��ѯ").isClosed())
			{
				SelectUser iframe=new SelectUser();
				frames.put("�û���Ϣ��ѯ", iframe);
				BicycleManager.addIFame(frames.get("�û���Ϣ��ѯ"));
			}
		}
	}
	
	
	//�������г�
	private static class SelectBicycleAction extends AbstractAction{
		SelectBicycleAction(){
			super("���г���Ϣ��ѯ",null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(!frames.containsKey("���г���Ϣ��ѯ")||frames.get("���г���Ϣ��ѯ").isClosed())
			{
				SelectBicycle iframe=new SelectBicycle();
				frames.put("���г���Ϣ��ѯ", iframe);
				BicycleManager.addIFame(frames.get("���г���Ϣ��ѯ"));
			}
		}
	}
	
	//�˳�ϵͳ
	private static class ExitAction extends AbstractAction{
		@SuppressWarnings("unused")
		public ExitAction() {
			super("�˳�ϵͳ", null);
			putValue(Action.LONG_DESCRIPTION, "�˳�У԰���г�����ϵͳ");
			putValue(Action.SHORT_DESCRIPTION, "�˳�ϵͳ");
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
			DatabaseOp.close();
		}
	}
	
		
}
