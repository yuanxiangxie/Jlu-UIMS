package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import MyThread.SelectThread;
import Uims.MainUims;

interface Params
{
	//final int Frame_Width = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
	//final int Frame_Height = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
	final int Frame_Width = 500;
	final int Frame_Height = 309;
	
	final String Tips = "Please Input the ID of Course";
	final String Font_Name = "Times Roman";
	final int Font_Number = 20;
	
	final int TextFieldLength = 20;
}
public class SecondFrame implements Params 
{
	private JFrame Frame = null;
	private JTextField FirstTextField = null;
	private JTextField SecondTextField = null;
	private JTextField ThirdTextField = null;
	private JProgressBar ProgressBar = null;
	private MainUims mainUims = null;
	
	public SecondFrame()
	{
		Frame = new JFrame();
	}
	
	public SecondFrame(MainUims mainUims)
	{
		Frame = new JFrame();
		this.mainUims = mainUims;
	}
	
	public SecondFrame(MainUims mainUims, String Title)
	{
		Frame = new JFrame(Title);
		this.mainUims = mainUims;
	}
	
	public void showFrame()
	{
		buildLabel();
		buildPanel();
		buildButton();
		
		Frame.setSize(Frame_Width, Frame_Height);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setVisible(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void buildLabel()
	{
		Font font = new Font(Font_Name, Font.BOLD, Font_Number);
		JLabel Label = new JLabel(Tips, JLabel.CENTER);
		Label.setFont(font);
		JPanel Panel = new JPanel( new BorderLayout());
		Panel.add( new JLabel(" "), BorderLayout.NORTH);
		Panel.add(Label);
		Frame.add(Panel, BorderLayout.NORTH);
	}
	
	private void buildPanel()
	{
		JPanel Panel = new JPanel();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout gridBagLayout = new GridBagLayout();
		Panel.setLayout(gridBagLayout);
		
		FirstTextField = new JTextField(TextFieldLength);
		SecondTextField = new JTextField(TextFieldLength);
		ThirdTextField = new JTextField(TextFieldLength);
		
		JLabel FirstLabel = new JLabel("CourseID: ");
		JLabel SecondLabel = new JLabel("CourseID: ");
		JLabel ThirdLabel = new JLabel("CourseID: ");
		
		gridBagConstraints.insets = new Insets(10, 5, 10, 0);
		gridBagConstraints.gridx = 2; gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 1; gridBagConstraints.gridheight = 1;
		gridBagLayout.setConstraints(FirstLabel, gridBagConstraints);
		Panel.add(FirstLabel);
		
		gridBagConstraints.gridy ++;
		gridBagLayout.setConstraints(SecondLabel, gridBagConstraints);
		Panel.add(SecondLabel);
		
		gridBagConstraints.gridy ++;
		gridBagLayout.setConstraints(ThirdLabel, gridBagConstraints);
		Panel.add(ThirdLabel);
		
		gridBagConstraints.gridx = 3; gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; gridBagConstraints.gridheight = 1;
		gridBagLayout.setConstraints(FirstTextField, gridBagConstraints);
		Panel.add(FirstTextField);
		
		gridBagConstraints.gridy ++;
		gridBagLayout.setConstraints(SecondTextField, gridBagConstraints);
		Panel.add(SecondTextField);
		
		gridBagConstraints.gridy ++;
		gridBagLayout.setConstraints(ThirdTextField, gridBagConstraints);
		Panel.add(ThirdTextField);
		
		ProgressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		ProgressBar.setIndeterminate(false);
		
		gridBagConstraints.gridx = 2; gridBagConstraints.gridy ++;
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; gridBagConstraints.gridheight = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagLayout.setConstraints(ProgressBar, gridBagConstraints);
		Panel.add(ProgressBar);
		
		Frame.add(Panel, BorderLayout.CENTER);
	}
	
	private void buildButton()
	{
		JButton Button = new JButton("Begin");
		Button.setBackground( new Color(244, 244, 244));
		Button.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Button.setText("Waiting...");
				Button.setEnabled(false);
				ProgressBar.setIndeterminate(true);
				String[] IDArray = { getFirstCourse(), getSecondCourse(), getThirdCourse()};
				new SelectThread(mainUims, Button, ProgressBar, IDArray).start();
			}

		});
		
		JPanel Panel = new JPanel( new FlowLayout( FlowLayout.CENTER));
		Panel.add(Button);
		Frame.add(Panel, BorderLayout.SOUTH);
	
	}
	
	public String getFirstCourse()
	{
		return FirstTextField.getText().trim();
	}
	
	public String getSecondCourse()
	{
		return SecondTextField.getText().trim();
	}
	
	public String getThirdCourse()
	{
		return ThirdTextField.getText().trim();
	}
	
}
