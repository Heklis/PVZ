package code;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
/**
 * 
 * @author AndyLau96
 *	所有需要用图片画出来的对象的父类
 */
public class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected JPanel parent;
	protected Image image;
	
	public GameObject(int x, int y, int width, int height, JPanel parent) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.parent = parent;
	}
	
	public void onDraw(Graphics g){
		g.drawImage(image, x, y, width,height,parent);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
