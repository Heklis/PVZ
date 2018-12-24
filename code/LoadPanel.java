package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JPanel;

/**
 * 
 * @author 丁相国 7-20
 * 
 */
public class LoadPanel extends JPanel {

	int width = 50;// 480
	int caoWidth = 80;
	int i = 0;
	int x = 0;
	int y = 380;

	@Override
	public void paint(Graphics g) {

		// 背景
		g.drawImage(ImgSrc.dbgIMG2, 0, 0, 900, 600, this);
		g.drawImage(ImgSrc.dtitle, 160, 45, 604, 125, this);

		// 草卷
		g.drawImage(ImgSrc.dground, 180, 400, 180 + x, 470, 0, 0,
				(int) (1072 * (x * 1.0 / 480)), 108, this);

		// 草球
		g.drawImage(ImgSrc.dcaoqiu, 160 + x, y, caoWidth, caoWidth, this);

		// 草球滚动效果
		g.drawImage(ImgSrc.dcao, 180, 450, 500, 70, this);
		
		if (x >= 470) {// 移动
			x = 470;
			caoWidth = 30;
			y = 380 + 40;

		}

		if (caoWidth <= 30) {
			caoWidth = 30;
			y = 380 + 25;
		}
		x++;
		i++;
		if (i % 20 == 0) {// 草球随滚动变小 并根据大小向下移动
			caoWidth -= 2;
			y++;
			i = 0;
		}

	}

}
