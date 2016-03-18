package Course;

/*
 * 50743511 数据挖掘导论
 * 51374430 分布式软件开发
 * 50978373 云计算技术
 */
public class MainCourse 
{
	private String CourseID = null;
	
	public MainCourse(String CourseID)
	{
		if(CourseID.equals(""))
			this.CourseID = "";
		else
			this.CourseID = "{\"lsltId\":\"" + CourseID + "\",\"opType\":\"Y\"}";
	}
	
	public String getCourseID()
	{
		return CourseID;
	}
}
