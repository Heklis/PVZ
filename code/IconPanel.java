package code;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Date;

import javax.swing.JPanel;

/**
 * 
 * @author 丁相国 7-20
 * 
 */

public class IconPanel extends JPanel {
	public boolean isJump = false;
	float i = 0.9f;
	float j = 0.3f;
	Date date = new Date();

	@Override
	public void paint(Graphics g) {
		g.drawImage(ImgSrc.dbgIMG1, 0, 0, 900, 600, this);
		// 设置透明度
		/*根据效果可进行替换
		 * AlphaComposite ac =
		 * AlphaComposite.getInstance(AlphaComposite.SRC_OVER, i); Graphics2D
		 * g2d = (Graphics2D) g; g2d.setComposite(ac);
		 * 
		 * g2d.drawImage(ImgSrc.diconIMG, 900/2-150/2, 600/2-150/2, 150, 150,
		 * this);
		 */
		g.drawImage(ImgSrc.diconIMG, 375, 225, 150,
				150, this);

	}

}
