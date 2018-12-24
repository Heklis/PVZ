package code.zombies;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import code.ContextVar;
import code.GameBackground;
import code.ImgSrc;
import code.Sound;
import code.plants.Bean;
import code.plants.Plant;

public class Zombie {

	// ��ʬ��ʼ����ֵ
	static final int HP = ContextVar.HP_zombie;
	// ��ʬ�ٶ�
	static final double SPEED = ContextVar.SPEED_zombie;
	// �����ӳ�ʱ��
	static final int DELAY = ContextVar.DELAY;
	//������
	static final int ATTACK = ContextVar.ATTACK_zombie;
	// ������
	public int x;
	// ������
	public int y;
	// ����
	public int line;
	// ����
	public int column;
	// �ٶ�
	double speed;
	// ��ը����״̬
	public boolean boomDeath;
	// ��ը����״̬
	public boolean mowerDeath;
	// ��������״̬
	boolean attackDeath;
	// ��������״̬
	boolean walkDeath;
	// �ӳ�����״̬
	boolean delayDeath;
	// �ж�״̬
	boolean still;
	boolean beanDelay;
	// ����
	int hp;
	// �����ӳ�ʱ��
	int delay;
	//�㶹��ը�ӳ�ʱ��
	int beanDelayTime;
	// ��ʬ����ʱλ��
	int deathX;
	int deathY;
	// ��ʬ����ͼƬ
	Image walkImg;
	// ��ʬ����ͼƬ
	Image attackImg;
	//���
	GameBackground panel;
	//��ʱ��
	long timer;

	public Zombie(int x, int y, int line, GameBackground panel) {
		this.x = x;
		this.y = y;
		this.line = line;
		this.panel = panel;
		this.hp = HP;
		this.speed = SPEED;
		this.delay = DELAY;
		this.walkImg = ImgSrc.zombie;
		this.attackImg = ImgSrc.zombieAttack;
		this.beanDelayTime = 10;
	}
	public boolean isAlive(){
		return boomDeath||attackDeath||mowerDeath||walkDeath;
	}
	/**
	 * ���ƽ�ʬ
	 */
	public void drawZombie(Graphics g) {
		// ����ʬ��ը����
		if (this.boomDeath) {
			this.boomDie(g);
			return;
		}
		// ����ʬ����ʱ����
		else if (this.attackDeath) {
			attackDie(g);
			return;
		}
		//����ʬ����ʱ����
		else if (this.walkDeath) {
			walkDie(g);
			return;
		}
		else if(this.mowerDeath){
			mowerDie(g);
			return;
		}

		// ��ȡֲ�Ｏ��
		List<Plant> plantList = panel.plant_list;
		// �ж��Ƿ��ֲ����ײ
		this.still = false;
		for (int i = 0; i < plantList.size(); i++) {
			Plant plant = plantList.get(i);
			// �ж��Ƿ���ͬһ��
			if (this.line != plant.line)
				continue;
			if (this.getRect().intersects(plant.getRect())) {
				if(this.x >= plant.x) {
					attack(g);
					this.still = true;
				}
			}
		}
		// ����
		if (!this.still)
			walk(g);
		//�������ѵ��㶹
		if(this.beanDelay) {
			this.beanDelayTime--;
			if(this.beanDelayTime <= 0) {
				this.beanDelay = false;
				this.beanDelayTime = 10;
			}
			Image img = ImgSrc.IMG_PEABULLETHIT;
			g.drawImage(img, this.x+25, this.y + 65, img.getWidth(null),img.getHeight(null) ,panel);
		}
		// ��ȡ�㶹����
		List<Bean> beanList = panel.bean_list;

		// �ж��Ƿ���㶹��ײ
		for (int i = 0; i < beanList.size(); i++) {
			Bean bean = beanList.get(i);
			// �ж��Ƿ���ͬһ��
			if (this.line != bean.line)
				continue;
			if (this.getRect().intersects(bean.getRect())) {
				this.beanDelay = true;
				attacked(g);
				beanList.remove(bean);
			}
		}
		//��ʱ����ʱ
		this.timer++;
		
	}

	/**
	 * ��ʬ����
	 */
	public void walk(Graphics g) {
		g.drawImage(this.walkImg, this.x, this.y, walkImg.getWidth(null), walkImg.getHeight(null), null);
		if(this.timer % 4 == 0)
			this.x -= this.speed;
	}

	/**
	 * ��ʬ����
	 */
	public void attack(Graphics g) {
		if(this.timer % 50 == 0)
			new Sound("./music/zombie_chew.mp3").play();
		g.drawImage(this.attackImg, x, y, attackImg.getWidth(null), attackImg.getHeight(null), null);
	}

	/**
	 * ��ʬ�ܵ�����
	 */
	public void attacked(Graphics g) {
		// hp��Ϊ0����ʬ����
		this.hp--;
		if (this.hp <= 0) {
			//����ͷ��ص�����
			new Sound("./music/zombie_falling.mp3").play();
			if (this.still)
				this.attackDeath = true;
			else
				this.walkDeath = true;
		}
	}

	/**
	 * ��ʬ����ʱ����
	 */
	public void walkDie(Graphics g) {
		//ȷ������λ��
		if (!delayDeath) {
			this.deathX = this.x;
			this.deathY = this.y - 20;
			this.delayDeath = true;
		}
		if (delay > 0 && delay <= 100) {
			// ���Ƶ��صĹ���
			Image img = ImgSrc.zombieDie;
			g.drawImage(img, x-70, y, img.getWidth(null), img.getHeight(null), null);
			Image img1 = ImgSrc.zombieHead;
			g.drawImage(img1, this.deathX, this.deathY, img1.getWidth(null), img1.getHeight(null),
					null);
			delay--;
			return;
		} else if (delay <= 0) {
			panel.zombie_list.remove(this);
			return;
		}
		// ���Ƶ����ͷ
		Image img = ImgSrc.zombieHead;
		g.drawImage(img, this.deathX, this.deathY, img.getWidth(null), img.getHeight(null), null);
		// ���Ƶ���ͷ�����ߵ�����
		Image img1 = ImgSrc.zombieLostHead;
		g.drawImage(img1, this.x, this.y, img1.getWidth(null), img1.getHeight(null), null);
		if(this.delay % 4 == 0)
			this.x -= this.speed;
		delay--;

	}

	/**
	 * ��ʬ����ʱ����
	 */
	public void attackDie(Graphics g) {
		delay--;
		if (delay <= 0) {
			panel.zombie_list.remove(this);
			return;
		}
		Image img = ImgSrc.zombieLostHeadAttack;
		g.drawImage(img, this.x, this.y, img.getWidth(null), img.getHeight(null), null);
		Image img1 = ImgSrc.zombieHead;
		g.drawImage(img1, this.x, this.y, img1.getWidth(null), img1.getHeight(null), null);
	}

	/**
	 * ��ʬ��ը����
	 */
	public void boomDie(Graphics g) {
		if (delay <= 0) {
			panel.zombie_list.remove(this);
			return;
		}
		delay--;
		Image img = ImgSrc.boomDie;
		g.drawImage(img, this.x, this.y, img.getWidth(null), img.getHeight(null), null);
	}
	
	/**
	 * ��ʬ����ݻ�����
	 */
	public void mowerDie(Graphics g) {
		if (delay <= 0) {
			panel.zombie_list.remove(this);
			System.out.println("ha");
			return;
		}
		delay--;
		// ���Ƶ����ͷ
		Image img = ImgSrc.zombieHead;
		g.drawImage(img, x, this.y, img.getWidth(null), img.getHeight(null), null);
		Image img1 = ImgSrc.zombieDie;
		g.drawImage(img1, x-70, y, img1.getWidth(null), img1.getHeight(null), null);
	}

	public Rectangle getRect() {
		return new Rectangle(this.x+25, this.y, walkImg.getWidth(null), walkImg.getHeight(null));
	}
}
