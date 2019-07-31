package com.nudeu.start;


import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;

public class GameStart {
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        DateStore.put("gameFrame",gameFrame);
        gameFrame.init();


    }
}
