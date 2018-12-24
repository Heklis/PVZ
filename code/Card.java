package code;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Date;

public class Card {	
	public int id;	//��Ƭ��id�����Ա�ʶ����
	int x;	//��Ƭ�ĺ�����
	int y;	//��Ƭ��������
	
	int width = ContextVar.cardWidth;	//��Ƭ�Ŀ�
	int height =ContextVar.cardHeight;	//��Ƭ�ĸ�
	
	int needSunNumber;	//��Ҫ����������
	int coolDownTime;	//��Ҫ����ȴʱ��
	
	Image image;
	
	int coolDownStartTime; //��ʼ��ȴ��ʱ��
	Boolean isCoolDowning = false;	//�Ƿ�����ȴ״̬
	Boolean isCanUse;	//�ܷ�ʹ�ã������Ƿ���ȴ��ӵ�е�����������

	//���췽��������id���뿨Ƭ�ĺ�������
	public Card(int id, int x, int y) {
		this.x = x;
		this.y = y;
		this.id=id;
		//����id����ͬ��ֲ������費ͬ������
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
	//��ʼ��ȴ��������ʹ��ʱ����
	public Boolean startCoolDown() {
		if(isCanUse) {
			coolDownStartTime = (int) new Date().getTime();
			isCoolDowning = true;
			return true;
		}else {
			return false;
		}
	}
	
	//��ͼ����
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
