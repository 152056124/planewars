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
    private Image image2 ;
    private Image image3 ;
    private final int speed = FrameConstant.GAME_SPEED * 3;
    private Random random = new Random();
    private int type;
    private int count = 4; // 出现的敌机总数


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public EnePlane() {
        this(0,0, 1);
    }

    public EnePlane(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.getImage("ep01");
        this.image2 = ImageMap.getImage("ep02");
        this.image3 = ImageMap.getImage("ep03");
    }

    @Override
    public void draw(Graphics g) {
        move();
        add();
        fire();
        if (type == 1){
            g.drawImage(image3,getX(),getY(),image3.getWidth(null) - 8,image3.getHeight(null) - 8,null);
        }else if (type == 2){
            g.drawImage(image2,getX(),getY(),image2.getWidth(null) - 8,image2.getHeight(null) - 8,null);
        }else {
            g.drawImage(image,getX(),getY(),image.getWidth(null) - 8,image.getHeight(null) - 8,null);

        }
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
    //边缘检测回收
    public void crossBorderErase(){
        if(getY() > FrameConstant.HEIGHT) {
            GameFrame a = DateStore.get("gameFrame");
            a.enePlanes.remove(this);
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public void add(){
        Random random = new Random();
        GameFrame a = DateStore.get("gameFrame");
        int size = a.enePlanes.size();  //敌方飞机数
        if (size < FrameConstant.ENEPLANE_COUNT){
            a.enePlanes.add(new EnePlane(random.nextInt(FrameConstant.WIDTH - image.getWidth(null)),
                    0,random.nextInt(4)));
            a.enePlane.setCount(a.enePlane.getCount() + 1);//每add一架飞机，飞机总数+1
        }

    }
    //攻击检测
    public void collisionCheck(MyPlane myPlane){
        GameFrame frame = DateStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())){
            frame.enePlanes.remove(this);
            frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() - 1);
            if (frame.myPlane.getBloodVolue() <= 0){
                frame.gameOver = false;
            }
        }
    }
}
