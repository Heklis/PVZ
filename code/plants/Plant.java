package code.plants;
/*
 * 植物大战僵尸
 * 制作人：任超
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import code.ContextVar;
import code.GameBackground;
import code.ImgSrc;
import code.Sound;
import code.zombies.Zombie;

public class Plant{
	public int line;//行
	public int column;//列
	public int x;//x坐标
	public int y;//y坐标
	public int width;//宽
	public int height;//高
	
	public int hp=ContextVar.normalPlantHp;//血量
	public GameBackground parent;//引用
	public Image apperance;//外观
	public boolean isLive=true;//是否存活
	public boolean shotOnce=false;//射击
	public boolean shotTwice =false;
	public boolean firstPlant=true;//是否是第一次绘制（是否是第一次种下）
	public Date date1;//用来控制血量掉落的频率
	public Date date2;
	/*
	 * 获取矩形框
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,width,height);
	}
	/*
	 * 构造方法初始化
	 */
	public Plant(int x,int y,GameBackground parent,int line,int column){
		this.x=x;
		this.y=y;
		this.line=line;
		this.parent=parent;
		this.column=column;
		date1=new Date();//初始化开始时间
		
		new Sound("music/plant_plant.mp3").play();	
		//播放泥土动画时间
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
			firstPlant=false;	
			
			}
		}, 650);
	}
	/*
	 * 绘制植物
	 */
	public void drawPlant(Graphics graphics){
		//绘制阴影
		graphics.drawImage(ImgSrc.plantShadow, x-width/7*2, y+height/10*7, width/2*3, width/2, parent);
		//绘制植物外观
		graphics.drawImage(apperance, x,y,width,height,parent);
		//如果是第一次绘制，绘制泥土效果
		if (firstPlant) {
			graphics.drawImage(ImgSrc.plantDirt, x, y+height/3*2, width, width/2, parent);
			
		}
		//检测僵尸
		zoombieCheck();
	}
	/*
	 * 僵尸碰撞检测
	 */
	public  void zoombieCheck() {
		//检测到僵尸
		for(int i=0;i<parent.zombie_list.size();i++){
			Zombie zoombie=parent.zombie_list.get(i);
			if (zoombie.line==this.line) {
				if (zoombie.getRect().intersects(this.getRect())) {
					/*
					 * 设置定时器，如果定时器之间的差距大于1s，就掉血
					 */
					date2=new Date();
					if ((date2.getTime()-date1.getTime())/1000>=1) {
						hp--;
						if (hp<=0) {
							isLive=false;
							parent.plant_list.remove(Plant.this);
							parent.isHave[line][column]=false;
						}
						//将date1设置为date2
						date1=date2;
					}
					//改变外观，由子类重写
					changeStatus();//检测到碰撞改变外观
				}
			}
		}

	}
	public void changeStatus() {
	}
	 
}
