package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.until.ImageMap;

import java.awt.*;

public class Background extends AbstractElf implements Moveable, Drawable {
    private Image bg ;

    public Background(int x, int y, Image bg) {
        super(x, y);
        this.bg = bg;
    }

    public Background() {
        this(0,FrameConstant.HEIGHT - ImageMap.getImage("bg01").getHeight(null) , ImageMap.getImage("bg01"));
    }

    @Override
    public void move() {
        setY(getY() + FrameConstant.GAME_SPEED);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg,getX(),getY(),bg.getWidth(null),bg.getHeight(null),null);
        move();
    }
}
