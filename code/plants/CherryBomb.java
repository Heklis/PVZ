package code.plants;
/*
 * 植物大战僵尸
 * 制作人：任超
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
		//获取图片和宽高
		this.apperance =ImgSrc.cherryBomb;
		ImageIcon imageIcon=new ImageIcon("./src/images/cherryBoom.gif");
		this.width=imageIcon.getIconWidth()/4*3;
		this.height=imageIcon.getIconHeight()/4*3;
		boom();//爆炸
	}
	/*
	 * 在爆炸里面检测僵尸，如果检测到僵尸就炸死
	 */
	private void boom() {
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				//设置爆炸图片
				CherryBomb.this.apperance=ImgSrc.boom;
				new Sound("music/plant_cherrybomb.mp3").play();
				ImageIcon imageIcon=new ImageIcon("./src/images/boom.gif");
				width=imageIcon.getIconWidth()/4*3;
				height=imageIcon.getIconHeight()/4*3;
				x-=width/3;
				y-=height/3;
				//遍历僵尸集合，杀死九格的僵尸
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
		//设置爆炸图片的播放时间
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
