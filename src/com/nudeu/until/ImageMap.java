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
       //Boss子弹
       imageHashMap.put("bb01",ImageUntil.getImage("com\\nudeu\\image\\bbullet\\bb01.png"));


   }
   public static Image getImage(String key){
       return imageHashMap.get(key);
   }
}
