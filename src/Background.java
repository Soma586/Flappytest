import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Background {
	
	int x;
	int y;
	Image image;

	public Background(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		image = Toolkit.getDefaultToolkit().getImage("background.png").getScaledInstance(1200,800,Image.SCALE_DEFAULT);
	}
	
	public void moveleft() {
		x -= 1;
	}
	
	public void draw(Graphics g) {
		
		for(int i = 0; i < 12; i++) {
			g.drawImage(image, (int)(x + 1200*i ), y, null);
			
		}
		
	}
	
	
	
}



