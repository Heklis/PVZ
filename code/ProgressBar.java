package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
/**
 * 
 * @author AndyLau96
 *
 */
public class ProgressBar extends GameObject{

	private Image img_tip;
	private Image img_head;
	private int rectX=824;
	private int rectWidth=0;
	private int headX=810;
	
	public ProgressBar(int x, int y, int width, int height, JPanel parent) {
		super(x, y, width, height, parent);
		image=ImgSrc.IMG_PROGRESS_EMPTY;
		img_tip=ImgSrc.IMG_PROGRESS_TIP;
		img_head=ImgSrc.IMG_PROGRESS_HEAD;
	}
	/**
	 * 进度条前进
	 */
	public void moveProgress(){
		rectX-=6;  //产生一个僵尸 进度前进1/25*150=6
		rectWidth+=6;  //产生一个僵尸 进度条长度增加6
		
		headX-=6;
	}
	@Override
	public void onDraw(Graphics g) {
		//大框坐标  668 540 162 25
		//进度条满坐标 674 547 150 11
		g.setColor(Color.blue);
		g.drawImage(image, x, y, width,height,parent);  //画进度条大框
		g.fillRect(rectX, 547, rectWidth, 11);        //画进度
		g.drawImage(img_tip, 709, 555, 82,15,parent);  //画进度条提示
		g.drawImage(img_head, headX,540,23,23,parent); //画僵尸头
	}
	
}
