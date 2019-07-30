package com.nudeu.main;

import com.nudeu.constant.FrameConstant;
import com.nudeu.runtime.Background;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
    private Background background = new Background();
    @Override
    public void paint(Graphics g) {
        background.draw(g);
    }
    public void init(){
        //设置尺寸
        setSize(FrameConstant.WIDTH,FrameConstant.HEIGHT);
        //设置居中
        setLocationRelativeTo(null);
        //关闭输入法
        enableInputMethods(false);
        //设置关闭监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //设置刷新频率：1000ms/x
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(FrameConstant.REFRESH_RATE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //设置可显示
        setVisible(true);
    }
    //双缓冲解决闪屏
    private Image ImageBuffer = null;
    private Graphics GraImage = null;
    public void update(Graphics g){
        ImageBuffer = createImage(FrameConstant.WIDTH, FrameConstant.HEIGHT);//创建图形缓冲区
        GraImage = ImageBuffer.getGraphics();//获取图形缓冲区的图形上下文
        paint(GraImage);//用paint方法中编写的绘图过程对图形缓冲区绘图
        GraImage.dispose();//释放图形上下文资源
        g.drawImage(ImageBuffer, 0, 0, this);//将图形缓冲区绘制到屏幕上
    }
}
