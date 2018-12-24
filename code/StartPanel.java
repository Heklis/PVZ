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
 * @author 丁相国 7-20
 * 
 */
public class StartPanel extends Panel {
	GameBackground GameBgPanel= null;

	// name掉落面板效果 变量
	int nameX = 30;
	int nameY1 = -20;
	int nameY2 = -20;
	int nameY3 = -20;
	double v1 = 6;
	int v2 = 12;
	int v3 = 20;
	int g = 23;
	
	// 随机变量控制变换按钮图片样式
	int i = 1;
	
	// 重绘线程
	MyThread thread = new MyThread();
	
	// 四个开始游戏按钮
	JButton enterBut = new JButton();
	JButton miniBut = new JButton();
	JButton yizhiBut = new JButton();
	JButton scBut = new JButton();

	// 鬼手
	JButton handBut = new JButton();

	// 用户面板组件
	JPanel NamePanel = new JPanel();
	JButton NameBut1 = new JButton();
	JButton NameBut2 = new JButton();
	JButton NameBut3 = new JButton();

	// 暂未开放组件
	JPanel noJpanel = new JPanel();
	JButton noBut1 = new JButton();
	JButton noBut2 = new JButton();
	
	// 右下方字组件
	JButton wordButs[] = new JButton[3];
	
	// 花 奖杯
	JButton flowerBut1 = new JButton();
	JButton flowerBut2 = new JButton();
	
	// 帮助组件
	JPanel helpPanel = new JPanel();
	JButton helpBackBut = new JButton();
	JButton helpWordBut = new JButton();
	JButton helpReturnBut = new JButton();

	// 关于组件
	JPanel aboutPanel = new JPanel();
	JButton aboutBack = new JButton();
	JButton aboutReturnBut = new JButton();

	//是否开始游戏
	boolean isStart = false;
	boolean isStartHand = false;

	
	//持有总控制类引用
	GameStart gs = null;

	
	public StartPanel(GameStart gs) {
		this.gs = gs;
	}

	@Override
	public void paint(Graphics g) {

		//绘制背景图
		g.drawImage(ImgSrc.dbgIMG3, 0, 0, 900, 600, this);
		super.paint(g);

	}

	// 双缓冲技术
	Image imgBuffer;
	@Override
	public void update(Graphics g) {
		if (imgBuffer == null) {// 判断是否为空
			imgBuffer = this.createImage(900, 600);
		}
		// 得到图像的画笔
		Graphics newG = imgBuffer.getGraphics();
		// 在图像中画图
		paint(newG);
		// 画缓冲区
		g.drawImage(imgBuffer, 0, 0, 900, 600, this);

	}

	// 重绘线程
	public class MyThread extends Thread {

		@Override
		public void run() {
			Date StartDate = null;// 开始游戏计时
			Date StartNameDate = new Date();// 画用户面板计时

			while (true) {
				repaint();

				// 是否添加出鬼手
				if (isStartHand) {
					handBut.setBounds(300, 360, 200, 200);
					setButton(handBut, ImgSrc.dhand);
					StartPanel.this.add(handBut);
					isStartHand = false;
					isStart = true;
					StartDate = new Date();


					// 声音效果
					gs.si.backMusic.stop();
					new Sound("music/losemusic.mp3").play();//恐怖声音
					new Timer().schedule(new TimerTask() {//诡笑声音延迟出现

						@Override
						public void run() {
							setButton(enterBut, ImgSrc.dmaoxianbut1);
							new Sound("music/evillaugh.mp3").play();
						}
					}, 1000);
				}

				// 判断是否开始游戏
				if (isStart) {

					if (StartDate != null
							&& new Date().getTime() - StartDate.getTime() > 4000) {
						// gs.remove(StartPanel.this);
						
						// 添加其他的(开始游戏----转周磊天)
					    GameBgPanel =new GameBackground(0,0,1400,570,gs);
					    gs.add(GameBgPanel,0);
/*						gs.add(new JPanel().add(new JButton("画面跳转测试")));//测试
						gs.repaint();//测试
						gs.revalidate();//测试
						*/
						gs.remove(StartPanel.this);//测试
						gs.repaint();//测试
						gs.revalidate();//测试
						// 停止线程
						thread.stop();
						return;
					} else if (StartDate != null) {
						// 按钮闪烁效果
						if (i > 0) {
							setButton(enterBut, ImgSrc.dmaoxianbut2);
							i = -1;
						} else {
							setButton(enterBut, ImgSrc.dmaoxianbut1);
							i = 1;
						}

					}
				}

				// 对名字面板掉落效果的设置
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
		// 初始化鼠标样式
		Toolkit tk = Toolkit.getDefaultToolkit();
		Cursor cursor = tk.createCustomCursor(ImgSrc.dmouse, new Point(10, 10),
				"norm");
		this.setCursor(cursor);

		//布局样式为空
		this.setLayout(null);

		// 添加开始冒险游戏按钮
		initEnterBut();
		this.add(enterBut);

		// 添加迷你游戏按钮
		initMiniBut();
		this.add(miniBut);

		// 添加益智游戏按钮
		initYizhiBut();
		this.add(yizhiBut);

		// 添加生存按钮
		initScBut();
		this.add(scBut);

		// 添加用户面板
		intitNamePanel();

		// 添加未开放组件-----测试
		// initNotFund();

		// 添加右下方选项组件
		initWordBut();

		// 添加花的奖杯
		initFlowers();

		// 添加帮助组件------测试
		// initHelp();

		thread.start();

	}

	//冒险模式按钮
	public void initEnterBut() {
		enterBut.setBounds(470, 60, 350, 110);
		setButton(enterBut, ImgSrc.dmaoxianbut1);
		enterBut.addMouseListener(new MouseAdapter() {
			// 鼠标事件
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
	
	//迷你游戏按钮
	public void initMiniBut() {
		miniBut.setBounds(470, 80 * 2, 350 - 5, 110);
		setButton(miniBut, ImgSrc.dmini1);
		miniBut.addMouseListener(new MouseAdapter() {
			// 鼠标事件
			// 鼠标事件
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
	//益智游戏按钮
	public void initYizhiBut() {
		yizhiBut.setBounds(470 + 5, 80 * 3, 350 - 40, 110);
		setButton(yizhiBut, ImgSrc.dyizhi11);
		yizhiBut.addMouseListener(new MouseAdapter() {
			// 鼠标事件
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

	//解密游戏按钮
	public void initScBut() {
		scBut.setBounds(470 + 10, 80 * 4, 350 - 60, 110);
		setButton(scBut, ImgSrc.dshengcun1);
		scBut.addMouseListener(new MouseAdapter() {
			// 鼠标事件
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

	// 名字面板组件
	public void intitNamePanel() {
		int width = 300;
		int height = 80;
		// 0 111 160
		//三个木板
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

	// 暂未开放组件
	public void initNotFund() {

		noJpanel.setLayout(null);
		noJpanel.setLocation(170, 50);
		noJpanel.setSize(500, 500);
		// x坐标 170 260
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

	// 右下方字组件
	public void initWordBut() {

		// 帮助按钮
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
		
		// 关于按钮
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

		// 退出按钮
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

	// 添加花的奖杯
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

	// 添加帮助界面
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

	// 关于组件
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

	// Jbutton 透明，背景图，图片填满的设置
	public void setButton(JButton enter, Image img) {
		ImageIcon ii = new ImageIcon(img);
		Image temp = ii.getImage().getScaledInstance(enter.getWidth(),
				enter.getHeight(), ii.getImage().SCALE_DEFAULT);
		ii = new ImageIcon(temp);
		enter.setIcon(ii);
		enter.setOpaque(false);// 设置控件是否透明，true为不透明，false为透明
		enter.setContentAreaFilled(false);// 设置图片填满按钮所在的区域
		enter.setMargin(new Insets(0, 0, 0, 0));// 设置按钮边框和标签文字之间的距离
		enter.setFocusPainted(false);// 设置这个按钮是不是获得焦点
		enter.setBorderPainted(false);// 设置是否绘制边框
		enter.setBorder(null);// 设置边框
	}

}
