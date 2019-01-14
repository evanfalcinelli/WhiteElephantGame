package main_game;

public class GiftChangeEvent {

	public enum EventType{STOLEN,OPENED,ACQUIRED}
	private GiftChangeEvent.EventType type;
	
	public GiftChangeEvent(EventType type) {
		this.type=type;
	}
	
	public GiftChangeEvent.EventType getType(){
		return type;
	}
}
