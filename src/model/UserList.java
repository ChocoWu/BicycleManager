package model;

public class UserList {
//	private int id;
	private String loginName;//�û���¼����
	private String userName;//�û���ʵ������
	private String pass;//�û���¼����
	private String quan;//�û���Ȩ��
	
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
