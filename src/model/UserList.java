package model;

public class UserList {
//	private int id;
	private String loginName;//用户登录姓名
	private String userName;//用户真实的姓名
	private String pass;//用户登录密码
	private String quan;//用户的权限
	
	public String getlogName()
	{
		return loginName;
	}
	public void setlogName(String logname)
	{
		this.loginName=logname;
	}
	public String getuserName()
	{
		return userName;
	}
	public void setuserName(String username)
	{
		this.userName=username;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass=pass;
	}
	public String getQuan()
	{
		return quan;
	}
	public void setQuan(String Quan)
	{
		this.quan=Quan;
	}
}
