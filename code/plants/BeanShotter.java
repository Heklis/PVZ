package code.plants;
/*
 * 植物大战僵尸
 * 制作人：任超
 */
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import code.GameBackground;
import code.ImgSrc;
import code.Sound;



public class BeanShotter extends Plant{
	boolean shouldShot=false;//是否应该射击
	//构造方法初始化
	public BeanShotter(int x, int y, GameBackground parent,int line,int column) {
		super(x, y, parent, line,column);
		this.shotOnce=true;//具有射击的能力
		//获取图片和宽高
		ImageIcon imageIcon=new ImageIcon("./src/images/bean_shooter.gif");
		this.apperance=ImgSrc.beanShotter;
		this.width=imageIcon.getIconWidth();
		this.height=imageIcon.getIconHeight();
		
	}
	//画豌豆
	@Override
	public void drawPlant(Graphics graphics) {
		//调用父类方法
		super.drawPlant(graphics);
		if (isLive) {//存活就射击
			shot();			
		}else {
			//射击设置为false
			shouldShot=false;
			//将timer设置为null
			if (timer!=null) {
				timer.cancel();
				timer=null;				
			}
		}
	}
	Timer timer=null;
	//检测射击
	public void shot(){
		for(int i=0;i<parent.zombie_list.size();i++){
			if (parent.zombie_list.get(i).line==this.line&&parent.zombie_list.get(i).x>this.x&&parent.zombie_list.get(i).x<850) {
				shouldShot=true;//设置为true
				if (timer==null) {//设置定时射击
					timer=new Timer();
					timer.schedule(new TimerTask() {
						
						@Override
						public void run() {
							parent.bean_list.add(new Bean(x+width/2, y+5, parent,line,column));
							new Sound("music/plant_shoot.mp3").play();
						}
					}, 1000, 2000);
				}else{
					break;//timer不为空直接break
				}
			}
		}
		if (!shouldShot) {//如果不应该射击，将timer设置为空
			if (timer!=null) {
				timer.cancel();
				timer=null;				
			}
		}
		
	}
	public void stopShot(){
		if(timer!=null){
			timer.cancel();
			timer=null;
		}
	}

}

