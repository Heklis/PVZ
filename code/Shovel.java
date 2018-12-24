package code;

import java.awt.Rectangle;

import javax.swing.JPanel;
/**
 * 
 * @author AndyLau96
 *
 */
public class Shovel extends GameObject{

	public Shovel(int x, int y, int width, int height, JPanel parent) {
		super(x, y, width, height, parent);
		image=ImgSrc.IMG_SHOVEL;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(x,y,width,height);
	}
}
