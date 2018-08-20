import java.util.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;

public class Game{
    double mX, mY, prevX, prevY;
    boolean win,gameover;
    Obstacle[] obst;
    Level[] levels;
    int curlvl;

    public static void main(String[] args){
        //play game
        new Game().play();
    } 

    public Game(){
        StdDraw.setCanvasSize(1920,1080);
        StdDraw.setXscale(0,1920);
        StdDraw.setYscale(0,1080);
        StdDraw.setPenRadius(0.0005);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 90));
        StdDraw.enableDoubleBuffering();

        curlvl = 0;
        levels = new Level[2];
        for(int i = 0; i < levels.length; i++){
            levels[i] = new Level();
        }

        obst = new Obstacle[10];
        double initial_speed = 5;
        double initial_x_obst_post = 220;
        double initial_y_obst_post = 600;
        double initial_wid_obst = 20;
        double initial_hei_obst = 50;
        obst[0] = new Obstacle(initial_x_obst_post,initial_y_obst_post,initial_wid_obst,initial_hei_obst);
        obst[0].setMovement(200,800,initial_speed,false,-1);
        //obst[0]=new Obstacle(500,600,20, 300);
        //bst[1] = new Obstacle(1300,300, 20, 300);
        /*for (int i =0;i<2;i++){
        obst[i].setMovement(200,800,20, false,-1);
        }
        for(int i = 2; i < 10; i++){
        obst[i] = new Obstacle(Math.random()*1200+200, Math.random()*800+100, 20, 300);
        obst[i].setMovement(200, 800, Math.random()*30+10, false,1);
        }*/
        for(int i = 1; i<10; i++)
        {
            initial_speed += 2;
            initial_x_obst_post += 150;
            initial_y_obst_post += 100;
            initial_hei_obst += 20;
            obst[i] = new Obstacle(initial_x_obst_post,initial_y_obst_post,initial_wid_obst,initial_hei_obst);
            obst[i].setMovement(200,800,initial_speed,false,1);
        }
        levels[0].setObstacles(obst);

        Random ran = new Random();
        Obstacle[] temp = new Obstacle[10];
        for(int i = 0; i<10; i++)
        {
            temp[i] = new Obstacle(Math.random()*1600+400,Math.random()*800,Math.random()*200+10,Math.random()*200+10);
            boolean moveX = ran.nextBoolean();
            double dir = Math.random()*2-1;
            double low, high;
            double speed = Math.random()*23+2;
            if(moveX){
                low = Math.random()*1600+200;
                high = Math.random()*1600+200;
            }else{
                low = Math.random()*800;
                high = Math.random()*800;
            }
            if(low > high){
                high += low;
                low = high-low;
                high -= low;
            }
            if(high-low < 1000){
                high += 100;
                low -= 100;
            }
            temp[i].setMovement(low,high,speed,moveX,dir);
        }
        levels[1].setObstacles(temp);
    }

    public void play(){
        init();
        draw();
        while(1==1){
            update();
        }
    }

    public void init(){
        mX = 0;
        mY = 0;
        prevX = 0;
        prevY = 0;
        win = false;
        gameover = false;

        try{
            Robot r = new Robot();
            r.mouseMove(100,400);
        }catch(Exception e){
            System.err.println("Error on mouseMove");
        }
        StdDraw.pause(100);
    }

    public void update(){
        prevX = mX;
        prevY = mY;
        mX=StdDraw.mouseX();
        mY=StdDraw.mouseY()-20;

        if(!gameover){
            for(Obstacle obj: obst){
                obj.move();
            }
            checkPosition();
            draw();
        }else if(win){
            win();
        }else{
            lose();
        }

        StdDraw.show();
        StdDraw.pause(10);
    }

    public void draw(){  
        StdDraw.clear();
        StdDraw.setPenRadius(0.15);
        //StdDraw.setPenColor(StdDraw.BLUE);
        //StdDraw.filledCircle(mX,mY,3.0);
        StdDraw.picture(mX,mY,"rsz_1airplane.png");
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.rectangle(1850,540,70,100);
        StdDraw.picture(870,1020,"UFO.png");
        StdDraw.picture(100,900,"UFO.png");
        StdDraw.picture(1600,1100,"UFO.png");

        for (int i =0;i<obst.length;i++){
            obst[i].paint();
        }
    }

    public void checkPosition(){
        //if player collide with other stuff then gameover
        for(Obstacle obj: obst){
            if(obj.isInside(mX,mY)||obj.passedThrough(prevX, prevY, mX, mY)){
                gameover = true;
            }

        }
        if(mX>0 && mX < 370 && mY>800 && mY < 1080){
            gameover = true;
            win = false;
        }
        if(mX>370 && mX < 1270 && mY>930 && mY < 1080){
            gameover = true;
            win = false;
        }
        if(mX>1270 && mX < 1920 && mY>1000 && mY < 1080){
            gameover = true;
            win = false;
        }
        if(mX>1780 && mX < 1920 && mY>440 && mY < 640){
            gameover = true;
            win = true;
        }
    }

    public void win(){
        StdDraw.clear();
        if(StdDraw.mousePressed()){
            curlvl +=1;
            if(curlvl < levels.length){
                loadLevel(levels[curlvl]);
            }else{
                levels = Arrays.copyOf(levels,levels.length+1);
                levels[levels.length-1] = getRandomLevel();
                if(curlvl < levels.length)
                    loadLevel(levels[curlvl]);
            }
        }
        if(!(curlvl < levels.length)){
            StdDraw.text(800,400, "Try Random Levels!");
        }else{
            StdDraw.text(200,200,"You Win!");
        }
    }

    public void lose(){
        StdDraw.clear();
        StdDraw.text(200,200,"You Lose!");

        if(StdDraw.mousePressed()){
            loadLevel(levels[curlvl]);
        }
    }

    public void loadLevel(Level l){
        init();
        obst = new Obstacle[l.obst.length];
        for(int i = 0; i < obst.length; i++){
            obst[i] = l.obst[i].copy();
        }
    }
    
    public Level getRandomLevel(){
        Random ran = new Random();
        Obstacle[] temp = new Obstacle[10];
        for(int i = 0; i<10; i++)
        {
            temp[i] = new Obstacle(Math.random()*1600+400,Math.random()*800,Math.random()*200+10,Math.random()*200+10);
            boolean moveX = ran.nextBoolean();
            double dir = Math.random()*2-1;
            double low, high;
            double speed = Math.random()*23+2;
            if(moveX){
                low = Math.random()*1600+200;
                high = Math.random()*1600+200;
            }else{
                low = Math.random()*800;
                high = Math.random()*800;
            }
            if(low > high){
                high += low;
                low = high-low;
                high -= low;
            }
            if(high-low < 1000){
                high += 100;
                low -= 100;
            }
            temp[i].setMovement(low,high,speed,moveX,dir);
        }
        Level l = new Level();
        l.setObstacles(temp);
        return l;
    }
}