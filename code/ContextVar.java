package code;

import java.awt.Rectangle;

/**
 * 全局常量的公共类
 */
public final class ContextVar {
	public  static Rectangle[][] grassRect;
	public  static Rectangle[][] plantRect;
	public static final int plantStartX=110;
	public static final int plantStartY=80;   
	
	public static final int grassStartX=100;
	public static final int grassStartY=70;
	public static final int grassWidth=81;
	public static final int grassHeight=98;
	
	public static final int zombieFirstLineY=20;   

	
	public static void initRectangle(){
		int py=plantStartY;
		int gy=grassStartY;
		grassRect=new Rectangle[5][9];
		plantRect=new Rectangle[5][9];
		for(int i=0;i<5;i++){
			int px=plantStartX;
			int gx=grassStartX;
			for(int j=0;j<9;j++){
				grassRect[i][j]=new Rectangle(gx,gy,grassWidth,grassHeight);
				plantRect[i][j]=new Rectangle(px,py,grassWidth,grassHeight);
				gx+=81;
				px+=81;
				//System.out.println("static");
			}
			gy+=98;
			py+=98;
		}
	}
	public static final String PLANT_BACK_URL = "/zhou/SeedBank.png"; // 植物框图片资源
	public static final String BACKGROUND_URL = "/zhou/background1.jpg"; // 主背景框图片资源
	public static final String SHOVEL_BACK_URL = "/zhou/ShovelBank.png"; // 铁铲框
	public static final String SHOVEL_URL = "/zhou/Shovel.png"; // 工具铁铲图片资源
	public static final String LAWN_MOWER_URL = "/zhou/LawnMower.png"; // 铲车图片资源
	public static final String PROGRESS_EMPTY_URL = "/zhou/ProgressEmpty.png"; // 进度条图片资源
	public static final String PROGRESS_TIP_URL = "/zhou/FlagMeterLevelProgress.png"; // 进度条下方提示
	public static final String PROGRESS_HEAD_URL = "/zhou/FlagMeterParts1.png"; // 僵尸头
	public static final String REAL_SHOVEL_URL = "/zhou/Shovel_hi_res.png";// 大铁铲

	public static final String SUN_FLOWER_URL = "/zhou/SunFlower.gif";
	public static final String SUN_FLOWER_TRANSPARENT_URL = "/zhou/sun_half_transparent.png";
	
	public static final int ATTACK_zombie=1;

	public static final int sunflowerCardId = 0;
	public static final int sunflowerCardNeedSunNumber = 50;
	public static final int sunflowerCardCoolDownTime = 10000;

	public static final int peashooterCardId = 1;
	public static final int peashooterCardNeedSunNumber = 100;
	public static final int peashooterCardCoolDownTime = 10000;

	public static final int potatoMineCardId = 2;
	public static final int potatoMineCardNeedSunNumber = 25;
	public static final int potatoMineCardCoolDownTime = 5000;

	public static final int wallNutCardId = 3;
	public static final int wallNutCardNeedSunNumber = 50;
	public static final int wallNutCardCoolDownTime = 20000;

	public static final int icePeaShotterCardId = 4;
	public static final int icePeaShotterCardNeedSunNumber = 175;
	public static final int icePeaShotterCardCoolDownTime = 30000;

	public static final int cherryBombCardId = 5;
	public static final int cherryBombCardNeedSunNumber = 150;
	public static final int cherryBombCardCoolDownTime = 25000;

	public static final int cardWidth = 50;
	public static final int cardHeight = 70;

	public static final int sunWidth = 75;
	public static final int sunHeight = 75;
	public static final int sunLiveTime = 5000;

	
	public static final int HP_zombie = 10;
	
	public static final int HP_coneHeadZombie = 20;
	
	public static final int HP_bucketHeadZombie = 30;

	public static final double SPEED_zombie = 0.5;
	
	public static final int DELAY = 200;
	
	public static final int createSunTime=5000;
	public static final int potatoGrowTime=5000;
	public static final int potatoBoomDisTime=2000;
	public static final int cherryGrowTime=5000;
	public static final int cherryBoomDisTime=7000;
	public static final int width=900;
	public static final int height=600;
	public static final int normalPlantHp=6;
	public static final int wallNutHp=25;
	public static final int attack=10;
	
	public static int doubleShotterCardId = 4;
	public static int  doubleShotterCardNeedSunNumber = 200;
	public static int  doubleShotterCardCoolDownTime = 20000;
}
