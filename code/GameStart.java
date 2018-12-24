package code;


import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * 
 * @author AndyLau96
 *
 */
public class GameStart extends JFrame {

	private GameBackground GameBgPanel;
	StartInterface si = new StartInterface(this);
	public GameStart(){
		super();
		this.setBounds(0,0,900,600);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//GameBgPanel=new GameBackground(-150,0,1400,550,this);
/*		GameBgPanel=new GameBackground(0,0,1400,570,this);*/
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//this.add(GameBgPanel);

		
		this.setVisible(true);
		si.startPaint();
	
	}
	
	public static void main(String[] args){
		new GameStart();
	}
}
