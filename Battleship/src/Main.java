import gui.BattleshipGUI;
import view.TUI;
import controller.Controller;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Controller controller = new Controller(0);
		// TUI tui = new TUI(controller);
		BattleshipGUI gui = new BattleshipGUI(controller);
		
		controller.gameLoop();
	}
}