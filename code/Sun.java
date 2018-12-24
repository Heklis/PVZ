package code;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Date;
import java.util.Random;

public class Sun {
	
	int birthMode; //0是自然掉落,1是太阳花生成
	
	int x;	//橫坐標
	int y;	//纵坐标

	int finalY;		//自然掉落的阳光最终纵坐标
	int finalX;		//
	
	int birthTime; 	//生成时间
	int time;		//已经存活时间
		
	int width = ContextVar.sunWidth; 		//阳光宽度
	int height = ContextVar.sunHeight; 		//阳光高度
	Image image = ImgSrc.sun; 			//阳光图片
	int liveTime = ContextVar.sunLiveTime; 	//存活时间
	Boolean isLive = true; 			//是否存活
	
	int randomSpeed;
	int randomY;
	int speed = 2;
	int jumpHeight;
	ImageObserver observer;
	
	Boolean isClick = false;
	double xSlope;			
	double ySlope;
	int sunCardX = 50;		
	int sunCardY = 0;
	int sunCardWidth = 50;	 
	int sunCardHeight = 50;
	
	
	/*构造方法
	 * 自然生成阳光不传参数
	 * 太阳花生成传入太阳花的横、纵坐标
	 * */
	public Sun(ImageObserver observer) {
		birthMode = 0;
		this.x = new Random().nextInt(730 - width) + 100;
		this.y = 0;	
		this.observer = observer;
		birthTime = (int) new Date().getTime();
		finalY = new Random().nextInt(480 - height) + 70;
	}
	
	public Sun(int x, int y, ImageObserver observer) {

		birthMode = 1;
		this.x = x;
		this.y = y;
		this.observer = observer;
		birthTime = (int) new Date().getTime();

		randomSpeed = (int) (Math.random() * 4);
		randomY = (int) (Math.random() * 200 + 50);
		jumpHeight = (int) (Math.random() * 100 + 30);
	}
	

	public void setSlope() {
		xSlope = (x-(sunCardX+0.5*sunCardWidth))/40.0;
		ySlope = (y-(sunCardY+0.5*sunCardHeight))/40.0;	
	}
	/*画图方法
	 * 
	 * */
	public void drawImage(Graphics g) {
		if (isClick) {
			x -= xSlope;
			y -= ySlope;
			g.drawImage(image, x, y, width, height, observer);
			if (new Rectangle(sunCardX,sunCardY,sunCardWidth,sunCardHeight).intersects(new Rectangle(x,y,ContextVar.cardWidth,ContextVar.cardHeight))) {
				isLive = false;
			}
		} else {

			time = (int) (new Date().getTime() - birthTime); 

			
			if (time >= liveTime) {
				isLive = false;
			} else {
			    g.drawImage(image, x, y, width, height, observer);
				if (birthMode == 0) {
					if (y < finalY) {
						y += 4;
					}
				} else if (birthMode == 1) {
					if (jumpHeight > 0) {
						y -= speed;
						x += speed;
						jumpHeight -= speed;
					} else {

						if (randomY > 0) {
							y += randomSpeed;
							randomY -= randomSpeed;
						}
					}
				}
			}
		}
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,width,height);
	}
}