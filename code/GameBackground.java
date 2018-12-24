package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import javax.swing.JPanel;

import code.plants.Bean;
import code.plants.BeanShotter;
import code.plants.CherryBomb;
import code.plants.DoubleBeanShotter;
import code.plants.Plant;
import code.plants.Potato;
import code.plants.SunFlower;
import code.plants.WallNut;
import code.zombies.BucketHeadZombie;
import code.zombies.ConeHeadZombie;
import code.zombies.Zombie;

/**
 * 
 * @author AndyLau96 植物坐标 第一行第一列110,80 长宽70 列宽81 行高98 阴影植物坐标 第一个 120 间隔55 长宽70
 *         第一个草坪矩形 100,70,81,98 第一行僵尸 y=20
 */
public class GameBackground extends JPanel implements Runnable, MouseListener, MouseMotionListener {

	private int x;
	private int y;
	public int width;
	public int height;
	public boolean isHave[][];
	private int zombie_batch; // 第几波僵尸
	private GameStart tf; // 父窗体
	private Thread bgThread; // 线程
	private boolean drawBack = false; // 是否开始画各种框
	private boolean useShovel = false; // 是否点击了铁铲
	private boolean useCard = false; // 是否点击了卡片

	private Image img_background; // 背景图image
	private PlantBack plant_back; // 左上植物框

	private ShovelBack shovel_back; // 铁铲框
	private Shovel shovel; // 铁铲
	private ProgressBar progress_bar;// 进度条
	private RealShovel real_shovel; // 大铁铲
	private ShadowPlant shadow_plant;

	public List<LawnMower> lawn_mower_list; // 铲车集合
	public List<Card> card_list; // 植物卡片集合
	public List<Plant> plant_list; // 植物集合
	public List<Zombie> zombie_list;// 僵尸集合
	public List<Bean> bean_list; // 子弹集合
	public List<Sun> sun_list; // 阳光集合
	public List<StandZombie> stand_zombie_list; // 罚站僵尸集合

	public End_view end_view;
	public int cardID = -1;

	private SunTask sun_task;
	private int zombieCount = 25;
	private int isHaveMower[];
	private int isWin = -1;

	public GameBackground(int x, int y, int width, int height, GameStart tf) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tf = tf;

		ContextVar.initRectangle();
		init();
		bgThread = new Thread(this); // 开线程
		bgThread.start();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void init() {
		img_background = ImgSrc.IMG_GAME_BACKGROUND;// 初始化背景图
		plant_back = new PlantBack(50, 0, 430, 80, this); // 初始化左上植物框
		shovel_back = new ShovelBack(480, 0, 60, 60, this); // 初始化铁铲框
		shovel = new Shovel(480, 0, 55, 55, this); // 初始化铁铲
		progress_bar = new ProgressBar(668, 540, 162, 25, this); // 初始化进度条
		real_shovel = new RealShovel(480, 0, 55, 55, this); // 初始化铁锹
		end_view = new End_view(this); // 初始化结束界面
		card_list = new ArrayList<Card>(); // 初始化卡片集合
		lawn_mower_list = new ArrayList<LawnMower>(); // 初始化铲车集合
		plant_list = new ArrayList<Plant>(); // 初始化植物集合
		zombie_list = new ArrayList<Zombie>(); // 初始化僵尸集合
		bean_list = new ArrayList<Bean>(); // 初始化豌豆集合
		sun_list = new ArrayList<Sun>(); // 初始化阳光集合
		zombie_batch = 1; // 初始化僵尸波数为1
		stand_zombie_list = new ArrayList<StandZombie>(); // 初始化罚站僵尸
		isHave = new boolean[5][9];
		isHaveMower = new int[5];
		for (int i = 0; i < 5; i++) {
			isHaveMower[i] = 1;
		}
		/**
		 * 初始化卡片
		 */
		int cardX = 120;
		for (int i = 0; i < 6; i++) {
			card_list.add(new Card(i, cardX, 5));
			cardX += 55;
		}

		/**
		 * 初始化铲车
		 */
		int lx = 190; // 铲车x坐标
		int ly = 104; // 第一辆铲车y坐标
		int lw = 60; // 铲车宽度
		int lh = 30; // 铲车高度
		for (int i = 0; i < 5; i++) {
			lawn_mower_list.add(new LawnMower(lx, ly, lw, lh, this));
			ly += 98; // 每辆铲车之间间隔
		}
		/**
		 * 初始化罚站僵尸
		 */
		stand_zombie_list.add(new StandZombie(1100, 50, 110, 140, this, 2));
		stand_zombie_list.add(new StandZombie(1120, 150, 110, 140, this, 1));
		stand_zombie_list.add(new StandZombie(1150, 200, 110, 140, this, 1));
		stand_zombie_list.add(new StandZombie(1140, 300, 110, 140, this, 2));
		stand_zombie_list.add(new StandZombie(1120, 400, 110, 140, this, 3));
		stand_zombie_list.add(new StandZombie(1200, 120, 110, 140, this, 3));
		stand_zombie_list.add(new StandZombie(1180, 150, 110, 140, this, 1));
		/**
		 * 开启阳光任务
		 */
		sun_task = new SunTask(this);
		Timer timer = new Timer();
		timer.schedule(sun_task, 8000, 8000);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mX = e.getX(); // 获取鼠标点击处坐标
		int mY = e.getY();
		Point point = new Point(mX, mY); // 获取点击点
		/**
		 * 结束界面点击监听
		 */
		if (isWin == 0) {
			Rectangle rect = new Rectangle(347, 376, 202, 39);
			if (rect.contains(point)) {
				System.exit(0);
			}
		} else if (isWin == 1) {
			Rectangle rect = new Rectangle(363, 3450, 174, 40);
			if (rect.contains(point)) {
				System.exit(0);
			}
		}
		for (Card card : card_list) {
			if (card.getRectangle().contains(point)) { // 判断是否点击了卡片
				if (card.isCanUse) {
					useCard = !useCard;
					cardID = card.id;
					useShovel = false;
					shadow_plant = new ShadowPlant(120 + card.id * 55, 5, 70, 70, this, card.id); // 初始化阴影植物
				}
				return;
			}
		}
		// 判断是否点击了铁铲
		if (shovel_back.getRectangle().contains(point)) {
			useShovel = !useShovel;
			useCard = false;
			return;
		}
		// 铲除植物
		if (useShovel) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 9; j++) {
					if (ContextVar.grassRect[i][j].contains(point)) {
						for (int k = 0; k < plant_list.size(); k++) {
							if (plant_list.get(k).column == j && plant_list.get(k).line == i) {
								if (plant_list.get(k).shotOnce) {
									BeanShotter beanShotter = (BeanShotter) plant_list.get(k);
									beanShotter.stopShot();
								} else if (plant_list.get(k).shotTwice) {
									DoubleBeanShotter beanShotter = (DoubleBeanShotter) plant_list.get(k);
									beanShotter.stopShot();
								}
								plant_list.get(k).isLive = false;
								plant_list.remove(k);
								isHave[i][j] = false;
								useShovel = !useShovel;
							}
						}
					}
				}
			}
		}
		/**
		 * 点击阳光后进行收集
		 */
		if (!sun_list.isEmpty()) {
			for (int i = 0; i < sun_list.size(); i++) {
				Sun sun = sun_list.get(i);
				if (sun.getRect().contains(point)) {
					sun.setSlope();
					sun.isClick = true;
					new Sound("music/collectSun.mp3").play();
				}
			}
		}

		/**
		 * 若点击了卡片之后再发生点击事件 则判断是否点击了草坪 若点击草坪 则进行植物安放
		 */
		if (useCard) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 9; j++) {
					if (ContextVar.grassRect[i][j].contains(point)) {
						if (!isHave[i][j]) {
							isHave[i][j] = true;
							card_list.get(cardID).startCoolDown();
							switch (cardID) {
							case 0:
								plant_list.add(new SunFlower((int) ContextVar.plantRect[i][j].getX(),
										(int) ContextVar.plantRect[i][j].getY(), this, i, j));
								plant_back.setSunNum(plant_back.getSunNum() - card_list.get(cardID).needSunNumber);
								useCard = !useCard;
								break;
							case 1:
								plant_list.add(new BeanShotter((int) ContextVar.plantRect[i][j].getX(),
										(int) ContextVar.plantRect[i][j].getY(), this, i, j));
								plant_back.setSunNum(plant_back.getSunNum() - card_list.get(cardID).needSunNumber);
								useCard = !useCard;
								break;
							case 2:
								plant_list.add(new Potato((int) ContextVar.plantRect[i][j].getX() + 10,
										(int) ContextVar.plantRect[i][j].getY() + 20, this, i, j));
								plant_back.setSunNum(plant_back.getSunNum() - card_list.get(cardID).needSunNumber);
								useCard = !useCard;
								break;
							case 3:
								plant_list.add(new WallNut((int) ContextVar.plantRect[i][j].getX(),
										(int) ContextVar.plantRect[i][j].getY(), this, i, j));
								plant_back.setSunNum(plant_back.getSunNum() - card_list.get(cardID).needSunNumber);
								useCard = !useCard;
								break;
							case 4:
								plant_list.add(new DoubleBeanShotter((int) ContextVar.plantRect[i][j].getX(),
										(int) ContextVar.plantRect[i][j].getY(), this, i, j));
								plant_back.setSunNum(plant_back.getSunNum() - card_list.get(cardID).needSunNumber);
								useCard = !useCard;
								break;
							case 5:
								plant_list.add(new CherryBomb((int) ContextVar.plantRect[i][j].getX(),
										(int) ContextVar.plantRect[i][j].getY(), this, i, j));
								plant_back.setSunNum(plant_back.getSunNum() - card_list.get(cardID).needSunNumber);
								useCard = !useCard;
								break;
							}
						}
					}
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void run() {

		moveBg(); // 开场移动

		/**
		 * 开始死循环不断刷新
		 */
		int t = 0;
		int k;
		Random rand = new Random();
		k = Math.abs(rand.nextInt(400) + 50);
		while (true) {
			this.repaint();
			try {
				t++;
				if (t % k == 0) {
					if (zombieCount > 20 && zombieCount <= 25) { // 前10个僵尸为第一波
						productZombie(1);
						t = 0;
						k = Math.abs(rand.nextInt(300) + 50);
					} else if (zombieCount > 15 && zombieCount <= 20) {
						t = 0;
						k = Math.abs(rand.nextInt(300) + 50);
						switch (zombieCount) {
						case 20:
							productZombie(1);
							break;
						case 19:
							productZombie(1);
							break;
						case 18:
							productConeHeadZombie(1);
							break;
						case 17:
							productConeHeadZombie(1);
							break;
						case 16:
							productBucketHeadZombie(1);
							break;

						}
					} else if(zombieCount>0&&zombieCount<=15){ // 后15个为第二波
						if (t % k == 0) {
							t = 0;
							k = Math.abs(rand.nextInt(200) + 50);
							productZombie(2);
							productBucketHeadZombie(2);
							productConeHeadZombie(1);
						}
					}
				}
				for (int i = 0; i < zombie_list.size(); i++) {
					Zombie zombie = zombie_list.get(i);
					LawnMower la=lawn_mower_list.get(zombie.line);
					if(zombie.getRect().intersects(la.getRectangle())){
						zombie.mowerDeath=true;
					}
					if(zombie.x<=100){
						if(isHaveMower[zombie.line]<0&&!zombie.mowerDeath)
						{
							isWin=0;
						}
						isHaveMower[zombie.line]--;
						la.isMoving=true;
					}
					
				}

				for (int i = 0; i < lawn_mower_list.size(); i++) {
					if (lawn_mower_list.get(i).isMoving)
						lawn_mower_list.get(i).move();
				}
				bgThread.sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

	}

	public void productZombie(int num) {
		Random rand = new Random();
		int r;
		for (int i = 0; i < num; i++) {
			r = Math.abs(rand.nextInt()) % 5;
			zombie_list.add(new Zombie(1000, ContextVar.zombieFirstLineY + r * 98, r, this));
			progress_bar.moveProgress();
		}
		zombieCount -= num;
	}

	public void productBucketHeadZombie(int num) {
		Random rand = new Random();
		int r;
		for (int i = 0; i < num; i++) {
			r = Math.abs(rand.nextInt()) % 5;
			zombie_list.add(new BucketHeadZombie(1000, ContextVar.zombieFirstLineY + r * 98, r, this));
			progress_bar.moveProgress();
		}
		zombieCount -= num;
	}

	public void productConeHeadZombie(int num) {
		Random rand = new Random();
		int r;
		for (int i = 0; i < num; i++) {
			r = Math.abs(rand.nextInt()) % 5;
			zombie_list.add(new ConeHeadZombie(1000, ContextVar.zombieFirstLineY + r * 98, r, this));
			progress_bar.moveProgress();
		}
		zombieCount -= num;
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img_background, x, y, width, height, this); // 画背景图

		for (int i = 0; i < lawn_mower_list.size(); i++) {
			lawn_mower_list.get(i).onDraw(g); // 画铲车
		}

		if (drawBack) { // 当背景图和铲车移动完毕 画出其余工具
			plant_back.onDraw(g); // 画植物框
			shovel_back.onDraw(g); // 画铁铲框
			progress_bar.onDraw(g); // 画进度条
			/**
			 * 画铁铲
			 */
			if (!useShovel) {
				shovel.onDraw(g);
			} else {
				real_shovel.onDraw(g);
			}
			/**
			 * 画卡片
			 */
			for (int i = 0; i < card_list.size(); i++) {
				card_list.get(i).drawImage(g, this, plant_back.getSunNum());
			}

			// 子弹
			for (int i = 0; i < bean_list.size(); i++) {
				bean_list.get(i).drawBean(g);
			}
			/**
			 * 画植物
			 */
			if (!plant_list.isEmpty()) {
				for (int i = 0; i < plant_list.size(); i++) {
					plant_list.get(i).drawPlant(g);
				}
			}
			// 如果已点击卡片 则画出未安放的阴影植物图片
			if (useCard) {
				shadow_plant.onDraw(g);
			}

		} else {
			for (int i = 0; i < stand_zombie_list.size(); i++) {
				stand_zombie_list.get(i).onDraw(g);
			}
		}
		/**
		 * 画僵尸
		 */
		for (int i = 0; i < zombie_list.size(); i++) {
			Zombie zombie = zombie_list.get(i);
			zombie.drawZombie(g);
			int zX = zombie.x - 5 - 100;
			int t = zX / 81;
			zombie.column = t;
		}

		/**
		 * 画阳光
		 */
		for (int i = 0; i < sun_list.size(); i++) {
			if (sun_list.get(i).isLive)
				sun_list.get(i).drawImage(g);
			else {
				if (sun_list.get(i).isClick) {
					plant_back.setSunNum(plant_back.getSunNum() + 25);
				}
				sun_list.remove(i);
			}

		}
		/**
		 * 结束界面
		 */
		if (isWin == 0) {
			end_view.Draw_end_view(g, 0);

		} else {
			if (zombieCount == 0 && zombie_list.isEmpty()) {
				end_view.Draw_end_view(g, 1);
			}
		}
		if (end_view.isover) {
			bgThread.stop();
		}
	}

	/**
	 * 背景图片、铲车开场移动
	 */
	public void moveBg() {
		// 113次循环
		while (x > -450) { // 初始X为0，图片左移450 显示街道
			for (int i = 0; i < lawn_mower_list.size(); i++) {
				lawn_mower_list.get(i).setX(lawn_mower_list.get(i).getX() - 5);
			}
			for (int i = 0; i < stand_zombie_list.size(); i++) {
				stand_zombie_list.get(i).setX(stand_zombie_list.get(i).getX() - 5);
			}
			x -= 5; // 移动速度4
			this.repaint(); // 重绘
			try {
				bgThread.sleep(20); // 每20ms重绘一次
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}

		try {
			bgThread.sleep(1000); // 转到最右边之后暂停1秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 43次循环
		while (x < -150) { // 图片向右移动到-150
			for (int i = 0; i < lawn_mower_list.size(); i++) {
				lawn_mower_list.get(i).setX(lawn_mower_list.get(i).getX() + 5);
			}
			for (int i = 0; i < stand_zombie_list.size(); i++) {
				stand_zombie_list.get(i).setX(stand_zombie_list.get(i).getX() + 5);
			}
			x += 5;
			this.repaint();
			try {
				bgThread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		drawBack = true;
		this.repaint();

		/*
		 * for (int k = 0; k < 10; k++) { progress_bar.moveProgress(); try {
		 * bgThread.sleep(1000); } catch (InterruptedException e) { // TODO
		 * 自动生成的 catch 块 e.printStackTrace(); } this.repaint(); }
		 */
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (useShovel) {
			real_shovel.setX(e.getX() - 30);
			real_shovel.setY(e.getY() - 30);
		}
		if (useCard) {
			shadow_plant.setX(e.getX() - 30);
			shadow_plant.setY(e.getY() - 30);
		}
	}
}
