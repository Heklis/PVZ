package code;

import java.awt.Rectangle;

import javax.swing.JPanel;
/**
 * 
 * @author AndyLau96
 *
 */
public class ShovelBack extends GameObject{
	
	
	public ShovelBack(int x, int y, int width, int height, JPanel parent) {
		super(x,y,width,height,parent);
		
		image=ImgSrc.IMG_SHOVEL_BACK;
	}
	public Rectangle getRectangle(){
		return new Rectangle(x,y,width,height);
	}
}
