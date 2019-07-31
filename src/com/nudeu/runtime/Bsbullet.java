package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;

public class Bsbullet extends AbstractElf implements Moveable, Drawable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 3;

    public Bsbullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public Bsbullet() {
        this(0,0, ImageMap.getImage("bb01"));
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
            a.bsbullets.remove(this);
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public void collisionCheck(MyPlane myPlane){
        GameFrame frame = DateStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())){
            frame.bsbullets.remove(this);
            frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() - 2);//boss伤害翻倍
            if (frame.myPlane.getBloodVolue() <= 0){
                frame.gameOver = false;
            }
        }
    }
}
