package MyThread;

import javax.swing.JProgressBar;

public class ProgressBarThread extends Thread
{
	private JProgressBar ProgressBar = null;
	
	public ProgressBarThread(JProgressBar ProgressBar)
	{
		this.ProgressBar = ProgressBar;
	}
	
	public void run()
	{
		while(true)
		{
			ProgressBar.setIndeterminate(true);
		}
	}
}
