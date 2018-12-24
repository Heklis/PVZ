package code.plants;
/*
 * ֲ���ս��ʬ
 * �����ˣ��γ�
 */
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import code.ContextVar;
import code.GameBackground;
import code.ImgSrc;
import code.Sound;
import code.zombies.Zombie;

public class CherryBomb extends Plant{
	public CherryBomb(int x, int y, GameBackground gameStart,int line,int column) {
		super(x-12, y, gameStart, line,column);
		//��ȡͼƬ�Ϳ��
		this.apperance =ImgSrc.cherryBomb;
		ImageIcon imageIcon=new ImageIcon("./src/images/cherryBoom.gif");
		this.width=imageIcon.getIconWidth()/4*3;
		this.height=imageIcon.getIconHeight()/4*3;
		boom();//��ը
	}
	/*
	 * �ڱ�ը�����⽩ʬ�������⵽��ʬ��ը��
	 */
	private void boom() {
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				//���ñ�ըͼƬ
				CherryBomb.this.apperance=ImgSrc.boom;
				new Sound("music/plant_cherrybomb.mp3").play();
				ImageIcon imageIcon=new ImageIcon("./src/images/boom.gif");
				width=imageIcon.getIconWidth()/4*3;
				height=imageIcon.getIconHeight()/4*3;
				x-=width/3;
				y-=height/3;
				//������ʬ���ϣ�ɱ���Ÿ�Ľ�ʬ
				for (int i = 0; i < parent.zombie_list.size(); i++) {
					Zombie zombie=parent.zombie_list.get(i);
					System.out.println(zombie.line+" "+zombie.column+" "+line+" "+column);
					if (zombie.line==(line+1)||zombie.line==line||zombie.line==(line-1)) {
						if (zombie.column==column||zombie.column==column+1||zombie.column==column-1) {
							parent.zombie_list.get(i).boomDeath=true;
						}
					}			
				}
			}
		}, ContextVar.cherryGrowTime);
		//���ñ�ըͼƬ�Ĳ���ʱ��
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				parent.plant_list.remove(CherryBomb.this);
				parent.isHave[line][column]=false;
				isLive=false;
			}
		}, ContextVar.cherryBoomDisTime);
	}

}
