package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

public class GuiModel implements ActionListener
{
	public JLabel label, nameLabel, genderLabel, dobLabel, motherLabel, fatherLabel;  //first frame labels
	public JButton searchButton, button, addButton; // first frame buttons
	public JTextField textfield; //first frame text field
	public JTextArea output, nameOutput, genderOutput, dobOutput, motherOutput, fatherOutput, empty; //first frame text area
	public JTextField nameField, genderField, dobField, motherField, fatherField;
	public JLabel pNameLabel, pGenderLabel, pDoBLabel, pMotherLabel, pFatherLabel;
	private JPanel panel, inputpanel;
	private JFrame frame;

	public Reader reader;
	
	public GuiModel()
	{
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setTitle("Family Tree");
		label = new JLabel("Search for a Person");
		frame.add(label);
		
		textfield = new JTextField(20);
		
		frame.add(textfield);
		
		searchButton = new JButton();
		searchButton.setText("Search..");
		frame.add(searchButton);
		
		//Normal output
		output = new JTextArea(1,25);
		output.setEditable(false);
		output.setLineWrap(true);
		output.setWrapStyleWord(true);
		frame.add(output);
		
		//Name output
		nameLabel = new JLabel("Name");
		frame.add(nameLabel);
		nameOutput = new JTextArea(1,25);
		nameOutput.setEditable(false);
		nameOutput.setLineWrap(true);
		nameOutput.setWrapStyleWord(true);
		frame.add(nameOutput);
		
		//Gender output
		genderLabel = new JLabel("Gender");
		frame.add(genderLabel);
		genderOutput = new JTextArea(1,25);
		genderOutput.setEditable(false);
		genderOutput.setLineWrap(true);
		genderOutput.setWrapStyleWord(true);
		frame.add(genderOutput);
		
		//DOB output
		dobLabel = new JLabel("DOB");
		frame.add(dobLabel);
		dobOutput = new JTextArea(1,25);
		dobOutput.setEditable(false);
		dobOutput.setLineWrap(true);
		dobOutput.setWrapStyleWord(true);
		frame.add(dobOutput);
		
		//Mother output
		motherLabel = new JLabel("Mother");
		frame.add(motherLabel);
		motherOutput = new JTextArea(1,25);
		motherOutput.setEditable(false);
		motherOutput.setLineWrap(true);
		motherOutput.setWrapStyleWord(true);
		frame.add(motherOutput);
		
		//Father output
		fatherLabel = new JLabel("Father");
		frame.add(fatherLabel);
		fatherOutput = new JTextArea(1,25);
		fatherOutput.setEditable(false);
		fatherOutput.setLineWrap(true);
		fatherOutput.setWrapStyleWord(true);
		frame.add(fatherOutput);
		
		frame.setSize(300,500);
		button = new JButton("Add a New Person");
		frame.add(button);
		frame.setResizable(false);
		//pack(); //fit the gui window
		
        try 
        {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }

        
	}
	
	public void createFrame()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	//reader = new Reader();
                JFrame frame = new JFrame("Add a Person");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try 
                {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                   e.printStackTrace();
                }
                panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                
                pNameLabel = new JLabel("Name");
                panel.add(pNameLabel);
                nameField = new JTextField(15);
                panel.add(nameField);
                
                pGenderLabel = new JLabel("Gender (M/F)");
                panel.add(pGenderLabel);
                genderField = new JTextField(1);
                panel.add(genderField);
                
                pDoBLabel = new JLabel("DoB (Year)");
                panel.add(pDoBLabel);
                dobField = new JTextField(20);
                panel.add(dobField);
                
                pMotherLabel = new JLabel("Mother");
                panel.add(pMotherLabel);
                motherField = new JTextField(20);
                panel.add(motherField);
                
                pFatherLabel = new JLabel("Father");
                panel.add(pFatherLabel);
                fatherField = new JTextField(20);
                panel.add(fatherField);
                
                addButton = new JButton("Add Person");
        		
                inputpanel.add(addButton);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
                //input.requestFocus();
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public JButton getSearchButton()
	{
		return searchButton;
	}
	
	public JFrame getFrame()
	{
		return frame;
	}
	
	
	
}
