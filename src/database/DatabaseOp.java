package database;


import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;
import model.BicycleList;
import model.UserInfo;
import model.UserList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseOp   {
	
	    protected static String dbClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		protected static String dbUrl="jdbc:sqlserver://localhost:1433;databaseName=ManageBicycle";
		protected static String dbUser = "sa";
		protected static String dbPwd = "GAOKAO160nihao";
		protected static String second = null;
		public static Connection conn = null;
		private static ResultSetMetaData metaData;
		private DatabaseOp() {
			try {
				if (conn == null) {
					Class.forName(dbClassName).newInstance();
					conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
					System.out.println("���ݿ����ӳɹ�");
				}
			} catch (Exception ee) {
				ee.printStackTrace();
				System.out.println("���ݿ�����ʧ��");
			}
		}
	
		//�����ݿ��еõ�ResultSet��һ���洢��ѯ����Ķ���ͬʱҲ�����޸�
		public static ResultSet findForResult(String sql){
			try{
				if(conn==null)
					new DatabaseOp();
			   return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			}catch(SQLException e){
				e.printStackTrace();
				return null;
			}finally{}
		}

		@SuppressWarnings("rawtypes")
		public static List findForList(String sql){
			List <List> list=new ArrayList<List>();
			ResultSet rs=findForResult(sql);
			try{
				metaData=rs.getMetaData();
				int count=metaData.getColumnCount();
				while(rs.next()){
					List<String> row=new ArrayList<String>();
					for(int i=1;i<=count;i++){
						String str=rs.getString(i);
						if(str!=null && !str.isEmpty())
							str=str.trim();
						row.add(str);
					}
					list.add(row);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
		}
		//�������ݿ�
		public static int update(String sql){
			int result=0;
			try{
				Statement st=conn.createStatement();
				result=st.executeUpdate(sql);//���ص��ǲ������ݵ�����
				if(result>=1)
					System.out.println("���ݸ��³ɹ�");
				else
					return 0;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}
		
		//�ر����ݿ�
		public static void close(){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
	/*
	 * ��¼�ķ���
	 */
	public static UserList Login(String logName,String passWord){
		UserList userList=new UserList();
		String sql="select * from UserList where logName='"+logName+"' and pass='"+passWord+"'";
		ResultSet rs=DatabaseOp.findForResult(sql);
		try{
			while(rs.next()){
				userList.setlogName(rs.getString("logName"));
				userList.setuserName(rs.getString("userName"));
				userList.setQuan(rs.getString("Quan"));
				userList.setPass(rs.getString("pass"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
//		DatabaseOp.close();
		return userList;
	}
	
	//��ȡ��������Ϣ
	public static List getCzyInfos(){
		String str="select * from UserList";
		List list=findForList(str);
		return list;
	}
		
	//��ȡ����������Ϣ
	public static List getBicycleInfos(){
		String str="select * from BicycleList";
		List list=findForList(str);
		return list;
	}
	
	//�������г���������Ϣ������ֵΪBicycleList
	public static BicycleList getBicycleInfo(String s){
		String where="BicycleNo='"+s+"'";
		BicycleList bicycleList=new BicycleList();
		ResultSet set=findForResult("select * from BicycleList where "+where);
		try{
			if(set.next()){
				bicycleList.setBicycleNo(set.getString("BicycleNo").trim());
				bicycleList.setZhuangTai(set.getString("ZhuangTai").trim());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bicycleList;
	}
	
	//��ȡ��ǰ�û���������Ϣ
	public static List getUserInfos()
	{
		String str="select * from UserInfo";
		List list=findForList(str);
		return list;
	}
	
	//��ȡ�û��Ĳ�����Ϣ������ɾ���û�
	public static List getUserInfo(){
		String str="select userName,major,userNum,BicycleNo from UserInfo";
		List list=findForList(str);
		return list;
	}
	
	//��ȡ�û���������Ϣ
	public static UserInfo getUserInfo(String s){
		String where="logName='"+s+"'";
		UserInfo userInfo=new UserInfo();
		ResultSet set=findForResult("select * from UserInfo where "+where);
		try{
			if(set.next()){
				userInfo.setBicycleNo(set.getString("BicycleNo").trim());
				userInfo.setUserName(set.getString("userName").trim());
				userInfo.setLogName(set.getString("logName").trim());
				userInfo.setUsex(set.getString("userSex").trim());
				userInfo.setdept(set.getString("dept").trim());
				userInfo.setmajor(set.getString("major").trim());
				userInfo.setUserNum(set.getString("userNum").trim());
				userInfo.setXueBu(set.getString("XueBu").trim());
				userInfo.setBlocks(set.getString("Blocks").trim());
				userInfo.setdomiNo(set.getString("domiNo").trim());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userInfo;
	}
	

	//�޸���Ϣ
	public static int updateUser(UserList userList)
	{
		return update("update UserList set Quan='" + userList.getQuan() + "' where logName='"
				+ userList.getlogName() + "'");
	}
	
	//�����û�ѡ�񣬻�ȡ�û�����
	public static UserList getUser(String s){
		String where="userName='"+s+"'";
		ResultSet rs=findForResult("select * from UserList where "+where);
		UserList userList=new UserList();
		try{
			if(rs.next()){
				userList.setlogName(rs.getString("logName").trim());
				userList.setuserName(rs.getString("userName").trim());
				userList.setPass(rs.getString("pass").trim());
				userList.setQuan(rs.getString("Quan").trim());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userList;
	}
	
	//ɾ����Ϣ
	public static int delete(String sql)
	{
		return update(sql);
	}
	
	//����û���Ϣ��������û���Ϣʱ����Ҫ����Ϣͬʱ��ӵ�����Ա��Ϣ�ı���
	public static int addUser(UserInfo userInfo)
	{
		if(userInfo==null){
			System.out.println("����");
			return 0;
		}
		return (update("insert UserInfo values('"+
				userInfo.getBicycleNo()+"','"+userInfo.getUserName()+"','"+userInfo.getLogName()+
				"','"+userInfo.getUsex()+"','"+userInfo.getdept()+"','"+userInfo.getmajor()+
				"','"+userInfo.getUserNum()+"','"+userInfo.getPass()+"','"+userInfo.getXueBu()+
				"','"+userInfo.getBlocks()+"','"+userInfo.getdomiNo()+"','"+userInfo.getQuan()+"')")) ;
	}
	//��ӹ���Ա
	public static int addCzy(UserList userList)
	{
		if(userList==null)
			return 0;
		return update("insert UserList values('"+userList.getlogName()+"','"+
			userList.getuserName()+"','"+userList.getPass()+"','"+
				userList.getQuan()+"')");
	}
	

	
	
}
