package com.example.coursify;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {
	String name;
	ArrayList<Integer> ratings=new ArrayList<Integer>();
	ArrayList<Comment> comments=new ArrayList<Comment>();
	
	Course(String name)
	{
		this.name=name;
	}
	
	public String getName()
	{
		return name;
	}
}