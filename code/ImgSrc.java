package code;

import java.awt.Image;
import java.awt.Toolkit;

public class ImgSrc {
	public static Toolkit tool=Toolkit.getDefaultToolkit();
	
	public static final Image IMG_SHOVEL_BACK=tool.getImage(GameBackground.class.getResource(ContextVar.SHOVEL_BACK_URL)); //铁铲框
	
	public static final Image IMG_SHOVEL=tool.getImage(GameBackground.class.getResource(ContextVar.SHOVEL_URL));  //铁铲
	
	public static final Image IMG_REAL_SHOVEL=tool.getImage(GameBackground.class.getResource(ContextVar.REAL_SHOVEL_URL)); //大铁铲
	
	public static final Image IMG_GAME_BACKGROUND=tool.getImage(GameBackground.class.getResource(ContextVar.BACKGROUND_URL));  //游戏背景
	
	public static final Image IMG_PLANT_BACK=tool.getImage(GameBackground.class.getResource(ContextVar.PLANT_BACK_URL));  //植物框
	
	public static final Image IMG_LAWN_MOWER=tool.getImage(GameBackground.class.getResource(ContextVar.LAWN_MOWER_URL));   //铲车
	
	public static final Image IMG_PROGRESS_EMPTY=tool.getImage(GameBackground.class.getResource(ContextVar.PROGRESS_EMPTY_URL)); //空进度条
	
	public static final Image IMG_PROGRESS_TIP=tool.getImage(GameBackground.class.getResource(ContextVar.PROGRESS_TIP_URL));   //进度条提示
	
	public static final Image IMG_PROGRESS_HEAD=tool.getImage(GameBackground.class.getResource(ContextVar.PROGRESS_HEAD_URL));   //僵尸头
	
	public static final Image IMG_SUN_FLOWER=tool.getImage(GameBackground.class.getResource(ContextVar.SUN_FLOWER_URL));
	
	public static final Image IMG_SUN_FLOWER_TRANSPARENT=tool.getImage(GameBackground.class.getResource(ContextVar.SUN_FLOWER_TRANSPARENT_URL));
	
	public static final Image IMG_BEAN_SHOOTER_TRANSPARENT=tool.getImage(GameBackground.class.getResource("/zhou/beanshooter_half_transparent.png"));
	
	public static final Image IMG_BOOM_POTATO_TRANSPARENT=tool.getImage(GameBackground.class.getResource("/zhou/boompotato_half_transparent.png"));
	
	public static final Image IMG_DOUBLE_BEAN_SHOOTER_TRANSPARENT=tool.getImage(GameBackground.class.getResource("/zhou/doublebeanshooter_half_transparent.png"));
	
	public static final Image IMG_POTATO_TRANSPARENT=tool.getImage(GameBackground.class.getResource("/zhou/potato_half_transparent.png"));
	
	public static final Image IMG_CHERRYBOOM_HALF_TRANSPARENT=tool.getImage(GameBackground.class.getResource("/zhou/cherryboom_half_transparent.png"));
	
	public static final Image IMG_PEABULLETHIT=tool.getImage(GameBackground.class.getResource("/images/PeaBulletHit.png"));
	
	public static final Image cardCoolDownImage=tool.getImage(GameBackground.class.getResource("/image/fd50.png")); 
	
	public static final Image sunflowerCardImage=tool.getImage(GameBackground.class.getResource("/image/SunflowerCard.png"));  
	
	public static final Image peashooterCardImage=tool.getImage(GameBackground.class.getResource("/image/PeashooterCard.gif"));  
	
	public static final Image potatoMineCardImage=tool.getImage(GameBackground.class.getResource("/image/PotatoMineCard.gif"));  
	
	public static final Image wallNutCardImage=tool.getImage(GameBackground.class.getResource("/image/WallNutCard.gif"));  
	
	public static final Image cherryBombCardImage=tool.getImage(GameBackground.class.getResource("/image/CherryBombCard.png"));  
	
	public static final Image cardNoImage=tool.getImage(GameBackground.class.getResource("/image/fd20.png"));
	
	public static final Image sun=tool.getImage(GameBackground.class.getResource("/image/Sun.gif"));
	
	
	public static Image  doubleShotterCardImage = tool.getImage(GameBackground.class.getResource("/image/DoubleShooter.jpg"));

	public static final Image sunFlower=tool.getImage(GameBackground.class.getResource("/images/sunflower.gif"));
	
	public static final Image beanShotter=tool.getImage(GameBackground.class.getResource("/images/bean_shooter.gif"));
	public static final Image doubleBeanShotter=tool.getImage(GameBackground.class.getResource("/images/double_bean_shooter.gif"));
	public static final Image normalBean=tool.getImage(GameBackground.class.getResource("/images/bean.png"));
	public static final Image wallNut=tool.getImage(GameBackground.class.getResource("/images/wallnut.gif"));
	public static final Image wallNutCrack1=tool.getImage(GameBackground.class.getResource("/images/wallnut_crack1.gif"));
	public static final Image wallNutCrack2=tool.getImage(GameBackground.class.getResource("/images/wallnut_crack2.gif"));
	public static final Image cherryBomb=tool.getImage(GameBackground.class.getResource("/images/cherryBoom.gif"));
	public static final Image boom=tool.getImage(GameBackground.class.getResource("/images/boom.gif"));
	public static final Image potatoNotReady=tool.getImage(GameBackground.class.getResource("/images/potato_not_ready.gif"));
	public static final Image potatoReady=tool.getImage(GameBackground.class.getResource("/images/potato_ready.gif"));
	public static final Image potatoBoom=tool.getImage(GameBackground.class.getResource("/images/potato_boom.png"));
	public static final Image plantShadow=tool.getImage(GameBackground.class.getResource("/images/plant_shadow.png"));
	public static final Image plantDirt=tool.getImage(GameBackground.class.getResource("/images/plant_dirt.gif"));

	
	static Image background = tool.getImage(GameBackground.class.getResource("/images/background.jpg"));

	static Image gameover = tool.getImage(GameBackground.class.getResource("/images/gameover.png"));

	static Image gameover_word = tool.getImage(GameBackground.class.getResource("/images/gameover_word.png"));

	static Image winning = tool.getImage(GameBackground.class.getResource("/images/winging.png"));
	
	static Image winning_word = tool.getImage(GameBackground.class.getResource("/images/winning_word.png"));
	
	public static Image zombie = tool.getImage(ImgSrc.class.getResource("/images/Zombies/Zombie/Zombie.gif"));
	//ÆÕÍ¨½©Ê¬2
	static Image zombie2 = tool.getImage(ImgSrc.class.getResource("/images/Zombies/Zombie/Zombie2.gif"));
	//ÆÕÍ¨½©Ê¬3
	static Image zombie3 = tool.getImage(ImgSrc.class.getResource("/images/Zombies/Zombie/Zombie3.gif"));
	//ÆÕÍ¨½©Ê¬¹¥»÷
	public static Image zombieAttack = tool.getImage(ImgSrc.class.getResource("/images/Zombies/Zombie/ZombieAttack.gif"));
	//ÆÕÍ¨½©Ê¬ËÀÍö
	public static Image zombieDie = tool.getImage(ImgSrc.class.getResource("/images/Zombies/Zombie/ZombieDie.gif"));
	//ÆÕÍ¨½©Ê¬µÄÍ·
	public static Image zombieHead = tool.getImage(ImgSrc.class.getResource("/images/Zombies/Zombie/ZombieHead.gif"));
	//ÆÕÍ¨½©Ê¬µôÍ·
	public static Image zombieLostHead = tool.getImage(ImgSrc.class.getResource("/images/Zombies/Zombie/ZombieLostHead.gif"));
	//ÆÕÍ¨½©Ê¬µôÍ·¹¥»÷
	public static Image zombieLostHeadAttack = tool.getImage(ImgSrc.class.getResource("/images/Zombies/Zombie/ZombieLostHeadAttack.gif"));
	//Ô²×¶Í·½©Ê¬
	public static Image coneHead = tool.getImage(ImgSrc.class.getResource("/images/Zombies/ConeheadZombie/ConeheadZombie.gif"));
	//Ô²×¶Í·½©Ê¬¹¥»÷
	public static Image coneHeadAttack = tool.getImage(ImgSrc.class.getResource("/images/Zombies/ConeheadZombie/ConeheadZombieAttack.gif"));
	//Ë®Í°Í·½©Ê¬
	public static Image bucketHead = tool.getImage(ImgSrc.class.getResource("/images/Zombies/BucketheadZombie/BucketheadZombie.gif"));
	//Ë®Í°Í·½©Ê¬¹¥»÷
	public static Image bucketHeadAttack = tool.getImage(ImgSrc.class.getResource("/images/Zombies/BucketheadZombie/BucketheadZombieAttack.gif"));
	//½©Ê¬±¬Õ¨ËÀÍö
	public static Image boomDie = tool.getImage(ImgSrc.class.getResource("/images/Zombies/BoomDie.gif"));
	//±³¾°
	
	public static Image normalZoombieStand=tool.getImage(GameBackground.class.getResource("/zhou/normalzoombiestand.gif"));
	
	public static Image BucketheadZombie=tool.getImage(GameBackground.class.getResource("/zhou/BucketheadZombie.gif"));
	
	public static Image coneheadZombieStand=tool.getImage(GameBackground.class.getResource("/zhou/ConeheadZombieStand.gif"));
	

	// ¶¡Ïà¹ú---start
	// ±³¾°Í¼Æ¬1
	public static Image dbgIMG1 = tool.getImage(GameBackground.class
			.getResource("/images/black.jpg"));// µÃµ½Í¼Æ¬¶ÔÏó
	// ¿ªÊ¼Ò³Ãæ1 Í¼±ê
	public static Image diconIMG = tool.getImage(GameBackground.class
			.getResource("/images/popcap_logo_registered.gif"));// µÃµ½Í¼Æ¬¶ÔÏó
	// ±³¾°Í¼Æ¬2
	public static Image dbgIMG2 = tool.getImage(GameBackground.class
			.getResource("/images/PvZ 00129.jpg"));
	// ±³¾°Í¼Æ¬3
	public static Image dbgIMG3 = tool.getImage(GameBackground.class
			.getResource("/images/Surface.png"));
	// ±êÌâÍ¼Æ¬
	public static Image dtitle = tool.getImage(GameBackground.class
			.getResource("/images/pvz_logo.png"));
	// ²ÝµØ
	public static Image dground = tool.getImage(GameBackground.class
			.getResource("/images/PvZ001303.png"));
	// ²Ý
	public static Image dcao = tool.getImage(GameBackground.class
			.getResource("/images/PvZ001301.png"));
	// ²ÝÇò
	public static Image dcaoqiu = tool.getImage(GameBackground.class
			.getResource("/images/SodRollCap.gif"));
	// ¹ö²ÝÇò¶¯Í¼
	public static Image dguncaoqiu = tool.getImage(GameBackground.class
			.getResource("/images/dguncaoqiu.gif"));

	// Ìí¼Ó¿ªÊ¼Ã°ÏÕÓÎÏ·°´Å¥
	public static Image dmaoxianbut1 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenAdventure_8_01.gif"));
	public static Image dmaoxianbut2 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenAdventure_8_02.gif"));
	public static Image dmaoxianbut3 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenAdventure_8_03.gif"));

	// Ìí¼ÓÃÔÄãÓÎÏ·°´Å¥
	public static Image dmini1 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenSurvival_8_01.gif"));
	public static Image dmini2 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenSurvival_8_02.gif"));

	// Ìí¼ÓÒæÖÇÓÎÏ·°´Å¥
	public static Image dyizhi11 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenChallenges_8_01.gif"));
	public static Image dyizhi12 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenChallenges_8_02.gif"));

	// Ìí¼ÓÉú´æÄ£Ê½°´Å¥
	public static Image dshengcun1 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenChallenges_01.gif"));
	public static Image dshengcun2 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreenChallenges_02.gif"));
	// ÊÖ
	public static Image dhand = tool.getImage(GameBackground.class
			.getResource("/images/zh.gif"));

	// ÓÃ»§Ãæ°å×é¼þ
	public static Image dNamePanel1 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreen_WoodSign1_32.png"));
	public static Image dNamePanel2 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreen_WoodSign2_8_01.gif"));
	public static Image dNamePanel2_2 = tool.getImage(GameBackground.class
			.getResource("/images/SelectorScreen_WoodSign2_8_02.gif"));
	public static Image dNamePanel3 = tool.getImage(GameBackground.class
			.getResource("/images/PvZ 00007.png"));

	// ÔÝÎ´¿ª·Å×é¼þ
	public static Image dNoKai = tool.getImage(GameBackground.class
			.getResource("/images/OptionsMenuback8.png"));
	// È·ÈÏ°´Å¥
	public static Image dsure = tool.getImage(GameBackground.class
			.getResource("/images/OptionsBackButton8_01.png"));
	public static Image dsure2 = tool.getImage(GameBackground.class
			.getResource("/images/OptionsBackButton8_01_2.png"));
	// ÓÒÏÂ·½×Ö
	public static Image[] dword = new Image[] {
			tool.getImage(GameBackground.class
					.getResource("/images/1500450278_191072.png")),
			tool.getImage(GameBackground.class
					.getResource("/images/1500450360_967726.png")),
			tool.getImage(GameBackground.class
					.getResource("/images/1500425748_2849582_2.png")),
			tool.getImage(GameBackground.class
					.getResource("/images/1500425765_3268672_1.png")),
			tool.getImage(GameBackground.class
					.getResource("/images/1500425748_284958_3_1.png")),
			tool.getImage(GameBackground.class
					.getResource("/images/1500425765_326867_3_1.png")),

	};

	// Êó±ê
	public static Image dmouse = tool.getImage(GameBackground.class
			.getResource("/images/mouse.png"));

	// logo
	public static Image dlogo = tool.getImage(GameBackground.class
			.getResource("/images/SmallLogo.png"));

	// »¨
	public static Image dflower1 = tool.getImage(GameBackground.class
			.getResource("/images/Sunflower_trophy32_01.gif"));
	public static Image dflower2 = tool.getImage(GameBackground.class
			.getResource("/images/Sunflower_trophy32_02.gif"));

	// °ïÖú½çÃæ
	public static Image dhelpBack = tool.getImage(GameBackground.class
			.getResource("/images/dsod3row.png"));
	public static Image dhelpWord = tool.getImage(GameBackground.class
			.getResource("/images/ddHelp.png"));
	public static Image dhelpReturn = tool.getImage(GameBackground.class
			.getResource("/images/003b2fd8.png"));
	// ¹ØÓÚ½çÃæ
	public static Image daboutback = tool.getImage(GameBackground.class
			.getResource("/images/daboutBack.png"));
	// ¶¡Ïà¹ú----end
}	
