package com.nudeu.runtime;

import com.nudeu.base.AbstractElf;
import com.nudeu.base.Drawable;
import com.nudeu.constant.FrameConstant;
import com.nudeu.main.GameFrame;
import com.nudeu.until.DateStore;
import com.nudeu.until.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Art extends AbstractElf implements Drawable {
    private  final List<Image> atrImages = new ArrayList<>();
    public Art(){
        for (int i = 1; i < 10; i++) {
            atrImages.add(ImageMap.getImage("e" + i));
        }
    }
    private int index;
    @Override
    public void draw(Graphics g) {
        GameFrame frame = DateStore.get("gameFrame");
        if (frame.bossA.bossimages.size() == 0){
            g.drawImage(atrImages.get(index++ ),
                    FrameConstant.WIDTH / 2 - ImageMap.getImage("sa01").getWidth(null) / 2,
                    100,ImageMap.getImage("sa01").getWidth(null),
                    ImageMap.getImage("sa01").getHeight(null),null);
            if (index >= 9){
                index = 0;
            }
        }

    }

}
