package com.nudeu.main;

import com.nudeu.constant.FrameConstant;
import com.nudeu.runtime.*;
import com.nudeu.until.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    private Background background = new Background();

    public final MyPlane myPlane = new MyPlane();
    public final CopyOnWriteArrayList<MyBullet> myBulletList = new CopyOnWriteArrayList<>();
    public final MyBullet myBullett = new MyBullet();
    public final List<EnePlane> enePlanes = new CopyOnWriteArrayList<>();
    public final List<EneBullet> eneBullets = new CopyOnWriteArrayList<>();
    public final List<Prop> props = new CopyOnWriteArrayList<>();
    private Prop prop = new Prop();
    public final EnePlane enePlane = new EnePlane();
    public final Boss boss1 = new Boss(10,30,Boss.get("ss1"));
    public final Boss boss2 = new Boss(50,50,Boss.get("ss2"));
    public final List<Bsbullet> bsbullets = new CopyOnWriteArrayList<>();
    public final List<Bsbullet2> bsbullet2s = new CopyOnWriteArrayList<>();
    public final List<Abossbullet> abossbullets = new CopyOnWriteArrayList<>();
    public final BossA bossA = new BossA();
    public final Art art = new Art();

    public boolean gameOver = true;
    public boolean bossOver = false;
    @Override
    public void paint(Graphics g) {
        background.draw(g);
        if(gameOver){
            myPlane.draw(g);
            for (MyBullet myBullet : myBulletList) {
                myBullet.draw(g);
            }
            for (EnePlane enePlane : enePlanes) {
                enePlane.draw(g);
            }
            for (EneBullet eneBullet : eneBullets) {
                eneBullet.draw(g);
            }
            for (MyBullet myBullet : myBulletList) {
                myBullet.collisionCheck(enePlanes);
            }
            for (EneBullet eneBullet : eneBullets) {
                eneBullet.collisionCheck(myPlane);
            }
            for (EnePlane enePlane : enePlanes) {
                enePlane.collisionCheck(myPlane);
            }
            prop.add();
            for (Prop prop : props) {
                prop.draw(g);
            }
            for (Prop prop : props) {
                prop.collisionCheck(myPlane);
            }

            if(enePlane.getCount() >= FrameConstant.BOSS_APPERA && boss1.getBloodBoss() > 0){
                if(boss1.getBloodBoss() >= FrameConstant.BOSS_TWO){
                    boss1.draw(g);
                    boss1.setOne(true);
                    for (MyBullet myBullet : myBulletList) {
                        myBullet.collisionCheck(boss1);
                    }
                    boss1.collisionCheck();
                }else {
                    boss2.draw(g);
                    boss2.setOne(false);
                    for (MyBullet myBullet : myBulletList) {
                        myBullet.collisionCheck(boss2);
                    }
                    boss2.collisionCheck();
                }
                    for (Bsbullet bsbullet : bsbullets) {
                        bsbullet.draw(g);
                    }
                    for (Bsbullet bsbullet : bsbullets) {
                        bsbullet.collisionCheck(myPlane);
                    }


                    for (Bsbullet2 bsbullet2 : bsbullet2s) {
                        bsbullet2.draw(g);
                    }
                    for (Bsbullet2 bsbullet2 : bsbullet2s) {
                        bsbullet2.collisionCheck(myPlane);
                    }
                }
            if(boss1.getBloodBoss() <= 0){
                bossA.draw(g);
                for (Abossbullet abossbullet1 : abossbullets) {
                    abossbullet1.draw(g);
                }
                for (Abossbullet abossbullet1 : abossbullets) {
                    abossbullet1.collisionCheck(myPlane);
                }
                for (MyBullet myBullet : myBulletList) {
                    myBullet.collisionCheck(bossA);
                }
            }
            if (bossA.getBossAblood() <= 0){
                art.draw(g);
            }

            g.setColor(Color.RED);

            g.drawString("BOSS血量： " + boss1.getBloodBoss(),100,100);
            g.drawString("HP： " + myPlane.getBloodVolue(),100,150);
            g.drawString("得分： " + myBullett.getFraction(),100,200);
            g.drawString("敌机总数" + enePlane.getCount(),100,50);
            g.drawString("三boss" + bossA.getBossAblood(),100,250);

        } else if(bossOver){
            Font font = new Font("宋体",15,30);
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString("GAME"+" "+"PASS" ,FrameConstant.WIDTH / 2 - 60,300);
            g.drawString("最终得分： " + myBullett.getFraction(),FrameConstant.WIDTH / 2 - 60,350);
        }else {
            Font font = new Font("宋体",15,30);
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString("GAME"+" "+"OVER" ,FrameConstant.WIDTH / 2 - 60,300);
        }


    }
    public void init(){
        //设置尺寸
        setSize(FrameConstant.WIDTH,FrameConstant.HEIGHT);
        //设置居中
        setLocationRelativeTo(null);
        //关闭输入法
        enableInputMethods(false);
        //关闭放大窗口
        setResizable(false);
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
        //设置监听器
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myPlane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                myPlane.keyReleased(e);
            }
        });

        enePlanes.add(new EnePlane(300,30, 1));
        enePlanes.add(new EnePlane(50,30, 1));
        enePlanes.add(new EnePlane(300,-30, 2));
        enePlanes.add(new EnePlane(50,60, 4));
        props.add(new Prop(350,40,ImageMap.getImage("pp01")));
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
