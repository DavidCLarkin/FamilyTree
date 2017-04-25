/**
 * Driver class with main method
 * @author David Larkin, 20070186
 * @version 1.0
 */

package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Driver 
{
	//public Scanner scan;
	public static GuiModel model;
	private Reader reader;
	private ArrayList<Person> finalChildren = new ArrayList<Person>();
	private ArrayList<Person> finalAncestors = new ArrayList<Person>();
	
	public static void main(String[] args)
	{
		Driver app = new Driver();
	}
	
	public Driver()
	{
		reader = new Reader();
		try 
		{
			reader.readTextFile("large-database.txt");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		setMotherAndFather(reader.getPeople());
		setChildren(reader.getPeople());
		setSiblings(reader.getPeople());
		
		setUpGUI();

		
	}
	
	public void setUpGUI()
	{
		model = new GuiModel();
		model.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		model.getFrame().setVisible(true);
		model.getSearchButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)  //If button pressed
			{
				displayPerson();
			}
		});
		
		//Add a listener that only runs when createFrame() is called
		model.button.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
		        model.createFrame(); //create the new window
		        EventQueue.invokeLater(new Runnable() //invoke later
		        {
					@Override
					public void run() 
					{
			            model.addButton.addActionListener(new ActionListener()
			            {
			    			@Override
			    			public void actionPerformed(ActionEvent e) 
			    			{
			    				addPerson();
			    				System.out.println(reader.getPeople().size());
			    			}
			            });
					}
		        });
		    }
		});
		
		model.ancestorButton.addActionListener(new ActionListener()
		{
			@Override
		    public void actionPerformed(ActionEvent e)
		    {
		        model.createAncestorFrame(); //create the new window
		        EventQueue.invokeLater(new Runnable() //invoke later
		        {
					@Override
					public void run() 
					{
			            model.searchButtonTwo.addActionListener(new ActionListener()
			            {
			    			@Override
			    			public void actionPerformed(ActionEvent e) 
			    			{
			    				displayPersonAncDes();
			    			}
			            });
					}
		        });
		    }
		});
	}
	
	private void displayPersonAncDes()
	{
		String name = null;
		StringBuilder descendants = new StringBuilder();
		StringBuilder ancestors = new StringBuilder();
		
		try
		{
			name = capitaliseWord(model.textfieldName.getText());
			
		}
		catch(Exception exc)
		{
			System.err.println("Invalid, please enter a name");
		}
		
		if(name == null || name.length() < 1 || !reader.getPeople().containsKey(name))
		{
			model.createErrorPane("Person does not exist, or found empty field", "No Person");
		}
		else
		{
			//Add all children to list
			allChildren(reader.getPeople().get(name));
			//Sorts by date
			Collections.sort(finalChildren);
		
			for(Person child : finalChildren)
			{
				descendants.append(child.getName()+ "("+child.getDateOfBirth()+")(" +child.getGender() +")" + ", ");
			}
		
			model.desOutput.setText(descendants.toString());
			finalChildren.clear(); //clear the list for next time
		
			//Add all ancestors to list
			allAncestors(reader.getPeople().get(name));
			//Sort by date
			Collections.sort(finalAncestors);
			
			for(Person anc : finalAncestors)
			{
				ancestors.append(anc.getName()+ "(" + anc.getDateOfBirth()+")("+ anc.getGender() +")" + ", ");
			}
		
			model.ancOutput.setText(ancestors.toString());
			finalAncestors.clear();
		}
		
	}
	
	/**
	 * Recursive algorithm to get all children a person has
	 * and adding them to a list
	 * @param person
	 * @return
	 */
	public ArrayList<Person> allChildren(Person person)
	{
	   ArrayList<Person> allChildren = new ArrayList<Person>();
	   for(Person child : person.getChildren()) //for each person's children
	   {
	       allChildren.addAll(allChildren(child)); //add all their children 
	       finalChildren.add(child);
	   }
	   return allChildren;
	}
	
	/**
	 * Method to add all the ancestors a person has
	 * to a list
	 * @param person
	 * @return
	 */
	public ArrayList<Person> allAncestors(Person person)
	{
		ArrayList<Person> ancestors = new ArrayList<Person>();
		if(person.hasMother()) //only if person has mother
		{
			ancestors.addAll(allAncestors(person.getpMother())); //addAll mothers gotten from person.getpMother()
			finalAncestors.add(person.getpMother()); //Add to global list as the function enters this if statement
		}
		if(person.hasFather())
		{
			ancestors.addAll(allAncestors(person.getpFather()));
			finalAncestors.add(person.getpFather());
		}
		return ancestors;
	}
	/**
	 * Method to add a person to the map
	 * and handle the exceptions
	 */
	private void addPerson()
	{
		String name = null, mother = null, father = null;
		char gender = 0;
		int dob = -1;
		
		//Get the name
		try
		{
			name = capitaliseWord(model.nameField.getText());
		}
		catch(Exception exc)
		{
			System.err.println("Invalid, please enter a name");
		}
		
		//Get the gender
		try
		{
			gender = capitaliseWord(model.genderField.getText()).charAt(0);
			if(gender == 'M' || gender == 'm' || gender== 'F' || gender == 'f')
			{
				gender = model.genderField.getText().charAt(0);
			}
			else
			{
				System.err.println("Invalid, enter correct gender");
			}
		}
		catch(Exception exc)
		{
			System.err.println("Invalid, gender is wrong, enter (M/F)");
		}
	
		//Get the Date of Birth
		try
		{
			dob = Integer.parseInt(model.dobField.getText());
		}
		catch(Exception exc)
		{
			System.err.println("Invalid, enter a Year for Date of Birth, e.g, 2017");
		}
		
		//Get the Mother
		try
		{
			mother = capitaliseWord(model.motherField.getText());
		}
		catch(Exception exc)
		{
			System.err.println("Invalid, enter a valid mother's name");
		}
		
		//Get the father
		try
		{
			father = capitaliseWord(model.fatherField.getText());
		}
		catch(Exception exc)
		{
			System.err.println("Invalid, enter a valid father's name");
		}
		
		//Add the Person to the map
		if(name != null && gender != 0 && dob != -1 && mother != null && father != null)
		{
			Person person = new Person(name, gender, dob, mother, father);
			reader.getPeople().put(person.getName(), person);
			setMotherAndFather(reader.getPeople());
			setChildren(reader.getPeople());
			setSiblings(reader.getPeople());
			System.out.println("Person has been added to the Map");
		}
		else
		{
			model.createErrorPane("Please check your details are properly inputted, or not empty", "Incorrect Details");
		}
	}
	
	/**
	 * Method to display a person based on input
	 */
	private void displayPerson()
	{
		if(model.textfield.getText().length() < 1 || model.textfield.getText() == null)
			return;
		String input = capitaliseWord(model.textfield.getText()); //capitalise the first letter
		try
		{
			if(input.equals(reader.getPeople().get(input).getName()))
			{
				model.output.setText(reader.getPeople().get(input).toString());
				model.nameOutput.setText(reader.getPeople().get(input).getName());
				model.genderOutput.setText(Character.toString(reader.getPeople().get(input).getGender()));
				model.dobOutput.setText(Integer.toString(reader.getPeople().get(input).getDateOfBirth()));
				model.motherOutput.setText(reader.getPeople().get(input).getMother());
				model.fatherOutput.setText(reader.getPeople().get(input).getFather());
			}
		}
		catch(Exception ex)
		{
			model.createWrongPane("Person does not exist!", "Doesn't Exist"); //create error pane if person doesn't exist
		}
	}
	
	/**
	 * Helper method to capitalise first letter of a word, used mainly for Name field of Person objects
	 * @param word
	 * @return
	 */
	private String capitaliseWord(String word)
	{
		return new String (word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
	}
	
	
	/**
	 * Set up the children to the parents, based on the list of people
	 * @param people
	 */
	public void setChildren(HashMap<String, Person> people)
	{
		Person[] persons = people.values().toArray(new Person[people.size()]); //make array of people objects from the map
		for (int i = 0; i < persons.length; i++) 
		{
			if(persons[i].hasMother())
			{
				persons[i].getpMother().addChildren(persons[i]); //add the child to the person's mother object
			}
			if(persons[i].hasFather())
			{
				persons[i].getpFather().addChildren(persons[i]); //add the child to the person's father object
			}
		}
	}
	
	
	/**
	 * Set up the mother and fathers for a person object
	 * @param people
	 */
	public void setMotherAndFather(HashMap<String, Person> people)
	{	
		Person[] persons = people.values().toArray(new Person[people.size()]);
		for (int i = 0; i < persons.length; i++) 
		{
			for (int j = 0; j< persons.length; j++) 
			{
				if (persons[i].getMother().equalsIgnoreCase(persons[j].getName())) //if someones mother is equal to the name of another object
				{
					persons[i].setpMother(persons[j]);
				}
				if (persons[i].getFather().equalsIgnoreCase(persons[j].getName())) //if someones father is equal to the name of another object
				{
					persons[i].setpFather(persons[j]);
				}
		  	}
		}
	}
	
	/**
	 * Set up the siblings of a person
	 * @param people
	 */
	public void setSiblings(HashMap<String, Person> people)
	{
		Person[] persons = people.values().toArray(new Person[people.size()]);
		for (int i = 0; i < persons.length; i++) 
		{
			for (int j = 0; j< persons.length; j++) 
			{
				if(persons[i].hasMother() && persons[j].hasMother()) //if both have a mother
				{
					if(persons[i].getMother().equals(persons[j].getMother())) //if both mothers are equal
						if(!persons[i].getSiblings().contains(persons[j])) //if does not exists
							persons[i].addSibling(persons[j]); //add the person	
				}
				
				if(persons[i].hasFather() && persons[j].hasFather()) //if both have a mother
				{
					if(persons[i].getFather().equals(persons[j].getFather())) //if both mothers are equal
						if(!persons[i].getSiblings().contains(persons[j])) //if does not exists
							persons[i].addSibling(persons[j]);
				}
			}
		}
	}
	
	
}
