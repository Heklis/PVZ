package code;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author ����� 7-20
 * 
 */
public class StartPanel extends Panel {
	GameBackground GameBgPanel= null;

	// name�������Ч�� ����
	int nameX = 30;
	int nameY1 = -20;
	int nameY2 = -20;
	int nameY3 = -20;
	double v1 = 6;
	int v2 = 12;
	int v3 = 20;
	int g = 23;
	
	// ����������Ʊ任��ťͼƬ��ʽ
	int i = 1;
	
	// �ػ��߳�
	MyThread thread = new MyThread();
	
	// �ĸ���ʼ��Ϸ��ť
	JButton enterBut = new JButton();
	JButton miniBut = new JButton();
	JButton yizhiBut = new JButton();
	JButton scBut = new JButton();

	// ����
	JButton handBut = new JButton();

	// �û�������
	JPanel NamePanel = new JPanel();
	JButton NameBut1 = new JButton();
	JButton NameBut2 = new JButton();
	JButton NameBut3 = new JButton();

	// ��δ�������
	JPanel noJpanel = new JPanel();
	JButton noBut1 = new JButton();
	JButton noBut2 = new JButton();
	
	// ���·������
	JButton wordButs[] = new JButton[3];
	
	// �� ����
	JButton flowerBut1 = new JButton();
	JButton flowerBut2 = new JButton();
	
	// �������
	JPanel helpPanel = new JPanel();
	JButton helpBackBut = new JButton();
	JButton helpWordBut = new JButton();
	JButton helpReturnBut = new JButton();

	// �������
	JPanel aboutPanel = new JPanel();
	JButton aboutBack = new JButton();
	JButton aboutReturnBut = new JButton();

	//�Ƿ�ʼ��Ϸ
	boolean isStart = false;
	boolean isStartHand = false;

	
	//�����ܿ���������
	GameStart gs = null;

	
	public StartPanel(GameStart gs) {
		this.gs = gs;
	}

	@Override
	public void paint(Graphics g) {

		//���Ʊ���ͼ
		g.drawImage(ImgSrc.dbgIMG3, 0, 0, 900, 600, this);
		super.paint(g);

	}

	// ˫���弼��
	Image imgBuffer;
	@Override
	public void update(Graphics g) {
		if (imgBuffer == null) {// �ж��Ƿ�Ϊ��
			imgBuffer = this.createImage(900, 600);
		}
		// �õ�ͼ��Ļ���
		Graphics newG = imgBuffer.getGraphics();
		// ��ͼ���л�ͼ
		paint(newG);
		// ��������
		g.drawImage(imgBuffer, 0, 0, 900, 600, this);

	}

	// �ػ��߳�
	public class MyThread extends Thread {

		@Override
		public void run() {
			Date StartDate = null;// ��ʼ��Ϸ��ʱ
			Date StartNameDate = new Date();// ���û�����ʱ

			while (true) {
				repaint();

				// �Ƿ���ӳ�����
				if (isStartHand) {
					handBut.setBounds(300, 360, 200, 200);
					setButton(handBut, ImgSrc.dhand);
					StartPanel.this.add(handBut);
					isStartHand = false;
					isStart = true;
					StartDate = new Date();


					// ����Ч��
					gs.si.backMusic.stop();
					new Sound("music/losemusic.mp3").play();//�ֲ�����
					new Timer().schedule(new TimerTask() {//��Ц�����ӳٳ���

						@Override
						public void run() {
							setButton(enterBut, ImgSrc.dmaoxianbut1);
							new Sound("music/evillaugh.mp3").play();
						}
					}, 1000);
				}

				// �ж��Ƿ�ʼ��Ϸ
				if (isStart) {

					if (StartDate != null
							&& new Date().getTime() - StartDate.getTime() > 4000) {
						// gs.remove(StartPanel.this);
						
						// ���������(��ʼ��Ϸ----ת������)
					    GameBgPanel =new GameBackground(0,0,1400,570,gs);
					    gs.add(GameBgPanel,0);
/*						gs.add(new JPanel().add(new JButton("������ת����")));//����
						gs.repaint();//����
						gs.revalidate();//����
						*/
						gs.remove(StartPanel.this);//����
						gs.repaint();//����
						gs.revalidate();//����
						// ֹͣ�߳�
						thread.stop();
						return;
					} else if (StartDate != null) {
						// ��ť��˸Ч��
						if (i > 0) {
							setButton(enterBut, ImgSrc.dmaoxianbut2);
							i = -1;
						} else {
							setButton(enterBut, ImgSrc.dmaoxianbut1);
							i = 1;
						}

					}
				}

				// ������������Ч��������
				if (nameY1 * v1 <= 5) {
					NameBut1.setLocation(nameX, (int) (nameY1 * v1));
					nameY1 += 3;
				} else {
					NameBut1.setLocation(nameX, 5);
				}
				if (nameY2 * v2 <= 111) {
					NameBut2.setLocation(nameX, (int) (nameY2 * v2));
					nameY2 += 3;

				} else {
					NameBut2.setLocation(nameX, (int) (111));
				}
				if (nameY3 * v3 <= 160) {
					NameBut3.setLocation(nameX, (int) (nameY3 * v3));
					nameY3 += 3;
				} else {
					NameBut3.setLocation(nameX, (int) (160));
				}

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}

	public void startThread() {
		// ��ʼ�������ʽ
		Toolkit tk = Toolkit.getDefaultToolkit();
		Cursor cursor = tk.createCustomCursor(ImgSrc.dmouse, new Point(10, 10),
				"norm");
		this.setCursor(cursor);

		//������ʽΪ��
		this.setLayout(null);

		// ��ӿ�ʼð����Ϸ��ť
		initEnterBut();
		this.add(enterBut);

		// ���������Ϸ��ť
		initMiniBut();
		this.add(miniBut);

		// ���������Ϸ��ť
		initYizhiBut();
		this.add(yizhiBut);

		// ������水ť
		initScBut();
		this.add(scBut);

		// ����û����
		intitNamePanel();

		// ���δ�������-----����
		// initNotFund();

		// ������·�ѡ�����
		initWordBut();

		// ��ӻ��Ľ���
		initFlowers();

		// ��Ӱ������------����
		// initHelp();

		thread.start();

	}

	//ð��ģʽ��ť
	public void initEnterBut() {
		enterBut.setBounds(470, 60, 350, 110);
		setButton(enterBut, ImgSrc.dmaoxianbut1);
		enterBut.addMouseListener(new MouseAdapter() {
			// ����¼�
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(enterBut, ImgSrc.dmaoxianbut2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(enterBut, ImgSrc.dmaoxianbut1);
			}

			public void mouseClicked(MouseEvent arg0) {
				// setButton(enterBut, ImgSrc.dmaoxianbut3);
				isStartHand = true;
			}
		});
	}
	
	//������Ϸ��ť
	public void initMiniBut() {
		miniBut.setBounds(470, 80 * 2, 350 - 5, 110);
		setButton(miniBut, ImgSrc.dmini1);
		miniBut.addMouseListener(new MouseAdapter() {
			// ����¼�
			// ����¼�
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(miniBut, ImgSrc.dmini2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(miniBut, ImgSrc.dmini1);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Sound("music/gravebutton.mp3").play();
				initNotFund();

			}

		});
	}
	//������Ϸ��ť
	public void initYizhiBut() {
		yizhiBut.setBounds(470 + 5, 80 * 3, 350 - 40, 110);
		setButton(yizhiBut, ImgSrc.dyizhi11);
		yizhiBut.addMouseListener(new MouseAdapter() {
			// ����¼�
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(yizhiBut, ImgSrc.dyizhi12);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(yizhiBut, ImgSrc.dyizhi11);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Sound("music/gravebutton.mp3").play();
				initNotFund();
			}
		});
	}

	//������Ϸ��ť
	public void initScBut() {
		scBut.setBounds(470 + 10, 80 * 4, 350 - 60, 110);
		setButton(scBut, ImgSrc.dshengcun1);
		scBut.addMouseListener(new MouseAdapter() {
			// ����¼�
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(scBut, ImgSrc.dshengcun2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(scBut, ImgSrc.dshengcun1);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new Sound("music/gravebutton.mp3").play();
				initNotFund();
			}
		});
	}

	// ����������
	public void intitNamePanel() {
		int width = 300;
		int height = 80;
		// 0 111 160
		//����ľ��
		NameBut1.setBounds(30, nameY1, width, height + 40);
		NameBut2.setBounds(30, nameY2, width, height);
		NameBut3.setBounds(30, nameY3, width, height);
		NameBut2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(NameBut2, ImgSrc.dNamePanel2_2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(NameBut2, ImgSrc.dNamePanel2);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				initNotFund();
			}
		});
		setButton(NameBut1, ImgSrc.dNamePanel1);
		setButton(NameBut2, ImgSrc.dNamePanel2);
		setButton(NameBut3, ImgSrc.dNamePanel3);
		this.add(NameBut1);
		this.add(NameBut2);
		this.add(NameBut3);

	}

	// ��δ�������
	public void initNotFund() {

		noJpanel.setLayout(null);
		noJpanel.setLocation(170, 50);
		noJpanel.setSize(500, 500);
		// x���� 170 260
		noBut1.setBounds(170, 40, 500, 500);
		setButton(noBut1, ImgSrc.dNoKai);
		this.add(noBut1, 0);

		noBut2.setBounds(260, 450, 350, 70);
		setButton(noBut2, ImgSrc.dsure);
		noBut2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Sound("music/gravebutton.mp3").play();
				StartPanel.this.remove(noBut1);
				StartPanel.this.remove(noBut2);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setButton(noBut2, ImgSrc.dsure2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(noBut2, ImgSrc.dsure);
			}
			

		});
		this.add(noBut2, 0);

	}

	// ���·������
	public void initWordBut() {

		// ������ť
		wordButs[1] = new JButton();
		wordButs[1].setBounds(650, 485, 65, 40);
		setButton(wordButs[1], ImgSrc.dword[2]);
		wordButs[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(wordButs[1], ImgSrc.dword[3]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(wordButs[1], ImgSrc.dword[2]);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				initHelp();

			}
		});
		
		// ���ڰ�ť
		wordButs[2] = new JButton();
		wordButs[2].setBounds(727, 520, 63, 35);
		setButton(wordButs[2], ImgSrc.dword[4]);
		wordButs[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(wordButs[2], ImgSrc.dword[5]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(wordButs[2], ImgSrc.dword[4]);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				intitAbout();
			}
		});

		// �˳���ť
		wordButs[0] = new JButton();
		wordButs[0].setBounds(810, 510, 63, 35);
		setButton(wordButs[0], ImgSrc.dword[0]);
		wordButs[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(wordButs[0], ImgSrc.dword[1]);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(wordButs[0], ImgSrc.dword[0]);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		for (int i = 0; i < wordButs.length; i++) {
			this.add(wordButs[i]);
		}

	}

	// ��ӻ��Ľ���
	public void initFlowers() {

		flowerBut1.setBounds(70, 360, 100, 170);
		setButton(flowerBut1, ImgSrc.dflower1);
		flowerBut1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				new Sound("music/bleep.mp3").play();
				setButton(flowerBut1, ImgSrc.dflower2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setButton(flowerBut1, ImgSrc.dflower1);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				initNotFund();
			}
		});
		this.add(flowerBut1);

	}

	// ��Ӱ�������
	public void initHelp() {
		helpPanel.setBounds(0, 0, 900, 600);
		helpPanel.setLayout(null);
		helpBackBut.setBounds(0, 0, 900, 600);
		helpWordBut.setBounds(150, 100, 600, 400);
		helpReturnBut.setBounds(700, 20, 150, 60);
		helpReturnBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Sound("music/gravebutton.mp3").play();
				StartPanel.this.remove(helpPanel);

			}
		});
		setButton(helpBackBut, ImgSrc.dhelpBack);
		setButton(helpWordBut, ImgSrc.dhelpWord);
		setButton(helpReturnBut, ImgSrc.dhelpReturn);
		helpPanel.add(helpBackBut);
		helpPanel.add(helpWordBut, 0);
		helpPanel.add(helpReturnBut, 0);
		this.add(helpPanel, 0);
	}

	// �������
	public void intitAbout() {
		aboutPanel.setBounds(0, 0, 900, 600);
		aboutBack.setBounds(0, 0, 900, 600);
		aboutReturnBut.setBounds(20, 20, 150, 60);
		aboutReturnBut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Sound("music/gravebutton.mp3").play();
				StartPanel.this.remove(aboutPanel);
			}
		});
		setButton(aboutBack, ImgSrc.daboutback);
		setButton(aboutReturnBut, ImgSrc.dhelpReturn);
		aboutPanel.add(aboutBack);
		aboutPanel.add(aboutReturnBut, 0);
		this.add(aboutPanel, 0);
	}

	// Jbutton ͸��������ͼ��ͼƬ����������
	public void setButton(JButton enter, Image img) {
		ImageIcon ii = new ImageIcon(img);
		Image temp = ii.getImage().getScaledInstance(enter.getWidth(),
				enter.getHeight(), ii.getImage().SCALE_DEFAULT);
		ii = new ImageIcon(temp);
		enter.setIcon(ii);
		enter.setOpaque(false);// ���ÿؼ��Ƿ�͸����trueΪ��͸����falseΪ͸��
		enter.setContentAreaFilled(false);// ����ͼƬ������ť���ڵ�����
		enter.setMargin(new Insets(0, 0, 0, 0));// ���ð�ť�߿�ͱ�ǩ����֮��ľ���
		enter.setFocusPainted(false);// ���������ť�ǲ��ǻ�ý���
		enter.setBorderPainted(false);// �����Ƿ���Ʊ߿�
		enter.setBorder(null);// ���ñ߿�
	}

}
