package com.nudeu.until;

import java.awt.*;
import java.util.HashMap;

public class ImageMap {
   private static final HashMap<String, Image> imageHashMap = new HashMap<>();
   static {
       //背景
       imageHashMap.put("bg01",ImageUntil.getImage("com\\nudeu\\image\\bg\\bg1.jpg"));
       //我方飞机
       imageHashMap.put("my01",ImageUntil.getImage("com\\nudeu\\image\\myplane\\my_1.png"));
       //敌方飞机
       imageHashMap.put("ep01",ImageUntil.getImage("com\\nudeu\\image\\eneplane\\ep_1.png"));
       imageHashMap.put("ep02",ImageUntil.getImage("com\\nudeu\\image\\eneplane\\ep_2.png"));
       imageHashMap.put("ep03",ImageUntil.getImage("com\\nudeu\\image\\eneplane\\ep_3.png"));
       //我方子弹
       imageHashMap.put("mb01",ImageUntil.getImage("com\\nudeu\\image\\mybullet\\mb_1.png"));
       //敌方子弹
       imageHashMap.put("eb01",ImageUntil.getImage("com\\nudeu\\image\\enebullet\\ene_bullet.png"));
       //道具
       imageHashMap.put("pp01",ImageUntil.getImage("com\\nudeu\\image\\prop\\pp01.png"));//加血
       //imageHashMap.put("pp02",ImageUntil.getImage("com\\nudeu\\image\\prop\\pp02.png"));//护盾
       //imageHashMap.put("pp03",ImageUntil.getImage("com\\nudeu\\image\\prop\\pp03.png"));//回蓝、魔法阵
       //imageHashMap.put("pp04",ImageUntil.getImage("com\\nudeu\\image\\prop\\pp04.png"));//加强子弹
       //BOSS
       imageHashMap.put("ss01",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_1.png"));
       imageHashMap.put("ss02",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_2.png"));

       imageHashMap.put("sa01",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_01.png"));
       imageHashMap.put("sa02",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_02.png"));
       imageHashMap.put("sa03",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_03.png"));
       imageHashMap.put("sa04",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_04.png"));
       imageHashMap.put("sa05",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_05.png"));
       imageHashMap.put("sa06",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_06.png"));
       imageHashMap.put("sa07",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_07.png"));
       imageHashMap.put("sa08",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_08.png"));
       imageHashMap.put("sa09",ImageUntil.getImage("com\\nudeu\\image\\boss\\boss_A_09.png"));
       //Boss子弹
       imageHashMap.put("bb01",ImageUntil.getImage("com\\nudeu\\image\\bbullet\\bb01.png"));
       imageHashMap.put("bb02",ImageUntil.getImage("com\\nudeu\\image\\bbullet\\bb02.png"));//二阶段

       imageHashMap.put("abb01",ImageUntil.getImage("com\\nudeu\\image\\bbullet\\abb01.png"));
       //爆炸就是艺术
       imageHashMap.put("e1",ImageUntil.getImage("com\\nudeu\\image\\art\\e1.png"));
       imageHashMap.put("e2",ImageUntil.getImage("com\\nudeu\\image\\art\\e2.png"));
       imageHashMap.put("e3",ImageUntil.getImage("com\\nudeu\\image\\art\\e3.png"));
       imageHashMap.put("e4",ImageUntil.getImage("com\\nudeu\\image\\art\\e4.png"));
       imageHashMap.put("e5",ImageUntil.getImage("com\\nudeu\\image\\art\\e5.png"));
       imageHashMap.put("e6",ImageUntil.getImage("com\\nudeu\\image\\art\\e6.png"));
       imageHashMap.put("e7",ImageUntil.getImage("com\\nudeu\\image\\art\\e7.png"));
       imageHashMap.put("e8",ImageUntil.getImage("com\\nudeu\\image\\art\\e8.png"));
       imageHashMap.put("e9",ImageUntil.getImage("com\\nudeu\\image\\art\\e9.png"));

   }
   public static Image getImage(String key){
       return imageHashMap.get(key);
   }
}
