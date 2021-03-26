import game.Environment;
import graphics.MainWindow;

public class Main {
	public static void main(String[] args) {
		Environment env = new Environment(new MainWindow());
		env.run();
	}
}
