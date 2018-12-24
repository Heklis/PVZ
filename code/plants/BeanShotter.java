package code.plants;
/*
 * ֲ���ս��ʬ
 * �����ˣ��γ�
 */
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import code.GameBackground;
import code.ImgSrc;
import code.Sound;



public class BeanShotter extends Plant{
	boolean shouldShot=false;//�Ƿ�Ӧ�����
	//���췽����ʼ��
	public BeanShotter(int x, int y, GameBackground parent,int line,int column) {
		super(x, y, parent, line,column);
		this.shotOnce=true;//�������������
		//��ȡͼƬ�Ϳ��
		ImageIcon imageIcon=new ImageIcon("./src/images/bean_shooter.gif");
		this.apperance=ImgSrc.beanShotter;
		this.width=imageIcon.getIconWidth();
		this.height=imageIcon.getIconHeight();
		
	}
	//���㶹
	@Override
	public void drawPlant(Graphics graphics) {
		//���ø��෽��
		super.drawPlant(graphics);
		if (isLive) {//�������
			shot();			
		}else {
			//�������Ϊfalse
			shouldShot=false;
			//��timer����Ϊnull
			if (timer!=null) {
				timer.cancel();
				timer=null;				
			}
		}
	}
	Timer timer=null;
	//������
	public void shot(){
		for(int i=0;i<parent.zombie_list.size();i++){
			if (parent.zombie_list.get(i).line==this.line&&parent.zombie_list.get(i).x>this.x&&parent.zombie_list.get(i).x<850) {
				shouldShot=true;//����Ϊtrue
				if (timer==null) {//���ö�ʱ���
					timer=new Timer();
					timer.schedule(new TimerTask() {
						
						@Override
						public void run() {
							parent.bean_list.add(new Bean(x+width/2, y+5, parent,line,column));
							new Sound("music/plant_shoot.mp3").play();
						}
					}, 1000, 2000);
				}else{
					break;//timer��Ϊ��ֱ��break
				}
			}
		}
		if (!shouldShot) {//�����Ӧ���������timer����Ϊ��
			if (timer!=null) {
				timer.cancel();
				timer=null;				
			}
		}
		
	}
	public void stopShot(){
		if(timer!=null){
			timer.cancel();
			timer=null;
		}
	}

}

