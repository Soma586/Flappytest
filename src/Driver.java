import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import java.awt.Font;

public class Driver extends Applet implements Runnable, KeyListener {

	Thread t;
	Image offscreeng;
	Graphics offscreen_g;
	Random rand;
	
	//image = Toolkit.getDefaultToolkit().getImage(filename);
	
	//Image image  = Toolkit.getDefaultToolkit().getImage("background.png");
	Background bg = new Background(0,0);
	Rectangle bird;
	ArrayList<Rectangle> pipes;
	int score = 0;
	int count = 0;
	
	//Bird word = new Bird();
	
	int w = 1200;
	int h = 800; 
	
	boolean gameOver = false;
	boolean start = false;
	
	
	/*
	boolean up_pressed = false;
	boolean dn_pressed = false;
	boolean lt_pressed = false;
	boolean rt_pressed = false;
	boolean  A_pressed = false;
	boolean  S_pressed = false;
	boolean  W_pressed = false;
	
	*/

	
	
	//this is random fromgithub
	public void init() {
	
		//image = image.getScaledInstance(1200, 680, Image.SCALE_DEFAULT);
		rand = new Random();
		bird = new Rectangle(1200/2, 800/2, 20, 20);
		pipes = new ArrayList<Rectangle>();
		offscreeng = this.createImage(1200,800);
		offscreen_g = offscreeng.getGraphics();
		requestFocus();
		
		this.setSize(1200, 800);
		requestFocus();
		addKeyListener(this);
		
		addPipe(true);
		addPipe(true);
		addPipe(true);
		t = new Thread(this);
		t.start();
	}
	
	
	public void addPipe(boolean start) {
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);
		
		//h is 120 because of the top of the grass
		
		//starts all the way right of the screen
		//height -120 to be on top of ground
		if(start) {
			//these will be the starting pipes
		pipes.add(new Rectangle(w + width + pipes.size()*300, h - height, width, height));
		pipes.add(new Rectangle(w + width + (pipes.size() - 1) * 300, 0, width, h - height - space ));
		}else {
			//these will be the pipes that come after
			//it'll take the last pipe
			pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x + 600, h - height , width, height));
			pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x, 0, width, h - height  - space)); 
		}
	}
	
	public void paintPipe(Graphics g, Rectangle pipe) {
		
		//System.out.println("im print bitch");
		//g.drawRect(200,500, 100,100);
		g.setColor(Color.green.darker());
		g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
	}
	
	
	public void paint(Graphics g) {
		//background
		//g.drawImage(image, 0, 0, null);
		bg.draw(g);
	
		
		
		g.setColor(Color.blue);
		g.fillRect(bird.x, bird.y, bird.width, bird.height);
		//System.out.println("yp");
		//System.out.println("hey there");
		for (Rectangle pipe : pipes)
		{
			//g.drawRect(20,20, 100,100);
			paintPipe(g, pipe);
		}
		//g.drawRect(20,20, 100,100);
		//g.setColor(Color.white);
		//g.setFont(new Font("Arial",Font.,100));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
		
		if(gameOver) {
			g.setColor(Color.red);
			g.drawString("Game Over", 400, h/2 -50);
		}
		
		if(!start) {
			g.setColor(Color.blue);
			g.drawString("Press Z to start", 280, h/2 -50);
		}
		
		
			g.setColor(Color.white);
			g.drawString(String.valueOf(score), w/2 -25, 100);
		
		
		
		
		
	}
	
	public void update(Graphics g) {
		offscreen_g.clearRect(0,0, 1200, 800);
		paint(offscreen_g);
		g.drawImage(offscreeng, 0,0,1200, 800,null);
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int speed = 5;
		
		while(true) {
			
			if(start) {
				bg.moveleft();
			
			for(int i =0; i < pipes.size(); i++) {
				Rectangle pipe = pipes.get(i);
				pipe.x -= speed;
			}
			
			for(int i =0; i < pipes.size(); i++) {
				Rectangle pipe = pipes.get(i);
				if(pipe.x + pipe.width <0) {
					pipes.remove(pipe);
					
					
					if(pipe.y == 0) {
						addPipe(false);
					}
				}
			}
			
			bird.y+=3;
			
			//checks for collision
			for(int i = 0; i < pipes.size(); i++) {
				
				if(pipes.get(i).intersects(bird)) {
					gameOver = true;
					bird.x = pipes.get(i).x - bird.width;
					
				}
			}
			
			
			
			if(bird.y > h  || bird.y < 0) {
			
				gameOver = true;
			}
			
			}
			
			//System.out.println(pipes.size());
			System.out.println("gameover is " + gameOver);
			
		repaint();
		
		try {
			t.sleep(16); 
			
		}catch(Exception x) {};
		
	}
	}



	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
			System.out.println("space");
			bird.y -=50;
			count ++;
			if(count == 9) {
				score++;
				count = 0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_Z && start == true) {
			start = false;
		}else {
			if(e.getKeyCode() == KeyEvent.VK_Z && start == false) {
				start = true;
			}
		}
		
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}

}
