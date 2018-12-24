package code.plants;
/*
 * ֲ���ս��ʬ
 * �����ˣ��γ�
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import code.GameBackground;
import code.ImgSrc;

public class Bean {
	int x;//x����
	int y;//y����
	int width;//��
	int height;//��
	Image apperance;//��ʾ��ͼƬ���
	Image shadow;//��Ӱ
	GameBackground parent;//����
	boolean isLive = true;//�Ƿ���
	public int line;//��
	public int column;//��
	public int attack=10;//������
	/*
	 * ��ʼ�����췽��
	 */
	public Bean(int x, int y, GameBackground parent, int line, int column) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.line = line;
		this.column = column;

		ImageIcon imageIcon = new ImageIcon("./src/images/bean.png");
		//��ȡͼƬ�Լ���͸�
		this.apperance = ImgSrc.normalBean;
		this.shadow = ImgSrc.plantShadow;
		this.width = imageIcon.getIconWidth();
		this.height = imageIcon.getIconHeight();
	}
	/*
	 * ������
	 */
	public void drawBean(Graphics graphics) {
		graphics.drawImage(apperance, x, y, width, height, parent);
		//����Ӱ
		graphics.drawImage(shadow, x, y + height * 2, width, height/2, parent);

		x += 5;
		//���x�����߽磬������Ϊ�����
		if (x > parent.width) {
			this.isLive = false;
			parent.bean_list.remove(this);
		}
	}
	//�õ�����
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}
