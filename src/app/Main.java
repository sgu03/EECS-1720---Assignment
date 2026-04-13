package app;

import model.game.Game;
import view.StartFrame;

public class Main {
	public static void main(String[] arg) {
		Game game = new Game(3);
		StartFrame frame = new StartFrame(game);
	}
}
