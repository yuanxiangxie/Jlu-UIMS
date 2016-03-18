package Uims;

import Encrypt.MD5;

public class Personal 
{
	private static String UserName = null;
	private static String Password = null;
	private MD5 M;
	
	public Personal(final String UserName, final String Password)
	{
		this.UserName = UserName;
		this.Password = Password;
		M = new MD5();
	}
	
	public String getUserName()
	{
		return UserName;
	}
	
	public String getPassword()
	{
		return M.md5("UIMS" + UserName + Password);
	}
}
