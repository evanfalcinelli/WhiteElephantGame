package main_game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Game extends Observable{

	public HashMap<String,Player> players=new HashMap<String,Player>();
	public HashMap<String,Gift> gifts=new HashMap<String,Gift>();
	public List<DisplayObserver> displayObservers;
	
	public Game() {
		displayObservers=new ArrayList<DisplayObserver>();
	}
	
	public boolean addPlayer(String name, int id) {
		Player p= new Player(name,id);
		
		if(players.containsKey(name)) {
			//System.out.println(name+" is already in the game.");
			return false;
		}
		players.put(name, p);
		//System.out.println(name+" has been added to the game.");
		setChanged();
		notifyObservers();
		return true;
	}
	
	public boolean removePlayer(String name) {
		if(!players.containsKey(name)) {
			return false;
		}
		//Player p=players.get(name);
		//p.getGift().setOwner(null);
		
		players.remove(name);
		setChanged();
		notifyObservers();
		return true;
	
	}
	
	public boolean setGift(Player p,Gift g) {
		p.setGift(g);
		g.setOwner(p);
		setChanged();
		notifyObservers();
		return true;
	}
	
	public boolean stealGift(String thief, String victim) {
		
		if(!players.containsKey(thief) || !players.containsKey(victim)) {
			//System.out.print("Steal is unavailable.");
			return false;
		}
		Gift stolen=players.get(victim).getGift();
		if(stolen==null) {
			//System.out.println(thief+" wants to steal a gift from "+victim);
			//System.out.println(victim+" has no gift to steal.");
			return false;
		}
		
		if(thief.equals(victim)) {
			//System.out.println(thief+" wants to steal "+stolen.getName()+" from "+victim);
			//System.out.println("You can't steal from yourself!");
			return false;
		}
		
		//System.out.println(thief+" wants to steal "+stolen.getName()+" from "+victim);
		
		players.get(thief).setGift(stolen);
		stolen.setOwner(players.get(thief));
		stolen.steal();
		
		if(stolen.getSteals()>=3) {
			players.remove(thief);
			gifts.remove(stolen.getName());
			//System.out.println("With 3 takes, "+thief+" wins the "+stolen.getName()+"!");
			return true;
		}
		stolen.setOwner(null);
		players.get(victim).setGift(null);
		//System.out.println(thief+" stole "+stolen.getName()+" from "+victim+". The gift has "+stolen.getSteals()+" take(s).");
		setChanged();
		notifyObservers();
		return true;
	}
	
}
