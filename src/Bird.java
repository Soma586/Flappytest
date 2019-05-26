import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Bird extends Rectangle{
	
	
	Image image  = Toolkit.getDefaultToolkit().getImage("bird.png").getScaledInstance(30,30,Image.SCALE_DEFAULT);
	
	
	public void draw(Graphics g) {
		g.drawImage(image, 50,50,null);

	}

}
