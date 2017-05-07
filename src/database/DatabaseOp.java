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
					System.out.println("数据库连接成功");
				}
			} catch (Exception ee) {
				ee.printStackTrace();
				System.out.println("数据库连接失败");
			}
		}
	
		//从数据库中得到ResultSet，一个存储查询结果的对象，同时也可以修改
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
		//更新数据库
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
	 * 登录的方法
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
	
	
	//读取用户的部分信息，用于删除用户
	public static List getUserInfo()
	{
		String str="select userName,major,userNum,BicycleNo from UserInfo";
		List list=findForList(str);
		return list;
	}
	
	//读取管理者的信息
	public static List getCzyInfos()
	{
		String str="select logName,userName,pass,Quan from UserList";
		List list=findForList(str);
		return list;
	}
	
//	
//	
	//读取所欲车辆信息
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
	
	//读取用户的所有信息
	public static List getUserInfos()
	{
		String str="select id,userName from UserInfo";
		List list=findForList(str);
		return list;
	}
	
	
	//读取用户的所有信息
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
	
	//读取用户的所有信息，根据登录的名称查询
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
	
	//读取用户、管理者信息（包括，登录名、真实姓名、密码、权限）
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
	
	//修改管理员的信息
	public static int updateUser(UserList userList)
	{
		return update("update UserList set logName='" + userList.getlogName()
				+ "',username='" + userList.getuserName() + "',pass='" + userList.getPass()
				+ "',Quan='" + userList.getQuan() + "' where logName='"
				+ userList.getlogName() + "'");
	}
	
	// 获取用户对象的方法*
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
	
	
	
	
	//进行指定查询
	public static ResultSet query(String querySql)
	{
		ResultSet rs=findForResult(querySql);
		return rs;
	}
	
	//删除信息
	public static int delete(String sql)
	{
		return update(sql);
	}
	
	//添加用户信息，在添加用户信息时，需要将信息同时添加到管理员信息的表里
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
	
	//添加管理员
	public static boolean addCzy(UserList userList)
	{
		if(userList==null)
			return false;
		return insert("insert UserList values('"+userList.getlogName()+"','"+
			userList.getuserName()+"','"+userList.getPass()+"','"+
				userList.getQuan()+"')");
	}
	
	//向数据库插入信息
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
