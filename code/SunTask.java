package code;
import java.util.TimerTask;
/**
 * 
 * @author AndyLau96
 *
 */
public class SunTask extends TimerTask{

	private GameBackground parent;
	public SunTask(GameBackground parent){
		this.parent=parent;
	}
	@Override
	public void run() {
		parent.sun_list.add(new Sun(parent));
	}
	
}
