package model;

public class UserInfo {
	private String  id;
	private String UserName;//�û�����
	private String LogName;//�ǳ�
	private String Usex;//�Ա�
	private String dept;//ѧԺ
	private String major;//רҵ
	private String UserNum;//ѧ��
	private String XueBu;//ѧ��
	private String Blocks;//¥����
	private String domiNo;//�����
	private String BicycleNo;//���г����ƺ�
	private String pass;//����
	
	
	public UserInfo(){}
	
	public UserInfo(String id)
	{
		this.id=id;
	}
	
	public UserInfo(String id,String UserName,String LogName,String Usex,String dept,String major,String UserNum,String XueBu
			,String Blocks,String domiNo,String BicycleNo,String pass )
	{
		this.id=id;
		this.UserName=UserName;
		this.LogName=LogName;
		this.Usex=Usex;
		this.dept=dept;
		this.major=major;
		this.UserNum=UserNum;
		this.XueBu=XueBu;
		this.Blocks=Blocks;
		this.domiNo=domiNo;
		this.BicycleNo=BicycleNo;
		this.pass=pass;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id=id;
	}
	public String getUserName()
	{
		return UserName;
	}
	public void setUserName(String name)
	{
		this.UserName=name;
	}
	public String getLogName()
	{
		return LogName;
	}
	public void setLogName(String LogName)
	{
		this.LogName=LogName;
	}
	public String getUsex()
	{
		return Usex;
	}
	public void setUsex(String Usex)
	{
		this.Usex=Usex;
	}
	public String getdept()
	{
		return dept;
	}
	public void setdept(String dept)
	{
		this.dept=dept;
	}
	public String getmajor()
	{
		return major;
	}
	public void setmajor(String major)
	{
		this.major=major;
	}
	public String getUserNum()
	{
		return UserNum;
	}
	public void setUserNum(String UserNum)
	{
		this.UserNum=UserNum;
	}
	public String getXueBu()
	{
		return XueBu;
	}
	public void setXueBu(String XueBu)
	{
		this.XueBu=XueBu;
	}
	public String getBlocks()
	{
		return Blocks;
	}
	public void setBlocks(String Blocks)
	{
		this.Blocks=Blocks;
	}
	public String getdomiNo()
	{
		return domiNo;
	}
	public void setdomiNo(String domiNo)
	{
		this.domiNo=domiNo;
	}
	public String getBicycleNo()
	{
		return BicycleNo;
	}
	public void setBicycleNo(String BicycleNo)
	{
		this.BicycleNo=BicycleNo;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPassword(String pass)
	{
		this.pass=pass;
	}
	
}
