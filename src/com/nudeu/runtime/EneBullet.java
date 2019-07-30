package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;

public class EneBullet extends AbstractElf implements Moveable, Drawable {
    private Image image ;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public EneBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public EneBullet() {
        this(0,0, ImageMap.getImage("eb01"));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
    }

    @Override
    public void move() {
        setY(getY() + speed);
        crossBorderErase();
    }
    public void crossBorderErase(){
        if(getY() > FrameConstant.HEIGHT) {
            GameFrame a = DateStore.get("gameFrame");
            a.eneBullets.remove(this);
        }
    }
}
