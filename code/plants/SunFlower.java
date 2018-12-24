package code.plants;

/*
 * ֲ���ս��ʬ
 * �����ˣ��γ�
 * ֲ�̫����
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
	 * ���췽����ʼ��
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
	 * ���Ʋ���̫����Ƶ��
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
