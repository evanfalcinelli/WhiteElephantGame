package we_view;

import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main_game.DisplayObserver;
import main_game.Game;
import main_game.GiftChangeEvent;
import main_game.Player;

public class DisplayView extends JPanel implements DisplayObserver{
	
	private List<JLabel> players=new LinkedList<JLabel>();
	private List<JLabel> gifts=new LinkedList<JLabel>();
	private Game game_model;

	public DisplayView(Game game_model) {
		this.game_model=game_model;
		game_model.displayObservers.add(this);
		game_model.addObserver(this);
		setLayout(new GridLayout(0,2,25,5));
		add(new JLabel("Players",SwingConstants.RIGHT));
		add(new JLabel("Gifts"));
	}
	
	public void refresh() {
		
		removeAll();
		add(new JLabel("Players",SwingConstants.RIGHT));
		add(new JLabel("Gifts"));
		
		for(Player c : game_model.players.values()) {
			add(new JLabel(c.getName(),SwingConstants.RIGHT));
			if(!c.hasGift()) {
				add(new JLabel("No gift"));
			}
			if(c.hasGift()){
				add(new JLabel(c.getGift().getName()));
			}
		}
		revalidate();
		repaint();
	}

	
	public void update(Observable o, Object event) {
		refresh();
	}

	@Override
	public void handleDisplayEvent(GiftChangeEvent e) {
		refresh();
	}
	
}
