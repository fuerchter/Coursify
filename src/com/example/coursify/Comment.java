package com.example.coursify;

import java.io.Serializable;

public class Comment implements Serializable {
	String author;
	String text;
	
	Comment(String author, String text)
	{
		this.author=author;
		this.text=text;
	}
}