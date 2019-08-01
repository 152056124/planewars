package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;

public class Abossbullet extends AbstractElf implements Moveable, Drawable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 3;

    public Abossbullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public Abossbullet() {
        this(0,0, ImageMap.getImage("abb01"));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
    }

    @Override
    public void move() {
        GameFrame frame = DateStore.get("gameFrame");
        System.out.println(frame.bossA.getAngle());
        setX(getX() + 20 * (int)Math.cos(Math.toRadians(frame.bossA.getAngle())));
        setY(getY() + 20 * (int)Math.sin(Math.toRadians(frame.bossA.getAngle())));
        crossBorderErase();
    }
    public void crossBorderErase(){
        if(getY() > FrameConstant.HEIGHT || getY() < 0 || getX() < 0 || getX() > FrameConstant.WIDTH) {
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
            frame.abossbullets.remove(this);
            frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() - 2);//boss伤害翻倍
            if (frame.myPlane.getBloodVolue() <= 0){
                frame.gameOver = false;
            }
        }
    }
}
