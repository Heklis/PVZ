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
/*
 * 注释详见单豌豆射手（beanshotter）
 */
public class DoubleBeanShotter extends Plant {
	boolean shouldShot = false;

	public DoubleBeanShotter(int x, int y, GameBackground gameStart, int line,
			int column) {
		super(x, y, gameStart, line, column);
		this.shotTwice = true;
		ImageIcon imageIcon = new ImageIcon(
				"./src/images/double_bean_shooter.gif");
		this.apperance = ImgSrc.doubleBeanShotter;
		this.width = imageIcon.getIconWidth();
		this.height = imageIcon.getIconHeight();
	}

	@Override
	public void drawPlant(Graphics graphics) {
		super.drawPlant(graphics);
		if (isLive) {
			shot();
		} else {
			shouldShot = false;
			if (timer != null) {
				timer.cancel();
				timer = null;
			}
		}
	}

	Timer timer = null;

	public void shot() {

		boolean shouldShot = false;
		for (int i = 0; i < parent.zombie_list.size(); i++) {
			if (parent.zombie_list.get(i).line == this.line
					&& parent.zombie_list.get(i).x > this.x&&parent.zombie_list.get(i).x<=850) {
				shouldShot = true;
				if (timer == null) {
					timer = new Timer();
					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							parent.bean_list.add(new Bean(x + width / 2, y + 5,
									parent, line, column));
							new Sound("music/plant_shoot.mp3").play();

							//发射第二枚子弹
							new Timer().schedule(new TimerTask() {

								@Override
								public void run() {
									parent.bean_list.add(new Bean(
											x + width / 2, y + 5, parent, line,
											column));
									new Sound("music/plant_shoot.mp3").play();

								}
							}, 500);
						}
					}, 1000, 2000);
				} else {
					break;
				}
			}
		}
		if (!shouldShot) {
			if (timer != null) {
				timer.cancel();
				timer = null;
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
