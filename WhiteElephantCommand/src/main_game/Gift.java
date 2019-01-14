package main_game;

public class Gift {

	private String name;
	private int takes;
	private Player owner;
	
	public Gift(String name) {
		this.name=name;
	}
	
	public Gift(String name, Player owner) {
		this.name=name;
		this.owner=owner;
	}
	
	public int getSteals() {
		return takes;
	}
	
	public void steal() {
		takes++;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void setOwner(Player p) {
		this.owner=p;
	}
	
	public String getName() {
		return name;
	}
	
	
	
}
