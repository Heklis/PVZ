package code;
import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
public class Sound {
	private AdvancedPlayer ap =null;
	private boolean Tloop = false;
	private Muiscl ml = new Muiscl();
	private String mp3path =null; 
	InputStream in;
	//��ʼ��
	public Sound(String mp3path){
		this.mp3path = mp3path;
	}
	//����
	public void play(){
		Tloop = false;
		ml.start();
	}
	//ֹͣ
	@SuppressWarnings("deprecation")
	public void stop(){
		Tloop = false;
		ml.stop();
	}
	//ѭ������
	public void playloop(){
		Tloop = true;
		ml.start();
	}
	
	class Muiscl extends Thread{
		public void run() {
			do{
					in = this.getClass().getClassLoader().getResourceAsStream(mp3path);                          
				try {
					ap = new AdvancedPlayer(in);
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
				try {
					ap.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}while(Tloop);
		}
	}
}
