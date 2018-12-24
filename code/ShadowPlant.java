package code;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * 
 * @author AndyLau96
 *
 */
public class ShadowPlant extends GameObject{
	public int plant_type;  //÷≤ŒÔ¿‡–Õ
	public ShadowPlant(int x, int y, int width, int height, JPanel parent,int type) {
		super(x, y, width, height, parent);
		plant_type=type;  
		switch(plant_type){
			case 0:
				image=ImgSrc.IMG_SUN_FLOWER_TRANSPARENT;
				break;
			case 1:
				image=ImgSrc.IMG_BEAN_SHOOTER_TRANSPARENT;
				break;
			case 2:
				image=ImgSrc.IMG_BOOM_POTATO_TRANSPARENT;
				break;
			case 3:
				image=ImgSrc.IMG_POTATO_TRANSPARENT;
				break;
			case 4:
				image=ImgSrc.IMG_DOUBLE_BEAN_SHOOTER_TRANSPARENT;
				break;
			case 5:
				image=ImgSrc.IMG_CHERRYBOOM_HALF_TRANSPARENT;
				break;
		}
		ImageIcon imgIcon = new ImageIcon(image);
		this.width = imgIcon.getIconWidth();
		this.height = imgIcon.getIconHeight();
	}

}
