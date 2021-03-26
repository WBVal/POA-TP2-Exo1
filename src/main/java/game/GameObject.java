package game;

import java.awt.Point;

public class GameObject extends Thread{
	protected Point position;
	
	public GameObject(int x, int y) {
		position = new Point();
		position.x = x;
		position.y = y;
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point newPos) {
		position = newPos;
	}
}
