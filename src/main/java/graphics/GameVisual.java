package graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import game.Environment;
import game.Food;
import game.Pigeon;


public class GameVisual extends JPanel implements MouseListener{

	private BufferedImage img_grass, img_pigeon, img_pigeon_asleep, img_pigeon_scared, img_food, img_rotten_food;
	private Environment env;
	
	public GameVisual(MainWindow mw, Environment env) {

        this.setSize(mw.getWidth(), mw.getHeight());
        this.env = env;
        
        this.addMouseListener(this);
        
        //Load png resources
		try {
			img_grass = ImageIO.read(new File("./src/main/resources/grass.png"));
			img_pigeon = ImageIO.read(new File("./src/main/resources/pigeon.png"));
			img_pigeon_asleep = ImageIO.read(new File("./src/main/resources/pigeon_sleep.png"));
			img_pigeon_scared = ImageIO.read(new File("./src/main/resources/pigeon_scared.png"));
			img_food = ImageIO.read(new File("./src/main/resources/food.png"));
			img_rotten_food = ImageIO.read(new File("./src/main/resources/rotten_food.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void paintComponent(Graphics g) {
		this.paintBackground(g);
		this.paintPigeon(g);
		this.paintFood(g);
		this.paintRottenFood(g);
    }
	
	
	
	public void paintBackground(Graphics g) {
		for(int i=0; i<10; i++) {
        	for (int j=0; j<7; j++) {
                g.drawImage(img_grass, i*64, j*64, this); 
        	}
        }  
	}
	
	public void paintPigeon(Graphics g) {
		for(Pigeon p: env.getPigeons()) {
			Point position = p.getPosition();
			switch(p.getPigeonState()) {
			case Normal:
				g.drawImage(img_pigeon, position.x*64, position.y*64, this);
				break;
			case Asleep:
				g.drawImage(img_pigeon_asleep, position.x*64, position.y*64, this);
				break;
			case Scared:
				g.drawImage(img_pigeon_scared, position.x*64, position.y*64, this);
				break;
			default:
				break;
			}
		}
	}

	public void paintFood(Graphics g) {
		for(Food f: env.getFood()) {
			Point position = f.getPosition();
			g.drawImage(img_food, position.x*64, position.y*64, this);
		}
	}
	
	public void paintRottenFood(Graphics g) {
		for(Food f: env.getRottenFood()) {
			Point position = f.getPosition();
			g.drawImage(img_rotten_food, position.x*64, position.y*64, this);
		}
	}
	

	public void mouseClicked(MouseEvent e) {
    	Point position = new Point();
    	//convert to world coordinates
    	position.x = this.getMousePosition().x / 64;
    	position.y = this.getMousePosition().y / 64;
    	System.out.println("Clicked "+position);
		Food newFood = new Food(position.x, position.y);
		env.addFood(newFood);
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
