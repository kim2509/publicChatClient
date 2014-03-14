package com.dy.domain;

public class Keyword {

	private String name = "";

	public Keyword()
	{
		
	}
	
	public Keyword( String name )
	{
		setName(name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String keywordName) {
		this.name = keywordName;
	}
	
}
