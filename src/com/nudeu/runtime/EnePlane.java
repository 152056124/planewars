package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnePlane extends AbstractElf implements Moveable, Drawable {
    private Image image ;
    private final int speed = FrameConstant.GAME_SPEED * 3;
    private Random random = new Random();
    public EnePlane() {
        this(0,0, ImageMap.getImage("ep01"));
    }

    public EnePlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        fire();
    }
    public void fire(){
        GameFrame g = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 990){
        g.eneBullets.add(new EneBullet(getX() + image.getWidth(null) / 2 - ImageMap.getImage("eb01").getWidth(null) / 2
                , getY() + ImageMap.getImage("eb01").getHeight(null),ImageMap.getImage("eb01")));
        }
    }
    @Override
    public void move() {
        setY(getY() + speed);
        crossBorderErase();
    }

    public void crossBorderErase(){
        if(getY() > FrameConstant.HEIGHT) {
            GameFrame a = DateStore.get("gameFrame");
            a.enePlanes.remove(this);
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
}
