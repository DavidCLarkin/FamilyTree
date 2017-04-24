package main;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Reader 
{
	public HashMap<String, Person> people = new HashMap<String, Person>();
	private Scanner scan;
	
	public Reader()
	{
	}
	
	public void readTextFile(String url) throws Exception
	{
		scan = new Scanner(new File(url));
		String separator = " "; //separate by whitespace
		String temp;
		while (scan.hasNextLine()) //while scanner has another line
		{ 	
			temp = scan.nextLine();
			String[] lineSplits = temp.split(separator); //separate terms by space;
			if(lineSplits.length==5)
			{
				people.put(lineSplits[0],new Person(lineSplits[0],lineSplits[1].charAt(0), Integer.parseInt(lineSplits[2]), lineSplits[3], lineSplits[4])); //add objects to ArrayList
			}
			else
			{
				 throw new Exception("Invalid member length: "+lineSplits.length);
			}
		}
		scan.close();
	}
	
	public HashMap<String,Person> getPeople()
	{
		return people;
	}
}
