package main_game;

public class Player {

	private String name;
	private int id;
	private Gift gift;
	private boolean hasGift;
	
	public Player(String name, int id) {
		this.name=name;
		this.id=id;
		this.hasGift=false;
	}
		
	public Player(String name, int id, Gift gift) {
		this.name=name;
		this.id=id;
		this.gift=gift;
		this.hasGift=true;
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return id;
	}
	
	public Gift getGift() {
		if(gift==null) {
			return null;
		}
		return gift;
	}
	
	public void setGift(Gift g) {
		this.gift=g;
		hasGift=true;
	}
	
	public boolean hasGift() {
		return hasGift;
	}
	
	public void removeGift() {
		this.gift=null;
		hasGift=false;
	}
}
