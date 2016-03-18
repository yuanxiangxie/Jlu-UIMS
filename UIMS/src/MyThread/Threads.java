package MyThread;

import Uims.MainUims;

public class Threads  extends Thread
{
	private MainUims mainUims = null;
	private String CourseID = null;
	
	public Threads(MainUims mainUims, String CourseID)
	{
		this.mainUims = mainUims;
		this.CourseID = CourseID;
	}
	
	public void run()
	{
//		int i = 0;
		if(CourseID.equals(""))
			return;
		while(true)
		{
//			++i;
//			if(i > 10)
//				break;
			if(mainUims.selectCourse(CourseID))
				break;
			else 
				continue;
		}
	}
}
