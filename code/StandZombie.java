package code;

import javax.swing.JPanel;
/**
 * 
 * @author AndyLau96
 * ·£Õ¾½©Ê¬
 */
public class StandZombie extends GameObject{
	public int type;
	public StandZombie(int x, int y, int width, int height, JPanel parent,int type) {
		super(x, y, width, height, parent);
		this.type=type;
		if(type==1){
			image=ImgSrc.normalZoombieStand;
		}
		else if(type==2){
			image=ImgSrc.coneheadZombieStand;
		}
		else 
			image=ImgSrc.BucketheadZombie;
	}
	
}
