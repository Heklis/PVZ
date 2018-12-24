package code.plants;
/*
 * ֲ���ս��ʬ
 * �����ˣ��γ�
 * ֲ����
 */
import javax.swing.ImageIcon;

import code.ContextVar;
import code.GameBackground;
import code.ImgSrc;

public class WallNut extends Plant{
	
	public WallNut(int x, int y, GameBackground gameStart,int line,int column) {
		super(x, y, gameStart ,line,column);
		this.apperance =ImgSrc.wallNut;
		this.hp=ContextVar.wallNutHp;
		System.out.println(hp);
		ImageIcon imageIcon=new ImageIcon("./src/images/wallnut.gif");
		this.width=imageIcon.getIconWidth();
		this.height=imageIcon.getIconHeight();
		
	}
	/*
	 * ����Ѫ��
	 * ��Ѫ����������֮һʱ����ͼ��
	 * ��Ѫ����������֮��ʱ����ͼ��
	 * @see code.plants.Plant#changeStatus()
	 */
	@Override
	public void changeStatus() {
		if (hp<=ContextVar.wallNutHp/3) {
			System.out.println(hp);
			apperance=ImgSrc.wallNutCrack2;
		}else if(hp<=ContextVar.wallNutHp/3*2){
			apperance=ImgSrc.wallNutCrack1;
		}
	}

}
