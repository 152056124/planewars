package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MyPlane extends AbstractElf implements Moveable, Drawable {

    private Image image ;
    private  boolean up,left,down,right;
    private boolean fire;
    private int bloodVolue = FrameConstant.BLOOD_VOLUE;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public int getBloodVolue() {
        return bloodVolue;
    }

    public void setBloodVolue(int bloodVolue) {
        this.bloodVolue = bloodVolue;
    }

    public MyPlane() {
        this((FrameConstant.WIDTH  - ImageMap.getImage("my01").getWidth(null)) / 2,
                FrameConstant.HEIGHT - ImageMap.getImage("my01").getHeight(null), ImageMap.getImage("my01"));
    }

    public MyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null) / 2,image.getHeight(null) / 2,null);
        move();
    }

    public void fire(){
        if (fire){
            GameFrame g = DateStore.get("gameFrame");
            g.myBulletList.add(new MyBullet(
                    getX() + image.getWidth(null) / 2 - ImageMap.getImage("mb01").getWidth(null) / 2
                    , getY() - ImageMap.getImage("mb01").getHeight(null),
                    ImageMap.getImage("mb01")));
        }
    }
    public  void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire = true;
        }
    }
    public  void keyReleased(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_W){
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A){
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S){
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D){
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J){
            fire();
            fire = false;
        }
    }
    @Override
    public void move() {
        if (up){
            setY(getY() - speed);
        }
        if (left){
            setX(getX() - speed);
        }
        if (down){
            setY(getY() + speed);
        }
        if (right){
            setX(getX() + speed);
        }
        borderCheck();
    }
    //碰撞边缘检测
    public void borderCheck(){
        if (getX() < 0 ){
            setX(0);
        }
        if (getX() > FrameConstant.WIDTH - image.getWidth(null)) {
            setX(FrameConstant.WIDTH - image.getWidth(null));
        }
        if (getY() < 30){
            setY(30);
        }
        if (getY() > FrameConstant.HEIGHT - image.getHeight(null)){
            setY(FrameConstant.HEIGHT - image.getHeight(null));
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
}
