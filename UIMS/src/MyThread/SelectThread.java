package MyThread;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import Course.MainCourse;
import Uims.MainUims;

public class SelectThread extends Thread
{
	private JButton Button = null;
	private JProgressBar ProgressBar = null;
	private MainUims mainUims = null;
	private String[] IDArray = new String[3];
	
	public SelectThread(MainUims mainUims, JButton Button, JProgressBar ProgressBar, String[] IDArray)
	{
		this.mainUims = mainUims;
		this.Button = Button;
		this.ProgressBar = ProgressBar;
		this.IDArray = IDArray;
	}
	
	public void run()
	{
		try
		{
			Threads t1 = new Threads(mainUims, new MainCourse(IDArray[0]).getCourseID());
			Threads t2 = new Threads(mainUims, new MainCourse(IDArray[1]).getCourseID());
			Threads t3 = new Threads(mainUims, new MainCourse(IDArray[2]).getCourseID());
			t1.start(); t2.start(); t3.start();
			t1.join(); t2.join(); t3.join();

			Button.setText("Start");
			Button.setEnabled(true);
			ProgressBar.setIndeterminate(false);
			JOptionPane.showMessageDialog(null, "Successful !");
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
