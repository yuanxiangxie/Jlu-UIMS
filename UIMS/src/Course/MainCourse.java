package Course;

/*
 * 50743511 �����ھ���
 * 51374430 �ֲ�ʽ�������
 * 50978373 �Ƽ��㼼��
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
