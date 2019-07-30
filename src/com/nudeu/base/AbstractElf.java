package com.nudeu.base;

import java.awt.*;

public abstract class AbstractElf {
    private int x; //坐标x
    private int y; //坐标y

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public AbstractElf(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public AbstractElf() {
    }
    public Rectangle getRectangle(){
        return null;
    }
}
