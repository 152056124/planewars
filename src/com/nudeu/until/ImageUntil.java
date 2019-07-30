package com.nudeu.until;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * 读取图片工具类
 */
public class ImageUntil {
    public static Image getImage(String path){
        Image image = null;
        try {
            image = ImageIO.read(ImageUntil.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
