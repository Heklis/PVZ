package code;

import java.awt.Color;
import java.awt.Frame;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

/**
 * 
 * @author ����� 7-20
 * 
 */
public class StartInterface extends JPanel {

	// ������
	int width = 900;
	int height = 600;
	// ͼ�����ҳ��
	IconPanel iconPanel;
	// ��Ϸ����ҳ��
	LoadPanel loadPanel;
	// ��ʼѡ��ҳ��
	StartPanel startPanel;
	//���п���������
	GameStart gs;
	//������������ ���ڽ���
	Sound backMusic = new Sound("music/Faster.mp3");
	
	//��ʼ��
	public StartInterface(GameStart gs) {
		this.gs = gs;

	}

	// ��ʼ���ƻ���
	public void startPaint() {
		
		//���ñ�����͸��

		
		// �ı�javaͼ��
		gs.setIconImage(ImgSrc.dlogo);

		//���ñ���ɫ
		gs.setBackground(Color.black);
		
		// ����ҳ��һ
		iconPanel = new IconPanel();
		gs.add(iconPanel);
		backMusic.playloop();//�������ֿ�ʼ
		gs.revalidate();
		gs.validate();
		gs.repaint();
		Date startDate = new Date();
		while (new Date().getTime() - startDate.getTime() < 3000) {//ҳ��һʱ��
			iconPanel.repaint();
		}


		// ����ҳ���
		loadPanel = new LoadPanel();
		loadPanel.setBounds(0, 0, width, height);
		gs.add(loadPanel,0);
		gs.revalidate();
		gs.validate();
		gs.remove(iconPanel);
		startDate = new Date();
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				new Sound("music/groan1.mp3").play();
			}
		}, 1200);
		while (new Date().getTime() - startDate.getTime() < 3000) {//ҳ��ʱ��
			loadPanel.repaint();
			
		}


		// ����ҳ����

		startPanel = new StartPanel(gs);
		gs.add(startPanel,0);
		gs.remove(loadPanel);
		gs.repaint();
		startPanel.startThread();
		gs.revalidate();
		
		
		
	}

	
}
