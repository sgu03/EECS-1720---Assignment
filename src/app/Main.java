package app;

import model.game.Game;
import view.GameFrame;

public class Main {
	public static void main(String[] arg) {
		Game game = new Game(3);
		game.startGame();
		GameFrame frame = new GameFrame(game);
	}
}
