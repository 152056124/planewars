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

public class BossA extends AbstractElf implements Moveable, Drawable {
    public   final List<Image> bossimages = new ArrayList<>();
    private int bossAblood = FrameConstant.BOSS_BLOOD_A;
    public final int r = ImageMap.getImage("sa01").getWidth(null) / 2;

    public int getBossAblood() {
        return bossAblood;
    }

    public void setBossAblood(int bossAblood) {
        this.bossAblood = bossAblood;
    }

    public BossA(int x, int y) {
        super(x, y);

    }

    public BossA() {
        for (int i = 1; i < 10; i++) {
            bossimages.add(ImageMap.getImage("sa0" + i));
        }
    }
    int index = 0;
    @Override
    public void draw(Graphics g) {

        if (bossimages.size() != 0){
            Fire();
            g.drawImage(bossimages.get(index++ ),
                    FrameConstant.WIDTH / 2 - bossimages.get(0).getWidth(null) / 2,
                    100,bossimages.get(0).getWidth(null),
                    bossimages.get(0).getHeight(null),null);
            if (index >= 9){
                index = 0;
            }
        }
    }

    private int angle = 0;

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void Fire(){
        Random random = new Random();
        GameFrame g = DateStore.get("gameFrame");

        if (random.nextInt(1000) > 980) {
            g.abossbullets.add(new Abossbullet(
                    getX() + bossimages.get(0).getWidth(null) / 2 + 30 * (int)Math.cos(g.bossA.getAngle())
                    , getY() + bossimages.get(0).getHeight(null) + 30 * (int) Math.sin(g.bossA.getAngle()), ImageMap.getImage("abb01")));
            g.bossA.setAngle(g.bossA.getAngle() + 30);
            if (g.bossA.getAngle() >= 360 ){
                g.bossA.setAngle(30);
            }
        }
    }


    @Override
    public void move() {

    }

    public Rectangle getRectangle() {
        if (bossimages.size() != 0) {
            return new Rectangle(FrameConstant.WIDTH / 2 - bossimages.get(0).getWidth(null) / 2, 100, bossimages.get(0).getWidth(null), bossimages.get(0).getHeight(null));

        }
        return null;
    }
}
