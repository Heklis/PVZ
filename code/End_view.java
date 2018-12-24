package code;

/*
 * ��Ȩ��������
 * �汾��plant v1.0
 * �����ߣ�������
 */

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class End_view {

	private int x1 = 168;
	private int y1 = 1000;
	private int x2 = 295;
	private int y2 = 200;

	private int x3 = 80;
	private int y3 = 1200;
	private int x4 = 363;
	private int y4 = 450;

	private int gameover_word_width = 564;
	private int gameover_word_height = 468;
	private int gameover_width = 309;
	private int gameover_height = 238;

	private int win_width = 741;
	private int win_height = 234;
	private int win_word_width = 174;
	private int win_word_height = 40;

	public boolean isover = false;

	private GameBackground gStart;

	// �����๹��
	public End_view(GameBackground gStart) {
		this.gStart = gStart;
	}

	// ���ƽ�������
	public void Draw_end_view(Graphics graphics, int isWinning) {
		if (isWinning == 1) {
			winning(graphics);
		} else if (isWinning == 0) {
			lose(graphics);
		}
	}

	// ʤ������
	public void winning(Graphics graphics) {
		graphics.drawImage(ImgSrc.winning, x3, y3, win_width, win_height, gStart);

		if (y3 > 200) {
			y3 -= 5;
		} else {
			graphics.drawImage(ImgSrc.winning_word, x4, y4, win_word_width, win_word_height, gStart);
			isover = true;
		}

	}

	// ʧ�ܽ���
	public void lose(Graphics graphics) {
		graphics.drawImage(ImgSrc.gameover_word, x1, y1, gameover_word_width, gameover_word_height, gStart);

		if (y1 > 100) {
			y1 -= 6;
		} else {
			graphics.drawImage(ImgSrc.gameover, x2, y2, gameover_width, gameover_height, gStart);
			isover = true;
		}

	}
}
