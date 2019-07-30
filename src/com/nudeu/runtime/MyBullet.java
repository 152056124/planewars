package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;

public class MyBullet extends AbstractElf implements Moveable, Drawable {
    private Image image ;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public MyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public MyBullet() {
        this(0,0, ImageMap.getImage("mb01"));
    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        crossBorderErase();
    }

    @Override
    public void move() {
        setY(getY() - speed);
    }
    //子弹越界擦除
    public void crossBorderErase(){
        if(getY() < 30 - image.getHeight(null)) {
            GameFrame a = DateStore.get("gameFrame");
            a.myBulletList.remove(this);
        }
    }
}