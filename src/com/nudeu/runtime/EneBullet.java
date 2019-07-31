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

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

    //攻击检测
    public void collisionCheck(MyPlane myPlane){
        GameFrame frame = DateStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())){
                frame.eneBullets.remove(this);
                frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() - 1);
                //FrameConstant.BLOOD_VOLUE --;
            if (frame.myPlane.getBloodVolue() <= 0){
                frame.gameOver = false;
            }
        }


    }
}
