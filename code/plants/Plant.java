package code.plants;
/*
 * ֲ���ս��ʬ
 * �����ˣ��γ�
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
	public int line;//��
	public int column;//��
	public int x;//x����
	public int y;//y����
	public int width;//��
	public int height;//��
	
	public int hp=ContextVar.normalPlantHp;//Ѫ��
	public GameBackground parent;//����
	public Image apperance;//���
	public boolean isLive=true;//�Ƿ���
	public boolean shotOnce=false;//���
	public boolean shotTwice =false;
	public boolean firstPlant=true;//�Ƿ��ǵ�һ�λ��ƣ��Ƿ��ǵ�һ�����£�
	public Date date1;//��������Ѫ�������Ƶ��
	public Date date2;
	/*
	 * ��ȡ���ο�
	 */
	public Rectangle getRect(){
		return new Rectangle(x,y,width,height);
	}
	/*
	 * ���췽����ʼ��
	 */
	public Plant(int x,int y,GameBackground parent,int line,int column){
		this.x=x;
		this.y=y;
		this.line=line;
		this.parent=parent;
		this.column=column;
		date1=new Date();//��ʼ����ʼʱ��
		
		new Sound("music/plant_plant.mp3").play();	
		//������������ʱ��
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
			firstPlant=false;	
			
			}
		}, 650);
	}
	/*
	 * ����ֲ��
	 */
	public void drawPlant(Graphics graphics){
		//������Ӱ
		graphics.drawImage(ImgSrc.plantShadow, x-width/7*2, y+height/10*7, width/2*3, width/2, parent);
		//����ֲ�����
		graphics.drawImage(apperance, x,y,width,height,parent);
		//����ǵ�һ�λ��ƣ���������Ч��
		if (firstPlant) {
			graphics.drawImage(ImgSrc.plantDirt, x, y+height/3*2, width, width/2, parent);
			
		}
		//��⽩ʬ
		zoombieCheck();
	}
	/*
	 * ��ʬ��ײ���
	 */
	public  void zoombieCheck() {
		//��⵽��ʬ
		for(int i=0;i<parent.zombie_list.size();i++){
			Zombie zoombie=parent.zombie_list.get(i);
			if (zoombie.line==this.line) {
				if (zoombie.getRect().intersects(this.getRect())) {
					/*
					 * ���ö�ʱ���������ʱ��֮��Ĳ�����1s���͵�Ѫ
					 */
					date2=new Date();
					if ((date2.getTime()-date1.getTime())/1000>=1) {
						hp--;
						if (hp<=0) {
							isLive=false;
							parent.plant_list.remove(Plant.this);
							parent.isHave[line][column]=false;
						}
						//��date1����Ϊdate2
						date1=date2;
					}
					//�ı���ۣ���������д
					changeStatus();//��⵽��ײ�ı����
				}
			}
		}

	}
	public void changeStatus() {
	}
	 
}
