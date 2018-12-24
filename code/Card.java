package code;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Date;

public class Card {	
	public int id;	//卡片的id，用以标识种类
	int x;	//卡片的横坐标
	int y;	//卡片的纵坐标
	
	int width = ContextVar.cardWidth;	//卡片的宽
	int height =ContextVar.cardHeight;	//卡片的高
	
	int needSunNumber;	//需要的阳光数量
	int coolDownTime;	//需要的冷却时间
	
	Image image;
	
	int coolDownStartTime; //开始冷却的时刻
	Boolean isCoolDowning = false;	//是否处于冷却状态
	Boolean isCanUse;	//能否被使用（依据是否冷却与拥有的阳光数量）

	//构造方法，传入id，与卡片的横纵坐标
	public Card(int id, int x, int y) {
		this.x = x;
		this.y = y;
		this.id=id;
		//根据id（不同的植物），赋予不同的属性
		switch(id) {
		case 0:
			needSunNumber = ContextVar.sunflowerCardNeedSunNumber;
			coolDownTime = ContextVar.sunflowerCardCoolDownTime;
			image = ImgSrc.sunflowerCardImage;
			break;
		case 1:
			needSunNumber = ContextVar.peashooterCardNeedSunNumber;
			coolDownTime = ContextVar.peashooterCardCoolDownTime;
			image = ImgSrc.peashooterCardImage;
			break;
		case 2:
			needSunNumber = ContextVar.potatoMineCardNeedSunNumber;
			coolDownTime = ContextVar.potatoMineCardCoolDownTime;
			image = ImgSrc.potatoMineCardImage;
			break;
		case 3:
			needSunNumber = ContextVar.wallNutCardNeedSunNumber;
			coolDownTime = ContextVar.wallNutCardCoolDownTime;
			image = ImgSrc.wallNutCardImage;
			break;
		case 4:
			needSunNumber = ContextVar.doubleShotterCardNeedSunNumber;
			coolDownTime = ContextVar.doubleShotterCardCoolDownTime;
			image = ImgSrc.doubleShotterCardImage;
			break;
		case 5:
			needSunNumber = ContextVar.cherryBombCardNeedSunNumber;
			coolDownTime = ContextVar.cherryBombCardCoolDownTime;
			image = ImgSrc.cherryBombCardImage;
			break;
		}
	}
	//开始冷却方法，被使用时调用
	public Boolean startCoolDown() {
		if(isCanUse) {
			coolDownStartTime = (int) new Date().getTime();
			isCoolDowning = true;
			return true;
		}else {
			return false;
		}
	}
	
	//画图方法
	public void drawImage(Graphics g,ImageObserver observer, int sunNumber) {
		g.drawImage(image, x, y, width, height, observer);
		if(sunNumber < needSunNumber || isCoolDowning) {
			isCanUse = false;
			g.drawImage(ImgSrc.cardNoImage, x, y, width, height, observer);
			if(isCoolDowning) {
				int time = (int) (new Date().getTime() - coolDownStartTime);
				if(time > coolDownTime) {
					isCoolDowning = false;
				}else {
					g.drawImage(ImgSrc.cardCoolDownImage, x, y, width, (int)((1 - time/(double)(coolDownTime))*height), observer);
				}	
			}
		}else {
			isCanUse = true;
		}
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
	
	public Rectangle getRectangle(){
		return new Rectangle(x,y,width,height);
	}
}
