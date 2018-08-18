import java.util.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;

public class Game{
    double mX, mY, prevX, prevY;
    boolean win,gameover;
    Obstacle[] obst;

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
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 30));
        StdDraw.enableDoubleBuffering();
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
        obst = new Obstacle[10];
        obst[0]=new Obstacle(500,600,20, 300);
        obst[1] = new Obstacle(1300,300, 20, 300);
        for (int i =0;i<2;i++){
            obst[i].setMovement(200,800,20, false,-1);
        }
        for(int i = 2; i < 10; i++){
        		obst[i] = new Obstacle(Math.random()*1200+200, Math.random()*800+100, 20, 300);
        		obst[i].setMovement(200, 800, Math.random()*30+10, false,1);
        }
        try{
            Robot r = new Robot();
            r.mouseMove(100,400);
        }catch(Exception e){
            System.err.println("Error on mouseMove");
        }    
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
    }

    public void draw(){  
        StdDraw.clear();
        StdDraw.setPenRadius(0.15);
        //StdDraw.setPenColor(StdDraw.BLUE);
        //StdDraw.filledCircle(mX,mY,3.0);
        StdDraw.picture(mX,mY,"rsz_1airplane.png");
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.rectangle(1850,540,70,100);
			

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
        if(mX>1780 && mX < 1920 && mY>440 && mY < 640){
        	gameover = true;
          win = true;
        }
    }

    public void win(){
        StdDraw.clear();
        StdDraw.text(200,200,"You Win!");
        if(StdDraw.mousePressed()){
            init();
        }
    }

    public void lose(){
        StdDraw.clear();
        StdDraw.text(200,200,"You Lose!");

        if(StdDraw.mousePressed()){
            init();
        }
    }
}