package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import sun.audio.*;
	
	public class MyCanvas extends Canvas implements KeyListener {
	
		
		
	
		
	// global variables - accesible in all methods
	Goodguy link = new Goodguy(650,0,10,10, "files/left.png");
	LinkedList badguys = new LinkedList ();
	boolean StartGame = false;
	SplashScreen homescreen = new SplashScreen (0,0,800,800, "Files/homescreen.png");
	Image finish = Toolkit.getDefaultToolkit().getImage("files/l.png");
	Image gameover = Toolkit.getDefaultToolkit().getImage("files/endscreen.png");
	//Image suck =  Toolkit.getDefaultToolkit().getImage("files/suck.png");
	String  done = "Press";
	String done2 = "Space for";
	String done3 = "easier";
	String done4 = "Enter for";
	String done5 = "hard";
	String done6 =  "use arrow keys to navigate the maze";
	Integer countdown = 40;
	
	
	
	

	
	
	
	

	/**
	 * MyCanvas drawing canvas inherits from java.awt.Canvas
	 * @author drake.belanger-polak
	 * @since October 10 6`
	 * |[\|"/≥ 
	 * 
	 * 
	 * hj'
	 * 'hn
b
	 * @param nonb parameters, default constructor
	 */
	public MyCanvas() {
		this.setSize(800,800);
		this.addKeyListener((KeyListener) this);
		playIt("files/sans.wav");
		TimerTask repeatedTask = new TimerTask() {

	        public void run() {

	        	countdown = countdown - 1;
	        	 repaint();
	        	}

	            

	        

	    };

	    Timer timer = new Timer("Timer");

	     

	    long delay  = 1000L;

	    long period = 1000L;

	    timer.scheduleAtFixedRate(repeatedTask, delay, period);
	}
		
	public void playIt(String filename) {
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream (in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
		
	/**
	 * paint overload java.awt.Canvas paint method and make new oval
	 * @param graphics content variable called g
	 */
	@Override
	public void paint(Graphics g) {
		
		g.drawImage(homescreen.getImg(), homescreen.getxCoord(),homescreen.getyCoord(), homescreen.getHeight(), homescreen.getWidth(), this);
		Font newFont = new Font("Serif", Font.BOLD, 20);
		g.setFont(newFont);
		g.setColor(Color.WHITE);
		g.drawString(done, 10, 600);
		g.drawString(done2, 10, 650);
		g.drawString(done3, 10, 700);
		g.drawString(done, 700, 600);
		g.drawString(done4, 700, 650);
		g.drawString(done5, 700, 700);
		g.drawString(done6, 325, 700);
		
		
		if (countdown < 1) {
			//g.drawImage(suck, 0, 0, 800, 800, this);
			g.drawImage(Toolkit.getDefaultToolkit().getImage("files/suck.png"), 0, 0, 800, 800, this);		
		} else if (StartGame == true) {
				int endmaze = 0;
				g.drawImage(Toolkit.getDefaultToolkit().getImage("files/goodmaze.png"),0,0,800,800, this);
				g.drawImage(finish,700,750,30,30,this);
				g.drawImage(link.getImg(), link.getxCoord(),link.getyCoord(), link.getHeight(), link.getWidth(), this);
				Rectangle fin = new Rectangle(700,750,30,30);
				Rectangle l = new Rectangle(link.getxCoord(),link.getyCoord(), link.getWidth(), link.getHeight());
				g.drawString(countdown.toString(), 30, 30);
				if (l.intersects(fin)) {
					endmaze = 1;
					g.drawImage(gameover,0,0,800,800,this);	
					playIt("files/nice.wav");
				for(int i = 0; i < badguys.size(); i++) {
					Badguy bg = (Badguy) badguys.get(i);
					g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(),bg.getWidth(),bg.getHeight(), this);
					
					
					}
				}
				int[] rgbarray = {0,0,0,0};

				try {

				    File img = new File("files/goodmaze.png");

				    BufferedImage image = ImageIO.read(img); 

				    image.getRaster().getPixel(link.getxCoord(),link.getyCoord(),rgbarray);

				    System.out.println("R: " + rgbarray[0]);
				   
				    if (rgbarray[0] != 255) {
				    	if (endmaze == 0) {
				    	link.setxCoord(490);
				    	link.setyCoord(0); 
				    } else {
				    	link.setyCoord(20);
				    	link.setxCoord(20);
				    }
				    }  
				    
				} catch (IOException e) { 

				    e.printStackTrace(); 

				}
			}
			
		}
		
		
	
	
		
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
		
	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		System.out.println(e);
		link.moveIt(e.getKeyCode(), this.getWidth(), this.getHeight());
		
		
		repaint();
		
		if (key == KeyEvent.VK_ENTER) {
			StartGame = true;
			link.setHeight(30);
			link.setWidth(30);
			
	}
	    if (key == KeyEvent.VK_SPACE) {
	    	StartGame = true;
	    	
	}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
	
