package code.plants;
/*
 * 植物大战僵尸
 * 制作人：任超
 * 植物：坚果
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
	 * 控制血量
	 * 当血量低于三分之一时，换图标
	 * 当血量低于三分之二时，换图标
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
