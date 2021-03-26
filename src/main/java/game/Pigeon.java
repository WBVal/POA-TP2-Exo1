package game;

import java.awt.Point;

public class Pigeon extends GameObject{

	private Environment env;
	public enum PigeonState {Normal, Asleep, Scared}
	private PigeonState state;
	private Point scare_pos;
	
	public Pigeon(int x, int y, Environment env) {
		super(x, y);
		this.env = env;
		this.state = PigeonState.Asleep;
		scare_pos = new Point();
	}
	
	@Override
	public void run() {
		while(true) {
			this.searchFood();
			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public PigeonState getPigeonState() {
		return this.state;
	}
	
	public void searchFood() {
		//Pigeon is scared
		if(this.state == PigeonState.Scared)
		{
			this.moveTo(this.scare_pos.x, this.scare_pos.y);
			//test arrival
			if(this.position.x == this.scare_pos.x && this.position.y == this.scare_pos.y) {
				this.state = PigeonState.Asleep;
			}
		}
		//Pigeon searches for food
		else if(env.getFood().size() != 0) {
			
			this.state = PigeonState.Normal;
			
			Food food = env.getFood().get(env.getFood().size()-1); //always search for freshest food in list (most recently placed)

			this.moveTo(food.getPosition().x, food.getPosition().y);
			
			//test arrival
			if(this.position.x == food.getPosition().x && this.position.y == food.getPosition().y && env.getFood().size() != 0) {
				env.getFood().remove(env.getFood().size()-1);
			}
		}else {
			//sleep
			this.state = PigeonState.Asleep;
		}
		
	}
	
	public void scare() {
		this.scare_pos.x = (int)(Math.random() * 10);
		this.scare_pos.y = (int)(Math.random() * 7);
		this.state = PigeonState.Scared;
	}
	
	public void moveTo(int x, int y) {
		//check x position
		if(this.position.x < x) {
			this.position.x ++;
		}
		else if(this.getPosition().x > x) {
			this.position.x --;
		}
		
		//check y position
		if(this.position.y < y) {
			this.position.y ++;
		}
		else if(this.position.y > y) {
			this.position.y --;
		}
	}
	
}
