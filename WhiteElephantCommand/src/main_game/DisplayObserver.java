package main_game;

import java.util.Observer;

public interface DisplayObserver extends Observer{

	public void handleDisplayEvent(GiftChangeEvent e);
}
