package code.plants;

/*
 * 植物大战僵尸
 * 制作人：任超
 * 植物：太阳花
 */
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;

import code.ContextVar;
import code.GameBackground;
import code.ImgSrc;
import code.Sun;



public class SunFlower extends Plant{
	/*
	 * 构造方法初始化
	 */
	public SunFlower(int x, int y, GameBackground parent,int line,int column) {
		super(x, y, parent,line,column);
		ImageIcon imageIcon=new ImageIcon("./src/images/sunflower.gif");
		this.apperance=ImgSrc.sunFlower;
		this.width=imageIcon.getIconWidth();
		this.height=imageIcon.getIconHeight();
		createSun();
	}
	/*
	 * 控制产生太阳的频率
	 */
	public void createSun(){
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				parent.sun_list.add(new Sun(x, y, parent));
				
			}
		}, ContextVar.createSunTime, ContextVar.createSunTime);
	}
	
	
}
