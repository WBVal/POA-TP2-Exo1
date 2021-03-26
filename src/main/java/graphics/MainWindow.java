package graphics;

import game.Environment;
import javax.swing.*;

public class MainWindow extends JFrame{
	
	private GameVisual panel;
	
	
	public MainWindow() {
		super();
		this.setSize(656, 486);
	}
	
	public void initWindow(Environment env) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.panel = new GameVisual(this, env);
		this.setContentPane(this.panel);
	}

	
	public void run() {
        this.panel.repaint();
	}
	
}
