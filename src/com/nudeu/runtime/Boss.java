package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.base.Moveable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Boss extends AbstractElf implements  Drawable {
    public final static Map<String,Image> images = new HashMap<>();
    static {
        images.put("ss1",ImageMap.getImage("ss01"));
        images.put("ss2",ImageMap.getImage("ss02"));
    }
    public static Image get(String key){
        return images.get(key);
    }
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 2;
    private boolean right = true;
    private boolean up = true;
    private int bloodBoss = FrameConstant.BOSS_BLOOD;
    private boolean isOne = true;


    public boolean isOne() {
        return isOne;
    }

    public void setOne(boolean one) {
        isOne = one;
    }

    public int getBloodBoss() {
        return bloodBoss;
    }

    public void setBloodBoss(int bloodBoss) {
        this.bloodBoss = bloodBoss;
    }

    public Boss(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public Boss() {
    }


    @Override
    public void draw(Graphics g) {
        GameFrame frame = DateStore.get("gameFrame");
        move(isOne);
        fire(isOne);
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        g.drawRoundRect(10,30,FrameConstant.BOSS_BLOOD * 3,20,5,5);
        g.setColor(Color.RED);
        g.fillRoundRect(10,30,frame.boss1.getBloodBoss() * 3,20,5,5);
    }

    public void fire(boolean isOne) {
        if (isOne){
            Random random = new Random();
            GameFrame g = DateStore.get("gameFrame");
            if (random.nextInt(1000) > 980) {
                g.bsbullets.add(new Bsbullet(getX() + image.getWidth(null) / 2 - ImageMap.getImage("bb01").getWidth(null) / 2
                        , getY() + ImageMap.getImage("bb01").getHeight(null), ImageMap.getImage("bb01")));
            }
        }else {
            Random random = new Random();
            GameFrame g = DateStore.get("gameFrame");
            if (random.nextInt(1000) > 980) {
                g.bsbullets.add(new Bsbullet(getX() + image.getWidth(null) / 2 - ImageMap.getImage("bb02").getWidth(null) / 2
                        , getY() + ImageMap.getImage("bb02").getHeight(null), ImageMap.getImage("bb02")));
            }
        }

    }

    public void move(boolean isOne) {
            crossBorderErase(isOne);
            if(isOne){
                if (right) {
                    setX(getX() + speed);
                } else {
                    setX(getX() - speed);
                }
            }else {
                if(right && up){
                    setX(getX() + speed);
                    setY(getY() + speed);
                }else {
                    setX(getX() - speed);
                    setY(getY() - speed);
                }
            }

    }

    public void crossBorderErase(boolean isOne) {
        if(isOne){
            if (getX() > FrameConstant.WIDTH - image.getWidth(null)) {
                right = false;
            }
            if (getX() < 0) {
                right = true;
            }
        }else {
            if (getX() > FrameConstant.WIDTH - image.getWidth(null)) {
                right = false;
            }
            if (getX() < 0) {
                right = true;
            }
            if(getY() > 30 + image.getHeight(null)){
                up = false;
            }
            if(getY() < 30){
                up = true;
            }
        }

    }

    public Rectangle getRectangle() {
            return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));

    }

    public boolean isDropBlood = true;


    public void collisionCheck() {
        GameFrame frame = DateStore.get("gameFrame");
        if(true){
            if (frame.myPlane.getRectangle().intersects(frame.boss1.getRectangle()) && isDropBlood) {
                frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() - 1);
                frame.boss1.setBloodBoss(frame.boss1.getBloodBoss() - 1);
                isDropBlood = false;

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            isDropBlood = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                if (frame.myPlane.getBloodVolue() <= 0) {
                    //frame.gameOver = false;

                }
            }
        }else {
            if (frame.myPlane.getRectangle().intersects(frame.boss2.getRectangle()) && isDropBlood) {
                frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() - 1);
                frame.boss2.setBloodBoss(frame.boss2.getBloodBoss() - 1);
                isDropBlood = false;

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            isDropBlood = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                if (frame.myPlane.getBloodVolue() <= 0) {
                    //frame.gameOver = false;
                    images.remove("ss2");
                }
            }
        }

    }
}
