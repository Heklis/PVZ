package code.plants;
/*
 * ֲ���ս��ʬ
 * �����ˣ��γ�
 * ֲ�����
 */
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import code.ContextVar;
import code.GameBackground;
import code.ImgSrc;
import code.Sound;
import code.zombies.Zombie;

public class Potato extends Plant{
	//�Ƿ񳤳�����
	boolean growUp=false;
	/*
	 * ���췽����ʼ��
	 */
	public Potato(int x, int y, GameBackground gameStart,int line,int column) {
		super(x, y, gameStart,line,column);
		//��ۺͿ��
		this.apperance =ImgSrc.potatoNotReady;
		ImageIcon imageIcon=new ImageIcon("./src/images/potato_not_ready.gif");
		this.width=imageIcon.getIconWidth();
		this.height=imageIcon.getIconHeight();
		//��ʼ����
		grow();
	}
	/*
	 * ���ö�ʱ����
	 */
	private void grow() {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				Potato.this.apperance =ImgSrc.potatoReady;
				x-=12;
				y-=15;
				new Sound("music/plant_seedlift.mp3").play();
				growUp=true;
				ImageIcon imageIcon=new ImageIcon("./src/images/potato_ready.gif");
				width=imageIcon.getIconWidth();
				height=imageIcon.getIconHeight();
			}
		}, ContextVar.potatoGrowTime);
		
	}
	/*
	 * ��⵽��ײ����д�ķ�������������˾�ը����ʬ
	 * @see code.plants.Plant#changeStatus()
	 */
	@Override
	public void changeStatus() {
		if(growUp&&isLive){
			isLive=false;
			apperance=ImgSrc.potatoBoom;
			new Sound("music/plant_potato_boom.mp3").play();
			ImageIcon imageIcon=new ImageIcon("./src/images/potato_boom.png");
			this.width=imageIcon.getIconWidth();
			this.height=imageIcon.getIconHeight();
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run() {
					
					parent.plant_list.remove(Potato.this);
					parent.isHave[line][column]=false;
				}
			}, ContextVar.potatoBoomDisTime);
			for(int i=0;i<parent.zombie_list.size();i++){
				Zombie zoombie=parent.zombie_list.get(i);
				if (zoombie.line==this.line) {
					if (zoombie.getRect().intersects(this.getRect())) {
						parent.zombie_list.get(i).boomDeath=true;
					}
				}
			}

		}
		
	}
	
}
