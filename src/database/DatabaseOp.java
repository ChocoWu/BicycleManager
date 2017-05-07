package database;


import java.util.List;
import java.util.Set;

import internalFrame.management.Item;
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
		public static List findForList(String sql)
		{
			List <List> list=new ArrayList<List>();
			ResultSet rs=findForResult(sql);
			try
			{
				int count=metaData.getColumnCount();
				List<String> row=new ArrayList<String>();
				for(int i=0;i<count;i++)
				{
					String str=rs.getString(i);
					if(str!=null && !str.isEmpty())
					{
						str=str.trim();
					}
					row.add(str);
				}
				list.add(row);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return list;
		}
		//�������ݿ�
		public static int update(String sql)
		{
			int result=0;
			try
			{
				Statement st=conn.createStatement();
				result=st.executeUpdate(sql);
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			return result;
		}
		private static void close(){
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
	public static UserList Login(String logName,String passWord)
	{
		int i=0;
		UserList userList=new UserList();
		String sql="select * from UserList where logName='"+logName+"' and pass='"+passWord+"'";
		ResultSet rs=DatabaseOp.findForResult(sql);
		try{
			while(rs.next()){
				String names=rs.getString(1);
				userList.setlogName(rs.getString("logName"));
				userList.setuserName(rs.getString("userName"));
				userList.setQuan(rs.getString("Quan"));
				userList.setPass(rs.getString("pass"));
				if(names!=null)
					i=1;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DatabaseOp.close();
		return userList;
	}
	
	
	//��ȡ�û��Ĳ�����Ϣ������ɾ���û�
	public static List getUserInfo()
	{
		String str="select userName,major,userNum,BicycleNo from UserInfo";
		List list=findForList(str);
		return list;
	}
	
	//��ȡ�����ߵ���Ϣ
	public static List getCzyInfos()
	{
		String str="select logName,userName,pass,Quan from UserList";
		List list=findForList(str);
		return list;
	}
	
//	
//	
	//��ȡ����������Ϣ
	public static List getBicycleInfos()
	{
		String str="select BicycleNo,ZhangTai from BicycleList";
		List list=findForList(str);
		return list;
	}
	
	public static BicycleList getBicycleInfo(Item item)
	{
		String where="BicycleNo='"+item.getName()+"'";
		if(item.getId()!=null)
			where="id='"+item.getId()+"'";
		BicycleList bicycleList=new BicycleList();
		ResultSet set=findForResult("select * from BicycleList where="+where);
		try{
			if(set.next())
			{
				bicycleList.setBicycleNo(set.getString("BicycleNo").trim());
				bicycleList.setZhuangTai(set.getString("ZhuangTai").trim());
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return bicycleList;
	}
	
	//��ȡ�û���������Ϣ
	public static List getUserInfos()
	{
		String str="select id,userName from UserInfo";
		List list=findForList(str);
		return list;
	}
	
	
	//��ȡ�û���������Ϣ
	public static UserInfo getUserInfo(Item item){
		String where="userName='"+item.getName()+"'";
		if(item.getId()!=null)
			where="id='"+item.getId()+"'";
		UserInfo userInfo=new UserInfo();
		ResultSet set=findForResult("select * from UserInfo where"+where);
		try{
			if(set.next()){
				userInfo.setId(set.getString("id").trim());
				userInfo.setUserName(set.getString("userName").trim());
				userInfo.setUsex(set.getString("userSex").trim());
				userInfo.setdept(set.getString("dept").trim());
				userInfo.setmajor(set.getString("major").trim());
				userInfo.setUserNum(set.getString("userNum").trim());
				userInfo.setXueBu(set.getString("XueBu").trim());
				userInfo.setBlocks(set.getString("Blocks").trim());
				userInfo.setdomiNo(set.getString("domiNo").trim());
				userInfo.setBicycleNo(set.getString("BicycleNo").trim());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userInfo;
	}
	
	//��ȡ�û���������Ϣ�����ݵ�¼�����Ʋ�ѯ
	public static UserInfo getInfo(String logName){
		UserInfo userInfo=new UserInfo();
		ResultSet rs=findForResult("select *from where=logName='"+logName+"'");
		try{
			if(rs.next()){
				userInfo.setLogName(rs.getString(logName));
				if(userInfo.getLogName().equals(logName)){
					userInfo.setId(rs.getString("id"));
					userInfo.setBicycleNo(rs.getString("bicycleNo"));
					userInfo.setUserName(rs.getString("userName"));
					userInfo.setLogName(rs.getString("logName"));
					userInfo.setUsex(rs.getString("userSex"));
					userInfo.setdept(rs.getString("dept"));
					userInfo.setmajor(rs.getString("major"));
					userInfo.setUserNum("userNum");
					userInfo.setPassword("pass");
					userInfo.setXueBu(rs.getString("XueBu"));
					userInfo.setBlocks(rs.getString("Blocks"));
					userInfo.setdomiNo(rs.getString("domiNo"));
				}	
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userInfo;
	}
	
	//��ȡ�û�����������Ϣ����������¼������ʵ���������롢Ȩ�ޣ�
	public static UserList getUser(String logName,String pass){
		UserList userList=new UserList();
		ResultSet rs=findForResult("select * from user where logName='"+logName+"'");
		try{
			if(rs.next()){
				userList.setlogName(logName);
				userList.setPass(rs.getString(pass));
				if(userList.getPass().equals(pass)){
					userList.setlogName(rs.getString("logName"));
					userList.setuserName(rs.getString("userName"));
					userList.setPass(rs.getString("pass"));
					userList.setQuan(rs.getString("Quan"));
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return userList;
	}
	
	//�޸Ĺ���Ա����Ϣ
	public static int updateUser(UserList userList)
	{
		return update("update UserList set logName='" + userList.getlogName()
				+ "',username='" + userList.getuserName() + "',pass='" + userList.getPass()
				+ "',Quan='" + userList.getQuan() + "' where logName='"
				+ userList.getlogName() + "'");
	}
	
	// ��ȡ�û�����ķ���*
		public static UserList getUser(Item item) {
			String where = "username='" + item.getName() + "'";
			if (item.getId() != null)
				where = "name='" + item.getId() + "'";
			ResultSet rs = findForResult("select * from tb_userlist where "
					+ where);
			UserList user = new UserList();
			try {
				if (rs.next()) {
					user.setlogName(rs.getString("name").trim());
					user.setuserName(rs.getString("username").trim());
					user.setPass(rs.getString("pass").trim());
					user.setQuan(rs.getString("quan").trim());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return user;
		}
	
	
	
	
	//����ָ����ѯ
	public static ResultSet query(String querySql)
	{
		ResultSet rs=findForResult(querySql);
		return rs;
	}
	
	//ɾ����Ϣ
	public static int delete(String sql)
	{
		return update(sql);
	}
	
	//����û���Ϣ��������û���Ϣʱ����Ҫ����Ϣͬʱ��ӵ�����Ա��Ϣ�ı���
	public static boolean addUser(UserInfo userInfo)
	{
		if(userInfo==null)
			return false;
		return (insert("insert UserInfo values('"+userInfo.getId()+"','"+
				userInfo.getUserName()+"',"+userInfo.getUsex()+"','"+
				userInfo.getdept()+"','"+userInfo.getmajor()+"','"+
				userInfo.getUserNum()+"','"+userInfo.getXueBu()+"','"+
				userInfo.getBlocks()+"','"+userInfo.getdomiNo()+"','"+
				userInfo.getBicycleNo()+"'"+userInfo.getLogName()+"','"+userInfo.getPass()+"')")) && 
				(insert("insert UserList values('"+userInfo.getLogName()+"','"+userInfo.getUserName()+"','"+userInfo.getPass()+
						"','0')"));
	}
	
	//��ӹ���Ա
	public static boolean addCzy(UserList userList)
	{
		if(userList==null)
			return false;
		return insert("insert UserList values('"+userList.getlogName()+"','"+
			userList.getuserName()+"','"+userList.getPass()+"','"+
				userList.getQuan()+"')");
	}
	
	//�����ݿ������Ϣ
	public static boolean insert(String sql)
	{
		boolean result=false;
		try
		{
			Statement st=conn.createStatement();
			result=st.execute(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	
}
