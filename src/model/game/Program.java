package model.game;

public class Program {
	public static void main(String[] arg) {
		Game game = new Game(1);
		game.startGame();
		System.out.print(game.getActionMsg());
	}
}
