import java.util.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;


import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;

import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game{
  double mX, mY;
    boolean win,gameover;

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
    draw();
    init();
    
    while(1==1){
        update();
    }
  }
  public void draw()
  {
        StdDraw.clear();
        StdDraw.setPenRadius(0.15);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.circle(mX,mY,1.0);
        StdDraw.show();

  }
 
  public void init(){
    mX = 0;
    mY = 0;
    win = false;
    gameover = false;
  }
  public void update(){
    mX=StdDraw.mouseX();
    mY=StdDraw.mouseY();
    if(!gameover){
        checkCollision();
    }
    draw();
  }
  public void checkCollision(){
    //if player collide with other stuff then gameover
    
  }
}