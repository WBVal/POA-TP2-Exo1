package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graphics.MainWindow;

public class Environment {
	private List<Pigeon> pigeon_list;
	private List<Food> food_list, rotten_food_list;
	private MainWindow ui;
	private Boolean running;
	
	public Environment(MainWindow mw) {
		this.pigeon_list = new ArrayList<Pigeon>();
		this.food_list = new ArrayList<Food>();
		this.ui = mw;
	}
	
	public void initEnvironment() {
		this.running = true;
		this.food_list = new ArrayList<Food>();
		this.rotten_food_list = new ArrayList<Food>();
		Pigeon pigeon1 = new Pigeon(0, 0, this);
		pigeon_list.add(pigeon1);
		Pigeon pigeon2 = new Pigeon(9, 0, this);
		pigeon_list.add(pigeon2);
	}
	
	public List<Pigeon> getPigeons(){
		return pigeon_list;
	}
	
	public List<Food> getFood(){
		return food_list;
	}
	
	public List<Food> getRottenFood(){
		return rotten_food_list;
	}
	
	public void addFood(Food f) {
		food_list.add(f);
	}
	
	public void run() {
		this.initEnvironment();
		this.ui.initWindow(this);
		this.ui.run();
		for(Pigeon p: pigeon_list) {
			p.start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		while(running) {
			try {

			//handle Pigeons
				
			int random_range = (int) (Math.random() * 10000) + 100;
			int scare_test = (int) (Math.random() * random_range);
			
			if(scare_test == 0) {
				System.out.println("Pigeons are Scared !");
				for (Pigeon p : pigeon_list) {
		            if (p.isInterrupted()) {
		                p.notify();
		            }
		            p.scare();
				}
			}
			
			//handle Food
			for(Iterator<Food> it = food_list.iterator(); it.hasNext();) {
				Food f = it.next();
				//decrement countdown
				if(f.getRotCountdown()>0) {
					f.setRotCountdown(f.getRotCountdown()-1);
				}else {
					rotten_food_list.add(f);
					it.remove();
				}
			}
			
			this.ui.run();
			Thread.sleep(1);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
