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

public class Prop extends AbstractElf implements Moveable, Drawable {
    private Image image;
    private final int speed = FrameConstant.GAME_SPEED * 3;

    public Prop(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public Prop() {
        this(0,0, ImageMap.getImage("pp01"));
    }
    public void add(){

        Random random = new Random();
        GameFrame a = DateStore.get("gameFrame");
        int size = a.props.size();  //道具数
        if ( size < FrameConstant.PROP_COUNT && a.myPlane.getBloodVolue() <= FrameConstant.BLOOD_STASIS){
            a.props.add(new Prop(random.nextInt(FrameConstant.WIDTH - image.getWidth(null)),
                    0, ImageMap.getImage("pp01")));
        }
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),image.getHeight(null),null);
        move();
        System.out.println(1);

    }

    @Override
    public void move() {
        setY(getY() + speed);
        crossBorderErase();
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public void collisionCheck(MyPlane myPlane){
        GameFrame frame = DateStore.get("gameFrame");
        if (myPlane.getRectangle().intersects(this.getRectangle())){
            frame.props.remove(this);
            frame.myPlane.setBloodVolue(frame.myPlane.getBloodVolue() + 1);
        }
    }
    //边缘检测回收
    public void crossBorderErase(){
        if(getY() > FrameConstant.HEIGHT) {
            GameFrame a = DateStore.get("gameFrame");
            a.props.remove(this);
        }
    }
}
