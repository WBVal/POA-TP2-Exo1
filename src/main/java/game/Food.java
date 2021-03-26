package game;

public class Food extends GameObject{

	public enum FoodState{Fresh, Rotten};
	
	private FoodState state;
	private int rot_countdown;
	
	public Food(int x, int y) {
		super(x, y);
		this.state = FoodState.Fresh; 
		this.rot_countdown = 3000;
	}
	
	public void setRotCountdown(int cd) {
		this.rot_countdown = cd;
	}
	
	public int getRotCountdown() {
		return this.rot_countdown;
	}

	public void setFoodState(FoodState fs) {
		this.state = fs;
	}
	
	public FoodState getFoodState() {
		return this.state;
	}
	
}
