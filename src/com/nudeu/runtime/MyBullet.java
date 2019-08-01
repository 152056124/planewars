package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;
import java.util.List;

public class MyBullet extends AbstractElf implements Moveable, Drawable {
    private Image image ;
    private int speed = FrameConstant.GAME_SPEED * 5;
    private int fraction = 0;//得分

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }
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
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    //攻击检测
    public void collisionCheck(List<EnePlane> enePlanes){
        GameFrame frame = DateStore.get("gameFrame");
        for (EnePlane enePlane : enePlanes) {
            if (enePlane.getRectangle().intersects(this.getRectangle())){
                enePlanes.remove(enePlane);
                frame.myBullett.setFraction(frame.myBullett.getFraction() + 1 * FrameConstant.BASE_FRACTION);
            }
        }
    }
    public void collisionCheck(Boss boss){
        GameFrame frame = DateStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())){
            frame.myBulletList.remove(this);
            frame.boss1.setBloodBoss(frame.boss1.getBloodBoss() - 1);
            if (frame.boss1.getBloodBoss() <= 0){
                //frame.bossOver = true;
                //frame.gameOver = false;
                Boss.images.remove("ss2");
                frame.myBullett.setFraction(frame.myBullett.getFraction() + 1 * FrameConstant.BOSS_FRACTION);
            }
        }
    }
    public void collisionCheck(BossA bossa){
        GameFrame frame = DateStore.get("gameFrame");
        if (frame.bossA.bossimages.size() != 0){
            if (bossa.getRectangle().intersects(this.getRectangle())){
                frame.myBulletList.remove(this);
                frame.bossA.setBossAblood(frame.bossA.getBossAblood() - 1);
                if (frame.bossA.getBossAblood() <= 0){
                    frame.bossOver = true;
                    frame.bossA.bossimages.removeAll(frame.bossA.bossimages);
                    //frame.gameOver = false;
                    frame.myBullett.setFraction(frame.myBullett.getFraction() + 1 * FrameConstant.BOSS_FRACTION);
                }
            }
        }

    }
}
