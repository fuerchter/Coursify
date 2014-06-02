package com.example.coursify;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
	private String name;
	private ArrayList<Integer> ratings=new ArrayList<Integer>();
	
	ArrayList<Comment> comments=new ArrayList<Comment>();
	
	Course(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void addRating(int rating)
	{
		//TODO: Add min/max rating attributes?
		if(rating<1)
		{
			rating=1;
		}
		if(rating>5)
		{
			rating=5;
		}
		
		ratings.add(rating);
	}
	
	public float getAverageRating()
	{
		//TODO: check whether no ratings available
		float avg=0;
		for(int rating : ratings)
		{
			avg+=rating;
		}
		avg/=ratings.size();
		return avg;
	}
	
	public int getRatingsCount()
	{
		return ratings.size();
	}
}