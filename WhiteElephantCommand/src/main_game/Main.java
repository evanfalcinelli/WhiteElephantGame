package main_game;

import javax.swing.JFrame;

import we_view.ViewUI;


public class Main {

	public static void main(String[] args) {
		Game g=new Game();
		
		
		ViewUI game_view = new ViewUI(g);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("White Elephant");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		main_frame.setContentPane(game_view);

		main_frame.pack();
		main_frame.setVisible(true);
	
	}
}
