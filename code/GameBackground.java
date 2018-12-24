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
 * @author AndyLau96 ֲ������ ��һ�е�һ��110,80 ����70 �п�81 �и�98 ��Ӱֲ������ ��һ�� 120 ���55 ����70
 *         ��һ����ƺ���� 100,70,81,98 ��һ�н�ʬ y=20
 */
public class GameBackground extends JPanel implements Runnable, MouseListener, MouseMotionListener {

	private int x;
	private int y;
	public int width;
	public int height;
	public boolean isHave[][];
	private int zombie_batch; // �ڼ�����ʬ
	private GameStart tf; // ������
	private Thread bgThread; // �߳�
	private boolean drawBack = false; // �Ƿ�ʼ�����ֿ�
	private boolean useShovel = false; // �Ƿ���������
	private boolean useCard = false; // �Ƿ����˿�Ƭ

	private Image img_background; // ����ͼimage
	private PlantBack plant_back; // ����ֲ���

	private ShovelBack shovel_back; // ������
	private Shovel shovel; // ����
	private ProgressBar progress_bar;// ������
	private RealShovel real_shovel; // ������
	private ShadowPlant shadow_plant;

	public List<LawnMower> lawn_mower_list; // ��������
	public List<Card> card_list; // ֲ�￨Ƭ����
	public List<Plant> plant_list; // ֲ�Ｏ��
	public List<Zombie> zombie_list;// ��ʬ����
	public List<Bean> bean_list; // �ӵ�����
	public List<Sun> sun_list; // ���⼯��
	public List<StandZombie> stand_zombie_list; // ��վ��ʬ����

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
		bgThread = new Thread(this); // ���߳�
		bgThread.start();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void init() {
		img_background = ImgSrc.IMG_GAME_BACKGROUND;// ��ʼ������ͼ
		plant_back = new PlantBack(50, 0, 430, 80, this); // ��ʼ������ֲ���
		shovel_back = new ShovelBack(480, 0, 60, 60, this); // ��ʼ��������
		shovel = new Shovel(480, 0, 55, 55, this); // ��ʼ������
		progress_bar = new ProgressBar(668, 540, 162, 25, this); // ��ʼ��������
		real_shovel = new RealShovel(480, 0, 55, 55, this); // ��ʼ������
		end_view = new End_view(this); // ��ʼ����������
		card_list = new ArrayList<Card>(); // ��ʼ����Ƭ����
		lawn_mower_list = new ArrayList<LawnMower>(); // ��ʼ����������
		plant_list = new ArrayList<Plant>(); // ��ʼ��ֲ�Ｏ��
		zombie_list = new ArrayList<Zombie>(); // ��ʼ����ʬ����
		bean_list = new ArrayList<Bean>(); // ��ʼ���㶹����
		sun_list = new ArrayList<Sun>(); // ��ʼ�����⼯��
		zombie_batch = 1; // ��ʼ����ʬ����Ϊ1
		stand_zombie_list = new ArrayList<StandZombie>(); // ��ʼ����վ��ʬ
		isHave = new boolean[5][9];
		isHaveMower = new int[5];
		for (int i = 0; i < 5; i++) {
			isHaveMower[i] = 1;
		}
		/**
		 * ��ʼ����Ƭ
		 */
		int cardX = 120;
		for (int i = 0; i < 6; i++) {
			card_list.add(new Card(i, cardX, 5));
			cardX += 55;
		}

		/**
		 * ��ʼ������
		 */
		int lx = 190; // ����x����
		int ly = 104; // ��һ������y����
		int lw = 60; // �������
		int lh = 30; // �����߶�
		for (int i = 0; i < 5; i++) {
			lawn_mower_list.add(new LawnMower(lx, ly, lw, lh, this));
			ly += 98; // ÿ������֮����
		}
		/**
		 * ��ʼ����վ��ʬ
		 */
		stand_zombie_list.add(new StandZombie(1100, 50, 110, 140, this, 2));
		stand_zombie_list.add(new StandZombie(1120, 150, 110, 140, this, 1));
		stand_zombie_list.add(new StandZombie(1150, 200, 110, 140, this, 1));
		stand_zombie_list.add(new StandZombie(1140, 300, 110, 140, this, 2));
		stand_zombie_list.add(new StandZombie(1120, 400, 110, 140, this, 3));
		stand_zombie_list.add(new StandZombie(1200, 120, 110, 140, this, 3));
		stand_zombie_list.add(new StandZombie(1180, 150, 110, 140, this, 1));
		/**
		 * ������������
		 */
		sun_task = new SunTask(this);
		Timer timer = new Timer();
		timer.schedule(sun_task, 8000, 8000);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mX = e.getX(); // ��ȡ�����������
		int mY = e.getY();
		Point point = new Point(mX, mY); // ��ȡ�����
		/**
		 * ��������������
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
			if (card.getRectangle().contains(point)) { // �ж��Ƿ����˿�Ƭ
				if (card.isCanUse) {
					useCard = !useCard;
					cardID = card.id;
					useShovel = false;
					shadow_plant = new ShadowPlant(120 + card.id * 55, 5, 70, 70, this, card.id); // ��ʼ����Ӱֲ��
				}
				return;
			}
		}
		// �ж��Ƿ���������
		if (shovel_back.getRectangle().contains(point)) {
			useShovel = !useShovel;
			useCard = false;
			return;
		}
		// ����ֲ��
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
		 * ������������ռ�
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
		 * ������˿�Ƭ֮���ٷ�������¼� ���ж��Ƿ����˲�ƺ �������ƺ �����ֲ�ﰲ��
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
		// TODO �Զ����ɵķ������

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

		moveBg(); // �����ƶ�

		/**
		 * ��ʼ��ѭ������ˢ��
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
					if (zombieCount > 20 && zombieCount <= 25) { // ǰ10����ʬΪ��һ��
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
					} else if(zombieCount>0&&zombieCount<=15){ // ��15��Ϊ�ڶ���
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
				// TODO �Զ����ɵ� catch ��
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
		g.drawImage(img_background, x, y, width, height, this); // ������ͼ

		for (int i = 0; i < lawn_mower_list.size(); i++) {
			lawn_mower_list.get(i).onDraw(g); // ������
		}

		if (drawBack) { // ������ͼ�Ͳ����ƶ���� �������๤��
			plant_back.onDraw(g); // ��ֲ���
			shovel_back.onDraw(g); // ��������
			progress_bar.onDraw(g); // ��������
			/**
			 * ������
			 */
			if (!useShovel) {
				shovel.onDraw(g);
			} else {
				real_shovel.onDraw(g);
			}
			/**
			 * ����Ƭ
			 */
			for (int i = 0; i < card_list.size(); i++) {
				card_list.get(i).drawImage(g, this, plant_back.getSunNum());
			}

			// �ӵ�
			for (int i = 0; i < bean_list.size(); i++) {
				bean_list.get(i).drawBean(g);
			}
			/**
			 * ��ֲ��
			 */
			if (!plant_list.isEmpty()) {
				for (int i = 0; i < plant_list.size(); i++) {
					plant_list.get(i).drawPlant(g);
				}
			}
			// ����ѵ����Ƭ �򻭳�δ���ŵ���Ӱֲ��ͼƬ
			if (useCard) {
				shadow_plant.onDraw(g);
			}

		} else {
			for (int i = 0; i < stand_zombie_list.size(); i++) {
				stand_zombie_list.get(i).onDraw(g);
			}
		}
		/**
		 * ����ʬ
		 */
		for (int i = 0; i < zombie_list.size(); i++) {
			Zombie zombie = zombie_list.get(i);
			zombie.drawZombie(g);
			int zX = zombie.x - 5 - 100;
			int t = zX / 81;
			zombie.column = t;
		}

		/**
		 * ������
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
		 * ��������
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
	 * ����ͼƬ�����������ƶ�
	 */
	public void moveBg() {
		// 113��ѭ��
		while (x > -450) { // ��ʼXΪ0��ͼƬ����450 ��ʾ�ֵ�
			for (int i = 0; i < lawn_mower_list.size(); i++) {
				lawn_mower_list.get(i).setX(lawn_mower_list.get(i).getX() - 5);
			}
			for (int i = 0; i < stand_zombie_list.size(); i++) {
				stand_zombie_list.get(i).setX(stand_zombie_list.get(i).getX() - 5);
			}
			x -= 5; // �ƶ��ٶ�4
			this.repaint(); // �ػ�
			try {
				bgThread.sleep(20); // ÿ20ms�ػ�һ��
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}

		try {
			bgThread.sleep(1000); // ת�����ұ�֮����ͣ1��
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 43��ѭ��
		while (x < -150) { // ͼƬ�����ƶ���-150
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
		 * �Զ����ɵ� catch �� e.printStackTrace(); } this.repaint(); }
		 */
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO �Զ����ɵķ������

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
