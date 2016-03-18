package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Uims.Personal;
import Uims.MainUims;

interface Param
{
	//final int Frame_Width = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
	//final int Frame_Height = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
	final int Frame_Width = 500;
	final int Frame_Height = 309;
	
	final String Title = "Jlu UIMS Select Course";
	
	final String Font_Name = "Times Roman";
	final int Font_Number = 25;
}
public class FirstFrame implements Param
{
	private JFrame Frame = null;
	private JTextField TextField = null;
	private JPasswordField PasswordField = null;
	private Personal personal = null;
	private MainUims mainUims = null;
	
	public FirstFrame()
	{
		Frame = new JFrame();
	}
	
	public FirstFrame(String Title)
	{
		Frame = new JFrame(Title);
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
		JLabel Label = new JLabel(Title, JLabel.CENTER);
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
		
		TextField = new JTextField(15);
		PasswordField = new JPasswordField(15);
		
		JLabel NameLabel = new JLabel("UserName: ");
		JLabel PasswordLabel = new JLabel("Password: ");
		
		gridBagConstraints.insets = new Insets(10, 5, 10, 0);
		gridBagConstraints.gridx = 2; gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = 1; gridBagConstraints.gridheight = 1;
		gridBagLayout.setConstraints(NameLabel, gridBagConstraints);
		Panel.add(NameLabel);
		
		gridBagConstraints.gridy ++;
		gridBagLayout.setConstraints(PasswordLabel, gridBagConstraints);
		Panel.add(PasswordLabel);
		
		gridBagConstraints.gridx = 3; gridBagConstraints.gridy = 2;
		gridBagConstraints.gridwidth = GridBagConstraints.HORIZONTAL; gridBagConstraints.gridheight = 1;
		gridBagLayout.setConstraints(TextField, gridBagConstraints);
		Panel.add(TextField);
		
		gridBagConstraints.gridy ++;
		gridBagLayout.setConstraints(PasswordField, gridBagConstraints);
		Panel.add(PasswordField);
		
		Frame.add(Panel, BorderLayout.CENTER);
	}
	
	private void buildButton()
	{
		JButton Button = new JButton("login");
		Button.setBackground( new Color(244, 244, 244));
		Button.addActionListener( new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				personal = new Personal(getUserName(), getPassword());
				mainUims = new MainUims(personal);
				if(mainUims.isLogin())
				{
					new SecondFrame(mainUims).showFrame();
					Frame.dispose();
				}
				else
					JOptionPane.showMessageDialog(Frame, "Something is Error !", "Tips", JOptionPane.ERROR_MESSAGE);
			}
		});
		JPanel Panel = new JPanel( new FlowLayout( FlowLayout.CENTER));
		Panel.add(Button);
		Frame.add(Panel, BorderLayout.SOUTH);
	}
	
	public String getUserName()
	{
		return TextField.getText().trim();
	}
	
	public String getPassword()
	{
		return PasswordField.getText().trim();
	}
	
	public static void main(String[] args)
	{
		FirstFrame F = new FirstFrame();
		F.showFrame();
	}
}
