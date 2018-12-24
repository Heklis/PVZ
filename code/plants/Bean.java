package code.plants;
/*
 * 植物大战僵尸
 * 制作人：任超
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import code.GameBackground;
import code.ImgSrc;

public class Bean {
	int x;//x坐标
	int y;//y坐标
	int width;//宽
	int height;//高
	Image apperance;//显示的图片外观
	Image shadow;//阴影
	GameBackground parent;//引用
	boolean isLive = true;//是否存活
	public int line;//行
	public int column;//列
	public int attack=10;//攻击力
	/*
	 * 初始化构造方法
	 */
	public Bean(int x, int y, GameBackground parent, int line, int column) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.line = line;
		this.column = column;

		ImageIcon imageIcon = new ImageIcon("./src/images/bean.png");
		//获取图片以及宽和高
		this.apperance = ImgSrc.normalBean;
		this.shadow = ImgSrc.plantShadow;
		this.width = imageIcon.getIconWidth();
		this.height = imageIcon.getIconHeight();
	}
	/*
	 * 画豆子
	 */
	public void drawBean(Graphics graphics) {
		graphics.drawImage(apperance, x, y, width, height, parent);
		//画阴影
		graphics.drawImage(shadow, x, y + height * 2, width, height/2, parent);

		x += 5;
		//如果x超出边界，就设置为不存活
		if (x > parent.width) {
			this.isLive = false;
			parent.bean_list.remove(this);
		}
	}
	//得到矩形
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}
