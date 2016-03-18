package Uims;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import Course.MainCourse;
import MyThread.Threads;

interface Param
{
	final String login_url = "http://uims.jlu.edu.cn/ntms/j_spring_security_check";
	final String info_url = "http://uims.jlu.edu.cn/ntms/action/getCurrentUserInfo.do";
	final String select_url = "http://uims.jlu.edu.cn/ntms/selectlesson/select-lesson.do";
}

public class MainUims implements Param
{
	private Cookies cookie = null;
	
	public MainUims(Personal personal)
	{
		PrintWriter printWriter = null;
		String Params = "j_username=" + personal.getUserName() + "&j_password=" + personal.getPassword();
		try
		{
			URL url = new URL(login_url);
			HttpURLConnection http_login_url = (HttpURLConnection) url.openConnection();
			http_login_url.setDoOutput(true);
			http_login_url.setDoInput(true);
			http_login_url.setRequestProperty("Connection", "keep-alive");
			http_login_url.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http_login_url.setRequestProperty("Content-Length", Integer.toString(Params.length()));
			http_login_url.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
			http_login_url.setUseCaches(false);
			http_login_url.setInstanceFollowRedirects(false);
			
			printWriter = new PrintWriter( new OutputStreamWriter(http_login_url.getOutputStream(), "utf-8"));
			printWriter.print(Params);
			printWriter.flush();
			printWriter.close();
			
			String Cookies = http_login_url.getHeaderField("Set-Cookie");
			cookie = new Cookies();
			cookie.setCookie(Cookies);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				printWriter.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean isLogin()
	{
		BufferedReader bufferedReader = null;
		boolean flag = false;
		try
		{
			URL url = new URL(info_url);
			HttpURLConnection http_info_url = (HttpURLConnection) url.openConnection();
			http_info_url.setDoOutput(true);
			http_info_url.setDoInput(true);
			http_info_url.setRequestProperty("Connection", "keep-alive");
			http_info_url.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http_info_url.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
			http_info_url.setInstanceFollowRedirects(true);
			if(cookie.getCookie() != null)
				http_info_url.setRequestProperty("Cookie", cookie.getCookie());
			http_info_url.connect();
			
			bufferedReader = new BufferedReader( new InputStreamReader(http_info_url.getInputStream(), "utf-8"));
			String lines = "";
			String buffer = "";
			while((lines = bufferedReader.readLine()) != null)
			{
				buffer += lines + "\n";
			}
			//System.out.println(buffer);
			if(buffer.indexOf("userId") >= 0)
				flag = true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				bufferedReader.close();
				return flag;
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return flag;
	}
	
	public boolean selectCourse(String CourseID)
	{
		boolean flag = false;
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try
		{
			URL url = new URL(select_url);
			HttpURLConnection http_select_url = (HttpURLConnection) url.openConnection();
			http_select_url.setDoOutput(true);
			http_select_url.setDoInput(true);
			http_select_url.setRequestProperty("Connection", "keep-alive");
			http_select_url.setRequestProperty("Content-Length", Integer.toString(CourseID.length()));
			http_select_url.setRequestProperty("Content-Type", "application/json");
			http_select_url.setInstanceFollowRedirects(false);
			http_select_url.setRequestProperty("Cookie", cookie.getCookie());
			
			printWriter = new PrintWriter(http_select_url.getOutputStream());
			printWriter.print(CourseID);
			printWriter.flush();
			printWriter.close();
			
			bufferedReader = new BufferedReader( new InputStreamReader( http_select_url.getInputStream(), "utf-8"));
			String lines = "";
			String buffer = "";
			while((lines = bufferedReader.readLine()) != null)
				buffer += lines;
			System.out.println(buffer);
			String[] stringArray = buffer.split(",");
			if(stringArray[4].substring(9, 10).equals("0"))
				flag = true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try
			{
				printWriter.close();
				bufferedReader.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return flag;
	}
}
