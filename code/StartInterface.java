package code;

import java.awt.Color;
import java.awt.Frame;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/**
 * 
 * @author 丁相国 7-20
 * 
 */
public class StartInterface extends JPanel {

	// 定义宽高
	int width = 900;
	int height = 600;
	// 图标加载页面
	IconPanel iconPanel;
	// 游戏加载页面
	LoadPanel loadPanel;
	// 开始选择页面
	StartPanel startPanel;
	//持有控制类引用
	GameStart gs;
	//背景音乐引用 便于结束
	Sound backMusic = new Sound("music/Faster.mp3");
	
	//初始化
	public StartInterface(GameStart gs) {
		this.gs = gs;

	}

	// 开始绘制画面
	public void startPaint() {
		
		//设置标题栏透明

		
		// 改变java图标
		gs.setIconImage(ImgSrc.dlogo);

		//设置背景色
		gs.setBackground(Color.black);
		
		// 加载页面一
		iconPanel = new IconPanel();
		gs.add(iconPanel);
		backMusic.playloop();//背景音乐开始
		gs.revalidate();
		gs.validate();
		gs.repaint();
		Date startDate = new Date();
		while (new Date().getTime() - startDate.getTime() < 3000) {//页面一时间
			iconPanel.repaint();
		}


		// 加载页面二
		loadPanel = new LoadPanel();
		loadPanel.setBounds(0, 0, width, height);
		gs.add(loadPanel,0);
		gs.revalidate();
		gs.validate();
		gs.remove(iconPanel);
		startDate = new Date();
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				new Sound("music/groan1.mp3").play();
			}
		}, 1200);
		while (new Date().getTime() - startDate.getTime() < 3000) {//页面时间
			loadPanel.repaint();
			
		}


		// 加载页面三

		startPanel = new StartPanel(gs);
		gs.add(startPanel,0);
		gs.remove(loadPanel);
		gs.repaint();
		startPanel.startThread();
		gs.revalidate();
		
		
		
	}

	
}
