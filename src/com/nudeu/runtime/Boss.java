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

public class Boss extends AbstractElf implements Moveable, Drawable {
    private Image image1;
    private Image image2;
    private int type;
    private int speed = FrameConstant.GAME_SPEED * 2;
    private boolean right = true;
    private int bloodBoss = FrameConstant.BOSS_BLOOD;

    public int getBloodBoss() {
        return bloodBoss;
    }

    public void setBloodBoss(int bloodBoss) {
        this.bloodBoss = bloodBoss;
    }

    public Boss(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image1 = ImageMap.getImage("ss01");
        this.image2 = ImageMap.getImage("ss02");
    }


    public Boss() {
        //this(20, 30, 1);
    }

    @Override
    public void draw(Graphics g) {
        if (type == 1) {
            move();
            fire();
            g.drawImage(image1, getX(), getY(), image1.getWidth(null), image1.getHeight(null), null);
        }
        if (type == 2) {
            move();
            fire();
            g.drawImage(image2, getX(), getY(), image2.getWidth(null), image2.getHeight(null), null);
        }

    }

    public void fire() {
        if (type == 1) {
            Random random = new Random();
            GameFrame g = DateStore.get("gameFrame");
            if (random.nextInt(1000) > 980) {
                g.bsbullets.add(new Bsbullet(getX() + image1.getWidth(null) / 2 - ImageMap.getImage("bb01").getWidth(null) / 2
                        , getY() + ImageMap.getImage("bb01").getHeight(null), ImageMap.getImage("bb01")));
            }
        }
        if (type == 2) {
            Random random = new Random();
            GameFrame g = DateStore.get("gameFrame");
            if (random.nextInt(1000) > 980) {
                g.bsbullets.add(new Bsbullet(getX() + image2.getWidth(null) / 2 - ImageMap.getImage("bb01").getWidth(null) / 2
                        , getY() + ImageMap.getImage("bb01").getHeight(null), ImageMap.getImage("bb01")));
            }
        }

    }

    @Override
    public void move() {

        if (type == 1) {
            crossBorderErase();
            if (right) {
                setX(getX() + speed);
            } else {
                setX(getX() - speed);
            }
        }
        if (type == 2) {
            crossBorderErase();
            if (right) {
                setX(getX() + speed);
            } else {
                setX(getX() - speed);
            }
        }


    }

    public void crossBorderErase() {
        if (type == 1) {
            if (getX() > FrameConstant.WIDTH - image1.getWidth(null)) {
                right = false;
            }
            if (getX() < 0) {
                right = true;
            }
        }
        if (type == 2) {
            if (getX() > FrameConstant.WIDTH - image1.getWidth(null)) {
                right = false;
            }
            if (getX() < 0) {
                right = true;
            }
        }

    }

    public Rectangle getRectangle() {
        if (type == 1) {
            return new Rectangle(getX(), getY(), image1.getWidth(null), image1.getHeight(null));
        } else {
            return new Rectangle(getX(), getY(), image2.getWidth(null), image2.getHeight(null));
        }
    }

    public boolean isDropBlood = true;

    public void collisionCheck() {
        GameFrame frame = DateStore.get("gameFrame");
        if (frame.myPlane.getRectangle().intersects(frame.boss1.getRectangle()) && isDropBlood) {
            frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() - 1);
            if (type == 1) {
                frame.boss1.setBloodBoss(frame.boss1.getBloodBoss() - 1);
                isDropBlood = false;
            }
            if (type == 2) {
                frame.boss2.setBloodBoss(frame.boss2.getBloodBoss() - 1);
                isDropBlood = false;
            }

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
                frame.gameOver = false;
            }
        }
    }
}
