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
		//更新数据库
		public static int update(String sql){
			int result=0;
			try{
				Statement st=conn.createStatement();
				result=st.executeUpdate(sql);//返回的是插入数据的条数
				if(result>=1)
					System.out.println("数据更新成功");
				else
					return 0;
			}catch(SQLException e){
				e.printStackTrace();
			}
			return result;
		}
		
		//关闭数据库
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
	 * 登录的方法
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
	
	//读取管理者信息
	public static List getCzyInfos(){
		String str="select * from UserList";
		List list=findForList(str);
		return list;
	}
		
	//读取车辆所有信息
	public static List getBicycleInfos(){
		String str="select * from BicycleList";
		List list=findForList(str);
		return list;
	}
	
	//返回自行车的所有信息，返回值为BicycleList
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
	
	//读取当前用户的所有信息
	public static List getUserInfos()
	{
		String str="select * from UserInfo";
		List list=findForList(str);
		return list;
	}
	
	//读取用户的部分信息，用于删除用户
	public static List getUserInfo(){
		String str="select userName,major,userNum,BicycleNo from UserInfo";
		List list=findForList(str);
		return list;
	}
	
	//读取用户的所有信息
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
	

	//修改信息
	public static int updateUser(UserList userList)
	{
		return update("update UserList set Quan='" + userList.getQuan() + "' where logName='"
				+ userList.getlogName() + "'");
	}
	
	//根据用户选择，获取用户数据
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
	
	//删除信息
	public static int delete(String sql)
	{
		return update(sql);
	}
	
	//添加用户信息，在添加用户信息时，需要将信息同时添加到管理员信息的表里
	public static int addUser(UserInfo userInfo)
	{
		if(userInfo==null){
			System.out.println("错误");
			return 0;
		}
		return (update("insert UserInfo values('"+
				userInfo.getBicycleNo()+"','"+userInfo.getUserName()+"','"+userInfo.getLogName()+
				"','"+userInfo.getUsex()+"','"+userInfo.getdept()+"','"+userInfo.getmajor()+
				"','"+userInfo.getUserNum()+"','"+userInfo.getPass()+"','"+userInfo.getXueBu()+
				"','"+userInfo.getBlocks()+"','"+userInfo.getdomiNo()+"','"+userInfo.getQuan()+"')")) ;
	}
	//添加管理员
	public static int addCzy(UserList userList)
	{
		if(userList==null)
			return 0;
		return update("insert UserList values('"+userList.getlogName()+"','"+
			userList.getuserName()+"','"+userList.getPass()+"','"+
				userList.getQuan()+"')");
	}
	

	
	
}
