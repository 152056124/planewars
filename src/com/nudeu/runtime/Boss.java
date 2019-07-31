package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends AbstractElf implements Moveable, Drawable {
    private Image image ;
    private int speed = FrameConstant.GAME_SPEED * 2;
    private boolean right = true;
    private int bloodBoss = 50;

    public int getBloodBoss() {
        return bloodBoss;
    }

    public void setBloodBoss(int bloodBoss) {
        this.bloodBoss = bloodBoss;
    }
    //List<Image> imageList = new ArrayList<>();
    //public int type ;

    public Boss(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public Boss() {
        this(10,30, ImageMap.getImage("ss01"));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        fire();
    }

    public void fire(){
        Random random = new Random();
        GameFrame g = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 980){
            g.bsbullets.add(new Bsbullet(getX() + image.getWidth(null) / 2 - ImageMap.getImage("bb01").getWidth(null) / 2
                    , getY() + ImageMap.getImage("bb01").getHeight(null),ImageMap.getImage("bb01")));
        }
    }

    @Override
    public void move() {
        crossBorderErase();
        if (right) {
            setX(getX() + speed);
        }else {
            setX(getX() - speed);
        }

    }
    public void crossBorderErase(){
        if (getX() > FrameConstant.WIDTH - image.getWidth(null)){
            right = false;
        }
        if (getX() < 0){
            right = true;
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    public void collisionCheck(MyPlane myPlane){
        GameFrame frame = DateStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())){
            frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() - 1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (frame.myPlane.getBloodVolue() <= 0){
                frame.gameOver = false;
            }
        }
    }
}
