package main;

import java.util.ArrayList;

public class Person
{
	private String name;
	private char gender;
	private int dateOfBirth;
	private String mother;
	private String father;
	private Person pMother;
	private Person pFather;
	private ArrayList<Person> children;
	private ArrayList<Person> siblings;
	
	public Person(String name, char gender, int dateOfBirth, String mother, String father)
	{
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mother = mother;
		this.father = father;
		children = new ArrayList<Person>();
		siblings = new ArrayList<Person>();
	}
	
	public Person(String name, char gender, int dateOfBirth, Person pMother, Person pFather)
	{
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.pMother = pMother;
		this.pFather = pFather;
		children = new ArrayList<Person>();
		siblings = new ArrayList<Person>();
	}

	public ArrayList<Person> getSiblings() 
	{
		return siblings;
	}

	public void setSiblings(ArrayList<Person> siblings) 
	{
		this.siblings = siblings;
	}

	public void addSibling(Person sibling)
	{
		siblings.add(sibling);
	}
	
	public void addChildren(Person child)
	{
		children.add(child);
	}
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public char getGender() 
	{
		return gender;
	}

	public void setGender(char gender) 
	{
		this.gender = gender;
	}

	public int getDateOfBirth() 
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(int dateOfBirth) 
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getMother() 
	{
		return mother;
	}

	public void setMother(String mother) 
	{
		this.mother = mother;
	}

	public String getFather() 
	{
		return father;
	}

	public void setFather(String father) 
	{
		this.father = father;
	}

	public Person getpMother() 
	{
		return pMother;
	}

	public void setpMother(Person pMother) 
	{
		this.pMother = pMother;
	}

	public Person getpFather() 
	{
		return pFather;
	}

	public void setpFather(Person pFather) 
	{
		this.pFather = pFather;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + dateOfBirth;
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + gender;
		result = prime * result + ((mother == null) ? 0 : mother.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pFather == null) ? 0 : pFather.hashCode());
		result = prime * result + ((pMother == null) ? 0 : pMother.hashCode());
		result = prime * result + ((siblings == null) ? 0 : siblings.hashCode());
		return result;
	}
	*/

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	public ArrayList<Person> getChildren()
	{
		return children;
	}
	
	public Person getChildAtIndex(int index)
	{
		return children.get(index);
	}
	
	public void setChildren(ArrayList<Person> children)
	{
		this.children = children;
	}
	
	public boolean hasMother()
	{
		if(this.getpMother() != null)
			return true;
		return false;
	}
	
	public boolean hasFather()
	{
		if(this.getpFather() != null)
			return true;
		return false;
	}
	
    
	@Override
	public String toString() 
	{
		if(this.hasFather() || this.hasMother())
			return "Person (Name: " + name + ", Gender: " + gender + ", DoB: " + dateOfBirth + 
					" pMother: " + pMother + ", pFather: " + pFather + ") |";
		else
			return "Person (Name: " + name + ", Gender: " + gender + ", DoB: " + dateOfBirth + ", Mother: "
				+ mother + ", Father: " + father + ") |";
	}
	
	
}
