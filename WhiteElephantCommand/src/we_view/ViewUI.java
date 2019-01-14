package we_view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main_game.Game;

public class ViewUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 5924108406298077904L;
	
	private Game g;
	public PlayerView playerView;
	public GiftChangeView giftChangeView;
	public DisplayView displayView;
	public JPanel title;
	
	public ViewUI(Game g) {
		setLayout(new BorderLayout());
		
		playerView=new PlayerView(g);
		add(playerView,BorderLayout.WEST);
		
		GiftChangeView giftChangeView=new GiftChangeView(g);
		add(giftChangeView,BorderLayout.EAST);
		
		DisplayView displayView=new DisplayView(g);
		add(displayView,BorderLayout.CENTER);
		
		
		title=new JPanel();
		JLabel l=new JLabel("White Elephant");
		l.setFont(new Font("Comic Sans",Font.BOLD,48));
		title.add(l);
		add(title,BorderLayout.NORTH);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
