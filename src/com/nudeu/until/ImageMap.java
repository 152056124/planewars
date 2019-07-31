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
       imageHashMap.put("mb01",ImageUntil.getImage("com\\nudeu\\image\\mybullet\\myb_1.png"));
       //敌方子弹
       imageHashMap.put("eb01",ImageUntil.getImage("com\\nudeu\\image\\enebullet\\ene_bullet.png"));

   }
   public static Image getImage(String key){
       return imageHashMap.get(key);
   }
}
