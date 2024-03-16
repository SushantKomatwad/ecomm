package com.ecomm.modal;

public class Size {

	private String name;
	private int quentity;
	
	public Size()
	{
		
	}
	
	
	
	public Size(String name, int quentity) {
		super();
		this.name = name;
		this.quentity = quentity;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuentity() {
		return quentity;
	}
	public void setQuentity(int quentity) {
		this.quentity = quentity;
	}
	
	
}
