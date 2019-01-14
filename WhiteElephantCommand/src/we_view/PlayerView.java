package we_view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main_game.Game;
import main_game.Player;

public class PlayerView extends JPanel implements ActionListener{

	//ignore this
	private static final long serialVersionUID = -1793194666750992793L;
	private Game game_model;
	
	//buttons for adding a player
	private JButton addPlayer=new JButton("Add Player");
	private JTextField namePlayer=new JTextField("Name");
	private JTextField idPlayer=new JTextField("ID");
	private JButton finishPlayer=new JButton("Done!");
	
	private JButton removePlayer=new JButton("Remove Player");
	private JComboBox<String> deadPlayer=new JComboBox<String>();
	private JButton finishRemove=new JButton("Remove!");
	
	private JButton cancelButton=new JButton("Cancel");
	
	private JLabel title=new JLabel("                         Players");
	
	//keeping a list of all added players
	public List<Player> playerList=new LinkedList<Player>();
	public List<JButton> buttonList=new LinkedList<JButton>();
	
	//***************************************************CONSTRUCTOR********************************************************************************************
	public PlayerView(Game game_model) {
		
		this.game_model=game_model;
		
		setLayout(new GridLayout(30,1));
		
		title.setFont(new Font("Comic Sans",Font.BOLD,28));
		
		addPlayer.setBackground(Color.GREEN);
		addPlayer.addActionListener(this);
		addPlayer.setActionCommand("addPlayer");
		addPlayer.setPreferredSize(new Dimension(500,75));
		
		removePlayer.setBackground(Color.PINK);
		removePlayer.addActionListener(this);
		removePlayer.setActionCommand("removePlayer");
		removePlayer.setPreferredSize(new Dimension(500,75));
		
		//deadPlayer.setBackground(Color.BLACK);
		//deadPlayer.setForeground(Color.RED);
		deadPlayer.setPreferredSize(new Dimension(500,75));
		
		finishRemove.setBackground(Color.PINK);
		finishRemove.addActionListener(this);
		finishRemove.setActionCommand("finishRemove");
		finishRemove.setPreferredSize(new Dimension(500,75));
		
		
		//namePlayer.setBackground(Color.CYAN);
		namePlayer.addActionListener(this);
		namePlayer.setActionCommand("namePlayer");
		namePlayer.setPreferredSize(new Dimension(500,75));
		
		//idPlayer.setBackground(Color.CYAN);
		idPlayer.addActionListener(this);
		idPlayer.setActionCommand("idPlayer");
		idPlayer.setPreferredSize(new Dimension(500,75));
	
		finishPlayer.setBackground(Color.GREEN);
		finishPlayer.addActionListener(this);
		finishPlayer.setActionCommand("finishPlayer");
		finishPlayer.setPreferredSize(new Dimension(500,75));
		
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("cancel");
		cancelButton.setPreferredSize(new Dimension(500,75));
		
		add(title);
		add(addPlayer);
		add(removePlayer);
		add(cancelButton);
		add(new JLabel(""));
		revalidate();
		repaint();
	}
	//**********************************************************************************************************************************************************
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("addPlayer")) {
			add(namePlayer);
			add(idPlayer);
			add(finishPlayer);
			
			revalidate();
			repaint();
		}
		
		if(e.getActionCommand().equals("removePlayer")) {
			for(Player p : game_model.players.values()) {
				deadPlayer.addItem(p.getName());
			}
			add(deadPlayer);
			add(finishRemove);
			
			revalidate();
			repaint();
		}
		
		if(e.getActionCommand().equals("finishRemove")) {
			String dead=(String)deadPlayer.getSelectedItem();
			game_model.removePlayer(dead);
			JButton temp=null;
			for(JButton b : buttonList) {
				if(b.getActionCommand().equals(dead)) {
					temp=b;
				}
			}
			buttonList.remove(temp);
			remove(deadPlayer);
			remove(finishRemove);
			for(Player p : game_model.players.values()) {
				deadPlayer.removeItem(p.getName());
			}
			
			refresh();
			
			revalidate();
			repaint();
			
		}
		
		if(e.getActionCommand().equals("finishPlayer")) {
			String name=namePlayer.getText();
			int id=Integer.parseInt(idPlayer.getText());
			
			remove(namePlayer);
			remove(idPlayer);
			remove(finishPlayer);
			
			JButton newPlayer=new JButton(name);
			newPlayer.addActionListener(this);
			newPlayer.setActionCommand(name);
			newPlayer.setPreferredSize(new Dimension(500,75));
			add(newPlayer);
			buttonList.add(newPlayer);
			
			//System.out.println("finish player button was hit");
			game_model.addPlayer(name, id);
			
			revalidate();
			repaint();
		}
		
		if(e.getActionCommand().equals("cancel")) {
			removeAll();
			add(title,SwingConstants.CENTER);
			add(addPlayer);
			add(removePlayer);
			add(cancelButton);
			
			for(JButton b : buttonList) {
				add(b);
			}
			add(new JLabel(""));
			revalidate();
			repaint();
		}
		
		for(Player p : game_model.players.values()) {
			if(e.getActionCommand().equals(p.getName())) {
				JFrame frame= new JFrame("Player Information");
				
				if(p.getGift()==null) {
					JOptionPane.showMessageDialog(frame, "No gift");
				}
				else {
					JOptionPane.showMessageDialog(frame, p.getGift().getName());
				}
				
			}
		}
		
	}
	
	public void refresh() {
		removeAll();
		add(title);
		add(addPlayer);
		add(removePlayer);
		add(cancelButton);
		add(new JLabel(""));
		
		for(JButton b : buttonList) {
			add(b);
		}
		
		revalidate();
		repaint();
		
	}
}
