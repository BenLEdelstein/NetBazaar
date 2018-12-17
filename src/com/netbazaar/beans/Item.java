package com.netbazaar.beans;

public class Item {
	String name;
	int price;
	
	public Item() {
		
	}
	
	public Item (String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object obj) {
		Item item = (Item)obj;
		if((this.name.equals(item.getName())||this.name==item.getName()) && this.price==item.getPrice()) {
			return true;
		}
		return false;
	}
	
}
