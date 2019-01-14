package we_view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main_game.Game;
import main_game.Gift;
import main_game.GiftChangeEvent;
import main_game.Player;

public class GiftChangeView extends JPanel implements Observer,ActionListener{

	private static final long serialVersionUID = -3003360936424355004L;
	private JButton addGift=new JButton("Add Gift");
	private JTextField nameGift=new JTextField("Name");
	private JComboBox<String> ownerGift=new JComboBox<String>();
	private JButton finishAddGift=new JButton("Done!");
	
	private JButton stealGift=new JButton("Steal Gift");
	private JComboBox<String> thiefBox=new JComboBox<String>();
	private JComboBox<String> victimBox=new JComboBox<String>();
	private JButton finishSteal=new JButton("Confirm Steal");
	
	private JButton cancelButton=new JButton("Cancel");
	
	private JLabel title=new JLabel("                           Gifts");
	
	private Game game_model;

	public GiftChangeView(Game game_model) {
		
		this.game_model=game_model;
		game_model.addObserver(this);
		setLayout(new GridLayout(30,1));
		
		title.setFont(new Font("Comic Sans",Font.BOLD,28));
		add(title);
		
		addGift.addActionListener(this);
		addGift.setActionCommand("addGift");
		addGift.setBackground(Color.GREEN);
		addGift.setPreferredSize(new Dimension(500,75));
		add(addGift);
		
		nameGift.addActionListener(this);
		nameGift.setActionCommand("nameGift");
		//nameGift.setBackground(Color.cyan);
		nameGift.setPreferredSize(new Dimension(500,75));
		
		finishAddGift.addActionListener(this);
		finishAddGift.setActionCommand("finishAddGift");
		finishAddGift.setBackground(Color.GREEN);
		finishAddGift.setPreferredSize(new Dimension(500,75));
		
		stealGift.addActionListener(this);
		stealGift.setActionCommand("stealGift");
		stealGift.setBackground(Color.PINK);
		//stealGift.setForeground(Color.RED);
		stealGift.setPreferredSize(new Dimension(500,75));
		add(stealGift);
		
		finishSteal.addActionListener(this);
		finishSteal.setActionCommand("finishSteal");
		finishSteal.setBackground(Color.PINK);
		finishSteal.setPreferredSize(new Dimension(500,75));
		
		
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("cancel");
		cancelButton.setPreferredSize(new Dimension(500,75));
		add(cancelButton);
		add(new JLabel(""));
		
		revalidate();
		repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("addGift")) {
			add(nameGift);
			
			for(Player c : game_model.players.values()) {
				if(!c.hasGift()) {
					ownerGift.addItem(game_model.players.get(c.getName()).getName());
				}
			}
			ownerGift.setPreferredSize(new Dimension(300,50));
			add(ownerGift);
			add(finishAddGift);
			
			revalidate();
			repaint();
		}
		
		if(e.getActionCommand().equals("finishAddGift")) {
			String giftName=nameGift.getText();
			String playerName=ownerGift.getSelectedItem().toString();
			Player p=game_model.players.get(playerName);
			Gift g=new Gift(giftName,p);
			game_model.gifts.put(giftName, g);
			p.setGift(g);
			ownerGift.removeItem(playerName);
			
			remove(nameGift);
			remove(ownerGift);
			remove(finishAddGift);
			
			revalidate();
			repaint();		
		}
		
		if(e.getActionCommand().equals("stealGift")) {
			for(Player p : game_model.players.values()) {
				thiefBox.addItem(p.getName());
				victimBox.addItem(p.getName());
			}
			add(thiefBox);
			add(victimBox);
			add(finishSteal);
			
			revalidate();
			repaint();
		}
		
		if(e.getActionCommand().equals("finishSteal")) {
			String thief=(String)thiefBox.getSelectedItem();
			String victim=(String)victimBox.getSelectedItem();
			for(Player p : game_model.players.values()) {
				thiefBox.removeItem(p.getName());
				victimBox.removeItem(p.getName());
			}
			
			game_model.stealGift(thief, victim);
			
			
			
		}
		
		if(e.getActionCommand().equals("cancel")) {
			removeAll();
			add(title);
			add(addGift);
			add(stealGift);
			add(cancelButton);
			add(new JLabel(""));
			revalidate();
			repaint();
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		removeAll();
		add(title);
		add(addGift);
		add(stealGift);
		add(cancelButton);
		add(new JLabel(""));
		revalidate();
		repaint();
	}
}
