package code.zombies;

import java.awt.Graphics;

import javax.swing.JPanel;

import code.ContextVar;
import code.GameBackground;
import code.ImgSrc;

public class BucketHeadZombie extends Zombie{

	// Ω© ¨≥ı º…˙√¸÷µ
	static final int HP = ContextVar.HP_bucketHeadZombie;

	public BucketHeadZombie(int x, int y, int line, GameBackground panel) {
		super(x, y, line, panel);
		this.hp = HP;
		this.walkImg = ImgSrc.bucketHead;
		this.attackImg = ImgSrc.bucketHeadAttack;
	}

	@Override
	public void attacked(Graphics g) {
		if (this.hp == 100) {
			this.walkImg = ImgSrc.zombie;
			this.attackImg = ImgSrc.zombieAttack;
		}
		super.attacked(g);
	}

}
