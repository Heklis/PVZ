package code;

import java.awt.Rectangle;

import javax.swing.JPanel;
/**
 * 
 * @author AndyLau96
 *
 */
public class LawnMower extends GameObject{
	public int line;
	public boolean isMoving=false;
	public LawnMower(int x, int y, int width, int height, JPanel parent) {
		super(x, y, width, height, parent);
		image=ImgSrc.IMG_LAWN_MOWER;
	}
	
	public void move(){
		x+=20;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(x,y,width,height);
	}
}
