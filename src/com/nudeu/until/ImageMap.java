package com.nudeu.until;

import java.awt.*;
import java.util.HashMap;

public class ImageMap {
   private static final HashMap<String, Image> imageHashMap = new HashMap<>();
   static {
       imageHashMap.put("bg01",ImageUntil.getImage("com\\nudeu\\image\\bg\\bg1.png"));
   }
   public static Image getImage(String key){
       return imageHashMap.get(key);
   }
}
