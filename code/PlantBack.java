package code;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * 
 * @author AndyLau96
 *  ���Ͻ�ͼƬ��
 */
public class PlantBack extends GameObject{

	private int sunNum=10000;  //��������
	public PlantBack(int x, int y, int width, int height, JPanel parent) {
		super(x,y,width,height,parent);
		image=ImgSrc.IMG_PLANT_BACK;
	}
	
	public int getSunNum(){
		return sunNum;
	}
	
	public void setSunNum(int num){
		this.sunNum=num;
	}
	@Override
	public void onDraw(Graphics g) {
		//ֲ������� 50, 0, 430, 70
		super.onDraw(g);
		g.setColor(Color.black);
		g.drawString(sunNum+"",80,72);  //��������
		
	}
	
}
